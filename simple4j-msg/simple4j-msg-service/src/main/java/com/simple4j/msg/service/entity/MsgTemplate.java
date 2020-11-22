package com.simple4j.msg.service.entity;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class MsgTemplate {

	@Field("temp_type")
	private String tempType;

	@Field("temp_id")
	private String tempId;

	@Field("temp_content")
	private String tempContent;

	@Field("temp_title")
	private String tempTitle;

	@Field("temp_apply_reason")
	private String applyReason;

	@Field("apply_status")
	private int applyStatus;
}
