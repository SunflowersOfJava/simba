package com.caozj.controller.permission;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.controller.form.EasyUIPageForm;
import com.caozj.framework.model.easyui.PageGrid;
import com.caozj.framework.model.ext.ExtPageGrid;
import com.caozj.framework.model.json.JsonResult;
import com.caozj.framework.util.common.JsonUtil;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.permission.Permission;
import com.caozj.service.permission.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/list.do")
	public String list(ModelMap model) {
		return "permission/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<Permission> list = permissionService.page(page);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}
	
	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form) {
		Pager page = new Pager((form.getPage()-1) * form.getRows(), form.getRows());
		List<Permission> list = permissionService.page(page);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "permission/add";
	}

	@RequestMapping("/add.do")
	public String add(Permission permission, ModelMap model) {
		permissionService.add(permission);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model) {
		Permission permission = permissionService.get(id);
		model.put("permission", permission);
		return "permission/update";
	}

	@RequestMapping("/update.do")
	public String update(Permission permission, ModelMap model) {
		permissionService.update(permission);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		permissionService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}
	
	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		permissionService.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model) {
		Permission permission = permissionService.get(id);
		model.put("permission", permission);
		return "permission/show";
	}
	
	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		Permission permission = permissionService.get(id);
		model.put("message", new JsonResult(permission).toJson());
		return "message";
	}

}
