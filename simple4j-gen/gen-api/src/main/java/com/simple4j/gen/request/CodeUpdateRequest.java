package com.simple4j.gen.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 代码生成表修改请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "代码生成表修改请求实体类", description = "代码生成表修改请求实体类")
public class CodeUpdateRequest extends CodeAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 代码生成表ID
	 */
	@ApiModelProperty(value = "代码生成表ID")
	@NotNull(message = "代码生成表编号不能为空")
	private String id;
}
