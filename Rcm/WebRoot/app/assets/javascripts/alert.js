//globle 全局变量的定义区域

var  g_id = 0;
var g_desc=null;
var stop_message_json_user=null;//终止流程信息
var check_opinion_json=null;
var start_Time=null;
var user3_stop=false;
var user="";//
var flag="No";
//方法区域
function OnAlertContentReceived(xmlhttp)
{
  if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
  {
    //alert(xmlhttp.responseText);
    var table_body = document.getElementById("query_result_table_body");
    $("#query_result_table_body").empty();
    //table_body.rows.clear();
    table_body.innerHTML = xmlhttp.responseText;
    TableInitial('query_result_table', 14, 20);
  }
}



function OnAlertQuery()
{
  LoadXMLDoc('/alert/alert_content', 'POST', OnAlertContentReceived);
}

function OnAbortProcess(next_url){
  if(confirm('确认是否终止流程?')){
    self.location = next_url;
  }
}

function OnCheckProcess(previous_url)
{
  if(confirm('是否确认核实?'))
  {
    self.location = previous_url;
  }
}
function OnAuditProcess(previous_url)
{
  if(confirm('是否确认审定?'))
  {
    self.location = previous_url;
  }
}

function OnTicketSend(next_url)
{
  if(confirm('确认发送通知单?'))
  {
    self.location = next_url;
  }
}


/**提供给user2 user3 user4使用------------------详情*/
var user4_warninglevel=1;
function showWarningLisstByStatments(statments){
	 var operative_index_query_json = {
			        "page": {
			            "pageCount": "20",
			            "pageNo": "1"
			        },
			        "statments": statments
			};
	 
	 var Url="/Rcm/Manage/getWaringListBystatments.do";
	 PostJSONQuery(Url,operative_index_query_json,
			    function(response){
		 var warningList=response.warningList;
		 // $("#query_result_table_body").remove();
		  var from ={"1":"确认","2":"核实","3":"审定","4":"已处理"};
		  var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
		 for(var i=0;i<warningList.length;i++){
			 //0 未处理 123 确认中 4 已处理 5 已取消
		      var statmentsnumber=warningList[i].statments;
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
			 var statmentsnumber= warningList[i]["statments"];
			 var warningList_array = new Array(warningList[i]["equipmentId"],warningList[i]["equipmentname"],warningList[i]["systemName"],warningList[i]["warningType"],warningtLevel[warningList[i]["warningTypeLevel"]],warningList[i]["warninginfo"],warningList[i]["lineNo"],warningList[i]["stationName"],warningList[i]["warningDate"],stat,"系统生成",from[warningList[i].statments]);
			//alert(warningList_array);
			 user4_warninglevel=warningList[i]["warningTypeLevel"];
			  $("#query_result_table_body").append("<tr style='height: 26px; border-bottom: 1px solid gray'>" +
			  		"<td id='equipmentId'>"+warningList[i].equipmentId+"</td>" +
			  		"<td id='equipmentname'>"+warningList[i].equipmentname+"</td>" +
			  		"<td id='systemName'>"+warningList[i].systemName+"</td>" +
			  		"<td id='warningType'>"+warningList[i].warningType+"</td>" +
			  		"<td id='warningTypeLevel'>"+warningtLevel[warningList[i]["warningTypeLevel"]]+"</td>" +
			  		"<td id='warninginfo'>"+warningList[i].warninginfo+"</td>" +
			  		"<td id='warninginfo'>"+warningList[i].lineNo+"</td>" +
			  		"<td id='stationName'>"+warningList[i].stationName+"</td>" +
			  		"<td id='warningDate'>"+warningList[i].warningDate+"</td>" +
			  		"<td id='stat'>"+stat+"</td>" +
			  		"<td id='native'>系统生成</td>" +
			  		"<td id='from'>"+from[warningList[i].fromuser]+"</td>" +
			  		"<td ><a href='"+encodeURI("show"+statments+".jsp?id="+warningList[i].id+"&stat="+warningList[i].statments+"&equipmentId="+warningList[i].equipmentId)+"&user=user"+(eval(statments)+1)+"'>详情</a></td>" +
			  				"</tr>");
			  }
		 });
}

//user2获得信息详细信息
var user2_equipmentName=null;
var user2_faultType=null;
function getResponse(id){
	 var query_json = {
			   "id":id
			   };
	 var Url="/Rcm/Manage/getIds.do";
	 PostJSONQuery(Url,query_json,function(response){
		 var warningList = response;
		 user2_equipmentName = warningList.equipmentname;
		  var statmentsnumber = warningList.statments;
		 var from ={"1":"确认","2":"核实","3":"审定","4":"已处理"};
		 var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
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
		   //判断如果状态来自审定，则显示审定意见栏，否则隐藏掉。
		   if(from[warningList.fromuser]=="审定"){
			   $("#t_opinion1").css("display","block");
			   $("#t_opinion2").css("display","block");
			   $("#t_opinion3").css("display","block");
			  
		   }else{
			 
			   $("#reason").css("width","100%");
			   $("#impact").css("width","100%");
			   $("#strategy").css("width","100%");

		   }
			  
		   
		   $("#query_result_table_body").append("<tr style='height: 26px; border-bottom: 1px solid gray'>" +
			  		"<td id='equipmentId'>"+warningList.equipmentId+"</td>" +
			  		"<td id='equipmentname'>"+warningList.equipmentname+"</td>" +
			  		"<td id='systemName'>"+warningList.systemName+"</td>" +
			  		"<td id='warningType'>"+warningList.warningType+"</td>" +
			  		"<td id='warningTypeLevel'>"+warningtLevel[warningList.warningTypeLevel]+"</td>" +
			  		"<td id='warninginfo'>"+warningList.warninginfo+"</td>" +
			  		"<td id='lineNo'>"+warningList.lineNo+"</td>" +
			  		"<td id='stationName'>"+warningList.stationName+"</td>" +
			  		"<td id='warningDate'>"+warningList.warningDate+"</td>" +
			  		"<td id='stat'>"+stat+"</td>" +
			  		"<td id='native'>系统生成</td>" +
			  		"<td id='statments'>"+from[warningList.fromuser]+"</td>" +
			  		"</tr>");
		   getImgUrl(warningList.id);
		   if(window.confirm("是否需要提供系统推演？")){
			   flag="Yes";
			   user="user2";
			   
		    };
		  		 g_id=warningList.id;
				 g_desc=warningList.warninginfo;
				var  stop_message_json_user2={
					"endMessage":"核实员user2,已终止对"+warningList.equipmentId+" "+warningList.equipmentname+" "+warningList.systemName+" "+warningList.warningType+" "+warningtLevel[warningList.warningTypeLevel]+" "+warningList.warninginfo+" "+warningList.lineNo+" "+warningList.stationName+"的核实",
					"endTime":start_Time,
					"starMessage":"核实员user2,已开始对"+warningList.equipmentId+" "+warningList.equipmentname+" "+warningList.systemName+" "+warningList.warningType+" "+warningtLevel[warningList.warningTypeLevel]+" "+warningList.warninginfo+" "+warningList.lineNo+" "+warningList.stationName+"的核实",
					"starTime":new Date().format("yyyy-MM-dd hh:mm:ss"),
					"warningId":id,
					"fromuser":"2",
					"ok":"goback"
				};
				 
		   stop_message_json_user=stop_message_json_user2;
		  	//getFaultInfo(warningList.warninginfo);
		   //加载好页面数据后自动展开故障树的调用方法
		   user2_equipmentName = warningList.equipmentname;
		   user2_faultType = warningList.warninginfo; 
		    //调用显示故障树和设备树的方法
		   user="user2";
		   showTreeinfo(warningList.equipmentId,warningList.systemName,warningList.stationName,warningList.equipmentname,flag,user);
		 //判断如果状态来自审定 则直接显示故障原因，故障详细，维修策略。
		  	var json_id = {"id":id};
		   if(from[warningList.fromuser]=="审定"){
			   //alert(json_id.id);
			showFautInfo_user2(json_id);
			
		   }
		   
		   //显示提示信息
		   start_Time=new Date().format("yyyy-MM-dd hh:mm:ss");
		   if(from[warningList.fromuser]=="审定"){
			   fromUser="3";
		   }else if(from[warningList.fromuser]=="确认"){
			   fromUser="1";
		   }
		   showUsergoBackInUser2("user2",fromUser,id);
		   
	});
}
//这个方法是当user3审定之后返回到user2的告警详情的时候，故障原因，故障详情，维修策略等内容直接显示，不需要通过点击设备树才显示。

function showFautInfo_user2(json){
	//alert("json="+json.id);
	var show_faultInfo_json = {
				 "id":json.id
				};
	 var from ={"1":"确认","2":"核实","3":"审定","4":"已处理"};
	 
	 var Url="/Rcm/Manage/getFaultInfo_user2ById.do";
		 PostJSONQuery(Url,show_faultInfo_json,  function(response){
	 		var opinions=response.opinion.split("||");
			//alert("response="+JSON.stringify(response));
		   $("#text_reason").val(response.faultCause);
		   $("#text_impact").val(response.influence);
		   $("#text_strategy").val(response.maintenancePolicy);
		   $("#text_opinion1").val(opinions[0]);
		   $("#text_opinion2").val(opinions[1]);
		   $("#text_opinion3").val(opinions[2]);
		
		    
	});
		 
	
}
//user2如果是审定状态则可以调用getFaultInfo_user2ById这个方法进行查询
//user2获得故障信息详情
//定义全局变量存放故障详情
var fault_cause=null;
var fault_desc=null;
var fault_maintence=null;
function getFaultInfo(json){
	
	 var query_json = {	 
		 "id":json.id
			 };
	 
	 var Url="/Rcm/Manage/getFaultinfo.do";
	 PostJSONQuery(Url,query_json,
			    function(response){
		//alert("response="+JSON.stringify(response));
		 var warningList = response;
		 
			  fault_cause=warningList.faultCause;
			  fault_desc=warningList.influence;
			  fault_maintence=warningList.maintenancePolicy;
			  //alert(fault_cause);
	//alert("getFaultInfo is dowm"+warningList.faultCause);
		   $("#text_reason").val(warningList.faultCause);
		   $("#text_impact").val(warningList.influence);
		   $("#text_strategy").val(warningList.maintenancePolicy);
	});
	
}

//user3 获得页面动态信息---------------------------------------------------------
function getDetailInfo3(id){
	
	  //window.confirm("id="+id);
	 var query_json = {
				 "id":id
				};
	
	 var Url="/Rcm/Manage/getFaultInfo_user2ById.do";
	 PostJSONQuery(Url,query_json,
			    function(response){
		 var warningList = response;
		  var statmentsnumber = warningList.statments;
		 var from ={"1":"确认","2":"核实","3":"审定","4":"已处理"};
		 var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
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
		   $("#query_result_table_body").append("<tr style='height: 26px; border-bottom: 1px solid gray'>" +
			  		"<td id='user3_equipmentid'>"+warningList.equipmentid+"</td>" +
		  		    "<td id='user3_equipmentname'>"+warningList.equipmentname+"</td>" +
			  		"<td id='user3_systemname'>"+warningList.systemname+"</td>" +
			  		"<td id='user3_warningType'>"+warningList.warningType+"</td>" +
			  		"<td id='user3_warningTypeLevel'>"+warningtLevel[warningList.warningTypeLevel]+"</td>" +
			  		"<td id='user3_fultDescription'>"+warningList.fultDescription+"</td>" +
			  		"<td id='user3_lineNo'>"+warningList.lineNo+"</td>" +
			  		"<td id='user3_stationName'>"+warningList.stationName+"</td>" +
			  		"<td id='user3_warningDate'>"+warningList.warningDate+"</td>" +
			  		"<td id='user3_stat'>"+stat+"</td>" +
			  		"<td>系统生成</td>" +
			  		"<td id='from[warningList.statments]'>"+from[warningList.fromuser]+"</td>" +
			  		"</tr>");
				   $("#text_reason").val(warningList.faultCause);
				   $("#text_impact").val(warningList.influence);
				   $("#text_strategy").val(warningList.maintenancePolicy);
				   //加载好页面数据后自动展开故障树的调用方法
				   user2_equipmentName = warningList.equipmentname;
		  		   var user3_faultType = warningList.fultDescription; 
		  		   flag="No";
		  		   user="user3";
		  		   showTreeinfo(warningList.equipmentid,warningList.systemname,warningList.stationName,warningList.equipmentname,flag,user);
		  		   start_Time=new Date().format("yyyy-MM-dd hh:mm:ss");
		  		   //显示在途视图
		  		   getImgUrl(warningList.id);
		  		 // stop_message_json_user=stop_message_json_user3;
		  		  //获取审定意见
		  		  check_opinion_json={
		  			  "opinion1": $("#text_opinion1").val(),
		  			  "opinion2": $("#text_opinion2").val(),
		  			  "opinion3": $("#text_opinion3").val()
		  		  };
	});
}
//user4获得页面动态信息
var user4_fultDescription=null;
function getDetailInfo4(id){
	 var query_json = {
		            "id":id
		         };
	 
	 var Url="/Rcm/Manage/getFaultInfo_user2ById.do";
	 PostJSONQuery(Url,query_json,
			    function(response){
		 var warningList = response;
	//alert(JSON.stringify(warningList));
		/* window.confirm("response="+JSON.stringify(warningList))*/
		  var statmentsnumber = warningList.statments;
		 var from ={"1":"确认","2":"核实","3":"审定","4":"已处理"};
		 var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
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
			 var system_num={"信号系统":1,"综合监控系统":2};
			 var subsystem_num={"轨旁设备":1,"BAS系统":2,"电力SCAD系统":3};
		       $("#asset_lineNo").val(warningList.lineNo);
			   $("#asset_equipmentname").val(warningList.equipmentname);
		       $("#malfunction_level").val(user4_warninglevel);//告警等级
			   $("#malfunction_desc").val(warningList.stationName+"  "+warningList.fultDescription);//故障描述
			   $("#malfunction_cause").val(warningList.faultCause);//故障原因
			   $("#text_impact").val(warningList.influence);
			   $("#strategy").val(warningList.maintenancePolicy);
			   $("#asset_stationname").val(warningList.stationName);
			   $("#system_main").val(warningList.systemname);
			   $("#component_code").val(warningList.maintenanceId);
			   $("#health_index").val(user4_warninglevel);
			   $("#ticket_id").val(warningList.suggest_Number);
		
			   $("#system_main option:eq("+system_num[""+warningList.systemname]+")").attr("selected","selected");
			   //显示在途视图
	  		   getImgUrl(warningList.id);
			   user4_fultDescription = warningList.fultDescription;
			//加载好页面数据后自动展开故障树的调用方法
			 user2_equipmentName = warningList.equipmentname;
		     user2_faultType = warningList.warninginfo; 
		     user="user4";
		   	 showTreeinfo(warningList.equipmentid,warningList.systemname,warningList.stationName,warningList.equipmentname,flag,user);
	});
}

function getNotifyCode(){
	var notifyCode = "ODD";
	for(var i=0;i<=6;i++){
		notifyCode +=Math.floor(Math.random(10)*10);
	}
	return notifyCode;
}
//user2消息提示栏中显示user1、3的提示 如果来自确认则显示user1  如果来自审定则显示user3的提示
function showUsergoBackInUser2(userName,fromUser,id){
	 var operative_index_query_json = {
				   "userName":userName,
				   	"fromuser":fromUser,
				   	"warningId":id
				  };
	 var Url="/Rcm/Manage/getMessageList.do";
			  PostJSONQuery(Url,operative_index_query_json,
			    function(response){
				  var list=response.messageList;
				  for(var i=0;i<list.length;i++){
					  $("span table #query_result_table_body").append(
							  ""+
							  "<tr>"+
							  "<td>"+(i+1)+"</td>"+
							  "<td>"+list[i].message+"</td>"+
							  "<td>"+list[i].infodate+"</td>"+
							  "</tr>"
					  );
				  }
			  });
}

//user2的终止 操作

function  OnAbortProcess(id,statment,userName,opinions){
	//alert("id="+id+","+"statment="+statment+","+"userName="+userName);
	if(confirm("确认要终止流程吗？")){
		var  stopFlow_json = {
				"id":id,
			    "statments":statment,
			    "username":userName
		};
		
		var Url="/Rcm/Manage/stopFlow.do";
		PostJSONQuery(Url,stopFlow_json,
			    function(response){
		if(response.info=="sucess"){
			window.confirm("终止流程成功");
			insertMessage(stop_message_json_user);
			if(userName=="user2"){
				window.setTimeout(function(){
					location="_check_list.jsp";
				},500);
			}
		}
			 
	});
		
	};
}


//user2的核实操作
function OnCheckProcess(id,statment){
	if(confirm("确认要提交吗？")){
		//解析传来的数据
		/*var warningList_string_array = new Array(13);
		warningList_string_array = warningList_array.split("||");*/
		 var from ={"1":"确认","2":"核实","3":"审定","4":"已处理"};
		 var warningLevel = {"一级":"1","二级":"2","三级":"3","四级":"4"};
		 var  confirmorcancel_json ={
				"equipmentid":$("#equipmentId").html(),
				"equipmentname":$("#equipmentname").html(),
				"systemname":$("#systemName").html(),
				"warningType":$("#warningType").html(),
				"warningTypeLevel":warningLevel[$("#warningTypeLevel").html()],
				"lineNo":$("#lineNo").html(),
				"stationName":$("#stationName").html(),
				"warningDate":$("#warningDate").html(),
				"statments":$("#statments").html(),
				"id":g_id,
				"fultDescription":g_desc,
				"faultCause":$("#text_reason").val(),
				"influence": $("#text_impact").val(),
				"maintenancePolicy": $("#text_strategy").val()
			};
		 
		var Url="/Rcm/Manage/insertFaultInfo_user2.do";
		PostJSONQuery(Url,confirmorcancel_json,
			    function(response){
			//alert(JSON.stringify(response));
			if($("#text_reason").val()!=""){
				 if(response.info=="sucess"){
				//window.confirm("核实成功");	
					 window.setInterval(function(){
						 
						 $("#success_block").fadeIn("slow");
						 
					 }, 100);
		   		stop_message="核实员user2,已终止了对"+$("#equipmentId").html()+" "+$("#equipmentname").html()+" "+$("#systemName").html()+" "+$("#warningType").html()+" "+warningLevel[$("#warningTypeLevel").html()]+" "+g_desc+" "+$("#lineNo").html()+" "+$("#stationName").html()+" "+"的核实";
				var message_json = {
					"endMessage":"核实员user2,已结束对"+$("#equipmentId").html()+" "+$("#equipmentname").html()+" "+$("#systemName").html()+" "+$("#warningType").html()+" "+warningLevel[$("#warningTypeLevel").html()]+" "+g_desc+" "+$("#lineNo").html()+" "+$("#stationName").html()+" "+"的核实",
					"endTime":new Date().format("yyyy-MM-dd hh:mm:ss"),
					"starMessage":"核实员user2,已开始对"+$("#equipmentId").html()+" "+$("#equipmentname").html()+" "+$("#systemName").html()+" "+$("#warningType").html()+" "+warningLevel[$("#warningTypeLevel").html()]+" "+g_desc+" "+$("#lineNo").html()+" "+$("#stationName").html()+" "+"的核实",
					"starTime":start_Time,
					"warningId":id,
					"fromuser":"2",
					"ok":"goto"
				};
				//将操作记录插入到数据库中
				insertMessage(message_json);
				window.setTimeout(function(){
					location="_check_list.jsp";
				},2000);
			}else{
				window.confirm("核实失败");
			}
			}else{
				window.confirm("请填写故障原因，故障影响，维修策略等内容");
			}
		});
	}
}
//user2 user3 的消息提示插入到数据库中---------------------------------------------//
function  insertMessage(message_json){
	var  insert_message_json ={
       "endMessage":  message_json.endMessage, 
          "endTime":  new Date().format("yyyy-MM-dd hh:mm:ss"), 
         "fromuser":  message_json.fromuser, 
      "starMessage":  message_json.starMessage, 
         "starTime":  message_json.starTime, 
        "warningId":  message_json.warningId, 
               "ok":  message_json.ok
};
	var Url="/Rcm/Manage/message.do";
	PostJSONQuery(Url,insert_message_json,
			    function(response){
		//alert("response="+JSON.stringify(response));
		
		});
			    
}
//user3的审定及终止操作
function OnApproveByUser3(id,ok){
	if(confirm("确认要提交吗？")){
	
		var  approve_json ={
								   "ok":ok,
							       "id":id,
							       "opinion":$("#text_opinion1").val()+"||"+$("#text_opinion2").val()+"||"+$("#text_opinion3").val()+""
							    };
		
			 var warningLevel = {"一级":"1","二级":"2","三级":"3","四级":"4"};
			 var warningtLevel_user3={"1":"一级","2":"二级","3":"三级","4":"四级"};
			 
			 var Url="/Rcm/Manage/updateFaultInfo_user2ById.do";
		PostJSONQuery(Url,approve_json,
			    function(response){
				//alert("charge"+$("#text_opinion1").val()!=""&&$("#text_opinion2").val()!=""&&$("#text_opinion3").val()!="");
				if($("#text_opinion1").val()!=""&&$("#text_opinion2").val()!=""&&$("#text_opinion3").val()!=""){
				var check_message="";
				if(response.info=="sucess"){
					if(ok=="goto"){
						check_message="核实员user3,已结束对"+$("#user3_equipmentid").html()+" "+$("#user3_equipmentname").html()+" "+$("#user3_systemname").html()+" "+$("#user3_warningType").html()+$("#user3_warningTypeLevel").html()+" "+$("#user3_fultDescription").html()+" "+$("#user3_lineNo").html()+" "+$("#user3_stationName").html()+" "+"的审定";
						//window.confirm("审定成功");	
						 window.setInterval(function(){
							 
							 $("#success_block_shending").fadeIn("slow");
							 
						 }, 100);
					}
					if(ok=="goback"){
						window.confirm("终止流程成功");	
						check_message="核实员user3,已终止对"+$("#user3_equipmentid").html()+" "+$("#user3_equipmentname").html()+" "+$("#user3_systemname").html()+" "+$("#user3_warningType").html()+$("#user3_warningTypeLevel").html()+" "+$("#user3_fultDescription").html()+" "+$("#user3_lineNo").html()+" "+$("#user3_stationName").html()+" "+"的审定";

					}
				$("table #message_step2_tr2_info1").html("2");
				$("table #message_step2_tr2_info2").html("核实员user3,已结束对"+$("#user3_equipmentid").html()+" "+$("#user3_equipmentname").html()+" "+$("#user3_systemname").html()+" "+$("#user3_warningType").html()+$("#user3_warningTypeLevel").html()+" "+$("#user3_fultDescription").html()+" "+$("#user3_lineNo").html()+" "+$("#user3_stationName").html()+" "+"的审定");
		   		$("table #message_step2_tr2_info3").html(new Date().format("yyyy-MM-dd hh:mm:ss"));
		   		
		   		var message_json_check = {
					"endMessage":check_message,
					"endTime":new Date().format("yyyy-MM-dd hh:mm:ss"),
					"starMessage":"核实员user3,已开始对"+$("#user3_equipmentid").html()+" "+$("#user3_equipmentname").html()+" "+$("#user3_systemname").html()+" "+$("#user3_warningType").html()+$("#user3_warningTypeLevel").html()+" "+$("#user3_fultDescription").html()+" "+$("#user3_lineNo").html()+" "+$("#user3_stationName").html()+" "+"的审定",
					"starTime":start_Time,
					"fromuser": "3",
					"warningId": id, 
					"ok":ok
				};
		   		insertMessage(message_json_check);
		   		window.setTimeout(function(){
					location="_audit_list.jsp";
				},2000);
				}else{
					window.confirm("审定失败");
				}
				}else{
				window.confirm("请填写审定意见");
			}
		});
	}
}

//user4发通知单请求
function   sendMessage(id,equipmentId){
	
	var warningLevel = {"一级":"1","二级":"2","三级":"3","四级":"4"};
	var  messageForm_json = {
				"equipmentId":equipmentId,
				"equipmentName":$("#asset_equipmentname").val(),
				"faultCause":$("#malfunction_cause").val(),
				"fultDescription":user4_fultDescription,
				"id":id,
				"influence": $("#text_impact").val(),
				"lineNo":$("#asset_lineNo").val(),
				"mainTenancePolicy": $("#strategy").val(),
				"operator":$("#operator").val(),
				"stationName":$("#asset_stationname").val(),
				"subSystemName":$("#subsystem").val(),
				"systemName":$("#system_main").val(),
				"starTime":$("#start_time").val(),
				"warningTypeLevel":$("#malfunction_level").val(),
				"healthLevel":$("#health_index").val(),
				"jobNumber":$("#ticket_id").val()

	};
				record_array[0] = $("#system_main").val();
				record_array[1] = $("#asset_equipmentname").val();
				record_array[2] = user4_fultDescription;
				record_array[3] = $("#ticket_id").val();
				record_array[4] = $("#sendto").val();
				if(($("#ticket_id").val()!="")&&($("#system_main").val()!="")&&($("#subsystem").val()!="")){
					var Url="/Rcm/Manage/insertinform_user4.do";
					PostJSONQuery(Url,messageForm_json,
							function(response){
						if(response.info=="sucess"){
							
							//window.confirm("发送成功");
							 window.setInterval(function(){
								 
								 $("#success_block_send").fadeIn("slow");
								 
							 }, 100);
							 
							showMessage();
							
							
						}else{
							window.confirm("发送失败");
							return false;
						}
						
					});
					
				}else{
					if($("#ticket_id").val()==""){
						window.confirm("请填写工单号");
					}else if($("#system").val()==""){
						window.confirm("请选择系统名称");
					}else if($("#subsystem").val()==""){
						window.confirm("请选择子系统名称");
					}
				}
				
	
}
//通知单显示信息--------------------------------------------------
//定义一个数组临时存放step4发送通知单的记录用于step5页面数据的显示。
var  record_array = new Array(5);

function  showMessage(){
	window.setInterval(function(){
	location=encodeURI("show4.jsp?record="+record_array+"") ;
		
	},2000);
}
//为通知单从数据库查询记录添加到页面    动态追加记录 并使用查询条件进行查询

var recordCount=1;//global
	function OnNotifyPageQuery(query_notify){
		
    var notify_query_json = {
						"page":{
							"pageCount":"10",
							"pageNo":"1"
						},
						"query":{
							"endTime":"2099-1-1",
							"equipmentName":"",
							"lineNO":"",
							"starTime":"1990-1-1",
							"stationName":"",
							"systemName":"",
							"warningTypeLevel":"",
								"warningType":""
						}
  };
 
  if (query_notify)
  {
    if (query_notify.pageNo) {
    	
      notify_query_json.page.pageNo = query_notify.pageNo;
        }
   if (query_notify.endDate) {
      notify_query_json.query.endTime = query_notify.endTime;
    }
     if (query_notify.startDate) {
      notify_query_json.query.starTime = query_notify.startTime;
    }
   if (query_notify.equipmentname) {
    	
        notify_query_json.query.equipmentName = query_notify.equipmentname;
      }
    if (query_notify.lineNo) {
        notify_query_json.query.lineNO = query_notify.lineNo;
      }
    
    if (query_notify.stationname) {
    	
        notify_query_json.query.stationName = query_notify.stationname;
      }
    
    if (query_notify.systemname) {
       notify_query_json.query.systemName = query_notify.systemname;
      }
    
    if (query_notify.warningtypeLevel) {
        notify_query_json.query.warningTypeLevel = query_notify.warningtypeLevel;
      }
    if (query_notify.warningType) {
        notify_query_json.query.warningType = query_notify.warningType;
      }
  }
  
  var Url="/Rcm/Manage/getInformList.do";
  PostJSONQuery(Url,notify_query_json,function(response){
	  $('#notify_page_totalpage')[0].innerHTML = response["totalPage"];
	  $('#notify_page_totalCount')[0].innerHTML = response["totalCount"];
	  var pageNo = notify_query_json.page.pageNo; 
	  $('#notify_page_nowpage')[0].innerHTML = pageNo;
	   
	  var informList=response.informList;
	 $('#query_result_table_body tr').remove();
	  for(var i=0;i<informList.length;i++){
		 
		  var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
		  var page=pageNo;
		  var leve=informList[i]["warningTypeLevel"];
		  var checkBox=null;
		  var onmouse="<tr style='height:26px'>";
		
		  $('#query_result_table_body').append("" +
				  onmouse +
		  		"<td scope='col' >"+(((page-1)*10)+1+i)+"</td>" +
		  		"<td scope='col' >"+informList[i]["jobNumber"]+"</td>" +
		  		"<td scope='col' >"+informList[i]["equipmentId"]+"</td>" +
		  		"<td scope='col' >"+informList[i]["equipmentName"]+"</td>" +
		  		"<td scope='col' >"+informList[i]["systemName"]+"</td>" +
		  		"<td scope='col' >"+
		  		"<span   onmouseover='showDetail(true,this);' onMouseOut='showDetail(false,this);'>"+informList[i]["equipmentDescription"].substring(0,3)+"..."+"</span>"+
		  		"<div class='detail_info'>"+
           			"<textarea style='height: 150px; width:250px;'rows='6' cols='1'>"+informList[i]["equipmentDescription"]+
           			"</textarea>"+
        		"</div>"+
		  		"</td>" +
		  		"<td scope='col' >"+informList[i]["lineNo"]+"号线</td>" +
		  		"<td scope='col' >"+informList[i]["stationName"]+"</td>" +
		  		"<td scope='col' >"+informList[i]["warningType"]+"</td>" +
		  		"<td scope='col' >"+warningtLevel[informList[i]["warningTypeLevel"]]+"</td>" +
		  		"<td scope='col' >"+informList[i]["healthLevel"]+"</td>" +
		  		"<td scope='col' >"+informList[i]["starTime"].substring(0,10)+"</td>" +
		  		//"<td scope='col' >"+informList[i]["fultDescription"]+"</td>" +
		  		"<td scope='col' >" +
		  		"<span   onmouseover='showDetail(true,this);' onMouseOut='showDetail(false,this);'>"+informList[i]["fultDescription"].substring(0,3)+"..."+"</span>"+
		  		"<div class='detail_info'>"+
           			"<textarea style='height: 150px; width:250px;'rows='6' cols='1'>"+informList[i]["fultDescription"]+"</textarea>"+
        		"</div>"+
		  		"</td>" +
		  		"<td scope='col' >" +
		  		"<span   onmouseover='showDetail(true,this);' onMouseOut='showDetail(false,this);'>"+informList[i]["faultCause"].substring(0,3)+"..."+"</span>"+
		  		"<div class='detail_info'>"+
           			"<textarea style='height: 150px; width:250px;'rows='6' cols='1'>"+informList[i]["faultCause"]+
           			"</textarea>"+
        		"</div>"+
		  		"</td>" +
		  		"<td scope='col' >" +
		  		"<span   onmouseover='showDetail(true,this);' onMouseOut='showDetail(false,this);'>"+informList[i]["mainTenancePolicy"].substring(0,3)+"..."+"</span>"+
		  		"<div class='detail_info'>"+
           			"<textarea style='height: 150px; width:250px;'rows='6' cols='1'>"+informList[i]["mainTenancePolicy"]+"</textarea>"+
        		"</div>"+
		  		"</td>" +
		  		//"<td scope='col' >"+informList[i]["mainTenancePolicy"]+"</td>" +
		  		"<td scope='col' >"+informList[i]["operator"]+"</td>" +
		  		"</tr>");
	  } 
    });  
}
	/**查询按钮*/
function selectNotifyData(val8,type){	
	var val1 = $('#condition1').val();
	var val2 = $('#condition2').val();
	var val3 = $('#condition3').val();
	var val4 = $('#condition4').val();
	var val5 = $('#condition5').val();
	var val6 = $('#notify_page_start_time').val();
	var val7 = $('#notify_page_end_time').val();
	

	var query_notify={
						"stationname":val1,
						"lineNo":val2,
						"systemname":val3,
						"equipmentname":val4,
						"warningtypeLevel":val5,
						"warningType":type,
						"startTime":val6,
						"endTime":val7,
						"pageNo":val8
						};
	
	OnNotifyPageQuery(query_notify);
}

/**上一页*/
function tobackpage(type){
	var nowpage= $('#notify_page_nowpage')[0].innerHTML;
	var totalpage= $('#notify_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		selectNotifyData(backPageNo,type);
	}
}
/**下一页*/
function tonextpage(type){
	var nowpage= $('#notify_page_nowpage')[0].innerHTML;
	var totalpage= $('#notify_page_totalpage')[0].innerHTML;
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		selectNotifyData(nextPageNo,type);
	}
}

/**通知单维保反馈记录--------------------------------------------------------------------------*/
	function OnNotifyMaintencePageQuery(query_notify){
		
    var notify_query_json = {
        "page": {
            "pageCount": "10",
            "pageNo": "1"
        },
        "query": {
            "component": "",
            "endTime": "",
            "lineNO": "",
            "starTime": "",
            "stationName": "",
            "systemName": "",
            "warningType":""
        }
};
 
  if (query_notify)
  {
    if (query_notify.pageNo) {
    	
      notify_query_json.page.pageNo = query_notify.pageNo;
        }
   if (query_notify.endTime) {
      notify_query_json.query.endTime = query_notify.endTime;
    }
     if (query_notify.starTime) {
      notify_query_json.query.starTime = query_notify.starTime;
    }
  
    if (query_notify.lineNo) {
        notify_query_json.query.lineNO = query_notify.lineNo;
      }
    
    if (query_notify.stationName) {
    	
        notify_query_json.query.stationName = query_notify.stationName;
      }
    
    if (query_notify.systemName) {
       notify_query_json.query.systemName = query_notify.systemName;
      }
    
    if (query_notify.component) {
        notify_query_json.query.component = query_notify.component;
      }
    //warningType
    if (query_notify.warningType) {
        notify_query_json.query.warningType = query_notify.warningType;
      }
  }
  var Url="/Rcm/Manage/getMaintain_couple_back.do";
  PostJSONQuery(Url,notify_query_json,function(response){
	  
	  $('#maintence_page_totalpage')[0].innerHTML = response["totalPage"];
	  $('#maintence_page_totalCount')[0].innerHTML = response["totalCount"];
	  var pageNo = notify_query_json.page.pageNo; 
	  $('#maintence_page_nowpage')[0].innerHTML = pageNo;
	   
	  var informList=response.backInfoList;
	 // alert(JSON.stringify(informList));
	  var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	 $('#query_result_table_body tr').remove();
	   var onmouse="<tr style='height:26px'>";
	  for(var i=0;i<informList.length;i++){
		
		  var page=pageNo;
		
		  $("#query_result_table_body").append(""+
				"<tr style='height:26px'>" +
		  		"<td >"+(((page-1)*10)+1+i)+"</td>" +
		  		"<td >"+informList[i].componentId+"</td>" +
		  		"<td >"+informList[i].componentName+"</td>" +
		  		"<td >"+informList[i].systemName+"</td>" +
		  		"<td >"+informList[i].equipmentDescription+"</td>" +
		  		"<td >"+informList[i].lineNo+"号线</td>" +
		  		"<td >"+informList[i].stationName+"</td>" +
		  		"<td >"+informList[i].repairsDate.substring(0,11)+"</td>" +
		  		"<td >"+informList[i].changeEquipment+"</td>" +
		  		"<td >"+informList[i].maintainInfo+"</td>" +
		  		"<td >"+informList[i].maintainResult+"</td>" +
		  		"<td >"+informList[i].maintainPerson+"</td>" +
		  		"<td >"+informList[i].checkPerson+"</td>" +
		  		"<td >"+informList[i].remark+"</td>" +
		  		"<td >"+"<a href='_detailsoffeedback_ticket.jsp?id="+informList[i].id+"&equipmentid="+informList[i].componentId+" '>详情</a>"+"</td>" +
		  		"</tr>"
				
		  		);
		  } 
    });  
}
	/**查询按钮*/
function selectMaintenceData(val7,type){	
	var val1 = $('#condition1').val();//系统
	var val2 = $('#condition2').val();//线路
	var val3 = $('#condition3').val();//车站
	var val4 = $('#condition4').val();//资产名称
	var val5 = $('#maintence_page_start_time').val();
	var val6 = $('#maintence_page_end_time').val();
	//alert(val1+","+val2+","+val3+","+val4+","+val5);

	var query_notify={
						"systemName":val1,
						"lineNO":val2,
						"stationName":val3,
						"component":val4,
						"starTime":val5,
						"endTime":val6,
						"pageNo":val7,
						"warningType":type
						};
	
	OnNotifyMaintencePageQuery(query_notify);
}

/**上一页*/
function maintence_backpage(){
	var nowpage= $('#maintence_page_nowpage')[0].innerHTML;
	var totalpage= $('#maintence_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		selectMaintenceData(backPageNo);
	}
}
/**下一页*/
function maintence_nextpage(){
	var nowpage= $('#maintence_page_nowpage')[0].innerHTML;
	var totalpage= $('#maintence_page_totalpage')[0].innerHTML;
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		selectMaintenceData(nextPageNo);
	}
}







//----------------------------------------------------------------------------------------------
//动态展开设备树和故障
function showTreeinfo(id,sys,st,equipmentname,flag,user){
	//alert("资产编号："+id+"所属系统："+sys+"车站："+st+"资产名称"+equipmentname+"flag:"+flag+"user:"+user);
	/**显示数据之后调用showTreeinfo()显示设备树和故障树*/
	var query_criteriaFault = {
		    "equipmentName":equipmentname,
		    "faulttype":"",
		    "equipmentId":id,
		    "flag":flag,
		    "user":user
		    	};
	openFaultTree(query_criteriaFault);
	
	var query_criteriaEquipment={
		    "stationName":st,
		    "systemName":sys,
		    "subComponentId":id
		    	};
	openEquipmentTree(query_criteriaEquipment);
	
}

//根据传入的值自动展开设备树和故障树
function openFaultTree(query_criteria){
	
	//alert(query_criteria.flag+","+query_criteria.user);
	  var operative_index_query_json = {
	    	"equipmentName":query_criteria.equipmentName,
	    	"faulttype":query_criteria.faulttype,
	    	"equipmentId":query_criteria.equipmentId,
	    	"flag":query_criteria.flag,
	    	"user":query_criteria.user
	    	};
	  
	  var Url="/Rcm/Manage/getstationFaultTree.do";
	  PostJSONQuery(Url,operative_index_query_json,
	    function(response){
		  //alert(JSON.stringify(response));
		  var setting = {
					view: {
						fontCss: getFont,
						nameIsHTML: true
						}
					};
		  var  systems=response.name;
		 
		  $.fn.zTree.init($("#mal_faultTree"), setting, systems);
		  
	    });
}

/***Ztree插件的样式*/
function getFont(treeId, node) {
	return node.font ? node.font : {};
}

function openEquipmentTree(query_criteria){
	  var operative_index_query_json = {
	    	"stationName":query_criteria.stationName,
	    	"systemName":query_criteria.systemName,
	    	"subComponentId":query_criteria.subComponentId,
	    	"flag":flag,
	    	"user":user
	    	};
	  
	  var Url="/Rcm/Manage/showOneStationsubComponent.do";
	  PostJSONQuery(Url,operative_index_query_json,
	    function(response){
				 var setting = {
				view: {
					fontCss: getFont,
					nameIsHTML: true
					}
				};
		  var  systems=response.name;
		  $.fn.zTree.init($("#mal_equipmentTree"), setting, systems);
	    });
	
	 
	}
//根据故障树的条件显示对应的故障原因，故障详细，维护策略。
/**用于点击故障树和设备树的查询*/

var faultInfoID=0;
function showFaultInfo(id){
	query_criteria={
			"id":id
	};
	faultInfoID=id;
	getFaultInfo(query_criteria);
	
}
//设备树，故障树查询方法
function getEquipmentInfo(query_criteria){
		
		  var operative_index_query_json = {
		    	"id":query_criteria.id
		    	};
		  var Url="/Rcm/Manage/searchEquipmentInfo.do";
		  PostJSONQuery(Url,operative_index_query_json,
		    function(response){
			  
		    });
		
		}

//获得当前的格式化后的日期 yyyy-mm-dd-----------------------------------------------------------

/**
* 时间对象的格式化;
*/
Date.prototype.format = function(format){
 /*
  * eg:format="YYYY-MM-dd hh:mm:ss";
  */
 var o = {
  "M+" :  this.getMonth()+1,  //month
  "d+" :  this.getDate(),     //day
  "h+" :  this.getHours(),    //hour
  "m+" :  this.getMinutes(),  //minute
  "s+" :  this.getSeconds(), //second
  "q+" :  Math.floor((this.getMonth()+3)/3),  //quarter
  "S"  :  this.getMilliseconds() //millisecond
   };
  
   if(/(y+)/.test(format)) {
    format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
   }
 
   for(var k in o) {
    if(new RegExp("("+ k +")").test(format)) {
      format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    }
   }
 return format;
};
//获的在途视图
function   getImgUrl(id){

	var faultImg=["../../assets/images/Snap1.jpg","../../assets/images/Snap2.jpg","../../assets/images/Snap3.jpg","../../assets/images/Snap4.jpg"];
	var inId=parseInt(id);
	var randomNum=(inId % 4);
	$("#onLineImg")[0].src=faultImg[randomNum];
	
	
}

