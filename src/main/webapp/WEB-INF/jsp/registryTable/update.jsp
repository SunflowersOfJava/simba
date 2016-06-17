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
<body style="padding:0px;margin:0px">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="修改" style="width:700px">
		<div style="padding:10px 60px 20px 60px">
			<form id="registryTableForm" method="post">
				<input type="hidden" id="id" name="id" value="${registryTable.id}" />
				<table cellpadding="0" cellspacing="0" style="table-layout:fixed;">
					<tr>
						<td>code:</td>
						<td><input class="easyui-textbox" type="text" id="code" name="code" data-options="required:true" style="width:200px" value="${registryTable.code}"></input></td>
					</tr>
					<tr>
						<td>value:</td>
						<td><input class="easyui-textbox" type="text" id="value" name="value" data-options="required:true" style="width:200px" value="${registryTable.value}"></input></td>
					</tr>
					<tr>
						<td>typeID:</td>
						<td><input class="easyui-textbox" type="text" id="typeID" name="typeID" data-options="required:true" style="width:200px" value="${registryTable.typeID}"></input></td>
					</tr>
					<tr>
						<td>description:</td>
						<td><input class="easyui-textbox" type="text" id="description" name="description" data-options="required:true" style="width:200px" value="${registryTable.description}"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="RegistryTable.update();" data-options="iconCls:'icon-add'">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="RegistryTable.toList();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>
