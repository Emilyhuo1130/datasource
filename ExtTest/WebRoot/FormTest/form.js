Ext.onReady(function(){
	Ext.QuickTips.init();
	var testform=new Ext.form.FormPanel({
		frame:true,
		width:600,
		monitorValid:true,
		
		layout:"form",
		labelWidth:50,
		title:'添加个人信息',
		labelAlign:"right",
		buttonAlign:'center',
		renderTo:Ext.getBody(),
		defaults:{
			border:0
		},
		 items:[{
             xtype:"panel",
             layout:"column",
            // fieldLabel:"用户名",
             isFormField:true,
             defaults:{
            	 border:0
             },
             items:[{
                       columnWidth:.66,
                       fieldLabel:"用户名",
                       xtype:"textfield",
                       allowBlank:false,
                       blankText:"不能为空，请填写",
                       name:"UserName",
                       anchor:"90%"
             },{
            	 xtype:"radio",
                 fieldLabel:"性别",
                 boxLabel:"男",
                 name:"Sex",
                 checked:true,
                 inputValue:"man",//这里如果用value，值是on，所以用inputValue（出现这种情况的是radio,checkbox）
                 anchor:"90%"
             },{
            	 xtype:"radio",
                 boxLabel:"女",
                 labelSeparator:"",//去除分隔符“：”
                 name:"Sex",
                 inputValue:"woman",
                 anchor:"90%" 
             }]
		 },{
			 xtype:'panel',
			 layout:'column',
			 isFormField:true,
			 defaults:{
				 border:0
			 },
			 items:[{
				 fieldLabel:'出生日期',
				 columnWidth:.8,
				 xtype:'datefield',
				 name:'birthDate',
				 anchor:'90%'
				 
			 },{
				 xtype:'combo',
				 name:'degree',
				 fieldLabel:'学位',
				 store:['小学','本科','博士'],
				 emptyText:'请选择合适你的学位',
				 anchor:'90%'
			 }]
		 },{
			 xtype:'panel',
			 layout:'column',
			// isFormField:true,
			 fieldLabel:'使用框架',
			 defaults:{
				 border:0
			 },
			 items:[{
				 fieldLabel:'使用框架',
				 columnWidth:.4,
				 xtype:'checkbox',
				 boxLabel:'spring',
				 name:'spring',
				 inputValue:'spring'
			 },{
				 columnWidth:.2,
				 xtype:'checkbox',
				 boxLabel:'hibernate',
				 name:'hibernate',
				 inputValue:'hibernate',
				 anchor:'90%'
			 },{
				 xtype:'checkbox',
				 boxLabel:'struts',
				 name:'struts',
				 inputValue:'struts'
			 }]
		 },{
			
			 xtype:'textfield',
			 fieldLabel:'email',
			 name:'Email',
			 vtype:'email',
			 vtypeText:'email格式错误'
			 
		 },{
			 xtype:'textarea',
			 fieldLabel:'个性签名',
			 name:'OneWord',
			 height:50
			
			 
		 },{
			 xtype:'htmleditor',
			 fieldLabel:'想说的话',
			 name:'whattosay',
			 anebleAlignments:false,
			 enableLists:false
			 
		 }],
		 buttons:[{
			 text:'确定',handler:login,formBind:true
		 },{
			 text:'取消',handler:reset
		 }]
		
	});
	function login(){
		Ext.Msg.alert('提示','提交成功');
	}
	function reset(){
		testform.form.reset();
	}
	
});