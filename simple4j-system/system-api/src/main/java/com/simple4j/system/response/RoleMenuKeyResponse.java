package com.simple4j.system.response;

import java.util.Set;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class RoleMenuKeyResponse {

	/**
	 * 菜单编号列表
	 */
	@ApiModelProperty(value = "菜单编号列表", name = "menus")
	Set<String> menus;
}
