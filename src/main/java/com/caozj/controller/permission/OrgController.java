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
import com.caozj.model.permission.Org;
import com.caozj.service.permission.OrgService;

@Controller
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private OrgService orgService;

	@RequestMapping("/list.do")
	public String list(ModelMap model) {
		return "org/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<Org> list = orgService.page(page);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}
	
	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form) {
		Pager page = new Pager((form.getPage()-1) * form.getRows(), form.getRows());
		List<Org> list = orgService.page(page);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "org/add";
	}

	@RequestMapping("/add.do")
	public String add(Org org, ModelMap model) {
		orgService.add(org);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model) {
		Org org = orgService.get(id);
		model.put("org", org);
		return "org/update";
	}

	@RequestMapping("/update.do")
	public String update(Org org, ModelMap model) {
		orgService.update(org);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		orgService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}
	
	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		orgService.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model) {
		Org org = orgService.get(id);
		model.put("org", org);
		return "org/show";
	}
	
	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		Org org = orgService.get(id);
		model.put("message", new JsonResult(org).toJson());
		return "message";
	}

}
