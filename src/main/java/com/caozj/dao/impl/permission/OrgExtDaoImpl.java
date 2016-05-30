package com.caozj.dao.impl.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caozj.dao.permission.OrgExtDao;
import com.caozj.framework.util.jdbc.Jdbc;

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

}
