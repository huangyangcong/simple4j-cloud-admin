package com.simple4j.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.simple4j.common.orm.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("simple4j_user_post")
public class UserPost extends BaseEntity {


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
	private Long postId;
}
