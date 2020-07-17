package com.simple4j.user.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.simple4j.common.orm.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author huangyangcong
 * @since 2019-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Menu extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建日期
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 是否外链
	 */
	@TableField("i_frame")
	private Boolean iFrame;

	/**
	 * 菜单名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 组件
	 */
	@TableField("component")
	private String component;

	/**
	 * 上级菜单ID
	 */
	@TableField("pid")
	private Long pid;

	/**
	 * 排序
	 */
	@TableField("sort")
	private Long sort;

	/**
	 * 图标
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 链接地址
	 */
	@TableField("path")
	private String path;


}
