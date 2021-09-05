package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户第三方认证表详情请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@Schema(name = "用户第三方认证表详情请求实体类", description = "用户第三方认证表详情请求实体类")
public class UserOauthDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户第三方认证表详情编号
	 */
	@Parameter(description = "用户第三方认证表详情编号", name = "id")
	@JsonProperty("id")
	private String id;
	/**
	 * 第三方系统用户ID
	 */
	@Parameter(name = "uuid", description = "第三方系统用户ID")
	@JsonProperty("uuid")
	private String uuid;
}
