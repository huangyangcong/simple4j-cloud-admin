package com.simple4j.gen.request;

import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

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
