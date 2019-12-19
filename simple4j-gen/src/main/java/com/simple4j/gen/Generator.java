package com.simple4j.gen;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Generator {
	public static void main(String[] args) {
		String packageName = "com.simple4j";
		String module = "msg";
		String apiComment = "消息接口";
		String author = "hyc";
		Map<String, String> methods = Maps.newHashMap();
		methods.put("sendMailMsg", "发送邮件消息");
		methods.put("sendSmsMsg", "发送短信消息");
		methods.put("sendWechatMsg", "发送公众号消息");
		methods.put("sendDingdingMsg", "发送钉钉消息");
		methods.put("sendJpushMsg", "发送极光推送消息");


		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("package", packageName);
		dataMap.put("module", module);
		dataMap.put("api_comment", apiComment);
		dataMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		dataMap.put("author", author);
		dataMap.put("methods", methods);

		File serviceFilePath = new File((packageName + "." + module + ".service").replaceAll("\\.", File.separator));
		if (!serviceFilePath.exists()) {
			serviceFilePath.mkdirs();
		}
		File requestFilePath = new File((packageName + "." + module + ".request").replaceAll("\\.", File.separator));
		if (!requestFilePath.exists()) {
			requestFilePath.mkdirs();
		}
		File responseFilePath = new File((packageName + "." + module + ".response").replaceAll("\\.", File.separator));
		if (!responseFilePath.exists()) {
			responseFilePath.mkdirs();
		}
		File enumsFilePath = new File((packageName + "." + module + ".enums").replaceAll("\\.", File.separator));
		if (!enumsFilePath.exists()) {
			enumsFilePath.mkdirs();
		}
		// step1 创建freeMarker配置实例
		Configuration configuration = new Configuration();
		Writer out = null;
		try {
			// step2 获取模版路径
			ClassPathResource classPathResource = new ClassPathResource("");
			configuration.setDirectoryForTemplateLoading(classPathResource.getFile());

			// step4 加载模版文件
			Template apiTemplate = configuration.getTemplate("api.java.ftl");
			// step5 生成数据
			File docFile = new File(serviceFilePath.getPath() + File.separator + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, module) + "Service.java");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
			// step6 输出文件
			apiTemplate.process(dataMap, out);

			Template requestTemplate = configuration.getTemplate("request.java.ftl");
			Template responseTemplate = configuration.getTemplate("response.java.ftl");
			for (Map.Entry<String, String> method : methods.entrySet()) {
				String methodName = method.getKey();
				String methodComment = method.getValue();
				dataMap.put("methodName", methodName);
				dataMap.put("request_comment", methodComment + "请求体");
				dataMap.put("response_comment", methodComment + "响应体");
				// step5 生成数据
				docFile = new File(requestFilePath.getPath() + File.separator + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, methodName) + "Request.java");
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
				// step6 输出文件
				requestTemplate.process(dataMap, out);


				docFile = new File(responseFilePath.getPath() + File.separator + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, methodName) + "Response.java");
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
				// step6 输出文件
				responseTemplate.process(dataMap, out);
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.flush();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
