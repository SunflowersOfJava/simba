var ${className} = {

	"toAdd" : function() {
		<#if ${pageType=="treeTable"}>
			window.self.location.href = contextPath + "/${firstLower}/toAdd.do?parentID=" + $("#parentID").val();
		</#if> 
		<#if ${pageType!="treeTable"}>
			window.self.location.href = contextPath + "/${firstLower}/toAdd.do";
		</#if> 
	},

	"batchDelete" : function() {
		var idArray = new Array();
		var selected${className}s = $("#${firstLower}Table").datagrid("getSelections");
		$.each(selected${className}s, function(i, ${firstLower}) {
			idArray.push(${firstLower}.id);
		});
		if (idArray.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的行", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/${firstLower}/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				id : idArray.join(",")
			},
			success : function(data) {
				if (data.code == 200) {
					$("#${firstLower}Table").datagrid("load", {
						id : $("#parentID").val()
					});
					var parentNode = $("#${firstLower}Tree").tree("find", $("#parentID").val());
					$("#${firstLower}Tree").tree("reload", parentNode.target);
					$.messager.alert("系统提示", "删除成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "删除失败", 'error');
			}
		});
	},

	"select${className}" : function(node) {
		var id = node.id;
		$("#parentID").val(id);
		$("#${firstLower}Table").datagrid("load", {
			id : id
		});
		var name = node.text + "--子菜单";
		$(".layout-panel-center .panel-title").html(name);
	},

	"toUpdate" : function(id) {
		window.self.location.href = contextPath + "/${firstLower}/toUpdate.do?id=" + id;
	},

	"delete${className}" : function(id) {
		$.ajax({
			url : contextPath + "/${firstLower}/delete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				id : id
			},
			success : function(data) {
				if (data.code == 200) {
					$("#${firstLower}Table").datagrid("load", {
						id : $("#parentID").val()
					});
					var parentNode = $("#${firstLower}Tree").tree("find", $("#parentID").val());
					$("#${firstLower}Tree").tree("reload", parentNode.target);
					$.messager.alert("系统提示", "删除成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "删除失败", 'error');
			}
		});
	},

	"add" : function() {
		$("#${firstLower}Form").form('submit', {
			url : contextPath + "/${firstLower}/add.do?json",
			onSubmit : function() {
				return $("#${firstLower}Form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					${className}.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"update" : function() {
		$("#${firstLower}Form").form('submit', {
			url : contextPath + "/${firstLower}/update.do?json",
			onSubmit : function() {
				return $("#${firstLower}Form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					${className}.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/${firstLower}/list.do";
	},

	"end" : null

};