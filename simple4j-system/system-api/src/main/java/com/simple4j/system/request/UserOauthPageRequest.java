package com.simple4j.user.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户第三方认证表分页请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "用户第三方认证表分页请求实体类", description = "用户第三方认证表分页请求实体类")
public class UserOauthPageRequest implements Serializable {

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
}
