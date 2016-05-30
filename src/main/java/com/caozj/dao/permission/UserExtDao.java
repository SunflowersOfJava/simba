package com.caozj.dao.permission;

import java.util.List;

import com.caozj.model.permission.UserExt;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface UserExtDao {

	List<String> showAllColumns();

	void addColumn(String column);

	UserExt get(String userAccount);
}
