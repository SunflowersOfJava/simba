package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 代办设置
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processAgencySet")
public class ProcessAgencySetController {

  @RequestMapping
  public String list() {
    return "activiti/processAgencySetList";
  }

}
