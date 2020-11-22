package com.simple4j.system.mapper;

import java.util.List;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper 接口
 *
 * @author hyc
 */
public interface DeptMapper extends ExtendMapper<Dept> {

	/**
	 * 获取树形节点
	 *
	 * @param tenantId
	 * @return
	 */
	List<Dept> tree(@Param("tenantId") String tenantId);

	/**
	 * 获取部门名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getDeptNames(@Param("userId") String userId);
}
