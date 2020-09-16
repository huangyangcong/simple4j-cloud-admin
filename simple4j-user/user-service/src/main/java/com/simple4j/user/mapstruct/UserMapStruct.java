package com.simple4j.user.mapstruct;

import com.simple4j.user.base.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.User;
import com.simple4j.user.response.UserInfo;
import com.simple4j.user.request.UserExcelImportRequest;
import com.simple4j.user.request.UserAddRequest;
import com.simple4j.user.response.UserDetailResponse;
import com.simple4j.user.response.UserLoginResponse;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapStruct {

	UserLoginResponse toVo(UserInfo userInfo);

	UserDetailResponse toVo(User user);

	User toPo(UserExcelImportRequest userExcel);

	UserInfo toUserInfo(User user);

	Page<UserDetailResponse> toVo(Page<User> user);

	User toPo(UserAddRequest userAddRequest);

	List<UserExcelImportRequest> toExcel(List<UserDetailResponse> users);
}
