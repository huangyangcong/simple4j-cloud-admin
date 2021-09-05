package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class MenuListRequest {

	@Parameter(name = "code", description = "菜单编号")
	@JsonProperty("code")
	private String code;

	@Parameter(name = "name", description = "菜单名称")
	@JsonProperty("name")
	private String name;
}
