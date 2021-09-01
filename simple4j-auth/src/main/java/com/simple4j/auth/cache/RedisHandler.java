package com.simple4j.auth.cache;

import com.simple4j.auth.response.CaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisHandler {

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 设置验证码
	 *
	 * @param username        用户名
	 * @param captchaResponse 验证码信息
	 */
	public void setCaptchaCode(String username, CaptchaResponse captchaResponse) {
		HashOperations<String, String, CaptchaResponse> hashOperations = redisTemplate.opsForHash();
		hashOperations.put(RedisKeys.CAPTCHA_CODE, username, captchaResponse);
	}

	/**
	 * 获取验证码
	 *
	 * @param username 用户名
	 */
	public CaptchaResponse getCaptchaCode(String username) {
		HashOperations<String, String, CaptchaResponse> hashOperations = redisTemplate.opsForHash();
		return hashOperations.get(RedisKeys.CAPTCHA_CODE, username);
	}

	/**
	 * 删除验证码
	 *
	 * @param username 用户名
	 */
	public void delCaptchaCode(String username) {
		HashOperations<String, String, CaptchaResponse> hashOperations = redisTemplate.opsForHash();
		hashOperations.delete(RedisKeys.CAPTCHA_CODE, username);
	}
}
