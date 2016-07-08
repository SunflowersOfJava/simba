var ProcessManagement = {

	"toAdd" : function() {
		$("#processWindow").window('open');
	},

	"batchDelete" : function() {

	},

	"search" : function() {
		$("#table").datagrid("load", {
			processName : $("#processName").val()
		});
	},

	"showView" : function(id) {

	},

	"showXml" : function(id) {

	},

	"deleteProcess" : function(id) {

	},

	"stop" : function(id) {

	},

	"start" : function(id) {

	},

	"uploadProcessFile" : function() {
		var file = $("[name=processFile]").val();
		if (file == "") {
			$.messager.alert("系统提示", "请选择流程文件", 'warning');
			return false;
		}
		var fileType = file.substring(file.lastIndexOf('.') + 1, file.length)
				.toLowerCase();
		if (fileType != "xml" && fileType != "zip" && fileType != "bar"
				&& fileType != "bpmn") {
			$.messager.alert("系统提示", "请选择正确的流程文件", 'warning');
			return false;
		}
		if (!$("#uploadProcessForm").form("validate")) {
			return false;
		}
		$("#uploadProcessForm").attr("action",
				contextPath + "/processManagement/uploadProcess.do");
		$("#uploadProcessForm").submit();
	},

	"cancelUploadProcess" : function() {
		$("#processWindow").window('close');
	},

	"end" : null
};