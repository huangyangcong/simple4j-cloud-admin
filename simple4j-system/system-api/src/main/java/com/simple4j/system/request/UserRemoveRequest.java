package com.simple4j.system.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRemoveRequest {

	@ApiModelProperty(value = "用户删除编号列表", name = "ids")
	@JsonProperty("ids")
	private List<String> ids;
}
