package com.simple4j.user.model;

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
public class Dept extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 上级部门
	 */
	@TableField("pid")
	private Long pid;

	@TableField("create_time")
	private Date createTime;

	@TableField("enabled")
	private Boolean enabled;


}
