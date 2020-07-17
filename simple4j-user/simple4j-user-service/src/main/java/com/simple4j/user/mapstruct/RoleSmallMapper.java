package com.simple4j.user.mapstruct;

import com.simple4j.common.base.BaseMapStructMapper;
import com.simple4j.user.dto.RoleSmallDto;
import com.simple4j.user.dao.dataobject.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author hyc
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapStructMapper<RoleSmallDto, Role> {

}
