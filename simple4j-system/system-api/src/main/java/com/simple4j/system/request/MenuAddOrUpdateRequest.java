package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 菜单表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "菜单表新增请求实体类", description = "菜单表新增请求实体类")
public class MenuAddOrUpdateRequest extends MenuUpdateRequest {

}
