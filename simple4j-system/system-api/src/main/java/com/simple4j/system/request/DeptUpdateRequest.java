package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 部门表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "部门表修改请求实体类", description = "部门表修改请求实体类")
public class DeptUpdateRequest extends DeptAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 部门表ID
	 */
	@Parameter(description = "部门表ID")
	private String id;
}
