package com.simple4j.gen.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 代码生成表修改请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@Schema(name = "代码生成表修改请求实体类", description = "代码生成表修改请求实体类")
public class CodeUpdateRequest extends CodeAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 代码生成表ID
	 */
	@Parameter(description = "代码生成表ID")
	@NotNull(message = "代码生成表编号不能为空")
	private String id;
}
