package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "菜单表修改请求实体类", description = "菜单表修改请求实体类")
public class MenuUpdateRequest extends MenuAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单表ID
	 */
	@ApiModelProperty(value = "菜单表ID")
	private String id;
}
