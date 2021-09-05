package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class UserListRequest {

	@Parameter(name = "account", description = "帐号")
	@JsonProperty("account")
	private String account;

	@Parameter(name = "real_name", description = "姓名")
	@JsonProperty("real_name")
	private String realName;

	/**
	 * 租户ID
	 */
	@Parameter(hidden = true, description = "租户ID")
	@JsonIgnore
	private String tenantId;
}
