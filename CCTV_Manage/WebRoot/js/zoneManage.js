/***区位查询入参**/
function searchzoneManage(page){
	var zoneName=$("#zoneName").val();
	if(page==0){
		page=1;
	}
	if((veriftyChar(zoneName))){//验证通过 可以查询
		selectallzones(zoneName,page,10);
	}else{
		showAlertInfo("查询条件存在非法字符");
	}
}
/***显示所有区位***/
function selectallzones(zoneName,page,pageSize){
	var json = {
			sectionName:zoneName,
			 page:page,
			 pageSize:pageSize
	 };
	PostJSONQuery("searchSectionInfo.do",json,function(response){
		//showAlertInfo(JSON.stringify(response));
		$('#totalPage')[0].innerHTML = 	response.pages;;
		$("table tbody tr td").html("");
		response.sectionInfo.forEach(function(e,i){
       				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(page)-1)*10+i+1);
       				$("table tbody tr:eq("+i+") td:eq(1)").html(e.sectionName);
       				$("table tbody tr:eq("+i+") td:eq(2)").html(e.remark);
       				$("table tbody tr:eq("+i+") td:eq(3)").html("<a href='javascript:deletezoneById("+e.sectionId+");'>删除</a> | <a href='modifyZone.jsp?id="+e.sectionId+"'>修改</a>"); 
       	 }); 
       	 if(response.sectionInfo.length==0){
	 					showAlertInfo("没有查找到符合条件的数据！"); 
		 }
		});
}

/***更具id获取区位信息***/
function showzoneByID(id){
	var json={sectionId:id};
	//alert(JSON.stringify(json));
	PostJSONQuery("searchSectionInfoById.do",json,function(response){
		response=response.sectionInfo;
		$("#zoneId").val(response.sectionId);
		$("#zoneName").val(response.sectionName);
		$("#remark").val(response.remark);
	});
	
}

/***更具id删除区位**/
function deletezoneById(id){
	$("#someinfo").html("是否真的删除这个区位");
	$(".test").fbmodal({
			cancelbutton:true
		}
		,function(callback){
	     if(callback == 1){
	    	 	var json={sectionId:id};
	    	 	PostJSONQuery("deleteSectionInfoById.do",json,function(response){
	    			if(response.deleteInfo){
	    				setTimeout(function(){
	     					showAlertInfo("删除成功");
	      				},1000);
	    				var page=parseInt($("#current_page").html());
	    				searchzoneManage(page);
	    				
	    			}else{
	    				setTimeout(function(){
	     					showAlertInfo("删除失败!因为此区位下绑有摄像机!"); 
	      				},1000);
	    			}
	    		});
	    }
		if(callback == 2){
		    
	    }
	});  
	
}

/**更新或者添加区位***/
function savezoneinfo(){
	var id=$("#zoneId").val();
	var name=$("#zoneName").val();
	var remark=$("#remark").val();
	if(name.length==0){
		showAlertInfo("请填写完整信息");
	}else{
		if(veriftyChar(name)&&veriftyChar(remark)){
			var json={
					sectionId:id,
					sectionName:name,
					remark:remark
					};
					
			if(json.sectionId==null||json.sectionId.length==0){//添加区位
				//添加区位前先验证区位名字是否已经存在
				var jsonname={sectionName:json.sectionName};
				PostJSONQuery("validateSectionName.do",jsonname,function(response){
					if(response){
						PostJSONQuery("updateSectionInfo.do",json,function(response){
							if(response.updateSectionInfo){
								setTimeout(function(){
									showAlertInfo("保存成功");
				  				},600);
								setTimeout(function(){
									reback();
				  				},2000);
							}else{
								setTimeout(function(){
									showAlertInfo("保存失败!");
				  				},1000);
							}
						});
					}else{
						setTimeout(function(){
 								showAlertInfo("保存失败!此区位已经存在!");
		  				},600);
					}
				});
				
				
			}else{//更新
				
				var jsonother={
					sectionId:json.sectionId,
					sectionName:json.sectionName
					};
				PostJSONQuery("validateotherSectionName.do",jsonother,function(response){
					if(response){
						PostJSONQuery("updateSectionInfo.do",json,function(response){
							if(response.updateSectionInfo){
								setTimeout(function(){
									showAlertInfo("保存成功");
				  				},600);
								setTimeout(function(){
									reback();
				  				},2000);
							}else{
								setTimeout(function(){
									showAlertInfo("保存失败!");
				  				},1000);
							}
						});
					}else{
						setTimeout(function(){
							showAlertInfo("保存失败!此区位已经存在!");
		  				},600);
					}
				});
				
				
				
			}
			
		}else{
			showAlertInfo("文本框存在不符合规范的字符串");
		}
	}
	
	
}