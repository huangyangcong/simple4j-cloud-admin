package com.simple4j.user.service;

import com.simple4j.user.response.CaptchaResponse;

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
	CaptchaResponse captcha();
}
