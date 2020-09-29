package com.simple4j.system.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.simple4j.autoconfigure.jwt.security.SecurityScope;
import com.simple4j.autoconfigure.jwt.security.SecurityUtils;
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
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		log.debug("start insert fill ....");
		this.strictInsertFill(metaObject, "createUser", String.class,
				securityScope.getUserId());
		this.strictInsertFill(metaObject, "updateUser", String.class,
				securityScope.getUserId());
		this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);
		this.strictInsertFill(metaObject, "status", Integer.class, 1);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		log.debug("start update fill ....");
		this.strictUpdateFill(metaObject, "updateUser", String.class,
				securityScope.getUserId());
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
	}
}
