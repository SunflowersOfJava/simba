<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<title>${pd.name}</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/activiti/process.js"></script>
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="流程信息" style="width: 800px">
		<div style="padding: 10px 60px 20px 60px">
				<table cellpadding="0" cellspacing="0" style="table-layout: fixed;">
					<tr>
						<td>流程ID:</td>
						<td>${pd.id}</td>
					</tr> 
					<tr>
						<td>流程Key:</td>
						<td>${pd.key}</td>
					</tr>
					<tr>
						<td>流程名称:</td>
						<td>${pd.name}</td>
					</tr>
					<c:if test="${not empty pd.description}">
						<tr>
							<td>流程描述:</td>
							<td>${pd.description}</td>
						</tr>
					</c:if>
					<tr>
						<td>流程发起人:</td>
						<td>${startUserName}</td>
					</tr>
				</table>
		</div>
	</div>
	<br/>
	<br/>
	<div class="easyui-panel" title="任务信息" style="width: 800px">
		<div style="padding: 10px 60px 20px 60px">
				<table cellpadding="0" cellspacing="0" style="table-layout: fixed;">
					<tr>
						<td>任务ID:</td>
						<td>${task.id}</td>
					</tr> 
					<tr>
						<td>任务名称:</td>
						<td>${task.name}</td>
					</tr>
					<tr>
						<td>创建时间:</td>
						<td><fmt:formatDate value="${task.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<c:if test="${not empty task.assignee}">
						<tr>
							<td>办理人:</td>
							<td>${task.assignee}</td>
						</tr>
					</c:if>
					<c:if test="${not empty task.description}">
						<tr>
							<td>任务描述:</td>
							<td>${task.description}</td>
						</tr>
					</c:if>
				</table>
		</div>
	</div>
	<br/>
	<br/>
	<div class="easyui-panel" title="表单数据" style="width: 800px">
		<div style="padding: 10px 60px 20px 60px">
			<form id="processForm" method="post">
				<input type="hidden" id="taskId" name="taskId" value="${task.id}"/>
				${taskForm}
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Process.backTaskDone();"	data-options="iconCls:'icon-cancel'">返回</a>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		
	</script>
</body>
</html>
