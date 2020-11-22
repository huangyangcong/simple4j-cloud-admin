package com.simple4j.msg.request;

import java.io.Serializable;

/**
 * 发送公众号消息请求体
 *
 * @author hyc
 * @since 2019-12-08
 */
public class SendWechatMsgRequest implements Serializable {

	private String templateId;
}
