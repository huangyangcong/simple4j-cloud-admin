CREATE TABLE `user_connection`
(
    `user_id`          varchar(255) NOT NULL,
    `provider_id`      varchar(255) NOT NULL,
    `provider_user_id` varchar(255) NOT NULL,
    `rank`             int(11) NOT NULL,
    `display_name`     varchar(255) DEFAULT NULL,
    `profile_url`      varchar(512) DEFAULT NULL,
    `image_url`        varchar(512) DEFAULT NULL,
    `access_token`     varchar(512) NOT NULL,
    `token_id`         varchar(512) DEFAULT NULL,
    `refresh_token`    varchar(512) DEFAULT NULL,
    `expire_time`      bigint(20) DEFAULT NULL,
    PRIMARY KEY (`user_id`, `provider_id`, `provider_user_id`) USING BTREE,
    UNIQUE KEY `idx_userId_providerId_rank` (`user_id`,`provider_id`,`rank`),
    KEY                `idx_providerId_providerUserId_rank` (`provider_id`,`provider_user_id`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
