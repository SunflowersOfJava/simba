var ProcessMonitor = {

	"search" : function() {
		$("#table").datagrid("load", {
			processName : $("#processName").val()
		});
	},

	"end" : null

};