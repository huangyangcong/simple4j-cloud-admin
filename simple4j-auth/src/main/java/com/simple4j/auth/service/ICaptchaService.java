package com.simple4j.auth.service;


import com.simple4j.auth.response.CaptchaResponse;

import javax.servlet.http.HttpSession;

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
	CaptchaResponse captcha(HttpSession httpSession);

	/**
	 * 校验验证码
	 *
	 * @param captchaKey
	 * @param captchaCode
	 * @return
	 */
	void verify(HttpSession httpSession, String captchaKey, String captchaCode);

	/**
	 * 删除验证码
	 *
	 * @param captchaKey
	 */
	void deleteCaptcha(HttpSession httpSession);
}