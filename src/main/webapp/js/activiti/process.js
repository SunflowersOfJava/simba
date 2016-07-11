var Process = {

	"saveStart" : function() {
		$("#processForm").form('submit', {
			url : contextPath + "/process/saveStart.do?json",
			onSubmit : function() {
				return $("#processForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					$.messager.alert("系统提示", "保存成功", "info");
					Process.cancelStart();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"submitStart" : function() {
		$("#processForm").form('submit', {
			url : contextPath + "/process/submitStart.do?json",
			onSubmit : function() {
				return $("#processForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					$.messager.alert("系统提示", "发送成功", "info");
					Process.cancelStart();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"cancelStart" : function() {
		window.self.location.href = contextPath + "/processStart/list.do";
	},

	"end" : null

};