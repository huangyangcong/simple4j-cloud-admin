package com.simple4j.user.mapstruct;

import com.simple4j.common.base.BaseMapStructMapper;
import com.simple4j.user.dto.JobDto;
import com.simple4j.user.dao.dataobject.Job;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hyc
* @date 2019-03-29
*/
@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends BaseMapStructMapper<JobDto, Job> {
}