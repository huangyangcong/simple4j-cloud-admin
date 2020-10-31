package com.simple4j.system.service;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.DeptAddRequest;
import com.simple4j.system.request.DeptDetailRequest;
import com.simple4j.system.request.DeptListRequest;
import com.simple4j.system.request.DeptPageRequest;
import com.simple4j.system.request.DeptRemoveRequest;
import com.simple4j.system.request.DeptUpdateRequest;
import com.simple4j.system.response.DeptDetailResponse;

import java.util.List;
import java.util.Set;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IDeptService {

  /**
   * 树形结构
   *
   * @param tenantId
   * @return
   */
  List<DeptDetailResponse> tree(String tenantId);

  /**
   * 获取部门ID
   *
   * @param tenantId
   * @param deptNames
   * @return
   */
  Set<String> getDeptIds(String tenantId, List<String> deptNames);

  /**
   * 获取部门名
   *
   * @param userId
   * @return
   */
  List<String> getDeptNames(String userId);

  /** 详情 */
  DeptDetailResponse detail(DeptDetailRequest deptDetailRequest);

  /** 列表 部门表 */
  List<DeptDetailResponse> list(DeptListRequest deptListRequest);

  /** 自定义分页 部门表 */
  Page<DeptDetailResponse> page(DeptPageRequest deptPageRequest);

  /** 新增 部门表 */
  boolean add(DeptAddRequest deptAddRequest);

  /** 修改 部门表 */
  boolean update(DeptUpdateRequest deptUpdateRequest);

  /** 删除 部门表 */
  boolean remove(DeptRemoveRequest deptRemoveRequest);
}
