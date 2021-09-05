package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class RoleDetailRequest {

	@Parameter(description = "角色编号")
	private String id;
}
