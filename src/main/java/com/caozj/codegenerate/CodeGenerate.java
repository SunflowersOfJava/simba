package com.caozj.codegenerate;

import java.io.IOException;

import com.caozj.model.RegistryType;

import freemarker.template.TemplateException;

/**
 * 代码生成器入口
 * 
 * @author caozj
 *
 */
public class CodeGenerate {

	public static void main(String[] args) throws IOException, TemplateException {
		// 只需要将需要生成代码的class对象放入下面数组中，就可以自动生成代码
		Class<?>[] classes = new Class<?>[] { RegistryType.class };
		// 生成代码的dao层使用的方式，目前只支持枚举类型CODETYPE的类型
		CODETYPE type = CODETYPE.JDBC;
		// 下面的代码无需修改
		CodeGenerateUtil.getInstance().codeGenerate(classes, type);
		System.exit(0);
	}

}

enum CODETYPE {

	// mybatis对应的代码生成器暂未实现
	JDBC("jdbc"), MYBATIS("mybatis");

	private String folderName;

	public String getFolderName() {
		return folderName;
	}

	private CODETYPE(String folderName) {
		this.folderName = folderName;
	}
}
