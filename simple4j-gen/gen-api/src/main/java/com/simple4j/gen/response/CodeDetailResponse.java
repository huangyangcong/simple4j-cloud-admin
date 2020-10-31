package com.simple4j.gen.response;


import com.simple4j.gen.request.CodeAddOrUpdateRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 代码生成表详情响应实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "代码生成表详情响应实体类", description = "代码生成表详情响应实体类")
public class CodeDetailResponse extends CodeAddOrUpdateRequest implements
	Serializable {

	private static final long serialVersionUID = 1L;


}
