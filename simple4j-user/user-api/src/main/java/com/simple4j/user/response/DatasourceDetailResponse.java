package com.simple4j.user.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


/**
 * 数据源配置表详情响应实体类
 *
 * @author Blade
 * @since 2020-09-15
 */
	@Data
	@ApiModel(value = "数据源配置表详情响应实体类", description = "数据源配置表详情响应实体类")
	public class DatasourceDetailResponse extends DatasourceAddOrUpdateRequest implements
		Serializable {

		private static final long serialVersionUID = 1L;

		


}
