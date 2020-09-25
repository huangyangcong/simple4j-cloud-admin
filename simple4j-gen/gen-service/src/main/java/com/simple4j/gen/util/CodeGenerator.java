package com.simple4j.gen.util;

/**
 * @author hyc
 * @version 1.0.0
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipOutputStream;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.simple4j.dao.base.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


@Data
@Slf4j
public class CodeGenerator {


	/**
	 * sword 系统名
	 */
	private static final String SWORD_NAME = "templates/sword";
	/**
	 * saber 系统名
	 */
	private static final String SABER_NAME = "templates/saber";
	/**
	 * pom中gourpId
	 */
	private String groupId = "com.simple4j.admin";
	/**
	 * 项目名称
	 */
	private String projectName = "simple4j";
	/**
	 * 代码所在系统
	 */
	private String systemName = SWORD_NAME;
	/**
	 * 代码模块名称
	 */
	private String moduleName;
	/**
	 * 代码生成的包名
	 */
	private String packageName = "com.simple4j.admin";
	/**
	 * 需要去掉的表前缀
	 */
	private String[] tablePrefix = {"blade_"};
	/**
	 * 需要生成的表名(两者只能取其一)
	 */
	private String[] includeTables = {"blade_test"};
	/**
	 * 需要排除的表名(两者只能取其一)
	 */
	private String[] excludeTables = {};
	/**
	 * 基础业务字段
	 */
	private Class<?> superEntityClass;
	/**
	 * 租户字段
	 */
	private String tenantColumn = "tenant_id";
	/**
	 * 是否启用swagger
	 */
	private Boolean isSwagger2 = Boolean.TRUE;
	/**
	 * 数据库驱动名
	 */
	private String driverName;
	/**
	 * 数据库链接地址
	 */
	private String url;
	/**
	 * 数据库用户名
	 */
	private String username;
	/**
	 * 数据库密码
	 */
	private String password;
	/**
	 * mapper超类
	 */
	private String superMapperClass;
	/**
	 * 是否树形结构
	 */
	private boolean tree = false;
	/**
	 * 主键生成方式
	 */
	private int idType = 0;
	/**
	 * 日期格式
	 */
	private String dateType = "ONLY_DATE";

	/**
	 * 输出流
	 */
	private OutputStream outputStream;

	public static void main(String[] args) throws IOException {
		CodeGenerator generator = new CodeGenerator();
		// 设置数据源
		generator.setDriverName("com.mysql.cj.jdbc.Driver");
		generator.setUrl(
				"jdbc:mysql://122.51.133.124:30002/blade?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&serverTimezone=GMT%2B8");
		generator.setUsername("root");
		generator.setPassword("123456");
		// 设置基础配置
		generator.setSuperMapperClass("com.simple4j.autoconfigure.mybatis.base.ExtendMapper");
		generator.setSystemName(SABER_NAME);
		generator.setProjectName("simple4j");
		generator.setModuleName("task");
		generator.setGroupId("com.simple4j.admin");
		generator.setPackageName("com.simple4j");
		generator.setTablePrefix(new String[]{"simple4j_"});
		generator.setIncludeTables(new String[]{"cloud_app_config"});
		generator.setTree(false);
		generator.setSuperEntityClass(BaseEntity.class);
		generator.setIdType(1);
		generator.setOutputStream(new FileOutputStream("D:/task"));
		generator.run();
	}

	public void run() {
		Properties props = getProperties();
		AutoGenerator mpg = new AutoGenerator();
		GlobalConfig gc = new GlobalConfig();
		for (IdType value : IdType.values()) {
			if (value.getKey() == idType) {
				gc.setIdType(value);
			}
		}
		for (DateType value : DateType.values()) {
			if (value.name().equals(dateType)) {
				gc.setDateType(value);
			}
		}
		String author = props.getProperty("author");
		gc.setAuthor(author);
		gc.setFileOverride(true);
		gc.setOpen(false);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		gc.setSwagger2(isSwagger2);
		mpg.setGlobalConfig(gc);
		DataSourceConfig dsc = new DataSourceConfig();
		String driverName = StrUtil.nullToDefault(this.driverName,
				props.getProperty("spring.datasource.driver-class-name"));
		if (StrUtil.containsAny(driverName, DbType.MYSQL.getDb())) {
			dsc.setDbType(DbType.MYSQL);
			dsc.setTypeConvert(new MySqlTypeConvert());
		} else if (StrUtil.containsAny(driverName, DbType.POSTGRE_SQL.getDb())) {
			dsc.setDbType(DbType.POSTGRE_SQL);
			dsc.setTypeConvert(new PostgreSqlTypeConvert());
		} else {
			dsc.setDbType(DbType.ORACLE);
			dsc.setTypeConvert(new OracleTypeConvert());
		}
		dsc.setDriverName(driverName);
		dsc.setUrl(StrUtil.nullToDefault(this.url, props.getProperty("spring.datasource.url")));
		dsc.setUsername(
				StrUtil.nullToDefault(this.username,
						props.getProperty("spring.datasource.username")));
		dsc.setPassword(
				StrUtil.nullToDefault(this.password,
						props.getProperty("spring.datasource.password")));
		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名
		// strategy.setDbColumnUnderline(true);//全局下划线命名
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setTablePrefix(tablePrefix);
		strategy.setSuperMapperClass(superMapperClass);
		strategy.setLogicDeleteFieldName("is_deleted");
		if (includeTables.length > 0) {
			strategy.setInclude(includeTables);
		}
		if (excludeTables.length > 0) {
			strategy.setExclude(excludeTables);
		}
		strategy.setSuperServiceClass(IService.class);
		strategy.setSuperServiceImplClass(ServiceImpl.class);

		// 自定义 controller 父类
//		strategy.setSuperControllerClass("org.springblade.core.boot.ctrl.BladeController");
		strategy.setChainModel(false);
		strategy.setEntityLombokModel(true);
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setSuperEntityClass(superEntityClass);
		mpg.setStrategy(strategy);
		// 包配置
		PackageConfig pc = new PackageConfig();
		// 控制台扫描
		pc.setParent(packageName);
		pc.setModuleName(moduleName);
		pc.setController("controller");
		pc.setEntity("entity");
		pc.setXml("templates/mapper");
		mpg.setPackageInfo(pc);

		String dic = projectName + "-" + moduleName;
		Path tempDirectory = null;
		try {
			tempDirectory = Files.createTempDirectory(dic);
		} catch (IOException e) {
			throw new RuntimeException("压缩文件错误");
		}
		String path = tempDirectory.toFile().getAbsolutePath() + File.separator + dic;
		log.info("gen path:{}", path);
		mpg.setCfg(getInjectionConfig(path, pc.getParent()));
		mpg.execute();

		try (ZipOutputStream zipOutputStream = outputStream == null ? new ZipOutputStream(
				new FileOutputStream(
						path + ".zip")) : new ZipOutputStream(outputStream)) {
			ZipUtil.zip(zipOutputStream, false, file -> true,
					new File(path));
		} catch (Exception e) {
			throw new RuntimeException("压缩文件错误");
		}
	}

	private InjectionConfig getInjectionConfig(String outputDir, String servicePackage) {
		String upperModuleName = StrUtil.upperFirst(moduleName);

		// 自定义配置
		Map<String, Object> map = new HashMap<>(16);
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				map.put("jackson", true);
				map.put("groupId", groupId);
				map.put("moduleName", moduleName);
				map.put("projectName", projectName);
				map.put("tenantColumn", tenantColumn);
				map.put("tree", tree);
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/sql/menu.sql.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				map.put("entityKey", (tableInfo.getEntityName().toLowerCase()));
				map.put("menuId", IdWorker.getId());
				map.put("addMenuId", IdWorker.getId());
				map.put("editMenuId", IdWorker.getId());
				map.put("removeMenuId", IdWorker.getId());
				map.put("viewMenuId", IdWorker.getId());
				map.put("upperModuleName", upperModuleName);
				return outputDir + "/sql/" + tableInfo
						.getEntityName().toLowerCase() + ".menu.mysql";
			}
		});
		String javaSource = "/src/main/java";
		String api = File.separator + moduleName + "-api";
		String dao = File.separator + moduleName + "-dao";
		String service = File.separator + moduleName + "-service";
		String web = File.separator + moduleName + "-web";
		String start = File.separator + moduleName + "-start";
		String manager = File.separator + moduleName + "-manager";

		focList.add(new FileOutConfig("/templates/parentPom.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + "/pom.xml";
			}
		});

		focList.add(new FileOutConfig("/templates/apiPom.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + "/pom.xml";
			}
		});

		focList.add(new FileOutConfig("/templates/daoPom.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + dao + "/pom.xml";
			}
		});
		focList.add(new FileOutConfig("/templates/servicePom.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + service + "/pom.xml";
			}
		});
		focList.add(new FileOutConfig("/templates/managerPom.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + manager + "/pom.xml";
			}
		});
		focList.add(new FileOutConfig("/templates/startPom.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + start + "/pom.xml";
			}
		});
		focList.add(new FileOutConfig("/templates/webPom.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + web + "/pom.xml";
			}
		});
		focList.add(new FileOutConfig("/templates/application.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + start + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + upperModuleName
						+ "Application"
						+ StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/service.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "service" + "/" + tableInfo
						.getServiceName() + StringPool.DOT_JAVA;
			}
		});
		if (tree) {
			focList.add(new FileOutConfig("/templates/treeRequest.java.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return outputDir + api + javaSource + File.separator + servicePackage
							.replace(".", File.separator) + File.separator + "request"
							+ File.separator
							+ tableInfo.getEntityName() + "TreeRequest" + StringPool.DOT_JAVA;
				}
			});
		}
		focList.add(new FileOutConfig("/templates/addRequest.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "request" + File.separator
						+ tableInfo.getEntityName() + "AddRequest" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/detailRequest.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "request" + File.separator
						+ tableInfo.getEntityName() + "DetailRequest" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/listRequest.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "request" + File.separator
						+ tableInfo.getEntityName() + "ListRequest" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/pageRequest.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "request" + File.separator
						+ tableInfo.getEntityName() + "PageRequest" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/removeRequest.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "request" + File.separator
						+ tableInfo.getEntityName() + "RemoveRequest" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/updateRequest.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "request" + File.separator
						+ tableInfo.getEntityName() + "UpdateRequest" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/addOrUpdateRequest.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "request" + File.separator
						+ tableInfo.getEntityName() + "AddOrUpdateRequest" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/detailResponse.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + api + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "response" + File.separator
						+ tableInfo.getEntityName() + "DetailResponse" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + File.separator + moduleName
						+ "-dao/src/main/resources/mapper" + File.separator
						+ File.separator + tableInfo.getXmlName() + ConstVal.XML_SUFFIX;
			}
		});
		focList.add(new FileOutConfig("/templates/mapper.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + dao + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "mapper" + File.separator
						+ tableInfo.getMapperName()
						+ StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/entity.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + dao + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "entity" + File.separator
						+ tableInfo.getEntityName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/mapStruct.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + service + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "mapstruct"
						+ File.separator
						+ tableInfo.getEntityName() + "MapStruct" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + service + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "service/impl"
						+ File.separator
						+ tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/controller.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return outputDir + web + javaSource + File.separator + servicePackage
						.replace(".", File.separator) + File.separator + "controller"
						+ File.separator
						+ tableInfo.getControllerName() + StringPool.DOT_JAVA;
			}
		});
		String front = outputDir + File.separator + "front";
		if (systemName.equals(SWORD_NAME)) {
			focList.add(new FileOutConfig("/templates/sword/action.js.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/actions" + File.separator + tableInfo
							.getEntityName().toLowerCase() + ".js";
				}
			});
			focList.add(new FileOutConfig("/templates/sword/model.js.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/models" + File.separator + tableInfo
							.getEntityName().toLowerCase() + ".js";
				}
			});
			focList.add(new FileOutConfig("/templates/sword/service.js.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/services" + File.separator + tableInfo
							.getEntityName().toLowerCase() + ".js";
				}
			});
			focList.add(new FileOutConfig("/templates/sword/list.js.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/pages" + File.separator + StrUtil
							.upperFirst(servicePackage) + File.separator + tableInfo
							.getEntityName()
							+ File.separator + tableInfo.getEntityName() + ".js";
				}
			});
			focList.add(new FileOutConfig("/templates/sword/add.js.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/pages" + File.separator + StrUtil
							.upperFirst(servicePackage) + File.separator + tableInfo
							.getEntityName()
							+ File.separator + tableInfo.getEntityName() + "Add.js";
				}
			});
			focList.add(new FileOutConfig("/templates/sword/edit.js.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/pages" + File.separator + StrUtil
							.upperFirst(servicePackage) + File.separator + tableInfo
							.getEntityName()
							+ File.separator + tableInfo.getEntityName() + "Edit.js";
				}
			});
			focList.add(new FileOutConfig("/templates/sword/view.js.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/pages" + File.separator + StrUtil
							.upperFirst(servicePackage) + File.separator + tableInfo
							.getEntityName()
							+ File.separator + tableInfo.getEntityName() + "View.js";
				}
			});
		} else if (systemName.equals(SABER_NAME)) {
			focList.add(new FileOutConfig("/templates/saber/api.js.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/api" + File.separator + moduleName
							.toLowerCase() + File.separator + tableInfo.getEntityName()
							.toLowerCase() + ".js";
				}
			});
			focList.add(new FileOutConfig("/templates/saber/crud.vue.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					return front + "/views" + File.separator + moduleName
							.toLowerCase() + File.separator + tableInfo.getEntityName()
							.toLowerCase() + ".vue";
				}
			});
		}
		cfg.setFileOutConfigList(focList);
		cfg.setFileCreate((configBuilder, fileType, filePath) -> {
			if (fileType == FileType.OTHER) {
				// 全局判断【默认】
				File file = new File(filePath);
				boolean exist = file.exists();
				if (!exist) {
//					file = File.createTempFile("tmp",".jpg", new File(System.getProperty("java.io.tmpdir")));
					file.getParentFile().mkdirs();
				}
			}
			return fileType == FileType.OTHER;
		});
		return cfg;
	}

	/**
	 * 获取配置文件
	 *
	 * @return 配置Props
	 */
	private Properties getProperties() {
		// 读取配置文件
		Resource resource = new ClassPathResource("/templates/code.properties");
		Properties props = new Properties();
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 页面生成的文件名
	 */
	private String getGeneratorViewPath(String viewOutputDir, TableInfo tableInfo,
			String suffixPath) {
		String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
		String path = viewOutputDir + File.separator + name + File.separator + name + suffixPath;
		File viewDir = new File(path).getParentFile();
		if (!viewDir.exists()) {
			viewDir.mkdirs();
		}
		return path;
	}
}
