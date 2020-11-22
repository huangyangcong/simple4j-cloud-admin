package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行政区划表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "行政区划表修改请求实体类", description = "行政区划表修改请求实体类")
public class RegionUpdateRequest extends RegionAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 行政区划表ID
	 */
	@ApiModelProperty(value = "行政区划表ID")
	private String id;
}
