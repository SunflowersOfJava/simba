package com.caozj.dao.permission;

import java.util.List;

import com.caozj.model.permission.OrgExt;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface OrgExtDao {

	List<String> showAllColumns();

	void addColumn(String column);

	OrgExt get(int orgID);
}
