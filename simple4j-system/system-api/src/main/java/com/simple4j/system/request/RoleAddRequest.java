package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "角色表新增请求实体类", description = "角色表新增请求实体类")
public class RoleAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 父主键
	 */
	@ApiModelProperty(name = "parent_id", value = "父主键")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 角色名
	 */
	@ApiModelProperty(name = "role_name", value = "角色名")
	@JsonProperty("role_name")
	private String roleName;
	/**
	 * 排序
	 */
	@ApiModelProperty(name = "sort", value = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 角色别名
	 */
	@ApiModelProperty(name = "role_alias", value = "角色别名")
	@JsonProperty("role_alias")
	private String roleAlias;


}
