package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.controller.form.EasyUIPageForm;

/**
 * 作业管理
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("processJob")
public class ProcessJobControlle {

  @RequestMapping
  public String list() {
    return "activiti/processJobList";
  }


  @RequestMapping("/listDataOfEasyUI.do")
  public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form) {

    return "message";
  }
}
