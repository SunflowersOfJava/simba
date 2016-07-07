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

	"showXml": function(id) {

	},

	"deleteProcess" : function(id) {

	},

	"stop" : function(id) {

	},

	"start" : function(id) {

	},

		"uploadProcess" :function(){
		
		},

					"cancelUploadProcess" :function(){
					$("#processWindow").window('close'); 
					},

	"end" : null
};