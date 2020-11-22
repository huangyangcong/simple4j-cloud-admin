package com.simple4j.system.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户第三方认证表修改请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "用户第三方认证表修改请求实体类", description = "用户第三方认证表修改请求实体类")
public class UserOauthUpdateRequest extends UserOauthAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户第三方认证表ID
	 */
	@ApiModelProperty(value = "用户第三方认证表ID")
	@NotNull(message = "用户第三方认证表编号不能为空")
	private String id;
}
