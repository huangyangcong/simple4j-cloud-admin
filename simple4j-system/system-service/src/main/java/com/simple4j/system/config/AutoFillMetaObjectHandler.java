package com.simple4j.system.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author hyc
 * @version 1.0.0
 */
@Slf4j
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		String loginId = StpUtil.getLoginIdAsString();
		log.debug("start insert fill ....");
		if (loginId != null) {
			this.strictInsertFill(metaObject, "createUser", String.class, loginId);
			this.strictInsertFill(metaObject, "updateUser", String.class, loginId);
		}
		this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);
		this.strictInsertFill(metaObject, "status", Integer.class, 1);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		String loginId = StpUtil.getLoginIdAsString();
		log.debug("start update fill ....");
		if (loginId != null) {
			this.strictUpdateFill(metaObject, "updateUser", String.class, loginId);
		}
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
	}
}
