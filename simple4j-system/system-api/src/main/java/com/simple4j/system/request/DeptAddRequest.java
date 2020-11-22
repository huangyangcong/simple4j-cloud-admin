package com.simple4j.system.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "部门表新增请求实体类", description = "部门表新增请求实体类")
public class DeptAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 父主键
	 */
	@ApiModelProperty(name = "parent_id", value = "父主键")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 部门名
	 */
	@ApiModelProperty(name = "dept_name", value = "部门名")
	@JsonProperty("dept_name")
	private String deptName;
	/**
	 * 部门全称
	 */
	@ApiModelProperty(name = "full_name", value = "部门全称")
	@JsonProperty("full_name")
	private String fullName;
	/**
	 * 排序
	 */
	@ApiModelProperty(name = "sort", value = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark", value = "备注")
	@JsonProperty("remark")
	private String remark;
}
