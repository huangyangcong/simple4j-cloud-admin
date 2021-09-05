package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "修改请求实体类", description = "修改请求实体类")
public class NavbarMenuUpdateRequest extends NavbarMenuAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Parameter(description = "ID")
	private String id;
}
