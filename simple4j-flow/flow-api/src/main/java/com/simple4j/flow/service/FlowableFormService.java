package com.simple4j.flow.service;


import com.simple4j.api.base.Page;
import com.simple4j.flow.request.FlowableFormDetailResponse;
import com.simple4j.flow.request.FlowableFormPageRequest;

/**
 * 流程表单Service
 *
 * @author 庄金明
 */
public interface FlowableFormService {
    /**
     * 分页查询流程表单
     *
     * @param flowableFormPageRequest
     * @return
     */
    Page<FlowableFormDetailResponse> list(FlowableFormPageRequest flowableFormPageRequest);

}
