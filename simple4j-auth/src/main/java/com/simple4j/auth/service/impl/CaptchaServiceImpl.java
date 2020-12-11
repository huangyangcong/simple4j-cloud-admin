package com.simple4j.auth.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import com.simple4j.api.base.BusinessException;
import com.simple4j.auth.constant.AuthConstant;
import com.simple4j.auth.response.CaptchaResponse;
import com.simple4j.auth.service.ICaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
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

	private static final String KEY = "blade:auth::captcha:";


	@Override
	public CaptchaResponse captcha(HttpSession httpSession) {
		CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(130, 48);
		String imageBase64 = circleCaptcha.getImageBase64();
		String key = UUID.randomUUID().toString();
		// 将key和base64返回给前端
		CaptchaResponse captchaResponse = new CaptchaResponse();
		captchaResponse.setKey(key);
		captchaResponse.setImage("data:image/jpg;base64," + imageBase64);
		httpSession.setAttribute(AuthConstant.CAPTCHA_CODE, captchaResponse);
		return captchaResponse;
	}

	@Override
	public void verify(HttpSession httpSession, String captchaKey, String captchaCode) {
		CaptchaResponse captchaResponse = (CaptchaResponse)httpSession.getAttribute(AuthConstant.CAPTCHA_CODE);
		// 判断验证码
		if (captchaResponse == null || !captchaKey.equals(captchaResponse.getKey()) || !StrUtil.equalsIgnoreCase(captchaResponse.getImage(), captchaCode)) {
			throw new BusinessException("验证码错误");
		}
	}

	@Override
	public void deleteCaptcha(HttpSession httpSession) {
		httpSession.removeAttribute(AuthConstant.CAPTCHA_CODE);
	}
}