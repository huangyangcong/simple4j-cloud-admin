package com.simple4j.user.request;

	import lombok.Data;
import java.io.Serializable;
	import io.swagger.annotations.ApiModel;

/**
 * 数据源配置表列表请求实体类
 *
 * @author Blade
 * @since 2020-09-15
 */
	@Data
	@ApiModel(value = "数据源配置表列表请求实体类", description = "数据源配置表列表请求实体类")
public class DatasourceListRequest implements Serializable {

	private static final long serialVersionUID = 1L;

}
