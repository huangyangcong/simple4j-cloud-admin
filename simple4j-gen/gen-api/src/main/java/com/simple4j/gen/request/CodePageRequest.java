package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class CodePageRequest {

	@Parameter(name = "page_no", description = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	@Parameter(name = "pageSize", description = "分页数")
	@JsonProperty("page_size")
	private int pageSize;

	@Parameter(name = "code_name", description = "模块名")
	@JsonProperty("code_name")
	private String codeName;

	@Parameter(name = "table_name", description = "表名")
	@JsonProperty("table_name")
	private String tableName;
}
