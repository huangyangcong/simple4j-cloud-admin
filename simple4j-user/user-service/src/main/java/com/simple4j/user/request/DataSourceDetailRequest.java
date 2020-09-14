package com.simple4j.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class DataSourceDetailRequest {

	@ApiModelProperty("数据源详情编号")
	private Long id;
}
