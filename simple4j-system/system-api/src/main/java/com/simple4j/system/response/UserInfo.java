package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 用户信息
 *
 * @author hyc
 */
@Data
@Schema(name = "用户信息", description = "用户信息")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Parameter(description = "用户id")
	private String id;

	/**
	 * 租户ID
	 */
	@Parameter(description = "租户ID")
	private String tenantId;
	/**
	 * 编号
	 */
	@Parameter(description = "code")
	private String code;
	/**
	 * 账号
	 */
	@Parameter(description = "账号")
	private String account;
	/**
	 * 密码
	 */
	@Parameter(description = "密码")
	private String password;
	/**
	 * 昵称
	 */
	@Parameter(description = "昵称")
	private String name;
	/**
	 * 真名
	 */
	@Parameter(description = "avatar")
	@JsonProperty("real_name")
	private String realName;
	/**
	 * 头像
	 */
	@Parameter(description = "avatar")
	private String avatar;
	/**
	 * 邮箱
	 */
	@Parameter(description = "email")
	private String email;
	/**
	 * 手机
	 */
	@Parameter(description = "phone")
	private String phone;
	/**
	 * 生日
	 */
	@Parameter(description = "生日")
	private Date birthday;
	/**
	 * 性别
	 */
	@Parameter(description = "性别")
	private Integer sex;

	/**
	 * 第三方授权id
	 */
	@Parameter(description = "第三方授权id")
	private String oauthId;

	/**
	 * 权限标识集合
	 */
	@Parameter(description = "权限集合")
	private Set<String> permissions;

	/**
	 * 角色标识集合
	 */
	@Parameter(description = "角色集合")
	private Set<String> roles;
}
