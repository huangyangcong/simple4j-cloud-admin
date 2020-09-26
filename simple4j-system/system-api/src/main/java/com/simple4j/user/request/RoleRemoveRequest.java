package com.simple4j.user.request;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleRemoveRequest {

	@ApiModelProperty(value = "角色删除编号列表", name = "role_ids")
	@JsonProperty("role_ids")
	private List<Long> roleIds;
}
