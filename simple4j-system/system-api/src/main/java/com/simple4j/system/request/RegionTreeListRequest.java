package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class RegionTreeListRequest {

	@Parameter(name = "parent_code", description = "父级编号")
	@JsonProperty("parent_code")
	private String parentCode;

	@Parameter(description = "区划编号")
	private String code;

	@Parameter(description = "区划名称")
	private String name;
}
