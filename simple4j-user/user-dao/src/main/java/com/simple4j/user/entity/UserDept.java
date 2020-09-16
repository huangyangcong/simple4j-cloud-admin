package com.simple4j.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.simple4j.user.base.BaseEntity;

/**
 * 用户部门关联
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("simple4j_user_dept")
public class UserDept extends BaseEntity {

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
	private Long deptId;
}
