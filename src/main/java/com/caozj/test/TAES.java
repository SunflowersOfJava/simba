package com.caozj.test;

import java.io.File;

import com.caozj.framework.util.code.AESUtil;

public class TAES {

  public static void main(String[] args) throws Exception {
    String key = "test";
    String source = "我是你大哥,oh my god!!!";
    String m = AESUtil.encrypt(source, key);
    System.out.println(m);
    System.out.println(AESUtil.decrypt(m, key));
    AESUtil.encrypt(new File("D://1.txt"), new File("D://2.txt"), key);
    AESUtil.decrypt(new File("D://2.txt"), new File("D://3.txt"), key);
  }

}
