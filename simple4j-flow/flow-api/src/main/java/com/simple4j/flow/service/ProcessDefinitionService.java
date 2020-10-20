package com.simple4j.flow.service;

import javax.servlet.http.HttpServletRequest;

import com.simple4j.flow.request.IdentityRequest;
import com.simple4j.flow.request.ProcessDefinitionRequest;
import org.flowable.engine.repository.ProcessDefinition;


/**
 * @author 庄金明
 * @date 2020年3月24日
 */
public interface ProcessDefinitionService {

    /**
     * 查询单一流程定义
     * /repository/process-definitions/{processDefinitionId}
     * @param processDefinitionId
     * @return
     */
    ProcessDefinition getProcessDefinitionById(String processDefinitionId);

    /**
     * 删除流程定义
     * /repository/deployments/{deploymentId}
     * @param processDefinitionId
     * @param cascade
     */
    void delete(String processDefinitionId, Boolean cascade);

    /**
     * 激活流程定义
     * /repository/process-definitions/{processDefinitionId}
     * @param actionRequest
     */
    void activate(ProcessDefinitionRequest actionRequest);

    /**
     * 挂起流程定义
     * /repository/process-definitions/{processDefinitionId}
     * @param actionRequest
     */
    void suspend(ProcessDefinitionRequest actionRequest);

    /**
     * 导入流程定义
     * /app-modeler/rest/models/{modelId}/newversion
     * @param tenantId
     * @param request
     */
    void doImport(String tenantId, HttpServletRequest request);

    /**
     * 保存流程授权
     * /repository/process-definitions/{processDefinitionId}/identitylinks
     * @param identityRequest
     */
    void saveProcessDefinitionIdentityLink(IdentityRequest identityRequest);

    /**
     * 删除流程授权
	 * /repository/process-definitions/{processDefinitionId}/identitylinks/{family}/{identityId}
     * @param processDefinitionId
     * @param identityId
     * @param type
     */
    void deleteProcessDefinitionIdentityLink(String processDefinitionId, String identityId, String type);
}
