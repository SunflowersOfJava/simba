package com.caozj.dao.permission;

import java.util.List;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface UserExtDao {

	List<String> showAllColumns();

	void addColumn(String column);
}
