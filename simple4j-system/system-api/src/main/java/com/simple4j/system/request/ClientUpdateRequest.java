package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 客户端表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "客户端表修改请求实体类", description = "客户端表修改请求实体类")
public class ClientUpdateRequest extends ClientAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端表ID
	 */
	@Parameter(description = "客户端表ID")
	private String id;
}
