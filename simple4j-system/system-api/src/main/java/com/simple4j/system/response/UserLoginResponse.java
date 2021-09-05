package com.simple4j.system.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class UserLoginResponse {

	/**
	 * token
	 */
	@Parameter(description = "token")
	private String token;
}
