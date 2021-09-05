package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门表详情请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "部门表详情请求实体类", description = "部门表详情请求实体类")
public class DeptDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 部门表详情编号
	 */
	@Parameter(description = "部门表详情编号", name = "id")
	private String id;
}
