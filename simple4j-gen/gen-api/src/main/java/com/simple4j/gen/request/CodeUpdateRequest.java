package com.simple4j.gen.request;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import com.simple4j.gen.request.CodeAddOrUpdateRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	private Long id;

	}
