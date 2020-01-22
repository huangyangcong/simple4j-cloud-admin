package com.simple4j.user.service;


import com.simple4j.common.api.bean.ApiRequest;
import com.simple4j.common.api.bean.ApiResponse;
import com.simple4j.common.api.expection.BusinessException;

    import com.simple4j.user.request.GenerateVerificationCodeRequest;
    import com.simple4j.user.response.GenerateVerificationCodeResponse;

/**
* <p>
* 登录接口
* </p>
*
* @author hyc
* @since 2019-12-19
*/
public interface LoginService {

    /**
    * 生成验证码
    *
    * @param request
    * @return
    * @throws BusinessException
    */
    ApiResponse<GenerateVerificationCodeResponse> generateVerificationCode(ApiRequest<GenerateVerificationCodeRequest> request) throws BusinessException;

}
