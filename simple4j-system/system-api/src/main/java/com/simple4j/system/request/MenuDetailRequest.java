package com.simple4j.system.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuDetailRequest {

	@ApiModelProperty("菜单编号")
	private String id;
}
