package com.simple4j.system.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据传输对象实体类
 *
 * @author Chill
 */
@Data
public class MenuDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String alias;
	private String path;
}
