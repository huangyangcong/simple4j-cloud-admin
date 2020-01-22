package com.simple4j.gen;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Generator {
	public static void main(String[] args) {
		//包路径
		String packageName = "com.simple4j.user";
		String interfaceName = "LoginService";
		String apiComment = "登录接口";
		String author = "hyc";
		Map<String, String> methods = Maps.newHashMap();
		methods.put("generateVerificationCode", "生成验证码");


		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("package", packageName);
		dataMap.put("interface_name", interfaceName);
		dataMap.put("api_comment", apiComment);
		dataMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		dataMap.put("author", author);
		dataMap.put("methods", methods);


		String enumsFilePath = generatePackagePath(packageName + ".enums");
		// step1 创建freeMarker配置实例
		Configuration configuration = new Configuration();
		try {
			// step2 获取模版路径
			ClassPathResource classPathResource = new ClassPathResource("");
			configuration.setDirectoryForTemplateLoading(classPathResource.getFile());
			generateFile("api.java.ftl", packageName + ".service", interfaceName + "Service.java", dataMap, configuration);

			for (Map.Entry<String, String> method : methods.entrySet()) {
				String methodName = method.getKey();
				String methodComment = method.getValue();
				dataMap.put("methodName", methodName);
				dataMap.put("request_comment", methodComment + "请求体");
				dataMap.put("response_comment", methodComment + "响应体");

				generateFile("request.java.ftl", packageName + ".request", CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, methodName) + "Request.java", dataMap, configuration);
				generateFile("response.java.ftl", packageName + ".response", CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, methodName) + "Response.java", dataMap, configuration);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void generateFile(String templateName, String packagePath, String javaName, Map<String, Object> dataMap, Configuration configuration) throws IOException, TemplateException {
		// step4 加载模版文件
		Template apiTemplate = configuration.getTemplate(templateName);
		// step5 生成数据
		File docFile = new File(packagePath.replaceAll("\\.", File.separator) + File.separator + javaName);
		File parentFile = docFile.getParentFile();
		if (null != parentFile) {
			parentFile.mkdirs(); // 创建文件夹
		}
		try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)))) {
			// step6 输出文件
			apiTemplate.process(dataMap, out);
		}
	}

	private static String generatePackagePath(String packageName) {
		File serviceFilePath = new File((packageName).replaceAll("\\.", File.separator));
		serviceFilePath.mkdirs();
		return serviceFilePath.getPath();
	}

}
