package com.simple4j.system.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoleRemoveRequest {

	@ApiModelProperty(value = "角色删除编号列表", name = "role_ids")
	@JsonProperty("role_ids")
	private List<Long> roleIds;
}
