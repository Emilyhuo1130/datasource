var  g_id = 0;
var g_desc=null;
var stop_message_json_user=null;//终止流程信息
var check_opinion_json=null;
var start_Time=null;
var user3_stop=false;
var user="";//
var flag="No";
/***Ztree插件的样式*/
function getFont(treeId, node) {
	return node.font ? node.font : {};
}



function showWarningLisstByStatments(statments,warningType){
	 var operative_index_query_json = {
			        "page": {
			            "pageCount": "10",
			            "pageNo": "1"
			        },
			        "statments": statments,
			        "warningType": warningType
			};
	 
	 
	 var Url="/Rcm/Manage/getWaringListBystatments.do";
	 PostJSONQuery(Url,operative_index_query_json,function(response){
		 var warningList=response.warningList;
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
			  		
			  		"<td id='from'>"+warningList[i].fromuser+"</td>" +
			  		"<td ><a href='"+encodeURI("show"+statments+".jsp?id="+warningList[i].id+"&stat="+warningList[i].statments+"&equipmentId="+warningList[i].equipmentId)+"&user=user"+(eval(statments)+1)+"'>详情</a></td>" +
			  				"</tr>");
			  }
		 });
}





/***occ专业主任查看详情**/
var user4_fultDescription=null;
var user4_warninglevel=1;
function getDetailInfo4(id){
	 var query_json = {
		              "id":id
		              };
	
	 var Url="/Rcm/Manage/getFaultInfo_user2ById.do";
	 PostJSONQuery(Url,query_json, function(response){
		 var warningList = response;
		//alert(JSON.stringify(response));
		  var statmentsnumber = warningList.statments;
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
		       $("#asset_lineNo").val(warningList.lineNo);
			   $("#asset_equipmentname").val(warningList.equipmentname);
		       $("#malfunction_level").val(user4_warninglevel);//告警等级
			   $("#malfunction_desc").val(warningList.stationName+"  "+warningList.fultDescription);//故障描述
			   $("#malfunction_cause_director").val(warningList.faultCause);//故障原因
			   $("#text_impact_directo").val(warningList.influence);
			   $("#maintenancePolicy").val(warningList.maintenancePolicy);
			   $("#asset_stationname").val(warningList.stationName);
			   $("#system_main").val(warningList.systemname);
			   $("#component_code").val(warningList.maintenanceId);
			   $("#health_index").val(user4_warninglevel);
			   $("#ticket_id").val(warningList.suggest_Number);
			   $("#component_code").val(warningList.equipmentid);
			   $("#system_main option:eq("+system_num[""+warningList.systemname]+")").attr("selected","selected");
			   //显示在途视图
			  
	  		   getImgUrl(warningList.equipmentid);
			   user4_fultDescription = warningList.fultDescription;
			   
			   //TODO 业务测试
			   test_occ_director();
			   user="user4";
		   	 showTreeinfo(warningList.equipmentid,warningList.systemname,warningList.stationName,warningList.equipmentname,flag,user);
	});
}
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
	//alert(JSON.stringify(query_criteriaFault));
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
//设备树
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
		  
		  /**显示设备的故障履历表*/
			var treeObj = $.fn.zTree.getZTreeObj("mal_equipmentTree");
			var node = treeObj.getNodeByParam('name', $("#asset_equipmentname").val(), null);
			
			_component_couple_back=node.getParentNode().name;
			$("#profession_name")[0].innerHTML=_component_couple_back;
			getMaintain_couple_back(1);
	    });
	
	 
}
var _component_couple_back=null;
/***找到某条线的某个设备的维修反馈记录**/
function getMaintain_couple_back(page){
	var notify_query_json = {
	        "page": {
	            "pageCount": "2",
	            "pageNo": page
	        },
	        "query": {
	        	"component": _component_couple_back,
	            "lineNO":$("#asset_lineNo").val(),
	            "warningType":""
	           
	        }
	    };
	var Url="/Rcm/Manage/getMaintain_couple_back.do";
	 PostJSONQuery(Url,notify_query_json,function(response){
		  
		  $('#getMaintain_couple_back_page_totalpage')[0].innerHTML = response["totalPage"];
		  $('#getMaintain_couple_back_page_totalCount')[0].innerHTML = response["totalCount"];
		  var pageNo = notify_query_json.page.pageNo; 
		  $('#getMaintain_couple_back_page_nowpage')[0].innerHTML = pageNo;
		  var informList=response.backInfoList;
		 $('#query_result_table_body_getMaintain_couple_back tr').remove();
		  
		  for(var i=0;i<informList.length;i++){
			  
			  $("#query_result_table_body_getMaintain_couple_back").append(""+
					  "<tr style='height:auto'>" +
						"<td width='100' >"+informList[i].repairsDate.substring(0,11)+"</td>" +
						"<td width='100' >"+informList[i].lineNo+"号线</td>" +
						"<td width='100' >"+informList[i].stationName+"</td>" +
						"<td width='200' >"+informList[i].warningInfo+"</td>" +
						"<td width='400' >"+informList[i].maintencePolicy+"</td>" +
						"<td width='200' >"+informList[i].maintainInfo+"</td>" +
				  		"</tr>"
					
			  		);
			  } 
	    });  
}

/**维修历史上一页和下一页**/
function backpageMaintain_couple_back(){
	var nowpage= $('#getMaintain_couple_back_page_nowpage')[0].innerHTML;
	var totalpage= $('#getMaintain_couple_back_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		
		getMaintain_couple_back(backPageNo);
	}
}
function nextpageMaintain_couple_back(){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#getMaintain_couple_back_page_nowpage')[0].innerHTML;
	var totalpage= $('#getMaintain_couple_back_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		//alert("nextpage="+nextPageNo);
		getMaintain_couple_back(nextPageNo);
	}
}
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

/**显示分配任务**/
var id_send=null;
var equipmentId_send=null;
function   showPersonToalltoTask(id,equipmentId){
	$("#citySel").val('');
	//TODO 业务测试
	id_send=id;
	equipmentId_send=equipmentId;
    var attr=equipmentId.split(".");
	  var strof=attr[2]+"."+attr[3];
	  var zNodes=null;
	  if(strof=="08.01"){//转辙机
		   zNodes =[
		   			{id:4, pId:0, name:"通号专业维修人员", open:true, nocheck:true},
		   			{id:41, pId:4, name:"顾城(共1项任务未处理)"},
		   			{id:42, pId:4, name:"李想(共1项任务未处理)"},
		   		 ];

  	  }else{
  		 zNodes =[
		   			{id:4, pId:0, name:"其他专业维修人员", open:true, nocheck:true},
		   			{id:41, pId:4, name:"顾斌(共1项任务未处理)"},
		   			{id:42, pId:4, name:"李一天(共1项任务未处理)"},
		   		 ];
  		  
  	  }
	  $.fn.zTree.init($("#treeDemo"), setting_task, zNodes);
	  $("#tableOfTaskToalloc").slideDown("slow");
	  location="#link1";//锚点
}
/**分配任务到人的确定按钮**/
function OnApproveByUser4_goto(){
	var person_task=$("#citySel").val();
	if(person_task.length>0){
		if($("#opinion1").val().trim().length==0||$("#opinion2").val().trim().length==0||$("#opinion3").val().trim().length==0){
			confirm("请填写完整审定意见");
			
		}else{
			if(confirm("确定将维修任务分配给"+person_task+"?")){
				savetheMessage();
			}
		}
		
	}else{
		confirm("请至少选择一个人");
	}
}
/**分配任务到人的取消按钮按钮**/
function task_cancel(){
	 $("#tableOfTaskToalloc").slideUp("slow");
}
/**occ专业主任发通知单请求*/
function savetheMessage(){
	var  messageForm_json = {
			"equipmentId":equipmentId_send,
			"equipmentName":$("#asset_equipmentname").val(),
			"faultCause":$("#malfunction_cause_director").val(),
			"fultDescription":user4_fultDescription,
			"id":id_send,
			"influence": $("#text_impact_directo").val(),
			"lineNo":$("#asset_lineNo").val(),
			"mainTenancePolicy": $("#maintenancePolicy").val(),
			"operator":$("#operator").val(),
			"stationName":$("#asset_stationname").val(),
			"subSystemName":$("#subsystem").val(),
			"systemName":$("#system_main").val(),
			"starTime":$("#start_time").val()+" 00:00:00",
			"warningTypeLevel":$("#malfunction_level").val(),
			"healthLevel":$("#health_index").val(),
			"jobNumber":$("#ticket_id").val(),
			"opinions":$("#opinion1").val()+'||'+$("#opinion2").val()+'||'+$("#opinion3").val(),
			"operator":operator

};
			record_array[0] = $("#system_main").val();
			record_array[1] = $("#asset_equipmentname").val();
			record_array[2] = user4_fultDescription;
			record_array[3] = $("#ticket_id").val();
			record_array[4] = $("#citySel").val();//某某维修人员
			if(($("#ticket_id").val()!="")&&($("#system_main").val()!="")&&($("#subsystem").val()!="")){
				var Url="/Rcm/Manage/insertinform_user4.do";
				//alert(JSON.stringify(messageForm_json));
				// TODO 演示需要注释
				/*PostJSONQuery(Url,messageForm_json,function(response){
					if(response.info=="sucess"){
						 window.setTimeout(function(){
							 $("#success_block_send").fadeIn("slow");
						 }, 100);
						 showMessage();
					}else{
						window.confirm("发送失败");
						return false;
					}
					
				});*/
				window.setTimeout(function(){
					 $("#success_block_send").fadeIn("slow");
				 }, 100);
				 showMessage();
				
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




//----------------------------------趋势分析统计表--------------------------------------------------//
//获得点代码  形参设备id
var componentId_=null;
function   getReqData(id){
	//TODO
	componentId_=id;
	var  query_PointData={
		        "componentId":id
	};
	 var Url="/Rcm/Manage/getTendencyList.do";
	PostJSONQuery(Url,query_PointData,function(response){
		//alert(JSON.stringify(response));
		 var result = [];
		 
		 
		response.infoList.forEach(function(element,index){
		
        var target_per_day = {};
        target_per_day.key = element.name;
        target_per_day.values = [];
        element.infoList.forEach(
          function(elementCount,indexCount){
          	//alert(JSON.stringify({"key":elementCount.savetime,"value": parseInt(elementCount.value)}));
            target_per_day.values[indexCount] =[elementCount.savetime,parseInt(elementCount.value)];
           if (isNaN(target_per_day.values[indexCount][1]))
            {
              target_per_day.values[indexCount][1] = 0;
            }
          }
        );
     
        	result[index] = target_per_day;
		});
		showTendChart("#sc_trend_analysis svg", result);
      });
}

function   showTendChart(domName, data){
	
	init_TargetLineChart("#sc_trend_analysis svg",data);
} 
//初始化图表
function init_TargetLineChart(domName,data)
{
nv.addGraph(function() {
  var chart = nv.models.lineWithFocusChart()
    .x(function(d) { return d[0] ;})
    .y(function(d) { return d[1];})
    .color(d3.scale.category10().range());

  chart.xAxis
    .axisLabel('时间(t)')
    .tickFormat(function(d) {
      return d3.time.format('%H:%M:%S')(new Date(d));
    });
	chart.x2Axis
    .axisLabel('时间(t)')
    .tickFormat(function(d) {
      return d3.time.format('%H:%M:%S')(new Date(d));
    });
  chart.yAxis
    .axisLabel('电压(V)')
    .tickFormat(d3.format('.02f'));
  chart.y2Axis
       .tickFormat(d3.format(',.02f'));

  d3.select(domName)
    .datum(data)
    .transition().duration(500)
    .call(chart);
	
  
  nv.utils.windowResize(chart.update);

  return chart;
});
}


/***任务分配**/
var setting_task ={
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
			
		});




/**************************************/
function test_occ_director(){

    var attr=componentId_.split(".");
	  var strof=attr[2]+"."+attr[3];
	
		  if(strof=="08.01"){//转辙机
				  $("#first_workspace").slideDown("slow");
				  $("#sc_trend_analysis").slideDown("slow");
    	  }else if(strof=="02.24"){
    		  $("#sc_trend_analysis").slideDown("slow");
    	  }else if(strof=="03.36"){
    		  $("#sc_trend_analysis").slideDown("slow");
    	  }
}

