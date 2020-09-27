package com.simple4j.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "部门表修改请求实体类", description = "部门表修改请求实体类")
public class DeptUpdateRequest extends DeptAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 部门表ID
	 */
	@ApiModelProperty(value = "部门表ID")
	private Long id;

}
