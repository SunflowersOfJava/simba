package com.caozj.sigar.model;

/**
 * 性能
 * 
 * @author caozj
 *
 */
public class Capability {

  /**
   * 主键自增ID
   */
  private long id;

  /**
   * 服务器IP地址
   */
  private String ip;

  /**
   * 类型:cpu network memory disk
   */
  private String type;

  /**
   * 当类型为network和disk时才有二级类型,type:network,subType:[receive,send];type:disk,subType:[盘符]
   */
  private String subType;

  /**
   * 收集数据时间
   */
  private String dateTime;

  /**
   * 数据值
   */
  private double value;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }



}
