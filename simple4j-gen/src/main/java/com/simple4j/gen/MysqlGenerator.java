package com.simple4j.gen;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.simple4j.common.orm.BaseEntity;

import java.util.Scanner;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotBlank(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	/**
	 * RUN THIS
	 */
	public static void main(String[] args) {
		String module = "user";
		String[] tables = {"dept", "job", "menu", "permission", "role", "roles_depts", "roles_menus", "roles_permissions", "user", "users_roles",};
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setDateType(DateType.ONLY_DATE);
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/simple4j-gen/src/main/java");
		gc.setAuthor("huangyangcong");
		gc.setOpen(false);
		gc.setMapperName("%sDao");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://192.168.99.100:30306/simple4j_user?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8&useAffectedRows=true");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(module);
		pc.setParent("com.simple4j");
		pc.setMapper("dao");
		pc.setEntity("model");
		pc.setXml("mapper");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};

		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setController(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setTablePrefix("bus_");
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperEntityClass(BaseEntity.class);
		strategy.setSuperEntityColumns("id", "created_on_utc", "is_delete", "creator", "updated_on_utc", "updator");
		strategy.setSuperMapperClass("com.simple4j.autoconfigure.mybatis.BatchMapper");
		strategy.setEntityTableFieldAnnotationEnable(true);
		strategy.setEntityLombokModel(true);
		//strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
		strategy.setInclude(tables);
		mpg.setStrategy(strategy);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}
