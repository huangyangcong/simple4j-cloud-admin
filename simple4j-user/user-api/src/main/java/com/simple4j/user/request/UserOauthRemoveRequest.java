package com.simple4j.user.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户第三方认证表删除请求实体类
 *
 * @author Blade
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "用户第三方认证表删除请求实体类", description = "用户第三方认证表删除请求实体类")
public class UserOauthRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户第三方认证表删除编号列表
	 */
	@ApiModelProperty(value = "用户第三方认证表删除编号列表", name = "ids")
	@JsonProperty("ids")
	@NotEmpty(message = "用户第三方认证表编号标号不能为空")
	private List<String> ids;
	}
