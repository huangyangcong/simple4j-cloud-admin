package com.simple4j.msg.service;


import com.simple4j.api.base.BusinessException;
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
	SendMailMsgResponse sendMailMsg(SendMailMsgRequest request) throws BusinessException;

	/**
	 * 发送钉钉消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	SendDingdingMsgResponse sendDingdingMsg(SendDingdingMsgRequest request) throws BusinessException;

	/**
	 * 发送极光推送消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	SendJpushMsgResponse sendJpushMsg(SendJpushMsgRequest request) throws BusinessException;

	/**
	 * 发送短信消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	SendSmsMsgResponse sendSmsMsg(SendSmsMsgRequest request) throws BusinessException;

	/**
	 * 发送公众号消息
	 *
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	SendWechatMsgResponse sendWechatMsg(SendWechatMsgRequest request) throws BusinessException;
}
