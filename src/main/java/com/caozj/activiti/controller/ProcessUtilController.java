package com.caozj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.framework.model.json.JsonResult;
import com.caozj.model.permission.User;

/**
 * 流程工具Controller
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processUtil")
public class ProcessUtilController {

  /**
   * 获取当前用户对象
   * 
   * @param sessAccount
   * @param sessName
   * @param model
   * @return
   */
  @RequestMapping
  public String getCurrentUser(String sessAccount, String sessName, ModelMap model) {
    User user = new User();
    user.setAccount(sessAccount);
    user.setName(sessName);
    model.put("message", new JsonResult(user).toJson());
    return "message";
  }

}
