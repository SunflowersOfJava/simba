package com.caozj.sigar.model.enums;

/**
 * 性能监控类型
 * 
 * @author caozj
 *
 */
public enum CapabilityType {

  CPU("cpu", "cpu"),

  DISK("disk", "磁盘"),

  MEMORY("memory", "内存"),

  NETWORK("network", "网络");


  private CapabilityType(String name, String description) {
    this.name = name;
    this.description = description;
  }

  private String name;

  private String description;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

}
