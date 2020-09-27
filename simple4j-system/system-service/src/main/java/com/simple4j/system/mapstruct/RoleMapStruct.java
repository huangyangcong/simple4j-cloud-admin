package com.simple4j.system.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.RoleAddOrUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.system.entity.Role;
import com.simple4j.system.response.RoleDetailResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapStruct {

	RoleDetailResponse toVo(Role detail);

	Role toPo(RoleAddOrUpdateRequest vo);

	Page<RoleDetailResponse> toVo(Page<Role> page);

	List<RoleDetailResponse> toVo(List<Role> list);

}
