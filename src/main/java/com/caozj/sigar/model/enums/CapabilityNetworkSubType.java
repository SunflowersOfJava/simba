package com.caozj.sigar.model.enums;

/**
 * 网络性能监控二级类型
 * 
 * @author caozj
 *
 */
public enum CapabilityNetworkSubType {

  RECEIVE("receive", "接收"),

  SEND("send", "发送");

  private CapabilityNetworkSubType(String name, String description) {
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
