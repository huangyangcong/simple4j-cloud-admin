package com.simple4j.user.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.simple4j.common.orm.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class UsersRoles extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId("user_id")
	private Long userId;

	/**
	 * 角色ID
	 */
	@TableField("role_id")
	private Long roleId;


}
