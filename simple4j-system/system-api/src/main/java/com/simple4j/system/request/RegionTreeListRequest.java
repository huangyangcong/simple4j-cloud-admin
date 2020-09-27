package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegionTreeListRequest {

	@ApiModelProperty(name = "parent_code", value = "父级编号")
	@JsonProperty("parent_code")
	private String parentCode;

	@ApiModelProperty("区划编号")
	private String code;

	@ApiModelProperty("区划名称")
	private String name;
}
