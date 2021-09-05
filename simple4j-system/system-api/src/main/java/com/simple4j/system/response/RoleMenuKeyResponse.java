package com.simple4j.system.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Set;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class RoleMenuKeyResponse {

	/**
	 * 菜单编号列表
	 */
	@Parameter(description = "菜单编号列表", name = "menus")
	Set<String> menus;
}
