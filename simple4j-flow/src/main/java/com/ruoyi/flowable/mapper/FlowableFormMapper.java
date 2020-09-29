package com.ruoyi.flowable.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.flowable.domain.FlowableForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程表单Mapper
 *
 * @author kubilewang
 */
public interface FlowableFormMapper extends BaseMapper<FlowableForm> {
    /**
     * 查询流程表单列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<FlowableForm> list(IPage<FlowableForm> page, @Param("entity") FlowableForm entity);
}
