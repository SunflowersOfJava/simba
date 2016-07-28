<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>任务管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/job.js"></script>
</head>

<body>
	<div style="margin: 20px 0;"></div>
	<div id="panel">
		<table id="jobTable"></table>
		<div id="jobToolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="Job.toAdd();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0);" class="easyui-linkbutton"
				onclick="Job.batchDelete();" data-options="iconCls:'icon-remove'">删除</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#panel").panel({
				height : $(document).height() - 50,
				width : $(document).width() - 20,
				title : "任务列表信息"
			});
			$("#jobTable").datagrid({
				url : contextPath + "/job/listDataOfEasyUI.do",
				method : "post",
				animate : true,
				toolbar : "#jobToolbar",
				singleSelect : false,
				pagination : true,
				idField : "id",
				loadMsg : "正在加载数据，请耐心等待...",
				rownumbers : true,
				columns : [ [ {
					title : "全选",
					field : "ck",
					checkbox : true
				}
				, {
					field : 'name',
					title : '名称',
					width : 150
				}
				, {
					field : 'description',
					title : '描述',
					width : 150
				}
				, {
					field : 'cronExpression',
					title : 'cron表达式',
					width : 150
				}
				, {
					field : 'startTime',
					title : '开始执行时间',
					width : 150
				}
				, {
					field : 'endTime',
					title : '结束执行时间',
					width : 150
				}
				, {
					field : 'exeCount',
					title : '执行次数',
					width : 150
				}
				, {
					field : 'maxExeCount',
					title : '最大执行次数',
					width : 150
				}
				, {
					field : 'status',
					title : '状态',
					width : 150
				}
				, {
					field : 'className',
					title : '完整类路径',
					width : 150
				}
				, {
					field : 'methodName',
					title : '执行类方法名',
					width : 150
				}
				, {
					field : 'delayTime',
					title : '延迟时间',
					width : 150
				}
				, {
					field : 'intervalTime',
					title : '间隔时间',
					width : 150
				}
				, {
					title : "操作",
					field : "oper",
					width : 250,
					formatter : function(value, row, index) {
						var html = "<a href=\"javascript:void(0)\" onclick=\"Job.toUpdate('" + row["id"] + "')\">修改</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0)\" onclick=\"Job.deleteJob('" + row["id"] + "')\">删除</a>";
						return html;
					}
				} ] ]
			});
		});
	</script>
</body>
</html>
