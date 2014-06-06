//注册窗
 ec.js.pagejs.RegisterForm=function(){
	var _this=this;
	_this.flg=null;
	this.store = null;
	
	var citys=[
		['bj','xxxx']
	];
	var citysStore=new Ext.data.Store({
		proxy:new Ext.data.MemoryProxy(citys),
		reader:new Ext.data.ArrayReader({},[
	 	{name:'cityValue'},
	 	{name:'cityName'}
 	])
	});
	citysStore.load();
	
	
	_this.buttonSave=new Ext.Button({
		text:'保存',
		handler:function(){
			if(_this.getForm().findField('userPassWordAgain').getValue()
					!=_this.getForm().findField('userPassWord').getValue()){
				ec.PublicUtils.showInfo('两次密码输入不一致');
				return;
			};
			var userNameField=_this.getForm().findField('userName');
			Ext.Ajax.request({
				url:'public_verifyUserName@UserController.do',
				
				params:{
					'userName':userNameField.getValue()
				},
				waitMsg:'用户名验证中...',
				success:function(response,option){
					var ret = Ext.decode(response.responseText);
					if(ret){//返回true 存在
						userNameField.markInvalid('用户名已经存在');
						ec.PublicUtils.showInfo('用户名已经存在');
						return;
					}
					if(_this.getForm().isValid()){
						_this.getForm().submit({
							url:'public_addUserInfo@UserController.do',
							success : function(form, action) {
								ec.PublicUtils.showSuccess('注册成功,为您跳转到登陆页面');
								_this.dlg.close();
								(function(){
									ec.js.utils.gotoLogin();
								}).defer(2000)
							},
							failure : function(form, action) {
								ec.PublicUtils.showError('异常','系统出错');
							},
							waitMsg : '保存中......'
						});
					};
				},
				failure:function(response,option){
					ec.PublicUtils.showError('异常','系统出错');
				}
			});
			
		}
	});
	
	ec.js.pagejs.RegisterForm.superclass.constructor.call(this, {
		labelAlign : "right",
		defaultType : "textfield",
		frame : true,
		items : [{
			fieldLabel:'登陆账户',
			name : 'userName',
			hiddenName : 'userName',
			allowBlank:false,
			blankText:'账号不能为空',
			minLength:6,
			minLengthText:'最小长度不低于6',
			maxLength:30,
			maxLengthText:'最大长度不超过30',
			width:210
		},{
			fieldLabel : '年龄',
			name : 'userAge',
			hiddenName : 'userAge',
			allowBlank : false,
			blankText:'年龄不能为空',
			blankText : '年龄不能为空',
			xtype:'numberfield',
			minValue:1,
			maxValue:99,
			minText:'年龄不得小于1',
			maxText:'年龄不得大于99',
			width : 210
		},{
			fieldLabel : '目前工作状态',
			name : 'nowJob',
			hiddenName : 'nowJob',
			allowBlank : false,
			xtype:'combo',
			store :new Ext.data.SimpleStore({
				fields:['value','text'],
				data:[
					['0','上班族'],
					['1','学生党'],
					['2','自由职业者'],
					['3','不上班']
				]
			}),
			mode : 'local',
			emptyText:'请选择',
			valueField : "value",
			displayField : "text",
			typeAhead : true,// 根据输入的值自动匹配下拉框中的值
			forceSelection : true,
			triggerAction : 'all',
			allowBlank : false,
			blankText : "工作状态不能为空",
			editable : false,
			width : 210
		},
		{
			fieldLabel : '所在省',
			name : 'inProvince',
			hiddenName : 'inProvince',
			allowBlank : false,
			xtype:'combo',
			store :new Ext.data.SimpleStore({
				fields:['value','text'],
				data:allProvince
			}),
			mode : 'local',
			emptyText:'请选择',
			valueField : "value",
			displayField : "text",
			typeAhead : true,// 根据输入的值自动匹配下拉框中的值
			forceSelection : true,
			triggerAction : 'all',
			allowBlank : false,
			blankText : "所在省份不能为空",
			editable : false,
			width : 210,
			listeners:{  
			    select : function(combo, record,index){
			     citysStore.proxy=new Ext.data.MemoryProxy(province_citys[combo.value]);
			     citysStore.load();
			     _this.getForm().findField('inCity').setValue('0');
			    }
			}
		},{
			fieldLabel : '所在城市',
			name : 'inCity',
			hiddenName : 'inCity',
			allowBlank : false,
			xtype:'combo',
			store :citysStore,
			mode : 'local',
			emptyText:'请选择',
			valueField : "cityValue",
			displayField : "cityName",
			typeAhead : true,// 根据输入的值自动匹配下拉框中的值
			forceSelection : true,
			triggerAction : 'all',
			allowBlank : false,
			blankText : "所在城市不能为空",
			editable : false,
			width : 210
		},{
			fieldLabel : '密码',
			name : 'userPassWord',
			hiddenName : 'userPassWord',
			inputType:'password' ,
			allowBlank : false,
			blankText:'密码不能为空',
			minLength:6,
			minLengthText:'最小长度不低于8',
			maxLength:30,
			maxLengthText:'最大长度不超过30',
			width : 210
		},{
			fieldLabel : '确认密码',
			name : 'userPassWordAgain',
			hiddenName : 'userPassWordAgain',
			inputType:'password' ,
			allowBlank : false,
			blankText:'密码不能为空',
			minLength:6,
			minLengthText:'最小长度不低于8',
			maxLength:30,
			maxLengthText:'最大长度不超过30',
			width : 210
		}]
	});
}



Ext.extend(ec.js.pagejs.RegisterForm, Ext.form.FormPanel, {});