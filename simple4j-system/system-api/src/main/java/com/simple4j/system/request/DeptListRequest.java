package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门表列表请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "部门表列表请求实体类", description = "部门表列表请求实体类")
public class DeptListRequest implements Serializable {

	private static final long serialVersionUID = 1L;
}
