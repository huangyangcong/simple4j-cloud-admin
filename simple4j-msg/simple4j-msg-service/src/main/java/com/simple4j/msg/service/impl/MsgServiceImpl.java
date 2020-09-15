package com.simple4j.msg.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.simple4j.common.api.expection.BusinessException;
import com.simple4j.msg.request.*;
import com.simple4j.msg.response.*;
import com.simple4j.msg.service.MsgService;
import com.simple4j.msg.service.entity.MsgTemplate;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.MailSender;
import reactor.core.publisher.Mono;

@Service(version = "1.0.0")
public class MsgServiceImpl implements MsgService {
	@Autowired
	private JPushClient jPushClient;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	@Override
	public ApiResponse<SendMailMsgResponse> sendMailMsg(ApiRequest<SendMailMsgRequest> request) throws BusinessException {
		return mailSender;
	}

	@Override
	public ApiResponse<SendDingdingMsgResponse> sendDingdingMsg(ApiRequest<SendDingdingMsgRequest> request) throws BusinessException {
		return null;
	}

	@Override
	public ApiResponse<SendJpushMsgResponse> sendJpushMsg(ApiRequest<SendJpushMsgRequest> request) throws BusinessException {
		Platform platform = Platform.newBuilder()
				.addDeviceType()
				.build();
		Message message = Message.newBuilder()
				.setMsgContent()
				.setTitle()
				.setContentType()
				.addExtra("extra", )
				.build();
		Notification notification = Notification.newBuilder()
				.setAlert()
				.build();
		Options options = Options.newBuilder().build();

		Audience audience = Audience.registrationId();
		PushPayload pushPayload = PushPayload.newBuilder()
				.setPlatform(platform)
				.setAudience(audience)
				.setMessage(message)
				.setNotification(notification)
				.setOptions(options)
				.build();
		try {
			PushResult pushResult = jPushClient.sendPush(pushPayload);
		} catch (APIConnectionException e) {
			return ApiResponse.error(new SendJpushMsgResponse());
		} catch (APIRequestException e) {
			return ApiResponse.error(new SendJpushMsgResponse());
		}
		return ApiResponse.success(new SendJpushMsgResponse());
	}

	@Override
	public ApiResponse<SendSmsMsgResponse> sendSmsMsg(ApiRequest<SendSmsMsgRequest> request) throws BusinessException {
		return null;
	}

	@Override
	public ApiResponse<SendWechatMsgResponse> sendWechatMsg(ApiRequest<SendWechatMsgRequest> request) throws BusinessException {
		return null;
	}

	private Mono<MsgTemplate> getTemplate(String tempId) {
		return mongoTemplate.findOne(Query.query(Criteria.where("temp_id").is(tempId)), MsgTemplate.class);
	}
}
