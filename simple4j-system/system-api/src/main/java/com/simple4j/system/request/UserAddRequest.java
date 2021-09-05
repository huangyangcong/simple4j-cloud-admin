package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class UserAddRequest {

	/**
	 * 编号
	 */
	@Parameter(name = "code", description = "编号")
	private String code;
	/**
	 * 账号
	 */
	@Parameter(name = "account", description = "账号")
	private String account;
	/**
	 * 密码
	 */
	@Parameter(name = "password", description = "密码")
	private String password;
	/**
	 * 昵称
	 */
	@Parameter(name = "name", description = "昵称")
	private String name;
	/**
	 * 真名
	 */
	@Parameter(name = "realName", description = "真名")
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
	 * 角色
	 */
	@Parameter(name = "roles", description = "角色")
	private Set<String> roles;
	/**
	 * 部门
	 */
	@Parameter(name = "depts", description = "部门")
	private Set<String> depts;
	/**
	 * 岗位
	 */
	@Parameter(name = "posts", description = "岗位")
	private Set<String> posts;
}
