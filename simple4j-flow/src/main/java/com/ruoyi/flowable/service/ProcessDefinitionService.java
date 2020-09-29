package com.ruoyi.flowable.service;


import com.ruoyi.flowable.vo.IdentityRequest;
import com.ruoyi.flowable.vo.ProcessDefinitionRequest;
import org.flowable.engine.repository.ProcessDefinition;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
public interface ProcessDefinitionService {

    /**
     * 查询单一流程定义
     *
     * @param processDefinitionId
     * @return
     */
    ProcessDefinition getProcessDefinitionById(String processDefinitionId);

    /**
     * 删除流程定义
     *
     * @param processDefinitionId
     * @param cascade
     */
    void delete(String processDefinitionId, Boolean cascade);

    /**
     * 激活流程定义
     *
     * @param actionRequest
     */
    void activate(ProcessDefinitionRequest actionRequest);

    /**
     * 挂起流程定义
     *
     * @param actionRequest
     */
    void suspend(ProcessDefinitionRequest actionRequest);

    /**
     * 导入流程定义
     *
     * @param tenantId
     * @param request
     */
    void doImport(String tenantId, HttpServletRequest request);

    /**
     * 保存流程授权
     *
     * @param identityRequest
     */
    void saveProcessDefinitionIdentityLink(IdentityRequest identityRequest);

    /**
     * 删除流程授权
     *
     * @param processDefinitionId
     * @param identityId
     * @param type
     */
    void deleteProcessDefinitionIdentityLink(String processDefinitionId, String identityId, String type);
}
