package com.caozj.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.framework.util.code.BarCodeUtil;

/**
 * 条形码Controllor
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/barCode")
public class BarCodeController {

  /**
   * 获取条形码
   * 
   * @param text
   * @param response
   * @throws IOException
   */
  @RequestMapping
  public void getBarCode(String text, HttpServletResponse response) throws IOException {
    ServletOutputStream sos = response.getOutputStream();
    BarCodeUtil.writeToStream(sos, text);
    sos.close();
  }
}
