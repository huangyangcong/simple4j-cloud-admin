package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 参数表列表请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "参数表列表请求实体类", description = "参数表列表请求实体类")
public class ParamListRequest implements Serializable {

	private static final long serialVersionUID = 1L;
}
