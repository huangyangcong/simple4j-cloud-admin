package com.simple4j.user.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 数据源配置表新增请求实体类
 *
 * @author Blade
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "数据源配置表添新增请求实体类", description = "数据源配置表添新增请求实体类")
public class DatasourceAddOrUpdateRequest extends DatasourceUpdateRequest {

}
