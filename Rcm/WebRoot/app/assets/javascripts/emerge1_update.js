//修改后按照告警和预警区分开来查询     
function OnEmergePageQuery_emerge1(query_criteria ){
	//检查入参
	//alert(JSON.stringify(query_criteria));
	var canInfo=false;
	  var alert_query_json = {
			        "page":{
			            "pageCount": "10",
			            "pageNo": "1"
			        },
			        "statments": "0",
			        "warningType": "故障告警"
			   };
	  if(query_criteria){
		  if(query_criteria.page){
			  alert_query_json.page.pageNo=query_criteria.page;
		  }
		  if(query_criteria.warningType){
			  alert_query_json.warningType=query_criteria.warningType;
		  }
		  if(query_criteria.statments){
			  alert_query_json.statments=query_criteria.statmetns;
		  }
	  }
	 var Url="/Rcm/Manage/getWaringListBystatments.do";
     PostJSONQuery(Url,alert_query_json,function(response){
	  $('#user1_emerge_page_totalpage')[0].innerHTML = response["totalPage"];
	  $('#user1_emerge_page_totalcount')[0].innerHTML = response["totalCount"];
	  var warningList=response["warningList"];
	//查询时先将之前的删掉再冲数据库查询后重新追加记录
	$("#query_result_table_body tr").remove();
	 	 var click_ensure="&quot;goto&quot;";
		 var click_cancel="&quot;goback&quot;";
		 //alert(warningList.length);
	  for(var i=0;i<warningList.length;i++){
		  var statmentsnumber= warningList[i]["statments"];
		  //0 未处理 123 确认中 4 已处理 5 已取消
		  var stat="";
		 
		  if(statmentsnumber==0){
			  stat="未处理";
		  }
		  if(statmentsnumber==1||statmentsnumber==2||statmentsnumber==3){
			  stat="确认中";
		  }
		  if(statmentsnumber==4){
			  stat="已处理";
		  }
		  if(statmentsnumber==5){
			  stat="已取消";
		  }
		  
		  var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
		 //warningList["warningId"]+","+warningList["equipmentId"]+","+warningList["equipmentname"]+","+warningList["systemName"]+","+warningList["warningType"]+","+warningtLevel[warningList[i]["warningTypeLevel"]]+","+warningList["warninginfo"]+","+warningList["lineNo"]+","+warningList["stationName"]+","+warningList["warningDate"]+","+warningList["stat"]
		 var warningList_array = new Array(((( $('#user1_emerge_page_nowpage')[0].innerHTML-1)*10)+1+i),warningList[i]["warningId"],warningList[i]["equipmentId"],warningList[i]["equipmentname"],warningList[i]["systemName"],warningList[i]["warningType"],warningtLevel[warningList[i]["warningTypeLevel"]],warningList[i]["warninginfo"],warningList[i]["lineNo"],warningList[i]["stationName"],stat);
			//alert(warningList_array);
		 var username = "\"user1\||";
		 var user_powerName="\"ensure\"";
		 var user_powerName_cancel="\"cancel\"";
		
		 var warningList_string_array = username+warningList_array.join("||")+"\"";
		 // alert(warningList_string_array);
		var onmouse=null;
			onmouse="<tr style='height: 26px; >";
			//将未处理的告警信息显示出来
			var number=((( $('#user1_emerge_page_nowpage')[0].innerHTML-1)*10)+1+i);
			 $('#query_result_table_body').append("" +
				  onmouse +
		  		"<th></th>" +
		  		"<td  scope='col' ></td>" +
				"<td  scope='col' id='user1_emerge1_id'>"+number+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["warningId"]+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["equipmentId"]+"</td>"+
		  		"<td  scope='col' >"+warningList[i]["equipmentname"]+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["systemName"]+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["warningType"]+"</td>" +
		  		"<td  scope='col' >"+warningtLevel[warningList[i]["warningTypeLevel"]]+"</td>" +
		  		"<td scope='col' >"+
		  		"<span   onmouseover='showDetail(true,this);' onmouseout='showDetail(false,this);'>"+warningList[i]["warninginfo"].substring(1,8)+"..."+"</span>"+
		  		"<div class='detail_info'>"+
           			"<textarea style='height:150px; width:250px;'rows='6' cols='1'>"+warningList[i]["warninginfo"]+
           			"</textarea>"+
        		"</div>"+
		  		"</td>" +
		  		"<td  scope='col' >"+warningList[i]["lineNo"]+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["stationName"]+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["warningDate"]+"</td>" +
		  		"<td  scope='col' >"+stat+"</th>" +
		  		"<td  scope='col'><a href='javascript:showUpdateInfo("+warningList_string_array+","+user_powerName+","+warningList[i]['id']+","+click_ensure+");' onclick='esureEvent("+warningList[i]['id']+",this);' class='next' >确认</a></td>"+
   				"<td  scope='col'><a href='javascript:showUpdateInfo("+warningList_string_array+","+user_powerName_cancel+","+warningList[i]['id']+","+click_cancel+");' onclick='cacelEvent("+warningList[i]['id']+",this);' class='next'>清除</a></td>"+
   				"</tr>"
		  		);
		
	  } 
	  /**********************显示user2终止的操作记录*****************************************/
	 // alert(JSON.stringify(query_criteria));
	  	if(query_criteria.warningType=="故障告警"){
	  		showUser2goBackMessage("chenqiang","2",1);
	  	}
    });  
     
     canInfo=true;
     return canInfo;
}
//---------------------------------------故障修预警 用户看到的是不同的状态 公共方法-----------------------------------------------------------------//
function showPlanMaintenceAlertInfo(query_criteria){
	var alert_query_json={
		        "page": {
		            "pageCount": "10",
		            "pageNo": "1"
		        },
		        "query": {
		            "endDate": "",
		            "equipmentname": "",
		            "statments": "0",
		            "lineNo": "",
		            "startDate": "",
		            "stationName": "",
		            "systemName": "",
		            "warningType": ""
		        }
		};
	  if(query_criteria){
		  if(query_criteria.page){
			  alert_query_json.page.pageNo=query_criteria.page;
		  }
		  if(query_criteria.endDate){
			  alert_query_json.query.endDate=query_criteria.endDate;
		  }
		  if(query_criteria.equipmentname){
			  alert_query_json.query.equipmentname=query_criteria.equipmentname;
		  }
		  if(query_criteria.statments){
			  alert_query_json.query.statments=query_criteria.statments;
		  }
		  if(query_criteria.lineNo){
			  alert_query_json.query.lineNo=query_criteria.lineNo;
		  }
		  if(query_criteria.startDate){
			  alert_query_json.query.startDate=query_criteria.startDate;
		  }
		  if(query_criteria.stationName){
			  alert_query_json.query.stationName=query_criteria.stationName;
		  }
		  if(query_criteria.systemName){
			  alert_query_json.query.systemName=query_criteria.systemName;
		  }
		  if(query_criteria.warningType){
			  alert_query_json.query.warningType=query_criteria.warningType;
		  }
	  }
	  
	  var Url="/Rcm/Manage/getPlanWaringList.do";
	  PostJSONQuery(Url,alert_query_json,function(response){
		 // alert(JSON.stringify(response));
		  $('#user1_emerge_page_totalpage')[0].innerHTML = response["totalPage"];
		  console.info("************总页数="+ $('#user1_emerge_page_totalpage')[0].innerHTML+"***************");
		  $('#user1_emerge_page_totalcount')[0].innerHTML = response["totalCount"];
		  var planMaintenceAlerts=response["planWarningList"];
		//查询时先将之前的删掉再冲数据库查询后重新追加记录
		$("#query_result_table_body tr").remove();
		  for(var i=0;i<planMaintenceAlerts.length;i++){
			//  alert(planMaintenceAlerts[i]['id']+","+planMaintenceAlerts[i]['equipmentId']);  ok
			var onmouse=null;
				onmouse="<tr style='height: 26px; >";
				//将未处理的告警信息显示出来
				var number=((( $('#user1_emerge_page_nowpage')[0].innerHTML-1)*10)+1+i);
				 $('#query_result_table_body').append("" +
					  onmouse +
			  		"<th></th>" +
			  		"<td  scope='col' ></td>" +
					"<td  scope='col' id='user1_emerge1_id'>"+number+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["systemName"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["subsystemName"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["lineNo"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["stationName"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["equipmentname"]+"</td>" +
			  		"<td scope='col' >"+
			  		"<span 	onmouseover='showDetail(true,this);' onmouseout='showDetail(false,this);'>"+planMaintenceAlerts[i]["warninginfo"].substring(0,3)+"..."+"</span>"+
			  		"<div   class='detail_info'>"+
	           			"<textarea style='height:150px; width:250px;'rows='6' cols='1'>"+planMaintenceAlerts[i]["warninginfo"]+
	           			"</textarea>"+
	        		"</div>"+
			  		"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["warningTypeLevel"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["warningDate"]+"</td>" +
			  		"<td  scope='col'><a href='javascript:;' onclick='esurePlanMaintenceEvent("+"\""+planMaintenceAlerts[i]['id']+"\""+","+"\""+planMaintenceAlerts[i]['equipmentId']+"\""+");' class='next'>确认</a></td>"+
	   				"</tr>"
			  		);
				
		  };
		  
	  });
	
}
//-----------------------------历史修分页---------------------------------------//
function  history_backpage(){
	var nowpage= $('#user1_emerge_page_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		$('#user1_emerge_page_nowpage')[0].innerHTML--;
		query_report_plan(backPageNo);
	}
	
}
function  history_nextpage(){
	var nowpage= $('#user1_emerge_page_nowpage')[0].innerHTML;
	var totalpage= $('#user1_emerge_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		$('#user1_emerge_page_nowpage')[0].innerHTML++;
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_report_plan(nextPageNo);
	}
}
/**历史计划修查询按钮*/
function query_report_plan(val9){	
	
	var val1 = $('#report_condition1').val();//系统
	var val2 = $('#report_condition2').val();//子系统
	var val3 = $('#report_condition3').val();//线路
	var val4 = $('#report_condition4').val();//车站
	var val5 = $('#report_condition5').val();//资产名称
	var val6 = $('#report_condition6').val();//告警等级
	var val7 = $('#report_start_time').val();//开始时间
	var val8 = $('#report_end_time').val();//结束时间
	var query_criteria={
			            "systemName": val1, 
			            "subSystemName": val2,
			            "lineNo": val3, 
			            "stationName":val4, 
			            "component": val5, 
			            "warningTypeLevel": val6, 
			            "starTime": val7, 
			            "endTime":val8, 
			            "pageNo": val9,
			            "statments":"1"
									};
	show_history_PlanMaintenceAlertInfo(query_criteria);
}

	

//历史修_计划修----------------------------------------------------//
function show_history_PlanMaintenceAlertInfo(query_criteria){
	//检查入参
	//alert(JSON.stringify(query_criteria));
	var alert_query_json={
		        "page": {
		            "pageCount": "10",
		            "pageNo": "1"
		        },
		        "query": {
		            "endDate": "",
		            "equipmentname": "",
		            "statments": "1",
		            "lineNo": "",
		            "startDate": "",
		            "stationName": "",
		            "systemName": "",
		            "warningTypeLevel":""
		        }
		};
	  if(query_criteria){
		  if(query_criteria.page){
			  alert_query_json.page.pageNo=query_criteria.page;
		  }
		  if(query_criteria.endDate){
			  alert_query_json.query.endDate=query_criteria.endDate;
		  }
		  if(query_criteria.equipmentname){
			  alert_query_json.query.equipmentname=query_criteria.equipmentname;
		  }
		  if(query_criteria.statments){
			  alert_query_json.query.statments=query_criteria.statments;
		  }
		  if(query_criteria.lineNo){
			  alert_query_json.query.lineNo=query_criteria.lineNo;
		  }
		  if(query_criteria.startDate){
			  alert_query_json.query.startDate=query_criteria.startDate;
		  }
		  if(query_criteria.stationName){
			  alert_query_json.query.stationName=query_criteria.stationName;
		  }
		  if(query_criteria.systemName){
			  alert_query_json.query.systemName=query_criteria.systemName;
		  }
		  if(query_criteria.warningType){
			  alert_query_json.query.warningType=query_criteria.warningType;
		  }
		  if(query_criteria.warningTypeLevel){
			  alert_query_json.query.warningTypeLevel=query_criteria.warningTypeLevel;
		  }
	  }
	  var Url="/Rcm/Manage/getPlanWaringList.do";
	  PostJSONQuery(Url,alert_query_json,function(response){
		 // alert(JSON.stringify(response));
		  $('#user1_emerge_page_totalpage')[0].innerHTML = response["totalPage"];
		  $('#user1_emerge_page_totalcount')[0].innerHTML = response["totalCount"];
		  var planMaintenceAlerts=response["planWarningList"];
		//查询时先将之前的删掉再冲数据库查询后重新追加记录
		$("#query_result_table_body tr").remove();
		  for(var i=0;i<planMaintenceAlerts.length;i++){
			//  alert(planMaintenceAlerts[i]['id']+","+planMaintenceAlerts[i]['equipmentId']);  ok
			var onmouse=null;
				onmouse="<tr style='height: 26px; >";
				//将未处理的告警信息显示出来
				var number=((( $('#user1_emerge_page_nowpage')[0].innerHTML-1)*10)+1+i);
				 $('#query_result_table_body').append("" +
					  onmouse +
			  		"<th></th>" +
			  		"<td  scope='col' ></td>" +
					"<td  scope='col' id='user1_emerge1_id'>"+number+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["systemName"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["subsystemName"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["lineNo"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["stationName"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["equipmentname"]+"</td>" +
			  		"<td scope='col' >"+
			  		"<span   onmouseover='showDetail(true,this);' onmouseout='showDetail(false,this);'>"+planMaintenceAlerts[i]["warninginfo"].substring(1,8)+"..."+"</span>"+
			  		"<div class='detail_info'>"+
	           			"<textarea style='height:150px; width:250px;'rows='6' cols='1'>"+planMaintenceAlerts[i]["warninginfo"]+
	           			"</textarea>"+
	        		"</div>"+
			  		"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["warningTypeLevel"]+"</td>" +
			  		"<td  scope='col' >"+planMaintenceAlerts[i]["warningDate"]+"</td>" +
	   				"</tr>"
			  		);
				
		  };
		  
	  });
	
}
//-------------------------------故障计划修的确认按钮--------------------------------------------------//
function esurePlanMaintenceEvent(id,equipmentId){
	//alert(id+","+equipmentId);  ok
	var alert_query_json = {
		        "equipmentId": equipmentId,
		        "id": id
		};
	if(confirm("确认提交？")){
	    var Url="/Rcm/Manage/commitPlanWaringById.do";
		PostJSONQuery(Url,alert_query_json,function(response){
			alert("完成确认");
			window.location.reload();
		});
		
	}
	
	
}
//----------------------------在user1界面显示消息提示的信息----------------------------------------------//
function showUser2goBackMessage(userName,fromUser,nowpage){ 
	//检查入参
	//alert(userName,fromUser,nowpage);
	 var operative_index_query_json = {
				   "userName":"user1",
				   	"fromuser":fromUser
				 };
	 
	 var Url="/Rcm/Manage/getMessageList.do";
			  PostJSONQuery(Url,operative_index_query_json,
			    function(response){
				  //alert("showuser2mes"+JSON.stringify(response));
				  var list=response.messageList;
				  var totalCount = list.length;
				  var rowsPerPage = 10;
				  var totalPage = Math.floor((totalCount-1)/rowsPerPage)+1;
				  $("#alert_page_totalCount")[0].innerHTML=totalCount;
				  $("#alert_page_totalpage")[0].innerHTML=totalPage;
				   $("span table #query_result_table_body tr").remove();
				  for(var i=(nowpage-1)*10;i<(nowpage!=totalPage?nowpage*10:nowpage*10+(totalPage%10));i++){
				  
				  $("span table #query_result_table_body").append(
							  ""+
							  "<tr style='height:26px;'>"+
							  "<td>"+(i+1)+"</td>"+
							  "<td style='text-align:left;'>"+list[i].message+"</td>"+
							  "<td>"+list[i].infodate+"</td>"+
							  "</tr>"
					  );
				  }
			  });
}
//分页---------------------------提示信息-----------------------------------//
function  alert_backpage(){
	var nowpage= $('#alert_page_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		$('#alert_page_nowpage')[0].innerHTML -=1;
		var backPageNo=nowpage-1;
		showUser2goBackMessage("user1","2",backPageNo);
	}
	
}
function  alert_nextpage(nowpage){
	var nowpage= $('#alert_page_nowpage')[0].innerHTML;
	var totalpage= $('#alert_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		$('#alert_page_nowpage')[0].innerHTML++;
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		showUser2goBackMessage("user1","2",nextPageNo);
	}
	
}

//user1将状态更新 确认和清除
function OnOperativeUpdateOfUser1(id,stat){
  var operative_index_query_json = {
	  "action":stat,
	  "ids":[id]};
  var Url="/Rcm/Manage/warningOperate.do";
  PostJSONQuery(Url,operative_index_query_json,
    function(response){
	  var v_info1="确认成功";
	  var v_info2="确认失败";
	  var v_info3="清除成功";
	  var v_info4="清除失败";
	 if(response.info=="sucess"&&stat=="confirm"){
			 	window.confirm(v_info1);
			    OnEmergePageQuery_emerge1({"page":$('#user1_emerge_page_nowpage')[0].innerHTML});
			    window.location.reload();
		
		 
	 }else if(response.info=="false"&&stat=="confirm"){
		 window.confirm(v_info2);
	 }else if(response.info=="sucess"&&stat=="cancel"){
		 window.confirm(v_info3);
		 OnEmergePageQuery_emerge1({"page":$('#user1_emerge_page_nowpage')[0].innerHTML});
	 }else if(response.info=="false"&&stat=="cancel"){
		 window.confirm(v_info4);
	 }
    
    });
}
//故障告警-----------------------------------------------------//
/**上一页*/
function backpage(req){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#user1_emerge_page_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		 $('#user1_emerge_page_nowpage')[0].innerHTML = backPageNo;
		OnEmergePageQuery_emerge1({"page":backPageNo,"warningType":req});
	}
	
}

/**下一页*/
function nextpage(req){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#user1_emerge_page_nowpage')[0].innerHTML;
	var totalpage= $('#user1_emerge_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		 $('#user1_emerge_page_nowpage')[0].innerHTML = nextPageNo;
		OnEmergePageQuery_emerge1({"page":nextPageNo,"warningType":req});
	}
}
//--------------------------------计划修预警分页-------------------------------------//
/**上一页*/
function backpage_plan(req){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#user1_emerge_page_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		 $('#user1_emerge_page_nowpage')[0].innerHTML = backPageNo;
		 showPlanMaintenceAlertInfo({"page":backPageNo,"warningType":req});
	}
	
}

/**下一页*/
function nextpage_plan(req){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#user1_emerge_page_nowpage')[0].innerHTML;
	var totalpage= $('#user1_emerge_page_totalpage')[0].innerHTML;
	console.info("************nowpage="+nowpage+"*************");
	console.info("************totalpage="+totalpage+"*************");
	//比较大小注意数据的类型
	if(parseInt(nowpage)>=parseInt(totalpage)){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		 $('#user1_emerge_page_nowpage')[0].innerHTML = nextPageNo;
		 showPlanMaintenceAlertInfo({"page":nextPageNo,"warningType":req});
	}
}
//user1的确认  和 清除操作------------------------------------------------//
function esureEvent(id){
		
	sendNextUser_Byuser1(true,id);
			
		 
}

function cacelEvent(id,td){
	 if(window.confirm("确认清除？")){
		 var stat="cancel";
		 OnOperativeUpdateOfUser1(id,stat,td);
		 
	 }
	
}
//-------------------------------------------------------//

//user1用户进行确认后消息框中的消息提示信息插入到数据库中----------------------------//
function  showUpdateInfo(warningList_string_array,user_powerName,warningid,click_flag){
	 var warning_split_array = warningList_string_array.split("||");
	 var message_confirm="操作员"+warning_split_array[0]+"，对,"+warning_split_array[3]+","+warning_split_array[4]+","+warning_split_array[5]+","+warning_split_array[6]+","+warning_split_array[7]+","+warning_split_array[8]+","+warning_split_array[9]+","+warning_split_array[10]+","+warning_split_array[11]+"的进行了确认";
	 var message_cancel= "操作员"+warning_split_array[0]+"，对,"+warning_split_array[3]+","+warning_split_array[4]+","+warning_split_array[5]+","+warning_split_array[6]+","+warning_split_array[7]+","+warning_split_array[8]+","+warning_split_array[9]+","+warning_split_array[10]+","+warning_split_array[11]+"的进行了清除";
	 var message="";
	  if(warning_split_array[0]=="user1"&&user_powerName=="ensure"){
			   message=message_confirm;
			
		  }else if(warning_split_array[0]=="user1"&&user_powerName=="cancel"){
			   message=message_cancel;
		  }
	warning_split_array = warningList_string_array.split("||");
	var query_update_info = {
    			"warningId":warningid,
       			 "message":message,
       			 "time":new Date().format("yyyy-MM-dd hh:mm:ss"),
       			 "ok":click_flag
};
	 var Url="/Rcm/Manage/confirmorcancel.do";
	PostJSONQuery(Url,query_update_info,
    function(response){
		//alert(JSON.stringify(response));
   });
	
}
//显示浮动层
function  showFloatInfo(){
		 $("#query_result_table_body tr").each(function(i,e){
	 		var $e=$(e);
	 		$e.mouseover(function(e){
	 				var py=e.pageY;
	 			//alert(this.tagName); 显示tr
	 			//显示动态数据，从后台获取 {"method":"getEquipmentAllInfo","req":{"equipmentId":"10.29.08.01.001"},"type":"WarningManager"}
			 			//获取光标坐标 
			 			$("#query_result_table_body:eq(0) tr:eq("+i+")").after("<div id='dynamicFloor'><span>序号"+(i+1)+"</span>"+"<span>"+"</span></div>");
			 			var equipmentId = $("#query_result_table_body tr:eq("+i+") td:eq("+2+")").html();
			 			getFloatInfo(equipmentId);
			 			$("#dynamicFloor").css({"position":"absolute"});
			 			$("#dynamicFloor").css({"top":py+"px"});
			 			$("#dynamicFloor").css({"z-index":"1000"});
			 			$("#dynamicFloor").css({"width":"180px"});
			 			$("#dynamicFloor").css({"height":"260px"});
			 			$("#dynamicFloor").css({"left":"13px"});
			 			$("#dynamicFloor").css({"background-color":"#ddddff"});
			 			$("#dynamicFloor").css({"opacity":"1"});
	 			
	 				
	 		});
	 		//隐藏浮动层
	 		$e.mouseout(function(){
	 			$("#query_result_table_body>div").remove();
	 		});
		 	 });
	 		
	
	
}
//放置浮动提示信息
function   getFloatInfo(equipmentId){
	var req_json={"method":"getEquipmentAllInfo","req":{"equipmentId":"\""+equipmentId+"\""},"type":"WarningManager"};
	var url="/Rcm/Manage/getEquipmentAllInfo.do";
	PostJSONQuery(url,req_json,function(response){
		
			$("#dynamicFloor").append("<table id='float_table'>"+
					"<tr><td>组件名称:</td><td>"+response.componentName+"</td></tr>" +
					"<tr><td>型号规格:</td><td>"+response.equipmentType+"</td></tr>" +
					"<tr><td>生产时间:</td><td>"+response.productionDate+"</td></tr>" +
					"<tr><td>购买时间:</td><td>"+response.purchaseDate+"</td></tr>" +
					"<tr><td>生产厂家:</td><td>"+response.manufacturer+"</td></tr>" +
					"<tr><td>厂家编码:</td><td>"+response.manufacturerId+"</td></tr>" +
					"<tr><td>详情描述:</td><td>"+response.details+"</td></tr>" +
					"<tr><td>描述信息:</td><td>"+response.description+"</td></tr>" +
					"<tr><td>环境位置信息:</td><td>"+response.environment+"</td></tr>" +
					"<tr><td>故障影响:</td><td>"+response.effect+"</td></tr>" +
					"</table>");
			$("#float_table").css({"position":"absolute"});
			$("#float_table").css({"left":"-1%"});
			$("#float_table").css({"spacing":"40px"});
			$("#float_table").css({"width":"180px"});
			$("#float_table").css({"height":"200px"});
			$("#float_table").css({"border":"3px solid gray"});
			$("#float_table").css({"text-align":"right"});
			$("#float_table").css({"background-color":"#ffffff"});
			$("#float_table tr").css({"width":"100px"});
		});
};
//--------------------------------------------------------//
/**用户进行确认核实审定操作的提示信息方法*/
//------------------------------用户1
function sendNextUser_Byuser1(flag,id){
	$("#showUsers").find("div").remove();
	if(flag){
		$("#showUsers").append(

           			"<div style='background-color:#fee08f;position:absolute;z-index:100; width:300px;height:200px; border:0px solid red' >"+
					"<label style='position:relative;left:25%;'>请选择下一位处理人</label>"+
						"<div style='position:relative;left:35%;top:20px;'><p>"+
						  "<input  type='radio' name='userName' value='' checked='checked'/>"+
						 "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;张华</span><br/>"+
					  "</p>"+
						"<p>"+
						  "<input  type='radio' name='userName' value=''/>"+
						  "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;李华</span><br/>"+
					  "</p>"+
						"<p>"+
						  "<input  type='radio' name='userName' value=''/>"+
						  "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;刘鹏</span><br/>"+
					        "</p><div>"+
						"<div style='position:relative;left:-75px;top:55px;'>"+
						  "<input name='button' type='button'class='button' style='' value='确定' onclick='send_user2("+id+");' />"+
						  			"<span> &nbsp;&nbsp;&nbsp;"+ 
						 "&nbsp;&nbsp;&nbsp; </span>"+
					  "<input name='button2'  type='button' class='button' style='' value='取消' onclick='cancel();'/>"+
					  "</div>"+
           			"</div>"
);
		$("#showUsers").css({"position":"absolute"});
		$("#showUsers").css({"left":getMonitorSize()[0]/3+"px"});
		$("#showUsers").css({"top":getMonitorSize()[1]/4+"px"});
		$("#showUsers").append("<div style='background-color:#cccccc;opacity:.5;width:"+getMonitorSize()[0]+"px;height:"+getMonitorSize()[1]+"px;position:absolute;z-index:10;left:"+(-getMonitorSize()[0]/3)+"px;top:"+(-getMonitorSize()[1]/3)+"px'></div>");
		$("#showUsers").slideDown("slow");
	
	}else{
		$("#showUsers").slideUp("slow");
	}
	
}
function send_user2(id,user){
	if(confirm('是否确认提交?')){
		$("#showUsers").slideUp("slow");
					var stat = "confirm";
					 OnOperativeUpdateOfUser1(id,stat);
			return true;
		}
}
//---------------------用户2
function sendNextUser_Byuser2(flag,id){
	$("#showUsers").find("div").remove();
	if(flag){
		$("#showUsers").append(
				"<div style='background-color:#fee08f;position:absolute;z-index:100; width:300px;height:200px; border:0px solid red' >"+
       			"<div style='background-color:#fee08f; width:300px;height:200px; border:0px solid red' >"+
				"<label style='position:relative;left:25%;'>请选择下一位处理人</label>"+
					"<div style='position:relative;left:35%;top:20px;'><p>"+
					  "<input  type='radio' name='userName' value='' checked='checked'/>"+
					 "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;张华</span><br/>"+
				  "</p>"+
					"<p>"+
					  "<input  type='radio' name='userName' value=''/>"+
					  "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;李华</span><br/>"+
				  "</p>"+
					"<p>"+
					  "<input  type='radio' name='userName' value=''/>"+
					  "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;刘鹏</span><br/>"+
				        "</p><div>"+
					"<div style='position:relative;left:-75px;top:55px;'>"+
						  "<input name='button' type='button' class='button' value='确定' onclick='send_user3("+id+");' />"+
					  "<span> &nbsp;&nbsp;&nbsp;"+ 
						 "&nbsp;&nbsp;&nbsp; </span>"+
					  "<input name='button2'  type='button'class='button' value='取消' onclick='cancel();'/>"+
					  "</p>"+
           			"</div>"
);
		$("#showUsers").css({"position":"absolute"});
		$("#showUsers").css({"left":getMonitorSize()[0]/3+"px"});
		$("#showUsers").css({"top":getMonitorSize()[1]/2+"px"});
		$("#showUsers").append("<div style='background-color:#cccccc; opacity:0.5; width:"+getMonitorSize()[0]+"px;height:"+getMonitorSize()[1]+"px;position:absolute;z-index:10;left:"+(-getMonitorSize()[0]/3)+"px;top:"+(-getMonitorSize()[1]/2)+"px'></div>");
		$("#showUsers").slideDown("slow");
	
	}else{
		$("#showUsers").slideUp("slow");
	}
	
}
function send_user3(id,stat){
		$("#showUsers").slideUp("slow");
					OnCheckProcess(id,stat);
			return true;
}
//-------------------------------------------用户3  ok  入参为 "goto"  或者"goback"
function sendNextUser_Byuser3(id,ok){
	$("#showUsers").find("div").remove();
	if(flag){
		$("#showUsers").append(
				"<div style='background-color:#fee08f; position:absolute;z-index:100;width:300px;height:200px; border:0px solid red' >"+
				"<label style='position:relative;left:25%;'>请选择下一位处理人</label>"+
					"<div style='position:relative;left:35%;top:20px;'><p>"+
					  "<input  type='radio' name='userName' value='' checked='checked'/>"+
					 "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;张华</span><br/>"+
				  "</p>"+
					"<p>"+
					  "<input  type='radio' name='userName' value=''/>"+
					  "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;李华</span><br/>"+
				  "</p>"+
					"<p>"+
					  "<input  type='radio' name='userName' value=''/>"+
					  "<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;刘鹏</span><br/>"+
				        "</p><div>"+
					"<div style='position:relative;left:-75px;top:55px;'>"+
						  "<input name='button' type='button' class='button' value='确定' onclick='send_user4("+id+","+ok+");' />"+
					  "<span> &nbsp;&nbsp;&nbsp;"+ 
						 "&nbsp;&nbsp;&nbsp; </span>"+
					  "<input name='button2'  type='button'  class='button'  value='取消' onclick='cancel();'/>"+
					  "</p>"+
           			"</div>"
);
		$("#showUsers").css({"position":"absolute"});
		$("#showUsers").css({"left":getMonitorSize()[0]/3+"px"});
		$("#showUsers").css({"top":getMonitorSize()[1]/4+"px"});
		$("#showUsers").append("<div style='background-color:#cccccc;opacity:.5;width:"+getMonitorSize()[0]+"px;height:"+getMonitorSize()[1]+"px;position:absolute;z-index:10;left:"+(-getMonitorSize()[0]/3)+"px;top:"+(-getMonitorSize()[1]/3.6)+"px'></div>");
		$("#showUsers").slideDown("slow");
	
	}else{
		$("#showUsers").slideUp("slow");
	}
	
}
function send_user4(id,ok){
	
		$("#showUsers").slideUp("slow");
				OnApproveByUser3(id,ok);
			return true;
}
function cancel(){
	$("#showUsers").slideUp("slow");
}
/**-----------------------------------------*/
//获取屏幕宽高
function getMonitorSize(){
	return [document.body.scrollWidth,document.body.scrollHeight];
}

