package com.caozj.activiti.service.impl;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caozj.activiti.service.ProcessService;

/**
 * 流程Service实现类
 * 
 * @author caozj
 *
 */
@Service
@Transactional
public class ProcessServiceImpl implements ProcessService {

  @Autowired
  private RepositoryService repositoryService;

  @Override
  public void batchDeleteProcess(List<String> processIDs) {
    processIDs.forEach((processID) -> {
      this.deleteProcess(processID);
    });
  }

  @Override
  public void deleteProcess(String processID) {
    ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
        .processDefinitionId(processID).singleResult();
    if (pd == null) {
      return;
    }
    repositoryService.deleteDeployment(pd.getDeploymentId(), true);
  }

}
