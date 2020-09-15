package com.simple4j.user.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 客户端表列表请求实体类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "客户端表列表请求实体类", description = "客户端表列表请求实体类")
public class ClientListRequest implements Serializable {

	private static final long serialVersionUID = 1L;

}
