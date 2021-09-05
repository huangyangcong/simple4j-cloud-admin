package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端表列表请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "客户端表列表请求实体类", description = "客户端表列表请求实体类")
public class ClientListRequest implements Serializable {

	private static final long serialVersionUID = 1L;
}
