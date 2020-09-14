package com.simple4j.user.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuRoutersResponse {

	/**
	 *
	 */
	private String path;

	private List<String> authorities;
}
