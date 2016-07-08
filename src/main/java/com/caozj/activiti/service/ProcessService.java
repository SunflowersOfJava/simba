package com.caozj.activiti.service;

import java.util.List;

/**
 * 流程Service
 * 
 * @author caozj
 *
 */
public interface ProcessService {

  /**
   * 批量删除流程
   * 
   * @param processIDs 流程ID列表
   */
  void batchDeleteProcess(List<String> processIDs);

  /**
   * 删除流程
   * 
   * @param processID 流程ID
   */
  void deleteProcess(String processID);
}
