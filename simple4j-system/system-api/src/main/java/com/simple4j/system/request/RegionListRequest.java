package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class RegionListRequest {

	@Parameter(description = "区划编号")
	private String code;
}
