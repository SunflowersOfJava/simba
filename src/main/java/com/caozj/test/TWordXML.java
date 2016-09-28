package com.caozj.test;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.caozj.framework.util.office.WordUtil;
import com.caozj.model.constant.ConstantData;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class TWordXML {

  public static void main(String[] args) throws TemplateNotFoundException,
      MalformedTemplateNameException, ParseException, IOException, TemplateException {
    String file = "D:/b.xml";
    Map<String, Object> param = new HashMap<String, Object>();
    List<String> list = new ArrayList<>();
    list.add("测试1" + System.currentTimeMillis());
    list.add("测试2");
    list.add("测试3");
    list.add("测试4");
    list.add("测试5");
    list.add("测试6" + System.currentTimeMillis());
    list.add("测试7" + System.currentTimeMillis());
    param.put("list", list);
    param.put("pageType", "a");
    WordUtil.parseByFreemarker(new File(file), new File("D:/image_content.xml"), param);
    String content = FileUtils.readFileToString(new File("D:/image_content.xml"));
    content = WordUtil.replaceImage(content, "test", WordUtil.getImageForWord("D:/bs2.png"));
    content = WordUtil.replaceImage(content, "pie", WordUtil.getImageForWord(getPieChartImage()));
    content = WordUtil.replaceImage(content, "bar", WordUtil.getImageForWord(getBarChartImage()));
    FileUtils.writeStringToFile(new File("D:/image_content_2.xml"), content,
        ConstantData.DEFAULT_CHARSET);
  }


  public static ByteArrayInputStream getPieChartImage() {
    ByteArrayInputStream in = null;
    DefaultPieDataset pieDataset = new DefaultPieDataset();
    pieDataset.setValue(" 北京局 ", 20);
    pieDataset.setValue(" 上海局 ", 18);
    pieDataset.setValue(" 天津局 ", 16);
    pieDataset.setValue(" 重庆局 ", 15);
    pieDataset.setValue(" 山东局 ", 45);
    JFreeChart chart = ChartFactory.createPieChart3D(" 企业备案图 ", pieDataset, true, false, false);
    // 设置标题字体样式
    chart.getTitle().setFont(new Font(" 黑体 ", Font.BOLD, 20));
    // 设置饼状图里描述字体样式
    PiePlot piePlot = (PiePlot) chart.getPlot();
    piePlot.setLabelFont(new Font(" 黑体 ", Font.BOLD, 10));
    // 设置显示百分比样式
    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator((" {0}({2}) "),
        NumberFormat.getNumberInstance(), new DecimalFormat(" 0.00% ")));
    // 设置统计图背景
    piePlot.setBackgroundPaint(Color.white);
    // 设置图片最底部字体样式
    chart.getLegend().setItemFont(new Font(" 黑体 ", Font.BOLD, 10));
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
      in = new ByteArrayInputStream(out.toByteArray());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return in;
  }

  public static ByteArrayInputStream getBarChartImage() {
    ByteArrayInputStream in = null;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(100, "Spring　Security", "Jan");
    dataset.addValue(200, "jBPM　4", "Jan");
    dataset.addValue(300, "Ext　JS", "Jan");
    dataset.addValue(400, "JFreeChart", "Jan");
    JFreeChart chart = ChartFactory.createBarChart("chart", "num", "type", dataset,
        PlotOrientation.VERTICAL, true, false, false);
    // 设置标题字体样式
    chart.getTitle().setFont(new Font(" 黑体 ", Font.BOLD, 20));
    // 设置饼状图里描述字体样式
    // 设置图片最底部字体样式
    chart.getLegend().setItemFont(new Font(" 黑体 ", Font.BOLD, 10));
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
      in = new ByteArrayInputStream(out.toByteArray());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return in;
  }


}
