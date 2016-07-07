package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 启动流程
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processStart")
public class ProcessStartController {

  @RequestMapping
  public String list() {
    return "activiti/processStartList";
  }

}
