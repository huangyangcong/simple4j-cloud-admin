package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户第三方认证表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@Schema(name = "用户第三方认证表新增请求实体类", description = "用户第三方认证表新增请求实体类")
public class UserOauthAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 第三方系统用户ID
	 */
	@Parameter(name = "uuid", description = "第三方系统用户ID")
	@JsonProperty("uuid")
	private String uuid;
	/**
	 * 用户ID
	 */
	@Parameter(name = "user_id", description = "用户ID")
	@JsonProperty("user_id")
	private String userId;

	/**
	 * 租户ID
	 */
	@Parameter(description = hidden = true, description = "租户ID")
	@JsonProperty("tenant_id")
	private String tenantId;

	/**
	 * 账号
	 */
	@Parameter(name = "username", description = "账号")
	@JsonProperty("username")
	private String username;
	/**
	 * 用户名
	 */
	@Parameter(name = "nickname", description = "用户名")
	@JsonProperty("nickname")
	private String nickname;
	/**
	 * 头像
	 */
	@Parameter(name = "avatar", description = "头像")
	@JsonProperty("avatar")
	private String avatar;
	/**
	 * 应用主页
	 */
	@Parameter(name = "blog", description = "应用主页")
	@JsonProperty("blog")
	private String blog;
	/**
	 * 公司名
	 */
	@Parameter(name = "company", description = "公司名")
	@JsonProperty("company")
	private String company;
	/**
	 * 地址
	 */
	@Parameter(name = "location", description = "地址")
	@JsonProperty("location")
	private String location;
	/**
	 * 邮件
	 */
	@Parameter(name = "email", description = "邮件")
	@JsonProperty("email")
	private String email;
	/**
	 * 备注
	 */
	@Parameter(name = "remark", description = "备注")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 性别
	 */
	@Parameter(name = "gender", description = "性别")
	@JsonProperty("gender")
	private String gender;
	/**
	 * 来源
	 */
	@Parameter(name = "source", description = "来源")
	@JsonProperty("source")
	private String source;
}
