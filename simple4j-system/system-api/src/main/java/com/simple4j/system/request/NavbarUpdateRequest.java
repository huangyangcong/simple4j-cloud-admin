package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "修改请求实体类", description = "修改请求实体类")
public class NavbarUpdateRequest extends NavbarAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@NotEmpty(message = "顶部菜单编号不能为空")
	@Parameter(description = "ID")
	private String id;
}
