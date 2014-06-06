ec.PublicUtils={
	showSuccess:function(title,msg){
		if (!msg) {
			msg = title;
			title = '成功信息';
		}
		new Ext.ux.Notification({
			autoHide:   true,
			hideDelay:  3000
		}).showSuccess(title,'<h1>操作成功</h1>'+msg+'</br>'); 
	},
	showError:function(title,msg){
		if (!msg) {
			msg = title;
			title = '失败信息';
		}
		new Ext.ux.Notification({
			autoHide:   true,
			hideDelay:  3000
		}).showFailure(title,'<h1>操作失败</h1>+'+msg); 
	},
	showInfo:function(title,msg){
		if (!msg) {
			msg = title;
			title = '提示信息';
		}
		new Ext.ux.Notification({
			autoHide:   true,
			hideDelay:  3000
		}).showMessage(title,'<h1>操作提示</h1>'+msg+'</br>',true); 
	}
}