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
public class User extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 头像地址
	 */
	@TableField("avatar")
	private String avatar;

	/**
	 * 创建日期
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 邮箱
	 */
	@TableField("email")
	private String email;

	/**
	 * 状态：1启用、0禁用
	 */
	@TableField("enabled")
	private Long enabled;

	/**
	 * 密码
	 */
	@TableField("password")
	private String password;

	/**
	 * 用户名
	 */
	@TableField("username")
	private String username;

	/**
	 * 最后修改密码的日期
	 */
	@TableField("last_password_reset_time")
	private Date lastPasswordResetTime;

	@TableField("dept_id")
	private Long deptId;

	@TableField("phone")
	private String phone;

	@TableField("job_id")
	private Long jobId;


}
