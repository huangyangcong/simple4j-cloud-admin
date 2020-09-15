package com.simple4j.user.request;

	import lombok.Data;
	import io.swagger.annotations.ApiModel;

/**
 * 数据源配置表新增请求实体类
 *
 * @author Blade
 * @since 2020-09-15
 */
	@Data
	@ApiModel(value = "数据源配置表添新增请求实体类", description = "数据源配置表添新增请求实体类")
public class DatasourceAddOrUpdateRequest extends DatasourceUpdateRequest {

}
