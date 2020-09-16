package com.simple4j.user.mapstruct;

import com.simple4j.user.base.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.User;
import com.simple4j.user.response.UserInfo;
import com.simple4j.user.excel.UserExcelImport;
import com.simple4j.user.request.UserAddRequest;
import com.simple4j.user.response.UserDetailResponse;
import com.simple4j.user.response.UserLoginResponse;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapStruct {

	UserLoginResponse toVo(UserInfo userInfo);

	UserDetailResponse toVo(User user);

	Page<UserDetailResponse> toVo(Page<User> user);

	List<UserDetailResponse> toVo(List<User> user);

	User toPo(UserExcelImport userExcel);

	UserInfo toUserInfo(User user);

	User toPo(UserAddRequest userAddRequest);

	List<UserExcelImport> toExcel(List<UserDetailResponse> users);
}
