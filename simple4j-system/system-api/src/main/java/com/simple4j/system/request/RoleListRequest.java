package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class RoleListRequest {

	@Parameter(description = "角色名称", name = "role_name")
	@JsonProperty("role_name")
	private String roleName;

	@Parameter(description = "角色别名", name = "role_alias")
	@JsonProperty("role_alias")
	private String roleAlias;
}
