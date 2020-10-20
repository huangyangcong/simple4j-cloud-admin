package com.simple4j.flow.mapstruct;

import com.simple4j.api.base.Page;
import com.simple4j.flow.entity.FlowableForm;
import com.simple4j.flow.request.FlowableFormDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author hyc
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlowableFormMapStruct {
	FlowableFormDetailResponse toVo(FlowableForm po);

	Page<FlowableFormDetailResponse> toVo(Page<FlowableForm> po);
}
