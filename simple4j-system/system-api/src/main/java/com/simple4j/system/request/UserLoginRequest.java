package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class UserLoginRequest {

	@Parameter(name = "username", description = "用户名")
	@JsonProperty("username")
	private String username;

	@Parameter(name = "password", description = "用户部门列表")
	@JsonProperty("password")
	private String password;

	@Parameter(name = "captcha_key", description = "验证码key")
	@JsonProperty("captcha_key")
	private String captchaKey;

	@Parameter(name = "captcha_code", description = "验证码")
	@JsonProperty("captcha_code")
	private String captchaCode;
}
