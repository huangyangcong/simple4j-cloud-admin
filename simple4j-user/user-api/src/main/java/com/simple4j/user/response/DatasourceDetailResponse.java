package com.simple4j.user.response;

import java.io.Serializable;

import com.simple4j.user.request.DatasourceAddOrUpdateRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * 数据源配置表详情响应实体类
 *
 * @author Blade
 * @since 2020-09-16
 */
	@Data
	@ApiModel(value = "数据源配置表详情响应实体类", description = "数据源配置表详情响应实体类")
	public class DatasourceDetailResponse extends DatasourceAddOrUpdateRequest implements
		Serializable {

		private static final long serialVersionUID = 1L;

		


}
