package com.simple4j.user.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "新增请求实体类", description = "新增请求实体类")
public class NavbarAddOrUpdateRequest extends NavbarUpdateRequest {

}
