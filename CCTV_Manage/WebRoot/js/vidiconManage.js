							/**********摄像机管理*********************/

//查询按钮找到入参
function searchvidiconManage(page){
	var vidiconName=$("#vidiconName").val();
	var vidiconModel=$("#vidiconModel").val();
	var zone=$("#u43").val();
	if(zone=="--请选择--"){
		zone='';
	}
	if(page==0){
		page=1;
	}
	if((veriftyChar(vidiconName))&&(veriftyChar(vidiconModel))){//验证通过 可以查询
		selectAllvidicons(vidiconName,vidiconModel,zone,page,10);
	}else{
		showAlertInfo("查询条件存在非法字符");
	}
	
}
//显示所有摄像机
function selectAllvidicons(vidiconName,vidiconModel,zone,page,pageSize){
	 var json = {
			 cameraName:vidiconName,
			 cameraModel:vidiconModel,
			 section:zone,
			 page:page,
			 pageSize:pageSize
	 };
	PostJSONQuery("searchCameras.do",json,function(response){
		//showAlertInfo(JSON.stringify(response));
		var cameraInfo=response.cameraInfo;
		$('#totalPage')[0].innerHTML = 	response.pages;
		$("table tbody tr td").html("");
		cameraInfo.forEach(function(e,i){
        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(page)-1)*10+i+1);
        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.cameraName);
        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.cameraIp);
        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.cameraModel);
        				$("table tbody tr:eq("+i+") td:eq(4)").html(e.section);
        				$("table tbody tr:eq("+i+") td:eq(5)").html("<a href='javascript:deletecamera("+e.cameraId+")'>删除</a> | <a href='modifyoneVidicon.jsp?id="+e.cameraId+"'>修改</a>"); 
        
        	
        }); 
        if(cameraInfo.length==0){
	 			showAlertInfo("没有查找到符合条件的数据！"); 
		}	        
	      
	});
}
//删除摄像机
function deletecamera(id){
	$("#someinfo").html("是否真的删除这个摄像机");
	$(".test").fbmodal({
		cancelbutton: true
	}
	,function(callback){
     if(callback == 1){
    	 var json={cameraId:id};
 		PostJSONQuery("deleteCamera.do",json,function(response){
 			if(response.deleteCameraInfo){
 				setTimeout(function(){
 					showAlertInfo("删除成功");
  				},1000);
 				var page=parseInt($("#current_page").html());
 				searchvidiconManage(page);
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
//通过id显示摄像机
function showOneVidicon(id){
	//alert(id);
	selectsections();//显示所有区位
	var json={cameraId:id};
	PostJSONQuery("searchCameraById.do",json,function(response){
		//alert(JSON.stringify(response));
		var info=response.CameraInfo;
		$("#VidiconId").val(info.cameraId);//id
		$("#u3").val(info.cameraName);//摄像机名称
		$("#u8").val(info.cameraModel);//摄像机型号
		$("#u9").val(info.cameraIp);//摄像机IP
		$("#u14").val(info.cameraMac);//摄像机MAC
		$("#u12").val(info.capacity);//摄像机容量
		
		$("#u41").val(info.cameraType);//摄像机类型
		$("#u43").val(info.section);//摄像机区位
	      
	});
}
//更新摄像机信息
function saveVidiconinfo(){
	$("#someinfo").html("是否确定真的保存？");
	$(".test").fbmodal({
		cancelbutton: true
	}
		,function(callback){
		    if(callback == 1){
		    	tosaveVidiconinfo();
		    }
			if(callback == 2){
		          
		    }
	});  
	
}

function tosaveVidiconinfo(){
	var VidiconId=$("#VidiconId").val();//id
	var vidiconName=$("#u3").val();//摄像机名称 *
	var vidiconModel=$("#u8").val();//摄像机型号
	var vidiconIP=$("#u9").val();//摄像机IP *
	var vidiconMAC=$("#u14").val();//摄像机MAC
	var vidiconVolume=$("#u12").val();//摄像机容量
	var vidiconType=$("#u41").val();//摄像机类型
	var section=$("#u43").val();//摄像机区位
	var json2={
			cameraId:VidiconId,
			cameraName:vidiconName,
			cameraModel:vidiconModel,
			cameraIP:vidiconIP,
			cameraMac:vidiconMAC,
			capacity:vidiconVolume,
			section:section,//区位
			cameraType:vidiconType
	};
		if(vidiconName.length==0||vidiconIP.length==0){
			showAlertInfo("带*项为必填项！");
		}else{
			//alert(JSON.stringify(json2));
			
			//添加摄像机前验证IP时否存在
			if(VidiconId.length==0){//添加
				var json={cameraIp:vidiconIP};
				PostJSONQuery("validateCameraIp.do",json,function(response){
					if(response){
						//此IP已经配置过
						showAlertInfo(vidiconIP+"这个IP已经在其他摄像机配置过");
					}else{
						if(veriftyChar(vidiconName)&&
								veriftyChar(vidiconModel)&&
								veriftyIPreturn(vidiconIP)&&
								veriftyMACreturn(vidiconMAC)&&veriftyAllNumberreturn(vidiconVolume)){
							PostJSONQuery("updateCameraInfo.do",json2,function(response){
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
              }else{//更新
				if(veriftyChar(vidiconName)&&
						veriftyChar(vidiconModel)&&
						veriftyIPreturn(vidiconIP)&&
						veriftyMACreturn(vidiconMAC)&&veriftyAllNumberreturn(vidiconVolume)){
					//先验证这个IP是否在除这个摄像机以外的摄像机中使用
					var jsonotherIP={cameraId:json2.cameraId,cameraIp:json2.cameraIP};
					PostJSONQuery("validateotherCameraIp.do",jsonotherIP,function(response){
						if(response){//不存在配置，可以使用这个IP
							PostJSONQuery("updateCameraInfo.do",json2,function(response){
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
								showAlertInfo(json2.cameraIP+"这个IP已经在其他摄像机中配置，请换一个IP");
			  				},600);
						}
						
					});
					
				}else{
					showAlertInfo("文本框存在不符合规范的字符串");
				}
			}
			
			
			
		}
	
}
