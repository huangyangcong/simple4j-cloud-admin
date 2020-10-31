package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 列表请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "列表请求实体类", description = "列表请求实体类")
public class NavbarListRequest implements Serializable {

  private static final long serialVersionUID = 1L;
}
