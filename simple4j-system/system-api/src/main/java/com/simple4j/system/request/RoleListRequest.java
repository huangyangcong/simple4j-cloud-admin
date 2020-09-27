package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleListRequest {

	@ApiModelProperty(value = "角色名称", name = "role_name")
	@JsonProperty("role_name")
	private String roleName;

	@ApiModelProperty(value = "角色别名", name = "role_alias")
	@JsonProperty("role_alias")
	private String roleAlias;

}
