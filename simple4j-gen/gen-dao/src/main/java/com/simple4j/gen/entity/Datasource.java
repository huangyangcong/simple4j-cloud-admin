package com.simple4j.gen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.simple4j.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据源配置表实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@TableName("simple4j_datasource")
@EqualsAndHashCode(callSuper = true)
public class Datasource extends BaseEntity {

private static final long serialVersionUID=1L;

	/**
	 * 主键
	 */
		@TableId(value = "id", type = IdType.NONE)
				private String id;
	/**
	 * 名称
	 */
			private String name;
	/**
	 * 驱动类
	 */
			private String driverClass;
	/**
	 * 连接地址
	 */
			private String url;
	/**
	 * 用户名
	 */
			private String username;
	/**
	 * 密码
	 */
			private String password;
	/**
	 * 备注
	 */
			private String remark;
	/**
	 * 创建人
	 */
			private String createUser;
	/**
	 * 创建时间
	 */
			private Date createTime;
	/**
	 * 修改人
	 */
			private String updateUser;
	/**
	 * 修改时间
	 */
			private Date updateTime;
	/**
	 * 状态
	 */
			private Integer status;
	/**
	 * 是否已删除
	 */
			@TableLogic
	private Integer isDeleted;


}
