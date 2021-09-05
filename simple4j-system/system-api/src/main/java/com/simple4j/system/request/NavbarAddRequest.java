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
public class NavbarAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单排序
	 */
	@Parameter(name = "sort", description = "菜单排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 按钮编号
	 */
	@Parameter(name = "name", description = "按钮编号")
	@JsonProperty("name")
	private String name;
	/**
	 * 租户编号
	 */
	@Parameter(name = "code", description = "租户编号")
	@JsonProperty("code")
	private String code;
	/**
	 * 图标
	 */
	@Parameter(name = "icon", description = "图标")
	@JsonProperty("icon")
	private String icon;
}
