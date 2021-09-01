package com.simple4j.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserLoginRequest {

	@ApiModelProperty(name = "username", value = "用户名")
	@NotBlank(message = "用户名不能为空")
	@JsonProperty("username")
	private String username;

	@ApiModelProperty(name = "password", value = "密码")
	@NotBlank(message = "密码不能为空")
	@JsonProperty("password")
	private String password;


	@ApiModelProperty(name = "captcha_key", value = "验证码key")
	@JsonProperty("captcha_key")
	private String captchaKey;

	@ApiModelProperty(name = "captcha_code", value = "验证码")
	@JsonProperty("captcha_code")
	private String captchaCode;
}
