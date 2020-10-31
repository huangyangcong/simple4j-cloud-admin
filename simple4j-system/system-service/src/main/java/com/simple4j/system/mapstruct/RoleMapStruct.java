package com.simple4j.system.mapstruct;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.Role;
import com.simple4j.system.request.RoleAddOrUpdateRequest;
import com.simple4j.system.response.RoleDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapStruct {

  RoleDetailResponse toVo(Role detail);

  Role toPo(RoleAddOrUpdateRequest vo);

  Page<RoleDetailResponse> toVo(Page<Role> page);

  List<RoleDetailResponse> toVo(List<Role> list);
}
