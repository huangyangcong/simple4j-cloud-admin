package com.simple4j.user.mapstruct;

import com.simple4j.common.base.BaseMapStructMapper;
import com.simple4j.user.dto.RoleDto;
import com.simple4j.user.dao.dataobject.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author hyc
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapStructMapper<RoleDto, Role> {

}
