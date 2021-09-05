package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 菜单表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "菜单表修改请求实体类", description = "菜单表修改请求实体类")
public class MenuUpdateRequest extends MenuAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单表ID
	 */
	@Parameter(description = "菜单表ID")
	private String id;
}
