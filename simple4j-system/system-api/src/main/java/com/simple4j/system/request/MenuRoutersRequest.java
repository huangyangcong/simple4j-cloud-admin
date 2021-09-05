package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class MenuRoutersRequest {

	/**
	 * 顶部菜单编号
	 */
	@Parameter(name = "navbar_id", description = "顶部菜单编号")
	@JsonProperty("navbar_id")
	private Long navbarId;
}
