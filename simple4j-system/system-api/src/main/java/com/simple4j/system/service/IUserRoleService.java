package com.simple4j.system.service;

import com.simple4j.system.request.UserRoleGrantRequest;

import java.util.Set;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IUserRoleService {

  /**
   * 获取角色
   *
   * @param userId
   * @return
   */
  Set<String> getRoleIds(String userId);

  /**
   * 授权角色
   *
   * @param userRoleGrantRequest
   * @return
   */
  void grant(UserRoleGrantRequest userRoleGrantRequest);

  /** 授权角色 */
  void grant(Set<String> userIds, Set<String> roleIds);
}
