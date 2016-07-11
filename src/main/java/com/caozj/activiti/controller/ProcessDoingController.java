package com.caozj.activiti.controller;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.controller.form.EasyUIPageForm;

/**
 * 待办任务
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processDoing")
public class ProcessDoingController {

  @Autowired
  private TaskService taskService;

  @RequestMapping
  public String list() {
    return "activiti/processDoingList";
  }

  @RequestMapping("/listDataOfEasyUI.do")
  public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, String sessAccount) {

    return "message";
  }
}
