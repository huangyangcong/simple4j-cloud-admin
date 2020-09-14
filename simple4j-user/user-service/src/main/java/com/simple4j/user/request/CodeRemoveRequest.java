package com.simple4j.user.request;


import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CodeRemoveRequest {

	@NotNull
	@ApiModelProperty(value = "编码删除编号列表", name = "ids")
	@JsonProperty("ids")
	private List<String> ids;
}
