package com.caozj.controller.permission;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.framework.model.ext.ExtPageGrid;
import com.caozj.framework.model.json.JsonResult;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.constant.ConstantData;
import com.caozj.model.permission.Permission;
import com.caozj.model.permission.Role;
import com.caozj.service.permission.PermissionService;
import com.caozj.service.permission.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/list.do")
	public String list() {
		return "permission/listRole";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<Role> list = roleService.page(page);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "permission/addRole";
	}

	@RequestMapping("/add.do")
	public String add(Role role, ModelMap model) {
		roleService.add(role);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(String name, ModelMap model) throws UnsupportedEncodingException {
		name = URLDecoder.decode(name, ConstantData.DEFAULT_CHARSET);
		Role role = roleService.get(name);
		model.put("role", role);
		return "permission/updateRole";
	}

	@RequestMapping("/update.do")
	public String update(Role role, ModelMap model) {
		roleService.update(role);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(String[] roleNames, ModelMap model) {
		roleService.batchDelete(Arrays.asList(roleNames));
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toAssignPermission.do")
	public String toAssignPermission(String roleName, ModelMap model) {
		List<Permission> allPermissionList = permissionService.listAll();
		List<Permission> assignPermissionList = roleService.listByRole(roleName);
		model.put("roleName", roleName);
		model.put("allPermissionList", allPermissionList);
		model.put("assignPermissionList", assignPermissionList);
		return "permission/assignPermission";
	}

	@RequestMapping("/assignPermission.do")
	public String assignPermission(Integer[] permissionID, String roleName, ModelMap model) {
		if (permissionID.length == 0) {
			throw new RuntimeException("权限不能为空");
		}
		roleService.assignPermission(roleName, Arrays.asList(permissionID));
		model.put("message", new JsonResult().toJson());
		return "message";
	}
}
