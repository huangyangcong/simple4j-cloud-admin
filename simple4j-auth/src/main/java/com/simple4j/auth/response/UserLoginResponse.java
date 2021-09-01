package com.simple4j.auth.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {

	/**
	 * token
	 */
	@ApiModelProperty(value = "token")
	private String token;
}
