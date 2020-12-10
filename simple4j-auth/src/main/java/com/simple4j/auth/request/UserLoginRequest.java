package com.simple4j.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginRequest {

	@ApiModelProperty(name = "username", value = "用户名")
	@JsonProperty("username")
	private String username;

	@ApiModelProperty(name = "password", value = "用户部门列表")
	@JsonProperty("password")
	private String password;
}
