<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>作业管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/activiti/processJob.js"></script>
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div id="panel">
		<table id="table"></table>
		<div id="toolbar">
				<label>流程名称:</label> <input type="text" id="processName" name="processName" class="easyui-textbox" prompt="请输入您要查询的流程名称" />
				<label>任务名称:</label> <input type="text" id="taskName" name="taskName" class="easyui-textbox" prompt="请输入您要查询的任务名称" />
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="ProcessDoing.search();" data-options="iconCls:'icon-search'">查询</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#panel").panel({
				height : $(document).height() - 50,
				width : $(document).width() - 20,
				title : "作业管理"
			});
			$("#table").datagrid({
				url : contextPath + "/processJob/listDataOfEasyUI.do",
				method : "post",
				animate : true,
				toolbar : "#toolbar",
				singleSelect : true,
				pagination : true,
				idField : "id",
				loadMsg : "正在加载数据，请耐心等待...",
				rownumbers : true,
				queryParams : {
					processName : $("#processName").val(),
					taskName : $("#taskName").val()
				},
				columns : [ [ {
					field : 'taskID',
					title : '任务ID',
					width : 150,
					formatter : function(value, row, index) {
						return row.id;
					}
				}, {
					field : 'name',
					title : '任务名称',
					width : 150
				}, {
					field : 'createTime',
					title : '创建时间',
					width : 150
				}, {
					field : 'assignee',
					title : '办理人',
					width : 100
				}, {
					field : 'processInstanceId',
					title : '流程实例ID',
					width : 100
				}, {
					field : 'processDefinitionId',
					title : '流程ID',
					width : 200
				}, {
					field : 'description',
					title : '描述',
					width : 150
				}, {
					title : "操作",
					field : "oper",
					width : 250,
					formatter : function(value, row, index) {
						var html = "<a href=\"javascript:void(0)\" onclick=\"ProcessDoing.toDeal('" + row["id"] + "')\">办理</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0)\" onclick=\"ProcessManagement.showView('" + row["processDefinitionId"] + "')\">查看流程图</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0)\" onclick=\"ProcessManagement.showXml('" + row["processDefinitionId"] + "')\">查看XML</a>";
						return html;
					}
				} ] ]
			});
			
		});
	</script>
</body>
</html>
