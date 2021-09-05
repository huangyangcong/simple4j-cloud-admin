package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class UserRoleGrantRequest {

	@Parameter(name = "user_ids", description = "用户编号列表")
	@JsonProperty("user_ids")
	Set<String> userIds;

	@Parameter(name = "role_ids", description = "用户角色列表")
	@JsonProperty("role_ids")
	Set<String> roleIds;
}
