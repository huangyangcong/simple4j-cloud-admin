package com.simple4j.user.model;

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
public class RolesMenus extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId("menu_id")
	private Long menuId;

	/**
	 * 角色ID
	 */
	@TableField("role_id")
	private Long roleId;


}
