package com.simple4j.user.service.impl;


import java.util.List;
import java.util.Objects;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.simple4j.user.base.BusinessException;
import com.simple4j.user.common.constant.CommonConstant;
import com.simple4j.user.common.util.SecurityUtils;
import com.simple4j.user.service.IDeptService;
import com.simple4j.user.service.IDictService;
import com.simple4j.user.service.IPostService;
import com.simple4j.user.service.IRoleMenuService;
import com.simple4j.user.service.IRoleService;
import com.simple4j.user.service.ITenantService;
import com.simple4j.user.service.IUserDeptService;
import com.simple4j.user.service.IUserOauthService;
import com.simple4j.user.service.IUserPostService;
import com.simple4j.user.service.IUserRoleService;
import com.simple4j.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.entity.Tenant;
import com.simple4j.user.entity.User;
import com.simple4j.user.response.UserInfo;
import com.simple4j.user.entity.UserOauth;
import com.simple4j.user.excel.UserExcel;
import com.simple4j.user.dao.UserMapper;
import com.simple4j.user.mapstruct.UserMapStruct;
import com.simple4j.user.request.UserAddRequest;
import com.simple4j.user.request.UserDetailRequest;
import com.simple4j.user.request.UserPageRequest;
import com.simple4j.user.request.UserRemoveRequest;
import com.simple4j.user.request.UserResetPasswordRequest;
import com.simple4j.user.request.UserUpdateRequest;
import com.simple4j.user.response.UserDetailResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final UserMapStruct userMapStruct;

	private final IRoleService roleService;
	private final IDeptService deptService;
	private final IPostService postService;
	private final ITenantService tenantService;
	private final IUserOauthService userOauthService;
	private final IUserRoleService userRoleService;
	private final IUserDeptService userDeptService;
	private final IUserPostService userPostService;
	private final IRoleMenuService roleMenuService;
	private final IDictService dictService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Page<UserDetailResponse> page(
		UserPageRequest userPageRequest) {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
			.eq(StrUtil.isNotEmpty(userPageRequest.getAccount()), User::getAccount,
				userPageRequest.getAccount())
			.eq(StrUtil.isNotEmpty(userPageRequest.getRealName()), User::getRealName,
				userPageRequest.getRealName());
		Page<User> users = this
			.page(new Page<>(userPageRequest.getPageNo(), userPageRequest.getPageSize()),
				(!SecurityUtils.getTenantId().equals(CommonConstant.ADMIN_TENANT_ID)) ? queryWrapper
					.eq(User::getTenantId, SecurityUtils.getTenantId()) : queryWrapper);
		Page<UserDetailResponse> userDetailResponsePage = userMapStruct.toVo(users);
		userDetailResponsePage.getRecords().forEach(this::convert);
		return userDetailResponsePage;
	}

	@Override
	public UserDetailResponse detail(UserDetailRequest userDetailRequest) {
		User user = this
			.getOne(Wrappers.<User>lambdaQuery().eq(User::getId, userDetailRequest.getId()));
		if (user != null) {
			UserDetailResponse userDetailResponse = userMapStruct.toVo(user);
			convert(userDetailResponse);
			return userDetailResponse;
		}
		return null;
	}

	private void convert(UserDetailResponse userDetailResponse) {
		Long userId = userDetailResponse.getId();
		Integer sex = userDetailResponse.getSex();
		userDetailResponse.setRoles(userRoleService.getRoleIds(userId));
		userDetailResponse.setDepts(userDeptService.getDeptIds(userId));
		userDetailResponse.setPosts(userPostService.getPostIds(userId));
		String sexName = dictService.getValue("sex", sex);
		userDetailResponse.setSexName(sexName);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void submit(UserAddRequest userAddRequest) {
		if (StrUtil.isNotEmpty(userAddRequest.getPassword())) {
			userAddRequest.setPassword(passwordEncoder.encode(userAddRequest.getPassword()));
		}
		Integer cnt = baseMapper.selectCount(
			Wrappers.<User>query().lambda().eq(User::getTenantId, userAddRequest.getTenantId())
				.eq(User::getAccount, userAddRequest.getAccount()));
		if (cnt > 0) {
			throw new BusinessException("当前用户已存在!");
		}
		User user = userMapStruct.toPo(userAddRequest);
		user.setStatus(1);
		grant(userAddRequest, user);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(UserUpdateRequest userUpdateRequest) {
		if (StrUtil.isNotEmpty(userUpdateRequest.getPassword())) {
			userUpdateRequest.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
		}
		Integer cnt = baseMapper.selectCount(
			Wrappers.<User>query().lambda().eq(User::getTenantId, userUpdateRequest.getTenantId())
				.eq(User::getAccount, userUpdateRequest.getAccount()));
		if (cnt == 0) {
			throw new BusinessException("当前用户不存在!");
		}
		User user = userMapStruct.toPo(userUpdateRequest);
		user.setId(userUpdateRequest.getId());
		grant(userUpdateRequest, user);
	}

	private void grant(UserAddRequest userAddRequest, User user) {
		//保存用户
		saveOrUpdate(user);
		List<Long> userIds = Lists.newArrayList(user.getId());
		//授予角色
		userRoleService.grant(userIds, userAddRequest.getRoles());
		//授予部门
		userDeptService.grant(userIds, userAddRequest.getDepts());
		//授予岗位
		userPostService.grant(userIds, userAddRequest.getPosts());
	}

	@Override
	public IPage<User> selectUserPage(IPage<User> page, User user) {
		return page.setRecords(baseMapper.selectUserPage(page, user));
	}

	@Override
	public UserInfo userInfo(Long userId) {
		User user = baseMapper.selectById(userId);
		UserInfo userInfo = userMapStruct.toUserInfo(user);
		if (ObjectUtil.isNotEmpty(user)) {
//			List<String> roleAlias = roleService.getRoleAlias(user.getId());
//			userInfo.setRoles(roleAlias);
		}
		return userInfo;
	}

	@Override
	public UserInfo userInfo(String tenantId, String account, String password) {
		User user = baseMapper.getUser(tenantId, account, password);
		UserInfo userInfo = userMapStruct.toUserInfo(user);
		if (ObjectUtil.isNotEmpty(user)) {
//			List<String> roleAlias = roleService.getRoleAlias(user.getId());
//			userInfo.setRoles(roleAlias);
		}
		return userInfo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserInfo userInfo(UserOauth userOauth) {
		UserOauth uo = userOauthService.getOne(
			Wrappers.<UserOauth>query().lambda().eq(UserOauth::getUuid, userOauth.getUuid())
				.eq(UserOauth::getSource, userOauth.getSource()));
		UserInfo userInfo;
		if (ObjectUtil.isNotEmpty(uo) && ObjectUtil.isNotEmpty(uo.getUserId())) {
			userInfo = this.userInfo(uo.getUserId());
			userInfo.setOauthId(ObjectUtil.toString(uo.getId()));
		} else {
			userInfo = new UserInfo();
			if (ObjectUtil.isEmpty(uo)) {
				userOauthService.save(userOauth);
				userInfo.setOauthId(ObjectUtil.toString(userOauth.getId()));
			} else {
				userInfo.setOauthId(ObjectUtil.toString(uo.getId()));
			}
			userInfo.setAccount(userOauth.getUsername());
//			userInfo.setRoles(Collections.singletonList(GUEST_NAME));
		}
		return userInfo;
	}

	@Override
	public boolean resetPassword(UserResetPasswordRequest userResetPasswordRequest) {
		User user = new User();
		user.setPassword(passwordEncoder.encode(CommonConstant.DEFAULT_PASSWORD));
		return this.update(user, Wrappers.<User>update().lambda()
			.in(User::getId, userResetPasswordRequest.getUserIds()));
	}

	@Override
	public boolean updatePassword(Long userId, String oldPassword, String newPassword,
		String newPassword1) {
		User user = getById(userId);
		if (!newPassword.equals(newPassword1)) {
			throw new BusinessException("请输入正确的确认密码!");
		}
		if (!user.getPassword().equals(DigestUtil.sha256Hex(oldPassword))) {
			throw new BusinessException("原密码不正确!");
		}
		return this.update(Wrappers.<User>update().lambda()
			.set(User::getPassword, DigestUtil.sha256Hex(newPassword)).eq(User::getId, userId));
	}

	@Override
	public List<String> getDeptName(List<Long> deptIds) {
		return baseMapper.getDeptName(deptIds);
	}

	@Override
	public void importUser(List<UserExcel> data) {
		data.forEach(userExcel -> {
			User user = Objects.requireNonNull(userMapStruct.toPo(userExcel));
			// 设置默认密码
			user.setPassword(CommonConstant.DEFAULT_PASSWORD);
			this.saveOrUpdate(user);

			List<Long> userIds = Lists.newArrayList(user.getId());
			// 设置部门ID
			List<Long> deptIds = deptService
				.getDeptIds(userExcel.getTenantId(), userExcel.getDeptName());
			userDeptService.grant(userIds, deptIds);

			// 设置岗位ID
			List<Long> postIds = postService
				.getPostIds(userExcel.getTenantId(), userExcel.getDeptName());
			userPostService.grant(userIds, postIds);

			// 设置角色ID
			List<Long> roleIds = roleService
				.getRoleIds(userExcel.getTenantId(), userExcel.getDeptName());
			userRoleService.grant(userIds, roleIds);
		});
	}

	@Override
	public List<UserExcel> exportUser(Wrapper<User> queryWrapper) {
		List<UserExcel> userList = baseMapper.exportUser(queryWrapper);
		userList.forEach(user -> {
			user.setRoleName(roleService.getRoleNames(user.getId()));
			user.setDeptName(deptService.getDeptNames(user.getId()));
			user.setPostName(postService.getPostNames(user.getId()));
		});
		return userList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean registerGuest(User user, Long oauthId) {
		Tenant tenant = tenantService.getByTenantId(user.getTenantId());
		if (tenant == null || tenant.getId() == null) {
			throw new ApiException("租户信息错误!");
		}
		UserOauth userOauth = userOauthService.getById(oauthId);
		if (userOauth == null || userOauth.getId() == null) {
			throw new ApiException("第三方登陆信息错误!");
		}
		user.setRealName(user.getName());
		user.setAvatar(userOauth.getAvatar());
		boolean userTemp = this.save(user);
		userOauth.setUserId(user.getId());
		userOauth.setTenantId(user.getTenantId());
		boolean oauthTemp = userOauthService.updateById(userOauth);
		return (userTemp && oauthTemp);
	}

	@Override
	public UserInfo loadUserByUsername(String username) {
		User user = baseMapper
			.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, username));
		if (ObjectUtil.isNotEmpty(user)) {
			UserInfo userInfo = userMapStruct.toUserInfo(user);
			List<Long> roleIds = userRoleService.getRoleIds(user.getId());
			userInfo.setRoles(roleIds);
			List<String> permissions = roleMenuService.getPermission(roleIds);
			userInfo.setPermissions(permissions);
			return userInfo;
		}
		return null;
	}

	@Override
	public void remove(UserRemoveRequest userRemoveRequest) {
		removeByIds(userRemoveRequest.getIds());
	}
}
