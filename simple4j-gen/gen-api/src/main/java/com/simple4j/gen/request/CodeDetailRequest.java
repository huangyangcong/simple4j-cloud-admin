package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 代码生成表详情请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "代码生成表详情请求实体类", description = "代码生成表详情请求实体类")
public class CodeDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 代码生成表详情编号
	 */
	@ApiModelProperty(value = "代码生成表详情编号", name = "id", required = true)
	@NotNull(message = "编号不能为空")
	@JsonProperty("id")
	private String id;
}
