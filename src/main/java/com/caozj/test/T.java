package com.caozj.test;

import java.util.ArrayList;
import java.util.List;

import com.caozj.framework.util.common.FastJsonUtil;
import com.caozj.framework.util.common.JsonUtil;
import com.caozj.model.Menu;

public class T {
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.setId(1);
		menu.setText("菜单");
		menu.setOrderNo(12);
		menu.setState("状态");
		menu.setUrl("url");
		menu.setLeaf(true);
		menu.setParentID(100);
		System.out.println(JsonUtil.toJson(menu));
		Menu menu2 = new Menu();
		menu2.setId(1);
		menu2.setText("菜单2");
		menu2.setOrderNo(122);
		menu2.setState("状态2");
		menu2.setUrl("url2");
		List<Menu> list = new ArrayList<>();
		list.add(menu);
		list.add(menu2);
		System.out.println(JsonUtil.toJson(list));
		List<Menu> ml = JsonUtil.toList(JsonUtil.toJson(list), Menu.class);
		System.out.println(ml.toString());
		Menu m = JsonUtil.toObject(JsonUtil.toJson(menu), Menu.class);
		System.out.println(m.toString());
	}
}
