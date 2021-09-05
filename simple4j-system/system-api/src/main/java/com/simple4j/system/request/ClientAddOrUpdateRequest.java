package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户端表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "客户端表新增请求实体类", description = "客户端表新增请求实体类")
public class ClientAddOrUpdateRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Parameter(name = "id", description = "主键")
	@JsonProperty("id")
	private String id;
	/**
	 * 客户端id
	 */
	@Parameter(name = "client_id", description = "客户端id")
	@JsonProperty("client_id")
	private String clientId;
	/**
	 * 客户端密钥
	 */
	@Parameter(name = "client_secret", description = "客户端密钥")
	@JsonProperty("client_secret")
	private String clientSecret;
	/**
	 * 资源集合
	 */
	@Parameter(name = "resource_ids", description = "资源集合")
	@JsonProperty("resource_ids")
	private String resourceIds;
	/**
	 * 授权范围
	 */
	@Parameter(name = "scope", description = "授权范围")
	@JsonProperty("scope")
	private String scope;
	/**
	 * 授权类型
	 */
	@Parameter(name = "authorized_grant_types", description = "授权类型")
	@JsonProperty("authorized_grant_types")
	private String authorizedGrantTypes;
	/**
	 * 回调地址
	 */
	@Parameter(name = "web_server_redirect_uri", description = "回调地址")
	@JsonProperty("web_server_redirect_uri")
	private String webServerRedirectUri;
	/**
	 * 权限
	 */
	@Parameter(name = "authorities", description = "权限")
	@JsonProperty("authorities")
	private String authorities;
	/**
	 * 令牌过期秒数
	 */
	@Parameter(name = "access_token_validity", description = "令牌过期秒数")
	@JsonProperty("access_token_validity")
	private Integer accessTokenValidity;
	/**
	 * 刷新令牌过期秒数
	 */
	@Parameter(name = "refresh_token_validity", description = "刷新令牌过期秒数")
	@JsonProperty("refresh_token_validity")
	private Integer refreshTokenValidity;
	/**
	 * 附加说明
	 */
	@Parameter(name = "additional_information", description = "附加说明")
	@JsonProperty("additional_information")
	private String additionalInformation;
	/**
	 * 自动授权
	 */
	@Parameter(name = "autoapprove", description = "自动授权")
	@JsonProperty("autoapprove")
	private String autoapprove;
	/**
	 * 创建人
	 */
	@Parameter(name = "create_user", description = "创建人")
	@JsonProperty("create_user")
	private String createUser;
	/**
	 * 创建时间
	 */
	@Parameter(name = "create_time", description = "创建时间")
	@JsonProperty("create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@Parameter(name = "update_user", description = "修改人")
	@JsonProperty("update_user")
	private String updateUser;
	/**
	 * 修改时间
	 */
	@Parameter(name = "update_time", description = "修改时间")
	@JsonProperty("update_time")
	private Date updateTime;
	/**
	 * 状态
	 */
	@Parameter(name = "status", description = "状态")
	@JsonProperty("status")
	private Integer status;
}
