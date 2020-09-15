package com.simple4j.user.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.simple4j.autoconfigure.jwt.annotation.AnonymousAccess;
import com.simple4j.user.response.CaptchaResponse;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/captcha")
@Api(tags = "验证码")
public class CaptchaController {

	@Autowired
	private RedisTemplate redisTemplate;

	@PostMapping("")
	@ApiOperation(value = "获取验证码")
	@AnonymousAccess
	public ApiResponse<CaptchaResponse> captcha() {
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
		return ApiResponse.ok(captchaResponse);
	}
}
