package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 租户表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "租户表修改请求实体类", description = "租户表修改请求实体类")
public class TenantUpdateRequest extends TenantAddOrUpdateRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户表ID
	 */
	@ApiModelProperty(value = "租户表ID")
	private String id;
}
