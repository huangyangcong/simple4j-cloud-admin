package com.simple4j.gen.request;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import com.simple4j.gen.request.DatasourceAddOrUpdateRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 数据源配置表修改请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "数据源配置表修改请求实体类", description = "数据源配置表修改请求实体类")
public class DatasourceUpdateRequest extends DatasourceAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据源配置表ID
	 */
		@ApiModelProperty(value = "数据源配置表ID")
	@NotNull(message = "数据源配置表编号不能为空")
	private Long id;

	}
