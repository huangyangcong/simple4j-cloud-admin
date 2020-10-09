package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实体类
 *
 * @author hycX
 * @since 2019-03-24
 */
@Data
@TableName("simple4j_client")
@EqualsAndHashCode(callSuper = true)
public class AuthClient extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 客户端id
	 */
	private String clientId;
	/**
	 * 客户端密钥
	 */
	private String clientSecret;
	/**
	 * 资源集合
	 */
	private String resourceIds;
	/**
	 * 授权范围
	 */
	private String scope;
	/**
	 * 授权类型
	 */
	private String authorizedGrantTypes;
	/**
	 * 回调地址
	 */
	private String webServerRedirectUri;
	/**
	 * 权限
	 */
	private String authorities;
	/**
	 * 令牌过期秒数
	 */
	private Integer accessTokenValidity;
	/**
	 * 刷新令牌过期秒数
	 */
	private Integer refreshTokenValidity;
	/**
	 * 附加说明
	 */
	private String additionalInformation;
	/**
	 * 自动授权
	 */
	private String autoapprove;


}
