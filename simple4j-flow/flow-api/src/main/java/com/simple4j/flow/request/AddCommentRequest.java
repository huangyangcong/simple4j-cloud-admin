package com.simple4j.flow.request;

import lombok.Data;

/**
 * @author hyc
 */
@Data
public class AddCommentRequest {
	private String taskId;
	private String processInstanceId;
	private String userId;
	private String type;
	private String message;
}
