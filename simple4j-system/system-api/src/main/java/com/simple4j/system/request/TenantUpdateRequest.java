package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 租户表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "租户表修改请求实体类", description = "租户表修改请求实体类")
public class TenantUpdateRequest extends TenantAddOrUpdateRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户表ID
	 */
	@Parameter(description = "租户表ID")
	private String id;
}
