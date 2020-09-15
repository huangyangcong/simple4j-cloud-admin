package com.simple4j.user.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色菜单权限
 *
 * @author Blade
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "角色菜单权限请求实体类", description = "角色菜单权限请求实体类")
public class RoleMenuKeyRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色
	 */
	@ApiModelProperty(name = "roles", value = "角色")
	private List<Long> roles;


}
