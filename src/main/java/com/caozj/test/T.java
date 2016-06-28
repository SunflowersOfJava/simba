package com.caozj.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.caozj.framework.util.freemarker.FreemarkerUtil;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class T {
	
	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Map<String,Object> param = new HashMap<>();
		param.put("a", "我是你大哥");
		param.put("pageType", "treeTable");
		String content = "${a},大哥您好啊！！！over!!!<#if pageType!=\"treeTable\">text</#if>";
		System.out.println(FreemarkerUtil.parseContent(content, param));
	}
	
}
