package com.simple4j.gen.request;


import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.gen.request.DatasourceUpdateRequest;
/**
 * 数据源配置表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "数据源配置表新增请求实体类", description = "数据源配置表新增请求实体类")
public class DatasourceAddOrUpdateRequest extends DatasourceUpdateRequest {

}
