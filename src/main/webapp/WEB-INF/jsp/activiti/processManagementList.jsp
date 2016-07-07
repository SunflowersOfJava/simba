<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>流程管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/activiti/processManagement.js"></script>
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div id="panel">
		<table id="table"></table>
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="ProcessManagement.toAdd();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0);" class="easyui-linkbutton"
				onclick="ProcessManagement.batchDelete();" data-options="iconCls:'icon-remove'">删除</a>
				<label>流程名称:</label> <input type="text" id="processName" name="processName" class="easyui-textbox" prompt="请输入您要查询的流程名称" /> <a
				href="javascript:void(0);" class="easyui-linkbutton" onclick="ProcessManagement.search();" data-options="iconCls:'icon-search'">查询</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#panel").panel({
				height : $(document).height() - 50,
				width : $(document).width() - 20,
				title : "流程信息"
			});
			$("#table").datagrid({
				url : contextPath + "/processManagement/listDataOfEasyUI.do",
				method : "post",
				animate : true,
				toolbar : "#toolbar",
				singleSelect : false,
				pagination : true,
				idField : "id",
				loadMsg : "正在加载数据，请耐心等待...",
				rownumbers : true,
				queryParams : {
					processName : $("#processName").val()
				},
				columns : [ [ {
					title : "全选",
					field : "ck",
					checkbox : true
				}, {
					field : 'processID',
					title : '流程ID',
					width : 150,
					formatter : function(value, row, index) {
						return row.id;
					}
				}, {
					field : 'key',
					title : '流程主键',
					width : 150
				}, {
					field : 'name',
					title : '名称',
					width : 150
				}, {
					field : 'description',
					title : '描述',
					width : 300
				}, {
					title : "操作",
					field : "oper",
					width : 250,
					formatter : function(value, row, index) {
						var html = "<a href=\"javascript:void(0)\" onclick=\"ProcessManagement.showView('" + row["id"] + "')\">查看流程图</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0)\" onclick=\"ProcessManagement.deleteProcess('" + row["id"] + "')\">删除</a>";
						html += "&nbsp;&nbsp;";
						if(row.suspensionState==1){
							html += "<a href=\"javascript:void(0)\" onclick=\"ProcessManagement.stop('" + row["id"] + "')\">暂停</a>";
						}else{
							html += "<a href=\"javascript:void(0)\" onclick=\"ProcessManagement.start('" + row["id"] + "')\">启动</a>";
						}
						return html;
					}
				} ] ]
			});
			
		});
	</script>
</body>
</html>
