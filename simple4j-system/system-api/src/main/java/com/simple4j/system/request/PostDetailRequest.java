package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class PostDetailRequest {

	@Parameter(description = "岗位编号")
	private String id;
}
