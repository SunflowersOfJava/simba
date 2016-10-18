package com.caozj.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.echarts.model.line.PileArea;
import com.caozj.framework.util.json.FastJsonUtil;

@Controller
@RequestMapping("/te")
public class TEController {

  /**
   * 获取堆叠区域图的数据
   * 
   * @param model
   * @return
   */
  @RequestMapping
  public String pileArea(ModelMap model) {
    PileArea p = new PileArea();
    p.setTitle("堆叠区域图");
    List<String> legendList = new ArrayList<>();
    legendList.add("邮件营销");
    legendList.add("联盟广告");
    legendList.add("视频广告");
    legendList.add("直接访问");
    legendList.add("搜索引擎");
    p.setLegendList(legendList);
    List<String> xAxisList = new ArrayList<>();
    xAxisList.add("周一");
    xAxisList.add("周二");
    xAxisList.add("周三");
    xAxisList.add("周四");
    xAxisList.add("周五");
    xAxisList.add("周六");
    xAxisList.add("周日");
    p.setxAxisList(xAxisList);
    List<List<Double>> valueList = new ArrayList<>();
    List<Double> v1 = new ArrayList<>();
    v1.add(1.0D);
    v1.add(2.0D);
    v1.add(3.0D);
    v1.add(4.0D);
    v1.add(5.0D);
    v1.add(6.0D);
    v1.add(7.0D);
    List<Double> v2 = new ArrayList<>();
    v2.add(11.0D);
    v2.add(12.0D);
    v2.add(13.0D);
    v2.add(14.0D);
    v2.add(15.0D);
    v2.add(16.0D);
    v2.add(77.0D);
    List<Double> v3 = new ArrayList<>();
    v3.add(21.0D);
    v3.add(22.0D);
    v3.add(23.0D);
    v3.add(24.0D);
    v3.add(25.0D);
    v3.add(26.0D);
    v3.add(27.0D);
    List<Double> v4 = new ArrayList<>();
    v4.add(31.0D);
    v4.add(32.0D);
    v4.add(33.0D);
    v4.add(34.0D);
    v4.add(35.0D);
    v4.add(36.0D);
    v4.add(37.0D);
    List<Double> v5 = new ArrayList<>();
    v5.add(1.0D);
    v5.add(2.0D);
    v5.add(3.0D);
    v5.add(4.0D);
    v5.add(5.0D);
    v5.add(6.0D);
    v5.add(7.0D);
    valueList.add(v1);
    valueList.add(v2);
    valueList.add(v3);
    valueList.add(v4);
    valueList.add(v5);
    p.setValueList(valueList);
    model.put("message", FastJsonUtil.toJson(p));
    return "message";
  }

}
