package com.simple4j.auth.mapper;

import com.simple4j.auth.entity.User;
import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author hyc
 */
public interface UserMapper extends ExtendMapper<User> {

	/**
	 * 查询用户名是否存在
	 *
	 * @param account
	 * @return
	 */
	Boolean existedByAccount(@Param("account") String account);

	/**
	 * 查询用户名是否存在
	 *
	 * @param usernames
	 * @return
	 */
	List<String> existedByUsernames(String[] usernames);
}
