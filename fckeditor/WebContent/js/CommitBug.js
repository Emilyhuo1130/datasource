ec.js.pagejs.CommitBug=function(){
	var _this =this;
	var form = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 55,
       // url: 'save-form.php',
        layout: {
            type: 'vbox',
            align: 'stretch'  // Child items are stretched to full width
        },
        defaults: {
            xtype: 'textfield'
        },

        items: [/*{
            xtype: 'combo',
            store: ['test@example.com', 'someone-else@example.com' ],
            plugins: [ Ext.ux.FieldReplicator, Ext.ux.FieldLabeler ],
            fieldLabel: 'Send To',
            name: 'to'
        },{
            plugins: [ Ext.ux.FieldLabeler ],
            fieldLabel: 'Subject',
            name: 'subject'
        },*/ {
            xtype: 'textarea',
            fieldLabel: 'Message text',
            name:'opinion',
            id:'opinion',
            hiddenName:'opinion',
            hideLabel: true,
            flex: 1  // Take up all *remaining* vertical space
        }]
    });
	
    _this.commit=new Ext.Button({
    		text:'提交建议和Bug',
    		handler:function(){
    			Ext.Ajax.request({
    				url:'public_CommitBug@PublicMethod.do',
    				
    				params:{
    					'opinion':Ext.getDom('opinion').value
    				},
    				waitMsg:'意见正在提交.......',
    				success:function(response,option){
    					if(Ext.decode(response.responseText)){
    						ec.PublicUtils.showSuccess('意见反馈成功');
    						_this.close();
    					}
    				},
    				failure:function(response,option){
    					ec.PublicUtils.showError('异常','系统出错');
    				}
    			});
    		}
    });
    _this.cancel=new Ext.Button({
    	text:'取消提交',
    	handler:function(){
    		_this.close();
    	}
    });
    
    
	ec.js.pagejs.CommitBug.superclass.constructor.call(this, {
		title: '提交意见/建议和Bug',
        collapsible: true,
        maximizable: true,
        width: 750,
        height: 500,
        minWidth: 300,
        minHeight: 200,
        layout: 'fit',
        plain: true,
        bodyStyle: 'padding:5px;',
        buttonAlign: 'center',
        html : '<iframe src="demo.jsp" frameborder="0" scrolling="yes" id="setframe" name="setframe" width="100%" height="100%"></iframe>', 
        
       
	});
};
Ext.extend(ec.js.pagejs.CommitBug, Ext.Window, {});