package com.simple4j.user.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.simple4j.user.util.SecurityUtils;
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
		log.debug("start insert fill ....");
		this.strictInsertFill(metaObject, "createUser", Long.class,
				SecurityUtils.getCurrentUserId());
		this.strictInsertFill(metaObject, "updateUser", Long.class,
				SecurityUtils.getCurrentUserId());
		this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);
		this.strictInsertFill(metaObject, "status", Integer.class, 1);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.debug("start update fill ....");
		this.strictUpdateFill(metaObject, "updateUser", Long.class,
				SecurityUtils.getCurrentUserId());
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
	}
}
