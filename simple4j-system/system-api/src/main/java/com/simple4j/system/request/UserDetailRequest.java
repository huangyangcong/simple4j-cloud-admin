package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class UserDetailRequest {

	@Parameter(description = "用户编号")
	private String id;
}
