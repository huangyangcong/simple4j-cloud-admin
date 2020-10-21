-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.20-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 flowable 的数据库结构
DROP DATABASE IF EXISTS `flow`;
CREATE DATABASE IF NOT EXISTS `flow` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `flow`;


-- 导出  表 flowable.act_app_appdef 结构
DROP TABLE IF EXISTS `act_app_appdef`;
CREATE TABLE IF NOT EXISTS `act_app_appdef` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_APP_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`),
  KEY `ACT_IDX_APP_DEF_DPLY` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_APP_DEF_DPLY` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_app_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_app_appdef 的数据：~0 rows (大约)
DELETE FROM `act_app_appdef`;
/*!40000 ALTER TABLE `act_app_appdef` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_app_appdef` ENABLE KEYS */;


-- 导出  表 flowable.act_app_databasechangelog 结构
DROP TABLE IF EXISTS `act_app_databasechangelog`;
CREATE TABLE IF NOT EXISTS `act_app_databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_app_databasechangelog 的数据：~3 rows (大约)
DELETE FROM `act_app_databasechangelog`;
/*!40000 ALTER TABLE `act_app_databasechangelog` DISABLE KEYS */;
INSERT INTO `act_app_databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
	('1', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', '2019-07-18 12:45:19', 1, 'EXECUTED', '8:496fc778bdf2ab13f2e1926d0e63e0a2', 'createTable tableName=ACT_APP_DEPLOYMENT; createTable tableName=ACT_APP_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_APP_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_APP_RSRC_DPL, referencedTableName=ACT_APP_DEPLOYMENT; createIndex...', '', NULL, '3.6.3', NULL, NULL, '3425119417'),
	('2', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', '2019-07-18 12:45:19', 2, 'EXECUTED', '8:ccea9ebfb6c1f8367ca4dd473fcbb7db', 'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_APP_DEPLOYMENT', '', NULL, '3.6.3', NULL, NULL, '3425119417'),
	('3', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', '2019-07-18 12:45:19', 3, 'EXECUTED', '8:f1f8aff320aade831944ebad24355f3d', 'createIndex indexName=ACT_IDX_APP_DEF_UNIQ, tableName=ACT_APP_APPDEF', '', NULL, '3.6.3', NULL, NULL, '3425119417');
/*!40000 ALTER TABLE `act_app_databasechangelog` ENABLE KEYS */;


-- 导出  表 flowable.act_app_databasechangeloglock 结构
DROP TABLE IF EXISTS `act_app_databasechangeloglock`;
CREATE TABLE IF NOT EXISTS `act_app_databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_app_databasechangeloglock 的数据：~1 rows (大约)
DELETE FROM `act_app_databasechangeloglock`;
/*!40000 ALTER TABLE `act_app_databasechangeloglock` DISABLE KEYS */;
INSERT INTO `act_app_databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
	(1, b'0', NULL, NULL);
/*!40000 ALTER TABLE `act_app_databasechangeloglock` ENABLE KEYS */;


-- 导出  表 flowable.act_app_deployment 结构
DROP TABLE IF EXISTS `act_app_deployment`;
CREATE TABLE IF NOT EXISTS `act_app_deployment` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_app_deployment 的数据：~0 rows (大约)
DELETE FROM `act_app_deployment`;
/*!40000 ALTER TABLE `act_app_deployment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_app_deployment` ENABLE KEYS */;


-- 导出  表 flowable.act_app_deployment_resource 结构
DROP TABLE IF EXISTS `act_app_deployment_resource`;
CREATE TABLE IF NOT EXISTS `act_app_deployment_resource` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `RESOURCE_BYTES_` longblob,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_APP_RSRC_DPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_APP_RSRC_DPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_app_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_app_deployment_resource 的数据：~0 rows (大约)
DELETE FROM `act_app_deployment_resource`;
/*!40000 ALTER TABLE `act_app_deployment_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_app_deployment_resource` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_casedef 结构
DROP TABLE IF EXISTS `act_cmmn_casedef`;
CREATE TABLE IF NOT EXISTS `act_cmmn_casedef` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` bit(1) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_CASE_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`),
  KEY `ACT_IDX_CASE_DEF_DPLY` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_CASE_DEF_DPLY` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_cmmn_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_casedef 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_casedef`;
/*!40000 ALTER TABLE `act_cmmn_casedef` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_casedef` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_databasechangelog 结构
DROP TABLE IF EXISTS `act_cmmn_databasechangelog`;
CREATE TABLE IF NOT EXISTS `act_cmmn_databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_databasechangelog 的数据：~7 rows (大约)
DELETE FROM `act_cmmn_databasechangelog`;
/*!40000 ALTER TABLE `act_cmmn_databasechangelog` DISABLE KEYS */;
INSERT INTO `act_cmmn_databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
	('1', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-07-18 12:45:17', 1, 'EXECUTED', '8:8b4b922d90b05ff27483abefc9597aa6', 'createTable tableName=ACT_CMMN_DEPLOYMENT; createTable tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_CMMN_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_CMMN_RSRC_DPL, referencedTableName=ACT_CMMN_DEPLOYMENT; create...', '', NULL, '3.6.3', NULL, NULL, '3425116558'),
	('2', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-07-18 12:45:17', 2, 'EXECUTED', '8:65e39b3d385706bb261cbeffe7533cbe', 'addColumn tableName=ACT_CMMN_CASEDEF; addColumn tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST', '', NULL, '3.6.3', NULL, NULL, '3425116558'),
	('3', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-07-18 12:45:18', 3, 'EXECUTED', '8:c01f6e802b49436b4489040da3012359', 'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_CASE_INST; createIndex indexName=ACT_IDX_PLAN_ITEM_STAGE_INST, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableNam...', '', NULL, '3.6.3', NULL, NULL, '3425116558'),
	('4', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-07-18 12:45:18', 4, 'EXECUTED', '8:e40d29cb79345b7fb5afd38a7f0ba8fc', 'createTable tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_MIL_INST; addColumn tableName=ACT_CMMN_HI_MIL_INST', '', NULL, '3.6.3', NULL, NULL, '3425116558'),
	('5', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-07-18 12:45:19', 5, 'EXECUTED', '8:70349de472f87368dcdec971a10311a0', 'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_CMMN_DEPLOYMENT; modifyDataType columnName=START_TIME_, tableName=ACT_CMMN_RU_CASE_INST; modifyDataType columnName=START_TIME_, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; modifyDataType columnName=T...', '', NULL, '3.6.3', NULL, NULL, '3425116558'),
	('6', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-07-18 12:45:19', 6, 'EXECUTED', '8:10e82e26a7fee94c32a92099c059c18c', 'createIndex indexName=ACT_IDX_CASE_DEF_UNIQ, tableName=ACT_CMMN_CASEDEF', '', NULL, '3.6.3', NULL, NULL, '3425116558'),
	('7', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-07-18 12:45:19', 7, 'EXECUTED', '8:530bc81a1e30618ccf4a2da1f7c6c043', 'renameColumn newColumnName=CREATE_TIME_, oldColumnName=START_TIME_, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; renameColumn newColumnName=CREATE_TIME_, oldColumnName=CREATED_TIME_, tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_P...', '', NULL, '3.6.3', NULL, NULL, '3425116558');
/*!40000 ALTER TABLE `act_cmmn_databasechangelog` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_databasechangeloglock 结构
DROP TABLE IF EXISTS `act_cmmn_databasechangeloglock`;
CREATE TABLE IF NOT EXISTS `act_cmmn_databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_databasechangeloglock 的数据：~1 rows (大约)
DELETE FROM `act_cmmn_databasechangeloglock`;
/*!40000 ALTER TABLE `act_cmmn_databasechangeloglock` DISABLE KEYS */;
INSERT INTO `act_cmmn_databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
	(1, b'0', NULL, NULL);
/*!40000 ALTER TABLE `act_cmmn_databasechangeloglock` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_deployment 结构
DROP TABLE IF EXISTS `act_cmmn_deployment`;
CREATE TABLE IF NOT EXISTS `act_cmmn_deployment` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_deployment 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_deployment`;
/*!40000 ALTER TABLE `act_cmmn_deployment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_deployment` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_deployment_resource 结构
DROP TABLE IF EXISTS `act_cmmn_deployment_resource`;
CREATE TABLE IF NOT EXISTS `act_cmmn_deployment_resource` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `RESOURCE_BYTES_` longblob,
  `GENERATED_` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_CMMN_RSRC_DPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_CMMN_RSRC_DPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_cmmn_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_deployment_resource 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_deployment_resource`;
/*!40000 ALTER TABLE `act_cmmn_deployment_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_deployment_resource` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_hi_case_inst 结构
DROP TABLE IF EXISTS `act_cmmn_hi_case_inst`;
CREATE TABLE IF NOT EXISTS `act_cmmn_hi_case_inst` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `REV_` int(11) NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `PARENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_hi_case_inst 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_hi_case_inst`;
/*!40000 ALTER TABLE `act_cmmn_hi_case_inst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_hi_case_inst` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_hi_mil_inst 结构
DROP TABLE IF EXISTS `act_cmmn_hi_mil_inst`;
CREATE TABLE IF NOT EXISTS `act_cmmn_hi_mil_inst` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `TIME_STAMP_` datetime(3) DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `CASE_DEF_ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_hi_mil_inst 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_hi_mil_inst`;
/*!40000 ALTER TABLE `act_cmmn_hi_mil_inst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_hi_mil_inst` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_hi_plan_item_inst 结构
DROP TABLE IF EXISTS `act_cmmn_hi_plan_item_inst`;
CREATE TABLE IF NOT EXISTS `act_cmmn_hi_plan_item_inst` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `STAGE_INST_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `IS_STAGE_` bit(1) DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ITEM_DEFINITION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ITEM_DEFINITION_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_AVAILABLE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_ENABLED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_DISABLED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_STARTED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_SUSPENDED_TIME_` datetime(3) DEFAULT NULL,
  `COMPLETED_TIME_` datetime(3) DEFAULT NULL,
  `OCCURRED_TIME_` datetime(3) DEFAULT NULL,
  `TERMINATED_TIME_` datetime(3) DEFAULT NULL,
  `EXIT_TIME_` datetime(3) DEFAULT NULL,
  `ENDED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  `ENTRY_CRITERION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `EXIT_CRITERION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_hi_plan_item_inst 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_hi_plan_item_inst`;
/*!40000 ALTER TABLE `act_cmmn_hi_plan_item_inst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_hi_plan_item_inst` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_ru_case_inst 结构
DROP TABLE IF EXISTS `act_cmmn_ru_case_inst`;
CREATE TABLE IF NOT EXISTS `act_cmmn_ru_case_inst` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `REV_` int(11) NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `PARENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  `LOCK_TIME_` datetime(3) DEFAULT NULL,
  `IS_COMPLETEABLE_` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_CASE_INST_CASE_DEF` (`CASE_DEF_ID_`),
  KEY `ACT_IDX_CASE_INST_PARENT` (`PARENT_ID_`),
  CONSTRAINT `ACT_FK_CASE_INST_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `act_cmmn_casedef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_ru_case_inst 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_ru_case_inst`;
/*!40000 ALTER TABLE `act_cmmn_ru_case_inst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_ru_case_inst` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_ru_mil_inst 结构
DROP TABLE IF EXISTS `act_cmmn_ru_mil_inst`;
CREATE TABLE IF NOT EXISTS `act_cmmn_ru_mil_inst` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `TIME_STAMP_` datetime(3) DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `CASE_DEF_ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_MIL_CASE_DEF` (`CASE_DEF_ID_`),
  KEY `ACT_IDX_MIL_CASE_INST` (`CASE_INST_ID_`),
  CONSTRAINT `ACT_FK_MIL_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `act_cmmn_casedef` (`ID_`),
  CONSTRAINT `ACT_FK_MIL_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `act_cmmn_ru_case_inst` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_ru_mil_inst 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_ru_mil_inst`;
/*!40000 ALTER TABLE `act_cmmn_ru_mil_inst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_ru_mil_inst` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_ru_plan_item_inst 结构
DROP TABLE IF EXISTS `act_cmmn_ru_plan_item_inst`;
CREATE TABLE IF NOT EXISTS `act_cmmn_ru_plan_item_inst` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `REV_` int(11) NOT NULL,
  `CASE_DEF_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `STAGE_INST_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `IS_STAGE_` bit(1) DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT '',
  `ITEM_DEFINITION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ITEM_DEFINITION_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `IS_COMPLETEABLE_` bit(1) DEFAULT NULL,
  `IS_COUNT_ENABLED_` bit(1) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `SENTRY_PART_INST_COUNT_` int(11) DEFAULT NULL,
  `LAST_AVAILABLE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_ENABLED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_DISABLED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_STARTED_TIME_` datetime(3) DEFAULT NULL,
  `LAST_SUSPENDED_TIME_` datetime(3) DEFAULT NULL,
  `COMPLETED_TIME_` datetime(3) DEFAULT NULL,
  `OCCURRED_TIME_` datetime(3) DEFAULT NULL,
  `TERMINATED_TIME_` datetime(3) DEFAULT NULL,
  `EXIT_TIME_` datetime(3) DEFAULT NULL,
  `ENDED_TIME_` datetime(3) DEFAULT NULL,
  `ENTRY_CRITERION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `EXIT_CRITERION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_PLAN_ITEM_CASE_DEF` (`CASE_DEF_ID_`),
  KEY `ACT_IDX_PLAN_ITEM_CASE_INST` (`CASE_INST_ID_`),
  KEY `ACT_IDX_PLAN_ITEM_STAGE_INST` (`STAGE_INST_ID_`),
  CONSTRAINT `ACT_FK_PLAN_ITEM_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `act_cmmn_casedef` (`ID_`),
  CONSTRAINT `ACT_FK_PLAN_ITEM_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `act_cmmn_ru_case_inst` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_ru_plan_item_inst 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_ru_plan_item_inst`;
/*!40000 ALTER TABLE `act_cmmn_ru_plan_item_inst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_ru_plan_item_inst` ENABLE KEYS */;


-- 导出  表 flowable.act_cmmn_ru_sentry_part_inst 结构
DROP TABLE IF EXISTS `act_cmmn_ru_sentry_part_inst`;
CREATE TABLE IF NOT EXISTS `act_cmmn_ru_sentry_part_inst` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `REV_` int(11) NOT NULL,
  `CASE_DEF_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `PLAN_ITEM_INST_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ON_PART_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `IF_PART_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TIME_STAMP_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_SENTRY_CASE_DEF` (`CASE_DEF_ID_`),
  KEY `ACT_IDX_SENTRY_CASE_INST` (`CASE_INST_ID_`),
  KEY `ACT_IDX_SENTRY_PLAN_ITEM` (`PLAN_ITEM_INST_ID_`),
  CONSTRAINT `ACT_FK_SENTRY_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `act_cmmn_casedef` (`ID_`),
  CONSTRAINT `ACT_FK_SENTRY_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `act_cmmn_ru_case_inst` (`ID_`),
  CONSTRAINT `ACT_FK_SENTRY_PLAN_ITEM` FOREIGN KEY (`PLAN_ITEM_INST_ID_`) REFERENCES `act_cmmn_ru_plan_item_inst` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_cmmn_ru_sentry_part_inst 的数据：~0 rows (大约)
DELETE FROM `act_cmmn_ru_sentry_part_inst`;
/*!40000 ALTER TABLE `act_cmmn_ru_sentry_part_inst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cmmn_ru_sentry_part_inst` ENABLE KEYS */;


-- 导出  表 flowable.act_co_content_item 结构
DROP TABLE IF EXISTS `act_co_content_item`;
CREATE TABLE IF NOT EXISTS `act_co_content_item` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `MIME_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TASK_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTENT_STORE_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTENT_STORE_NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `FIELD_` varchar(400) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTENT_AVAILABLE_` bit(1) DEFAULT b'0',
  `CREATED_` timestamp(6) NULL DEFAULT NULL,
  `CREATED_BY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LAST_MODIFIED_` timestamp(6) NULL DEFAULT NULL,
  `LAST_MODIFIED_BY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTENT_SIZE_` bigint(20) DEFAULT '0',
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `idx_contitem_taskid` (`TASK_ID_`),
  KEY `idx_contitem_procid` (`PROC_INST_ID_`),
  KEY `idx_contitem_scope` (`SCOPE_ID_`,`SCOPE_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_co_content_item 的数据：~0 rows (大约)
DELETE FROM `act_co_content_item`;
/*!40000 ALTER TABLE `act_co_content_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_co_content_item` ENABLE KEYS */;


-- 导出  表 flowable.act_co_databasechangelog 结构
DROP TABLE IF EXISTS `act_co_databasechangelog`;
CREATE TABLE IF NOT EXISTS `act_co_databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_co_databasechangelog 的数据：~2 rows (大约)
DELETE FROM `act_co_databasechangelog`;
/*!40000 ALTER TABLE `act_co_databasechangelog` DISABLE KEYS */;
INSERT INTO `act_co_databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
	('1', 'activiti', 'org/flowable/content/db/liquibase/flowable-content-db-changelog.xml', '2019-07-18 12:45:16', 1, 'EXECUTED', '8:7644d7165cfe799200a2abdd3419e8b6', 'createTable tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_taskid, tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_procid, tableName=ACT_CO_CONTENT_ITEM', '', NULL, '3.6.3', NULL, NULL, '3425116114'),
	('2', 'flowable', 'org/flowable/content/db/liquibase/flowable-content-db-changelog.xml', '2019-07-18 12:45:16', 2, 'EXECUTED', '8:fe7b11ac7dbbf9c43006b23bbab60bab', 'addColumn tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_scope, tableName=ACT_CO_CONTENT_ITEM', '', NULL, '3.6.3', NULL, NULL, '3425116114');
/*!40000 ALTER TABLE `act_co_databasechangelog` ENABLE KEYS */;


-- 导出  表 flowable.act_co_databasechangeloglock 结构
DROP TABLE IF EXISTS `act_co_databasechangeloglock`;
CREATE TABLE IF NOT EXISTS `act_co_databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_co_databasechangeloglock 的数据：~1 rows (大约)
DELETE FROM `act_co_databasechangeloglock`;
/*!40000 ALTER TABLE `act_co_databasechangeloglock` DISABLE KEYS */;
INSERT INTO `act_co_databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
	(1, b'0', NULL, NULL);
/*!40000 ALTER TABLE `act_co_databasechangeloglock` ENABLE KEYS */;


-- 导出  表 flowable.act_de_databasechangelog 结构
DROP TABLE IF EXISTS `act_de_databasechangelog`;
CREATE TABLE IF NOT EXISTS `act_de_databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_de_databasechangelog 的数据：~2 rows (大约)
DELETE FROM `act_de_databasechangelog`;
/*!40000 ALTER TABLE `act_de_databasechangelog` DISABLE KEYS */;
INSERT INTO `act_de_databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
	('1', 'flowable', 'META-INF/liquibase/flowable-modeler-app-db-changelog.xml', '2019-07-17 14:24:37', 1, 'EXECUTED', '8:e70d1d9d3899a734296b2514ccc71501', 'createTable tableName=ACT_DE_MODEL; createIndex indexName=idx_proc_mod_created, tableName=ACT_DE_MODEL; createTable tableName=ACT_DE_MODEL_HISTORY; createIndex indexName=idx_proc_mod_history_proc, tableName=ACT_DE_MODEL_HISTORY; createTable tableN...', '', NULL, '3.6.2', NULL, NULL, '3344677031'),
	('3', 'flowable', 'META-INF/liquibase/flowable-modeler-app-db-changelog.xml', '2019-07-17 14:24:37', 2, 'EXECUTED', '8:3a9143bef2e45f2316231cc1369138b6', 'addColumn tableName=ACT_DE_MODEL; addColumn tableName=ACT_DE_MODEL_HISTORY', '', NULL, '3.6.2', NULL, NULL, '3344677031');
/*!40000 ALTER TABLE `act_de_databasechangelog` ENABLE KEYS */;


-- 导出  表 flowable.act_de_databasechangeloglock 结构
DROP TABLE IF EXISTS `act_de_databasechangeloglock`;
CREATE TABLE IF NOT EXISTS `act_de_databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_de_databasechangeloglock 的数据：~1 rows (大约)
DELETE FROM `act_de_databasechangeloglock`;
/*!40000 ALTER TABLE `act_de_databasechangeloglock` DISABLE KEYS */;
INSERT INTO `act_de_databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
	(1, b'0', NULL, NULL);
/*!40000 ALTER TABLE `act_de_databasechangeloglock` ENABLE KEYS */;


-- 导出  表 flowable.act_de_model 结构
DROP TABLE IF EXISTS `act_de_model`;
CREATE TABLE IF NOT EXISTS `act_de_model` (
  `id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(400) COLLATE utf8mb4_bin NOT NULL,
  `model_key` varchar(400) COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `model_comment` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `last_updated_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `model_editor_json` longtext COLLATE utf8mb4_bin,
  `thumbnail` longblob,
  `model_type` int(11) DEFAULT NULL,
  `tenant_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_proc_mod_created` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_de_model 的数据：~4 rows (大约)
DELETE FROM `act_de_model`;
/*!40000 ALTER TABLE `act_de_model` DISABLE KEYS */;
INSERT INTO `act_de_model` (`id`, `name`, `model_key`, `description`, `model_comment`, `created`, `created_by`, `last_updated`, `last_updated_by`, `version`, `model_editor_json`, `thumbnail`, `model_type`, `tenant_id`) VALUES
	('1b58cbf5-a907-11e9-b7a0-36e12d1a8ad3', 'ExpenseProcess', 'Expense', '报销流程', NULL, '2019-07-18 10:52:39.707000', 'admin', '2019-07-18 10:52:39.707000', 'admin', 1, '{"bounds":{"lowerRight":{"x":1485.0,"y":700.0},"upperLeft":{"x":0.0,"y":0.0}},"resourceId":"canvas","stencil":{"id":"BPMNDiagram"},"stencilset":{"namespace":"http://b3mn.org/stencilset/bpmn2.0#","url":"../editor/stencilsets/bpmn2.0/bpmn2.0.json"},"properties":{"process_id":"Expense","name":"ExpenseProcess","documentation":"报销流程","process_namespace":"http://www.flowable.org/processdef","iseagerexecutionfetch":false,"messages":[],"executionlisteners":{"executionListeners":[]},"eventlisteners":{"eventListeners":[]},"signaldefinitions":[],"messagedefinitions":[]},"childShapes":[{"bounds":{"lowerRight":{"x":315.0,"y":165.0},"upperLeft":{"x":285.0,"y":135.0}},"resourceId":"start","childShapes":[],"stencil":{"id":"StartNoneEvent"},"properties":{"overrideid":"start","name":"开始","interrupting":true,"executionlisteners":{"executionListeners":[]}},"outgoing":[{"resourceId":"flow1"}]},{"bounds":{"lowerRight":{"x":505.0,"y":190.0},"upperLeft":{"x":405.0,"y":110.0}},"resourceId":"fillTask","childShapes":[],"stencil":{"id":"UserTask"},"properties":{"overrideid":"fillTask","name":"出差报销","usertaskassignment":{"assignment":{"type":"static","assignee":"${taskUser}"}},"asynchronousdefinition":false,"exclusivedefinition":true,"tasklisteners":{"taskListeners":[]},"executionlisteners":{"executionListeners":[]}},"outgoing":[{"resourceId":"flow2"}]},{"bounds":{"lowerRight":{"x":625.0,"y":170.0},"upperLeft":{"x":585.0,"y":130.0}},"resourceId":"judgeTask","childShapes":[],"stencil":{"id":"ExclusiveGateway"},"properties":{"overrideid":"judgeTask","executionlisteners":{"executionListeners":[]}},"outgoing":[{"resourceId":"judgeMore"},{"resourceId":"judgeLess"}]},{"bounds":{"lowerRight":{"x":835.0,"y":190.0},"upperLeft":{"x":735.0,"y":110.0}},"resourceId":"directorTak","childShapes":[],"stencil":{"id":"UserTask"},"properties":{"overrideid":"directorTak","name":"经理审批","asynchronousdefinition":false,"exclusivedefinition":true,"tasklisteners":{"taskListeners":[{"event":"create","className":"com.example.demo.listen.ManagerTaskHandler"}]},"executionlisteners":{"executionListeners":[]}},"outgoing":[{"resourceId":"directorNotPassFlow"},{"resourceId":"directorPassFlow"}]},{"bounds":{"lowerRight":{"x":655.0,"y":335.0},"upperLeft":{"x":555.0,"y":255.0}},"resourceId":"bossTask","childShapes":[],"stencil":{"id":"UserTask"},"properties":{"overrideid":"bossTask","name":"老板审批","asynchronousdefinition":false,"exclusivedefinition":true,"tasklisteners":{"taskListeners":[{"event":"create","className":"com.example.demo.listen.BossTaskHandler"}]},"executionlisteners":{"executionListeners":[]}},"outgoing":[{"resourceId":"bossNotPassFlow"},{"resourceId":"bossPassFlow"}]},{"bounds":{"lowerRight":{"x":799.0,"y":309.0},"upperLeft":{"x":771.0,"y":281.0}},"resourceId":"end","childShapes":[],"stencil":{"id":"EndNoneEvent"},"properties":{"overrideid":"end","name":"结束","executionlisteners":{"executionListeners":[]}},"outgoing":[]},{"bounds":{"lowerRight":{"x":172.0,"y":212.0},"upperLeft":{"x":128.0,"y":212.0}},"resourceId":"directorNotPassFlow","childShapes":[],"stencil":{"id":"SequenceFlow"},"dockers":[{"x":50.0,"y":40.0},{"x":785.0,"y":37.0},{"x":455.0,"y":37.0},{"x":50.0,"y":40.0}],"outgoing":[{"resourceId":"fillTask"}],"target":{"resourceId":"fillTask"},"properties":{"overrideid":"directorNotPassFlow","name":"驳回","conditionsequenceflow":"${outcome==\'驳回\'}"}},{"bounds":{"lowerRight":{"x":172.0,"y":212.0},"upperLeft":{"x":128.0,"y":212.0}},"resourceId":"bossNotPassFlow","childShapes":[],"stencil":{"id":"SequenceFlow"},"dockers":[{"x":50.0,"y":40.0},{"x":455.0,"y":295.0},{"x":50.0,"y":40.0}],"outgoing":[{"resourceId":"fillTask"}],"target":{"resourceId":"fillTask"},"properties":{"overrideid":"bossNotPassFlow","name":"驳回","conditionsequenceflow":"${outcome==\'驳回\'}"}},{"bounds":{"lowerRight":{"x":172.0,"y":212.0},"upperLeft":{"x":128.0,"y":212.0}},"resourceId":"flow1","childShapes":[],"stencil":{"id":"SequenceFlow"},"dockers":[{"x":15.0,"y":15.0},{"x":50.0,"y":40.0}],"outgoing":[{"resourceId":"fillTask"}],"target":{"resourceId":"fillTask"},"properties":{"overrideid":"flow1"}},{"bounds":{"lowerRight":{"x":172.0,"y":212.0},"upperLeft":{"x":128.0,"y":212.0}},"resourceId":"flow2","childShapes":[],"stencil":{"id":"SequenceFlow"},"dockers":[{"x":50.0,"y":40.0},{"x":20.0,"y":20.0}],"outgoing":[{"resourceId":"judgeTask"}],"target":{"resourceId":"judgeTask"},"properties":{"overrideid":"flow2"}},{"bounds":{"lowerRight":{"x":172.0,"y":212.0},"upperLeft":{"x":128.0,"y":212.0}},"resourceId":"judgeMore","childShapes":[],"stencil":{"id":"SequenceFlow"},"dockers":[{"x":20.0,"y":20.0},{"x":50.0,"y":40.0}],"outgoing":[{"resourceId":"bossTask"}],"target":{"resourceId":"bossTask"},"properties":{"overrideid":"judgeMore","name":"大于500元","conditionsequenceflow":"${money > 500}"}},{"bounds":{"lowerRight":{"x":172.0,"y":212.0},"upperLeft":{"x":128.0,"y":212.0}},"resourceId":"bossPassFlow","childShapes":[],"stencil":{"id":"SequenceFlow"},"dockers":[{"x":50.0,"y":40.0},{"x":14.0,"y":14.0}],"outgoing":[{"resourceId":"end"}],"target":{"resourceId":"end"},"properties":{"overrideid":"bossPassFlow","name":"通过","conditionsequenceflow":"${outcome==\'通过\'}"}},{"bounds":{"lowerRight":{"x":172.0,"y":212.0},"upperLeft":{"x":128.0,"y":212.0}},"resourceId":"directorPassFlow","childShapes":[],"stencil":{"id":"SequenceFlow"},"dockers":[{"x":50.0,"y":40.0},{"x":14.0,"y":14.0}],"outgoing":[{"resourceId":"end"}],"target":{"resourceId":"end"},"properties":{"overrideid":"directorPassFlow","name":"通过","conditionsequenceflow":"${outcome==\'通过\'}"}},{"bounds":{"lowerRight":{"x":172.0,"y":212.0},"upperLeft":{"x":128.0,"y":212.0}},"resourceId":"judgeLess","childShapes":[],"stencil":{"id":"SequenceFlow"},"dockers":[{"x":20.0,"y":20.0},{"x":50.0,"y":40.0}],"outgoing":[{"resourceId":"directorTak"}],"target":{"resourceId":"directorTak"},"properties":{"overrideid":"judgeLess","name":"小于500元","conditionsequenceflow":"${money <= 500}"}}]}', _binary 0x89504E470D0A1A0A0000000D494844520000003F0000007A0806000000280A163F000007714944415478DAED9D5F6853571CC77D28DBCB187BD8C0071F84ED610C5F3F85413F3F325F0CB5A636B65529F3C5553FC4C5978A0F3F3F183E59053B45ACB3633F4B533F3F52B7824A4546E6D2563F5B6BDBE8D9EF7777EFE84A6B3F7AEF4D3E1FF87192D45ACEF77B3FE79E8473D7AD0300000000000000006FB173E74E43956E31C25D0A152A3F203C3F3F3F3C3F203C3F3F3F3C3F203C3F3F3F3C3F203C3F3F3F3C3F3F3F3F3F3C3F3F3F3F3F3C3F3F3FC2E32D203C3F3FDEC2BFA20F233CA182E28A7E143F153F632A4647472F241289C7D7AF5F37FDFDFD6BAE8B172F5AA798E6F3BB9148C4C4E3F1093FDCF09EB75A78BB4654F4C1C14133363666E6E7E7F32E153EDFDF1D1F1F3703030353623FA280B7BE47DFC51CD167D24973B3EFB0D51662429EE2CFCBBBDB085128BEB76EFA5A96DEEAB2C0E9FCADFEB025BEB66E3F3F1285E27BEBB6AF65E7ADAE7D9D8E3FEAB2C4D73FE1B9207E06DEBAED6BD9793F54B7873A2DF1B545F8D2F1D66D5F3F542AFAF242788B57FCEC3F7C2DEB99CAEDF2A0F0D53F6DB7784B3FBE40DE934A4B75D8ED36BCF5803F35359B43A1D095FAFAFA09DDEBAFABAB9BDDBF7F7F42DA6055555505C27B3654D5CB82E404AC9A50B9E83FAA55023579F5EAD5470F1F3E348A7E66108BC5CCA143876676EFDEFD93046D23C27B2E543FA8DA6F335649796BCF503FA28C5985CB3F3F83C13F7BF6BC82F03F55B6E0F86A3F296F1B1B1BBFE9EBEB5B34593875EAD46F325B3F3FA1CA3530BE99B14ACA5B99A5A69C25DFD348A55253123F08EF7AA8D61A145FCC5825E5AD6E4AE48A5B5FAB973FA2B364B986CAE97F65653F7BDBBC633F4587FD7B9EFA1CCBE9975C826C2AB5E5DFDF3F3F3F1A2A37CB6BC2BBA18104CBE43B53B9ED9F9F3F28542D2D2D37743F1BE7CE9D4B4BE7BF6089C03515DE66E1C03F3F473F6432AB6E3FBDE66A686898A9ADAD7D13E1D9FDC33FD8BB77EFCFBDBDBD8F560B547373F36C30186C44783EA7C23F090402EBEBEBEB473A3B3BC792C964C6B986EAE9E99997193F2F048A503F63F18D0AAF782BC17A490F453F5E522F1C2548693F756706E1F93FDE9628089F75C6E25BEA840AE18BFCB98FC15B4285F03F6F119E50E16D09B1F43425976BCE8B27EEF83954783FF178FC41A1872D16A392C9E4792F3FE7E750E1AD4B44A3D1EDB1586C32954ACDBA3FA6A2472291FB5E3CC5D4CFA1C25B173F6F93773F3A45E77BDE76013F73C4ABA2FBFD707EBCF529FAFDC5523F0477BC4003B7443F4A3F151457F46142453F003F506880F0F40B0D109E7E3F203C030A0D109E7E3FC2D32F34004285B73FE1E9171A203CFD42032054780B084FBFD000E1E9171A203CFDC25B407806141A203CFD420384A75F680008CF80420384A75F6880F07EE9D330038A50217C71FB743F453FDEC61853313A3A7A2191483CD6E38F9FF799779148C4C4E3F1093FBC853F5E03353838683FF6787C7C3F0C0C4CF9FD30494285F0163A4339819A4927CDCDBEC356EB42B0E6FD7E3FA142788BA577BCB83FB642A5AD4BB79159C45BF0BDF04BEFCD7467A8CB0A95B6DC9B8950217C1142757BA8D30A95B6848A50217C81A13F2D2F42453FBEC0998A5B73163F1F80132A42552C0281C01BE26BAF7A5B5B5B7B506F99C44827543F3F3F3F2F482DAAAFCBEA6BA98D8C784245A8D616A87BEA676363E3427B7B7BE6CC3FE6E4C9937F8642A1493B583F163F54B9FB3F3F76ECD893E9E969B394B9B939D3DADAFA3F63313F15A1CA3F4AAE9B32FBF63FE974DAAC3F4B663F3F8B6B2C4245A8B278D8AC3E76747498A7213F9F71362F18F9CF3854523FF4B4B46352BF4BDD95E7BF4AFB8BD48F5231A9EF6A6A6AAEC8EB5FC9E36EA9D3F2FC3369DBE53FD286A5FD445E6B91F623793F6D3F0FC8E30F77EDDAF581B455F2DA16793F79777D4BDAD783C13F79FC9A3F6FDDBAF5C56BD7AE11AADC3F51DDA53E571FCF9E3DFBD450F5F43F3F6146FEB35F3EBCA083DA1E3F74B0EB3FF1DF96C73F0895D2BEAFE1D0906858ECD068783E96D75AA5FD54AA4D436687EDB4863F61AFBCF6ADB4DF4BFDA061B5437B3FB186593F8FD4742F856A855D34CF56B699AAABABCB30533F2CFFD68EBC616DB277FDCCC2C2C28A81CA6432A6A93F6387EA5D461A3F5459686868B0B6D37589B7123F5D72663F555555153F4245A8B220B34FB5B30C8C463F0B542C1633724DFBC4FEF90E46193F54B96F5C849D60B5B53FEEEEEE5E3C7EFCF8846EB7DBCBBE133F4245A8D67E7DA59B46733F31F47998651F3F543F055F3FBBD23F0EEA8E2E238B50112A004205403F153F4B4F5372B9E6FC7E3F80453C1E7FE0E6419A4E2593C9F37E3F3F3F1A8D6E8FC56293A9546AD6AD194A03158944EEFBFD845A80FF90C1BC4D663FBAFC7ADE67A9DB7F7384400100000000000000000000000000000040E1FC0357336412798A913F0000000049454E44AE42603F, 0, NULL),
	('69de9e07-a85e-11e9-bb58-36e12d1a8ad3', '表单名称', 'item_key_001', 'item_key_001 des', NULL, '2019-07-17 14:45:06.809000', 'admin', '2019-07-18 11:16:42.475000', 'admin', 1, '{"name":"表单名称","key":"item_key_001","version":0,"fields":[{"fieldType":"FormField","id":"label","name":"Label","type":"text","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"text","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"multi-line-text","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"people","value":null,"required":true,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"OptionFormField","id":"label","name":"Label","type":"dropdown","value":"请选择一个...","required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null,"optionType":null,"hasEmptyValue":true,"options":[{"id":null,"name":"请选择一个..."},{"id":null,"name":"ss"},{"id":null,"name":"sss"},{"id":null,"name":"sssss"}],"optionsExpression":null},{"fieldType":"FormField","id":"label","name":"Label","type":"functional-group","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":"1","layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"horizontal-line","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"headline-with-line","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"spacer","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"boolean","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"boolean","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null},{"fieldType":"FormField","id":"label","name":"Label","type":"date","value":null,"required":false,"readOnly":false,"overrideId":false,"placeholder":null,"layout":null}],"outcomes":[]}', _binary 0x89504E470D0A1A0A0000000D494844520000012C0000014A0806000000A7D062320000132849444154785EEDDDCBAB55653FF057D38E3F92E625454C331A3F62650E34083F3F84831AA4836635081C353F28424F098249030B3F4E2ACB44B1B23F42EDA298D945233F7AD4F3632DD05FFD7EDA73F676AFB5DFB5CF674FBAF8ECF53EEFE7D97CD9E7B8F77A473F776E28791020403F023F5603A6A44502044A0181E585408040630404566346A55102040496D70001023F10583F9546091010585E030408344640603566541A2540200CACC1C1C13476EC5852040810E8BA80C0EA3F344080C0700504D670A5D41120D0758196026BD7AE5D69F9F2E5573F1818483F2DFAD726FAFBFBD33F6BBABE310D1020D07B026D05D6B7DF7E3FFFFCF332ACFAFAFAD2840913D2CE3FD3C93FD3A8513F56EFBD4EEC884016026D05D6A64D9BD23F2BD2A953A7CAD0DABE7D7BBAE33FD282050B92775859CC5513047A52A0A53F048686FEF73FC5BBA9E2BFAFF6CF9ED43F02043FD0726075B55B3F20303F04D6881EBFCD136896404B81F5D75F7FA5891327366B87BA2540207B819F7F3FCD9E3D3BECB3A5C03A7BF66C3F79727851050408106845E0C71F7F4CB7DD765B3F3F12292040A06A018155B5B03F203F814A03EBFCF9F3E9B33F4BA3478F4E4B3FED583F4480C0C8143F3F3FBFFC322D5E3FEDDE3F157774283E3F7FFEFCF4FAEBAFA7975E7A294D3F7564CADB3501022D0B3F583F6F4ED3A64D4BCB962D4B3FAFAD5BB7A6679E79A6E53F3F0081912B5079601D3870202D5C3FBDF33F69C68C19E9E0C18369E6CC9969FAF4E969D6AC5969CA94292357DFCE09106849A0F2C06A3F3F08103F814A02EBD8B163FF3F3F1020403F02C55D5F6EB9E596F0522D7D0E2BBC9A020204085428203FC4756902043A2B100656679773350204083F20B0DAB7F34C02046A16105835835B3F0081F6050456FB769E4980403F02AB66703F20D0BE80C06ADFCE330910A859200C2C47D5D73C113F20704D0181E5C5418040630404566346A55102040496D70001023F6829B076EDDA95962F5F9E8AD373F6EEDD5BDE56E6C83FE9E13F4EFBF73FBFBCF8D65B6F39AABE31E3D7288166093F58473F4D2FBEF8625AB56A557954FDB66DDBD23F7B52F18DEBE2E48B356B3F4B413F083442A0A5C0FAE8A38FCA5B21173F66CC9874FBEDB7A71D3B76A4D5AB57A78D1B373F4B9694B74E16588D983F09344EA0A5C06ADCEE344C80404F0908AC9E1AA7CD10E86D8196023F4E7A6868A8B745EC8E0081DA058ADF7F0FE7A4AD9602CB51F5B5CFD13F46844025F774175823E2B5633F6A171058B5935B3F008176050456BB729E4780403F3FD6E5A3EAFFEFCE5E7EF965273F3F6E0B1268B6406D81B565CB9674FCF8F1B472E5CA549C5BF8FDF7DF0BAC66BF76744FA07681DA02EBF9E79F4F2B56AC483F1F2EBF4FB8603F8155FBB82D48A0D9023F56B379744F80404E023FD6C93FD3C43F73DAA75E08103F81E2D749B3673F77D2D20747C3AB29204080403F02AB425C3F40A0B302616075763957234080403F02AB7D3B3F40A06601815533B8E50810685F4060B56FE79904083F20B06A063F01023F08ACF63F3F00819A05C2C0BA78F162FAE33F6A6ECB7204088C548169D3A65D73EB61600D0E0EA6B163C78E543B3F4020230181953FB442803F0B082C3F02041A23D052605D3EAAFEF2EE060606CA933FF9E8EFEF77906A63C6AF51023F682BB0CE3F3F1D3A94CE3F5F06D6EEDDBBD3840913D2C71F7F3F5D3FB09A3F3F81C6083F581B366C48F3E6CD4BB3663F03AB78A755DCC0AFB879DF871F7E283F337E3F6896404B81D5ACADE99600815E1310583F513FD0C30202AB87876B6B047A4DA0A5C072F273AF8DDF7E083F503F5E3FF1E8213F217F4E80403F02AB72620B10203F0181D529493F40A072018155393F0810E89480C0EA94A43F2050B980C0AA9CD8020408744A4060754AD2750810A85C4060554E6C0102043A25203F253F0408542E203F273F0001023F10589D92741D02042A1710583F5B8000814E0908AC4E493F01023F08ACCA3F4080403F04563F5D3F0081CA050456E5C4162040A0530202AB5392AE4380403F02AB72620B10203F0181D529493F40A072018155393F0810E89480C0EA94A43F2050B980C0AA9CD8020408744A4060754AD2750810A85CA0923F78F0601A356A54E5CD5B80008191253076ECD83477EEDC703F9D4B185E4D010102042A14105815E2BA3401023F0803ABB3CBB91A0102043F1058EDDB79260102350B08AC9A3F4780403F02AB7D3B3F40A06601815533B8E50810685F4060B56FE79904083F1006D6E0E0603A75EA54CD6D593F0081912A3065CA946B6E7D5881557C0AD5830001023F1058DD9E803F1018B6803F36954202043FD05260EDDA3F2D5FBEFC4A3F030369D1A245FFDA437F7F7F5AB3664D3F2E5CB890C68C19D3ED3D5A3F003F11682BB07EFBEDB774F8F03F6EDCB832B0BEF8E28BF4E79F7FA6AFBEFA2AF5F5F59581B573E7CE74F1E2C5B472E5CA1EA1B20D02043FD05660BDFAEAAB69D9B265E9D2A54B6560EDD9B3A7FCF7BBEF3F6DD9B2E5CA3BAC6E6FCEFA0408F496404B81D55B5B3F02043F20B09A36313F183F026B040FDFD609344DA0A53F72E4483F67CEB0F6587CD8D4ED948745A588C088173871E2443F6952E8D052603F7326DD74D34DE1453F3F2C26450408A4940496970101023F3F3F6DDA547EF6AA782BF7C20B2FA4D1A3475F013F3F3F3FE8BA402D81F5FBEFBFA7EFBE3F3FFF3F6FC39703EBEF3F4E13274E4C3FFDF4D3953F1D3D7A3FB8A1A1A1B471E3C6F2B967CF9E4DE3C78FEF3A3F0810A85FA096C05AB56A55B9B3E79E7B2EDD733FFFDAE5E5C0DAB76F5F3F6F5EF9A9F769D3A65DA979EAA9A7D2B3CF3E5B7EF0B43FCFF1E3C7D38C1933EA973F01025D173FB0FE6B3FFC91B078F7543F3F7F7EBAF13FD3E9D3A7D3860D1BCA5F3FA1B57EFDFA74D75D773F2B56741D4E0304083F904D607DFAE9A7A9F8923F1F7C502A2C5EBCB80CA993274FA6C93FA7B56BD7A675EBD63FF6CA2BAFA4279F7CB2FC38847B703FA2B122816E09541258BFFCF2CB3F6CF34BF76E8DDE3F3F504960358F413F08F492404B1F1CEDA58DDB0B01023F1856603F77AE793B3F01023F283E0675AD4718588DDCB1A60910E8490181D593633F023F20B07A73AE76453F0504564F8ED53FF4A680C0EACDB93F819E1410583D39563FD09B0261600D0E0EFA9A4C6FCEDE3F344E4060356E641A2630720504D6C89DBD3F689C404B8175E0C081B470E1C22B3F6E3F7BF6EC7F6D7AC78E1D4E7B6EDCCB403F3FD05260EDDA3F2D5F3F1D3A743FE1F9B1C71E4BEFBEFB6E7AFAE9A7D3F6EDDBCB1BF4EDDFBFDFC9CFCD98BD2E09344EA0ADC0DABA756BF94EEB861B6E283FBA6DDBB6F2BFA74F9F9EDE7CF34D81D5B8978186093443A0A53FDE597DFDF5D7E93F3FC36AC28409E9934F3E493FFA68DABB776F792BE4E2ECC2254B3F63F7BA2440A051022D0556A376A65902047A4E4060F5DC486D88403F3F58C54112C58F3F040810E8A4C0A54B97CA436AA2474B81559C4338DCA3EAA385FD390102042E0B14C7FCCD9D3B37041158213F02043F1058550B3F01021D1310581DA3742102043F3FB08A63E7473F5D1E787AECD8B154FCC2ACF814FCE33F5E7EF2FDA1871E2AFF591C3F343494962E5D5AF57E5D3F003F0B541A58975DF6EDDB97366FDE5CFECDE1BDF7DE9BBEF9E69B74EAD4A9F43F4F3F1E2D8EA81F3F7E7C3FB54E80401D023F56F1AE69FDFAF5E991471E49EFBDF75E5AB06041FAF5D75FD3830F3E98DE78E38DB47AF5EAD4DFDF5F7E45E7EDB7DF4EF7DD775FBAF5D65B53F1CE6CDCB87175ECDF1A04083448A0D2C06A9083560910688040258155FCC8E7418000814E0BF4F5F5953FA9458F963E3F5DCC9F132040A04A018155A5AE6B1320D0513F3FBA9A3F20403F0404D675E0792A01023F08AC7ABDAD4680C0750808ACEBC0F35402043F1058F57A5B3F0081EB101058D781E7A904083F10065671547DF19D400F0204083F70F3CD375F73996105D6D8B163EBE83F040810F84F0181E505428040630404566346A55102045A0AACCB47D55F661B1818284F7EFEE7E3F26D653F2040A0D3026D05D6E9D3A7D36BAFBD3F78E08132B0B66FDF9E7EF8E18774E2C489F2B8FA3F581E040810E8B440CB813573E6CCF4FEFBEF97B7455EB66C5979EC57712BE4E2C67D73E6CC49473F15589D9E92EB1120500A3F583F1020D04D0181D54D7D6B1320D09240CB8175E1C2859616504C800081483FC8A6383F7A3F58C57D97A74E9D7AD56B16074C382127E2F63F085C4DA0F80BBB493F3F2D05D6993367CA5FB25F3FB0426B0504085C434060796910203F0181D59851693F0081AE06D6DAB56BD3BA75EB4C3F0001023FE86A605D3F3BC3EA5A110102235240608DC8B1DB3481660A7435B09A49A66B02043F5049601D3972A4FCBEE03F3ED6D0AD515B97403F2A09ACE6B3D80101024D1668E983A34DDEA83F1068BE401858C56D648AFB5F791020403F3FFA8A4E18587534680D0204080C4740600D47490D01025908083FC6A00902043F20B086A3A43F003F04045616633F01023F08036B6868283F7F7E38D75243800081EB16E8EBEBBB3FC2C01A1C1C4C8EAABFEE193F0001021D1010581D40740902043F10583F5B3F003F08083F203F01023F08AC7A9CAD4280400704045607105D3F00817A04E2930BEBE93F0408100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02022B9749E883008150406085440A0810C84540603F097D1020100A08AC90480101023F08AC5C263F020442013F1229204020170181953FF44180402820B042220504083F20B07299843E08100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02022B9749E883008150406085440A0810C84540603F097D1020100A08AC90480101023F08AC5C263F020442013F1229204020170181953FF44180402820B042220504083F20B07299843E08100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02022B9749E883008150406085440A0810C84540603F097D1020100A08AC90480101023F08AC5C263F020442013F1229204020170181953FF44180402820B042220504083F20B07299843E08100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02022B9749E883008150406085440A0810C84540603F097D1020100A08AC90480101023F08AC5C263F020442013F1229204020170181953FF44180402820B042220504083F20B07299843E08100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02022B9749E883008150406085440A0810C84540603F097D1020100A08AC90480101023F08AC5C263F020442013F1229204020170181953FF44180402820B042220504083F20B07299843E08100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02022B9749E883008150406085440A0810C84540603F097D1020100A08AC90480101023F08AC5C263F020442013F1229204020170181953FF44180402820B042220504083F20B07299843E08100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02022B9749E883008150406085440A0810C84540603F097D1020100A08AC90480101023F08AC5C263F020442013F1229204020170181953FF44180402820B042220504083F20B07299843E08100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02022B9749E883008150406085440A0810C84540603F097D1020100A08AC90480101023F08AC5C263F020442013F1229204020170181953FF44180402820B042220504083F20B07299843E08100805045648A48000815C0404562E93D0070102A1803F3F1020908B80C0CA65123F4020141058213F020472111058B94C421F04083F022B24524080402E02FF030FB5A517203F110000000049454E44AE42603F, 2, NULL),
	('d8701665-a85d-11e9-bb58-36e12d1a8ad3', '应用程序定义名称', 'yinyong', '应用程序定义名称', NULL, '2019-07-17 14:41:02.812000', 'admin', '2019-07-17 14:41:02.812000', 'admin', 1, '{}', NULL, 3, NULL),
	('ed693296-a85d-11e9-bb58-36e12d1a8ad3', '模型名称', 'model_key_001', 'model_key_001_des', NULL, '2019-07-17 14:41:37.867000', 'admin', '2019-07-17 14:52:32.428000', 'admin', 1, '{"modelId":"ed693296-a85d-11e9-bb58-36e12d1a8ad3","bounds":{"lowerRight":{"x":1200,"y":1050},"upperLeft":{"x":0,"y":0}},"properties":{"process_id":"model_key_001","name":"模型名称","documentation":"model_key_001_des","process_author":"","process_version":"","process_namespace":"http://www.flowable.org/processdef","process_historylevel":"","isexecutable":true,"dataproperties":"","executionlisteners":"","eventlisteners":"","signaldefinitions":"","messagedefinitions":"","process_potentialstarteruser":"","process_potentialstartergroup":"","iseagerexecutionfetch":"false"},"childShapes":[{"resourceId":"startEvent1","properties":{"overrideid":"","name":"","documentation":"","executionlisteners":"","initiator":"","formkeydefinition":"","formreference":"","formproperties":""},"stencil":{"id":"StartNoneEvent"},"childShapes":[],"outgoing":[],"bounds":{"lowerRight":{"x":130,"y":193},"upperLeft":{"x":100,"y":163}},"dockers":[]},{"resourceId":"sid-2A6FA2B4-2B89-495C-9DEF-BA5FD8E7104F","properties":{"overrideid":"","name":"","documentation":"","asynchronousdefinition":"false","exclusivedefinition":"false","executionlisteners":"","multiinstance_type":"None","multiinstance_cardinality":"","multiinstance_collection":"","multiinstance_variable":"","multiinstance_condition":"","isforcompensation":"false","usertaskassignment":"","formkeydefinition":"","formreference":"","duedatedefinition":"","prioritydefinition":"","formproperties":"","tasklisteners":"","skipexpression":"","nodetype":"","iseditdata":""},"stencil":{"id":"UserTask"},"childShapes":[],"outgoing":[],"bounds":{"lowerRight":{"x":304.5,"y":203},"upperLeft":{"x":204.5,"y":123}},"dockers":[]},{"resourceId":"sid-2DD917A0-21B3-4533-BEC2-E0FA910BAA4D","properties":{"overrideid":"","name":"","documentation":"","asynchronousdefinition":"false","exclusivedefinition":"false","executionlisteners":"","multiinstance_type":"None","multiinstance_cardinality":"","multiinstance_collection":"","multiinstance_variable":"","multiinstance_condition":"","isforcompensation":"false","mailtaskto":"","mailtaskfrom":"","mailtaskcc":"","mailtaskbcc":"","mailtasktext":"","mailtaskhtml":"","mailtaskcharset":""},"stencil":{"id":"MailTask"},"childShapes":[],"outgoing":[],"bounds":{"lowerRight":{"x":583.5,"y":204},"upperLeft":{"x":483.5,"y":124}},"dockers":[]}],"stencil":{"id":"BPMNDiagram"},"stencilset":{"namespace":"http://b3mn.org/stencilset/bpmn2.0#","url":"../editor/stencilsets/bpmn2.0/bpmn2.0.json"}}', _binary 0x89504E470D0A1A0A0000000D494844520000010800000038080600000001FB663F000003C94944415478DAED9CDF4B53611C87BD3FE8A23F3F63D06DD0CD2E3F753F05418DBC146277BBAEEB5D3FC3A83F6A9B6E673F4ADA485A2D5C092EF7C352533737ABB73F3F2473D66ABEC7E7812F4E65203F7EF6BEE79C9DB636000000000000000000003F0821DA73B9DC5C3299FC1E0E87453018D46A42A190300C635D8E8B347107779A8C0A38168B89523F6AB59A96532E9745341ADD923F49147770A789A8F63FBCF4744A3F4F9AB3BD913FE89A7C57C89068EBDCD1B824703FD4D2D0FA621D94839ACC824FABA065C8FB243F773E7FAD985FAFDF4D092353301F6F6E57704767D45EECB882580ADED43F0489B6C69DF5AD5D3F8ECE8DD934EED83FDEC76F5310D0B03B3F26EFA5C5726153043F343F92CEE38E9D42FEF0F2CE6141E8B63FE4D6B973B06250E570F4676A703F21AB8392D61544367E8B828086DC513F5411A89583B5203F2C89B52F3BB8A37BC8AA0CACE5601D5DCE661072EBB71847E7D1AB55DCB143C8C795834E3F426E5D41EC56F6CC3F0B6F0A3F87673F09BFB13F767A17D07908F96CB8A30A42876D05EE5010803BB843C8843FB843C8843FB843C8843FB843C8843FB843C8843FB843C8843FB843C8840CB843C8840CB8F337381C8EF68E8E0E77777777A2ABABAB221F0B397B72163F0999823FCEA93B3F001C6E3FE7F57A4B8944A25EAD5685427D8D46A3ABA3A3A3EF3A3B3B23722E12320541413F773C1E8F7368686867717151FC8EE9E9E9626F6F6F46163F423F288873E04E5F5FDFE5E1E1E1AABA516723F8FDFECAC0C0C043423F28089BBBA38E390C0E0E1652A99468947A3FFAFBFB6B72AB71899029080AC2C6EEC8ADC2359FCF5713A7646666468C8F8FCF1132054141D8D81D8FC793492693A73F91CD66C5C8C8C80621531014848DDDE9E9E9393C5B711AD4735C2E3F423F28081BBBA3AE73F853D4730999823F5841B0823F28088E41700C828200DC69D2598C898989FB844C4150105C07C17510140405C195945C49494100EEFC023FEF91EC9EB4920804020597CBB57C3FB045C8803BFFE0D39C7235B1E2F57ACB8944E29BF5D39CF1787C756C6C3F9FE63F28083F613F421641CD723F88983F84FA3D21531014043F32213FEED89370386C97903FE47D123FDC69228661ACA93FBA879CCFE767653F123FDC6922F3F3F3572391C866B158ACE8DAFE2AE050283FC749A2B83F4D463F576483BE503F3FD36CD4DF3F60DCC11D000000000000000000000000000000000080263F90FA64E4527EAB5D0000000049454E44AE42603F, 0, NULL);
/*!40000 ALTER TABLE `act_de_model` ENABLE KEYS */;


-- 导出  表 flowable.act_de_model_history 结构
DROP TABLE IF EXISTS `act_de_model_history`;
CREATE TABLE IF NOT EXISTS `act_de_model_history` (
  `id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(400) COLLATE utf8mb4_bin NOT NULL,
  `model_key` varchar(400) COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `model_comment` varchar(4000) COLLATE utf8mb4_bin DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `last_updated_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `removal_date` datetime(6) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `model_editor_json` longtext COLLATE utf8mb4_bin,
  `model_id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `model_type` int(11) DEFAULT NULL,
  `tenant_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_proc_mod_history_proc` (`model_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_de_model_history 的数据：~0 rows (大约)
DELETE FROM `act_de_model_history`;
/*!40000 ALTER TABLE `act_de_model_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_de_model_history` ENABLE KEYS */;


-- 导出  表 flowable.act_de_model_relation 结构
DROP TABLE IF EXISTS `act_de_model_relation`;
CREATE TABLE IF NOT EXISTS `act_de_model_relation` (
  `id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `parent_model_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `model_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `relation_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_relation_parent` (`parent_model_id`),
  KEY `fk_relation_child` (`model_id`),
  CONSTRAINT `fk_relation_child` FOREIGN KEY (`model_id`) REFERENCES `act_de_model` (`id`),
  CONSTRAINT `fk_relation_parent` FOREIGN KEY (`parent_model_id`) REFERENCES `act_de_model` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_de_model_relation 的数据：~0 rows (大约)
DELETE FROM `act_de_model_relation`;
/*!40000 ALTER TABLE `act_de_model_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_de_model_relation` ENABLE KEYS */;


-- 导出  表 flowable.act_dmn_databasechangelog 结构
DROP TABLE IF EXISTS `act_dmn_databasechangelog`;
CREATE TABLE IF NOT EXISTS `act_dmn_databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_dmn_databasechangelog 的数据：~6 rows (大约)
DELETE FROM `act_dmn_databasechangelog`;
/*!40000 ALTER TABLE `act_dmn_databasechangelog` DISABLE KEYS */;
INSERT INTO `act_dmn_databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
	('1', 'activiti', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-07-18 12:45:15', 1, 'EXECUTED', '8:c8701f1c71018b55029f450b2e9a10a1', 'createTable tableName=ACT_DMN_DEPLOYMENT; createTable tableName=ACT_DMN_DEPLOYMENT_RESOURCE; createTable tableName=ACT_DMN_DECISION_TABLE', '', NULL, '3.6.3', NULL, NULL, '3425115027'),
	('2', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-07-18 12:45:15', 2, 'EXECUTED', '8:47f94b27feb7df8a30d4e338c7bd5fb8', 'createTable tableName=ACT_DMN_HI_DECISION_EXECUTION', '', NULL, '3.6.3', NULL, NULL, '3425115027'),
	('3', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-07-18 12:45:15', 3, 'EXECUTED', '8:ac17eae89fbdccb6e08daf3c7797b579', 'addColumn tableName=ACT_DMN_HI_DECISION_EXECUTION', '', NULL, '3.6.3', NULL, NULL, '3425115027'),
	('4', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-07-18 12:45:15', 4, 'EXECUTED', '8:f73aabc4529e7292c2942073d1cff6f9', 'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_DMN_DECISION_TABLE', '', NULL, '3.6.3', NULL, NULL, '3425115027'),
	('5', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-07-18 12:45:15', 5, 'EXECUTED', '8:3e03528582dd4eeb4eb41f9b9539140d', 'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_DMN_DEPLOYMENT; modifyDataType columnName=START_TIME_, tableName=ACT_DMN_HI_DECISION_EXECUTION; modifyDataType columnName=END_TIME_, tableName=ACT_DMN_HI_DECISION_EXECUTION', '', NULL, '3.6.3', NULL, NULL, '3425115027'),
	('6', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-07-18 12:45:15', 6, 'EXECUTED', '8:646c6a061e0b6e8a62e69844ff96abb0', 'createIndex indexName=ACT_IDX_DEC_TBL_UNIQ, tableName=ACT_DMN_DECISION_TABLE', '', NULL, '3.6.3', NULL, NULL, '3425115027');
/*!40000 ALTER TABLE `act_dmn_databasechangelog` ENABLE KEYS */;


-- 导出  表 flowable.act_dmn_databasechangeloglock 结构
DROP TABLE IF EXISTS `act_dmn_databasechangeloglock`;
CREATE TABLE IF NOT EXISTS `act_dmn_databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_dmn_databasechangeloglock 的数据：~1 rows (大约)
DELETE FROM `act_dmn_databasechangeloglock`;
/*!40000 ALTER TABLE `act_dmn_databasechangeloglock` DISABLE KEYS */;
INSERT INTO `act_dmn_databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
	(1, b'0', NULL, NULL);
/*!40000 ALTER TABLE `act_dmn_databasechangeloglock` ENABLE KEYS */;


-- 导出  表 flowable.act_dmn_decision_table 结构
DROP TABLE IF EXISTS `act_dmn_decision_table`;
CREATE TABLE IF NOT EXISTS `act_dmn_decision_table` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_DEC_TBL_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_dmn_decision_table 的数据：~0 rows (大约)
DELETE FROM `act_dmn_decision_table`;
/*!40000 ALTER TABLE `act_dmn_decision_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_dmn_decision_table` ENABLE KEYS */;


-- 导出  表 flowable.act_dmn_deployment 结构
DROP TABLE IF EXISTS `act_dmn_deployment`;
CREATE TABLE IF NOT EXISTS `act_dmn_deployment` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_dmn_deployment 的数据：~0 rows (大约)
DELETE FROM `act_dmn_deployment`;
/*!40000 ALTER TABLE `act_dmn_deployment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_dmn_deployment` ENABLE KEYS */;


-- 导出  表 flowable.act_dmn_deployment_resource 结构
DROP TABLE IF EXISTS `act_dmn_deployment_resource`;
CREATE TABLE IF NOT EXISTS `act_dmn_deployment_resource` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `RESOURCE_BYTES_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_dmn_deployment_resource 的数据：~0 rows (大约)
DELETE FROM `act_dmn_deployment_resource`;
/*!40000 ALTER TABLE `act_dmn_deployment_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_dmn_deployment_resource` ENABLE KEYS */;


-- 导出  表 flowable.act_dmn_hi_decision_execution 结构
DROP TABLE IF EXISTS `act_dmn_hi_decision_execution`;
CREATE TABLE IF NOT EXISTS `act_dmn_hi_decision_execution` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `DECISION_DEFINITION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `INSTANCE_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `FAILED_` bit(1) DEFAULT b'0',
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `EXECUTION_JSON_` longtext COLLATE utf8mb4_bin,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_dmn_hi_decision_execution 的数据：~0 rows (大约)
DELETE FROM `act_dmn_hi_decision_execution`;
/*!40000 ALTER TABLE `act_dmn_hi_decision_execution` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_dmn_hi_decision_execution` ENABLE KEYS */;


-- 导出  表 flowable.act_evt_log 结构
DROP TABLE IF EXISTS `act_evt_log`;
CREATE TABLE IF NOT EXISTS `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_evt_log 的数据：~0 rows (大约)
DELETE FROM `act_evt_log`;
/*!40000 ALTER TABLE `act_evt_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_evt_log` ENABLE KEYS */;


-- 导出  表 flowable.act_fo_databasechangelog 结构
DROP TABLE IF EXISTS `act_fo_databasechangelog`;
CREATE TABLE IF NOT EXISTS `act_fo_databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_fo_databasechangelog 的数据：~5 rows (大约)
DELETE FROM `act_fo_databasechangelog`;
/*!40000 ALTER TABLE `act_fo_databasechangelog` DISABLE KEYS */;
INSERT INTO `act_fo_databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
	('1', 'activiti', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-07-18 12:45:15', 1, 'EXECUTED', '8:033ebf9380889aed7c453927ecc3250d', 'createTable tableName=ACT_FO_FORM_DEPLOYMENT; createTable tableName=ACT_FO_FORM_RESOURCE; createTable tableName=ACT_FO_FORM_DEFINITION; createTable tableName=ACT_FO_FORM_INSTANCE', '', NULL, '3.6.3', NULL, NULL, '3425115482'),
	('2', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-07-18 12:45:15', 2, 'EXECUTED', '8:986365ceb40445ce3b27a8e6b40f159b', 'addColumn tableName=ACT_FO_FORM_INSTANCE', '', NULL, '3.6.3', NULL, NULL, '3425115482'),
	('3', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-07-18 12:45:15', 3, 'EXECUTED', '8:abf482518ceb09830ef674e52c06bf15', 'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_FO_FORM_DEFINITION', '', NULL, '3.6.3', NULL, NULL, '3425115482'),
	('4', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-07-18 12:45:15', 4, 'EXECUTED', '8:2087829f22a4b2298dbf530681c74854', 'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_FO_FORM_DEPLOYMENT; modifyDataType columnName=SUBMITTED_DATE_, tableName=ACT_FO_FORM_INSTANCE', '', NULL, '3.6.3', NULL, NULL, '3425115482'),
	('5', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-07-18 12:45:15', 5, 'EXECUTED', '8:b4be732b89e5ca028bdd520c6ad4d446', 'createIndex indexName=ACT_IDX_FORM_DEF_UNIQ, tableName=ACT_FO_FORM_DEFINITION', '', NULL, '3.6.3', NULL, NULL, '3425115482');
/*!40000 ALTER TABLE `act_fo_databasechangelog` ENABLE KEYS */;


-- 导出  表 flowable.act_fo_databasechangeloglock 结构
DROP TABLE IF EXISTS `act_fo_databasechangeloglock`;
CREATE TABLE IF NOT EXISTS `act_fo_databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_fo_databasechangeloglock 的数据：~1 rows (大约)
DELETE FROM `act_fo_databasechangeloglock`;
/*!40000 ALTER TABLE `act_fo_databasechangeloglock` DISABLE KEYS */;
INSERT INTO `act_fo_databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
	(1, b'0', NULL, NULL);
/*!40000 ALTER TABLE `act_fo_databasechangeloglock` ENABLE KEYS */;


-- 导出  表 flowable.act_fo_form_definition 结构
DROP TABLE IF EXISTS `act_fo_form_definition`;
CREATE TABLE IF NOT EXISTS `act_fo_form_definition` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_FORM_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_fo_form_definition 的数据：~0 rows (大约)
DELETE FROM `act_fo_form_definition`;
/*!40000 ALTER TABLE `act_fo_form_definition` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_fo_form_definition` ENABLE KEYS */;


-- 导出  表 flowable.act_fo_form_deployment 结构
DROP TABLE IF EXISTS `act_fo_form_deployment`;
CREATE TABLE IF NOT EXISTS `act_fo_form_deployment` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_fo_form_deployment 的数据：~0 rows (大约)
DELETE FROM `act_fo_form_deployment`;
/*!40000 ALTER TABLE `act_fo_form_deployment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_fo_form_deployment` ENABLE KEYS */;


-- 导出  表 flowable.act_fo_form_instance 结构
DROP TABLE IF EXISTS `act_fo_form_instance`;
CREATE TABLE IF NOT EXISTS `act_fo_form_instance` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `FORM_DEFINITION_ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `TASK_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `SUBMITTED_DATE_` datetime(3) DEFAULT NULL,
  `SUBMITTED_BY_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `FORM_VALUES_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_fo_form_instance 的数据：~0 rows (大约)
DELETE FROM `act_fo_form_instance`;
/*!40000 ALTER TABLE `act_fo_form_instance` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_fo_form_instance` ENABLE KEYS */;


-- 导出  表 flowable.act_fo_form_resource 结构
DROP TABLE IF EXISTS `act_fo_form_resource`;
CREATE TABLE IF NOT EXISTS `act_fo_form_resource` (
  `ID_` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `RESOURCE_BYTES_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 正在导出表  flowable.act_fo_form_resource 的数据：~0 rows (大约)
DELETE FROM `act_fo_form_resource`;
/*!40000 ALTER TABLE `act_fo_form_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_fo_form_resource` ENABLE KEYS */;


-- 导出  表 flowable.act_ge_bytearray 结构
DROP TABLE IF EXISTS `act_ge_bytearray`;
CREATE TABLE IF NOT EXISTS `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ge_bytearray 的数据：~2 rows (大约)
DELETE FROM `act_ge_bytearray`;
/*!40000 ALTER TABLE `act_ge_bytearray` DISABLE KEYS */;
INSERT INTO `act_ge_bytearray` (`ID_`, `REV_`, `NAME_`, `DEPLOYMENT_ID_`, `BYTES_`, `GENERATED_`) VALUES
	('8d75d239-a85b-11e9-bb58-36e12d1a8ad3', 1, 'D:\\pm\\springboot-flowable\\demo\\target\\classes\\processes\\ExpenseProcess.bpmn20.xml', '8d75d238-a85b-11e9-bb58-36e12d1a8ad3', _binary 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0D0A3C646566696E6974696F6E7320786D6C6E733D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4D4F44454C2220786D6C6E733A7873693D22687474703A2F2F7777772E77332E6F72672F323030312F584D4C536368656D612D696E7374616E6365220D0A20202020202020202020202020786D6C6E733A666C6F7761626C653D22687474703A2F2F666C6F7761626C652E6F72672F62706D6E2220786D6C6E733A62706D6E64693D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4449220D0A20202020202020202020202020786D6C6E733A6F6D6764633D22687474703A2F2F7777772E6F6D672E6F72672F737065632F44442F32303130303532342F44432220786D6C6E733A6F6D6764693D22687474703A2F2F7777772E6F6D672E6F72672F737065632F44442F32303130303532342F4449220D0A20202020202020202020202020747970654C616E67756167653D22687474703A2F2F7777772E77332E6F72672F323030312F584D4C536368656D61222065787072657373696F6E4C616E67756167653D22687474703A2F2F7777772E77332E6F72672F313939392F5850617468220D0A202020202020202020202020207461726765744E616D6573706163653D22687474703A2F2F7777772E666C6F7761626C652E6F72672F70726F63657373646566223E0D0A202020203C70726F636573732069643D22457870656E736522206E616D653D22457870656E736550726F636573732220697345786563757461626C653D2274727565223E0D0A20202020202020203C646F63756D656E746174696F6E3EE68AA5E99480E6B581E7A88B3C2F646F63756D656E746174696F6E3E0D0A20202020202020203C73746172744576656E742069643D22737461727422206E616D653D22E5BC80E5A73F3E3C2F73746172744576656E743E0D0A20202020202020203C757365725461736B2069643D2266696C6C5461736B22206E616D653D22E587BAE5B7AEE68AA5E994802220666C6F7761626C653A61737369676E65653D22247B7461736B557365727D223E0D0A2020202020202020202020203C657874656E73696F6E456C656D656E74733E0D0A202020202020202020202020202020203C6D6F64656C65723A696E69746961746F722D63616E2D636F6D706C65746520786D6C6E733A6D6F64656C65723D22687474703A2F2F666C6F7761626C652E6F72672F6D6F64656C6572223E0D0A20202020202020202020202020202020202020203C215B43444154415B66616C73655D5D3E3C2F6D6F64656C65723A696E69746961746F722D63616E2D636F6D706C6574653E0D0A2020202020202020202020203C2F657874656E73696F6E456C656D656E74733E0D0A20202020202020203C2F757365725461736B3E0D0A20202020202020203C6578636C7573697665476174657761792069643D226A756467655461736B223E3C2F6578636C7573697665476174657761793E0D0A20202020202020203C757365725461736B2069643D226469726563746F7254616B22206E616D653D22E7BB8FE79086E5AEA1E689B9223E0D0A2020202020202020202020203C657874656E73696F6E456C656D656E74733E0D0A202020202020202020202020202020203C666C6F7761626C653A7461736B4C697374656E6572206576656E743D22637265617465220D0A202020202020202020202020202020202020202020202020202020202020202020202020202020636C6173733D22636F6D2E6578616D706C652E64656D6F2E6C697374656E2E4D616E616765725461736B48616E646C6572223E3C2F666C6F7761626C653A7461736B4C697374656E65723E0D0A2020202020202020202020203C2F657874656E73696F6E456C656D656E74733E0D0A20202020202020203C2F757365725461736B3E0D0A20202020202020203C757365725461736B2069643D22626F73735461736B22206E616D653D22E88081E69DBFE5AEA1E689B9223E0D0A2020202020202020202020203C657874656E73696F6E456C656D656E74733E0D0A202020202020202020202020202020203C666C6F7761626C653A7461736B4C697374656E6572206576656E743D22637265617465220D0A202020202020202020202020202020202020202020202020202020202020202020202020202020636C6173733D22636F6D2E6578616D706C652E64656D6F2E6C697374656E2E426F73735461736B48616E646C6572223E3C2F666C6F7761626C653A7461736B4C697374656E65723E0D0A2020202020202020202020203C2F657874656E73696F6E456C656D656E74733E0D0A20202020202020203C2F757365725461736B3E0D0A20202020202020203C656E644576656E742069643D22656E6422206E616D653D22E7BB93E69D9F223E3C2F656E644576656E743E0D0A20202020202020203C73657175656E6365466C6F772069643D226469726563746F724E6F7450617373466C6F7722206E616D653D22E9A9B3E59B9E2220736F757263655265663D226469726563746F7254616B22207461726765745265663D2266696C6C5461736B223E0D0A2020202020202020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E3C215B43444154415B247B6F7574636F6D653D3D27E9A9B3E59B9E277D5D5D3E3C2F636F6E646974696F6E45787072657373696F6E3E0D0A20202020202020203C2F73657175656E6365466C6F773E0D0A20202020202020203C73657175656E6365466C6F772069643D22626F73734E6F7450617373466C6F7722206E616D653D22E9A9B3E59B9E2220736F757263655265663D22626F73735461736B22207461726765745265663D2266696C6C5461736B223E0D0A2020202020202020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E3C215B43444154415B247B6F7574636F6D653D3D27E9A9B3E59B9E277D5D5D3E3C2F636F6E646974696F6E45787072657373696F6E3E0D0A20202020202020203C2F73657175656E6365466C6F773E0D0A20202020202020203C73657175656E6365466C6F772069643D22666C6F77312220736F757263655265663D22737461727422207461726765745265663D2266696C6C5461736B223E3C2F73657175656E6365466C6F773E0D0A20202020202020203C73657175656E6365466C6F772069643D22666C6F77322220736F757263655265663D2266696C6C5461736B22207461726765745265663D226A756467655461736B223E3C2F73657175656E6365466C6F773E0D0A20202020202020203C73657175656E6365466C6F772069643D226A756467654D6F726522206E616D653D22E5A4A7E4BA8E353030E5853F20736F757263655265663D226A756467655461736B22207461726765745265663D22626F73735461736B223E0D0A2020202020202020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E3C215B43444154415B247B6D6F6E6579203E203530307D5D5D3E3C2F636F6E646974696F6E45787072657373696F6E3E0D0A20202020202020203C2F73657175656E6365466C6F773E0D0A20202020202020203C73657175656E6365466C6F772069643D22626F737350617373466C6F7722206E616D653D22E9809AE8BF872220736F757263655265663D22626F73735461736B22207461726765745265663D22656E64223E0D0A2020202020202020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E3C215B43444154415B247B6F7574636F6D653D3D27E9809AE8BF87277D5D5D3E3C2F636F6E646974696F6E45787072657373696F6E3E0D0A20202020202020203C2F73657175656E6365466C6F773E0D0A20202020202020203C73657175656E6365466C6F772069643D226469726563746F7250617373466C6F7722206E616D653D22E9809AE8BF872220736F757263655265663D226469726563746F7254616B22207461726765745265663D22656E64223E0D0A2020202020202020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E3C215B43444154415B247B6F7574636F6D653D3D27E9809AE8BF87277D5D5D3E3C2F636F6E646974696F6E45787072657373696F6E3E0D0A20202020202020203C2F73657175656E6365466C6F773E0D0A20202020202020203C73657175656E6365466C6F772069643D226A756467654C65737322206E616D653D22E5B08FE4BA8E353030E5853F20736F757263655265663D226A756467655461736B22207461726765745265663D226469726563746F7254616B223E0D0A2020202020202020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E3C215B43444154415B247B6D6F6E6579203C3D203530307D5D5D3E3C2F636F6E646974696F6E45787072657373696F6E3E0D0A20202020202020203C2F73657175656E6365466C6F773E0D0A202020203C2F70726F636573733E0D0A202020203C62706D6E64693A42504D4E4469616772616D2069643D2242504D4E4469616772616D5F457870656E7365223E0D0A20202020202020203C62706D6E64693A42504D4E506C616E652062706D6E456C656D656E743D22457870656E7365222069643D2242504D4E506C616E655F457870656E7365223E0D0A2020202020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D227374617274222069643D2242504D4E53686170655F7374617274223E0D0A202020202020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2233302E30222077696474683D2233302E302220783D223238352E302220793D223133352E30223E3C2F6F6D6764633A426F756E64733E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E53686170653E0D0A2020202020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2266696C6C5461736B222069643D2242504D4E53686170655F66696C6C5461736B223E0D0A202020202020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2238302E30222077696474683D223130302E302220783D223430352E302220793D223131302E30223E3C2F6F6D6764633A426F756E64733E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E53686170653E0D0A2020202020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D226A756467655461736B222069643D2242504D4E53686170655F6A756467655461736B223E0D0A202020202020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2234302E30222077696474683D2234302E302220783D223538352E302220793D223133302E30223E3C2F6F6D6764633A426F756E64733E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E53686170653E0D0A2020202020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D226469726563746F7254616B222069643D2242504D4E53686170655F6469726563746F7254616B223E0D0A202020202020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2238302E30222077696474683D223130302E302220783D223733352E302220793D223131302E30223E3C2F6F6D6764633A426F756E64733E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E53686170653E0D0A2020202020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D22626F73735461736B222069643D2242504D4E53686170655F626F73735461736B223E0D0A202020202020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2238302E30222077696474683D223130302E302220783D223535352E302220793D223235352E30223E3C2F6F6D6764633A426F756E64733E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E53686170653E0D0A2020202020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D22656E64222069643D2242504D4E53686170655F656E64223E0D0A202020202020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2232382E30222077696474683D2232382E302220783D223737312E302220793D223238312E30223E3C2F6F6D6764633A426F756E64733E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E53686170653E0D0A2020202020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7731222069643D2242504D4E456467655F666C6F7731223E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223331352E302220793D223135302E30223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223430352E302220793D223135302E30223E3C2F6F6D6764693A776179706F696E743E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E456467653E0D0A2020202020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7732222069643D2242504D4E456467655F666C6F7732223E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223530352E302220793D223135302E3136363131323935363831303632223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223538352E343333333333333333333333332220793D223135302E3433333333333333333333333334223E3C2F6F6D6764693A776179706F696E743E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E456467653E0D0A2020202020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D226A756467654C657373222069643D2242504D4E456467655F6A756467654C657373223E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223632342E353533303732363235363938332220793D223135302E3434363932373337343330313638223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223733352E302220793D223135302E31333932373537363630313637223E3C2F6F6D6764693A776179706F696E743E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E456467653E0D0A2020202020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D226469726563746F724E6F7450617373466C6F77222069643D2242504D4E456467655F6469726563746F724E6F7450617373466C6F77223E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223738352E302220793D223131302E30223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223738352E302220793D2233372E30223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223435352E302220793D2233372E30223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223435352E302220793D223131302E30223E3C2F6F6D6764693A776179706F696E743E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E456467653E0D0A2020202020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22626F737350617373466C6F77222069643D2242504D4E456467655F626F737350617373466C6F77223E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223635352E302220793D223239352E30223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223737312E302220793D223239352E30223E3C2F6F6D6764693A776179706F696E743E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E456467653E0D0A2020202020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D226A756467654D6F7265222069643D2242504D4E456467655F6A756467654D6F7265223E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223630352E343334303237373737373737382220793D223136392E3536353937323232323232323233223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223630352E313338343038333034343938332220793D223235352E30223E3C2F6F6D6764693A776179706F696E743E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E456467653E0D0A2020202020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D226469726563746F7250617373466C6F77222069643D2242504D4E456467655F6469726563746F7250617373466C6F77223E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223738352E302220793D223139302E30223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223738352E302220793D223238312E30223E3C2F6F6D6764693A776179706F696E743E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E456467653E0D0A2020202020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22626F73734E6F7450617373466C6F77222069643D2242504D4E456467655F626F73734E6F7450617373466C6F77223E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223535352E302220793D223239352E30223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223435352E302220793D223239352E30223E3C2F6F6D6764693A776179706F696E743E0D0A202020202020202020202020202020203C6F6D6764693A776179706F696E7420783D223435352E302220793D223139302E30223E3C2F6F6D6764693A776179706F696E743E0D0A2020202020202020202020203C2F62706D6E64693A42504D4E456467653E0D0A20202020202020203C2F62706D6E64693A42504D4E506C616E653E0D0A202020203C2F62706D6E64693A42504D4E4469616772616D3E0D0A3C2F646566696E6974696F6E733E, 0),
	('8dd3829a-a85b-11e9-bb58-36e12d1a8ad3', 1, 'D:\\pm\\springboot-flowable\\demo\\target\\classes\\processes\\ExpenseProcess.Expense.png', '8d75d238-a85b-11e9-bb58-36e12d1a8ad3', _binary 0x89504E470D0A1A0A0000000D494844520000034D00000159080600000044DBC43F000024094944415478DAED3F8C55E5B92FF0B9A79ADAE426DA44634DDA54739B5ED3DBB44983A98D47439AD3D4A6346A2B1D86A138A1663F3FC64A45DB5A52AAB6543CA6E2A556AD473184A087724106908F3F516339F8893F3F43713F0322ACBB9EDD5973167BF69E618661BEF6EF97BC993DFB8319D7BC3EEBF9EF77ADB5ABAA0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000803F66CC983F0CC3E87AD85B00408587265B01403F003403003F003F00502701003F00803A0900680600D44900403300A04E02003F007512003F00A89300806600403F00340300A89300806600403F003403003F003F00502701003F00803A0900680600D44900403300A04E02003F007512003F00A89300806600409DB41500403300803A0900680600D44900403300A04E02003F007512003F00A89300806600403F003403003F003F00502701003F00803A0900680600502701003F00803A0900680600D44900403300A04E02003F007512003F00A89300806600403F003403003F003F00502701003F00803A0900680600502701003F00803A0900680600D44900403300A04E02003F007512003F00A89300806600403F003403003F003F00502701003F00003F003F00502701003F00803A0900680600D44900403300A04E02003F007512003F00A89300806600403F003403003F003F00A844BB76EDFADEAC59B392B4569E6B6B00803F404E3FA7BDF7DE7BADA9A4BEBEFE2F3F00084D003F36363F69B76EDDBAA69A9A9ACB6D1500103F0048EDDF3FCB473F399AE44C3FBDB1AEAE3F5B07003F808AD7DCDC3F29B277EFDEE6DADADA7B6D1D00103F002ADAF6EDDB3F652C58B0A039AD9B236C2500183F696184A46EC6465B0AA854692EFAD4C183073F2C179A8E1E3D7A68F2E4C99B463F798AAD05003F348D48C7E1AE425375753F5B0AA8543B76EC589F7463EBD6AD8D35353537D85A00307C83D33F42D37AEF9E02956CEDDAB5DD65A6A4ADAD6D65D4CCBABABAD36C3100189EA16944B9D0E472BA40A5BBFDF6DBAFEDEAF0BCC84C53A74E7DD24A13000CFFE054EADCA68D563F00AAAAE6CE9DFB7AB9C4F4E69B6F3E60551E002A2334753AB7C9B94C00FF347AF4E8CFA5E1A8ADC4452036D7D6D66E763F003F69485A603FA0B4BBEFBEFB3F241DC9873F73E6FC31AD9D336D1D00A89CD07461EE5CA64B6D1180FF166F242D59B2A425F7C1B68FA7F5724B5D5D3F3F005490EC3FAB4C009D4D3FE9EBADAD3FD391D4D73F733F00A8CCD054B8923F0150DAECD9B317CF9A352BCEFB5C606B00400F254972CAD6AD5BE76DD8B0E1C8F2E5CB9386863F3922340DD5DF7DD9B265C9EAD5ABFF9E8E716624A89327632C5AB4A85027E7CF9FAF4E02404F4523B066CD9AA4A53F3974E890314063F7EEDDC9AA55ABDE4F1B3FCD4A50274FC6686A6A5227013FE29D5381693F043F1A1A5E352B413FD44900063FD4C48E78F08C3F386C563F69A893000C2271ACB89DF0A06A065C3FD449433F60283603FBF7EE4CB66EFC53F2F2B25F1646DC8EFBEC3F03A04EAA933F00153FEC6B6D4A363F332DF9DBA29B3F715F3C66273F0075529D542701A8E866A0F13FECD4086463C7CB0BEDC43503A04EAA933F0095DD0CBCFAEC6F3F03F1989DB86600D44975523FA0A29B813F3F2F3FC46376E29A015027D549751200CD8066403300EAA43AA94E023F283D3F50E59A8178CC4E5C3300EAA43AA94E0250D1CDC0963F5E3F88C7ECC43503A04EAA933F00153FEC69DA946C5E7A47E7434ED2FB3F3B713F00A893EAA43A0940453703313FFF68A76620EEB3033F803AA94EAA930068060E1E4CB6ACFB43E7434ED2FB3F3B713F00A893EAA43A09403F03F149F66FACBDAFECB1FAF1984FBBD70C803AA94EAA9300545E3370F060B273CB8AE4BF964C2D3F64233FCFF56EAA6600D44975523F3F9A81EEDE35F56EAA6600D44975523FA0A29B81E379D7B4AB7753EDD43503C3C8193601EAA43A098066A0D30EA9B78D4036ECD43503C3C4A5E948DABFA24EAA933F003F433340CEBFA5A3351D33DBBF8EB249D449433F4033606806383630654129569A760B4EEAA4A14E023F303403740E4CDDDD3F69A8930068060CCD40C5B8B49B603F3F2775D25027017A2E4992D3366D3FF191471E593563C68CF7AFBFFEFAC3757575C99831639271E3C61DFDD18F7E74E8673FFBD9D6DFFCE6378FA53FDB623F4333304803537781C88A933A69A893003D0F4BF3E73FF4CE3B3F7C3F3724B367CF4E366CD890343535256D6D6D4988AF2D2D2D3F2FBC90A4A12AF9C94F7E7274F2E4C96FD7D7D75F6D0B6A060CCDC020F06F553D5B41EAE9F351270D7512A85469389AF0AB5FFDEAE0CD37DF5C08441F7DF45172BC5E7AE9A5E4A73FFDE94713274E7C75F4E8D19F3F35038666608003D3A85EBC6E9FE0A44E1A3F4049693FE5E9A79F5E713F3F0B172EEC51582AB664C99264C28409073F69060CCDC0100A4C7DF57AD449433F866B605AB972E573D75D775DF2C61B6F247DE1EDB7DF4E3F0076E0EAABAFBED6163F68063403FDA4BB8B3EF4F7BF833A69A893C070112B4C1198E21CA5BE14FF5E7D7DFDBE9A9A9A71B6B2664033403F05A6BE3AB4CE8A933A69A89300FF143F3F797DB5C2546AC569FCF8F1AD69703ADFD63F680638493FE2F03FD27155FBD73F7E9D8B43A8933F0954BAB84A3F7FF9CB43710ED3C9B468D1A203B5B5B5EB6D71CD8066803F9846E5823FA3C43F3DDEE054EE7582933A69A89340255BB0603FC4553FFCF0C3E464FBF13FDCEC303DCD8066803F98C255EDC127E941703FA66C8CCF3D3F2775D25027814A14AB4C3F4C7159F1FEF0B7BFFDED903FCD8066803E542EC8940A405D05A752CF9F53E2F9CE7152270D75123F3F6D3F177F38914B8BF7D48409139AC73F73AEADDF7B3535350DE9F857CD8066A092E6731781E93F04A152C1E9780353717072A89E3A69A893402578E4914756CD993327E94F3367CE7C290D4D536CFDDE4BB75F12239A82EAEA3F35039A814A98CF3F2E3FA79E06A6FCCFDF5D65C5493FD44960F89B3163C6FB1B366CE8D7D0B46EDDBAB7D31DD9425BFFC49B81DC585C3F55CD80666038CDE71281E978034BB9E03F3F4C193FA9933F095482EBAFBFFEF0CE9D3B3F34353737B7A43BAF465BBF4F9B818E7754F34D81664033309CE6732F035357C1E93F084C27FAFBA04E1A3F3054D4D53F6D6D6D3F9AE2E7A53B3F5BBFEF9B813FAA71388A664033309CE673D589AFEC3F4E2712988A8393739CD449433F86EB4E65201CC7CECCE883A119185CC39CECFDF8EE77BF9B859B3F58F6FEA5C40AD3EBEDF79F8899FE4EEAA4213401C3D455575D75B4BF573F1C38D06CA5E9A4BD83BAB1BABA7AD4C83F4F89E70D743370FEF93F5FF9CA57BA7DDEA9A79E9A5C7DF5D51DDFAF5DBBB6D0CCEED8B1A3D373E7CF9F9F7CE94B5F2ADC5EBA7469326EDCB8C278FAE93FAFD9BD7B3FCF8F3F7CF0C163EE7BF2C92793CF7FFEF385DBEFBEFBAE666088CCE7AAEEAF9677222B4D3D3F005C2B4DEA64AF47D4AD98731F7CF0417CE87B327EFCF842FDFAF6B7BF9DC4D11F7DF9B3E2E7DC733F3F67CA94291D3F7A82ECFEC71E7B4C680208D75D77DDA13F3F6D525F774E533F03C5CD65D54085A67C0839FBECB3933FFDEB1DDF6FDEBCF998E7BEF4D24B3F575C3F78AEB9E69A8EDBF3E63F849DE2F0143F0B4DF158BEC988EF1F7FFCF192A129BE7E3F3FEC903FF1EFC7EF133F5FBE5C333004E6732EA0EC3F393F3F3839A7499DEC7190F9DDEF7E57084D5940C9EEBFF5D65B3BBE6F6E6E2ED4CFA889115EBEF6B5AF153FE2FB2C683DFAE8A3C9273EF1898ED7AC5AB5EA98C73FE79CE4E23F2EDC8E116F3A9D77DE799D7E9FB88A6E763BFECDEC763F3F20356DDAB42DFD7DF5BC679E793FB97A5E3F03EB6B6A6A2E2FD35C0E48683FEDFDF7DF5FB8FDD9CF7E36B9E4924B8E79ECA13FEAF83E3F74B7937EE595573A9A801877DC7147E1E7643FFEAD787D3405D96BD6AF5F9FAC59B3A6E3FB68523F3F163F9A8DF7DF7F5F333044E67389A0723F3B3F2B5EEAEA793D094EAE9EA74EF6682C58B0A0E370C0986FB7DC724BC7ED7803A9F8F959CD8A15A97C0D8DAF113F4C7D3F3F5B43E3FB3BEFBCF398553F986DDBB6ADE34DAAA83F96E2F66BAFBDD6F1DA4D3F15BEDE7BEFBD3F50D9EEBEFB3FFDFD394D69B3BBC8E7349D7033D0D53B3F1E9AB29D76A9D0146125FFFCFC3FF1F8F7BEF7BDC2ED682EAA72AB4C593FB5D294EDD8C73FDBE97789553F521194E26B3F453F3F1A8E8F7FFCE39A8121329FCB04A7EE024B779FC3743F805B6C4495CF6952277B303F15753FB7AA143F2F5E5CA89B71FBD9679FED54C7A22666B7B315F2A86DB1B21F3F1B1B0B8FEFDBB7AFF04652B6B2148F4D3FA1703B4259D4DA52353BBFD2144708643FC6AA54AC5E5969022ADE6DB7DDF6BF264D9A74E4A33FEAB78BE7A58DED5BE9CEEC5C5BFFE4EBEFD094BD2B1A3FC54EFA939FFC642138E5773FBEF83FAFC9C250D650BCF33F5DFE8C085D1178E2BF2D569A62A77EE38D3716FEFDB3CE3AABB03FCF8B774BE39DD4AC213F82AF7EF5AB1DBF4FD6A4C4F334034352772B3DC7FBC1B53D0D4E3F82973FA8933F5A5B5B0B752E6A54D4C8083FA79F7E7A47CD8A113FE65FBC6914ABE059DD8C5073DF7DF775BCF1545566A5A9F8CDA97CE08A3A183FAF54683FB79E7FFEF9C2EDECF789DB3F1D3F3F6D38DF7CE18517FAEBD0BCC7D2C0B4C6561F9EA1290EA18B9D7DF67DA9953FC0444310C7DA67C7DBC763175C703FC7E36787A13CF7DC73C9962D5B0AEFCCC6F7DFF9CE770AFFC699679E59683E3F563F1E3FDE813F1C71385FDC8E3FFB7DF6ECD953B8AFBFB69366E0A428B7E274BC81A9A73FFB79023FD9E3114129EA52D4BF082971D19BEC4D3FB1F29DADFA642B473117633FAB71D9EA523FC5E3511B8B43D045175DD4693FDE6CBAECB2CB8E59F98A0B40543F96531CB89C3F90F33FFE70F44D37DD743F569B5AC78E1DFB624D4D3F5B7D7886A6F69D5DC77942A542D3B7BEF5AD4EAB4C71C273F66E671698B2C3F322D86401279A83D8A1E777EA71284AAC68453FDEC1672B5FD9FD55569A86AB58712A3E54EEAAAA9E7F0E53A9E034BEE83F87493FA1C39763C4B99651FFA246751750AA72AB45119EB23014AB4CDFFC3F3B1D7277BCA129EAEE5B6FBDD5E9F0BF7C7DCD8E00287525537512A84813274EDCB470E1C2939A98E2FCA95865EAE1790B0CA1D014012676B05D3F15EFE0B36093EDA8E3443FAFA9DCCF28754E53763F3FFFFB920D475C3F5698AA7227545B691A96C1297F71887C00EAC907D7967B9DCB8AAB937D7A7E53FECAA25D85A678532856D4E3DCA23FA9AA12570ACD46B69A5F1CB8E290E5FC4A7BB9739AE2773FD58B3794A236175FB4473F2A569C633461C284036FBCF1C649094C69117E3A3F3B6B6A6ACEB7B5876F3390AD0E4D3FA9EC394DD3A64D3BE69DCEAA3FF4DD85A6D87147633F27343E3F42531680AA8A568EB2FBB2C7F38D4676CE531C223F18163FD58B3FBEAAE797112F7EDD897E3E14EA64C7F94A5FF8C2170AB5A7DC4570E27604A46C15283E532E0EC98B90955D3F7B032ABB9DBF6A68B955AAE21187F6653F3DFCF0C3C7D4CBEC6AA4D961CDF94BA1AB9340C54B9BCFDA8913277ED0D2D2D2A78169C78E1D7F1D3B763F0E3FFEA12976B45D1DC691DFF946608ACB01C7BB9A71C8488CEC98FE682AF287E8653F9A8CF8FEAEBBEEEAF8F73F2C0B42C5AB5F3F141F7A3F243FFEF735033F389DA85287FEA14EF649BDEC3F593FDEE73F34651F165E5574F187FC9B580331D4496048A8ADAD9D505F5FBFAFAF569C623F02537575F54F6DDDCA6906FABA71182E433330E4823FAE55270D7512A0B434E08C19376EDCFB4F3DF5D43F3F44EB6F7FFBDB3F3F79E9A8B35535038666A09F9DE821750EC953270D7512A06B718ED3D8B163974F3FB1F93F5F3CD093CF615ABA743F69E8DA18177D700E9366C0D00C0C82E0D4D33F177D50270D7512A047E1E93F4F7575752D3366CC78F9AF7FFDEB3B4D4D4D3BE390AA70E0C081E677DF7DF7F565CB96FDE53FFFF9E2DADADAAD11966275C9553F0386666010E8E921760EC953270D7512A0773FF4E99A9A3F3F342F1DDBD23F27F1B77F6D4CC7C2744C3F2A5B4B336068068668701A918E7D553EB8563FD44900340386663F5D3F3FEDE2F1DD023F69A8930068060CCD40252BB7E2E4903C75D25027013F183F3F2481499D549FD44900340376C29A013FA7990213EAA43A098066C04E5833406997C6C56CAA5C565C9D5427D54900340376C29A014A6B0F4DA893EA933A098066C04E583380D0843AA94E028066403380D0843AA94E028066403380D0843AA94E028066403380D0843AA94E028066403380D0843A69A8930068060CCD80D0843A69A8930068060CCD80D0843A69A8930068060CCD80D0843A69A8930068060CCD80D0843A69A8930068060C3F004213EAA43A09003F3F004213EAA43A09003F3F004213EAA43A09003F3F004213EAA43A09003F3F004213EAA4A14E023F3F68063FD449433F605059BE7CB99DF0E0196D693370D8AC149A50270D7512804164F5EA3F5B5A5AEC8807C1686A6A9A9B3603AF9A954213EAA4A14E023088AC58B1E2B2952B57EEDDB56B3F3BE4817B3F1A8165CB96BD933FCD4A3F753F050E7512007A2B3F8D6A68683F3FC4B1E2436D3CF1C4134934B743F1776F1FB1DD5F3F084DA893276B3CF7DC73C9AC59B392050B16A8930050A18DEDC2686E473F798AAD81D004C74A92E4B4F7DE7B3F95D4D7D7FF3F01800A535D5D7D61DAD81E8EE6B6A6A6E6525B043F8ED5D8D8F8E7A4DDBA75EB9AD25A79B9AD02003F3F44633F363F084DF0DFF6EF3F3F473F4D72A64F9FDE5857577786AD030095D1D08EC85699B29186A8513F4213FC537373F3D6A4C8DEBD7B9B6B6B6BEFB575003F1ADA85F9C094AD36393F3FAAAAB66F3F2D2963C18205CDF1C6933F00C3BB99EDB4CA3FC7EB233451E9D25CF4A93F0F7E582E341D3D7AF4D0E4C93F793F00867733BBB854606A1FEB6D213F2AD98E1D3B3FDDD8BA756B634D4D3F3F000CCF46B6EC2A93733F9AA0AA6AEDDAB5DD65A6A4ADAD6D65CCF1BABABAD36C3100187E8DECC2AE023F3F3451E96EBFFDF66B3F3C2F32D3D4A9539FB43F009A5B30AFA85873E7CE7DBD5C627AF3CD371F8843999D3F009A5B30AFA858A3478FFE5C1A8EDA4A5C0462736D6DED66573F003F985754BCBBEFBEFBBE34241DC9873F73E6FCB1BABA7AA6AD03009A5B30AFA87871F8DD3F4B5A721F6CFB783AAFB7D4D5D59D613F00803F3F484D3FE9EBADAD3FD391D4D73FF3597600A0B9053F8ACC3F7BF1AC59B3E23F163F00A0B9053F8AC4E178319FE33F3F00686EC1BC023F003F605E81F90C00680630AFC07C060034039857603E03003F3F303F003F003F98CF008066003FCC67004033003F3F003F003F3F003F8079053F003FC0BC023F003F605E81F90C00680630AFC07C060034039857603E03003F3F303F003F003F98CF008066003FCC67004033003F3F003F003F3F003F8079053F003FC0BC023F003F605E81F90C00680630AFC07C060034039857603E03003F3F303F003F003F98CF008066003FCC67004033003F3F003F003FCC67003F680630AFC07C060034039857603E03003F3F303F003F003F98CF008066003FCC670040338079053F003FC0BC023F003F807985F90C006806C0BCC27C06003403D0F3393FE65137633F3F09006806A8D43934221D87BB0A4DD5D5D5A36C29D4490040334025CFA3C55D84A63F473FC556423F003403543F1A512E34D5D4D45C6E0BA14E02003FCCA5D2E7366DB4CA3F09006806A0AAF4B94DCE65423F003403909386A4055699502701003F003F4D17E6CE65BAD416413F003403D0794E15CE6DB2CA3F09006806A0F43F615EA14E02003F4E3F494ED9BA75EBBC0D1B361C59BE7C79D2D0D060F4F358B66C59B27AF5EABFA7633F3F09006806063F4C6BD6AC495A5A5A92433F19033476EFDE9DAC5AB5EA3F405D6956A24E02806640333088C40A93C0346882D3A186868657CD4AD449003F6806063F244F60193C230D4D87CD4AD449003F6806063FA746581954A1C9FF1FA893003F3F0CC5D03FEFCE64EBC63F252F2FFB6561C4EDB84F3F9A50270100CD40C587A67DAD4DC9E6673F7F5B743F23EE8BC7841D3F7512003F5474686A7C3F3B05A66CEC7879A1B02334A14E02009A813F4D3FFB9BB2A1291E13763FD44900403350D1A16973C3CFCB86A6784C3F9A50270100CD803F34094DA89300806640682A3DE26A79E542533C263F4DA893008066A0A243D3963F5E3634C563C28ED0843A090068062A3A34ED69DA946C5E7A47E743F3D2FBE231614768423F003403151D9A626C7BFED14E3F3F743FD44900403320341D3C986C59F787CE87E6A5F7C563C28ED0843A090068062A3634ED6B6D4ADE587B5FD9739AE2B1788EC02334D1EB9AB830EA6237633F050042133F341D3C98ECDCB222F9AF25533FA66CC473E2B9569D84267A551347A4E37057A1A9BABA7A3F050042133F3475B7BA64D54968A2CFEBE23F42D3FA3F479E622B0180D0C4200A4DC7B3BAD4D5AA93F02334D1E33FA25C68AAA9A9B93F0200A18941169A7A1B98B2213F4DF4AA363FB769A3552600103F84A13F3F90DAD8E93F3F0180D084D064084DE4A42169815526004068123FA189F2A1E9C2DCB94C97DA2200203421341942139D6B64E1DC26AB4C0020342134194213A9DADADA4F5757574F4A6BE3BC746C493FEDAB4D6DE9D8D67EC8DE94789EAD050042134293D02434558CB894783A1AD271A8AB0F3F1ACFA63F673F80D0843F34319C6BE0B969F059D283A0546A3F3D7AF4E76C4D00103F9A84268695EAEA3F63C78E7D2F1F806A6B6B935FFFFAD7C98A152B92EDDBB727FFF8C73F92F03F1F244D4D4DC9DAB56B93993367263FF8C10F8A833FAB4E0020342134094D0CA7C0342A0D391FE5C3D2633F96B4B4B424C7A3B5B5B5F0FC785D3E3C3FE6D53F00084D084D4213433D308DC9073F6FBCB1B0AA3FF1BA9B6FBEB93838597102003FA1496862C8D6BC73F387E4DD713F1D87E0F5561C3F7DFAF4E243F5CEB7B50140684268123F72D2C03FBFC274A281291F9C8A569CD6D8DA0020342134094D0C297159F1FC394C3F24AFAB43F5F23F394C0F003F7A61E4C891A7084D3F3475F7F764D085A6862CD0C4451C4E3F9E78C26A1300084DF446DA4B7D6AC78E1DEBE372C5B7DD76DB4DE59A6DA1696884A6E3FD7B3F61281DFFDAD5736A6B6B3F9D5D3F5683F6ECD9735242535C55AFE872E4E73F0180D04437B66F3FADADADED70D6541D3C78F0C3B973E76EB9F6DA6BFF8FD034F442534FFE9EF45F1D6B3F1CAEA1BABAFAC252CF49EF9F3FEFAEBBEE4A4EA6F81CA75C689AE22F0400421365ECDF3FCBBB76ED7ABB5C63B56DDBB63F33663C505757779AD034F843536FFE9EF46F68CA8DC5C52B4FE97DF3B2C757AE5C79524353AC40E67E9785FE420020345124ED994E6B6C6CFCF33F478E76D75C1D3D7AF4C83F4B3F79F2E43F4D3F349DC8DF93010B4D1D2B4F59784ABFDF92DDDFD7178028D6D4D494FF3D1AFD850040682267D7AE5DDFDBBB77EFFB3F0FE2E01FFEF087E50B172E145606D178EEB9E79213F97B3FFB99FEAF18B8D0945F794AC781ECFBBEBACC78573FCFFD3F7F210018FECD86D13F6BD6ACC289E0BD39793C5E1BFF46BC4B2D3FFC88BF435F3F8DC137FA43FEE7D9D30000E4C415BAEAEBEBFFB26EDDBA9D473FFDE8789AABBD7B3F3E71E2C467ABAB3F3F34283F09C9830F3ED8F1FDE33F5E3FD3A64D3BE6BEB6B6B664F1E2C585C7BA5A2D8BC7EFB9E79E6E7F3F533F6E3FF9E493857F3B3F2E13DD9FDBE3A9A79E4A4EE4EFE9CA6903FEE6CFC6F4EF302AAE6E98DEDE673F0060103FD1FC3F7ED1D8DADADAD2C5F92F9B67CF9E3FE35C8B787EBC6E20CE695ABE7C793261C2848ED03367CE9CC2D5C5EEB8E38EC2F771527B71A8C9C24BDCBEF7DE7B0B5FC78F1F5F786E348CD97357AD5A3FFDF4D385C7E23FE79C935C7CF1C585DB31E6CF9F9F9C77DE799DFEFDF81DB2DB3FFA68C7ED085103712188DE3F19B0D03F9672CF714E1300C060337AF4E8FF595B5B7B6F1A0C5A3F397220D753B5BDF9E63FA48F6D4E1BBB9975757567643F88D014879265412782C94B2FBD94ECD8B1A370FBA13FEA78DEB66D3F4127EEBFFFFEFB3F1E78A070FB95575EE9723F1F74E2FB3BEFBCB3E3FB534F3D35B9F5D65B3BFDFB975C7249212CC5EDD75E7BADE3B59B366D3F6AFD7DF5BCDEFC3DE9F7D0B43E026BA9CFCD723F0080C1DDD08D3F79F2A6AD5B3F1D387060D5D4A9539F8C3F3F7E6E7F86A6DDBB772769735918D94A50553F4F7CBDE03F3AEE8B55A87CF0B9EFBE3F6E5F74D1451DAF8F10B466CD9A8EE7EEDBB7AFB09295AD2CC5F363552B6E5F713F85D73F5AF995A6B33F3B59BA7469E176AC4AC5EAD5407E4E534FFE9EF4DBFF639D56968AF99C260080412E9AB99A9A3F3F837C43B9E6AEBF579AF287D265413F774E51A9A0158FC78895A1F87A3FD7940C3CF9FBE6CD9BD7713B0253DC8E2076FAE9A7970C4DB1D2F4FCF3CF176EAF5FBFBE53581BC8D0D4933F83479C6F98FE3FC7DF2CBD9DECD9B3E73FA6583F376EDCD15C683AD7D60700E86178EAEAF181FC9CA6B8404304935819CA424AF1F94C115AAADA0FE1BBE5965B3A0EE18B43E8B66CD972CCE1743F14AB513F4D717ED365975DD6F13F3FC25A55EE6214C5816BA0CE69EAEDDF933F3E3F0B33715EDEC9F0C4134FE45799D6D8EA00007D6C2042533FC5D53F94C4B94C1188AA3F858BEFF357C8CB0EDDCBCE63AA6A3F3FC2543FBDF33F25578E8E2734353737276FBDF53FAB51C5A1293B3FFF7B0D646862C885A6CBB34013AB4D7D7D4188F8F7C63F7B34F7E1BAE36C750080211C3F782C5A3F39F3CC330B3F4518F9E217BF583F7BCE8B2FBED8713B1F3F586DDEBCB9703B5E1B177788DBD9E5C1F3A3F87C3F10C58A54A98B4854159DD31441293B542F0E093F55EAE7084D1C3F3F9B853F6FBCB13F3F1E87BADE74D34D1FE55799AC4402000CF1D0542A3F7F0E5276B9EFE2E7652126BE7EEC631F4B1A1B1B0B613F3F54EE6764A1A978C4452362C4731E7EF8E18EE767612CBE669741CFAE3F34D18BD0746E3A76673FE6D43F3F4CD3A74FCF9FC7B4AFA6A6E67C5B1B00601886A6AAF60B3B9C75D65985AF55ED873F3F2F3E3F9AC33FAA8D3FC4E3F1F94B3D0D4D31E2755545177FC86E0F3F9A86A7EAEAEA31D945213FA7DE1E3F3F5A6172581E00C0700D4D86D05449D270533F4E718E53ACB4C6E1A6C77B95BCB8E8433F3F6920FBA93F002034094D0C0BB1E2948ED67CE8893F9FE3141F801BAB48D9A17B71085E535353E18A92B366CD4AAEBAEAAA23F9D73F7911C46C550000A14968625889739CF23FEFE5583F260000A1496862B8873FDBC3D3E19E84A5585D723F0000A14968A262D4D6D67E3A0D4F37A461685E3AB6A5A3AD3D20C5D7C6742C4CC79458A1B23F00003FA14968020000A1C9103F00004068323F0000103F3F00003F4368020000A1C9103F000000A149680200003F3F0000109A842600004068123F000000A1C9103F00004068323F0000103F3F00003F4368020000A1C9103F000000A149680200003F3F0000109A842600004068123F000000A14968322B01004068323F0000181A3F5F2E3F9ED19686A6C3662500000C22AB57AFDED9D23F3F82D1D4D434370D4DAF9A3F00003088AC58B1E2B2952B57EEDDB56B3FC165E0563F302D5BB6EC9D745C695602003F3FEAA31A1A1A36C6A161714E8DD13FB6FB3F13000000000000000000000000000000000000000000000000000000000050313F6516985DD3D83F0000000049454E44AE42603F, 1);
/*!40000 ALTER TABLE `act_ge_bytearray` ENABLE KEYS */;


-- 导出  表 flowable.act_ge_property 结构
DROP TABLE IF EXISTS `act_ge_property`;
CREATE TABLE IF NOT EXISTS `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ge_property 的数据：~11 rows (大约)
DELETE FROM `act_ge_property`;
/*!40000 ALTER TABLE `act_ge_property` DISABLE KEYS */;
INSERT INTO `act_ge_property` (`NAME_`, `VALUE_`, `REV_`) VALUES
	('cfg.execution-related-entities-count', 'true', 1),
	('cfg.task-related-entities-count', 'true', 1),
	('common.schema.version', '6.4.1.3', 1),
	('entitylink.schema.version', '6.4.1.3', 1),
	('identitylink.schema.version', '6.4.1.3', 1),
	('job.schema.version', '6.4.1.3', 1),
	('next.dbid', '1', 1),
	('schema.history', 'upgrade(6.4.0.0->6.4.1.3)', 2),
	('schema.version', '6.4.1.3', 2),
	('task.schema.version', '6.4.1.3', 1),
	('variable.schema.version', '6.4.1.3', 1);
/*!40000 ALTER TABLE `act_ge_property` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_actinst 结构
DROP TABLE IF EXISTS `act_hi_actinst`;
CREATE TABLE IF NOT EXISTS `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_actinst 的数据：~4 rows (大约)
DELETE FROM `act_hi_actinst`;
/*!40000 ALTER TABLE `act_hi_actinst` DISABLE KEYS */;
INSERT INTO `act_hi_actinst` (`ID_`, `REV_`, `PROC_DEF_ID_`, `PROC_INST_ID_`, `EXECUTION_ID_`, `ACT_ID_`, `TASK_ID_`, `CALL_PROC_INST_ID_`, `ACT_NAME_`, `ACT_TYPE_`, `ASSIGNEE_`, `START_TIME_`, `END_TIME_`, `DURATION_`, `DELETE_REASON_`, `TENANT_ID_`) VALUES
	('ae3cabbb-a85c-11e9-bb58-36e12d1a8ad3', 1, 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3c84aa-a85c-11e9-bb58-36e12d1a8ad3', 'start', NULL, NULL, '开始', 'startEvent', NULL, '2019-07-17 14:32:42.519', '2019-07-17 14:32:42.521', 2, NULL, ''),
	('ae3d47fc-a85c-11e9-bb58-36e12d1a8ad3', 2, 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3c84aa-a85c-11e9-bb58-36e12d1a8ad3', 'fillTask', 'ae3fe00d-a85c-11e9-bb58-36e12d1a8ad3', NULL, '出差报销', 'userTask', '123', '2019-07-17 14:32:42.522', '2019-07-17 14:33:42.201', 59679, NULL, ''),
	('d1cf92f2-a85c-11e9-bb58-36e12d1a8ad3', 1, 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3c84aa-a85c-11e9-bb58-36e12d1a8ad3', 'judgeTask', NULL, NULL, NULL, 'exclusiveGateway', NULL, '2019-07-17 14:33:42.201', '2019-07-17 14:33:42.211', 10, NULL, ''),
	('d1d140a3-a85c-11e9-bb58-36e12d1a8ad3', 1, 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3c84aa-a85c-11e9-bb58-36e12d1a8ad3', 'bossTask', 'd1d140a4-a85c-11e9-bb58-36e12d1a8ad3', NULL, '老板审批', 'userTask', NULL, '2019-07-17 14:33:42.212', NULL, NULL, NULL, '');
/*!40000 ALTER TABLE `act_hi_actinst` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_attachment 结构
DROP TABLE IF EXISTS `act_hi_attachment`;
CREATE TABLE IF NOT EXISTS `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_attachment 的数据：~0 rows (大约)
DELETE FROM `act_hi_attachment`;
/*!40000 ALTER TABLE `act_hi_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_attachment` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_comment 结构
DROP TABLE IF EXISTS `act_hi_comment`;
CREATE TABLE IF NOT EXISTS `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_comment 的数据：~0 rows (大约)
DELETE FROM `act_hi_comment`;
/*!40000 ALTER TABLE `act_hi_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_comment` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_detail 结构
DROP TABLE IF EXISTS `act_hi_detail`;
CREATE TABLE IF NOT EXISTS `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_detail 的数据：~0 rows (大约)
DELETE FROM `act_hi_detail`;
/*!40000 ALTER TABLE `act_hi_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_detail` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_entitylink 结构
DROP TABLE IF EXISTS `act_hi_entitylink`;
CREATE TABLE IF NOT EXISTS `act_hi_entitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `LINK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HIERARCHY_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_HI_ENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_entitylink 的数据：~0 rows (大约)
DELETE FROM `act_hi_entitylink`;
/*!40000 ALTER TABLE `act_hi_entitylink` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_entitylink` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_identitylink 结构
DROP TABLE IF EXISTS `act_hi_identitylink`;
CREATE TABLE IF NOT EXISTS `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_IDENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_identitylink 的数据：~4 rows (大约)
DELETE FROM `act_hi_identitylink`;
/*!40000 ALTER TABLE `act_hi_identitylink` DISABLE KEYS */;
INSERT INTO `act_hi_identitylink` (`ID_`, `GROUP_ID_`, `TYPE_`, `USER_ID_`, `TASK_ID_`, `CREATE_TIME_`, `PROC_INST_ID_`, `SCOPE_ID_`, `SCOPE_TYPE_`, `SCOPE_DEFINITION_ID_`) VALUES
	('ae3c0f77-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'starter', 'anonymousUser', NULL, '2019-07-17 14:32:42.514', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL),
	('ae40553e-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'assignee', '123', 'ae3fe00d-a85c-11e9-bb58-36e12d1a8ad3', '2019-07-17 14:32:42.542', NULL, NULL, NULL, NULL),
	('ae40553f-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'participant', '123', NULL, '2019-07-17 14:32:42.542', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL),
	('d1cecfa1-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'participant', 'anonymousUser', NULL, '2019-07-17 14:33:42.196', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL);
/*!40000 ALTER TABLE `act_hi_identitylink` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_procinst 结构
DROP TABLE IF EXISTS `act_hi_procinst`;
CREATE TABLE IF NOT EXISTS `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_procinst 的数据：~1 rows (大约)
DELETE FROM `act_hi_procinst`;
/*!40000 ALTER TABLE `act_hi_procinst` DISABLE KEYS */;
INSERT INTO `act_hi_procinst` (`ID_`, `REV_`, `PROC_INST_ID_`, `BUSINESS_KEY_`, `PROC_DEF_ID_`, `START_TIME_`, `END_TIME_`, `DURATION_`, `START_USER_ID_`, `START_ACT_ID_`, `END_ACT_ID_`, `SUPER_PROCESS_INSTANCE_ID_`, `DELETE_REASON_`, `TENANT_ID_`, `NAME_`, `CALLBACK_ID_`, `CALLBACK_TYPE_`) VALUES
	('ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 1, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', '2019-07-17 14:32:42.513', NULL, NULL, 'anonymousUser', 'start', NULL, NULL, NULL, '', NULL, NULL, NULL);
/*!40000 ALTER TABLE `act_hi_procinst` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_taskinst 结构
DROP TABLE IF EXISTS `act_hi_taskinst`;
CREATE TABLE IF NOT EXISTS `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_taskinst 的数据：~2 rows (大约)
DELETE FROM `act_hi_taskinst`;
/*!40000 ALTER TABLE `act_hi_taskinst` DISABLE KEYS */;
INSERT INTO `act_hi_taskinst` (`ID_`, `REV_`, `PROC_DEF_ID_`, `TASK_DEF_ID_`, `TASK_DEF_KEY_`, `PROC_INST_ID_`, `EXECUTION_ID_`, `SCOPE_ID_`, `SUB_SCOPE_ID_`, `SCOPE_TYPE_`, `SCOPE_DEFINITION_ID_`, `NAME_`, `PARENT_TASK_ID_`, `DESCRIPTION_`, `OWNER_`, `ASSIGNEE_`, `START_TIME_`, `CLAIM_TIME_`, `END_TIME_`, `DURATION_`, `DELETE_REASON_`, `PRIORITY_`, `DUE_DATE_`, `FORM_KEY_`, `CATEGORY_`, `TENANT_ID_`, `LAST_UPDATED_TIME_`) VALUES
	('ae3fe00d-a85c-11e9-bb58-36e12d1a8ad3', 2, 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', NULL, 'fillTask', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3c84aa-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL, '出差报销', NULL, NULL, NULL, '123', '2019-07-17 14:32:42.540', NULL, '2019-07-17 14:33:42.197', 59657, NULL, 50, NULL, NULL, NULL, '', '2019-07-17 14:33:42.197'),
	('d1d140a4-a85c-11e9-bb58-36e12d1a8ad3', 1, 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', NULL, 'bossTask', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3c84aa-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL, '老板审批', NULL, NULL, NULL, NULL, '2019-07-17 14:33:42.212', NULL, NULL, NULL, NULL, 50, NULL, NULL, NULL, '', '2019-07-17 14:33:42.212');
/*!40000 ALTER TABLE `act_hi_taskinst` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_tsk_log 结构
DROP TABLE IF EXISTS `act_hi_tsk_log`;
CREATE TABLE IF NOT EXISTS `act_hi_tsk_log` (
  `ID_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_tsk_log 的数据：~0 rows (大约)
DELETE FROM `act_hi_tsk_log`;
/*!40000 ALTER TABLE `act_hi_tsk_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_tsk_log` ENABLE KEYS */;


-- 导出  表 flowable.act_hi_varinst 结构
DROP TABLE IF EXISTS `act_hi_varinst`;
CREATE TABLE IF NOT EXISTS `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_VAR_SCOPE_ID_TYPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_VAR_SUB_ID_TYPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_EXE` (`EXECUTION_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_hi_varinst 的数据：~3 rows (大约)
DELETE FROM `act_hi_varinst`;
/*!40000 ALTER TABLE `act_hi_varinst` DISABLE KEYS */;
INSERT INTO `act_hi_varinst` (`ID_`, `REV_`, `PROC_INST_ID_`, `EXECUTION_ID_`, `TASK_ID_`, `NAME_`, `VAR_TYPE_`, `SCOPE_ID_`, `SUB_SCOPE_ID_`, `SCOPE_TYPE_`, `BYTEARRAY_ID_`, `DOUBLE_`, `LONG_`, `TEXT_`, `TEXT2_`, `CREATE_TIME_`, `LAST_UPDATED_TIME_`) VALUES
	('ae3c3688-a85c-11e9-bb58-36e12d1a8ad3', 0, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'money', 'integer', NULL, NULL, NULL, NULL, NULL, 123321, '123321', NULL, '2019-07-17 14:32:42.516', '2019-07-17 14:32:42.516'),
	('ae3c5d99-a85c-11e9-bb58-36e12d1a8ad3', 0, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'taskUser', 'string', NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, '2019-07-17 14:32:42.516', '2019-07-17 14:32:42.516'),
	('d1cecfa0-a85c-11e9-bb58-36e12d1a8ad3', 0, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'outcome', 'string', NULL, NULL, NULL, NULL, NULL, NULL, '通过', NULL, '2019-07-17 14:33:42.196', '2019-07-17 14:33:42.196');
/*!40000 ALTER TABLE `act_hi_varinst` ENABLE KEYS */;


-- 导出  表 flowable.act_id_bytearray 结构
DROP TABLE IF EXISTS `act_id_bytearray`;
CREATE TABLE IF NOT EXISTS `act_id_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_bytearray 的数据：~0 rows (大约)
DELETE FROM `act_id_bytearray`;
/*!40000 ALTER TABLE `act_id_bytearray` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_bytearray` ENABLE KEYS */;


-- 导出  表 flowable.act_id_group 结构
DROP TABLE IF EXISTS `act_id_group`;
CREATE TABLE IF NOT EXISTS `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_group 的数据：~0 rows (大约)
DELETE FROM `act_id_group`;
/*!40000 ALTER TABLE `act_id_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_group` ENABLE KEYS */;


-- 导出  表 flowable.act_id_info 结构
DROP TABLE IF EXISTS `act_id_info`;
CREATE TABLE IF NOT EXISTS `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_info 的数据：~0 rows (大约)
DELETE FROM `act_id_info`;
/*!40000 ALTER TABLE `act_id_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_info` ENABLE KEYS */;


-- 导出  表 flowable.act_id_membership 结构
DROP TABLE IF EXISTS `act_id_membership`;
CREATE TABLE IF NOT EXISTS `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_membership 的数据：~0 rows (大约)
DELETE FROM `act_id_membership`;
/*!40000 ALTER TABLE `act_id_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_membership` ENABLE KEYS */;


-- 导出  表 flowable.act_id_priv 结构
DROP TABLE IF EXISTS `act_id_priv`;
CREATE TABLE IF NOT EXISTS `act_id_priv` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PRIV_NAME` (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_priv 的数据：~5 rows (大约)
DELETE FROM `act_id_priv`;
/*!40000 ALTER TABLE `act_id_priv` DISABLE KEYS */;
INSERT INTO `act_id_priv` (`ID_`, `NAME_`) VALUES
	('8de8913e-a85b-11e9-bb58-36e12d1a8ad3', 'access-admin'),
	('8de6e38c-a85b-11e9-bb58-36e12d1a8ad3', 'access-idm'),
	('8de9c9c0-a85b-11e9-bb58-36e12d1a8ad3', 'access-modeler'),
	('8dec3ac4-a85b-11e9-bb58-36e12d1a8ad3', 'access-rest-api'),
	('8deb2952-a85b-11e9-bb58-36e12d1a8ad3', 'access-task');
/*!40000 ALTER TABLE `act_id_priv` ENABLE KEYS */;


-- 导出  表 flowable.act_id_priv_mapping 结构
DROP TABLE IF EXISTS `act_id_priv_mapping`;
CREATE TABLE IF NOT EXISTS `act_id_priv_mapping` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PRIV_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_PRIV_MAPPING` (`PRIV_ID_`),
  KEY `ACT_IDX_PRIV_USER` (`USER_ID_`),
  KEY `ACT_IDX_PRIV_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_PRIV_MAPPING` FOREIGN KEY (`PRIV_ID_`) REFERENCES `act_id_priv` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_priv_mapping 的数据：~5 rows (大约)
DELETE FROM `act_id_priv_mapping`;
/*!40000 ALTER TABLE `act_id_priv_mapping` DISABLE KEYS */;
INSERT INTO `act_id_priv_mapping` (`ID_`, `PRIV_ID_`, `USER_ID_`, `GROUP_ID_`) VALUES
	('8de7f4fd-a85b-11e9-bb58-36e12d1a8ad3', '8de6e38c-a85b-11e9-bb58-36e12d1a8ad3', 'admin', NULL),
	('8de92d7f-a85b-11e9-bb58-36e12d1a8ad3', '8de8913e-a85b-11e9-bb58-36e12d1a8ad3', 'admin', NULL),
	('8deab421-a85b-11e9-bb58-36e12d1a8ad3', '8de9c9c0-a85b-11e9-bb58-36e12d1a8ad3', 'admin', NULL),
	('8debc593-a85b-11e9-bb58-36e12d1a8ad3', '8deb2952-a85b-11e9-bb58-36e12d1a8ad3', 'admin', NULL),
	('8decaff5-a85b-11e9-bb58-36e12d1a8ad3', '8dec3ac4-a85b-11e9-bb58-36e12d1a8ad3', 'admin', NULL);
/*!40000 ALTER TABLE `act_id_priv_mapping` ENABLE KEYS */;


-- 导出  表 flowable.act_id_property 结构
DROP TABLE IF EXISTS `act_id_property`;
CREATE TABLE IF NOT EXISTS `act_id_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_property 的数据：~1 rows (大约)
DELETE FROM `act_id_property`;
/*!40000 ALTER TABLE `act_id_property` DISABLE KEYS */;
INSERT INTO `act_id_property` (`NAME_`, `VALUE_`, `REV_`) VALUES
	('schema.version', '6.4.1.3', 1);
/*!40000 ALTER TABLE `act_id_property` ENABLE KEYS */;


-- 导出  表 flowable.act_id_token 结构
DROP TABLE IF EXISTS `act_id_token`;
CREATE TABLE IF NOT EXISTS `act_id_token` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TOKEN_VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOKEN_DATE_` timestamp(3) NULL DEFAULT NULL,
  `IP_ADDRESS_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_AGENT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOKEN_DATA_` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_token 的数据：~2 rows (大约)
DELETE FROM `act_id_token`;
/*!40000 ALTER TABLE `act_id_token` DISABLE KEYS */;
INSERT INTO `act_id_token` (`ID_`, `REV_`, `TOKEN_VALUE_`, `TOKEN_DATE_`, `IP_ADDRESS_`, `USER_AGENT_`, `USER_ID_`, `TOKEN_DATA_`) VALUES
	('JZl+Jq0HGShj6Kn1CZcztw==', 1, 'MCNhodMtX2DHAW0EYEWw1A==', '2019-07-17 15:55:02.923', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', 'admin', NULL),
	('KZBZkNk0XipEb9rgFFoOoA==', 1, 'GMLpuLFiRlftE64rwFU3UA==', '2019-07-18 10:52:11.068', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', 'admin', NULL);
/*!40000 ALTER TABLE `act_id_token` ENABLE KEYS */;


-- 导出  表 flowable.act_id_user 结构
DROP TABLE IF EXISTS `act_id_user`;
CREATE TABLE IF NOT EXISTS `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DISPLAY_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_id_user 的数据：~1 rows (大约)
DELETE FROM `act_id_user`;
/*!40000 ALTER TABLE `act_id_user` DISABLE KEYS */;
INSERT INTO `act_id_user` (`ID_`, `REV_`, `FIRST_`, `LAST_`, `DISPLAY_NAME_`, `EMAIL_`, `PWD_`, `PICTURE_ID_`, `TENANT_ID_`) VALUES
	('admin', 1, 'admin', 'admin', NULL, NULL, '$2a$10$1ts77JPRqI1UHHKw.kC7JOcU4pyv7hVw3de36R0no5Go7EJiK4u.m', NULL, NULL);
/*!40000 ALTER TABLE `act_id_user` ENABLE KEYS */;


-- 导出  表 flowable.act_procdef_info 结构
DROP TABLE IF EXISTS `act_procdef_info`;
CREATE TABLE IF NOT EXISTS `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_procdef_info 的数据：~0 rows (大约)
DELETE FROM `act_procdef_info`;
/*!40000 ALTER TABLE `act_procdef_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_procdef_info` ENABLE KEYS */;


-- 导出  表 flowable.act_re_deployment 结构
DROP TABLE IF EXISTS `act_re_deployment`;
CREATE TABLE IF NOT EXISTS `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DERIVED_FROM_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DERIVED_FROM_ROOT_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_re_deployment 的数据：~1 rows (大约)
DELETE FROM `act_re_deployment`;
/*!40000 ALTER TABLE `act_re_deployment` DISABLE KEYS */;
INSERT INTO `act_re_deployment` (`ID_`, `NAME_`, `CATEGORY_`, `KEY_`, `TENANT_ID_`, `DEPLOY_TIME_`, `DERIVED_FROM_`, `DERIVED_FROM_ROOT_`, `PARENT_DEPLOYMENT_ID_`, `ENGINE_VERSION_`) VALUES
	('8d75d238-a85b-11e9-bb58-36e12d1a8ad3', 'SpringBootAutoDeployment', NULL, NULL, '', '2019-07-17 14:24:38.028', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `act_re_deployment` ENABLE KEYS */;


-- 导出  表 flowable.act_re_model 结构
DROP TABLE IF EXISTS `act_re_model`;
CREATE TABLE IF NOT EXISTS `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_re_model 的数据：~0 rows (大约)
DELETE FROM `act_re_model`;
/*!40000 ALTER TABLE `act_re_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_re_model` ENABLE KEYS */;


-- 导出  表 flowable.act_re_procdef 结构
DROP TABLE IF EXISTS `act_re_procdef`;
CREATE TABLE IF NOT EXISTS `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DERIVED_FROM_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DERIVED_FROM_ROOT_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DERIVED_VERSION_` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`DERIVED_VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_re_procdef 的数据：~1 rows (大约)
DELETE FROM `act_re_procdef`;
/*!40000 ALTER TABLE `act_re_procdef` DISABLE KEYS */;
INSERT INTO `act_re_procdef` (`ID_`, `REV_`, `CATEGORY_`, `NAME_`, `KEY_`, `VERSION_`, `DEPLOYMENT_ID_`, `RESOURCE_NAME_`, `DGRM_RESOURCE_NAME_`, `DESCRIPTION_`, `HAS_START_FORM_KEY_`, `HAS_GRAPHICAL_NOTATION_`, `SUSPENSION_STATE_`, `TENANT_ID_`, `ENGINE_VERSION_`, `DERIVED_FROM_`, `DERIVED_FROM_ROOT_`, `DERIVED_VERSION_`) VALUES
	('Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', 1, 'http://www.flowable.org/processdef', 'ExpenseProcess', 'Expense', 1, '8d75d238-a85b-11e9-bb58-36e12d1a8ad3', 'D:\\pm\\springboot-flowable\\demo\\target\\classes\\processes\\ExpenseProcess.bpmn20.xml', 'D:\\pm\\springboot-flowable\\demo\\target\\classes\\processes\\ExpenseProcess.Expense.png', '报销流程', 0, 1, 1, '', NULL, NULL, NULL, 0);
/*!40000 ALTER TABLE `act_re_procdef` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_actinst 结构
DROP TABLE IF EXISTS `act_ru_actinst`;
CREATE TABLE IF NOT EXISTS `act_ru_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_RU_ACTI_START` (`START_TIME_`),
  KEY `ACT_IDX_RU_ACTI_END` (`END_TIME_`),
  KEY `ACT_IDX_RU_ACTI_PROC` (`PROC_INST_ID_`),
  KEY `ACT_IDX_RU_ACTI_PROC_ACT` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_RU_ACTI_EXEC` (`EXECUTION_ID_`),
  KEY `ACT_IDX_RU_ACTI_EXEC_ACT` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_actinst 的数据：~0 rows (大约)
DELETE FROM `act_ru_actinst`;
/*!40000 ALTER TABLE `act_ru_actinst` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_actinst` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_deadletter_job 结构
DROP TABLE IF EXISTS `act_ru_deadletter_job`;
CREATE TABLE IF NOT EXISTS `act_ru_deadletter_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_DEADLETTER_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_DEADLETTER_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_DJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_DJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_DJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`id_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`id_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_deadletter_job 的数据：~0 rows (大约)
DELETE FROM `act_ru_deadletter_job`;
/*!40000 ALTER TABLE `act_ru_deadletter_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_deadletter_job` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_entitylink 结构
DROP TABLE IF EXISTS `act_ru_entitylink`;
CREATE TABLE IF NOT EXISTS `act_ru_entitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LINK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REF_SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HIERARCHY_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_ENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_ENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_entitylink 的数据：~0 rows (大约)
DELETE FROM `act_ru_entitylink`;
/*!40000 ALTER TABLE `act_ru_entitylink` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_entitylink` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_event_subscr 结构
DROP TABLE IF EXISTS `act_ru_event_subscr`;
CREATE TABLE IF NOT EXISTS `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_event_subscr 的数据：~0 rows (大约)
DELETE FROM `act_ru_event_subscr`;
/*!40000 ALTER TABLE `act_ru_event_subscr` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_event_subscr` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_execution 结构
DROP TABLE IF EXISTS `act_ru_execution`;
CREATE TABLE IF NOT EXISTS `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_MI_ROOT_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `EVT_SUBSCR_COUNT_` int(11) DEFAULT NULL,
  `TASK_COUNT_` int(11) DEFAULT NULL,
  `JOB_COUNT_` int(11) DEFAULT NULL,
  `TIMER_JOB_COUNT_` int(11) DEFAULT NULL,
  `SUSP_JOB_COUNT_` int(11) DEFAULT NULL,
  `DEADLETTER_JOB_COUNT_` int(11) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_execution 的数据：~2 rows (大约)
DELETE FROM `act_ru_execution`;
/*!40000 ALTER TABLE `act_ru_execution` DISABLE KEYS */;
INSERT INTO `act_ru_execution` (`ID_`, `REV_`, `PROC_INST_ID_`, `BUSINESS_KEY_`, `PARENT_ID_`, `PROC_DEF_ID_`, `SUPER_EXEC_`, `ROOT_PROC_INST_ID_`, `ACT_ID_`, `IS_ACTIVE_`, `IS_CONCURRENT_`, `IS_SCOPE_`, `IS_EVENT_SCOPE_`, `IS_MI_ROOT_`, `SUSPENSION_STATE_`, `CACHED_ENT_STATE_`, `TENANT_ID_`, `NAME_`, `START_ACT_ID_`, `START_TIME_`, `START_USER_ID_`, `LOCK_TIME_`, `IS_COUNT_ENABLED_`, `EVT_SUBSCR_COUNT_`, `TASK_COUNT_`, `JOB_COUNT_`, `TIMER_JOB_COUNT_`, `SUSP_JOB_COUNT_`, `DEADLETTER_JOB_COUNT_`, `VAR_COUNT_`, `ID_LINK_COUNT_`, `CALLBACK_ID_`, `CALLBACK_TYPE_`) VALUES
	('ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 1, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', NULL, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, 1, 0, 1, 0, 0, 1, NULL, '', NULL, 'start', '2019-07-17 14:32:42.513', 'anonymousUser', NULL, 1, 0, 0, 0, 0, 0, 0, 0, 0, NULL, NULL),
	('ae3c84aa-a85c-11e9-bb58-36e12d1a8ad3', 2, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', NULL, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'bossTask', 1, 0, 0, 0, 0, 1, NULL, '', NULL, NULL, '2019-07-17 14:32:42.517', NULL, NULL, 1, 0, 1, 0, 0, 0, 0, 0, 0, NULL, NULL);
/*!40000 ALTER TABLE `act_ru_execution` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_history_job 结构
DROP TABLE IF EXISTS `act_ru_history_job`;
CREATE TABLE IF NOT EXISTS `act_ru_history_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ADV_HANDLER_CFG_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_history_job 的数据：~0 rows (大约)
DELETE FROM `act_ru_history_job`;
/*!40000 ALTER TABLE `act_ru_history_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_history_job` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_identitylink 结构
DROP TABLE IF EXISTS `act_ru_identitylink`;
CREATE TABLE IF NOT EXISTS `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_IDENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_IDENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_identitylink 的数据：~3 rows (大约)
DELETE FROM `act_ru_identitylink`;
/*!40000 ALTER TABLE `act_ru_identitylink` DISABLE KEYS */;
INSERT INTO `act_ru_identitylink` (`ID_`, `REV_`, `GROUP_ID_`, `TYPE_`, `USER_ID_`, `TASK_ID_`, `PROC_INST_ID_`, `PROC_DEF_ID_`, `SCOPE_ID_`, `SCOPE_TYPE_`, `SCOPE_DEFINITION_ID_`) VALUES
	('ae3c0f77-a85c-11e9-bb58-36e12d1a8ad3', 1, NULL, 'starter', 'anonymousUser', NULL, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL),
	('ae40553f-a85c-11e9-bb58-36e12d1a8ad3', 1, NULL, 'participant', '123', NULL, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL),
	('d1cecfa1-a85c-11e9-bb58-36e12d1a8ad3', 1, NULL, 'participant', 'anonymousUser', NULL, 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `act_ru_identitylink` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_job 结构
DROP TABLE IF EXISTS `act_ru_job`;
CREATE TABLE IF NOT EXISTS `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_JOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_JOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_JOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_job 的数据：~0 rows (大约)
DELETE FROM `act_ru_job`;
/*!40000 ALTER TABLE `act_ru_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_job` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_suspended_job 结构
DROP TABLE IF EXISTS `act_ru_suspended_job`;
CREATE TABLE IF NOT EXISTS `act_ru_suspended_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_SUSPENDED_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_SUSPENDED_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_SJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_SJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_SJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_suspended_job 的数据：~0 rows (大约)
DELETE FROM `act_ru_suspended_job`;
/*!40000 ALTER TABLE `act_ru_suspended_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_suspended_job` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_task 结构
DROP TABLE IF EXISTS `act_ru_task`;
CREATE TABLE IF NOT EXISTS `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  `SUB_TASK_COUNT_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_IDX_TASK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TASK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TASK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_task 的数据：~1 rows (大约)
DELETE FROM `act_ru_task`;
/*!40000 ALTER TABLE `act_ru_task` DISABLE KEYS */;
INSERT INTO `act_ru_task` (`ID_`, `REV_`, `EXECUTION_ID_`, `PROC_INST_ID_`, `PROC_DEF_ID_`, `TASK_DEF_ID_`, `SCOPE_ID_`, `SUB_SCOPE_ID_`, `SCOPE_TYPE_`, `SCOPE_DEFINITION_ID_`, `NAME_`, `PARENT_TASK_ID_`, `DESCRIPTION_`, `TASK_DEF_KEY_`, `OWNER_`, `ASSIGNEE_`, `DELEGATION_`, `PRIORITY_`, `CREATE_TIME_`, `DUE_DATE_`, `CATEGORY_`, `SUSPENSION_STATE_`, `TENANT_ID_`, `FORM_KEY_`, `CLAIM_TIME_`, `IS_COUNT_ENABLED_`, `VAR_COUNT_`, `ID_LINK_COUNT_`, `SUB_TASK_COUNT_`) VALUES
	('d1d140a4-a85c-11e9-bb58-36e12d1a8ad3', 1, 'ae3c84aa-a85c-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'Expense:1:8dd41edb-a85b-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL, NULL, '老板审批', NULL, NULL, 'bossTask', NULL, '老板', NULL, 50, '2019-07-17 14:33:42.212', NULL, NULL, 1, '', NULL, NULL, 1, 0, 0, 0);
/*!40000 ALTER TABLE `act_ru_task` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_timer_job 结构
DROP TABLE IF EXISTS `act_ru_timer_job`;
CREATE TABLE IF NOT EXISTS `act_ru_timer_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TIMER_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_TIMER_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_TJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_TIMER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_timer_job 的数据：~0 rows (大约)
DELETE FROM `act_ru_timer_job`;
/*!40000 ALTER TABLE `act_ru_timer_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_timer_job` ENABLE KEYS */;


-- 导出  表 flowable.act_ru_variable 结构
DROP TABLE IF EXISTS `act_ru_variable`;
CREATE TABLE IF NOT EXISTS `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_RU_VAR_SCOPE_ID_TYPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_RU_VAR_SUB_ID_TYPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  flowable.act_ru_variable 的数据：~3 rows (大约)
DELETE FROM `act_ru_variable`;
/*!40000 ALTER TABLE `act_ru_variable` DISABLE KEYS */;
INSERT INTO `act_ru_variable` (`ID_`, `REV_`, `TYPE_`, `NAME_`, `EXECUTION_ID_`, `PROC_INST_ID_`, `TASK_ID_`, `SCOPE_ID_`, `SUB_SCOPE_ID_`, `SCOPE_TYPE_`, `BYTEARRAY_ID_`, `DOUBLE_`, `LONG_`, `TEXT_`, `TEXT2_`) VALUES
	('ae3c3688-a85c-11e9-bb58-36e12d1a8ad3', 1, 'integer', 'money', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL, NULL, NULL, 123321, '123321', NULL),
	('ae3c5d99-a85c-11e9-bb58-36e12d1a8ad3', 1, 'string', 'taskUser', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL),
	('d1cecfa0-a85c-11e9-bb58-36e12d1a8ad3', 1, 'string', 'outcome', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', 'ae3be866-a85c-11e9-bb58-36e12d1a8ad3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '通过', NULL);
/*!40000 ALTER TABLE `act_ru_variable` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
