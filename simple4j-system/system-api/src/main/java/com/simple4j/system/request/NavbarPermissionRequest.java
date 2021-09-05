package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class NavbarPermissionRequest {

	/**
	 * 顶部菜单id
	 */
	@Parameter(name = "id", description = "顶部菜单id")
	@JsonProperty("id")
	private String id;
}
