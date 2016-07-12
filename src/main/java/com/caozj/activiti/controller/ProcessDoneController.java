package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.activiti.form.TaskSearchForm;
import com.caozj.controller.form.EasyUIPageForm;

/**
 * 已办任务
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processDone")
public class ProcessDoneController {

  @RequestMapping
  public String list() {
    return "activiti/processDoneList";
  }

  @RequestMapping("/listDataOfEasyUI.do")
  public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, String sessAccount,
      TaskSearchForm searchForm) {
    model.put("message", "");
    return "message";
  }
}
