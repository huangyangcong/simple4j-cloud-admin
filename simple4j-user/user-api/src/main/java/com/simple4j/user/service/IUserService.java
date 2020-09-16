package com.simple4j.user.service;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.UserAddRequest;
import com.simple4j.user.request.UserDetailRequest;
import com.simple4j.user.request.UserListRequest;
import com.simple4j.user.request.UserOauthAddOrUpdateRequest;
import com.simple4j.user.request.UserPageRequest;
import com.simple4j.user.request.UserRegisterGuestRequest;
import com.simple4j.user.request.UserRemoveRequest;
import com.simple4j.user.request.UserResetPasswordRequest;
import com.simple4j.user.request.UserUpdateRequest;
import com.simple4j.user.response.UserDetailResponse;
import com.simple4j.user.response.UserInfo;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IUserService {

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
	boolean update(UserUpdateRequest userUpdateRequest);

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
	UserInfo userInfo(UserOauthAddOrUpdateRequest userOauth);

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
	 * 注册用户
	 *
	 * @param userRegisterGuestRequest
	 * @return
	 */
	boolean registerGuest(UserRegisterGuestRequest userRegisterGuestRequest);


	/**
	 * 根据用户名查找用户信息
	 *
	 * @param username
	 * @return
	 */
	UserInfo loadUserByUsername(String username);

	/**
	 * 获取用户列表
	 *
	 * @param userListRequest
	 * @return
	 */
	List<UserDetailResponse> list(UserListRequest userListRequest);

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
	 * @param userPageRequest
	 * @return
	 */
	Page<UserDetailResponse> page(UserPageRequest userPageRequest);

	/**
	 * 删除用户
	 *
	 * @param userRemoveRequest
	 * @return
	 */
	boolean remove(UserRemoveRequest userRemoveRequest);


	/**
	 * 导入用户
	 *
	 * @param inputStream
	 * @param filename
	 */
	void importUser(InputStream inputStream, String filename);

	/**
	 * 获取导出用户列表
	 *
	 * @param userListRequest
	 * @return
	 */
	void exportUser(OutputStream outputStream, UserListRequest userListRequest);

	/**
	 * 导出模板
	 *
	 * @param outputStream
	 */
	void exportUser(OutputStream outputStream);

}
