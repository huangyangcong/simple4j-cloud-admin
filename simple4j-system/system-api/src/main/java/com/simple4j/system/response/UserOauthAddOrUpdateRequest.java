package com.simple4j.system.response;

import com.simple4j.system.request.UserOauthUpdateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户第三方认证表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@Schema(name = "用户第三方认证表新增请求实体类", description = "用户第三方认证表新增请求实体类")
public class UserOauthAddOrUpdateRequest extends UserOauthUpdateRequest {

}
