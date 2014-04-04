/**************************************监控组管理*******************************************/
//入参
function searchcontrolGroup(page){
	if(page==0){
		page=1;
	}
	var controlName=$("#controlName").val();
	if(veriftyChar(controlName)){
		selectcontrolGroup(controlName,page,10);
	}else{
		showAlertInfo("查询条件存在非法字符");
	}
}
//显示
function selectcontrolGroup(controlName,page,pageSize){
	var json={
			monitorName:controlName,
			page:page,
			pageSize:pageSize
	};
	PostJSONQuery("searchAllMonitorGroup.do",json,function(response){
		//showAlertInfo(JSON.stringify(response));
		var monitorGroup=response.monitorGroup;
		$('#totalPage')[0].innerHTML = 	response.pages;
		$("table tbody tr td").html("");
		monitorGroup.forEach(function(e,i){
        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(page)-1)*10+i+1);
        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.groupName);
        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.remark);
        				$("table tbody tr:eq("+i+") td:eq(3)").html("<a href='javascript:deleteGroup("+e.groupId+");'>删除</a> |<a href='modifyGroup.jsp?id="+e.groupId+"'>修改</a>"); 
        
        }); 
        if(monitorGroup.length==0){
	 					showAlertInfo("没有查找到符合条件的数据！"); 
		 }
	});
}
//通过id寻找监控组信息
function showOneControlGroup(id){
	//alert(id);
	var json={groupId:id};
	PostJSONQuery("searchMonitorGroupById.do",json,function(response){
		//alert(JSON.stringify(response));
			var info=response.findMonitorById;
			$("#groupId").val(info.groupId);
			$("#groupName").val(info.groupName);
			$("#remark").val(info.remark);
			//TODO 显示监控组树状结构
			zNodes=info.sectionCameraTrees; 
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
}
//删除监控组
function deleteGroup(id){
	$("#someinfo").html("是否真的删除这个监控组");
	$(".test").fbmodal({
			cancelbutton:true
		}
		,function(callback){
	     if(callback == 1){
	    	 	var json={groupId:id};
	    	 	PostJSONQuery("deleteMonitorGroupById.do",json,function(response){
	    			if(response.deleteMonitorById){
	    				setTimeout(function(){
	     					showAlertInfo("删除成功");
	      				},1000);
	    				var page=parseInt($("#current_page").html());
	    				searchcontrolGroup(page);
	    			}else{
	    				setTimeout(function(){
	     					showAlertInfo("删除失败,因为有操作人员绑定了这个监控组！"); 
	      				},1000);
	    			}
	    		});
	    }
		if(callback == 2){
		    
	    }
	});  
	
}


/***天加监控组获取树状结构***/
function getgroupTree(){
	PostJSONQuery("getInitSectionCameraTree.do",{},function(response){
		//alert(JSON.stringify(response));
		var v=[];
		v[0]=response;
			$.fn.zTree.init($("#treeDemo"), setting, v);
	});
	
}
//更新或者添加新的管理组
function saveControlGroup(){
	$("#someinfo").html("是否确定真的保存？");
	$(".test").fbmodal({
		cancelbutton: true
	}
		,function(callback){
		    if(callback == 1){
		    	tosaveControlGroup();
		    }
			if(callback == 2){
		          
		    }
	});  
	
	
	
	
	
}

function tosaveControlGroup(){
	
	
	var groupId=$("#groupId").val();
	var groupName=$("#groupName").val();
	var remark=$("#remark").val();
	

	if(groupName.length==0){
		showAlertInfo("请填写完整信息");
	}else{
		if(veriftyChar(groupName)&&veriftyChar(remark)){
			var group={
					"groupId":groupId,
					"groupName":groupName,
					"remark":remark,
					"ids":""
				};
			var allids=[];
			//获取被选中的摄像机的id
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			for(var m=0;m<nodes.length;m++){
				var id=nodes[m].value;
				if(id!=null){allids.push(id);}
					
			}
			group.ids=allids;
			//		alert(JSON.stringify(group));
			if(allids.length>0){
				if(group.groupId==null||group.groupId.length==0){//添加 
					//添加前先验证这个监控组名字是否已经存在
					var jsongroupName={
						groupName:group.groupName
					};
					//alert(JSON.stringify(jsongroupName));
					PostJSONQuery("validateMonitorName.do",jsongroupName,function(response){
						if(response){
							PostJSONQuerySendString("updateMonitorGroup.do",group,function(response){
								if(response.updateMonitorGroup){
									setTimeout(function(){
										showAlertInfo("保存成功");
					  				},600);
									setTimeout(function(){
										reback();
					  				},2000);
								}else{
									setTimeout(function(){
										showAlertInfo("保存失败");
					  				},1000);
								}
							});
						}else{
							setTimeout(function(){
								showAlertInfo("该监控组名字已经存在，请重命名！");
			  				},600);
						}
					});
					
				}else{//更新
					//更新前先验证这个监控组名字是否已经在其他的监控组存在
					var jsonother={
						groupName:group.groupName,
						groupId:group.groupId
						};
					PostJSONQuery("validateotherMonitorName.do",jsonother,function(response){
						if(response){
							PostJSONQuerySendString("updateMonitorGroup.do",group,function(response){
								if(response.updateMonitorGroup){
									setTimeout(function(){
										showAlertInfo("保存成功");
					  				},600);
									setTimeout(function(){
										reback();
					  				},2000);
								}else{
									setTimeout(function(){
										showAlertInfo("保存失败");
					  				},1000);
								}
							});
						}else{
							setTimeout(function(){
								showAlertInfo("该监控组名字已经在其他监控组使用，请重命名！");
			  				},600);
						}
					});
					
					
					
				}
				
			}else{
				setTimeout(function(){
					showAlertInfo("请至少选择为一个摄像机！");
  				},600);
			}
			
		}else{
			showAlertInfo("文本框存在非法字符");
		}
	}
	
	
}