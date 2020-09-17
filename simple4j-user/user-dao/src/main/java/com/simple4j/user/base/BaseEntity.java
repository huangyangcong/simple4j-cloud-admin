package com.simple4j.user.base;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author hyc
 */
@Data
public class BaseEntity {

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 是否删除
	 */
	@TableField(value = "is_deleted", fill = FieldFill.INSERT)
	private Integer isDeleted;

	/**
	 * 创建者
	 */
	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private Long createUser;

	/**
	 * 更新时间
	 */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	 * 更新人
	 */
	@TableField(value = "update_user", fill = FieldFill.INSERT)
	private Long updateUser;
}
