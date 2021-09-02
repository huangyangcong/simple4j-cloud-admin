package com.simple4j.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hyc
 * @date 2021-09-01 18:22:57
 */
@Data
@TableName("user_connection")
public class UserConnection implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 本地用户id
	 */
	@TableId
	private String userId;
	/**
	 * 第三方服务商
	 */
	private String providerId;
	/**
	 * 第三方用户id
	 */
	private String providerUserId;
	/**
	 * userId 绑定同一个 providerId 的排序
	 */
	private Integer rank;
	/**
	 * 第三方用户名
	 */
	private String displayName;
	/**
	 * 主页
	 */
	private String profileUrl;
	/**
	 * 头像
	 */
	private String imageUrl;
	/**
	 *
	 */
	private String accessToken;
	/**
	 * auth_token.id
	 */
	private Long tokenId;
	/**
	 *
	 */
	private String refreshToken;
	/**
	 * 过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1
	 */
	private Long expireTime;

}
