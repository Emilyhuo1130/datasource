/*******
 * 
 * 
 *布局
 * 
 * **/
Ext.onReady(function(){
    
    var data=[
        [1,'张三',23],
        [2,'李四',33],
        [3,'王五',22]
    ];
    var store=new Ext.data.Store({
        proxy:new Ext.data.MemoryProxy(data),
        reader:new Ext.data.ArrayReader({},[
	        {name:'id'},
	        {name:'name'},
	        {name:'age'}
        ])
    });
    store.load();
    
   
      var viewport=new Ext.Viewport({
        layout:'border',//选择borderlayout的布局方式
        items:[
            {
                region:'north',//上方
                height:200,
                html:'<h3> layout 布局测试 上方</h3>',
                layout:'anchor',
                items:[
                    {
                        title:'panel',
                        html:'panel-1',
                        anchor:'30% 30%'
                        //anchor:'-300 -150'//右边距，下边距
                    },{
                        title:'panel',
                        html:'panel-2',
                       // anchor:'30%'
                        anchor:'-500'
                    }
                ]
            },{
                region:'south',//下方
                height:300,
                html:'<h3> layout 布局测试 下方</h3>',
                layout:'border',
                items:[
                    {
                        region:'west',
                        width:'300',
                        title:'分列式布局,columnLayout',
                        collapsible:true,
                        split:true,
                        layout:'column',
                        items:[
                            {
                                title:'column-1',
                                columnWidth:.25
                                //width:200   固定大小
                            },{
                                title:'column-2',
                                columnWidth:.4
                            },{
                                title:'column-3',
                                columnWidth:.35
                            }
                        ]
                    },{
                        region:'center',
                        title:'center'
                    },{
                        region:'east',
                        width:'300',
                        collapsible:true,
                        split:true,
                        title:'tableLaylout',
                        layout:'fit',
                        items:[
                            {
                                title:'table;Layout',
                                layout:'table',
                                defaults:{
                                    bodyStyle:'padding:20px'
                                },
                                layoutConfig:{
                                    columns:3//分三列
                                },
                                items:[
                                    {
                                        rowspan:2,
                                        html:'<p>cell 1</p>'
                                    },{
                                        rowspan:2,//占；两行
                                        html:'<p>cell 2</p>'
                                    },{
                                        html:'cell 3'
                                    },{
                                        html:'cell 4'
                                    }
                                ]
                            }
                            
                        ]
                    }
                ]
            },{
                region:'west',//左边
                width:'200',
                split:true,//边框可变大小
                minSize:100,//设置最小尺寸
                maxSize:600,//设置最大尺寸
                title:'west',//设置折叠按钮的标题
                collapsible:true,//模块折叠按钮
                layout:'accordion',
                lauoutConfig:{
                    titleCollapse:true,
                    animats:true,//展开折叠是否启用动画效果
                    activeOnTop:false//设置为true时，展开的始终在最上面
                },
                items:[
                    {
                        title:'第一栏',
                        html:'第一栏'
                    },{
                        title:'第二栏',
                        html:'第二栏'
                    },{
                        title:'第三栏',
                        html:'第三栏'
                    }
                ]
                
            },{
                region:'east',//右边
                width:500,
                html:'<h3> layout 布局测试右边</h3>',
                title:'east',
                split:true,
                collapsible:true,
                layout:'fit',
                items:[
                    {
                        xtype:'form',//等价与new Ext.form.FormPanel
                        title:'formlayout测试',
                        labelAlign:'right',
                        labelWidth:50,
                        frame:true,
                        defaultType:'textfield',
                        items:[
                            {
                                fieldLabel:'名称',
                                name:'mame',
                                anchor:'90%'
                            },{
                                fieldLabel:'日期',
                                name:'date',
                                xtype:'datefield',
                                anchor:'90%'
                            },{
                                fieldLabel:'备注',
                                name:'remark',
                                xtype:'textarea',
                                anchor:'90% -100'
                            }
                        ]
                    },{
                    
                    },{
                    }
                ]
            },{
                region:'center',//中间
                html:'主要内容',
                items:[new Ext.grid.GridPanel({
                    columns:[
                        {header:'编号',dataIndex:'id'},
                        {header:'姓名',dataIndex:'name'},
                        {header:'年龄',dataIndex:'age'}
                    ],
                    store:store,
                    loadMask:true,
                    height:200,
                    viewConfig:{
                        forceFit:true
                    }
                }
                )]
            }
        ]
        
      });  

});
