package com.simple4j.flow.request;

import lombok.Data;

/**
 * @author hyc
 */
@Data
public class CcToVo {
	private String userId;
	private String userName;

	@Override
	public String toString(){
		return String.format("%s[%s]",this.userName,this.userId);
	}
}
