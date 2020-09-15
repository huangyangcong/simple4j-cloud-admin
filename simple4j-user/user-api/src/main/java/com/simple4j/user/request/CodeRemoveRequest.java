package com.simple4j.user.request;

	import lombok.Data;
	import io.swagger.annotations.ApiModel;
	import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 代码生成表删除请求实体类
 *
 * @author Blade
 * @since 2020-09-15
 */
	@Data
	@ApiModel(value = "代码生成表删除请求实体类", description = "代码生成表删除请求实体类")
public class CodeRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 代码生成表删除编号列表
	 */
		@ApiModelProperty(value = "代码生成表删除编号列表", name = "ids")
	@JsonProperty("ids")
	@NotEmpty(message = "代码生成表编号标号不能为空")
	private List<String> ids;
	}
