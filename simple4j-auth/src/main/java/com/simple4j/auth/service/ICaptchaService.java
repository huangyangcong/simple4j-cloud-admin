package com.simple4j.auth.service;


import com.simple4j.auth.response.CaptchaResponse;

/**
 * @author hyc
 * @version 1.0.0
 */
public interface ICaptchaService {

	/**
	 * 生成图形验证码
	 *
	 * @return
	 */
	CaptchaResponse captcha(String username);

	/**
	 * 校验验证码
	 *
	 * @param captchaKey
	 * @param captchaCode
	 * @return
	 */
	void verify(String username, String captchaKey, String captchaCode);
}
