package com.ruoyi.flowable.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@Data
public class TaskUpdateRequest {
    private String id;
    private String name;
    private String assignee;
    private String owner;
    private Date dueDate;
    private String category;
    private String description;
    private int priority;
}
