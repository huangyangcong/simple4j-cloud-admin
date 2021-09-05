package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端表分页请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "客户端表分页请求实体类", description = "客户端表分页请求实体类")
public class ClientPageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@Parameter(name = "page_no", description = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	/**
	 * 分页数
	 */
	@Parameter(name = "pageSize", description = "分页数")
	@JsonProperty("page_size")
	private int pageSize;

	/**
	 * 客户端id
	 */
	@Parameter(name = "client_id", description = "客户端id")
	@JsonProperty("client_id")
	private String clientId;
	/**
	 * 客户端密钥
	 */
	@Parameter(name = "client_secret", description = "分页数")
	@JsonProperty("client_secret")
	private String clientSecret;
}
