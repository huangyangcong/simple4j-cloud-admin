package com.simple4j.pay.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.IpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.unionpay.UnionPayApi;
import com.ijpay.unionpay.UnionPayApiConfig;
import com.ijpay.unionpay.enums.ServiceEnum;
import com.ijpay.unionpay.model.*;
import com.simple4j.web.bean.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/unionPay")
@Slf4j
public class UnionPayController {

	@Autowired
	UnionPayApiConfig unionPayBean;

	@RequestMapping("")
	@ResponseBody
	public String index() {
		log.info("欢迎使用 IJPay 中的云闪付	- by Javen");
		return "欢迎使用 IJPay 中的云闪付	- by Javen";
	}

	@RequestMapping("test")
	@ResponseBody
	public UnionPayApiConfig test() {
		return unionPayBean;
	}

	/**
	 * 刷卡支付
	 */
	@RequestMapping(value = "/microPay", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse microPay(HttpServletRequest request, HttpServletResponse response) {
		try {
			String authCode = request.getParameter("authCode");
			String totalFee = request.getParameter("totalFee");

			String ip = IpKit.getRealIp(request);
			if (StrUtil.isBlank(ip)) {
				ip = "127.0.0.1";
			}

			Map<String, String> params = MicroPayModel.builder()
				.service(ServiceEnum.MICRO_PAY.toString())
				.mch_id(unionPayBean.getMchId())
				.out_trade_no(WxPayKit.generateStr())
				.body("IJPay 云闪付测试")
				.attach("聚合支付 SDK")
				.total_fee(totalFee)
				.mch_create_ip(ip)
				.auth_code(authCode)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			log.info("请求参数:" + JSONUtil.toJsonStr(params));

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			String returnCode = result.get("status");
			String resultCode = result.get("result_code");
			String errMsg = result.get("err_msg");
			String errCode = result.get("err_code");

			if (!"0".equals(returnCode) || !"0".equals(resultCode)) {
				return ApiResponse.businessEx("errCode:" + errCode + " errMsg:" + errMsg);
			}
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/queryOrder", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse query(@RequestParam(value = "transactionId", required = false) String transactionId,
							 @RequestParam(value = "outTradeNo", required = false) String outTradeNo) {
		try {

			if (StrUtil.isEmpty(transactionId) && StrUtil.isEmpty(outTradeNo)) {
				return ApiResponse.businessEx("out_trade_no transaction_id 不能同时为空");
			}

			Map<String, String> params = OrderQueryModel.builder()
				.service(ServiceEnum.QUERY.toString())
				.mch_id(unionPayBean.getMchId())
				.out_trade_no(outTradeNo)
				.transaction_id(transactionId)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			if (!WxPayKit.verifyNotify(result, unionPayBean.getApiKey(), SignType.MD5)) {
				return ApiResponse.businessEx("签名异常");
			}
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/microPayReverse", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse microPayReverse(@RequestParam("outTradeNo") String outTradeNo) {
		try {

			Map<String, String> params = OrderQueryModel.builder()
				.service(ServiceEnum.MICRO_PAY_REVERSE.toString())
				.mch_id(unionPayBean.getMchId())
				.out_trade_no(outTradeNo)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/refund", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse refund(@RequestParam(value = "transactionId", required = false) String transactionId,
							  @RequestParam(value = "outTradeNo", required = false) String outTradeNo,
							  @RequestParam(value = "totalFee") String totalFee,
							  @RequestParam(value = "refundFee") String refundFee) {
		try {

			if (StrUtil.isEmpty(transactionId) && StrUtil.isEmpty(outTradeNo)) {
				return ApiResponse.businessEx("out_trade_no transaction_id 不能同时为空");
			}

			Map<String, String> params = RefundModel.builder()
				.service(ServiceEnum.REFUND.toString())
				.mch_id(unionPayBean.getMchId())
				.out_trade_no(outTradeNo)
				.transaction_id(transactionId)
				.out_refund_no(transactionId)
				.total_fee(totalFee)
				.refund_fee(refundFee)
				.op_user_id(unionPayBean.getMchId())
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/queryRefund", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse queryRefund(@RequestParam(value = "transactionId", required = false) String transactionId,
								   @RequestParam(value = "outTradeNo", required = false) String outTradeNo,
								   @RequestParam(value = "outRefundNo", required = false) String outRefundNo,
								   @RequestParam(value = "refundId", required = false) String refundId) {
		try {

			if (StrUtil.isEmpty(transactionId) && StrUtil.isEmpty(outTradeNo)) {
				return ApiResponse.businessEx("out_trade_no transaction_id 不能同时为空");
			}

			if (StrUtil.isEmpty(outRefundNo) && StrUtil.isEmpty(refundId)) {
				return ApiResponse.businessEx("out_refund_no refund_id 不能同时为空");
			}

			Map<String, String> params = RefundQueryModel.builder()
				.service(ServiceEnum.REFUND_QUERY.toString())
				.mch_id(unionPayBean.getMchId())
				.out_trade_no(outTradeNo)
				.transaction_id(transactionId)
				.out_refund_no(outRefundNo)
				.refund_id(refundId)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/authCodeToOpenId", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse authCodeToOpenId(@RequestParam(value = "code") String code) {
		try {

			Map<String, String> params = AuthCodeToOpenIdModel.builder()
				.service(ServiceEnum.AUTH_CODE_TO_OPENID.toString())
				.mch_id(unionPayBean.getMchId())
				.auth_code(code)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/native", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse nativePay(HttpServletRequest request,
								 @RequestParam(value = "totalFee", defaultValue = "1") String totalFee) {
		try {
			String notifyUrl = unionPayBean.getDomain().concat("/unionPay/payNotify");

			String ip = IpKit.getRealIp(request);
			if (StrUtil.isBlank(ip)) {
				ip = "127.0.0.1";
			}

			Map<String, String> params = UnifiedOrderModel.builder()
				.service(ServiceEnum.NATIVE.toString())
				.mch_id(unionPayBean.getMchId())
				.out_trade_no(WxPayKit.generateStr())
				.body("IJPay 云闪付-扫码支付")
				.attach("聚合支付 SDK")
				.total_fee(totalFee)
				.mch_create_ip(ip)
				.notify_url(notifyUrl)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/wxJsPay", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse wxJsPay(HttpServletRequest request,
							   @RequestParam(value = "totalFee", defaultValue = "1") String totalFee,
							   @RequestParam(value = "openId") String openId) {
		try {

			String notifyUrl = unionPayBean.getDomain().concat("/unionPay/payNotify");

			String ip = IpKit.getRealIp(request);
			if (ip.contains(",")) {
				ip = ip.split(",")[0];
			}
			if (StrUtil.isBlank(ip)) {
				ip = "127.0.0.1";
			}

			Map<String, String> params = UnifiedOrderModel.builder()
				.service(ServiceEnum.WEI_XIN_JS_PAY.toString())
				.mch_id(unionPayBean.getMchId())
				.is_raw("1")
				.out_trade_no(WxPayKit.generateStr())
				.body("IJPay 云闪付-微信公众号/小程序支付")
				.sub_openid(openId)
//                    .sub_appid("appId")
				.attach("聚合支付 SDK")
				.total_fee(totalFee)
				.mch_create_ip(ip)
				.notify_url(notifyUrl)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			System.out.println(params);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/wxApp", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse wxApp(HttpServletRequest request,
							 @RequestParam(value = "totalFee", defaultValue = "1") String totalFee,
							 @RequestParam(value = "appId") String appId) {
		try {

			String notifyUrl = unionPayBean.getDomain().concat("/unionPay/payNotify");

			String ip = IpKit.getRealIp(request);
			if (ip.contains(",")) {
				ip = ip.split(",")[0];
			}
			if (StrUtil.isBlank(ip)) {
				ip = "127.0.0.1";
			}

			Map<String, String> params = UnifiedOrderModel.builder()
				.service(ServiceEnum.WEI_XIN_APP_PAY.toString())
				.mch_id(unionPayBean.getMchId())
				.appid(appId)
				.out_trade_no(WxPayKit.generateStr())
				.body("IJPay 云闪付-微信 App 支付")
				.attach("聚合支付 SDK")
				.total_fee(totalFee)
				.mch_create_ip(ip)
				.notify_url(notifyUrl)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			System.out.println(params);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			if (!WxPayKit.verifyNotify(result, unionPayBean.getApiKey(), SignType.MD5)) {
				return ApiResponse.businessEx("签名异常");
			}
			String status = result.get("status");
			String resultCode = result.get("result_code");
			if (!"0".equals(status) && !"0".equals(resultCode)) {
				return ApiResponse.businessEx(result.get("err_msg"));
			}

			return ApiResponse.ok(result.get("pay_info"));
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/aliJsPay", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse aliJsPay(HttpServletRequest request,
								@RequestParam(value = "totalFee", defaultValue = "1") String totalFee,
								@RequestParam(value = "buyerLogonId", required = false) String buyerLogonId,
								@RequestParam(value = "buyerId", required = false) String buyerId) {
		try {
			if (StrUtil.isEmpty(buyerLogonId) && StrUtil.isEmpty(buyerId)) {
				return ApiResponse.businessEx("buyer_logon_id buyer_id 不能同时为空");
			}

			String notifyUrl = unionPayBean.getDomain().concat("/unionPay/payNotify");

			String ip = IpKit.getRealIp(request);
			if (ip.contains(",")) {
				ip = ip.split(",")[0];
			}
			if (StrUtil.isBlank(ip)) {
				ip = "127.0.0.1";
			}

			Map<String, String> params = UnifiedOrderModel.builder()
				.service(ServiceEnum.ALI_PAY_JS_PAY.toString())
				.mch_id(unionPayBean.getMchId())
				.out_trade_no(WxPayKit.generateStr())
				.body("IJPay 云闪付-支付宝服务窗口")
				.attach("聚合支付 SDK")
				.total_fee(totalFee)
				.mch_create_ip(ip)
				.notify_url(notifyUrl)
				.nonce_str(WxPayKit.generateStr())
				.buyer_id(buyerId)
				.buyer_logon_id(buyerLogonId)
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			System.out.println(params);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}


	@RequestMapping(value = "/unionPayUserAuth", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void unionPayUserAuth(HttpServletResponse response) throws IOException {
		String notifyUrl = unionPayBean.getDomain().concat("/unionPay/callBack");
		String authUrl = UnionPayApi.buildAuthUrl(notifyUrl);
		log.info("authUrl:" + authUrl);
		response.sendRedirect(authUrl);
	}

	@RequestMapping(value = "/callBack", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, String> unionPayCall(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (String name : requestParams.keySet()) {
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;
	}


	@RequestMapping(value = "/unionJsPay", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse unionJsPay(HttpServletRequest request,
								  @RequestParam(value = "totalFee", defaultValue = "1") String totalFee,
								  @RequestParam(value = "userId", required = false) String userId) {
		try {

			String notifyUrl = unionPayBean.getDomain().concat("/unionPay/payNotify");

			String ip = IpKit.getRealIp(request);
			if (ip.contains(",")) {
				ip = ip.split(",")[0];
			}
			if (StrUtil.isBlank(ip)) {
				ip = "127.0.0.1";
			}

			Map<String, String> params = UnifiedOrderModel.builder()
				.service(ServiceEnum.UNION_JS_PAY.toString())
				.mch_id(unionPayBean.getMchId())
				.out_trade_no(WxPayKit.generateStr())
				.body("IJPay 云闪付-银联JS支付")
				.user_id(userId)
				.customer_ip(ip)
				.attach("聚合支付 SDK")
				.total_fee(totalFee)
				.mch_create_ip(ip)
				.notify_url(notifyUrl)
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			System.out.println(params);

			String xmlResult = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("xmlResult:" + xmlResult);
			Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	@RequestMapping(value = "/billDownload", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse billDownload(@RequestParam(value = "billDate", defaultValue = "20200325") String billDate,
									@RequestParam(value = "billType", defaultValue = "ALL") String billType) {
		try {

			Map<String, String> params = BillDownloadModel.builder()
				.service(ServiceEnum.BILL_MERCHANT.toString())
				.bill_date(billDate)
				.bill_type(billType)
				.mch_id(unionPayBean.getMchId())
				.nonce_str(WxPayKit.generateStr())
				.build()
				.createSign(unionPayBean.getApiKey(), SignType.MD5);

			System.out.println(params);

			String result = UnionPayApi.execution(unionPayBean.getServerUrl(), params);
			log.info("result:" + result);
			return ApiResponse.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.businessEx(e.getMessage());
		}
	}

	/**
	 * 异步通知
	 */
	@RequestMapping(value = "/payNotify", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String payNotify(HttpServletRequest request) {
		String xmlMsg = HttpKit.readData(request);
		log.info("支付通知=" + xmlMsg);
		Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);

		String status = params.get("status");
		String returnCode = params.get("result_code");

		log.info(status + " " + returnCode);

		if ("0".equals(status) && "0".equals(returnCode)) {
			// 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
			// 注意此处签名方式需与统一下单的签名类型一致
			if (WxPayKit.verifyNotify(params, unionPayBean.getApiKey(), SignType.MD5)) {
				log.info("支付成功....");
				// 更新订单信息
				// 发送通知等
				return "success";
			}
		}
		return "fail";
	}
}
