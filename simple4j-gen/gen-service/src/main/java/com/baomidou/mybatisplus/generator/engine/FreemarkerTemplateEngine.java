package com.baomidou.mybatisplus.generator.engine;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Freemarker 模板
 *
 * @author hyc
 */
public class FreemarkerTemplateEngine extends AbstractTemplateEngine {

	private Configuration configuration;

	@Override
	public FreemarkerTemplateEngine init(ConfigBuilder configBuilder) {
		super.init(configBuilder);
		configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setDefaultEncoding(ConstVal.UTF8);
		configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);
		return this;
	}

	@Override
	public void writer(Map<String, Object> objectMap, String templatePath, String outputFile)
		throws Exception {
		Template template = configuration.getTemplate(templatePath);
		try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
			template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
		}
		logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
	}

	@Override
	public String templateFilePath(String filePath) {
		return filePath + ".ftl";
	}
}
