package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserListRequest {

	@ApiModelProperty(name = "account", value = "帐号")
	@JsonProperty("account")
	private String account;

	@ApiModelProperty(name = "real_name", value = "姓名")
	@JsonProperty("real_name")
	private String realName;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(hidden = true, value = "租户ID")
	@JsonIgnore
	private String tenantId;
}
