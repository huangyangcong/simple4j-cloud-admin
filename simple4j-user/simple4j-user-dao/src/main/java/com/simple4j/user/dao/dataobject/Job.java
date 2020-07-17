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
public class Job extends BaseBean {

	private static final long serialVersionUID = 1L;

	@TableField("name")
	private String name;

	@TableField("enabled")
	private Boolean enabled;

	@TableField("create_time")
	private Date createTime;

	@TableField("sort")
	private Long sort;

	@TableField("dept_id")
	private Long deptId;


}
