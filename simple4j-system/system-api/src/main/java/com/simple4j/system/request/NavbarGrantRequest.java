package com.simple4j.user.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NavbarGrantRequest {

	/**
	 * 顶部菜单编号
	 */
	@ApiModelProperty(name = "top_menu_id", value = "顶部菜单编号")
	@JsonProperty("top_menu_id")
	private Long NavbarId;

	/**
	 * 菜单编号
	 */
	@ApiModelProperty(name = "menu_ids", value = "菜单编号")
	@JsonProperty("menu_ids")
	private List<Long> menuIds;

}
