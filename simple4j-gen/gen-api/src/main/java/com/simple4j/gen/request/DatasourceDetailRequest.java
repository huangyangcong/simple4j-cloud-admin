package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据源配置表详情请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "数据源配置表详情请求实体类", description = "数据源配置表详情请求实体类")
public class DatasourceDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 数据源配置表详情编号
	 */
	@ApiModelProperty(value = "数据源配置表详情编号", name = "id")
	@JsonProperty("id")
	private String id;
}
