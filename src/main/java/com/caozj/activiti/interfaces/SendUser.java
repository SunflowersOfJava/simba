package com.caozj.activiti.interfaces;

/**
 * 获取发送下一步的流程执行人
 * 
 * @author caozj
 *
 */
public interface SendUser {

  String getNextUser(String currentUserAccount);
}
