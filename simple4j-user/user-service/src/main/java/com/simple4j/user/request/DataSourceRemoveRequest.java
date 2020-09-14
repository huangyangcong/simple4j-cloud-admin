package com.simple4j.user.request;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataSourceRemoveRequest {

	@ApiModelProperty(value = "数据源删除编号列表", name = "ids")
	@JsonProperty("ids")
	private List<String> ids;
}
