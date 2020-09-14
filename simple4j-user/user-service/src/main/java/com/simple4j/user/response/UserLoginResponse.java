package com.simple4j.user.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginResponse {

	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "用户id")
	private Long id;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(value = "租户ID")
	private String tenantId;
	/**
	 * 编号
	 */
	@ApiModelProperty(value = "code")
	private String code;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "avatar")
	private String avatar;
	/**
	 * token
	 */
	@ApiModelProperty(value = "token")
	private String token;
	/**
	 * 角色
	 */
	@ApiModelProperty(value = "roles")
	private List<Long> roles;
	/**
	 * 权限
	 */
	@ApiModelProperty(value = "permissions")
	private List<String> permissions;
}
