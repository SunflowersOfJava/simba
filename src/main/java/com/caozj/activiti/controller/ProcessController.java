package com.caozj.activiti.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.activiti.service.ProcessService;
import com.caozj.framework.model.json.JsonResult;
import com.caozj.framework.util.common.StringUtil;

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

  @Autowired
  private TaskService taskService;

  @Autowired
  private ProcessService processService;

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

  /**
   * 进入办理任务页面
   * 
   * @param id 流程ID
   * @param model
   * @param sessAccount
   * @return
   */
  @RequestMapping
  public String taskForm(String id, ModelMap model, String sessAccount) {

    return "activiti/taskForm";
  }

  /**
   * 保存启动流程(启动流程，自己签收任务，可以在自己的待办任务查询到，后接着执行)
   * 
   * @param model
   * @param request
   * @return
   */
  @RequestMapping
  public String saveStart(ModelMap model, HttpServletRequest request, String sessAccount) {
    Map<String, String> params = buildParam(request);
    processService.saveStartProcess(params.get("processDefinitionId"), params, sessAccount);
    model.put("message", new JsonResult().toJson());
    return "message";
  }

  private Map<String, String> buildParam(HttpServletRequest request) {
    Map<String, String> params = new HashMap<>();
    request.getParameterMap().forEach((key, value) -> {
      if (value.length == 1) {
        params.put(key, value[0]);
      } else {
        params.put(key, StringUtil.join(value, ","));
      }
    });
    return params;
  }

  /**
   * 提交启动流程(启动流程，自己签收任务，完成任务到下一个活动，可以在自己的已办任务中查询到)
   * 
   * @param model
   * @param request
   * @return
   */
  @RequestMapping
  public String submitStart(ModelMap model, HttpServletRequest request, String sessAccount) {
    Map<String, String> params = buildParam(request);
    processService.submitStartProcess(params.get("processDefinitionId"), params, sessAccount);
    model.put("message", new JsonResult().toJson());
    return "message";
  }
}
