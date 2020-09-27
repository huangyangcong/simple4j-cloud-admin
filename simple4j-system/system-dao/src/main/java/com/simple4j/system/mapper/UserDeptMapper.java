package com.simple4j.system.mapper;

import java.util.List;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;
import com.simple4j.system.entity.UserDept;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface UserDeptMapper extends ExtendMapper<UserDept> {


	/**
	 * 获取用户部门ID
	 *
	 * @param userId
	 * @return
	 */
	List<Long> getDeptIds(@Param("userId") Long userId);
}
