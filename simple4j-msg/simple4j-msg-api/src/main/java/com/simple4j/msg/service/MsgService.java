package com.simple4j.msg.service;


import com.simple4j.common.api.expection.BusinessException;
import com.simple4j.msg.request.*;
import com.simple4j.msg.response.*;

/**
 * <p>
 * 消息接口
 * </p>
 *
 * @author hyc
 * @since 2019-12-09
 */
public interface MsgService {

	/**
	 * 发送邮件消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	ApiResponse<SendMailMsgResponse> sendMailMsg(ApiRequest<SendMailMsgRequest> request) throws BusinessException;

	/**
	 * 发送钉钉消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	ApiResponse<SendDingdingMsgResponse> sendDingdingMsg(ApiRequest<SendDingdingMsgRequest> request) throws BusinessException;

	/**
	 * 发送极光推送消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	ApiResponse<SendJpushMsgResponse> sendJpushMsg(ApiRequest<SendJpushMsgRequest> request) throws BusinessException;

	/**
	 * 发送短信消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	ApiResponse<SendSmsMsgResponse> sendSmsMsg(ApiRequest<SendSmsMsgRequest> request) throws BusinessException;

	/**
	 * 发送公众号消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	ApiResponse<SendWechatMsgResponse> sendWechatMsg(ApiRequest<SendWechatMsgRequest> request) throws BusinessException;
}
