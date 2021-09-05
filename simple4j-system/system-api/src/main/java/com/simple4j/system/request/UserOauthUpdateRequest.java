package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户第三方认证表修改请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@Schema(name = "用户第三方认证表修改请求实体类", description = "用户第三方认证表修改请求实体类")
public class UserOauthUpdateRequest extends UserOauthAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户第三方认证表ID
	 */
	@Parameter(description = "用户第三方认证表ID")
	@NotNull(message = "用户第三方认证表编号不能为空")
	private String id;
}
