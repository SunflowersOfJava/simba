package com.caozj.dao;

import java.util.List;

import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.DemoGener;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface DemoGenerDao {

	void add(DemoGener demoGener);

	void update(DemoGener demoGener);

	void delete(int id);

	List<DemoGener> listAll();

	int count();
	
	List<DemoGener> page(Pager page);

	DemoGener get(int id);

}
