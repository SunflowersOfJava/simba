package com.caozj.test;

import com.caozj.framework.util.common.SerializeUtil;

public class T {
	
	public static void main(String[] args) {
		String s  ="sw d 借口了接口连接";
		byte[] a = SerializeUtil.serialize(s);
		String t = (String)SerializeUtil.unserialize(a);
		System.out.println(a);
		System.out.println(t);
	}
	
}
