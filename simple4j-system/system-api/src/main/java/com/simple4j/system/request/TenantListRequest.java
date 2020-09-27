package com.simple4j.system.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 租户表列表请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "租户表列表请求实体类", description = "租户表列表请求实体类")
public class TenantListRequest implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(name = "tenant_id", value = "租户编号")
	@JsonProperty("tenant_id")
	private String tenantId;

	@ApiModelProperty(name = "tenant_name", value = "租户名称")
	@JsonProperty("tenant_name")
	private String tenantName;

	@ApiModelProperty(name = "contact_number", value = "租户联系电话")
	@JsonProperty("contact_number")
	private String contactNumber;

}
