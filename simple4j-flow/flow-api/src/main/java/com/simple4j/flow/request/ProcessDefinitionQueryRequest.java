package com.simple4j.flow.request;

import lombok.Data;

@Data
public class ProcessDefinitionQueryRequest{
	private Integer current = 1;
	private Integer size = 10;
	private String tenantId;
	private String orderRule;
	private String userId;

    private String processDefinitionCategory;
    private String processDefinitionName;
    private String processDefinitionKey;
    private String processDefinitionId;
    private Integer processDefinitionVersion;
    private Boolean suspended = false;
    private Boolean active = false;
    private Boolean latestVersion = false;
    private String startableByUser;

}
