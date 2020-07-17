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
public class Role extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建日期
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;

	@TableField("data_scope")
	private String dataScope;

	@TableField("level")
	private Integer level;


}
