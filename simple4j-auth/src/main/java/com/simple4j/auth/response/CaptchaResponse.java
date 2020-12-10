package com.simple4j.auth.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CaptchaResponse implements Serializable {

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
