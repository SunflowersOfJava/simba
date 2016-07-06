package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程监控
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processMonitor")
public class ProcessMonitorController {

  @RequestMapping
  public String list() {
    return "processMonitorList";
  }
}
