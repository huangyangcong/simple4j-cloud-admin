package com.simple4j.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema
public class UserLoginRequest {

	@Parameter(name = "username", description = "用户名")
	@NotBlank(message = "用户名不能为空")
	@JsonProperty("username")
	private String username;

	@Parameter(name = "password", description = "密码")
	@NotBlank(message = "密码不能为空")
	@JsonProperty("password")
	private String password;


	@Parameter(name = "captcha_key", description = "验证码key")
	@JsonProperty("captcha_key")
	private String captchaKey;

	@Parameter(name = "captcha_code", description = "验证码")
	@JsonProperty("captcha_code")
	private String captchaCode;
}
