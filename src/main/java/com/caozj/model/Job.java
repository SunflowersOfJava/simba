package com.caozj.model;

import com.caozj.codegenerate.DescAnnotation;

/**
 * 定时任务对象
 * 
 * @author caozj
 *
 */
@DescAnnotation(desc = "任务")
public class Job {

  private int id;

  /**
   * 名称
   */
  @DescAnnotation(desc = "名称")
  private String name;

  /**
   * 描述
   */
  @DescAnnotation(desc = "描述")
  private String desc;

  /**
   * cron表达式
   */
  @DescAnnotation(desc = "cron表达式")
  private String cronExpression;

  /**
   * 开始执行时间
   */
  @DescAnnotation(desc = "开始执行时间")
  private String startTime;

  /**
   * 结束执行时间
   */
  @DescAnnotation(desc = "结束执行时间")
  private String endTime;

  /**
   * 执行次数
   */
  @DescAnnotation(desc = "执行次数")
  private int exeCount;

  /**
   * 状态
   */
  @DescAnnotation(desc = "状态")
  private String status;

  /**
   * 完整类路径
   */
  @DescAnnotation(desc = "完整类路径")
  private String className;

  /**
   * 执行类方法名
   */
  @DescAnnotation(desc = "执行类方法名")
  private String methodName;

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getCronExpression() {
    return cronExpression;
  }

  public void setCronExpression(String cronExpression) {
    this.cronExpression = cronExpression;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public int getExeCount() {
    return exeCount;
  }

  public void setExeCount(int exeCount) {
    this.exeCount = exeCount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }



}
