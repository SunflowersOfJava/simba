<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/registryTable.js"></script>
</head>

<body>
	<div style="margin: 20px 0;"></div>
	<div id="panel">
		<table id="registryTableTable"></table>
		<div id="registryTableToolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="RegistryTable.toAdd();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0);" class="easyui-linkbutton"
				onclick="RegistryTable.batchDelete();" data-options="iconCls:'icon-remove'">删除</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#panel").panel({
				height : $(document).height() - 50,
				width : $(document).width() - 20,
				title : "列表信息"
			});
			$("#registryTableTable").datagrid({
				url : contextPath + "/registryTable/listDataOfEasyUI.do",
				method : "post",
				animate : true,
				toolbar : "#registryTableToolbar",
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
					field : 'code',
					title : 'code',
					width : 150
				}
				, {
					field : 'value',
					title : 'value',
					width : 150
				}
				, {
					field : 'typeID',
					title : 'typeID',
					width : 150
				}
				, {
					field : 'description',
					title : 'description',
					width : 150
				}
				, {
					title : "操作",
					field : "oper",
					width : 250,
					formatter : function(value, row, index) {
						var html = "<a href=\"javascript:void(0)\" onclick=\"RegistryTable.toUpdate('" + row["id"] + "')\">修改</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0)\" onclick=\"RegistryTable.deleteRegistryTable('" + row["id"] + "')\">删除</a>";
						return html;
					}
				} ] ]
			});
		});
	</script>
</body>
</html>
