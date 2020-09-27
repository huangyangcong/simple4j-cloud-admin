package com.simple4j.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegionListRequest {

	@ApiModelProperty("区划编号")
	private String code;

}
