/************************日志管理********************************/
//入参
function searchlogerManage(page){
	if(page==0){
		page=1;
	}
	var userName=$("#userName").val();
	var userAccount=$("#userAccount").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var operateType=$("#operateType").val();
	if(operateType=="--请选择--"){
		operateType='';
	}
	if(veriftyChar(userName)&&veriftyChar(userAccount)){
		selectLoger(userName,userAccount,startTime,endTime,operateType,page,10);
	}else{
		showAlertInfo("查询条件存在非法字符");
	}
}
//显示
function selectLoger(userName,userAccount,startTime,endTime,operateType,page,pageSize){
	var json={
			operatorName:userName,
			operatorAccount:userAccount,
			startTime:startTime+" 00:00:00",
			endTime:endTime+" 24:00:00",
			operateType:operateType,
			page:page,
			pageSize:pageSize
	};
	PostJSONQuery("searchOperatorLogInfo.do",json,function(response){
		//showAlertInfo(JSON.stringify(response));
		logInfo = response.logInfo;
		$('#totalPage')[0].innerHTML = 	response.pages;
		$("table tbody tr td").html("");
		logInfo.forEach(function(e,i){
        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(page)-1)*10+i+1);
        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.operatorAccount);
        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.operatorFormatDate);
        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.operateType);
        				$("table tbody tr:eq("+i+") td:eq(4)").html(e.remark); 
       
        	        	}); 
        	         
	      
	});
}
