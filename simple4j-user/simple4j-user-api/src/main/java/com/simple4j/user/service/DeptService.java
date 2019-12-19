package com.simple4j.user.service;

import com.simple4j.common.api.bean.ApiRequest;
import com.simple4j.common.api.bean.ApiResponse;
import com.simple4j.user.request.DeptQueryAllRequest;
import com.simple4j.user.response.DeptQueryAllResponse;

/**
 * @author hyc
 */
public interface DeptService {
	/**
	 * 查询搜有
	 *
	 * @param apiRequest
	 * @return
	 */
	ApiResponse<DeptQueryAllResponse> queryAll(ApiRequest<DeptQueryAllRequest> apiRequest);
//
//	/**
//	 * 根据id查询部门
//	 *
//	 * @param id
//	 * @return
//	 */
//	DeptDTO findById(Long id);
//
//	/**
//	 * 创建部门
//	 *
//	 * @param resources
//	 * @return
//	 */
//	DeptDTO create(Dept resources);
//
//	/**
//	 * 更新部门
//	 *
//	 * @param resources
//	 */
//	void update(Dept resources);
//
//	/**
//	 * 删除部门
//	 *
//	 * @param id
//	 */
//	void delete(Long id);
//
//	/**
//	 * 构建部门树
//	 * @param deptDtos
//	 * @return
//	 */
//	Object buildTree(List<DeptDTO> deptDtos);
//
//	/**
//	 * 根据父id查询所有归属部门
//	 *
//	 * @param pid
//	 * @return
//	 */
//	List<Dept> findByPid(long pid);
//
//	/**
//	 * 根据角色编号查询部门
//	 *
//	 * @param id
//	 * @return
//	 */
//	Set<Dept> findByRoleIds(Long id);

}
