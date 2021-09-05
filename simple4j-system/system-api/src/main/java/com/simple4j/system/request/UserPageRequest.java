package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class UserPageRequest {

	@Parameter(name = "page_no", description = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	@Parameter(name = "pageSize", description = "分页数")
	@JsonProperty("page_size")
	private int pageSize;

	@Parameter(name = "account", description = "帐号")
	@JsonProperty("account")
	private String account;

	@Parameter(name = "real_name", description = "姓名")
	@JsonProperty("real_name")
	private String realName;
}
