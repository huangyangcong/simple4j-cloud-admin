package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.simple4j.dao.base.BaseEntity;

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
	private String id;


	/**
	 * 用户编号
	 */
	private String userId;
	/**
	 * 角色编号
	 */
	private String deptId;
}
