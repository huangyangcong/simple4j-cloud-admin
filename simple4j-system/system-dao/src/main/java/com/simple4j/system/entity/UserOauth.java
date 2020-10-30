package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;

/**
 * 实体类
 *
 * @author hyc
 */
@Data
@TableName("simple4j_user_oauth")
public class UserOauth  extends BaseEntity {

	private static final long serialVersionUID = 1L;


	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 租户ID
	 */
	private String tenantId;

	/**
	 * 第三方系统用户ID
	 */
	private String uuid;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 用户头像
	 */
	private String avatar;
	/**
	 * 用户网址
	 */
	private String blog;
	/**
	 * 所在公司
	 */
	private String company;
	/**
	 * 位置
	 */
	private String location;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 用户备注（各平台中的用户个人介绍）
	 */
	private String remark;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 用户来源
	 */
	private String source;


}
