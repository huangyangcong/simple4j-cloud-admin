package com.simple4j.user.mapstruct;

import java.util.List;

import com.simple4j.user.base.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.Role;
import com.simple4j.user.response.RoleDetailResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapStruct {

	RoleDetailResponse toVo(Role detail);

	Page<RoleDetailResponse> toVo(Page<Role> page);

	List<RoleDetailResponse> toVo(List<Role> list);

}
