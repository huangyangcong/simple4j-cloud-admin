package com.simple4j.gen.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 数据源配置表修改请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@Schema(name = "数据源配置表修改请求实体类", description = "数据源配置表修改请求实体类")
public class DatasourceUpdateRequest extends DatasourceAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据源配置表ID
	 */
	@Parameter(description = "数据源配置表ID")
	@NotNull(message = "数据源配置表编号不能为空")
	private String id;
}
