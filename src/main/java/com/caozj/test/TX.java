package com.caozj.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.caozj.framework.util.office.WordUtil;
import com.caozj.model.constant.ConstantData;

import freemarker.template.TemplateException;

public class TX {

  public static void main(String[] args) throws IOException, TemplateException {
    String xml = "D:/r.xml";
    String word = FileUtils.readFileToString(new File(xml), ConstantData.DEFAULT_CHARSET);
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("test", "线型图");
    param.put("pageType", "a");
    word = WordUtil.parseByFreemarker(word, param);
    FileUtils.writeStringToFile(new File("D:/freemarker.xml"), word, ConstantData.DEFAULT_CHARSET);
  }

}
