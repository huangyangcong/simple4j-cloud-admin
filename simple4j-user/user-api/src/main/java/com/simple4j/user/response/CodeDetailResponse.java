package com.simple4j.user.response;

import java.io.Serializable;

import com.simple4j.user.request.CodeAddOrUpdateRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * 代码生成表详情响应实体类
 *
 * @author Blade
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "代码生成表详情响应实体类", description = "代码生成表详情响应实体类")
public class CodeDetailResponse extends CodeAddOrUpdateRequest implements
		Serializable {

	private static final long serialVersionUID = 1L;


}
