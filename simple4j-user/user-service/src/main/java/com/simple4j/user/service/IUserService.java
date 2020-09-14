package com.simple4j.user.service;


import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.User;
import com.simple4j.user.response.UserInfo;
import com.simple4j.user.entity.UserOauth;
import com.simple4j.user.excel.UserExcel;
import com.simple4j.user.request.UserAddRequest;
import com.simple4j.user.request.UserDetailRequest;
import com.simple4j.user.request.UserPageRequest;
import com.simple4j.user.request.UserRemoveRequest;
import com.simple4j.user.request.UserResetPasswordRequest;
import com.simple4j.user.request.UserUpdateRequest;
import com.simple4j.user.response.UserDetailResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IUserService extends IService<User> {

	/**
	 * 新增或修改用户
	 *
	 * @param userAddRequest
	 * @return
	 */
	void submit(UserAddRequest userAddRequest);

	/**
	 * @param userUpdateRequest
	 * @return
	 */
	void update(UserUpdateRequest userUpdateRequest);

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param user
	 * @return
	 */
	IPage<User> selectUserPage(IPage<User> page, User user);

	/**
	 * 用户信息
	 *
	 * @param userId
	 * @return
	 */
	UserInfo userInfo(Long userId);

	/**
	 * 用户信息
	 *
	 * @param tenantId
	 * @param account
	 * @param password
	 * @return
	 */
	UserInfo userInfo(String tenantId, String account, String password);

	/**
	 * 用户信息
	 *
	 * @param userOauth
	 * @return
	 */
	UserInfo userInfo(UserOauth userOauth);

	/**
	 * 初始化密码
	 *
	 * @param userResetPasswordRequest
	 * @return
	 */
	boolean resetPassword(UserResetPasswordRequest userResetPasswordRequest);

	/**
	 * 修改密码
	 *
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @param newPassword1
	 * @return
	 */
	boolean updatePassword(Long userId, String oldPassword, String newPassword,
		String newPassword1);

	/**
	 * 获取部门名
	 *
	 * @param deptIds
	 * @return
	 */
	List<String> getDeptName(List<Long> deptIds);

	/**
	 * 导入用户数据
	 *
	 * @param data
	 * @return
	 */
	void importUser(List<UserExcel> data);

	/**
	 * 获取导出用户数据
	 *
	 * @param queryWrapper
	 * @return
	 */
	List<UserExcel> exportUser(Wrapper<User> queryWrapper);

	/**
	 * 注册用户
	 *
	 * @param user
	 * @param oauthId
	 * @return
	 */
	boolean registerGuest(User user, Long oauthId);

	/**
	 * 根据用户名查找用户信息
	 *
	 * @param username
	 * @return
	 */
	UserInfo loadUserByUsername(String username);

	/**
	 * 用户详情
	 *
	 * @param userDetailRequest
	 * @return
	 */
	UserDetailResponse detail(UserDetailRequest userDetailRequest);

	/**
	 * 用户详情
	 *
	 * @param userDetailRequest
	 * @return
	 */
	Page<UserDetailResponse> page(UserPageRequest userPageRequest);

	/**
	 * 删除用户
	 *
	 * @param userRemoveRequest
	 * @return
	 */
	void remove(UserRemoveRequest userRemoveRequest);
}
