package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "新增请求实体类", description = "新增请求实体类")
public class NavbarMenuAddOrUpdateRequest extends NavbarMenuUpdateRequest {

	private static final long serialVersionUID = 1L;
}
