package com.simple4j.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 参数表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "参数表修改请求实体类", description = "参数表修改请求实体类")
public class ParamUpdateRequest extends ParamAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数表ID
	 */
	@ApiModelProperty(value = "参数表ID")
	private Long id;

}
