package com.simple4j.flow.response;

import lombok.Data;

/**
 * @author hyc
 */
@Data
public class ProcessDefinitionVoResponse {
	private String category;
	private String categoryName;
	private String processDefinitionKey;
	private String processDefinitionName;
	private Long count;
}
