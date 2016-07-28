package com.caozj.model.enums;

/**
 * 任务状态
 * 
 * @author caozj
 *
 */
public enum JobStatus {

  WAITING("未启动"),

  RUNNING("运行中"),

  ERROR("运行异常"),

  FINISH("运行完成");

  private String name;

  private JobStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }


}
