package com.caozj.framework.util.office;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.caozj.framework.util.freemarker.FreemarkerUtil;
import com.caozj.model.constant.ConstantData;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 复杂的word操作工具类(包括使用freemark语法解析word，插入表格，插入图表，插入图片等)
 * 
 * @author caozj
 *
 */
public class WordUtil {

  /**
   * 使用freemarker语法解析word(word必须先使用xml格式另存为)
   * 
   * @param content word文件的xml格式内容
   * @param param 参数
   * @return
   * @throws TemplateNotFoundException
   * @throws MalformedTemplateNameException
   * @throws ParseException
   * @throws IOException
   * @throws TemplateException
   */
  public static String parseByFreemarker(String content, Map<String, Object> param)
      throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
      TemplateException {
    content = content.replaceAll("<", "&1lt;").replaceAll(">", "&1gt;");
    content = content.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
    String word = FreemarkerUtil.parseContent(content, param);
    word = word.replaceAll("&1lt;", "<").replaceAll("&1gt;", ">");
    return word;
  }

  /**
   * 使用freemarker语法解析word(word必须先使用xml格式另存为)
   * 
   * @param inWord xml格式的输入word
   * @param outWord xml格式的输出word
   * @param param 参数
   * @throws TemplateNotFoundException
   * @throws MalformedTemplateNameException
   * @throws ParseException
   * @throws IOException
   * @throws TemplateException
   */
  public static void parseByFreemarker(File inWord, File outWord, Map<String, Object> param)
      throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
      TemplateException {
    String content = FileUtils.readFileToString(inWord, ConstantData.DEFAULT_CHARSET);
    String word = parseByFreemarker(content, param);
    FileUtils.writeStringToFile(outWord, word);
  }

}
