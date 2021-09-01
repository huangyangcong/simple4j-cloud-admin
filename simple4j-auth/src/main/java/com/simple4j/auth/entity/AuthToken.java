package com.simple4j.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hyc
 * @email
 * @date 2021-09-01 18:22:57
 */
@Data
@TableName("auth_token")
public class AuthToken implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * tokenId
	 */
	@TableId
	private Long id;
	/**
	 * 是否支持 refreshToken, 默认: 0. 1 表示支持, 0 表示不支持
	 */
	private Integer enablerefresh;
	/**
	 * 第三方服务商,如: qq,github
	 */
	private String providerid;
	/**
	 * accessToken
	 */
	private String accesstoken;
	/**
	 * 过期时间, 无过期时间默认为 -1
	 */
	private Long expirein;
	/**
	 * refreshToken
	 */
	private String refreshtoken;
	/**
	 * alipay userId
	 */
	private String uid;
	/**
	 * qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu
	 */
	private String openid;
	/**
	 * dingTalk, taobao 附带属性
	 */
	private String accesscode;
	/**
	 * QQ附带属性
	 */
	private String unionid;
	/**
	 * Google附带属性
	 */
	private String scope;
	/**
	 * Google附带属性
	 */
	private String tokentype;
	/**
	 * Google附带属性
	 */
	private String idtoken;
	/**
	 * 小米附带属性
	 */
	private String macalgorithm;
	/**
	 * 小米附带属性
	 */
	private String mackey;
	/**
	 * 企业微信附带属性
	 */
	private String code;
	/**
	 * Twitter附带属性
	 */
	private String oauthtoken;
	/**
	 * Twitter附带属性
	 */
	private String oauthtokensecret;
	/**
	 * Twitter附带属性
	 */
	private String userid;
	/**
	 * Twitter附带属性
	 */
	private String screenname;
	/**
	 * Twitter附带属性
	 */
	private String oauthcallbackconfirmed;
	/**
	 * 过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1
	 */
	private Long expiretime;

}
