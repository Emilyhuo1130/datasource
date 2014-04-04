function OnEmergePageQuery_emerge1(query_criteria ){
	var canInfo=false;
  var alert_query_json = {
	  		"page":{"pageCount":"10",
				    "pageNo":"1"},
	        "statments":"0"};
  if(query_criteria){
	  if(query_criteria.page){
		  alert_query_json.page.pageNo=query_criteria.page;
	  }
  }
  var Url="/Rcm/Manage/getWaringListBystatments.do";
     PostJSONQuery(Url,alert_query_json,function(response){
	 
	  var pageNo = alert_query_json.page.pageNo; 
	 // $('#alert_count')[0].innerHTML = response["totalCount"];
	  $('#user1_emerge_page_nowpage')[0].innerHTML = pageNo;
	  $('#user1_emerge_page_totalpage')[0].innerHTML = response["totalPage"];
	   $('#user1_emerge_page_totalcount')[0].innerHTML = response["totalCount"];
	  var warningList=response["warningList"];
	  //window.confirm(JSON.stringify(warningList))
	  
	//查询时先将之前的删掉再冲数据库查询后重新追加记录
	$("#query_result_table_body tr").remove();
	 	 var click_ensure="&quot;goto&quot;";
		 var click_cancel="&quot;goback&quot;";
	  for(var i=0;i<warningList.length;i++){
		  var statmentsnumber= warningList[i]["statments"];
		 // window.confirm(statmentsnumber);//获得状态信息列表
		  
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
		  var page=pageNo;
		 //warningList["warningId"]+","+warningList["equipmentId"]+","+warningList["equipmentname"]+","+warningList["systemName"]+","+warningList["warningType"]+","+warningtLevel[warningList[i]["warningTypeLevel"]]+","+warningList["warninginfo"]+","+warningList["lineNo"]+","+warningList["stationName"]+","+warningList["warningDate"]+","+warningList["stat"]
		 var warningList_array = new Array((((page-1)*10)+1+i),warningList[i]["warningId"],warningList[i]["equipmentId"],warningList[i]["equipmentname"],warningList[i]["systemName"],warningList[i]["warningType"],warningtLevel[warningList[i]["warningTypeLevel"]],warningList[i]["warninginfo"],warningList[i]["lineNo"],warningList[i]["stationName"],stat);
			//alert(warningList_array);
		 var username = "\"user1\||";
		 var user_powerName="\"ensure\"";
		 var user_powerName_cancel="\"cancel\"";
		
		 var warningList_string_array = username+warningList_array.join("||")+"\"";
		 // alert(warningList_string_array);
		var leve=warningList[i]["warningTypeLevel"];
		var checkBox=null;
		var onmouse=null;
		
		if(stat=="未处理"){
			onmouse="<tr style='height: 26px; >";
			//将未处理的告警信息显示出来
			var number=(((page-1)*10)+1+i);
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
		  		"<td  scope='col' >"+warningList[i]["warninginfo"]+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["lineNo"]+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["stationName"]+"</td>" +
		  		"<td  scope='col' >"+warningList[i]["warningDate"]+"</td>" +
		  		"<td  scope='col' >"+stat+"</th>" +
		  		"<td  scope='col'><a href='javascript:showUpdateInfo("+warningList_string_array+","+user_powerName+","+warningList[i]['id']+","+click_ensure+");' onclick='esureEvent("+warningList[i]['id']+",this);' class='next'  >确认</a></td>"+
   				"<td  scope='col'><a href='javascript:showUpdateInfo("+warningList_string_array+","+user_powerName_cancel+","+warningList[i]['id']+","+click_cancel+");' onclick='cacelEvent("+warningList[i]['id']+",this);' class='next'  >清除</a></td>"+
   				"</tr>"
		  		);
		}
		
	  } 
	  /**********************显示user2终止的操作记录*****************************************/
	  showUser2goBackMessage("user1","2",1);
    });  
     
     canInfo=true;
     return canInfo;
}
//----------------------------在user1界面显示消息提示的信息----------------------------------------------//
function showUser2goBackMessage(userName,fromUser,nowpage){ 
	
	 var operative_index_query_json = {
				   "userName":userName,
				   	"fromuser":fromUser
			};
	 
	 var Url="/Rcm/Manage/getMessageList.do";
			  PostJSONQuery(Url,operative_index_query_json,
			    function(response){
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
//分页
function  alert_backpage(){
	var nowpage= $('#alert_page_nowpage')[0].innerHTML;
	var totalpage= $('#alert_page_totalpage')[0].innerHTML;
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
		//alert("nextpae="+nextPageNo);
		showUser2goBackMessage("user1","2",nextPageNo);
	}
	
}

//user1将状态更新 确认和清除
function OnOperativeUpdateOfUser1(id,stat,td){
	
  var operative_index_query_json = {
     "action":stat,
	  "ids":[id]
  };
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
		 
	 }else if(response.info=="false"&&stat=="confirm"){
		 window.confirm(v_info2);
	 }else if(response.info=="sucess"&&stat=="cancel"){
		 window.confirm(v_info3);
		 OnEmergePageQuery_emerge1({"page":$('#user1_emerge_page_nowpage')[0].innerHTML});
	 }else if(response.info=="false"&&stat=="cancel"){
		 window.confirm(v_info4);
	 }
      var health_array = response['listHealth'];
    
    });
}

/**上一页*/
function backpage(){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#user1_emerge_page_nowpage')[0].innerHTML;
	var totalpage= $('#user1_emerge_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		OnEmergePageQuery_emerge1(backPageNo);
	}
	
}
/**下一页*/
function nextpage(){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#user1_emerge_page_nowpage')[0].innerHTML;
	var totalpage= $('#user1_emerge_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		//alert("nextpage="+nextPageNo);
		OnEmergePageQuery_emerge1({"page":nextPageNo});
	}
}
//user1的确认  和 清除操作------------------------------------------------//
function esureEvent(id,td){
		 if(window.confirm("确认提交？")){
			 var stat = "confirm";
			 OnOperativeUpdateOfUser1(id,stat,td);
			
		 }
}

function cacelEvent(id,td){
	 if(window.confirm("确认清除？")){
		 var stat="cancel";
		 OnOperativeUpdateOfUser1(id,stat,td);
		 
	 }
	
}
//user1用户进行确认后消息框中的消息提示信息插入到数据库中----------------------------//
function  showUpdateInfo(warningList_string_array,user_powerName,warningid,click_flag){
	 var user1_powerName="确认";
	 var warning_split_array = warningList_string_array.split("||");
	 var message_confirm="操作员"+warning_split_array[0]+"，对,"+warning_split_array[3]+","+warning_split_array[4]+","+warning_split_array[5]+","+warning_split_array[6]+","+warning_split_array[7]+","+warning_split_array[8]+","+warning_split_array[9]+","+warning_split_array[10]+","+warning_split_array[11]+"的进行了确认";
	 var message_cancel= "操作员"+warning_split_array[0]+"，对,"+warning_split_array[3]+","+warning_split_array[4]+","+warning_split_array[5]+","+warning_split_array[6]+","+warning_split_array[7]+","+warning_split_array[8]+","+warning_split_array[9]+","+warning_split_array[10]+","+warning_split_array[11]+"的进行了清除";
	 var message="";
	  if(warning_split_array[0]=="user1"&&user_powerName=="ensure"){
			   message=message_confirm;
			
		  }else if(warning_split_array[0]=="user1"&&user_powerName=="cancel"){
			   message=message_cancel;
		  }
	var warning_split_array = new Array();
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
			 			$("#query_result_table_body:eq(0) div").remove();
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
	 			$("#query_result_table_body div").remove();
	 		});
		 	 });
	 		
	
	
}
//放置浮动提示信息
function   getFloatInfo(equipmentId){
	var req_json={"equipmentId":"\""+equipmentId+"\""};
	 var Url="/Rcm/Manage/getEquipmentAllInfo.do";
	PostJSONQuery(Url,req_json,function(response){
			$("#dynamicFloor").append("<table id='float-table'>"+
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
			$("#float-table").css({"position":"absolute"});
			$("#float-table").css({"left":"0px"});
			$("#float-table").css({"spacing":"40px"});
			$("#float-table").css({"width":"180px"});
			$("#float-table").css({"height":"200px"});
			$("#float-table").css({"border":"3px solid gray"});
			$("#float-table").css({"text-align":"right"});
			$("#float-table").css({"background-color":"#ffffff"});
			$("#float-table tr").css({"width":"100px"});
		});
};
