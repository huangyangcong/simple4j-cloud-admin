package com.simple4j.auth.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {

	/**
	 * token
	 */
	@Parameter(description = "token")
	private String token;
}
