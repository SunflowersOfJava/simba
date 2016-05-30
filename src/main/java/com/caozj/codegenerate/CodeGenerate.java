package com.caozj.codegenerate;

import java.io.IOException;

import com.caozj.model.RegistryTable;
import com.caozj.model.RegistryType;
import com.caozj.model.permission.Org;
import com.caozj.model.permission.OrgExt;
import com.caozj.model.permission.UserExt;
import com.caozj.model.permission.UserOrg;

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
		Class<?>[] classes = new Class<?>[] { UserExt.class, Org.class, OrgExt.class, UserOrg.class, RegistryType.class, RegistryTable.class };
		// 生成代码的dao层使用的方式，目前只支持枚举类型CODETYPE的类型
		CODETYPE type = CODETYPE.JDBC;
		// 下面的代码无需修改
		CodeGenerateUtil.getInstance().codeGenerate(classes, type);
		System.exit(0);
	}

}

enum CODETYPE {

	JDBC("jdbc"), MYBATIS("mybatis");

	private String folderName;

	public String getFolderName() {
		return folderName;
	}

	private CODETYPE(String folderName) {
		this.folderName = folderName;
	}
}
