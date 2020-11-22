package com.simple4j.system.common.constant;

/**
 * 通用常量
 *
 * @author hyc
 */
public interface CommonConstant {

	/**
	 * sword 系统名
	 */
	String SWORD_NAME = "sword";

	/**
	 * saber 系统名
	 */
	String SABER_NAME = "saber";

	/**
	 * 顶级父节点id
	 */
	String TOP_PARENT_ID = "0";

	/**
	 * 顶级父节点名称
	 */
	String TOP_PARENT_NAME = "顶级";

	/**
	 * 默认密码
	 */
	String DEFAULT_PASSWORD = "123456";

	/**
	 * 管理员对应的租户ID
	 */
	String ADMIN_TENANT_ID = "000000";

	/**
	 * 删除状态[0:正常,1:删除]
	 */
	int DB_NOT_DELETED = 0;

	int DB_IS_DELETED = 1;

	String ADMIN = "administrator";

	String DEV_CODE = "dev";
	String PROD_CODE = "prod";
	String TEST_CODE = "test";
}
