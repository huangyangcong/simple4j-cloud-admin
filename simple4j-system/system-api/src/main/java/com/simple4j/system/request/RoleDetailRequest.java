package com.simple4j.system.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleDetailRequest {

  @ApiModelProperty("角色编号")
  private String id;
}
