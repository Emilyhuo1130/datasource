
/**
 * 文号责任者
 * 
 * @param {}
 *            config
 */
gdda.system.FileCodeAuthorForm = function(config) {
	var _this = this;
	this.dlg = null;
	this.store = null;
	this.buttonSave = new Ext.Button({
		text : '保存',
		handler : function() {
			var fileCode = _this.getForm().findField('bean.fileCode');
			Ext.Ajax.request({
				url : 'checkFileCode@FileCodeAuthorAction.action',
				params : {
					'map.fileCode' : fileCode.getValue(),
					'map.id' : _this.getForm().findField('bean.id').getValue(),
					'map.addFlag' : config.addFlag
				},
				waitMsg : '检测中...',
				success : function(response, option) {
					var ret = Ext.decode(response.responseText);
					if (ret > 0) {
						fileCode.markInvalid('文号已存在，请重新命名！');
						return;
					}
					if (_this.getForm().isValid()) {
						if (config.addFlag) {
							_this.getForm().submit({
								url : 'add@FileCodeAuthorAction.action',
								success : function(form, action) {
									gdda.Util.showInfo('新增', '新增成功！');
									_this.dlg.close();
								},
								failure : function(form, action) {
									gdda.Util.showError('失败', '新增失败！<br>' + action.result.json.message);
								},
								waitMsg : '保存中......'
							});
						} else {
							_this.getForm().submit({
								url : "modify@FileCodeAuthorAction.action",
								success : function(form, action) {
									gdda.Util.showInfo('修改', '修改成功！');
									_this.dlg.close();
								},
								failure : function(form, action) {
									gdda.Util.showError('修改', '修改失败！<br>' + action.result.json.message);
								},
								waitMsg : '保存中...'
							});
						}
					}
				},
				failure : function(response, option) {
					gdda.Util.showError('检测', '检测出错！');
				}
			})

		}
	});
	gdda.system.FileCodeAuthorForm.superclass.constructor.call(this, {
		labelAlign : "right",
		defaultType : "textfield",
		frame : true,
		items : [{
			name : 'bean.id',
			hideLabel : true,
			hidden : true
		}, {
			fieldLabel : "全宗名称",
			columns : "1",
			xtype : "combo",// 用于登记一个xtype
			name : "bean.fondsCode",
			hiddenName : 'bean.fondsCode',
			store : gdda.Gdda.Store.fondsCodeStore4User,
			mode : 'local',
			valueField : "id",
			displayField : "name",
			typeAhead : true,// 根据输入的值自动匹配下拉框中的值
			forceSelection : true,
			triggerAction : 'all',
			allowBlank : false,
			blankText : "全宗名称不能为空",
			editable : false,
			width : 210
		}, {
			fieldLabel : '文号',
			name : 'bean.fileCode',
			allowBlank : false,
			blankText : '文号不能为空',
			maxLength : 50,
			maxLengthText : '最大长度不能超过50',
			width : 210
		}, {
			fieldLabel : '责任者',
			name : 'bean.author',
			allowBlank : false,
			blankText : '责任者不能为空',
			maxLength : 50,
			maxLengthText : '最大长度不能超过50',
			width : 210
		}]
	});
};
Ext.extend(gdda.system.FileCodeAuthorForm, Ext.form.FormPanel, {});