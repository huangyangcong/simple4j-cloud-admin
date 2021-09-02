package com.simple4j.auth.service;

import com.simple4j.auth.entity.AuthToken;
import com.simple4j.auth.entity.User;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IAuthTokenService {

    void saveAuthToken(AuthToken authToken);
}
