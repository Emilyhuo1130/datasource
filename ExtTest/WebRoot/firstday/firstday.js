	/**
	Ext.onReady(function(){
		Ext.MessageBox.alert("hello word!","comming",callBack1);
		
	});
	*/
	
	//回调函数
	function  callBack1(id){
		
		if(id=="yes"){
			Ext.MessageBox.alert("修改成功","update secuess");
		}else{
			Ext.Msg.alert("other",id);
		}
		
		
	};
	
	
	
	function ExtMessageTest1(){
		Ext.Msg.show({
		title:'savechange',
		msg:'you are sholl save this change',
		buttons:Ext.Msg.YESNOCANCEL,
		animEl:'elid',
		icon:Ext.MessageBox.QUESTION,
		fn:callBack1
		});
		
	}
	

//
function confirmTest(){
	Ext.Msg.confirm("提示","是否确定修改？",next_Function);
	function next_Function(id){
		if(id=="yes"){
			Ext.Msg.alert("","去修改");
		}
	}
}

function waitTest () {
 
  
  Ext.Msg.wait("画面正在载入，请稍等","提示");
  
  var f=function(v){
  	return function(){
  		if(v==15){
  			Ext.Msg.hide();
  			Ext.Msg.alert("提示","加载成功");
  		}else{
  			var i=v / 14;
  			Ext.Msg.updateProgress(i,Math.round(100*i)+'%完成');
  		}
  	};
  };
	for(var i=0;i<=15;i++){
		  setTimeout(f(i),i*200);
		
	}  
  
}

function saveTest(name){
	Ext.Msg.alert("提示","saveTest"+name);
}

//Panel
Ext.onReady(function(){
  var newtoolbar=Ext.create('Ext.toolbar.Toolbar',{
          width:500,
          items:[
          {xtype:'button',text:'新建',handler:function(){Ext.Msg.alert("提示","新建");}},'-',
          {xtype:'button',text:'保存',handler:saveTest},'-',
          {xtype:'button',text:'撤销'},'->',
          new Ext.form.Field(),{
          	text:'搜索'
          }
          ]
    });
   
  var newBbar=Ext.create('Ext.toolbar.Toolbar',{
	  	width:500,
	  	items:[
	  		{xtype:'button',text:'上一页',icon:'../resources/themes/images/access/util/splitter/mini-left.gif'},
	  		//{xtype:tbseparator},
	  		{xtype:'button',text:'下一页',icon:'../resources/themes/images/default/layout/mini-right.gif'}
	  	]
  });
  var newPanel=Ext.create('Ext.panel.Panel',{
  	title:"my first panel",
  	collapsible:true,
  	width:500,
  	height:300,
  	x:30,
  	html:"我的第一个panel",
  	tbar:newtoolbar,
  	bbar:newBbar,
  	renderTo:document.body
  });
  
  
 	//禁用和启用按钮  导航栏的禁用和启用
 			Ext.get("btnDisable").on("click",function(){
                 newtoolbar.disable();
             });
            Ext.get("btnEnable").on("click", function(){
                 newtoolbar.enable();
            });
     
  
	
	
});

//表单
Ext.onReady(function(){
	 Ext.QuickTips.init();
	var myPanel=new Ext.form.FormPanel({
		title:'表单应用', 
		frame:true,
		width:300, 
		x:600, //距离左边框的距离
		y:200, //相当于top
		floating:true, 
		tools:[{id:'close'}], 
		frame:true, 
		bodyStyle:'padding:10px 0px 1px 1px', 
		labelSeparator:':',//label后面的分隔符 
		labelAlign:'right', 
		renderTo:Ext.getBody(),//为什么这里不可以用'id1' 
		items:[
		{
			xtype:'fieldset',
			title:'输入框',
			collapsible:true,
			autoHeight:true,
			autoWidth:true,
			defaults:{xtype:'textfield',width:250,allowBlank:false,msgTarget:'side'},//提取共同属性 
			items:[ 
				{ 
					fieldLabel:'用户名称', 
					name:'username', 
					id:'user', 
					emptyText:'请输入用户名', 
					blankText:'请输入用户名',//没有输入时候显示提示
					regex:/^[A-Za-z]+$/,//正则表达式验证
					regexText:"请输入字母"//正则的提示
				}, 
				{ 
					fieldLabel:'用户密码', 
					name:'userpassword', 
					id:'password', 
					inputType:'password',//它还包括 radiocheck text(默认) filepassword等等 
					blankText:'请输入密码' 
				} 
			]
			
			}
			
		],
		buttons:[
			{text:'submit',handler:login},
			{text:'cancel',handler:reset}
		],
		buttonAlign:'center'
	});
	function login(){
		myPanel.getForm().submit({
			url:'http://192.168.2.103:8081/ExtTest/testform.do',
			
			waitTitle:'wait....',
			waitMsg:'正在提交',
			success:function(form,action){
				alert(action.result);//的到返回的值。
			},
			failure:function(form,action){
				Ext.Msg.alert('提示','保存失败');
			}
		});
	}
	function reset(){
		myPanel.form.reset();
	}
});

















