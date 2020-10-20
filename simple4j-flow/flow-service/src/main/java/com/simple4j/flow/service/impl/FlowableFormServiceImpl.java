package com.simple4j.flow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.flow.entity.FlowableForm;
import com.simple4j.flow.mapper.FlowableFormMapper;
import com.simple4j.flow.mapstruct.FlowableFormMapStruct;
import com.simple4j.flow.request.FlowableFormDetailResponse;
import com.simple4j.flow.request.FlowableFormPageRequest;
import com.simple4j.flow.service.FlowableFormService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

/**
 * 流程Service
 *
 * @author 庄金明
 */
@Service
@RequiredArgsConstructor
public class FlowableFormServiceImpl  implements FlowableFormService {

	private final FlowableFormMapStruct flowableFormMapStruct;
	private final FlowableFormMapper flowableFormMapper;

	@Override
	public Page<FlowableFormDetailResponse> list(FlowableFormPageRequest flowableFormPageRequest) {
		IPage<FlowableForm> page = flowableFormMapper.page(
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
						flowableFormPageRequest.getPageNo(), flowableFormPageRequest.getPageSize()),
				Wrappers.lambdaQuery());
		Page<FlowableForm> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(),
				page.getRecords());
		return flowableFormMapStruct.toVo(pages);
	}
}
