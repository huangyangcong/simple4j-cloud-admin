package com.simple4j.system.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户端表详情响应实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "客户端表详情响应实体类", description = "客户端表详情响应实体类")
public class ClientDetailResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(name = "id", value = "主键")
	@JsonProperty("id")
	private Long id;
	/**
	 * 客户端id
	 */
	@ApiModelProperty(name = "client_id", value = "客户端id")
	@JsonProperty("client_id")
	private String clientId;
	/**
	 * 客户端密钥
	 */
	@ApiModelProperty(name = "client_secret", value = "客户端密钥")
	@JsonProperty("client_secret")
	private String clientSecret;
	/**
	 * 资源集合
	 */
	@ApiModelProperty(name = "resource_ids", value = "资源集合")
	@JsonProperty("resource_ids")
	private String resourceIds;
	/**
	 * 授权范围
	 */
	@ApiModelProperty(name = "scope", value = "授权范围")
	@JsonProperty("scope")
	private String scope;
	/**
	 * 授权类型
	 */
	@ApiModelProperty(name = "authorized_grant_types", value = "授权类型")
	@JsonProperty("authorized_grant_types")
	private String authorizedGrantTypes;
	/**
	 * 回调地址
	 */
	@ApiModelProperty(name = "web_server_redirect_uri", value = "回调地址")
	@JsonProperty("web_server_redirect_uri")
	private String webServerRedirectUri;
	/**
	 * 权限
	 */
	@ApiModelProperty(name = "authorities", value = "权限")
	@JsonProperty("authorities")
	private String authorities;
	/**
	 * 令牌过期秒数
	 */
	@ApiModelProperty(name = "access_token_validity", value = "令牌过期秒数")
	@JsonProperty("access_token_validity")
	private Integer accessTokenValidity;
	/**
	 * 刷新令牌过期秒数
	 */
	@ApiModelProperty(name = "refresh_token_validity", value = "刷新令牌过期秒数")
	@JsonProperty("refresh_token_validity")
	private Integer refreshTokenValidity;
	/**
	 * 附加说明
	 */
	@ApiModelProperty(name = "additional_information", value = "附加说明")
	@JsonProperty("additional_information")
	private String additionalInformation;
	/**
	 * 自动授权
	 */
	@ApiModelProperty(name = "autoapprove", value = "自动授权")
	@JsonProperty("autoapprove")
	private String autoapprove;
	/**
	 * 创建人
	 */
	@ApiModelProperty(name = "create_user", value = "创建人")
	@JsonProperty("create_user")
	private Long createUser;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "create_time", value = "创建时间")
	@JsonProperty("create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@ApiModelProperty(name = "update_user", value = "修改人")
	@JsonProperty("update_user")
	private Long updateUser;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(name = "update_time", value = "修改时间")
	@JsonProperty("update_time")
	private Date updateTime;
	/**
	 * 状态
	 */
	@ApiModelProperty(name = "status", value = "状态")
	@JsonProperty("status")
	private Integer status;
	/**
	 * 是否已删除
	 */
	@ApiModelProperty(name = "is_deleted", value = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;


}
