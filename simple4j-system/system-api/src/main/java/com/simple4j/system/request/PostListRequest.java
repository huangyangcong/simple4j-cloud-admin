package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostListRequest {

  @ApiModelProperty(value = "岗位编号", name = "code")
  @JsonProperty("code")
  private String code;

  @ApiModelProperty(value = "岗位名称", name = "name")
  @JsonProperty("name")
  private String name;

  @ApiModelProperty(value = "租户编号", name = "tenant_id")
  @JsonProperty("tenant_id")
  private String tenantId;
}
