package com.simple4j.user.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 新增请求实体类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "添新增请求实体类", description = "添新增请求实体类")
public class NavbarMenuAddOrUpdateRequest extends NavbarMenuUpdateRequest {

	private static final long serialVersionUID = 1L;


}
