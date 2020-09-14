package com.simple4j.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDetailRequest {

	@ApiModelProperty("用户编号")
	private Long id;
}
