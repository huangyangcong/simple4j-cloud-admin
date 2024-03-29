package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端表详情请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "客户端表详情请求实体类", description = "客户端表详情请求实体类")
public class ClientDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 客户端表详情编号
	 */
	@Parameter(description = "客户端表详情编号", name = "id")
	private String id;
}
