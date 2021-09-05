package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
public class UserDetailResponse implements Serializable {

	/**
	 * 用户编号
	 */
	@Parameter(name = "id", description = "用户编号")
	private String id;

	/**
	 * 租户ID
	 */
	@Parameter(name = "tenant_id", description = "租户ID")
	@JsonProperty("tenant_id")
	private String tenantId;
	/**
	 * 编号
	 */
	@Parameter(name = "code", description = "编号")
	private String code;
	/**
	 * 账号
	 */
	@Parameter(name = "account", description = "密码")
	private String account;
	/**
	 * 昵称
	 */
	@Parameter(name = "name", description = "昵称")
	private String name;
	/**
	 * 真名
	 */
	@Parameter(name = "real_name", description = "真名")
	@JsonProperty("real_name")
	private String realName;
	/**
	 * 头像
	 */
	@Parameter(name = "avatar", description = "头像")
	private String avatar;
	/**
	 * 邮箱
	 */
	@Parameter(name = "email", description = "邮箱")
	private String email;
	/**
	 * 手机
	 */
	@Parameter(name = "phone", description = "手机")
	private String phone;
	/**
	 * 生日
	 */
	@Parameter(name = "birthday", description = "生日")
	private Date birthday;
	/**
	 * 性别
	 */
	@Parameter(name = "sex", description = "性别")
	private Integer sex;

	/**
	 * 角色id
	 */
	@Parameter(name = "roles", description = "角色id")
	private Set<String> roles;

	/**
	 * 部门id
	 */
	@Parameter(name = "depts", description = "部门id")
	private Set<String> depts;

	/**
	 * 岗位名id
	 */
	@Parameter(name = "posts", description = "岗位名id")
	private Set<String> posts;

	/**
	 * 性别
	 */
	@Parameter(name = "sex_name", description = "性别")
	private String sexName;
}
