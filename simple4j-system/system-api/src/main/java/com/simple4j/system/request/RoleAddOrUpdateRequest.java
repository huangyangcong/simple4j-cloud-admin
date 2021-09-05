package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@Schema(name = "角色表新增请求实体类", description = "角色表新增请求实体类")
public class RoleAddOrUpdateRequest extends RoleUpdateRequest {

}
