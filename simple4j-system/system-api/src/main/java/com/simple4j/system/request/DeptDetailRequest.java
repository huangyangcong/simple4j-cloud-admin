package com.simple4j.system.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门表详情请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "部门表详情请求实体类", description = "部门表详情请求实体类")
public class DeptDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 部门表详情编号
	 */
	@ApiModelProperty(value = "部门表详情编号", name = "id")
	private String id;
}
