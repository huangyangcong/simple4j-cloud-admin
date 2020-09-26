package com.simple4j.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 角色表修改请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "角色表修改请求实体类", description = "角色表修改请求实体类")
public class RoleUpdateRequest extends RoleAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色表ID
	 */
		@ApiModelProperty(value = "角色表ID")
	@NotNull(message = "角色表编号不能为空")
	private Long id;

	}
