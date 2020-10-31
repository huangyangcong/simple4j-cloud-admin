package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 参数表删除请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "参数表删除请求实体类", description = "参数表删除请求实体类")
public class ParamRemoveRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 参数表删除编号列表 */
  @ApiModelProperty(value = "参数表删除编号列表", name = "ids")
  @JsonProperty("ids")
  private List<String> ids;
}
