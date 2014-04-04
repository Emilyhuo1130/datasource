var setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick,
				onCheck: onCheck
			}
		};

		var zNodes =[
			
			
			{id:41, pId:4, name:"系统管理"},
			{id:42, pId:4, name:"系统备份任"},
			{id:43, pId:4, name:"权限分配"},
			{id:44, pId:4, name:"网络管理"},
			{id:45, pId:4, name:"全网告警分配"},
			{id:46, pId:4, name:"报表统计查看"},
			{id:47, pId:4, name:"告警确认"},
			{id:48, pId:4, name:"告警取消"},
			{id:49, pId:4, name:"任务分配"},
			{id:50, pId:4, name:"告警核实"},
			{id:53, pId:4, name:"审定"},
			{id:54, pId:4, name:"专家知识库修改"},
		
			
			
			
		 ];

		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}

		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#citySel");
			cityObj.attr("value", v);
		}

		function showMenu() {
			var cityObj = $("#citySel");
			var cityOffset = $("#citySel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});