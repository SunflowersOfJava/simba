package com.caozj.taglib;

import com.caozj.framework.util.data.MathUtil;

public class TD {

  public static void main(String[] args) {
    double a = 123;
    double b = MathUtil.scale(a, 2);
    System.out.println(b);
  }

}
