package com.caozj.dao.impl.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caozj.dao.permission.UserExtDao;
import com.caozj.framework.util.jdbc.Jdbc;
import com.caozj.model.permission.UserExt;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class UserExtDaoImpl implements UserExtDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "userExt";

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
	public UserExt get(String userAccount) {
		String sql = "select * from " + table + " where userAccount = ? ";
		Map<String, Object> ext = jdbc.queryForMap(sql, userAccount);
		UserExt userExt = new UserExt();
		userExt.setUserAccount(userAccount);
		Map<String, String> extMap = new HashMap<>();
		userExt.setExtMap(extMap);
		if (ext != null && ext.size() > 0) {
			ext.forEach((key, value) -> {
				extMap.put(key, value + "");
			});
		}
		return userExt;
	}

}
