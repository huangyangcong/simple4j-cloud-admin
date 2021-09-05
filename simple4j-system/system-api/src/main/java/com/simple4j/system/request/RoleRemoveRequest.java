package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class RoleRemoveRequest {

	@Parameter(description = "角色删除编号列表", name = "role_ids")
	@JsonProperty("role_ids")
	private Set<String> roleIds;
}
