package com.simple4j.user.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 角色表新增请求实体类
 *
 * @author Blade
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "角色表添新增请求实体类", description = "角色表添新增请求实体类")
public class RoleAddOrUpdateRequest extends RoleUpdateRequest {

}
