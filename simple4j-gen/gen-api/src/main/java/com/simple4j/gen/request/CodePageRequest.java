package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CodePageRequest {

  @ApiModelProperty(name = "page_no", value = "页码")
  @JsonProperty("page_no")
  private int pageNo;

  @ApiModelProperty(name = "pageSize", value = "分页数")
  @JsonProperty("page_size")
  private int pageSize;

  @ApiModelProperty(name = "code_name", value = "模块名")
  @JsonProperty("code_name")
  private String codeName;

  @ApiModelProperty(name = "table_name", value = "表名")
  @JsonProperty("table_name")
  private String tableName;
}
