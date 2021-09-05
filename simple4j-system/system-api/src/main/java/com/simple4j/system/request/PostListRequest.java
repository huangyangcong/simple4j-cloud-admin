package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class PostListRequest {

	@Parameter(description = "岗位编号", name = "code")
	@JsonProperty("code")
	private String code;

	@Parameter(description = "岗位名称", name = "name")
	@JsonProperty("name")
	private String name;

	@Parameter(description = "租户编号", name = "tenant_id")
	@JsonProperty("tenant_id")
	private String tenantId;
}
