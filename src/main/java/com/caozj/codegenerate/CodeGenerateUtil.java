package com.caozj.codegenerate;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.caozj.common.CustomizedPropertyPlaceholderConfigurer;
import com.caozj.framework.util.common.ReflectUtil;
import com.caozj.framework.util.common.ServerUtil;
import com.caozj.framework.util.common.StringUtil;
import com.caozj.framework.util.freemarker.FreemarkerUtil;
import com.caozj.model.constant.ConstantData;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 代码生成器工具类
 * 
 * @author caozj
 *
 */
public class CodeGenerateUtil {

	private static final Log logger = LogFactory.getLog(CodeGenerateUtil.class);

	private static final String mybatisXmlDir = "mybatis";

	private String packageName = CustomizedPropertyPlaceholderConfigurer.getContextProperty("code.generate.package");

	private static class CodeGenerateUtilHolder {
		private static CodeGenerateUtil instance = new CodeGenerateUtil();
	}

	private CodeGenerateUtil() {

	}

	public static CodeGenerateUtil getInstance() {
		return CodeGenerateUtilHolder.instance;
	}

	/**
	 * 按照class对象生成代码
	 * 
	 * @param c
	 *            class对象
	 * @param dir
	 *            代码输入目录
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void codeGenerate(Class<?> c, File dir, CODETYPE type) throws IOException, TemplateException {
		logger.info("生成代码" + c.getName() + "开始,类型为" + type.getFolderName());
		initPath(dir, type);
		Map<String, Object> param = buildParam(c);
		generateClassFile(dir, c.getSimpleName(), param, type);
		logger.info("生成代码" + c.getName() + "结束,类型为" + type.getFolderName());
	}

	/**
	 * 构造参数
	 * 
	 * @param c
	 * @return
	 */
	private Map<String, Object> buildParam(Class<?> c) {
		Map<String, Object> param = new HashMap<String, Object>();
		String className = c.getSimpleName();
		String firstLower = StringUtil.getFirstLower(className);
		param.put("className", className);
		param.put("firstLower", firstLower);
		param.put("packageName", packageName);
		List<String> fields = ReflectUtil.getAllPropertiesName(c);
		String updateProperties = "";
		String insertProperties = "";
		String params = "";
		String propertiesCount = "";
		for (String field : fields) {
			if ("id".equals(field) || "serialVersionUID".equals(field)) {
				continue;
			}
			updateProperties += " " + field + " = ? ,";
			insertProperties += " " + field + ",";
			propertiesCount += "?,";
			params += firstLower + ".get" + StringUtil.getFirstUpper(field) + "(),";
		}
		updateProperties = updateProperties.substring(0, updateProperties.length() - 1);
		insertProperties = insertProperties.substring(0, insertProperties.length() - 1);
		propertiesCount = propertiesCount.substring(0, propertiesCount.length() - 1);
		params = params.substring(0, params.length() - 1);
		param.put("updateProperties", updateProperties);
		param.put("insertProperties", insertProperties);
		param.put("params", params);
		param.put("propertiesCount", propertiesCount);
		return param;
	}

	/**
	 * 生成类文件
	 * 
	 * @param dir
	 * @param className
	 * @param param
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generateClassFile(File dir, String className, Map<String, Object> param, CODETYPE type) throws IOException, TemplateException {
		String controllerContent = getController(param, type);
		String serviceContent = getService(param, type);
		String serviceImplContent = getServiceImpl(param, type);
		String daoContent = getDao(param, type);
		FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/controller/" + className + "Controller.java"), controllerContent, ConstantData.DEFAULT_CHARSET);
		FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/service/" + className + "Service.java"), serviceContent, ConstantData.DEFAULT_CHARSET);
		FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/service/impl/" + className + "ServiceImpl.java"), serviceImplContent, ConstantData.DEFAULT_CHARSET);
		if (type == CODETYPE.JDBC) {
			String daoImplContent = getDaoImpl(param, type);
			FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/dao/" + className + "Dao.java"), daoContent, ConstantData.DEFAULT_CHARSET);
			FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/dao/impl/" + className + "DaoImpl.java"), daoImplContent, ConstantData.DEFAULT_CHARSET);
		} else if (type == CODETYPE.MYBATIS) {
			FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/mybatisDao/" + className + "Mapper.java"), daoContent, ConstantData.DEFAULT_CHARSET);
			String mybatisDaoXmlContent = getMybatisDaoXml(param, type);
			FileUtils.writeStringToFile(new File(ServerUtil.getResourcesDir().getAbsolutePath() + "/" + mybatisXmlDir + "/" + StringUtil.getFirstLower(className) + ".xml"), mybatisDaoXmlContent,
					ConstantData.DEFAULT_CHARSET);
		}
	}

	private String getMybatisDaoXml(Map<String, Object> param, CODETYPE type) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		return FreemarkerUtil.parse("codegenerate/" + type.getFolderName() + "/mybatisXml.ftl", param);
	}

	private String getController(Map<String, Object> param, CODETYPE type) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		return FreemarkerUtil.parse("codegenerate/" + type.getFolderName() + "/controller.ftl", param);
	}

	private String getService(Map<String, Object> param, CODETYPE type) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		return FreemarkerUtil.parse("codegenerate/" + type.getFolderName() + "/service.ftl", param);
	}

	private String getServiceImpl(Map<String, Object> param, CODETYPE type) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		return FreemarkerUtil.parse("codegenerate/" + type.getFolderName() + "/serviceImpl.ftl", param);
	}

	private String getDao(Map<String, Object> param, CODETYPE type) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		return FreemarkerUtil.parse("codegenerate/" + type.getFolderName() + "/dao.ftl", param);
	}

	private String getDaoImpl(Map<String, Object> param, CODETYPE type) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		return FreemarkerUtil.parse("codegenerate/" + type.getFolderName() + "/daoImpl4Mysql.ftl", param);
	}

	/**
	 * 创建好所有需要的路径
	 */
	private void initPath(File dir, CODETYPE type) {
		String root = dir.getAbsolutePath();
		String service = root + "/service";
		String serviceImpl = service + "/impl";
		String controller = root + "/controller";
		new File(serviceImpl).mkdirs();
		new File(controller).mkdirs();
		if (type == CODETYPE.JDBC) {
			String dao = root + "/dao";
			String daoImpl = dao + "/impl";
			new File(daoImpl).mkdirs();
		} else if (type == CODETYPE.MYBATIS) {
			String dao = root + "/mybatisDao";
			new File(dao).mkdirs();
			new File(ServerUtil.getResourcesDir().getAbsolutePath() + "/" + mybatisXmlDir).mkdirs();
		}
	}

	/**
	 * 按照class对象数组生成代码
	 * 
	 * @param cs
	 *            class对象数组
	 * @param dir
	 *            代码输入目录
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void codeGenerate(Class<?>[] cs, File dir, CODETYPE type) throws IOException, TemplateException {
		for (Class<?> c : cs) {
			codeGenerate(c, dir, type);
		}
	}

	/**
	 * 按照class对象生成代码
	 * 
	 * @param c
	 *            class对象
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void codeGenerate(Class<?> c, CODETYPE type) throws IOException, TemplateException {
		codeGenerate(c, new File(ServerUtil.getSrcDir().getAbsolutePath() + "/" + getPackagePath()), type);
	}

	/**
	 * 按照class对象数组生成代码
	 * 
	 * @param cs
	 *            class对象数组
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void codeGenerate(Class<?>[] cs, CODETYPE type) throws IOException, TemplateException {
		codeGenerate(cs, new File(ServerUtil.getSrcDir().getAbsolutePath() + "/" + getPackagePath()), type);
	}

	private String getPackagePath() {
		return packageName.replaceAll("\\.", "/");
	}
}
