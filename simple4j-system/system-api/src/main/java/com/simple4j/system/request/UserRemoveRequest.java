package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserRemoveRequest {

  @ApiModelProperty(value = "用户删除编号列表", name = "ids")
  @JsonProperty("ids")
  private List<String> ids;
}
