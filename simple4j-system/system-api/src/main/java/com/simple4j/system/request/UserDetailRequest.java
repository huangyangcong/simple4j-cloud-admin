package com.simple4j.system.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDetailRequest {

	@ApiModelProperty("用户编号")
	private String id;
}
