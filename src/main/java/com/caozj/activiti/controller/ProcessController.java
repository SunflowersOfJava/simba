package com.caozj.activiti.controller;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程操作的Controller
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

  private static final Log logger = LogFactory.getLog(ProcessController.class);

  @Autowired
  private RepositoryService repositoryService;

  @Autowired
  private FormService formService;

  /**
   * 启动流程
   * 
   * @param id 流程ID
   * @param model
   * @return
   */
  @RequestMapping
  public String start(String id, ModelMap model, String sessAccount, String sessName) {
    ProcessDefinition pd =
        repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
    Object startForm = formService.getRenderedStartForm(id);
    model.put("pd", pd);
    model.put("startForm", startForm);
    model.put("startUser", sessAccount);
    model.put("startUserName", sessName);
    return "activiti/startForm";
  }
}
