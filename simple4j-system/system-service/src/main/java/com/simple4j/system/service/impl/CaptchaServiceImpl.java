package com.simple4j.system.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import com.simple4j.api.base.BusinessException;
import com.simple4j.system.common.constant.CacheNames;
import com.simple4j.system.response.CaptchaResponse;
import com.simple4j.system.service.ICaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author hyc
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements ICaptchaService {

	private final RedisTemplate redisTemplate;

	@Override
	public CaptchaResponse captcha() {
		CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(130, 48);
		String verCode = circleCaptcha.getCode();
		String imageBase64 = circleCaptcha.getImageBase64();
		String key = UUID.randomUUID().toString();
		// 存入redis并设置过期时间为30分钟
		redisTemplate.opsForValue()
			.set(CacheNames.CAPTCHA_KEY + key, verCode, 30L, TimeUnit.MINUTES);
		// 将key和base64返回给前端
		CaptchaResponse captchaResponse = new CaptchaResponse();
		captchaResponse.setKey(key);
		captchaResponse.setImage("data:image/jpg;base64," + imageBase64);
		return captchaResponse;
	}

	@Override
	public void verify(String captchaKey, String captchaCode) {
		String redisCode =
			String.valueOf(redisTemplate.opsForValue().get(CacheNames.CAPTCHA_KEY + captchaKey));
		// 判断验证码
		if (captchaCode == null || !StrUtil.equalsIgnoreCase(redisCode, captchaCode)) {
			throw new BusinessException("验证码错误");
		}
	}

	@Override
	public void deleteCaptcha(String captchaKey) {
		// 删除验证码
		redisTemplate.delete(CacheNames.CAPTCHA_KEY + captchaKey);
	}
}
