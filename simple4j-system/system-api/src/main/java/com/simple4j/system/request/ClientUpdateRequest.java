package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户端表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "客户端表修改请求实体类", description = "客户端表修改请求实体类")
public class ClientUpdateRequest extends ClientAddRequest {

  private static final long serialVersionUID = 1L;

  /** 客户端表ID */
  @ApiModelProperty(value = "客户端表ID")
  private String id;
}
