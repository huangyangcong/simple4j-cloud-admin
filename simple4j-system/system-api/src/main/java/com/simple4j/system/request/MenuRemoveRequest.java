package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class MenuRemoveRequest {

	@Parameter(description = "菜单删除编号列表", name = "menu_ids")
	@JsonProperty("menu_ids")
	private Set<String> menuIds;
}
