package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
public class UserPostGrantRequest {

  @ApiModelProperty(name = "user_ids", value = "用户编号列表")
  @JsonProperty("user_ids")
  Set<String> userIds;

  @ApiModelProperty(name = "post_ids", value = "用户岗位列表")
  @JsonProperty("post_ids")
  Set<String> postIds;
}
