package com.simple4j.auth.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import com.simple4j.api.base.BusinessException;
import com.simple4j.auth.cache.RedisHandler;
import com.simple4j.auth.response.CaptchaResponse;
import com.simple4j.auth.service.ICaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author hyc
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements ICaptchaService {

	private final RedisHandler redisHandler;

	@Override
	public CaptchaResponse captcha(String username) {
		CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(130, 48);
		String imageBase64 = circleCaptcha.getImageBase64();
		String key = UUID.randomUUID().toString();
		// 将key和base64返回给前端
		CaptchaResponse captchaResponse = new CaptchaResponse();
		captchaResponse.setKey(key);
		captchaResponse.setImage("data:image/jpg;base64," + imageBase64);
		redisHandler.setCaptchaCode(username, captchaResponse);
		return captchaResponse;
	}

	@Override
	public void verify(String username, String captchaKey, String captchaCode) {
		CaptchaResponse captchaResponse = redisHandler.getCaptchaCode(username);
		if (captchaResponse == null) {
			return;
		}
		// 判断验证码
		if (!captchaKey.equals(captchaResponse.getKey()) || !StrUtil.equalsIgnoreCase(captchaResponse.getImage(), captchaCode)) {
			throw new BusinessException("验证码错误");
		}
		redisHandler.delCaptchaCode(username);
	}
}
