package com.simple4j.system.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuRemoveRequest {

	@ApiModelProperty(value = "菜单删除编号列表", name = "menu_ids")
	@JsonProperty("menu_ids")
	private Set<String> menuIds;
}
