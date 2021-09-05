package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 租户表列表请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "租户表列表请求实体类", description = "租户表列表请求实体类")
public class TenantListRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Parameter(name = "tenant_id", description = "租户编号")
	@JsonProperty("tenant_id")
	private String tenantId;

	@Parameter(name = "tenant_name", description = "租户名称")
	@JsonProperty("tenant_name")
	private String tenantName;

	@Parameter(name = "contact_number", description = "租户联系电话")
	@JsonProperty("contact_number")
	private String contactNumber;
}
