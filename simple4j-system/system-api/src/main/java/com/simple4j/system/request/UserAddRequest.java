package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class UserAddRequest {

  /** 编号 */
  @ApiModelProperty(name = "code", value = "编号")
  private String code;
  /** 账号 */
  @ApiModelProperty(name = "account", value = "账号")
  private String account;
  /** 密码 */
  @ApiModelProperty(name = "password", value = "密码")
  private String password;
  /** 昵称 */
  @ApiModelProperty(name = "name", value = "昵称")
  private String name;
  /** 真名 */
  @ApiModelProperty(name = "realName", value = "真名")
  @JsonProperty("real_name")
  private String realName;
  /** 头像 */
  @ApiModelProperty(name = "avatar", value = "头像")
  private String avatar;
  /** 邮箱 */
  @ApiModelProperty(name = "email", value = "邮箱")
  private String email;
  /** 手机 */
  @ApiModelProperty(name = "phone", value = "手机")
  private String phone;
  /** 生日 */
  @ApiModelProperty(name = "birthday", value = "生日")
  private Date birthday;
  /** 性别 */
  @ApiModelProperty(name = "sex", value = "性别")
  private Integer sex;
  /** 角色 */
  @ApiModelProperty(name = "roles", value = "角色")
  private Set<String> roles;
  /** 部门 */
  @ApiModelProperty(name = "depts", value = "部门")
  private Set<String> depts;
  /** 岗位 */
  @ApiModelProperty(name = "posts", value = "岗位")
  private Set<String> posts;
}
