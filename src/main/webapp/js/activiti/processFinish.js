var ProcessFinish = {

	"search" : function() {
		$("#table").datagrid("load", {
			processName : $("#processName").val(),
			taskName : $("#taskName").val()
		});
	},

	"toView" : function(id) {
		window.self.location.href = contextPath
				+ "/process/viewProcessForm.do?id=" + id + "&type=finish";
	},

	"end" : null
};