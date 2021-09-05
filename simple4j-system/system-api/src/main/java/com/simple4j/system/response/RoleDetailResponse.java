package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.api.base.INode;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色表详情响应实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "角色表详情响应实体类", description = "角色表详情响应实体类")
public class RoleDetailResponse implements INode<RoleDetailResponse, String> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Parameter(name = "id", description = "主键")
	@JsonProperty("id")
	@Getter(onMethod = @__({@JsonIgnore}))
	private String id;

	/**
	 * 租户编号
	 */
	@Parameter(name = "tenant_id", description = "租户编号")
	@JsonProperty("tenant_id")
	private String tenantId;

	/**
	 * 父主键
	 */
	@Parameter(name = "parent_id", description = "父主键")
	@JsonProperty("parent_id")
	@Getter(onMethod = @__({@JsonIgnore}))
	private String parentId;
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
	/**
	 * 是否已删除
	 */
	@Parameter(name = "is_deleted", description = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<RoleDetailResponse> children;

	/**
	 * 上级角色
	 */
	private String parentName;

	@Override
	public void addChildren(com.simple4j.system.response.RoleDetailResponse children) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(children);
	}

	@Override
	public int compareTo(com.simple4j.system.response.RoleDetailResponse o) {
		if (o.getSort().equals(this.getSort())) {
			return 0;
		} else if (o.getSort() > this.getSort()) {
			return -1;
		} else {
			return 1;
		}
	}
}
