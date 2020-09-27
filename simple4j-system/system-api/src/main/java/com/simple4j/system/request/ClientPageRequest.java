package com.simple4j.user.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户端表分页请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "客户端表分页请求实体类", description = "客户端表分页请求实体类")
public class ClientPageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@ApiModelProperty(name = "page_no", value = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	/**
	 * 分页数
	 */
	@ApiModelProperty(name = "pageSize", value = "分页数")
	@JsonProperty("page_size")
	private int pageSize;

	/**
	 * 客户端id
	 */
	@ApiModelProperty(name = "client_id", value = "客户端id")
	@JsonProperty("client_id")
	private String clientId;
	/**
	 * 客户端密钥
	 */
	@ApiModelProperty(name = "client_secret", value = "分页数")
	@JsonProperty("client_secret")
	private String clientSecret;
}
