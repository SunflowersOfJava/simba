package com.caozj.menu.dao;

import java.util.List;

import com.caozj.menu.model.Menu;

/**
 * 菜单Dao
 * 
 * @author caozj
 * 
 */
public interface MenuDao {

	void add(Menu menu);

	void update(Menu menu);

	void delete(int id);

	List<Menu> listChildren(int parentID);

	int countChildren(int parentID);

	Menu get(int id);

}
