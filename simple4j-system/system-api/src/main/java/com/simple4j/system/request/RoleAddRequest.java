package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@Schema(name = "角色表新增请求实体类", description = "角色表新增请求实体类")
public class RoleAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 父主键
	 */
	@Parameter(name = "parent_id", description = "父主键")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 角色名
	 */
	@Parameter(name = "role_name", description = "角色名")
	@JsonProperty("role_name")
	private String roleName;
	/**
	 * 排序
	 */
	@Parameter(name = "sort", description = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 角色别名
	 */
	@Parameter(name = "role_alias", description = "角色别名")
	@JsonProperty("role_alias")
	private String roleAlias;
}
