package com.ruoyi.flowable.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author kubilewang
 * @date 2020年8月30日
 */
@Data
public class ModelResponse {
    private String id;

    private String name;
    private String key;
    private String category;
    private String description;
    private String tenantId;
    private String editor;

    private Date createTime;
    private Date lastUpdateTime;
    private Boolean deployed;
    private Integer version;
}
