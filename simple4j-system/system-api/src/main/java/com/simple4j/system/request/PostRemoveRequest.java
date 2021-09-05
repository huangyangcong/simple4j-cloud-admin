package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class PostRemoveRequest {

	@Parameter(description = "岗位删除编号列表", name = "ids")
	@JsonProperty("ids")
	private Set<String> ids;
}
