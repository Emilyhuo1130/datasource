//监听浏览器窗口大小变化
var window_height;
var window_width;
Ext.EventManager.onWindowResize(function(width,height){
    window_height=height;
    window_width=width;
});
Ext.onReady(function(){
	
	//
	var column=new Ext.grid.ColumnModel()
	
	var grid=new Ext.grid.GridPanel({
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	var viewport=new Ext.Viewport({
		layout:'border',
		items:[
			{
				region:'south',
				height:300,
				title:'消费曲线图',//设置折叠按钮的标题
		        collapsible:true,//模块折叠按钮
		        lauoutConfig:{
		        titleCollapse:true,
		              animats:true,//展开折叠是否启用动画效果
		              activeOnTop:false//设置为true时，展开的始终在最上面
		        },
				layout:'border',
				items:[
					{
						region:'west',
						width:document.body.clientWidth/2
						
					},{
						region:'center'
					}
				]
			},{
				region:'west',
				width:250,
				title:'消费日期树',
				collapsible:true//模块折叠按钮
				
			},{
				region:'center',
				title:'月消费列表',
				layout:'border',
				items:[
					{
						region:'north',
						height:100,
						title:'月固定收入/支出',
						collapsible:true
					},{
						region:'center',
						title:'每日消费记录'
					}
				]
				
			}
		]
	});
})



