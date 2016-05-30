package com.caozj.service.impl.permission;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caozj.dao.permission.OrgDao;
import com.caozj.dao.permission.OrgExtDao;
import com.caozj.dao.permission.RoleDao;
import com.caozj.dao.permission.UserDao;
import com.caozj.dao.permission.UserExtDao;
import com.caozj.dao.permission.UserOrgDao;
import com.caozj.dao.permission.UserRoleDao;
import com.caozj.framework.util.common.EncryptUtil;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.permission.Org;
import com.caozj.model.permission.OrgExt;
import com.caozj.model.permission.Role;
import com.caozj.model.permission.User;
import com.caozj.model.permission.UserExt;
import com.caozj.model.permission.UserExtDesc;
import com.caozj.model.permission.UserRole;
import com.caozj.service.permission.UserService;

/**
 * 用户service实现
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Value("${key}")
	private String key;

	@Value("${user.ext}")
	private String userExt;

	@Value("${default.pwd}")
	private String defaultPwd;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserExtDao userExtDao;

	@Autowired
	private OrgDao orgDao;

	@Autowired
	private OrgExtDao orgExtDao;

	@Autowired
	private UserOrgDao userOrgDao;

	@Override
	public void add(User user) {
		user.setPwd(defaultPwd);
		userDao.add(user);
	}

	@Override
	public void delete(String account) {
		userDao.delete(account);
		userRoleDao.deleteByUserAccount(account);
	}

	@Override
	public void updateName(String account, String name) {
		userDao.updateName(account, name);
	}

	@Override
	public void updatePwd(String account, String pwd) {
		pwd = EncryptUtil.md5(pwd + key);
		userDao.updatePwd(account, pwd);
	}

	@Override
	public User get(String account) {
		return userDao.get(account);
	}

	@Override
	public List<User> page(Pager page) {
		return userDao.page(page);
	}

	@Override
	public boolean checkUser(String account, String pwd) {
		pwd = EncryptUtil.md5(pwd + key);
		return userDao.checkUser(account, pwd);
	}

	@Override
	public List<User> listAll() {
		return userDao.listAll();
	}

	@Override
	public void assignRole(String userAccount, String roleName) {
		userRoleDao.deleteByUserAccount(userAccount);
		userRoleDao.add(new UserRole(userAccount, roleName));
	}

	@Override
	public void updatePwd(String account, String oldPwd, String newPwd) {
		if (!checkUser(account, oldPwd)) {
			throw new RuntimeException("旧密码错误");
		}
		updatePwd(account, newPwd);
	}

	@Override
	public void resetPwd(String account) {
		userDao.updatePwd(account, defaultPwd);
	}

	@Override
	public void batchDelete(List<String> accountList) {
		for (String account : accountList) {
			delete(account);
		}
	}

	@Override
	public List<Role> listRoleByAccount(String account) {
		List<UserRole> userRoleList = userRoleDao.listByUserAccount(account);
		List<Role> roleList = new ArrayList<Role>(userRoleList.size());
		for (UserRole userRole : userRoleList) {
			String roleName = userRole.getRoleName();
			Role role = roleDao.get(roleName);
			if (role != null) {
				roleList.add(role);
			}
		}
		return roleList;
	}

	@Override
	public void assignRoles(String userAccount, List<String> roleNames) {
		userRoleDao.deleteByUserAccount(userAccount);
		for (String roleName : roleNames) {
			if (StringUtils.isNotEmpty(roleName)) {
				userRoleDao.add(new UserRole(userAccount, roleName));
			}
		}
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void checkAndCreateUserExt() {
		if (StringUtils.isBlank(userExt)) {
			return;
		}
		List<String> existColumns = userExtDao.showAllColumns();
		String[] ext = userExt.trim().split(",");
		for (String column : ext) {
			String[] kv = column.split(":");
			String key = kv[0];
			String value = kv[1];
			UserExtDesc.put(key, value);
			if (!existColumns.contains(key)) {
				userExtDao.addColumn(key);
			}
		}
	}

	@Override
	public UserExt getUserExt(String userAccount) {
		return userExtDao.get(userAccount);
	}

	@Override
	public List<Org> listOrgByUser(String userAccount) {
		return null;
	}

	@Override
	public List<OrgExt> listOrgExtByUser(String userAccount) {
		return null;
	}
}
