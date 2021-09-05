package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "新增请求实体类", description = "新增请求实体类")
public class NavbarMenuAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 按钮编号
	 */
	@Parameter(name = "menu_id", description = "按钮编号")
	@JsonProperty("menu_id")
	private Long menuId;
	/**
	 * 顶部按钮编号
	 */
	@Parameter(name = "top_menu_id", description = "顶部按钮编号")
	@JsonProperty("top_menu_id")
	private Long NavbarId;
}
