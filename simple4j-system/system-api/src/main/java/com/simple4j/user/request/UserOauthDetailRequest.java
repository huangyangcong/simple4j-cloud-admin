package com.simple4j.user.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户第三方认证表详情请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "用户第三方认证表详情请求实体类", description = "用户第三方认证表详情请求实体类")
public class UserOauthDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户第三方认证表详情编号
	 */
	@ApiModelProperty(value = "用户第三方认证表详情编号", name = "id")
	@JsonProperty("id")
	private Long id;
	/**
	 * 第三方系统用户ID
	 */
	@ApiModelProperty(name = "uuid", value = "第三方系统用户ID")
	@JsonProperty("uuid")
	private String uuid;
}
