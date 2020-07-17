package com.simple4j.user.mapstruct;

import com.simple4j.common.base.BaseMapStructMapper;
import com.simple4j.user.dto.MenuDto;
import com.simple4j.user.dao.dataobject.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author hyc
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapStructMapper<MenuDto, Menu> {
}
