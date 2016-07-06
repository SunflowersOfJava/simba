package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    return "processJobList";
  }
}
