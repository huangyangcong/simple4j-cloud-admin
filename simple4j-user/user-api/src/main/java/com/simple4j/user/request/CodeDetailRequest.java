package com.simple4j.user.request;

	import lombok.Data;
	import io.swagger.annotations.ApiModel;
	import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

	import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 代码生成表详情请求实体类
 *
 * @author Blade
 * @since 2020-09-15
 */
	@Data
	@ApiModel(value = "代码生成表详情请求实体类", description = "代码生成表详情请求实体类")
public class CodeDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 代码生成表详情编号
	 */
		@ApiModelProperty(value = "代码生成表详情编号", name = "id")
	@JsonProperty("id")
	private Long id;
	}
