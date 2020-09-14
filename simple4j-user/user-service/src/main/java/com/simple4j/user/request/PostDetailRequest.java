package com.simple4j.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostDetailRequest {

	@ApiModelProperty("岗位编号")
	private Long id;
}
