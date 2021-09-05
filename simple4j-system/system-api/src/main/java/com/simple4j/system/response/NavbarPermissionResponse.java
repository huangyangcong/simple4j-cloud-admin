package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class NavbarPermissionResponse {

	/**
	 * 顶部菜单编号列表
	 */
	@Parameter(name = "menu_ids", description = "顶部菜单编号列表")
	@JsonProperty("menu_ids")
	private Set<String> menuIds;
}
