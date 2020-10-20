package com.simple4j.flow.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyc
 */
@Data
public class FlowableFormPageRequest {
	/**
	 * 页码
	 */
	@ApiModelProperty(name = "page_no", value = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	/**
	 * 分页数
	 */
	@ApiModelProperty(name = "pageSize", value = "分页数")
	@JsonProperty("page_size")
	private int pageSize;
}
