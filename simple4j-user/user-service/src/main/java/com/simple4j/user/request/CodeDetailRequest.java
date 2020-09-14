package com.simple4j.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class CodeDetailRequest {

	@ApiModelProperty("编码详情编号")
	private Long id;
}
