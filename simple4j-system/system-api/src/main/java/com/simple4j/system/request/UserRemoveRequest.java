package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.List;

@Data
public class UserRemoveRequest {

	@Parameter(description = "用户删除编号列表", name = "ids")
	@JsonProperty("ids")
	private List<String> ids;
}
