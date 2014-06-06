Ext.onReady(function(){
	Ext.get('login').on('click',function(){
		location.href='login@UserController.do';
	});
	Ext.get('zhuce').on('click',function(){
		var form = new ec.js.pagejs.RegisterForm();
		var dlg = new ec.js.utils.Dialog('用户注册', 400, 300, form);
		form.dlg = dlg;
		dlg.addButton(form.buttonSave);
		dlg.addButton(dlg.buttonClose);
		dlg.show();
	});
	//ec.js.pagejs.CommitBug
	Ext.get('commitbug').on('click',function(){
		var win=new ec.js.pagejs.CommitBug();
		win.show(this);
	})
})




