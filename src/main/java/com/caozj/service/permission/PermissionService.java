package com.caozj.service.permission;

import java.util.List;

import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.permission.Permission;

public interface PermissionService {

	void add(Permission permission);

	void update(Permission permission);

	void delete(String name);

	void batchDelete(List<String> names);

	List<Permission> listAll();

	List<Permission> page(Pager page);

	Permission get(String name);

}
