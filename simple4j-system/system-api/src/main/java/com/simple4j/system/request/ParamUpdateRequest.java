package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 参数表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "参数表修改请求实体类", description = "参数表修改请求实体类")
public class ParamUpdateRequest extends ParamAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数表ID
	 */
	@Parameter(description = "参数表ID")
	private String id;
}
