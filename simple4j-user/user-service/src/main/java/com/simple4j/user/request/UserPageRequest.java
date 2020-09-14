package com.simple4j.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPageRequest {

	@ApiModelProperty(name = "page_no", value = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	@ApiModelProperty(name = "pageSize", value = "分页数")
	@JsonProperty("page_size")
	private int pageSize;

	@ApiModelProperty(name = "account", value = "帐号")
	@JsonProperty("account")
	private String account;

	@ApiModelProperty(name = "real_name", value = "姓名")
	@JsonProperty("real_name")
	private String realName;

}
