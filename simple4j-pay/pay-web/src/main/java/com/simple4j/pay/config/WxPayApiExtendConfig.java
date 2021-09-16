package com.simple4j.pay.config;

import com.ijpay.wxpay.WxPayApiConfig;
import lombok.Data;

/**
 * @author hyc
 */
@Data
public class WxPayApiExtendConfig extends WxPayApiConfig {
	private String platformCertPath;
}
