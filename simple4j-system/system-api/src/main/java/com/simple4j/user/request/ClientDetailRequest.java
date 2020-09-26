package com.simple4j.user.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户端表详情请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "客户端表详情请求实体类", description = "客户端表详情请求实体类")
public class ClientDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 客户端表详情编号
	 */
	@ApiModelProperty(value = "客户端表详情编号", name = "id")
	private Long id;
}
