package com.ruoyi.flowable.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@Data
public class TaskRequest {
    private String taskId;
    private String userId;
    private String message;
    private String activityId;
    private String activityName;
    private Map<String, Object> values;
}
