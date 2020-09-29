package com.ruoyi.flowable.vo;

import lombok.Data;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@Data
public class IdentityRequest {
    private String processDefinitionId;
    private String taskId;
    private String identityId;
    private String identityType;
}
