package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class UserDeptGrantRequest {

	@Parameter(name = "user_ids", description = "用户编号列表")
	@JsonProperty("user_ids")
	Set<String> userIds;

	@Parameter(name = "dept_ids", description = "用户部门列表")
	@JsonProperty("dept_ids")
	Set<String> deptIds;
}
