package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 列表请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "列表请求实体类", description = "列表请求实体类")
public class NavbarMenuListRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户ID
	 */
	@Parameter(description = hidden = true, description = "租户ID")
	@JsonProperty("tenant_id")
	private String tenantId;
}
