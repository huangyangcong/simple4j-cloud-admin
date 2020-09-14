package com.simple4j.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NavbarPermissionRequest {

	/**
	 * 顶部菜单id
	 */
	@ApiModelProperty(name = "id", value = "顶部菜单id")
	@JsonProperty("id")
	private Long id;
}
