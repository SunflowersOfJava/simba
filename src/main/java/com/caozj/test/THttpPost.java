package com.caozj.test;

import com.caozj.framework.util.http.HttpClientUtil;

public class THttpPost {
  
  public static void main(String[] args) {
    String url ="http://192.168.1.132:8080/nrms/login/login/admin/admin";
   String s= HttpClientUtil.post(url);
   System.out.println(s);
  }

}
