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
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.TextAnchor;

import com.caozj.framework.util.office.WordUtil;
import com.caozj.model.constant.ConstantData;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class TWordXML {

  public static void main(String[] args) throws TemplateNotFoundException,
      MalformedTemplateNameException, ParseException, IOException, TemplateException {
    String xml = "D:/image.xml";
    Map<String, String> param = new HashMap<String, String>();
    param.put("test", "线型图");
    String word = FileUtils.readFileToString(new File(xml), ConstantData.DEFAULT_CHARSET);
    word = WordUtil.replaceText(word, param);
    word = WordUtil.replaceImage(word, "s", WordUtil.getImageForWord(getLineChartImage()));
    FileUtils.writeStringToFile(new File("D:/line.xml"), word, ConstantData.DEFAULT_CHARSET);

    param.put("test", "柱形图");
    word = FileUtils.readFileToString(new File(xml), ConstantData.DEFAULT_CHARSET);
    word = WordUtil.replaceText(word, param);
    word = WordUtil.replaceImage(word, "s", WordUtil.getImageForWord(getBarChartImage()));
    FileUtils.writeStringToFile(new File("D:/bar.xml"), word, ConstantData.DEFAULT_CHARSET);

    param.put("test", "饼图");
    word = FileUtils.readFileToString(new File(xml), ConstantData.DEFAULT_CHARSET);
    word = WordUtil.replaceText(word, param);
    word = WordUtil.replaceImage(word, "s", WordUtil.getImageForWord(getPieChartImage()));
    FileUtils.writeStringToFile(new File("D:/pie.xml"), word, ConstantData.DEFAULT_CHARSET);

    xml = "D:/table.xml";
    param.put("test", "表格");
    word = FileUtils.readFileToString(new File(xml), ConstantData.DEFAULT_CHARSET);
    word = WordUtil.replaceText(word, param);
    List<List<String>> rows = new ArrayList<>();
    List<String> row = new ArrayList<>();
    row.add("第一行我11");
    row.add("第一行你21");
    row.add("第一行他31");
    List<String> row2 = new ArrayList<>();
    row2.add("第二行我12");
    row2.add("第二行你22");
    row2.add("第二行他32");
    rows.add(row);
    rows.add(row2);
    List<String> header = new ArrayList<>();
    header.add("列1");
    header.add("列2");
    header.add("列3");
    word = WordUtil.replaceTable(word, "1", rows, header);
    FileUtils.writeStringToFile(new File("D:/table_gen.xml"), word, ConstantData.DEFAULT_CHARSET);

    xml = "D:/template/1.xml";
    word = FileUtils.readFileToString(new File(xml), ConstantData.DEFAULT_CHARSET);
    param.put("title", "大当家1" + System.currentTimeMillis());
    param.put("publishDate", "2000-01-04");
    for (int i = 1; i <= 100; i++) {
      param.put("num" + i, "测试字符串" + i);
    }
    word = WordUtil.replaceText(word, param);
    for (int i = 1; i <= 20; i++) {
      word = WordUtil.replaceImage(word, "image" + i, WordUtil.getImageForWord(getBarChartImage()));
    }
    for (int i = 1; i <= 10; i++) {
      word = WordUtil.replaceTable(word, "table" + i, rows, header);
    }
    FileUtils.writeStringToFile(new File("D:/template/zhu.xml"), word,
        ConstantData.DEFAULT_CHARSET);
  }


  public static ByteArrayInputStream getPieChartImage() {
    // 创建主题样式
    StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
    // 设置标题字体
    standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
    // 设置图例的字体
    standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
    // 设置轴向的字体
    standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
    // 应用主题样式
    ChartFactory.setChartTheme(standardChartTheme);
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
    // 创建主题样式
    StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
    // 设置标题字体
    standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
    // 设置图例的字体
    standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
    // 设置轴向的字体
    standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
    // 应用主题样式
    ChartFactory.setChartTheme(standardChartTheme);
    ByteArrayInputStream in = null;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(100, "Spring　Security我", "Jan我");
    dataset.addValue(200, "jBPM　4你", "2你");
    dataset.addValue(300, "Ext　JS他", "3他");
    dataset.addValue(400, "JFreeChart猪", "4猪");
    JFreeChart chart = ChartFactory.createBarChart("chart", "数量", "类型", dataset,
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

  public static ByteArrayInputStream getLineChartImage() throws IOException {
    // 创建主题样式
    StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
    // 设置标题字体
    standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
    // 设置图例的字体
    standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
    // 设置轴向的字体
    standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
    // 应用主题样式
    ChartFactory.setChartTheme(standardChartTheme);
    // 定义图标对象
    JFreeChart chart = ChartFactory.createLineChart(null, // 报表题目，字符串类型
        "采集时间", // 横轴
        "血糖值", // 纵轴
        createDataset(), // 获得数据集
        PlotOrientation.VERTICAL, // 图标方向垂直
        true, // 显示图例
        false, // 不用生成工具
        false // 不用生成URL地址
    );
    // 整个大的框架属于chart 可以设置chart的背景颜色

    // 生成图形
    CategoryPlot plot = chart.getCategoryPlot();
    // 图像属性部分
    plot.setBackgroundPaint(Color.white);
    plot.setDomainGridlinesVisible(true); // 设置背景网格线是否可见
    plot.setDomainGridlinePaint(Color.BLACK); // 设置背景网格线颜色
    plot.setRangeGridlinePaint(Color.GRAY);
    plot.setNoDataMessage("没有数据");// 没有数据时显示的文字说明。
    // 数据轴属性部分
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    rangeAxis.setAutoRangeIncludesZero(true); // 自动生成
    rangeAxis.setUpperMargin(0.20);
    rangeAxis.setLabelAngle(Math.PI / 2.0);
    rangeAxis.setAutoRange(false);
    // 数据渲染部分 主要是对折线做操作
    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
    renderer.setBaseItemLabelsVisible(true);
    renderer.setSeriesPaint(0, Color.black); // 设置折线的颜色
    renderer.setBaseShapesFilled(true);
    renderer.setBaseItemLabelsVisible(true);
    renderer.setBasePositiveItemLabelPosition(
        new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    /*
     * 这里的StandardCategoryItemLabelGenerator()我想强调下：当时这个地*方被搅得头很晕，Standard**ItemLabelGenerator是通用的
     * 因为我创建*的是CategoryPlot 所以很多设置都是Category相关 而XYPlot 对应的则是 ： StandardXYItemLabelGenerator
     */
    // 对于编程人员 这种根据一种类型方法联想到其他类型相似方法的思
    // 想是必须有的吧！目前只能慢慢培养了。。
    renderer.setBaseItemLabelFont(new Font("Dialog", 1, 14)); // 设置提示折点数据形状
    plot.setRenderer(renderer);
    // 区域渲染部分
    double lowpress = 4.5;
    double uperpress = 8; // 设定正常血糖值的范围
    IntervalMarker inter = new IntervalMarker(lowpress, uperpress);
    inter.setLabelOffsetType(LengthAdjustmentType.EXPAND); // 范围调整——扩张
    inter.setPaint(Color.LIGHT_GRAY);// 域顏色

    inter.setLabelFont(new Font("SansSerif", 41, 14));
    inter.setLabelPaint(Color.RED);
    inter.setLabel("正常血糖值范围"); // 设定区域说明文字
    plot.addRangeMarker(inter, Layer.BACKGROUND); // 添加mark到图形 BACKGROUND使得数据折线在区域的前端

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ChartUtilities.writeChartAsPNG(out, chart, 700, 500);
    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    return in;
  }

  // 获得数据集 （这里的数据是为了测试我随便写的一个自动生成数据的例子）
  public static DefaultCategoryDataset createDataset() {

    DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
    // 曲线名称
    String series = "血糖值"; // series指的就是报表里的那条数据线
    // 因此 对数据线的相关设置就需要联系到serise
    // 比如说setSeriesPaint 设置数据线的颜色

    // 横轴名称(列名称)
    String[] time = new String[15];
    String[] timeValue = {"6-1日", "6-2日", "6-3日", "6-4日", "6-5日", "6-6日", "6-7日", "6-8日", "6-9日",
        "6-10日", "6-11日", "6-12日", "6-13日", "6-14日", "6-15日"};
    for (int i = 0; i < 15; i++) {
      time[i] = timeValue[i];
    }
    // 随机添加数据值
    for (int i = 0; i < 15; i++) {
      java.util.Random r = new java.util.Random();
      linedataset.addValue(i + i * 9.34 + r.nextLong() % 100, // 值
          series, // 哪条数据线
          time[i]); // 对应的横轴
    }

    return linedataset;
  }
}
