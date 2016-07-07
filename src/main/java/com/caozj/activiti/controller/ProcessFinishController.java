package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 已归档流程
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processFinish")
public class ProcessFinishController {

  @RequestMapping
  public String list() {
    return "activiti/processFinishList";
  }
}
