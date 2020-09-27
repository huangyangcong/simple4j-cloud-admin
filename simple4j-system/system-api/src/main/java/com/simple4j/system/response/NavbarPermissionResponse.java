package com.simple4j.system.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NavbarPermissionResponse {

	/**
	 * 顶部菜单编号列表
	 */
	@ApiModelProperty(name = "menu_ids", value = "顶部菜单编号列表")
	@JsonProperty("menu_ids")
	private List<Long> menuIds;
}
