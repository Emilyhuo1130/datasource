/********************************系统配置信息*********************************/
//显示系统配置信息
function searchsystemConfig(page){
	if(page==0){
		page=1;
	}
	selectsystemConfig(page,8);
}

function selectsystemConfig(page,pageSize){
	var json={
			page:page,
			pageSize:pageSize
	};
	PostJSONQuery("searchAllDiskInfo.do",json,function(response){
		//showAlertInfo(JSON.stringify(response));
		$('#totalPage')[0].innerHTML = 	response.totalPage;
		$("#surplus_capacity")[0].innerHTML=response.totalSurplusCapacity;
		$("#least_capacity").val(response.perDiskCapacityLess);
		var diskList=response.diskList;
		$("table tbody tr td").html("");
		diskList.forEach(function(e,i){
        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(page)-1)*7+i+1);
        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.diskName);
        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.diskSize);
        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.diskSurplusCapacity);
        				 
       
        	        	}); 
        	         	
	      
	});
}
//保存系统配置信息
function savesystemConfig(){
	
	$("#someinfo").html("是否确定真的保存？");
	$(".test").fbmodal({
		cancelbutton: true
	}
		,function(callback){
		    if(callback == 1){
		    	tosavesystemConfig();
		    }
			if(callback == 2){
		          
		    }
	});  
	
	
	
	
	
}

function tosavesystemConfig(){
	var number=$("#least_capacity").val();
	if(veriftyNumber99(number)){
		var json={
				perDiskCapacityLess:number
		};
		PostJSONQuery("updateDiskInfo.do",json,function(response){
		//	alert(JSON.stringify(response));
			if(response){
				setTimeout(function(){
					showAlertInfo("保存成功");
  				},1000);
			}else{
				setTimeout(function(){
					showAlertInfo("保存失败");
  				},1000);
			}
			
		});
	}else{
		setTimeout(function(){
					showAlertInfo("请输入大于1小于100的数字");
  		},600);
		
	}
}


/****读取系统警戒值*****/
function readsystemMalarm_Value(){
	var json={};
	PostJSONQuery("getSystemInfo.do",json,function(response){
		//alert(JSON.stringify(response));
		var info=response;
			$("#CPUnumber").val(info.cpuOccupancy);
			$("#flowNumber").val(info.flowRate);
			$("#zoneNumber").val(info.totalDiskIndex);
			$("#lastZoneNumber").val(info.perDiskIndex);
	});
}
/****保存系统警戒值*****/
function saveSystemMalarmInfo(){
	$("#someinfo").html("是否确定真的保存？");
	$(".test").fbmodal({
		cancelbutton: true
	}
		,function(callback){
		    if(callback == 1){
		    	tosaveSystemMalarmInfo();
		    }
			if(callback == 2){
		          
		    }
	});  
	
	
	
}

function tosaveSystemMalarmInfo(){
	
	
	var CPUnumber=$("#CPUnumber").val();
	var flowNumber=$("#flowNumber").val();
	var zoneNumber=$("#zoneNumber").val();
	var lastZoneNumber=$("#lastZoneNumber").val();
	
	if(CPUnumber.length==0||flowNumber.length==0||zoneNumber.length==0||lastZoneNumber.length==0){
		showAlertInfo("请填写完整信息");
	}else{
		var flag=(veriftyNumber99(CPUnumber))&&(veriftyNumber99(lastZoneNumber))&&(veriftyNumber99(flowNumber))&&(veriftyNumber99(zoneNumber));
		if(flag){//信息符合规则 提交发请求
			var json={
					cpuOccupancy:CPUnumber,
					flowRate:flowNumber,
					totalDiskIndex:zoneNumber,
					perDiskIndex:lastZoneNumber
			};
			PostJSONQuery("updateSystemInfo.do",json,function(response){
				if(response){
					setTimeout(function(){
						showAlertInfo("保存成功");
	  				},1000);
				}else{
					setTimeout(function(){
						showAlertInfo("保存失败");
	  				},1000);
				}
			});
		}else{//存在错误信息
			showAlertInfo("存在不合符规范的字符");
		}
	}
}