package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleGrantRequest {

	@ApiModelProperty(name = "user_ids", value = "用户编号列表")
	@JsonProperty("user_ids")
	List<Long> userIds;

	@ApiModelProperty(name = "role_ids", value = "用户角色列表")
	@JsonProperty("role_ids")
	List<Long> roleIds;

}
