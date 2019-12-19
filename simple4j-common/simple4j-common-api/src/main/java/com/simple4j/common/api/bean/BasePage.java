package com.simple4j.common.api.bean;

import java.io.Serializable;

/**
 * @author hyc
 * 基础分页查询
 */
public class BasePage implements Serializable {
	private static final long serialVersionUID = -5643811922525097029L;

	private int pageNo = 1;

	private int pageSize = 10;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
