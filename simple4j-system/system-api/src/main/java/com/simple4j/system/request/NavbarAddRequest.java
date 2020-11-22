package com.simple4j.system.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "新增请求实体类", description = "新增请求实体类")
public class NavbarAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单排序
	 */
	@ApiModelProperty(name = "sort", value = "菜单排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 按钮编号
	 */
	@ApiModelProperty(name = "name", value = "按钮编号")
	@JsonProperty("name")
	private String name;
	/**
	 * 租户编号
	 */
	@ApiModelProperty(name = "code", value = "租户编号")
	@JsonProperty("code")
	private String code;
	/**
	 * 图标
	 */
	@ApiModelProperty(name = "icon", value = "图标")
	@JsonProperty("icon")
	private String icon;
}
