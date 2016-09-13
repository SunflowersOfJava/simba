package com.caozj.test;

import java.io.IOException;

import com.caozj.framework.util.common.StringUtil;

public class TCompress {

  public static void main(String[] args) throws IOException {
    String a =
        "i am 打算考虑附近 your brother,fyes oh My God!@adsfsfsfasdfasdffsfsfsfsfsf2234234234234234sfsfsasdfasdffsfsffgsffgfgfgfgfgsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsfsf好";
    System.out.println(a);
    
    String c = StringUtil.zip(a);
    System.out.println(c);
    String d = StringUtil.unzip(c);
    System.out.println(d);
    
    c = StringUtil.gzip(a);
    System.out.println(c);
    d = StringUtil.ungzip(c);
    System.out.println(d);
    
    for(int i =0;i<300000;i++){
      a += "a";
    }
    System.out.println(a.length());
    long now = System.currentTimeMillis();
    c = StringUtil.gzip(a);
    System.out.println("time:"+(System.currentTimeMillis() - now));
    System.out.println(c.length());
    now = System.currentTimeMillis();
    c = StringUtil.zip(a);
    System.out.println("time:"+(System.currentTimeMillis() - now));
    System.out.println(c.length());
  }
}
 