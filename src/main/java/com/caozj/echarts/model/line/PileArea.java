package com.caozj.echarts.model.line;

import java.util.List;

/**
 * 堆叠区域图
 * 
 * @author caozj
 *
 */
public class PileArea {

  /**
   * 标题
   */
  private String title;

  /**
   * 图例说明列表
   */
  private List<String> legendList;

  /**
   * X轴列表
   */
  private List<String> xAxisList;

  /**
   * 值列表
   */
  private List<List<Double>> valueList;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getLegendList() {
    return legendList;
  }

  public void setLegendList(List<String> legendList) {
    this.legendList = legendList;
  }

  public List<String> getxAxisList() {
    return xAxisList;
  }

  public void setxAxisList(List<String> xAxisList) {
    this.xAxisList = xAxisList;
  }

  public List<List<Double>> getValueList() {
    return valueList;
  }

  public void setValueList(List<List<Double>> valueList) {
    this.valueList = valueList;
  }

}
