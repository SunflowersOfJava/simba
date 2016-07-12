package com.caozj.activiti.util;

import java.text.SimpleDateFormat;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;

import com.caozj.activiti.vo.ActivityVo;
import com.caozj.activiti.vo.ProcessVo;
import com.caozj.activiti.vo.TaskVo;
import com.caozj.model.constant.ConstantData;

/**
 * 工作流对象工具栏
 * 
 * @author caozj
 *
 */
public class ActivitiObjectUtil {

  private static final SimpleDateFormat format = new SimpleDateFormat(ConstantData.TIME_FORMAT);

  /**
   * 从Task对象转化成Vo对象
   * 
   * @param task
   * @return
   */
  public static TaskVo buildTaskVo(Task task) {
    TaskVo vo = new TaskVo();
    vo.setId(task.getId());
    vo.setOwner(task.getOwner());
    vo.setAssignee(task.getAssignee());
    vo.setParentTaskId(task.getParentTaskId());
    vo.setName(task.getName());
    vo.setDescription(task.getDescription());
    vo.setCreateTime(format.format(task.getCreateTime()));
    vo.setProcessDefinitionId(task.getProcessDefinitionId());
    vo.setProcessInstanceId(task.getProcessInstanceId());
    return vo;
  }

  /**
   * 从历史Task对象转化成Vo对象
   * 
   * @param historyTask
   * @return
   */
  public static TaskVo buildTaskVo(HistoricTaskInstance historyTask) {
    TaskVo vo = new TaskVo();
    vo.setId(historyTask.getId());
    vo.setOwner(historyTask.getOwner());
    vo.setAssignee(historyTask.getAssignee());
    vo.setParentTaskId(historyTask.getParentTaskId());
    vo.setName(historyTask.getName());
    vo.setDescription(historyTask.getDescription());
    vo.setCreateTime(format.format(historyTask.getCreateTime()));
    vo.setStartTime(format.format(historyTask.getStartTime()));
    if (historyTask.getEndTime() != null) {
      vo.setEndTime(format.format(historyTask.getEndTime()));
    }
    vo.setProcessDefinitionId(historyTask.getProcessDefinitionId());
    vo.setProcessInstanceId(historyTask.getProcessInstanceId());
    return vo;
  }

  /**
   * 将历史活动对象转化成Vo对象
   * 
   * @param activity
   * @return
   */
  public static ActivityVo buildActivityVo(HistoricActivityInstance activity) {
    ActivityVo vo = new ActivityVo();
    vo.setActivityId(activity.getActivityId());
    vo.setActivityName(activity.getActivityName());
    vo.setActivityType(activity.getActivityType());
    vo.setAssignee(activity.getAssignee());
    vo.setDurationInMillis(activity.getDurationInMillis());
    if (activity.getEndTime() != null) {
      vo.setEndTime(format.format(activity.getEndTime()));
    }
    vo.setId(activity.getId());
    vo.setProcessInstanceId(activity.getProcessInstanceId());
    vo.setStartTime(format.format(activity.getStartTime()));
    vo.setTaskId(activity.getTaskId());
    return vo;
  }

  /**
   * 将流程对象转化成Vo对象
   * 
   * @param pd
   * @return
   */
  public static ProcessVo buildProcessVo(ProcessDefinition pd) {
    ProcessVo vo = new ProcessVo();
    vo.setId(pd.getId());
    vo.setName(pd.getName());
    vo.setKey(pd.getKey());
    vo.setDeploymentId(pd.getDeploymentId());
    vo.setDescription(pd.getDescription());
    vo.setDiagramResourceName(pd.getDiagramResourceName());
    vo.setVersion(pd.getVersion());
    vo.setCategory(pd.getCategory());
    vo.setResourceName(pd.getResourceName());
    vo.setSuspended(pd.isSuspended());
    return vo;
  }

}