package com.simple4j.flow.request;

import lombok.Data;

@Data
public class ModelQueryRequest{
	private Integer current = 1;
	private Integer size = 10;
	private String tenantId;
	private String orderRule;
	private String userId;

	private String modelId;
    private String modelCategory;
    private String modelName;
    private String modelKey;
    private Integer modelVersion;
    private Boolean latestVersion = false;
    private Boolean deployed;
    private String deploymentId;
}
