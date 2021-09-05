package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单权限
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "角色菜单权限请求实体类", description = "角色菜单权限请求实体类")
public class RoleMenuKeyRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色
	 */
	@Parameter(name = "roles", description = "角色")
	private List<Long> roles;
}
