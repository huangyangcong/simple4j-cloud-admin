package com.simple4j.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuListRequest {

	@ApiModelProperty(name = "code", value = "菜单编号")
	@JsonProperty("code")
	private String code;

	@ApiModelProperty(name = "name", value = "菜单名称")
	@JsonProperty("name")
	private String name;

}
