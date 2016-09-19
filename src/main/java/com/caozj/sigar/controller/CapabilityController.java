package com.caozj.sigar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.framework.model.json.JsonResult;
import com.caozj.sigar.service.CapabilityService;

/**
 * 性能Controller
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/capability")
public class CapabilityController {

  @Autowired
  private CapabilityService capabilityService;

  /**
   * 按照时间删除历史数据
   * 
   * @param startTime
   * @param endTime
   * @param model
   * @return
   */
  @RequestMapping
  public String deleteByTime(String startTime, String endTime, ModelMap model) {
    capabilityService.deleteByTime(startTime, endTime);
    model.put("message", new JsonResult().toJson());
    return "message";
  }


}
