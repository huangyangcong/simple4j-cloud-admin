package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class MenuDetailRequest {

	@Parameter(description = "菜单编号")
	private String id;
}
