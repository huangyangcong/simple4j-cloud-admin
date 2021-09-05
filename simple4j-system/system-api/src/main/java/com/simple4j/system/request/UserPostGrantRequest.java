package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class UserPostGrantRequest {

	@Parameter(name = "user_ids", description = "用户编号列表")
	@JsonProperty("user_ids")
	Set<String> userIds;

	@Parameter(name = "post_ids", description = "用户岗位列表")
	@JsonProperty("post_ids")
	Set<String> postIds;
}
