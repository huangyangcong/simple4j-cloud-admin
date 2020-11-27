package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门表分页请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "部门表分页请求实体类", description = "部门表分页请求实体类")
public class DeptPageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@ApiModelProperty(name = "page_no", value = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	/**
	 * 分页数
	 */
	@ApiModelProperty(name = "pageSize", value = "分页数")
	@JsonProperty("page_size")
	private int pageSize;

	@ApiModelProperty(name = "dept_name", value = "部门名称")
	@JsonProperty("dept_name")
	private String deptName;

	@ApiModelProperty(name = "full_name", value = "部门全称")
	@JsonProperty("full_name")
	private String fullName;
}
