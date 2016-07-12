package com.caozj.activiti.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caozj.activiti.form.TaskSearchForm;
import com.caozj.activiti.vo.TaskVo;
import com.caozj.controller.form.EasyUIPageForm;
import com.caozj.framework.model.easyui.PageGrid;
import com.caozj.framework.util.common.JsonUtil;
import com.caozj.model.constant.ConstantData;

/**
 * 已办任务
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processDone")
public class ProcessDoneController {

  @Autowired
  private HistoryService historyService;

  @RequestMapping
  public String list() {
    return "activiti/processDoneList";
  }

  @RequestMapping("/listDataOfEasyUI.do")
  public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, String sessAccount,
      TaskSearchForm searchForm) {
    HistoricTaskInstanceQuery query =
        historyService.createHistoricTaskInstanceQuery().taskAssignee(sessAccount);
    if (StringUtils.isNotEmpty(searchForm.getProcessName())) {
      query.processDefinitionNameLike("%" + searchForm.getProcessName() + "%");
    }
    if (StringUtils.isNotEmpty(searchForm.getTaskName())) {
      query.taskNameLike("%" + searchForm.getTaskName() + "%");
    }
    query = query.orderByHistoricTaskInstanceEndTime().desc();
    List<HistoricTaskInstance> list =
        query.listPage((form.getPage() - 1) * form.getRows(), form.getRows());
    int total = NumberUtils.toInt(query.count() + "");
    List<TaskVo> voList = new ArrayList<>(list.size());
    SimpleDateFormat format = new SimpleDateFormat(ConstantData.TIME_FORMAT);
    list.forEach((task) -> {
      TaskVo vo = new TaskVo();
      vo.setId(task.getId());
      vo.setOwner(task.getOwner());
      vo.setAssignee(task.getAssignee());
      vo.setParentTaskId(task.getParentTaskId());
      vo.setName(task.getName());
      vo.setDescription(task.getDescription());
      vo.setCreateTime(format.format(task.getCreateTime()));
      vo.setStartTime(format.format(task.getStartTime()));
      if (task.getEndTime() != null) {
        vo.setEndTime(format.format(task.getEndTime()));
      }
      vo.setProcessDefinitionId(task.getProcessDefinitionId());
      vo.setProcessInstanceId(task.getProcessInstanceId());
      voList.add(vo);
    });
    String message = JsonUtil.toJson(new PageGrid(total, voList));
    model.put("message", message);
    return "message";
  }
}
