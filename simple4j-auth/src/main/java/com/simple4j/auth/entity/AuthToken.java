package com.simple4j.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xkcoding.http.config.HttpConfig;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

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
	private Integer enableRefresh;
	/**
	 * 第三方服务商,如: qq,github
	 */
	private String providerId;
	/**
	 * accessToken
	 */
	private String accessToken;
	/**
	 * 过期时间, 无过期时间默认为 -1
	 */
	private Long expireIn;
	/**
	 * refreshToken
	 */
	private String refreshToken;
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
	private String accessCode;
	/**
	 * QQ附带属性
	 */
	private String unionId;
	/**
	 * Google附带属性
	 */
	private String scope;
	/**
	 * Google附带属性
	 */
	private String tokenType;
	/**
	 * Google附带属性
	 */
	private String idToken;
	/**
	 * 小米附带属性
	 */
	private String macAlgorithm;
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
	private String oauthToken;
	/**
	 * Twitter附带属性
	 */
	private String oauthTokenSecret;
	/**
	 * Twitter附带属性
	 */
	private String userId;
	/**
	 * Twitter附带属性
	 */
	private String screenName;
	/**
	 * Twitter附带属性
	 */
	private String oauthCallbackConfirmed;
	/**
	 * 过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1
	 */
	private Long expireTime;


	public static AuthToken convert(me.zhyd.oauth.model.AuthToken token, String providerId) {
		AuthToken authToken = new AuthToken();
		BeanUtils.copyProperties(token, authToken);
		authToken.setProviderId(providerId);
		// 有效期转时间戳
		expireIn2Timestamp(timeout, token.getExpireIn(), authToken);
	}


	/**
	 * 有效期转时间戳
	 * @param timeout   {@link HttpConfig#getTimeout()}, 单位毫秒
	 * @param expireIn  有效期
	 * @param authToken {@link me.zhyd.oauth.model.AuthToken}
	 */
	static <T extends AuthToken> void expireIn2Timestamp(@NonNull Long timeout,
														   @Nullable Integer expireIn, @NonNull T authToken) {
		if (expireIn == null || expireIn < 1)
		{
			// 无过期时间, 默认设置为 -1
			authToken.setExpireTime(-1L);
		}
		else
		{
			// 转换为到期日期的 EpochMilli, 考虑到网络延迟, 相对于第三方的过期时间, 减去根据用户设置的 timeout(HttpConfigProperties.timeout) 时间,
			long dealLine = Instant.now().plusSeconds(expireIn).minusMillis(timeout).toEpochMilli();
			authToken.setExpireTime(dealLine);
		}
	}


}
