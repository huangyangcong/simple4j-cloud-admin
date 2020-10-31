package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "新增请求实体类", description = "新增请求实体类")
public class NavbarMenuAddRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 按钮编号 */
  @ApiModelProperty(name = "menu_id", value = "按钮编号")
  @JsonProperty("menu_id")
  private Long menuId;
  /** 顶部按钮编号 */
  @ApiModelProperty(name = "top_menu_id", value = "顶部按钮编号")
  @JsonProperty("top_menu_id")
  private Long NavbarId;
}
