package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "部门表新增请求实体类", description = "部门表新增请求实体类")
public class DeptAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 父主键
	 */
	@Parameter(name = "parent_id", description = "父主键")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 部门名
	 */
	@Parameter(name = "dept_name", description = "部门名")
	@JsonProperty("dept_name")
	private String deptName;
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
}
