package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class NavbarGrantRequest {

	/**
	 * 顶部菜单编号
	 */
	@Parameter(name = "top_menu_id", description = "顶部菜单编号")
	@JsonProperty("top_menu_id")
	private String NavbarId;

	/**
	 * 菜单编号
	 */
	@Parameter(name = "menu_ids", description = "菜单编号")
	@JsonProperty("menu_ids")
	private Set<String> menuIds;
}
