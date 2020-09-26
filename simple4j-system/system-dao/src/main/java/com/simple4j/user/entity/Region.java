package com.simple4j.user.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.dao.base.BaseEntity;
import lombok.Data;

/**
 * 行政区划表实体类
 *
 * @author Chill
 */
@Data
@TableName("simple4j_region")
public class Region extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 区划编号
	 */
	@TableId(value = "code", type = IdType.INPUT)
	private String code;
	/**
	 * 父区划编号
	 */
	private String parentCode;
	/**
	 * 祖区划编号
	 */
	private String ancestors;
	/**
	 * 区划名称
	 */
	private String name;
	/**
	 * 省级区划编号
	 */
	private String provinceCode;
	/**
	 * 省级名称
	 */
	private String provinceName;
	/**
	 * 市级区划编号
	 */
	private String cityCode;
	/**
	 * 市级名称
	 */
	private String cityName;
	/**
	 * 区级区划编号
	 */
	private String districtCode;
	/**
	 * 区级名称
	 */
	private String districtName;
	/**
	 * 镇级区划编号
	 */
	private String townCode;
	/**
	 * 镇级名称
	 */
	private String townName;
	/**
	 * 村级区划编号
	 */
	private String villageCode;
	/**
	 * 村级名称
	 */
	private String villageName;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 备注
	 */
	private String remark;


	@TableField(exist = false)
	private Boolean hasChildren;

	@TableField(exist = false)
	private String parentName;
}
