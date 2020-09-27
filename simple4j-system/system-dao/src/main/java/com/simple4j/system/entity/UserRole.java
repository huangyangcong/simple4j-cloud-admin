package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.simple4j.dao.base.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("simple4j_user_role")
public class UserRole extends BaseEntity {


	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 用户编号
	 */
	private Long userId;
	/**
	 * 角色编号
	 */
	private Long roleId;
}
