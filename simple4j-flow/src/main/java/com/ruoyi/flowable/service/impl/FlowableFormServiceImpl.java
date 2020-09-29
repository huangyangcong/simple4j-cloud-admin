package com.ruoyi.flowable.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.domain.BaseServiceImpl;
import com.ruoyi.flowable.domain.FlowableForm;
import com.ruoyi.flowable.mapper.FlowableFormMapper;
import com.ruoyi.flowable.service.FlowableFormService;
import org.springframework.stereotype.Service;

/**
 * 流程Service
 *
 * @author kubilewang
 */
@Service
public class FlowableFormServiceImpl extends BaseServiceImpl<FlowableFormMapper, FlowableForm> implements FlowableFormService {
    @Override
    public IPage<FlowableForm> list(IPage<FlowableForm> page, FlowableForm flowableForm) {
        return page.setRecords(baseMapper.list(page, flowableForm));
    }
}
