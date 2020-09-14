package com.simple4j.user.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegionDetailRequest {

	@NotNull
	@ApiModelProperty(value = "区划编号", required = true)
	private String code;
}
