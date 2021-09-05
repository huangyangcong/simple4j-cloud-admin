package com.simple4j.auth.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

@Data
public class CaptchaResponse implements Serializable {

	/**
	 * 验证码编号
	 */
	@Parameter(description = "验证码编号")
	private String key;

	/**
	 * 验证码
	 */
	@Parameter(description = "验证码")
	private String image;
}
