package com.simple4j.gen.response;


import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.gen.request.CodeAddOrUpdateRequest;

/**
 * 代码生成表详情响应实体类
 *
 * @author Blade
 * @since 2020-09-19
 */
	@Data
	@ApiModel(value = "代码生成表详情响应实体类", description = "代码生成表详情响应实体类")
	public class CodeDetailResponse extends CodeAddOrUpdateRequest implements
		Serializable {

		private static final long serialVersionUID = 1L;

		


}
