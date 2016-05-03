package com.caozj.test.eight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.caozj.framework.util.common.ListUtil;
import com.caozj.test.A;

public class TEight {

	public static void main(String[] args) {
		// testInteface();
		// testLabmda();
		// testStream();
		// testJoin();
		// testMap();
	}

	private static void testMap() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "a");
		map.put("2", "b");
		map.forEach((key, value) -> {
			System.out.println("key:" + key + ",value=" + value);
		});
	}

	private static void testJoin() {
		List<String> l = new ArrayList<>();
		l.add("a");
		l.add("b");
		l.add("c");
		l.add("d");
		System.out.println(ListUtil.join(l));
		System.out.println(ListUtil.join(l, "#"));
		l.clear();
		System.out.println(ListUtil.join(l));
		l = null;
		System.out.println(ListUtil.join(l));
	}

	public static void testInteface() {
		EInterface ei = new EImp();
		System.out.println(ei.a("a"));
		System.out.println(ei.hello("a"));
	}

	public static void testLabmda() {
		int n = 90;
		List<A> l = new ArrayList<>();
		l.add(new A(new Date(System.currentTimeMillis() + 10000)));
		l.add(new A(new Date(System.currentTimeMillis() + 100000)));
		l.add(new A(new Date(System.currentTimeMillis() + 1000000)));
		l.add(new A(new Date(System.currentTimeMillis() + 10000000)));
		Collections.sort(l, (x, y) -> {
			int a = n + 100;
			return x.getBirthday().before(y.getBirthday()) ? -1 : 1;
		});
		System.out.println(l);
		Comparator<A> c = (A x, A y) -> {
			return x.getBirthday().before(y.getBirthday()) ? -1 : 1;
		};
	}

	private static void testStream() {
		List<A> l = new ArrayList<>();
		l.add(new A(new Date(System.currentTimeMillis() + 10000)));
		l.add(new A(new Date(System.currentTimeMillis() + 100000)));
		l.add(new A(new Date(System.currentTimeMillis() + 1000000)));
		l.add(new A(new Date(System.currentTimeMillis() + 10000000)));
		l.stream().sorted((a, b) -> {
			return a.getBirthday().compareTo(b.getBirthday());
		}).forEach((a) -> {
			System.out.println(a.getBirthday().getTime());
		});
	}
}
