package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 待办任务
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processDoing")
public class ProcessDoingController {

  @RequestMapping
  public String list() {
    return "activiti/processDoingList";
  }
}
