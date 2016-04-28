package com.caozj.controller.permission;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.framework.model.ext.ExtPageGrid;
import com.caozj.framework.model.json.JsonResult;
import com.caozj.framework.session.SessionUtil;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.constant.ConstantData;
import com.caozj.model.permission.Role;
import com.caozj.model.permission.User;
import com.caozj.service.permission.RoleService;
import com.caozj.service.permission.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping("/list.do")
	public String list(ModelMap model) {
		return "permission/listUser";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<User> list = userService.page(page);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "permission/addUser";
	}

	@RequestMapping("/add.do")
	public String add(User user, ModelMap model) {
		userService.add(user);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(String account, HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException {
		User loginUser = SessionUtil.getUser(request.getSession());
		User user = null;
		boolean self = true;
		if (StringUtils.isNotEmpty(account)) {
			account = URLDecoder.decode(account, ConstantData.DEFAULT_CHARSET);
			user = userService.get(account);
			if (!loginUser.getAccount().equals(account)) {
				self = false;
			}
		} else {
			user = loginUser;
		}
		model.put("self", self);
		model.put("user", user);
		return "permission/updateUser";
	}

	@RequestMapping("/update.do")
	public String update(User user, ModelMap model) {
		userService.updateName(user.getAccount(), user.getName());
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(String[] accounts, ModelMap model) {
		List<String> accountList = Arrays.asList(accounts);
		userService.batchDelete(accountList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/resetPwd.do")
	public String resetPwd(String account, ModelMap model) {
		userService.resetPwd(account);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toModifyPwd.do")
	public String toModifyPwd() {
		return "user/modifyPwd";
	}

	@RequestMapping("/modifyPwd.do")
	public String modifyPwd(HttpServletRequest request, String oldPwd, String newPwd, String confirmPwd, ModelMap model) {
		if (!confirmPwd.equals(newPwd)) {
			throw new RuntimeException("确认密码和新密码不一致");
		}
		if (oldPwd.equals(newPwd)) {
			throw new RuntimeException("旧密码和新密码不能一样");
		}
		User user = SessionUtil.getUser(request.getSession());
		userService.updatePwd(user.getAccount(), oldPwd, newPwd);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toModifyInfo.do")
	public String toModifyInfo(HttpServletRequest request, ModelMap model) {
		User loginUser = SessionUtil.getUser(request.getSession());
		model.put("user", loginUser);
		return "user/modifyInfo";
	}

	@RequestMapping("/modifyInfo.do")
	public String modifyInfo(User user, HttpServletRequest request, ModelMap model) {
		User loginUser = SessionUtil.getUser(request.getSession());
		loginUser.setName(user.getName());
		userService.update(loginUser);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toAssignRole.do")
	public String toAssignRole(String account, ModelMap model) {
		List<Role> allRoleList = roleService.listAll();
		List<Role> assignRoleList = userService.listRoleByAccount(account);
		model.put("account", account);
		model.put("allRoleList", allRoleList);
		model.put("assignRoleList", assignRoleList);
		return "permission/assignRole";
	}

	@RequestMapping("/assignRole.do")
	public String assignRole(String[] roleName, String account, HttpServletRequest request, ModelMap model) {
		if (roleName == null || roleName.length == 0) {
			throw new RuntimeException("角色不能为空");
		}
		userService.assignRoles(account, Arrays.asList(roleName));
		model.put("message", new JsonResult().toJson());
		return "message";
	}

}
