package com.simple4j.user.request;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostRemoveRequest {

	@ApiModelProperty(value = "岗位删除编号列表", name = "ids")
	@JsonProperty("ids")
	private List<String> ids;
}
