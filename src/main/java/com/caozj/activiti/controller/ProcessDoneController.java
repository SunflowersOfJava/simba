package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
