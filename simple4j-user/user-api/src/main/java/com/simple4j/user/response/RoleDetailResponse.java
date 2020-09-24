package com.simple4j.user.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.api.base.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

/**
 * 角色表详情响应实体类
 *
 * @author Blade
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "角色表详情响应实体类", description = "角色表详情响应实体类")
public class RoleDetailResponse implements INode<RoleDetailResponse, Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(name = "id", value = "主键")
	@JsonProperty("id")
	@Getter(onMethod=@__({@JsonIgnore}))
	private Long id;

	/**
	 * 租户编号
	 */
	@ApiModelProperty(name = "tenant_id", value = "租户编号")
	@JsonProperty("tenant_id")
	private String tenantId;

	/**
	 * 父主键
	 */
	@ApiModelProperty(name = "parent_id", value = "父主键")
	@JsonProperty("parent_id")
	@Getter(onMethod=@__({@JsonIgnore}))
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
	/**
	 * 是否已删除
	 */
	@ApiModelProperty(name = "is_deleted", value = "是否已删除")
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
	public void addChildren(com.simple4j.user.response.RoleDetailResponse children) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(children);
	}

	@Override
	public int compareTo(com.simple4j.user.response.RoleDetailResponse o) {
		if (o.getSort().equals(this.getSort())) {
			return 0;
		} else if (o.getSort() > this.getSort()) {
			return -1;
		} else {
			return 1;
		}
	}
}
