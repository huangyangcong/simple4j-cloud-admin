package com.simple4j.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(name = "navbar_id", value = "顶部菜单编号")
	@JsonProperty("navbar_id")
	private Long navbarId;
}
