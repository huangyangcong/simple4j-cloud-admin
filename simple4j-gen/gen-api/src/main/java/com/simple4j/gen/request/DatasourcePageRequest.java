package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据源配置表分页请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "数据源配置表分页请求实体类", description = "数据源配置表分页请求实体类")
public class DatasourcePageRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 页码 */
  @ApiModelProperty(name = "page_no", value = "页码")
  @JsonProperty("page_no")
  private int pageNo;

  /** 分页数 */
  @ApiModelProperty(name = "pageSize", value = "分页数")
  @JsonProperty("page_size")
  private int pageSize;
}
