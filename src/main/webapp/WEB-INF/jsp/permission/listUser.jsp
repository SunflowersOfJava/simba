<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>用户管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/ext.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/user.js"></script>
</head>
<body>
	<div id="list"></div>
	<script type="text/javascript">
		Ext.onReady(function() {
			var url = contextPath + "/user/listDataOfExt.do";
			var pageSize = 10;
			var fields = [ {
				name : "account",
				type : "string"
			}, {
				name : "name",
				type : "string"
			} ];
			var cmModels = [ {
				header : '账号',
				dataIndex : 'account',
				width : 100,
				sortable : true
			}, {
				header : '用户名',
				dataIndex : 'name',
				width : 100,
				sortable : true
			}, {
				header : "操作",
				renderer : renderOper
			} ];
			var type = "checkbox";
			var id = "userList";
			var field = "account";
			var tbar = [ {
				xtype : 'button',
				text : '新增',
				handler : function() {
					User.toAdd();
				}
			}, '-', {
				xtype : 'button',
				text : '删除',
				handler : function() {
					User.batchDelete();
				}
			} ];
			ExtGridUtil.loadPageGrid(url, pageSize, fields, cmModels, '用户信息', "list", tbar, 0, id, type, field);
		});

		function renderOper(value, cellmeta, record, rowIndex, columnIndex, store) {
			var html = "<a href=\"javascript:void(0)\" onclick=\"User.toUpdate('" + record.data["account"] + "')\">修改</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"User.deleteAccount('" + record.data["account"] + "')\">删除</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"User.resetPwd('" + record.data["account"] + "')\">重置密码</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"User.toAssignRole('" + record.data["account"] + "')\">分配角色</a>";
			return html;
		}
	</script>
</body>
</html>
