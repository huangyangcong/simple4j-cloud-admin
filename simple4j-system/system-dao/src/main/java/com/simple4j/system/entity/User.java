package com.simple4j.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@TableName("simple4j_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 租户ID
	 */
	private String tenantId;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 真名
	 */
	private String realName;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 状态[1:正常]
	 */
	private Integer status;

}
