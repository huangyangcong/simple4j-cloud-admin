package com.simple4j.user.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 租户表详情请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "租户表详情请求实体类", description = "租户表详情请求实体类")
public class TenantDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 租户表详情编号
	 */
	@ApiModelProperty(value = "租户表详情编号", name = "id")
	private Long id;
}
