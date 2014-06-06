/**
 * 对话框
 * 
 * @param {String}
 *            title
 * @param {int}
 *            width
 * @param {int}
 *            height
 * @param {Ext.Panel}
 *            widget
 */
ec.js.utils.Dialog = function(title, width, height, widget, config) {
	var _this = this;
	/**
	 * 关闭按钮
	 */
	this.buttonClose = new Ext.Button({
		text : '关闭',
		handler : function() {
			_this.close();
		}
	});
	this.buttonCancel = new Ext.Button({
		text : '取消',
		handler : function() {
			_this.close();
		}
	});
	config = config || {};
	Ext.applyIf(config, {
		defaults : {
			border : false
		},
		layout : 'fit',
		title : title,
		width : width,
		height : height,
		// resizable : config?config.resizable :true,
		// draggable : config?config.draggable :true,
		modal : true,
		autoScroll : true,
		items : widget
	});
	ec.js.utils.Dialog.superclass.constructor.call(this, config);
};
Ext.extend(ec.js.utils.Dialog, Ext.Window, {});
