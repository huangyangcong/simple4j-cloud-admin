package com.simple4j.system.mapper;

import java.util.Set;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.UserDept;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper 接口
 *
 * @author hyc
 */
public interface UserDeptMapper extends ExtendMapper<UserDept> {

	/**
	 * 获取用户部门ID
	 *
	 * @param userId
	 * @return
	 */
	Set<String> getDeptIds(@Param("userId") String userId);
}
