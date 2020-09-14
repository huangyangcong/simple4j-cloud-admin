package com.simple4j.common.orm;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author hyc
 */
@Data
public class BaseEntity {

	/**
	 * 创建时间
	 */
	@TableField(value = "created_on_utc", fill = FieldFill.INSERT)
	private Date createdOnUtc;

	/**
	 * 是否删除
	 */
	@TableField(value = "is_delete", fill = FieldFill.INSERT)
	private Boolean isDelete;

	/**
	 * 创建者
	 */
	@TableField(value = "creator", fill = FieldFill.INSERT)
	private Integer creator;

	/**
	 * 更新时间
	 */
	@TableField(value = "updated_on_utc", fill = FieldFill.INSERT_UPDATE)
	private Date updatedOnUtc;

	/**
	 * 更新人
	 */
	@TableField(value = "updator", fill = FieldFill.INSERT)
	private Integer updator;
}
