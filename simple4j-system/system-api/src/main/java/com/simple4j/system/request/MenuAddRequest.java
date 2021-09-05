package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "菜单表新增请求实体类", description = "菜单表新增请求实体类")
public class MenuAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 父级菜单
	 */
	@Parameter(name = "parent_id", description = "父级菜单")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 菜单编号
	 */
	@Parameter(name = "code", description = "菜单编号")
	@JsonProperty("code")
	private String code;
	/**
	 * 菜单名称
	 */
	@Parameter(name = "name", description = "菜单名称")
	@JsonProperty("name")
	private String name;
	/**
	 * 菜单别名
	 */
	@Parameter(name = "alias", description = "菜单别名")
	@JsonProperty("alias")
	private String alias;
	/**
	 * 请求地址
	 */
	@Parameter(name = "path", description = "请求地址")
	@JsonProperty("path")
	private String path;
	/**
	 * 菜单资源
	 */
	@Parameter(name = "source", description = "菜单资源")
	@JsonProperty("source")
	private String source;
	/**
	 * 排序
	 */
	@Parameter(name = "sort", description = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 菜单类型
	 */
	@Parameter(name = "category", description = "菜单类型")
	@JsonProperty("category")
	private Integer category;
	/**
	 * 操作按钮类型
	 */
	@Parameter(name = "action", description = "操作按钮类型")
	@JsonProperty("action")
	private Integer action;
	/**
	 * 是否打开新页面
	 */
	@Parameter(name = "is_open", description = "是否打开新页面")
	@JsonProperty("is_open")
	private Integer isOpen;
	/**
	 * 备注
	 */
	@Parameter(name = "remark", description = "备注")
	@JsonProperty("remark")
	private String remark;
}
