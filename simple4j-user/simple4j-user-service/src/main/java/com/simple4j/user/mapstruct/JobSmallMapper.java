package com.simple4j.user.mapstruct;

import com.simple4j.common.base.BaseMapStructMapper;
import com.simple4j.user.dto.JobSmallDto;
import com.simple4j.user.dao.dataobject.Job;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hyc
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSmallMapper extends BaseMapStructMapper<JobSmallDto, Job> {

}