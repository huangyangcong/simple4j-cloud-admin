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
	private String userid;
	/**
	 * 第三方服务商
	 */
	private String providerid;
	/**
	 * 第三方用户id
	 */
	private String provideruserid;
	/**
	 * userId 绑定同一个 providerId 的排序
	 */
	private Integer rank;
	/**
	 * 第三方用户名
	 */
	private String displayname;
	/**
	 * 主页
	 */
	private String profileurl;
	/**
	 * 头像
	 */
	private String imageurl;
	/**
	 *
	 */
	private String accesstoken;
	/**
	 * auth_token.id
	 */
	private Long tokenid;
	/**
	 *
	 */
	private String refreshtoken;
	/**
	 * 过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1
	 */
	private Long expiretime;

}
