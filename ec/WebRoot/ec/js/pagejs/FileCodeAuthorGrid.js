
/**
 * 文号责任者
 */
gdda.system.FileCodeAuthorGrid = function() {
	var _this = this;
	var sm = new Ext.grid.CheckboxSelectionModel();
	this.config = null;
	this.dataStore = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'list@FileCodeAuthorAction.action',
			method : 'post'
		}),
		reader : new Ext.data.JsonReader({
			id : 'id'
		}, [{
			name : 'id'
		}, {
			name : 'fileCode'
		}, {
			name : 'fondsCode'
		}, {
			name : 'author'
		}])
	});
	_this.dataStore.load();
	this.buttonAdd = new Ext.Button({
		text : '新增',
		handler : function() {
			_this.config = {
				addFlag : true
			}
			var form = new gdda.system.FileCodeAuthorForm(_this.config);
			var dlg = new gdda.ux.Dialog('新增文号责任者', 400, 300, form);
			form.dlg = dlg;
			dlg.addButton(form.buttonSave);
			dlg.addButton(dlg.buttonClose)
			dlg.on('close', function() {
				_this.dataStore.reload();
			});
			dlg.show();
		}
	});
	this.buttonUpdate = new Ext.Button({
		text : '修改',
		handler : function() {
			if (!_this.checkSelections())
				return;
			if (_this.getSelectedIds().split(',').length > 1) {
				gdda.Util.showInfo('提示', '只能选择一条记录！');
				return;
			}
			var r = '{';
			var v = _this.getSelectionModel().getSelections();
			var value = v[0].data;
			for (p in value) {
				if (typeof(value[p]) == 'string') {
					if (r != '{')
						r += ',';
					r += '"bean.' + p + '":"' + value[p].replace(/\"/g, '\\"') + '"';
				} else if (typeof(value[p]) == 'number') {
					if (r != '{')
						r += ',';
					r += '"bean.' + p + '":' + value[p]
				}
			}
			r += '}';
			_this.config = {
				addFlag : false
			};
			var form = new gdda.system.FileCodeAuthorForm(_this.config);
			var dlg = new gdda.ux.Dialog('修改文号责任者', 400, 300, form);
			dlg.on("show", function() {
				form.getForm().setValues(Ext.decode(r));
			});
			form.dlg = dlg;
			dlg.addButton(form.buttonSave);
			dlg.addButton(dlg.buttonClose);
			dlg.on('close', function() {
				_this.dataStore.reload();
			});
			dlg.show();
		}
	});
	this.buttonDelete = new Ext.Button({
		text : '删除',
		handler : function() {
			if (!_this.checkSelections())
				return;
			Ext.MessageBox.confirm('提示', '所选择的记录将被删除，确定吗？', function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : 'delete@FileCodeAuthorAction.action',
					params : {
						'ids' : _this.getSelectedIds()
					},
					success : function(response, option) {
						var result = Ext.util.JSON.decode(response.responseText);
						if (result.success == true) {
							gdda.Util.showInfo("删除", "删除成功！");
							_this.dataStore.reload();
						} else {
							gdda.Util.showError("删除", "删除失败！<br>" + result.json.message);
						}
					},
					failure : function(response, option) {
						gdda.Util.showError("删除", "删除失败！");
					},
					waitMsg : "保存中..."
				});
			});
		}
	});

	gdda.system.FileCodeAuthorGrid.superclass.constructor.call(this, {
		autoScroll : true,
		frame : false,
		store : _this.dataStore,
		sm : sm,
		columns : [new Ext.grid.RowNumberer(), sm, {
			header : '全宗名称',
			dataIndex : 'fondsCode',
			sortable : true,
			width : 120,
			renderer : gdda.Gdda.Renderer.fondsCode
		}, {
			header : '文号',
			dataIndex : 'fileCode',
			sortable : true,
			width : 300
		}, {
			header : '责任者',
			dataIndex : 'author',
			sortable : true,
			width : 100
		}],
		loadMask : {// True表示为当grid加载过程中，会有一个{@link Ext.LoadMask}的遮罩效果。默认为false。
			msg : "数据加载中"
		},
		tbar : [_this.buttonAdd, new Ext.Toolbar.Separator(), _this.buttonUpdate, new Ext.Toolbar.Separator(), _this.buttonDelete]
	});
};
Ext.extend(gdda.system.FileCodeAuthorGrid, Ext.grid.GridPanel, {

	/**
	 * 取得所选择节点的ID
	 * @return ids
	 */
	getSelectedIds : function() {
		var ids = '';
		var v = this.getSelectionModel().getSelections();
		if (v.length > 0) {
			for (var i = 0; i < v.length; i++) {
				ids += ',' + v[i].id;
			}
			ids = ids.substring(1);
		} else {
			ids = '';
		}
		return ids;
	},
	checkSelections : function() {
		if (this.dataStore.getCount() == 0) {
			gdda.Util.showInfo('无记录，不能进行该项操作！');
			return false;
		}
		if (this.getSelectedIds() == '') {
			gdda.Util.showInfo('请选择记录！');
			return false;
		}
		return true;
	}
});