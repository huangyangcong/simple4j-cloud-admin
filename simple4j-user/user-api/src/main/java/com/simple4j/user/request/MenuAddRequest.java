package com.simple4j.user.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单表新增请求实体类
 *
 * @author Blade
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "菜单表添新增请求实体类", description = "菜单表添新增请求实体类")
public class MenuAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 父级菜单
	 */
	@ApiModelProperty(name = "parent_id", value = "父级菜单")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 菜单编号
	 */
	@ApiModelProperty(name = "code", value = "菜单编号")
	@JsonProperty("code")
	private String code;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty(name = "name", value = "菜单名称")
	@JsonProperty("name")
	private String name;
	/**
	 * 菜单别名
	 */
	@ApiModelProperty(name = "alias", value = "菜单别名")
	@JsonProperty("alias")
	private String alias;
	/**
	 * 请求地址
	 */
	@ApiModelProperty(name = "path", value = "请求地址")
	@JsonProperty("path")
	private String path;
	/**
	 * 菜单资源
	 */
	@ApiModelProperty(name = "source", value = "菜单资源")
	@JsonProperty("source")
	private String source;
	/**
	 * 排序
	 */
	@ApiModelProperty(name = "sort", value = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 菜单类型
	 */
	@ApiModelProperty(name = "category", value = "菜单类型")
	@JsonProperty("category")
	private Integer category;
	/**
	 * 操作按钮类型
	 */
	@ApiModelProperty(name = "action", value = "操作按钮类型")
	@JsonProperty("action")
	private Integer action;
	/**
	 * 是否打开新页面
	 */
	@ApiModelProperty(name = "is_open", value = "是否打开新页面")
	@JsonProperty("is_open")
	private Integer isOpen;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark", value = "备注")
	@JsonProperty("remark")
	private String remark;


}
