package com.simple4j.user.service.impl;

import com.simple4j.common.api.bean.ApiRequest;
import com.simple4j.common.api.bean.ApiResponse;
import com.simple4j.user.request.DeptQueryAllRequest;
import com.simple4j.user.response.DeptQueryAllResponse;
import com.simple4j.user.service.DeptService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author hyc
 */
@Service(version = "v1.0")
public class DeptServiceImpl implements DeptService {

	@Override
	public ApiResponse<DeptQueryAllResponse> queryAll(ApiRequest<DeptQueryAllRequest> apiRequest) {
		DeptQueryAllRequest de = apiRequest.getBizRequest();
		return null;
	}
}
