package com.ruoyi.flowable.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.domain.BaseService;
import com.ruoyi.flowable.domain.FlowableForm;

/**
 * 流程表单Service
 *
 * @author kubilewang
 */
public interface FlowableFormService extends BaseService<FlowableForm> {
    /**
     * 分页查询流程表单
     *
     * @param page
     * @param flowableForm
     * @return
     */
    IPage<FlowableForm> list(IPage<FlowableForm> page, FlowableForm flowableForm);
}
