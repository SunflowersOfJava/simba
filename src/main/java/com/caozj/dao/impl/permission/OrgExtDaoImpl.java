package com.caozj.dao.impl.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caozj.dao.permission.OrgExtDao;
import com.caozj.framework.util.jdbc.Jdbc;
import com.caozj.model.permission.OrgExt;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class OrgExtDaoImpl implements OrgExtDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "orgExt";

	@Override
	public List<String> showAllColumns() {
		String sql = "select column_name from information_schema.COLUMNS where table_name = ?";
		return jdbc.queryForStrings(sql, table);
	}

	@Override
	public void addColumn(String column) {
		String sql = "ALTER TABLE " + table + " ADD " + column + " varchar(256)";
		jdbc.updateForBoolean(sql);
	}

	@Override
	public OrgExt get(int orgID) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, OrgExt.class, orgID);
	}
}
