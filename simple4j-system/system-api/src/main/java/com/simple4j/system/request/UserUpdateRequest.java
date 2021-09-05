package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class UserUpdateRequest extends UserAddRequest {

	/**
	 * 用户ID
	 */
	@Parameter(description = "用户ID")
	private String id;
}
