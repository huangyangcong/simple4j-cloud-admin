package com.simple4j.gen.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 数据源配置表列表请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "数据源配置表列表请求实体类", description = "数据源配置表列表请求实体类")
public class DatasourceListRequest implements Serializable {

	private static final long serialVersionUID = 1L;
}
