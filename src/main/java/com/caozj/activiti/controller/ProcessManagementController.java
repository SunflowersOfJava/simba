package com.caozj.activiti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.caozj.activiti.vo.ProcessVo;
import com.caozj.controller.form.EasyUIPageForm;
import com.caozj.framework.model.easyui.PageGrid;
import com.caozj.framework.util.common.FileUtils;
import com.caozj.framework.util.common.JsonUtil;

/**
 * 流程管理Controller
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/processManagement")
public class ProcessManagementController {

  @Autowired
  private RepositoryService repositoryService;

  @RequestMapping
  public String list(String errorMsg, ModelMap model) {
    model.put("errorMsg", errorMsg);
    return "activiti/processManagementList";
  }

  @RequestMapping("/listDataOfEasyUI.do")
  public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, String processName) {
    List<ProcessDefinition> list = null;
    long total = 0;
    ProcessDefinitionQuery processDefinitionQuery =
        repositoryService.createProcessDefinitionQuery();
    if (StringUtils.isEmpty(processName)) {
      list = processDefinitionQuery.listPage((form.getPage() - 1) * form.getRows(), form.getRows());
      total = processDefinitionQuery.count();
    } else {
      list = processDefinitionQuery.processDefinitionNameLike("%" + processName + "%")
          .listPage((form.getPage() - 1) * form.getRows(), form.getRows());
      total = processDefinitionQuery.processDefinitionNameLike("%" + processName + "%").count();
    }
    List<ProcessVo> voList = new ArrayList<>(list.size());
    list.forEach((pd) -> {
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
      voList.add(vo);
    });
    String message = JsonUtil.toJson(new PageGrid(NumberUtils.toInt(total + ""), voList));
    model.put("message", message);
    return "message";
  }

  /**
   * 上传流程
   * 
   * @param processFile
   * @return
   * @throws IOException
   */
  @RequestMapping
  public String uploadProcess(MultipartFile processFile) throws IOException {
    String errorMsg = StringUtils.EMPTY;
    String fileName = processFile.getOriginalFilename();
    String fileType = FileUtils.getFileExt(fileName);
    // 检查文件类型
    if (!".xml".equals(fileType) && !".zip".equals(fileType) && !".bar".equals(fileType)
        && !".bpmn".equals(fileType)) {
      errorMsg = "流程文件类型错误";
      return "redirect:/processManagement/list.do?errorMsg=" + errorMsg;
    }
    // 部署流程
    DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
    InputStream inputStream = processFile.getInputStream();
    if (!".zip".equals(fileType) && !".bar".equals(fileType)) {
      deploymentBuilder.addInputStream(fileName, inputStream);
    } else {
      deploymentBuilder.addZipInputStream(new ZipInputStream(inputStream));
    }
    deploymentBuilder.deploy();
    return "redirect:/processManagement/list.do";
  }

}
