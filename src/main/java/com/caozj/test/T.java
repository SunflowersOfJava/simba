package com.caozj.test;

import java.util.Date;

import com.caozj.framework.util.common.FastJsonUtil;

public class T {
	public static void main(String[] args) {
		B b = new B();
		A a = new A();
		a.setBirthday(new Date());
		a.setId(312);
		a.setName("a");
		b.setA(a);
		b.setbName("asf");
		b.setD(new Date());
		b.setEn(En.TEST);
		String json = FastJsonUtil.toJson(b);
		System.out.println(json);
		B b2 = FastJsonUtil.toObject(json, B.class);
		System.out.print(b2.toString());
	}
}
