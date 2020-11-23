package com.simple4j.gateway.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("simple4j_user_role")
public class UserRole extends BaseEntity {

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 用户编号
	 */
	private String userId;
	/**
	 * 角色编号
	 */
	private String roleId;
}
