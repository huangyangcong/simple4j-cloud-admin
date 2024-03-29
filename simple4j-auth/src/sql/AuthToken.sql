CREATE TABLE `auth_token`
(
    `enable_refresh`           int(1) DEFAULT NULL COMMENT '刷新token 1：支持 0：不支持',
    `id`                       bigint(20) NOT NULL AUTO_INCREMENT,
    `access_token`             varchar(255) DEFAULT NULL COMMENT 'accessToken',
    `expire_in`                bigint(20) DEFAULT NULL COMMENT '过期时间',
    `refresh_token`            varchar(255) DEFAULT NULL COMMENT 'refreshToken',
    `uid`                      varchar(11)  DEFAULT NULL COMMENT 'alipay userId',
    `open_id`                  varchar(255) DEFAULT NULL COMMENT 'qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu',
    `access_code`              varchar(512) DEFAULT NULL COMMENT 'dingTalk, taobao 附带属性',
    `union_id`                 varchar(512) DEFAULT NULL COMMENT 'QQ附带属性',
    `scope`                    varchar(512) DEFAULT NULL COMMENT 'Google附带属性',
    `token_type`               varchar(512) DEFAULT NULL COMMENT 'Google附带属性',
    `id_token`                 varchar(512) DEFAULT NULL COMMENT 'Google附带属性',
    `mac_algorithm`            varchar(512) DEFAULT NULL COMMENT '小米附带属性',
    `mac_key`                  varchar(512) DEFAULT NULL COMMENT '小米附带属性',
    `code`                     varchar(512) DEFAULT NULL COMMENT '企业微信附带属性',
    `oauth_token`              varchar(512) DEFAULT NULL COMMENT 'Twitter附带属性',
    `oauth_token_secret`       varchar(512) DEFAULT NULL COMMENT 'Twitter附带属性',
    `user_id`                  varchar(512) DEFAULT NULL COMMENT 'Twitter附带属性',
    `screen_name`              varchar(512) DEFAULT NULL COMMENT 'Twitter附带属性',
    `oauth_callback_confirmed` varchar(512) DEFAULT NULL COMMENT 'Twitter附带属性',
    `provider_id`              varchar(255) DEFAULT NULL,
    `expire_time`              mediumtext,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
