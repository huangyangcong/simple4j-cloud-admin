package com.simple4j.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuDetailRequest {

	@ApiModelProperty("菜单编号")
	private Long id;
}
