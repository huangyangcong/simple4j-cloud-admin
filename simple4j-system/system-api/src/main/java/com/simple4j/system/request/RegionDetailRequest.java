package com.simple4j.system.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegionDetailRequest {

	@NotNull
	@ApiModelProperty(value = "区划编号", required = true)
	private String code;
}
