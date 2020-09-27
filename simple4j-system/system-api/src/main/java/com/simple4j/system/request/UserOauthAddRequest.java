package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户第三方认证表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "用户第三方认证表新增请求实体类", description = "用户第三方认证表新增请求实体类")
public class UserOauthAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

		/**
	 * 第三方系统用户ID
	 */
	@ApiModelProperty(name = "uuid", value = "第三方系统用户ID")
	@JsonProperty("uuid")
	private String uuid;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(name = "user_id", value = "用户ID")
	@JsonProperty("user_id")
	private Long userId;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(hidden = true, value = "租户ID")
	@JsonProperty("tenant_id")
	private String tenantId;

	/**
	 * 账号
	 */
	@ApiModelProperty(name = "username", value = "账号")
	@JsonProperty("username")
	private String username;
	/**
	 * 用户名
	 */
	@ApiModelProperty(name = "nickname", value = "用户名")
	@JsonProperty("nickname")
	private String nickname;
	/**
	 * 头像
	 */
	@ApiModelProperty(name = "avatar", value = "头像")
	@JsonProperty("avatar")
	private String avatar;
	/**
	 * 应用主页
	 */
	@ApiModelProperty(name = "blog", value = "应用主页")
	@JsonProperty("blog")
	private String blog;
	/**
	 * 公司名
	 */
	@ApiModelProperty(name = "company", value = "公司名")
	@JsonProperty("company")
	private String company;
	/**
	 * 地址
	 */
	@ApiModelProperty(name = "location", value = "地址")
	@JsonProperty("location")
	private String location;
	/**
	 * 邮件
	 */
	@ApiModelProperty(name = "email", value = "邮件")
	@JsonProperty("email")
	private String email;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark", value = "备注")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 性别
	 */
	@ApiModelProperty(name = "gender", value = "性别")
	@JsonProperty("gender")
	private String gender;
	/**
	 * 来源
	 */
	@ApiModelProperty(name = "source", value = "来源")
	@JsonProperty("source")
	private String source;
	

}
