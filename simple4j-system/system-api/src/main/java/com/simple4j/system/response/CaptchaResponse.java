package com.simple4j.system.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CaptchaResponse {

	/**
	 * 验证码编号
	 */
	@ApiModelProperty(value = "验证码编号")
	private String key;

	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码")
	private String image;
}
