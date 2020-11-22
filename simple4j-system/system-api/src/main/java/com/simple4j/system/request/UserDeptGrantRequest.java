package com.simple4j.system.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDeptGrantRequest {

	@ApiModelProperty(name = "user_ids", value = "用户编号列表")
	@JsonProperty("user_ids")
	Set<String> userIds;

	@ApiModelProperty(name = "dept_ids", value = "用户部门列表")
	@JsonProperty("dept_ids")
	Set<String> deptIds;
}
