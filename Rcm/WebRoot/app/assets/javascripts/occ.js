//globle 全局变量的定义区域

var  g_id = 0;
var g_desc=null;
var stop_message_json_user=null;//终止流程信息
var check_opinion_json=null;
var start_Time=null;
var user3_stop=false;
var user="";//
var flag="No";

/*******************显示待处理的告警******************************/
function OnEmergePageQuery_OCCmain(pageNo){
  var alert_query_json = {
	  		"page":{"pageCount":"3",
				    "pageNo":pageNo},
	        "statments":"1"};
  
  var Url="/Rcm/Manage/getWaringListBystatments.do";
     PostJSONQuery(Url,alert_query_json,function(response){
	 
	  $('#occmain_page_nowpage')[0].innerHTML = pageNo;
	  $('#occmain_page_totalpage')[0].innerHTML = response["totalPage"];
	   $('#alert_count')[0].innerHTML = response["totalCount"];
	  var warningList=response["warningList"];
	  
	//查询时先将之前的删掉再冲数据库查询后重新追加记录
	$("#query_result_table_body tr").remove();
	  for(var i=0;i<warningList.length;i++){
		  var warningtLevel={"1":"一级告警","2":"二级告警","3":"三级告警","4":"四级告警"};
		  var page=pageNo;
		
		var leve=warningList[i]["warningTypeLevel"];
		var checkBox=null;
		var onmouse=null;
		if(warningList[i]["warningTypeLevel"]=="1"){
			onmouse="<tr style='height: 26px; border-bottom: 1px solid gray;color: red' >";
		}else if(warningList[i]["warningTypeLevel"]=="2"){
			onmouse="<tr style='height: 26px; border-bottom: 1px solid gray;color: #f6b52c' >";
		}else if(warningList[i]["warningTypeLevel"]=="3"){
			onmouse="<tr style='height: 26px; border-bottom: 1px solid gray;color: blue' >";
		}else if(warningList[i]["warningTypeLevel"]=="4"){
			onmouse="<tr style='height: 26px; border-bottom: 1px solid gray;color: green' >";
		}
		
			//将未处理的告警信息显示出来
			var number=(((page-1)*10)+1+i);
			 $('#query_result_table_body').append("" +
				  onmouse +
		  		"<td  >"+warningList[i]["equipmentId"]+"</td>"+
		  		"<td  >"+warningList[i]["equipmentname"]+"</td>" +
		  		"<td   >"+warningList[i]["systemName"]+"</td>" +
		  		"<td  >"+warningList[i]["equipmentname"]+"</td>" +
		  		"<td   >"+warningtLevel[warningList[i]["warningTypeLevel"]]+"</td>" +
		  		"<td  >"+warningList[i]["warningType"]+"</td>" +
		  		"<td   >"+warningList[i]["warninginfo"]+"</td>" +
		  		"<td   >"+warningList[i]["warningDate"]+"</td>" +
		  		"<td  ><a href='show1.jsp?id="+warningList[i]["id"]+"&stat=1&equipmentId="+warningList[i]["equipmentId"]+"&user=user2'  >确认</a></td>"+
   				"</tr>"
		  		);
		
		
	  } 
	 
    });  
}

/***上一页和下一页***/
function backpage(){
	var nowpage= $('#occmain_page_nowpage')[0].innerHTML;
	var totalpage= $('#occmain_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		OnEmergePageQuery_OCCmain(backPageNo);
	}
}
function nextpage(){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#occmain_page_nowpage')[0].innerHTML;
	var totalpage= $('#occmain_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		//alert("nextpage="+nextPageNo);
		OnEmergePageQuery_OCCmain(nextPageNo);
	}
}


/*************************任务分配*************************************/





var user2_equipmentName=null;
var user2_faultType=null;
var user2_warningId=null;
function getResponse(id){
	var query_json = {
			   "id":id
			   };
	 var Url="/Rcm/Manage/getIds.do";
	 PostJSONQuery(Url,query_json,function(response){
		 var warningList = response;
		 user2_equipmentName = warningList.equipmentname;
		  var statmentsnumber = warningList.statments;
		
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
			  		
			  		"<td id='statments'>"+warningList.fromuser+"</td>" +
			  		"</tr>");
		   user2_warningId=warningList.warningId;
		   getImgUrl(warningList.equipmentId);
		   if(window.confirm("是否需要提供系统推演？")){
			   flag="Yes";
			   user="user2";
			   
		    };
		  		 g_id=warningList.id;
				 g_desc=warningList.warninginfo;
				
				 
		
		  	//getFaultInfo(warningList.warninginfo);
		   //加载好页面数据后自动展开故障树的调用方法
		   user2_equipmentName = warningList.equipmentname;
		   user2_faultType = warningList.warninginfo; 
		    //调用显示故障树和设备树的方法
		   user="user2";
		   showTreeinfo(warningList.equipmentId,warningList.systemName,warningList.stationName,warningList.equipmentname,flag,user);
		 //判断如果状态来自审定 则直接显示故障原因，故障详细，维修策略。
		  	var json_id = {"id":id};
		   
		  
		   
	});
}

//user2如果是审定状态则可以调用getFaultInfo_user2ById这个方法进行查询
//user2获得故障信息详情
//定义全局变量存放故障详情
var fault_cause=null;
var fault_desc=null;
var fault_maintence=null;



//通知单显示信息--------------------------------------------------
//定义一个数组临时存放step4发送通知单的记录用于step5页面数据的显示。
var  record_array = new Array(5);

//----------------------------------------------------------------------------------------------
//动态展开设备树和故障树
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
		 var setting_FaultTree = {
					view: {
						fontCss: getFont,
						nameIsHTML: true
						}
					};
		  /*var setting_FaultTree = {
					data: {
						simpleData: {
							enable: true
						}
					},
					check: {
						enable: true,
						chkboxType: {"Y":"", "N":""}
					},
					view: {
						fontCss: getFont,
						nameIsHTML: true
					},
					callback: {
						beforeClick: beforeClick,
						onCheck: onCheck
					}
					

				};*/
		  var  systems=response.name;
		 
		  $.fn.zTree.init($("#mal_faultTree"), setting_FaultTree, systems);
		  //TODO 业务测试使用
		  var treeObj = $.fn.zTree.getZTreeObj("mal_faultTree");
		  if (flag=="No"){
			  treeObj.expandAll(false);
		  }
		  
		  
	    });
}

/***Ztree插件的样式*/
function getFont(treeId, node) {
	return node.font ? node.font : {};
}
function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("mal_faultTree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	//TODO //多选
	var check=treeNode.checked;
	if(check){
		
		
		//alert(treeNode.name+'    '+treeNode.value);
		//showFaultInfo(treeNode.value);
	}
	
	
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
				 var setting_equipment = {
					view: {
						fontCss: getFont,
						nameIsHTML: true
						}
				};
		  var  systems=response.name;
		  $.fn.zTree.init($("#mal_equipmentTree"), setting_equipment, systems);
		  //TODO 业务测试使用
		  var treeObj = $.fn.zTree.getZTreeObj("mal_equipmentTree");
          var equipment_to_sub=$("#equipmentId")[0].innerHTML;
          var attr=equipment_to_sub.split(".");
      	  var strof=attr[2]+"."+attr[3];
      	 if (flag=="No"){
			  treeObj.expandAll(false);
		  }else{
			  if(strof=="08.01"){//转辙机
	      		  var node = treeObj.getNodeByParam("name", "P01A(转辙机)", null);
	      		  node.name="P01A(转辙机) 75%";
	      		  treeObj.updateNode(node);
	      		  
	      		 var node2 = treeObj.getNodeByParam("name", "低压开关柜", null);
	      		 treeObj.expandNode(node2, true, true, true);
	      		 
	      		 var node3 = treeObj.getNodeByParam("name", "40A开关断路器", null);
	    		  node3.name="40A开关断路器  25%";
	    		  node3.font={'color':'red'};
	    		  treeObj.updateNode(node3);
	      	  }else{
	      		  
	      	  }
		  }
		
      	  
      	  
      	 
      	  
      	 
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
function   getImgUrl(equipmentId){

	var faultImg=[
	              "../../assets/images/fengji.jpg",
	              "../../assets/images/qiaoliang.jpg",
	              "../../assets/images/shuibeng.jpg",
	              "../../assets/images/suidao.jpg",
	              "../../assets/images/xinhaoji.jpg",
	              "../../assets/images/zhuanzheji.jpg"
	              ];
	 var attr=equipmentId.split(".");
 	  var strof=attr[2]+"."+attr[3];
	if(strof=="08.01"){//转辙机
		$("#onLineImg")[0].src=faultImg[5];
	}else if(strof=="02.24"){//高压开关
		$("#onLineImg")[0].src=faultImg[0]; // TODO 触网和开关没有在途试图
	}else if(strof=="14.101"){//触网
		$("#onLineImg")[0].src=faultImg[0];
	}else if(strof=="13.100"){//轨道
		$("#onLineImg")[0].src=faultImg[3];
	}else if(strof=="16.300"){//桥梁
		$("#onLineImg")[0].src=faultImg[1];
	}else if(strof=="15.200"){//隧道
		$("#onLineImg")[0].src=faultImg[3];
	}else if(strof=="03.36"){//风机
		$("#onLineImg")[0].src=faultImg[0];
	}
	
	
}
/***任务分配按钮**/
function slideUpactive_analysis(){
	$("#tasktoPerson").slideDown("slow");
	$("#divOfslideUpactive").slideUp("slow");
}
/***分配任务取消按钮***/
function cancelthisTask(){
	
	$("#tasktoPerson").slideUp("slow");
	$("#divOfslideUpactive").slideDown("slow");
}
/***分配任务中的确定按钮***/
function committhisTask(id){
	var userNames=$("#citySel").val();
	OnCheckProcess(id,userNames);
}

/***OCC调度员确定分配任务到专业工程师****/
function OnCheckProcess(id,userNames){
	if(userNames.length>0){
		if(confirm("确认要将任务分配给"+userNames+"?")){
			//解析传来的数据
			 var warningLevel = {"一级":"1","二级":"2","三级":"3","四级":"4"};
			 var  confirmorcancel_json ={
					"equipmentid":$("#equipmentId").html(),
					"warningId":user2_warningId,
					"equipmentname":$("#equipmentname").html(),
					"systemname":$("#systemName").html(),
					"warningType":$("#warningType").html(),
					"warningTypeLevel":warningLevel[$("#warningTypeLevel").html()],
					"lineNo":$("#lineNo").html(),
					"stationName":$("#stationName").html(),
					"warningDate":$("#warningDate").html(),
					"statments":$("#statments").html(),
					"id":g_id,
					"fultDescription":$("#warninginfo").html(),
					"operator":operator
				};
			 
			// alert(JSON.stringify(confirmorcancel_json));
			var Url="/Rcm/Manage/insertFaultInfo_user2.do";
			// TODO 演示需求注释掉
			/*PostJSONQuery(Url,confirmorcancel_json,function(response){
				
				 if(response.info=="sucess"){
					//window.confirm("核实成功");	
						 window.setTimeout(function(){
							 
							 $("#success_block").fadeIn("slow");
							 
						 }, 100);
					 
					window.setTimeout(function(){
						location="../occ/occmain.jsp";
					},2000);
				}else{
					window.confirm("任务分配失败");
				}
			});*/
			//演示用
			 window.setTimeout(function(){
				 
				 $("#success_block").fadeIn("slow");
				 
			 }, 100);
		 
			window.setTimeout(function(){
				location="../occ/occmain.jsp";
			},2000);
		}
	}else{
		window.confirm("请至少选择一个人！");
	}
	
}
/***OCC调度员驳回按钮****/
function  OnAbortProcess(id,statment){
	//alert("id="+id+","+"statment="+statment+","+"userName="+userName);
	if(confirm("确认要驳回这条告警吗?")){
		if($("#citySel").val().length>0){
			var  stopFlow_json = {
					   "id":id,
				       "statments":statment,
				       "username":"user2",
				       "operator":operator
			};
			
			var Url="/Rcm/Manage/stopFlow.do";
			PostJSONQuery(Url,stopFlow_json,function(response){
			if(response.info=="sucess"){
				window.confirm("告警驳回成功");
				window.setTimeout(function(){
					location="../occ/occmain.jsp";
				},500);
				
			}else{
				window.confirm("告警驳回失败");
			}
				 
			});
		}else{
			window.confirm("请至少选择一个人");
		}
		
		
	};
}



var setting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				radioType: "all"
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
			{id:3, pId:0, name:"供电", open:true, nocheck:true},
			{id:31, pId:3, name:"曹雪(共1项任务未处理)"},
			{id:32, pId:3, name:"薛林(共2项任务未处理)"},
			{id:4, pId:0, name:"通号", open:true, nocheck:true},
			{id:41, pId:4, name:"张华(共1项任务未处理)"},
			{id:42, pId:4, name:"蔡龙(共1项任务未处理)"},
			
			{id:5, pId:0, name:"工务", open:true, nocheck:true},
			{id:51, pId:5, name:"石岭(共1项任务未处理)"},
			{id:52, pId:5, name:"刘梅(共3项任务未处理)"},
			
			{id:6, pId:0, name:"车辆", open:true, nocheck:true},
			{id:61, pId:6, name:"戴迎(共2项任务未处理)"},
			{id:62, pId:6, name:"高磊(共3项任务未处理)"}
			
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
				
				v += nodes[i].name.substring(0,nodes[i].name.indexOf("(")) + ",";
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





