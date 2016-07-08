var ProcessStart = {

	"search" : function() {
		$("#table").datagrid("load", {
			processName : $("#processName").val()
		});
	},

	"start" : function(id) {
		
	},

	"end" : null

};