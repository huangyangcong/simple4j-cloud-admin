package com.simple4j.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.IUserOauthService;
import lombok.AllArgsConstructor;
import com.simple4j.user.entity.UserOauth;
import com.simple4j.user.mapper.UserOauthMapper;
import com.simple4j.user.service.IUserOauthService;

import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@AllArgsConstructor
public class UserOauthServiceImpl extends ServiceImpl<UserOauthMapper, UserOauth> implements
		IUserOauthService {

}
