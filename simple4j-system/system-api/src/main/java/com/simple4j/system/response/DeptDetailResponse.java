package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.api.base.INode;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表详情响应实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "部门表详情响应实体类", description = "部门表详情响应实体类")
public class DeptDetailResponse implements INode<DeptDetailResponse, String> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Parameter(name = "id", description = "主键")
	@JsonProperty("id")
	@Getter(onMethod = @__({@JsonIgnore}))
	private String id;
	/**
	 * 父主键
	 */
	@Parameter(name = "parent_id", description = "父主键")
	@JsonProperty("parent_id")
	@Getter(onMethod = @__({@JsonIgnore}))
	private String parentId;
	/**
	 * 部门名
	 */
	@Parameter(name = "dept_name", description = "部门名")
	@JsonProperty("dept_name")
	private String deptName;
	/**
	 * 租户编号
	 */
	@Parameter(name = "tenant_id", description = "租户编号")
	@JsonProperty("tenant_id")
	private String tenantId;
	/**
	 * 部门全称
	 */
	@Parameter(name = "full_name", description = "部门全称")
	@JsonProperty("full_name")
	private String fullName;
	/**
	 * 排序
	 */
	@Parameter(name = "sort", description = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 备注
	 */
	@Parameter(name = "remark", description = "备注")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 是否已删除
	 */
	@Parameter(name = "is_deleted", description = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;

	/**
	 * 上级部门名称
	 */
	@Parameter(name = "parent_name", description = "上级部门名称")
	@JsonProperty("parent_name")
	private String parentName;

	/**
	 * 子孙节点
	 */
	private List<DeptDetailResponse> children;

	@Override
	public void addChildren(DeptDetailResponse children) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(children);
	}

	@Override
	public int compareTo(DeptDetailResponse o) {
		if (o.getSort().equals(this.getSort())) {
			return 0;
		} else if (o.getSort() > this.getSort()) {
			return -1;
		} else {
			return 1;
		}
	}
}
