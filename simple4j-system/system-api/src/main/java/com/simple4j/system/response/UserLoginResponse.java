package com.simple4j.system.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginResponse {

	/**
	 * token
	 */
	@ApiModelProperty(value = "token")
	private String token;
}
