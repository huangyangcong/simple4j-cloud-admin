package com.simple4j.system.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostDetailRequest {

  @ApiModelProperty("岗位编号")
  private String id;
}
