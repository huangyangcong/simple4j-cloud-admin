package com.simple4j.gen.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据源配置表列表请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@Schema(name = "数据源配置表列表请求实体类", description = "数据源配置表列表请求实体类")
public class DatasourceListRequest implements Serializable {

	private static final long serialVersionUID = 1L;
}
