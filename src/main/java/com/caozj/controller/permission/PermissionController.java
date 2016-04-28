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
import com.caozj.service.permission.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/list.do")
	public String list() {
		return "permission/listPermission";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<Permission> list = permissionService.page(page);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "permission/addPermission";
	}

	@RequestMapping("/add.do")
	public String add(Permission permission, ModelMap model) {
		permissionService.add(permission);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(String name, ModelMap model) throws UnsupportedEncodingException {
		name = URLDecoder.decode(name, ConstantData.DEFAULT_CHARSET);
		Permission permission = permissionService.get(name);
		model.put("permission", permission);
		return "permission/updatePermission";
	}

	@RequestMapping("/update.do")
	public String update(Permission permission, ModelMap model) {
		permissionService.update(permission);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(String[] permissionNames, ModelMap model) {
		permissionService.batchDelete(Arrays.asList(permissionNames));
		model.put("message", new JsonResult().toJson());
		return "message";
	}
}
