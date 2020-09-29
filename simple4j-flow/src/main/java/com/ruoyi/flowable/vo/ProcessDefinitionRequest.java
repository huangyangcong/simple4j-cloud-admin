package com.ruoyi.flowable.vo;

import java.util.Date;

import lombok.Data;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@Data
public class ProcessDefinitionRequest {
    private String processDefinitionId;
    private boolean includeProcessInstances = false;
    private Date date;
}
