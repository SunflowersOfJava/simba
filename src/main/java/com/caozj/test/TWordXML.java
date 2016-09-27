package com.caozj.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.caozj.framework.util.office.WordUtil;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class TWordXML {

  public static void main(String[] args) throws TemplateNotFoundException,
      MalformedTemplateNameException, ParseException, IOException, TemplateException {
    String file = "D:/test.xml";
    Map<String, Object> param = new HashMap<String, Object>();
    List<String> list = new ArrayList<>();
    list.add("测试1");
    list.add("测试2");
    list.add("测试3");
    list.add("测试4");
    list.add("测试5");
    list.add("测试6" + System.currentTimeMillis());
    param.put("list", list);
    param.put("pageType", "a");
    WordUtil.parseByFreemarker(new File(file), new File("D:/444.xml"), param);
  }

}
