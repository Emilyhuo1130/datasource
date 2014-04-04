/**********录像机管理*********************/
//查询按钮找到入参
function searchVCRManagee(page){
	var VCRName=$("#VCRName").val();
	var VCRModel=$("#VCRModel").val();
	var zone=$("#zone").val();
	if(zone=="--请选择--"){
		zone='';
	}
	if(page==0){
		page=1;
	}
	if((veriftyChar(VCRName))&&(veriftyChar(VCRModel))){//验证通过 可以查询
		selectAllVCRS(VCRName,VCRModel,zone,page,10);
	}else{
		showAlertInfo("查询条件存在非法字符");
	}
	
}
//显示所有录像机
function selectAllVCRS(VCRName,VCRModel,zone,page,pageSize){
	 var json = {
			 recorderName:VCRName,
			 recorderModel:VCRModel,
			 page:page,
			 pageSize:pageSize
	 };
	PostJSONQuery("searchRecorders.do",json,function(response){
		//showAlertInfo(JSON.stringify(response));
		$('#totalPage')[0].innerHTML = 	response.pages;
		var recorderInfo=response.recorderInfo;
		$("table tbody tr td").html("");
		recorderInfo.forEach(function(e,i){
        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(page)-1)*10+i+1);
        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.recorderName);
        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.recorderIp);
        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.recorderModel);
        				$("table tbody tr:eq("+i+") td:eq(4)").html("<a href='javascript:deleteVCR("+e.recorderId+")'>删除</a> | <a href='modifyoneVCR.jsp?id="+e.recorderId+"'>修改</a>"); 
        }); 
        
        if(recorderInfo.length==0){
	 		showAlertInfo("没有查找到符合条件的数据！"); 
		}
        	         
	      
	});
}
//通过id显示录像机
function showOneVCR(id){
	//alert(id);
	var json={recorderId:id};
	PostJSONQuery("searchRecorderById.do",json,function(response){
		//alert(JSON.stringify(response));
		var info=response.recorderInfo;
		$("#VCRID").val(info.recorderId);
		$("#u3").val(info.recorderName);//名称
		$("#u8").val(info.recorderModel);//型号
		$("#u9").val(info.recorderIp);//	ip
		$("#u14").val(info.recorderMac);//mac
		$("#u12").val(info.capacity);//容量
		//$("#u41").val(info.recorderType);//类型
		
	      
	});
}
//s删除VCR
function deleteVCR(id){
	$("#someinfo").html("是否真的删除这个录像机");
	$(".test").fbmodal({
		cancelbutton: true
	}
	,function(callback){
     if(callback == 1){
    	 var json={recorderId:id};
 		PostJSONQuery("deleteRecorder.do",json,function(response){
 			if(response.deleteRecorderInfo){
 				setTimeout(function(){
 					showAlertInfo("删除成功");
  				},1000);
 				var page=parseInt($("#current_page").html());
 				searchVCRManagee(page);
 			}else{
 				setTimeout(function(){
 					showAlertInfo("删除失败"); 
  				},1000);
 			}
 		});
    }
	if(callback == 2){
       
	    
    }
	});  
	
}
//更新录像机信息
function saveVCRinfo(){
	$("#someinfo").html("是否确定真的保存？");
	$(".test").fbmodal({
		cancelbutton: true
	}
		,function(callback){
		    if(callback == 1){
		    	tosaveVCRinfo();
		    }
			if(callback == 2){
		          
		    }
	});  
	
	
	
	
}

function tosaveVCRinfo(){
	
	
	var VCRID=$("#VCRID").val();//录像机名称
	var VCRName=$("#u3").val();//录像机名称
	var VCRModel=$("#u8").val();//录像机型号
	var VCRIP=$("#u9").val();//录像机IP
	var VCRMAC=$("#u14").val();//录像机MAC
	var VCRVolume=$("#u12").val();//录像机容量
	var VCRType="全球";//录像机类型
	
	var json2={
			recorderId:VCRID,
			recorderName:VCRName,
			recorderModel:VCRModel,
			recorderIp:VCRIP,
			recorderMac:VCRMAC,
			capacity:VCRVolume,
			recorderType:VCRType
		};
	
	
		if(VCRName.length==0||VCRIP.length==0){
			showAlertInfo("带*为必填项");
		}else{
			//alert(JSON.stringify(json2));
			if(VCRID.length==0){
				var json={recorderIp:VCRIP};
				PostJSONQuery("validateRecorderIp.do",json,function(response){
					//alert(JSON.stringify(response));
					if(response){
						//此IP已经配置过
						showAlertInfo("此IP已经在其他录像机配置！");
					}else{//添加陆续相机
						if(veriftyChar(VCRName)&&
								veriftyChar(VCRModel)&&
								veriftyIPreturn(VCRIP)&&
								veriftyMACreturn(VCRMAC)&&veriftyAllNumberreturn(VCRVolume)){
							PostJSONQuery("updateRecorderInfo.do",json2,function(response){
								//alert(JSON.stringify(response));
								if(response){
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
							showAlertInfo("文本框存在不符合规范的字符串");
						}
					}
				});
			}else{//更新录像机
				if(veriftyChar(VCRName)&&
						veriftyChar(VCRModel)&&
						veriftyIPreturn(VCRIP)&&
						veriftyMACreturn(VCRMAC)&&veriftyAllNumberreturn(VCRVolume)){
							//验证这个IP是否在出本机 以外在其他的录像机上配置过
							var jsonother={
								recorderId:json2.recorderId,
								recorderIp:json2.recorderIp
								};
							PostJSONQuery("validateotherRecorderIp.do",jsonother,function(response){
								//alert(JSON.stringify(response));
								if(response){
									PostJSONQuery("updateRecorderInfo.do",json2,function(response){
										//alert(JSON.stringify(response));
										if(response){
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
										showAlertInfo(json2.recorderIp+"这个IP已经在其他摄像机中配置，请换一个IP");
				  					},600);
								}
								
							});
						
				}else{
					showAlertInfo("文本框存在不符合规范的字符串");
				}
			}
			
		}
	
		
	
	
	
}