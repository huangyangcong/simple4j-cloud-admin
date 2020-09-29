package com.ruoyi.flowable.vo;

import java.util.Map;

import lombok.Data;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@Data
public class ProcessInstanceRequest {
    private String processDefinitionId;
    private String processDefinitionKey;
    private String tenantId;
    private String businessKey;
    private Map<String, Object> values;
    private String processInstanceId;
}
