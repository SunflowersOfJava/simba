package com.caozj.activiti.controller;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.controller.form.EasyUIPageForm;
import com.caozj.framework.model.easyui.PageGrid;
import com.caozj.framework.util.common.JsonUtil;

/**
 * 流程管理Controller
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processManagement")
public class ProcessManagementController {

  @Autowired
  private RepositoryService repositoryService;

  @RequestMapping
  public String list() {
    return "activiti/processManagementList";
  }

  @RequestMapping("/listDataOfEasyUI.do")
  public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, String processName) {
    List<ProcessDefinition> list = null;
    long total = 0;
    ProcessDefinitionQuery processDefinitionQuery =
        repositoryService.createProcessDefinitionQuery();
    if (StringUtils.isEmpty(processName)) {
      list = processDefinitionQuery.listPage((form.getPage() - 1) * form.getRows(), form.getRows());
      total = processDefinitionQuery.count();
    } else {
      list = processDefinitionQuery.processDefinitionNameLike(processName)
          .listPage((form.getPage() - 1) * form.getRows(), form.getRows());
      total = processDefinitionQuery.processDefinitionNameLike(processName).count();
    }
    String message = JsonUtil.toJson(new PageGrid(NumberUtils.toInt(total + ""), list));
    model.put("message", message);
    return "message";
  }

}
