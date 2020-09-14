package com.simple4j.user.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 字典实体类
 *
 * @author Chill
 * @since 2018-12-24
 */
@Data
@TableName("simple4j_dict")
public class Dict implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 父主键
	 */
	private Long parentId;

	/**
	 * 字典码
	 */
	private String code;

	/**
	 * 字典值
	 */
	private Integer dictKey;

	/**
	 * 字典名称
	 */
	private String dictValue;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 字典备注
	 */
	private String remark;

	/**
	 * 是否已删除
	 */
	@TableLogic
	private Integer isDeleted;


}
