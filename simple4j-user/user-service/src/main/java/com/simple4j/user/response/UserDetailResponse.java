package com.simple4j.user.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDetailResponse {

	/**
	 * 主键id
	 */
	@ApiModelProperty(name = "roles", value = "主键id")
	private Long id;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(name = "tenant_id", value = "租户ID")
	@JsonProperty("tenant_id")
	private String tenantId;
	/**
	 * 编号
	 */
	@ApiModelProperty(name = "code", value = "编号")
	private String code;
	/**
	 * 账号
	 */
	@ApiModelProperty(name = "account", value = "密码")
	private String account;
	/**
	 * 昵称
	 */
	@ApiModelProperty(name = "name", value = "昵称")
	private String name;
	/**
	 * 真名
	 */
	@ApiModelProperty(name = "real_name", value = "真名")
	@JsonProperty("real_name")
	private String realName;
	/**
	 * 头像
	 */
	@ApiModelProperty(name = "avatar", value = "头像")
	private String avatar;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(name = "email", value = "邮箱")
	private String email;
	/**
	 * 手机
	 */
	@ApiModelProperty(name = "phone", value = "手机")
	private String phone;
	/**
	 * 生日
	 */
	@ApiModelProperty(name = "birthday", value = "生日")
	private Date birthday;
	/**
	 * 性别
	 */
	@ApiModelProperty(name = "sex", value = "性别")
	private Integer sex;

	/**
	 * 角色id
	 */
	@ApiModelProperty(name = "roles", value = "角色id")
	private List<Long> roles;

	/**
	 * 部门id
	 */
	@ApiModelProperty(name = "depts", value = "部门id")
	private List<Long> depts;

	/**
	 * 岗位名id
	 */
	@ApiModelProperty(name = "posts", value = "岗位名id")
	private List<Long> posts;

	/**
	 * 性别
	 */
	@ApiModelProperty(name = "sex_name", value = "性别")
	private String sexName;


}
