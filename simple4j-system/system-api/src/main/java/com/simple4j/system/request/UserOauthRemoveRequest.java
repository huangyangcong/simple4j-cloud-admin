package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 用户第三方认证表删除请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@Schema(name = "用户第三方认证表删除请求实体类", description = "用户第三方认证表删除请求实体类")
public class UserOauthRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户第三方认证表删除编号列表
	 */
	@Parameter(description = "用户第三方认证表删除编号列表", name = "ids")
	@JsonProperty("ids")
	@NotEmpty(message = "用户第三方认证表编号标号不能为空")
	private List<String> ids;
}
