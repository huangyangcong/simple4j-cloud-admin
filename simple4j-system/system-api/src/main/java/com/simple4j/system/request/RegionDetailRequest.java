package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegionDetailRequest {

	@NotNull
	@Parameter(description = "区划编号", required = true)
	private String code;
}
