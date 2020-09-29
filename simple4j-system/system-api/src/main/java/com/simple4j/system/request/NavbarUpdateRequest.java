package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 修改请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "修改请求实体类", description = "修改请求实体类")
public class NavbarUpdateRequest extends NavbarAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@NotEmpty(message = "顶部菜单编号不能为空")
	@ApiModelProperty(value = "ID")
	private String id;

}
