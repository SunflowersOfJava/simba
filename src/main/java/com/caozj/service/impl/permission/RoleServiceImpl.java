package com.caozj.service.impl.permission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caozj.dao.permission.PermissionDao;
import com.caozj.dao.permission.RoleDao;
import com.caozj.dao.permission.RolePermissionDao;
import com.caozj.dao.permission.UserRoleDao;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.permission.Permission;
import com.caozj.model.permission.Role;
import com.caozj.model.permission.RolePermission;
import com.caozj.service.permission.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public void add(Role role) {
		roleDao.add(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void delete(String name) {
		roleDao.delete(name);
		userRoleDao.deleteByRole(name);
		rolePermissionDao.deleteByRoleName(name);
	}

	@Override
	public List<Role> page(Pager page) {
		return roleDao.page(page);
	}

	@Override
	public Role get(String name) {
		return roleDao.get(name);
	}

	@Override
	public List<Role> listAll() {
		return roleDao.listAll();
	}

	@Override
	public void assignPermission(String roleName, List<Integer> permissionIDList) {
		rolePermissionDao.deleteByRoleName(roleName);
		for (Integer permissionID : permissionIDList) {
			if (permissionID != null) {
				rolePermissionDao.add(new RolePermission(roleName, permissionID));
			}
		}
	}

	@Override
	public void batchDelete(List<String> roleNameList) {
		for (String roleName : roleNameList) {
			delete(roleName);
		}
	}

	@Override
	public List<Permission> listByRole(String roleName) {
		List<RolePermission> rolePermissionList = rolePermissionDao.listBy("roleName", roleName);
		List<Permission> permissionList = new ArrayList<Permission>(rolePermissionList.size());
		for (RolePermission rolePermission : rolePermissionList) {
			Permission permission = permissionDao.get(rolePermission.getPermissionID());
			permissionList.add(permission);
		}
		return permissionList;
	}

}
