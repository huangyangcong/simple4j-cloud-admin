package com.simple4j.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.google.common.collect.Lists;
import com.simple4j.autoconfigure.jwt.properties.JwtProperties;
import com.simple4j.autoconfigure.jwt.service.AbstractUserDetailsService;
import com.simple4j.api.base.BusinessException;
import com.simple4j.api.base.Page;
import com.simple4j.system.common.constant.CommonConstant;
import com.simple4j.system.dto.JwtDto;
import com.simple4j.system.entity.User;
import com.simple4j.system.excel.UserExcelImport;
import com.simple4j.system.mapper.UserMapper;
import com.simple4j.system.mapstruct.UserMapStruct;
import com.simple4j.system.request.*;
import com.simple4j.system.response.*;
import com.simple4j.system.service.*;
import com.simple4j.system.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractUserDetailsService<JwtDto> implements IUserService {

	private final UserMapStruct userMapStruct;
	private final JwtProperties jwtProperties;

	private final UserMapper userMapper;
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
	private final ICaptchaService captchaService;
	private final RedisTemplate redisTemplate;

	@Override
	public Page<UserDetailResponse> page(
			UserPageRequest userPageRequest) {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
				.eq(StrUtil.isNotEmpty(userPageRequest.getAccount()), User::getAccount,
						userPageRequest.getAccount())
				.eq(StrUtil.isNotEmpty(userPageRequest.getRealName()), User::getRealName,
						userPageRequest.getRealName());
		IPage<User> page = userMapper
				.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(userPageRequest.getPageNo(), userPageRequest.getPageSize()),
						SecurityUtils.isTenantAdmin() ? queryWrapper
								.eq(User::getTenantId, SecurityUtils.getTenantId()) : queryWrapper);
		Page<User> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(),
				page.getRecords());
		return userMapStruct.toVo(pages);
	}

	@Override
	public List<UserDetailResponse> list(UserListRequest userListRequest) {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
				.eq(StrUtil.isNotEmpty(userListRequest.getAccount()), User::getAccount,
						userListRequest.getAccount())
				.eq(StrUtil.isNotEmpty(userListRequest.getRealName()), User::getRealName,
						userListRequest.getRealName());
		if (!SecurityUtils.isAdministrator()) {
			queryWrapper.eq(User::getTenantId, userListRequest.getTenantId());
		}
		queryWrapper.eq(User::getIsDeleted, CommonConstant.DB_NOT_DELETED);

		List<User> list = userMapper.list(queryWrapper);
		return userMapStruct.toVo(list);
	}

	@Override
	public UserDetailResponse detail(UserDetailRequest userDetailRequest) {
		User user = userMapper
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
		Integer cnt = userMapper.selectCount(
				Wrappers.<User>query().lambda().eq(User::getTenantId, SecurityUtils.getTenantId())
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
	public boolean update(UserUpdateRequest userUpdateRequest) {
		if (StrUtil.isNotEmpty(userUpdateRequest.getPassword())) {
			userUpdateRequest.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
		}
		Integer cnt = userMapper.selectCount(
				Wrappers.<User>query().lambda().eq(User::getTenantId, SecurityUtils.getTenantId())
						.eq(User::getAccount, userUpdateRequest.getAccount()));
		if (cnt == 0) {
			throw new BusinessException("当前用户不存在!");
		}
		User user = userMapStruct.toPo(userUpdateRequest);
		user.setId(userUpdateRequest.getId());
		grant(userUpdateRequest, user);
		return true;
	}

	private void grant(UserAddRequest userAddRequest, User user) {
		//保存用户
		userMapper.saveOrUpdate(user);
		List<Long> userIds = Lists.newArrayList(user.getId());
		//授予角色
		userRoleService.grant(userIds, userAddRequest.getRoles());
		//授予部门
		userDeptService.grant(userIds, userAddRequest.getDepts());
		//授予岗位
		userPostService.grant(userIds, userAddRequest.getPosts());
	}

	@Override
	public UserInfo currentUserInfo() {
		return SecurityUtils.getCurrentUser().getUserInfo();
	}

	@Override
	public UserInfo userInfo(Long userId) {
		User user = userMapper.selectById(userId);
		UserInfo userInfo = userMapStruct.toUserInfo(user);
		if (ObjectUtil.isNotEmpty(user)) {
//			List<String> roleAlias = roleService.getRoleAlias(user.getId());
//			userInfo.setRoles(roleAlias);
		}
		return userInfo;
	}

	@Override
	public UserInfo userInfo(String tenantId, String account, String password) {
		User user = userMapper.getUser(tenantId, account, password);
		UserInfo userInfo = userMapStruct.toUserInfo(user);
		if (ObjectUtil.isNotEmpty(user)) {
//			List<String> roleAlias = roleService.getRoleAlias(user.getId());
//			userInfo.setRoles(roleAlias);
		}
		return userInfo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserInfo userInfo(UserOauthAddOrUpdateRequest userOauth) {
		UserOauthDetailRequest userOauthDetailRequest = new UserOauthDetailRequest();
		userOauthDetailRequest.setUuid(userOauth.getUuid());
		UserOauthDetailResponse uo = userOauthService.detail(userOauthDetailRequest);
		UserInfo userInfo;
		if (ObjectUtil.isNotEmpty(uo) && ObjectUtil.isNotEmpty(uo.getUserId())) {
			userInfo = this.userInfo(uo.getUserId());
			userInfo.setOauthId(ObjectUtil.toString(uo.getId()));
		} else {
			userInfo = new UserInfo();
			if (ObjectUtil.isEmpty(uo)) {
				userOauthService.add(userOauth);
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
		return userMapper.updateBool(user, Wrappers.<User>update().lambda()
				.in(User::getId, userResetPasswordRequest.getUserIds()));
	}

	@Override
	public boolean updatePassword(String oldPassword, String newPassword,
								  String newPassword1) {
		Long userId = SecurityUtils.getCurrentUserId();
		User user = userMapper.getById(userId);
		if (!newPassword.equals(newPassword1)) {
			throw new BusinessException("请输入正确的确认密码!");
		}
		if (!user.getPassword().equals(DigestUtil.sha256Hex(oldPassword))) {
			throw new BusinessException("原密码不正确!");
		}
		return userMapper.update(Wrappers.<User>update().lambda()
				.set(User::getPassword, DigestUtil.sha256Hex(newPassword)).eq(User::getId, userId));
	}

	@Override
	public List<String> getDeptName(List<Long> deptIds) {
		return userMapper.getDeptName(deptIds);
	}

	@Override
	public void importUser(InputStream inputStream, String filename) {
		if (StringUtils.isEmpty(filename)) {
			throw new RuntimeException("请上传文件!");
		}
		if ((!StringUtils.endsWithIgnoreCase(filename, ".xls") && !StringUtils
				.endsWithIgnoreCase(filename, ".xlsx"))) {
			throw new RuntimeException("请上传正确的excel文件!");
		}
		if (inputStream == null) {
			throw new RuntimeException("请上传正确的excel文件!");
		}
		//默认每隔3000条存储数据库
		int batchCount = 3000;
		//缓存的数据列表
		List<UserExcelImport> list = new ArrayList<>();

		ExcelReaderBuilder builder = EasyExcel.read(inputStream, UserExcelImport.class,
				new AnalysisEventListener<UserExcelImport>() {
					@Override
					public void invoke(UserExcelImport data, AnalysisContext context) {
						list.add(data);
						// 达到BATCH_COUNT，则调用importer方法入库，防止数据几万条数据在内存，容易OOM
						if (list.size() >= batchCount) {
							// 调用importer方法
							importUser(list);
							// 存储完成清理list
							list.clear();
						}
					}

					@Override
					public void doAfterAllAnalysed(AnalysisContext context) {
						// 调用importer方法
						importUser(list);
						// 存储完成清理list
						list.clear();
					}

					public void importUser(List<UserExcelImport> data) {
						data.forEach(userExcel -> {
							User user = Objects.requireNonNull(userMapStruct.toPo(userExcel));
							// 设置默认密码
							user.setPassword(CommonConstant.DEFAULT_PASSWORD);
							userMapper.saveOrUpdate(user);

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
				});
		builder.doReadAll();
	}

	@Override
	public void exportUser(OutputStream outputStream, UserListRequest userListRequest) {
		List<UserDetailResponse> users = this.list(userListRequest);
		List<UserExcelImport> userExcelImportList = userMapStruct.toExcel(users);
		userExcelImportList.forEach(user -> {
			user.setRoleName(roleService.getRoleNames(user.getId()));
			user.setDeptName(deptService.getDeptNames(user.getId()));
			user.setPostName(postService.getPostNames(user.getId()));
		});
		EasyExcel.write(outputStream, UserExcelImport.class).sheet("用户数据表").doWrite(userExcelImportList);
	}

	@Override
	public void exportUser(OutputStream outputStream) {
		EasyExcel.write(outputStream, UserExcelImport.class).sheet("用户数据表").doWrite(new ArrayList<>());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean registerGuest(UserRegisterGuestRequest userRegisterGuestRequest) {
		String tenantId = SecurityUtils.getTenantId();
		Long oauthId = userRegisterGuestRequest.getOauthId();
		TenantDetailResponse tenant = tenantService.getByTenantId(tenantId);
		if (tenant == null || tenant.getId() == null) {
			throw new ApiException("租户信息错误!");
		}
		UserOauthDetailRequest userOauthDetailRequest = new UserOauthDetailRequest();
		userOauthDetailRequest.setId(oauthId);
		UserOauthDetailResponse userOauth = userOauthService.detail(userOauthDetailRequest);
		if (userOauth == null || userOauth.getId() == null) {
			throw new ApiException("第三方登陆信息错误!");
		}
		User user = userMapStruct.toPo(userRegisterGuestRequest);
		user.setRealName(user.getName());
		user.setAvatar(userOauth.getAvatar());
		boolean userTemp = userMapper.save(user);

		userOauth.setUserId(user.getId());
		userOauth.setTenantId(user.getTenantId());
		boolean oauthTemp = userOauthService.update(userOauth);
		return (userTemp && oauthTemp);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper
				.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, username));
		if (ObjectUtil.isEmpty(user)) {
			throw new UsernameNotFoundException("");
		}
		UserInfo userInfo = userMapStruct.toUserInfo(user);
		List<Long> roleIds = userRoleService.getRoleIds(user.getId());
		userInfo.setRoles(roleIds);
		List<String> permissions = roleMenuService.getPermission(roleIds);
		userInfo.setPermissions(permissions);
		return new JwtDto(userInfo);
	}

	@Override
	public boolean remove(UserRemoveRequest userRemoveRequest) {
		return userMapper.removeByIds(userRemoveRequest.getIds());
	}

	@Override
	public UserLoginResponse login(UserLoginRequest userLoginRequest) {
		String captchaKey = userLoginRequest.getCaptchaKey();
		//校验验证码
//		captchaService.verify(captchaKey, userLoginRequest.getCaptchaCode());
		//登录校验
		String token = usernameAndPasswordAuth(userLoginRequest.getUsername(),
				userLoginRequest.getPassword());
		UserLoginResponse userLoginResponse = new UserLoginResponse();
		userLoginResponse.setToken(token);
		//删除验证码
		captchaService.deleteCaptcha(captchaKey);
		return userLoginResponse;
	}


	//userDetail实现----------------------------------------------------

	@Override
	public void save(JwtDto userDetails, String token) {
		redisTemplate.opsForValue().set(jwtProperties.getOnlineKey() + token, userDetails,
				jwtProperties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
		redisTemplate.opsForSet()
				.add(jwtProperties.getOnlineKey() + userDetails.getUsername(), token);
	}

	@Override
	public JwtDto get(String token) {
		return (JwtDto) redisTemplate.opsForValue().get(jwtProperties.getOnlineKey() + token);
	}

	@Override
	public boolean checkExpire(String token) {
		return redisTemplate.opsForValue().get(jwtProperties.getOnlineKey() + token) != null;
	}

	@Override
	public void removeOtherToken(String userName, String ignoreToken) {
		Set<Object> tokens = redisTemplate.opsForSet()
				.members(jwtProperties.getOnlineKey() + userName);
		if (CollUtil.isEmpty(tokens)) {
			return;
		}
		List<Object> delTokens = new ArrayList<>();
		List<String> delKeys = new ArrayList<>();
		for (Object token : tokens) {
			if (!token.equals(ignoreToken)) {
				delTokens.add(token);
				delKeys.add(jwtProperties.getOnlineKey() + token);
			}
		}
		if (CollUtil.isNotEmpty(delTokens)) {
			redisTemplate.opsForSet()
					.remove(jwtProperties.getOnlineKey() + userName, delTokens.toArray());
		}
		if (CollUtil.isNotEmpty(delKeys)) {
			redisTemplate.delete(delKeys);
		}
	}

	@Override
	public void logout(String username) {
		removeOtherToken(username, null);
	}

	@Override
	public long getExpire(String token) {
		return redisTemplate.getExpire(jwtProperties.getOnlineKey() + token, TimeUnit.MILLISECONDS);
	}

	@Override
	public void setExpire(String token, long renew, TimeUnit milliseconds) {
		redisTemplate.expire(jwtProperties.getOnlineKey() + token, renew, milliseconds);
	}
}
