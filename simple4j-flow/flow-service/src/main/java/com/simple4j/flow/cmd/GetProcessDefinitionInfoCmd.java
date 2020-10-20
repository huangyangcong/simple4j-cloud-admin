package com.simple4j.flow.cmd;

import java.io.Serializable;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.repository.ProcessDefinition;

/**
 * @author 庄金明
 * @date 2020年3月24日
 */
public class GetProcessDefinitionInfoCmd implements Command<ProcessDefinition>, Serializable {

    private static final long serialVersionUID = 1L;

    protected String processDefinitionId;
    protected String processDefinitionKey;
    protected String tenantId;

    public GetProcessDefinitionInfoCmd(String processDefinitionId, String processDefinitionKey, String tenantId) {
        this.processDefinitionId = processDefinitionId;
        this.processDefinitionKey = processDefinitionKey;
        this.tenantId = tenantId;
    }

    @Override
    public ProcessDefinition execute(CommandContext commandContext) {
        ProcessEngineConfigurationImpl processEngineConfiguration =
                CommandContextUtil.getProcessEngineConfiguration(commandContext);
        ProcessDefinitionEntityManager processDefinitionEntityManager =
                processEngineConfiguration.getProcessDefinitionEntityManager();
        // Find the process definition
        ProcessDefinition processDefinition = null;
        if (processDefinitionId != null) {
            processDefinition = processDefinitionEntityManager.findById(processDefinitionId);
            if (processDefinition == null) {
                throw new FlowableObjectNotFoundException("No process definition found for id = '" + processDefinitionId + "'", ProcessDefinition.class);
            }
        } else if (processDefinitionKey != null && StrUtil.isEmpty(tenantId)) {
            processDefinition = processDefinitionEntityManager.findLatestProcessDefinitionByKey(processDefinitionKey);
            if (processDefinition == null) {
                throw new FlowableObjectNotFoundException("No process definition found for key '" + processDefinitionKey + "'", ProcessDefinition.class);
            }
        } else if (processDefinitionKey != null && StrUtil.isNotEmpty(tenantId)) {
            processDefinition =
                    processDefinitionEntityManager.findLatestProcessDefinitionByKeyAndTenantId(processDefinitionKey,
                            tenantId);
            if (processDefinition == null) {
                if (processEngineConfiguration.isFallbackToDefaultTenant()) {
                    if (StringUtils.isNotEmpty(processEngineConfiguration.getDefaultTenantValue())) {
                        processDefinition =
                                processDefinitionEntityManager.findLatestProcessDefinitionByKeyAndTenantId(processDefinitionKey, processEngineConfiguration.getDefaultTenantValue());
                    } else {
                        processDefinition =
                                processDefinitionEntityManager.findLatestProcessDefinitionByKey(processDefinitionKey);
                    }
                    if (processDefinition == null) {
                        throw new FlowableObjectNotFoundException("No process definition found for key '" + processDefinitionKey + "'. Fallback to default tenant was also applied", ProcessDefinition.class);
                    }
                } else {
                    throw new FlowableObjectNotFoundException("Process definition with key '" + processDefinitionKey + "' and tenantId '" + tenantId + "' was not found", ProcessDefinition.class);
                }
            }
        } else {
            throw new FlowableIllegalArgumentException("ProcessDefinitionKey and processDefinitionId are null");
        }
        return processDefinition;
    }
}