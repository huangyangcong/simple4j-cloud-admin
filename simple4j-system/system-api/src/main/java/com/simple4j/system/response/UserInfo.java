package com.simple4j.system.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息
 *
 * @author Chill
 */
@Data
@ApiModel(description = "用户信息")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "用户id")
	private Long id;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(value = "租户ID")
	private String tenantId;
	/**
	 * 编号
	 */
	@ApiModelProperty(value = "code")
	private String code;
	/**
	 * 账号
	 */
	@ApiModelProperty(value = "账号")
	private String account;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String name;
	/**
	 * 真名
	 */
	@ApiModelProperty(value = "avatar")
	@JsonProperty("real_name")
	private String realName;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "avatar")
	private String avatar;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "email")
	private String email;
	/**
	 * 手机
	 */
	@ApiModelProperty(value = "phone")
	private String phone;
	/**
	 * 生日
	 */
	@ApiModelProperty(value = "生日")
	private Date birthday;
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private Integer sex;

	/**
	 * 第三方授权id
	 */
	@ApiModelProperty(value = "第三方授权id")
	private String oauthId;


	/**
	 * 权限标识集合
	 */
	@ApiModelProperty(value = "权限集合")
	private List<String> permissions;

	/**
	 * 角色标识集合
	 */
	@ApiModelProperty(value = "角色集合")
	private List<Long> roles;

}
