package com.simple4j.system.mapstruct;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.User;
import com.simple4j.system.excel.UserExcelImport;
import com.simple4j.system.request.UserAddRequest;
import com.simple4j.system.response.UserDetailResponse;
import com.simple4j.system.response.UserInfo;
import com.simple4j.system.response.UserLoginResponse;
import com.simple4j.system.service.IDictService;
import com.simple4j.system.service.IUserDeptService;
import com.simple4j.system.service.IUserPostService;
import com.simple4j.system.service.IUserRoleService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapStruct {

	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IUserDeptService userDeptService;
	@Autowired
	private IUserPostService userPostService;
	@Autowired
	private IDictService dictService;

	@AfterMapping
	protected void fillUser(User user, @MappingTarget UserDetailResponse userDetailResponse) {
		String userId = user.getId();
		Integer sex = user.getSex();
		userDetailResponse.setRoles(userRoleService.getRoleIds(userId));
		userDetailResponse.setDepts(userDeptService.getDeptIds(userId));
		userDetailResponse.setPosts(userPostService.getPostIds(userId));
		String sexName = dictService.getValue("sex", sex);
		userDetailResponse.setSexName(sexName);
	}

	public abstract UserLoginResponse toVo(UserInfo userInfo);

	public abstract UserDetailResponse toVo(User user);

	public abstract Page<UserDetailResponse> toVo(Page<User> user);

	public abstract List<UserDetailResponse> toVo(List<User> user);

	public abstract User toPo(UserExcelImport userExcel);

	public abstract UserInfo toUserInfo(User user);

	public abstract User toPo(UserAddRequest userAddRequest);

	public abstract List<UserExcelImport> toExcel(List<UserDetailResponse> users);
}
