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
/**显示等待occ专业工程师处理的告警信息**/
var user4_warninglevel=1;
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




/***********************诊断分析*******************************/

//user3 获得页面动态信息---------------------------------------------------------
function getDetailInfo3(id){
	
	 var query_json = {
			 "id":id
			 };
 
 var Url="/Rcm/Manage/getFaultInfo_user2ById.do";
 PostJSONQuery(Url,query_json,function(response){
		 var warningList = response;
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
			  	
			  		"<td id='from[warningList.statments]'>"+warningList.fromuser+"</td>" +
			  		"</tr>");
				  
				   //加载好页面数据后自动展开故障树的调用方法
		  		   getImgUrl(warningList.equipmentid);
				  
		  		   flag="Yes";
		  		   user="user3";
		  		   showTreeinfo(warningList.equipmentid,warningList.systemname,warningList.stationName,warningList.equipmentname,flag,user);
		  		   start_Time=new Date().format("yyyy-MM-dd hh:mm:ss");
		  		   //显示在途视图
		  		 // stop_message_json_user=stop_message_json_user3;
		  		 
	});
}

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
		 /* var setting = {
					view: {
						fontCss: getFont,
						nameIsHTML: true
						}
					};*/
		  var setting_FaultTree = {
					data: {
						simpleData: {
							enable: true
						}
					},
					check: {
						enable: true,
						chkStyle: "radio",
						radioType: "all"
					},
					view: {
						fontCss: getFont,
						nameIsHTML: true
					},
					callback: {
						beforeClick: beforeClick,
						onCheck: onCheck_Equipment
					}
					

				};
		  var  systems=response.name;
		 
		  $.fn.zTree.init($("#mal_faultTree"), setting_FaultTree, systems);
		  
		  /***获取某个设备的历史维修记录的专业和***/
		  getprofessionAndequipment(query_criteria.faulttype,query_criteria.equipmentId);
	    });
}
var _lineNo_couple_back=null;
var _equipment_couple_back=null;
var _faulttype_couple_back=null;
var _component_couple_back=null;
function getprofessionAndequipment(faulttype,equipmentId){
	var attr=equipmentId.split(".");
	var strof=attr[2]+"."+attr[3];
	if(strof=="08.01"){//TODO 测试使用
		$("#equipemnt_Description")[0].innerHTML="道岔";
		$("#profession_name")[0].innerHTML="通号";
		$("#showotherresultOfdiagnose").slideDown("slow");//显示其他专业的告警记录标签
		_component_couple_back="道岔";
		//其他相关专业的维修反馈记录
	
	}else if(strof=="02.24"){//高压开关
		$("#equipemnt_Description")[0].innerHTML="高压开关柜";
		$("#profession_name")[0].innerHTML="供电";
		_component_couple_back="高压开关柜";
	}else if(strof=="14.101"){//触网
		$("#equipemnt_Description")[0].innerHTML="触网";
		$("#profession_name")[0].innerHTML="工务";
		_component_couple_back="上下行触网";
	}else if(strof=="13.100"){//轨道
		$("#equipemnt_Description")[0].innerHTML="轨道";
		$("#profession_name")[0].innerHTML="工务";
		_component_couple_back="上下行轨道";
	}else if(strof=="16.300"){//桥梁
		$("#equipemnt_Description")[0].innerHTML="桥梁";
		$("#profession_name")[0].innerHTML="工务";
		_component_couple_back="桥梁";
	}else if(strof=="03.36"){//风机
		$("#equipemnt_Description")[0].innerHTML="隧道风机通风系统";
		$("#profession_name")[0].innerHTML="供电";
		_component_couple_back="隧道风机通风系统";
	}else if(strof=="15.200"){//隧道
		$("#equipemnt_Description")[0].innerHTML="隧道";
		$("#profession_name")[0].innerHTML="工务";
		_component_couple_back="隧道";
	}
	
	_lineNo_couple_back=attr[0];
	_equipment_couple_back=$("#equipemnt_Description")[0].innerHTML;
	_faulttype_couple_back=faulttype;
	getMaintain_couple_back(1);
	
}
/***找到某条线的某个设备的维修反馈记录**/
function getMaintain_couple_back(page){
	var notify_query_json = {
	        "page": {
	            "pageCount": "3",
	            "pageNo": page
	        },
	        "query": {
	        	"component": _component_couple_back,
	            "lineNO":_lineNo_couple_back,
	            "warningType": _faulttype_couple_back
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
		   var onmouse="<tr style='height:26px'>";
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
/***Ztree插件的样式*/
function getFont(treeId, node) {
	return node.font ? node.font : {};
}
function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("mal_faultTree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck_Equipment(e, treeId, treeNode) {
	//TODO //多选
	var check=treeNode.checked;
	if(check){
		//alert(treeNode.name+'    '+treeNode.value+"   ");
		
		showFaultInfo(treeNode.value);
		
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
				 var setting_EquipmentTree = {
				view: {
					fontCss: getFont,
					nameIsHTML: true
					}
				};
		  var  systems=response.name;
		  $.fn.zTree.init($("#mal_equipmentTree"), setting_EquipmentTree, systems);
		  
		  //TODO 测试使用数据
		  test_mal_equipmentTree();
		  
	    });
	
	 
	}

//设备树，故障树查询方法
function getEquipmentInfo(query_criteria){
		
		  var operative_index_query_json = {
		    "method":"searchEquipmentInfo",
		    "req":{
		    	"id":query_criteria.id
		    		},
		    "type":"ChattelanalyseManager"
		    	};

		  PostJSONQuery(operative_index_query_json,
		    function(response){
			  
		    });
		
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



//----------------------------------趋势分析统计表--------------------------------------------------//
//获得点代码  形参设备id
function   getReqData(id){
	//TODO
	 var attr=id.split(".");
	  var strof=attr[2]+"."+attr[3];
	if(strof=="08.01"){//转辙机
		$("#sc_trend_analysis_father").slideDown("slow");
		$("#buttonofstartTodiagnose").slideDown("slow");
	}else if(strof=="02.24"){//高压开关
		$("#sc_trend_analysis_father").slideDown("slow");
		//没有推演，显示任务分配
		$("#tableOfdiagnoseResult").slideDown("slow");
		$("#showotherresultOfdiagnose").slideUp("slow");
	}else if(strof=="14.101"){//触网
		//没有趋势分析数据，没有推演，显示任务分配
		$("#tableOfdiagnoseResult").slideDown("slow");
		$("#showotherresultOfdiagnose").slideUp("slow");
	}else if(strof=="13.100"){//轨道
		//没有趋势分析数据，没有推演，显示任务分配
		$("#tableOfdiagnoseResult").slideDown("slow");
		$("#showotherresultOfdiagnose").slideUp("slow");
	}else if(strof=="16.300"){//桥梁
		//没有趋势分析数据，没有推演，显示任务分配
		$("#tableOfdiagnoseResult").slideDown("slow");
		$("#showotherresultOfdiagnose").slideUp("slow");
	}else if(strof=="03.36"){//风机
		$("#sc_trend_analysis_father").slideDown("slow");
		//没有推演，显示任务分配
		$("#tableOfdiagnoseResult").slideDown("slow");
		$("#showotherresultOfdiagnose").slideUp("slow");
	}else if(strof=="15.200"){//隧道
		//没有推演，显示任务分配
		$("#tableOfdiagnoseResult").slideDown("slow");
		$("#showotherresultOfdiagnose").slideUp("slow");
	}
	
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

/**开始诊断按钮**/
function startTodiagnose(){
	$("#startTodiagnose").slideDown("slow");
	$("#buttonofstartTodiagnose").slideUp("slow");
	
}
/****诊断中下一步的按钮*/
var nextflag=0;
function next_diagnoseimage(){

    if(nextflag==0){
		$("#diagnoseimages")[0].src="image2.png";
	}
	if(nextflag==1){
		$("#diagnoseimages")[0].src="image3.png";
	}
	if(nextflag==2){
		$("#diagnoseimages")[0].src="image4.png";
	}
	if(nextflag==3){
		$("#diagnoseimages")[0].src="image5.png";
		$("#buttonOfdiagnoseimages").html("显示诊断结果");
		//TODO
		//测试
		test_mal_faultTree();
		
    }
    if($("#buttonOfdiagnoseimages").html()=="显示诊断结果"){
    	show_resultOfdiagnose();
    }
	
	nextflag=nextflag+1;
}
/**显示诊断结果* 故障树开始替换数据**/
function show_resultOfdiagnose(){
	$("#startTodiagnose").slideUp("slow");
	//TODO
	//然后查找后台显示故障树的百分比
	
	//显示诊断结果表
	$("#tableOfdiagnoseResult").slideDown("slow");
	$("#showotherresultOfdiagnose_father").slideDown("slow");
	
}
/**显示其他专业诊断结果**/
function showotherresultOfdiagnose(){
	
	var text=$("#changeText").html();
	if(text=="显示其他专业诊断结果"){
		$("#changeText").html("隐藏其他专业诊断结果");
		$("#tableOfotherdiagnoseResult").slideDown("slow");
	}else{
		$("#changeText").html("显示其他专业诊断结果");
		$("#tableOfotherdiagnoseResult").slideUp("slow");
	}
	
}

/****任务分配**/
function allocation_task(){
	$("#tableOfTaskToalloc").slideDown("slow");
	location="#showtasktoallot";
	
}

/**任务分配确定按钮和驳回按钮***/
function OnApproveByUser3_goto(id,ok){
	var text=$("#citySel").val();
	if(text.length>0){
		if(ok=="goto"){
			sendNextUser_Byuser3(id,ok);
		}else{
			OnApproveByUser3(id,ok);
		}
	}else{
		confirm("请至少选择一个人");
	}
}

function OnApproveByUser3(id,ok){
	if(confirm("确认要驳回给"+$("#citySel").val()+"?")){
	
		var  approve_json ={
								   "ok":ok,
							       "id":id,
							       "opinion":'goback'+"||"+'goback'+"||"+'goback',
							       "operator":operator
							      };
		
		var Url="/Rcm/Manage/updateFaultInfo_user2ById.do";
		// TODO 演示需要注释
		/*PostJSONQuery(Url,approve_json,function(response){
				if(response.info=="sucess"){
					confirm("驳回成功");
			   		window.setTimeout(function(){
						location="_audit_list.jsp";
					},2000);
				}else{
					window.confirm("审定失败");
				}
			
		});*/
		//演示需要
		confirm("驳回成功");
   		window.setTimeout(function(){
			location="_audit_list.jsp";
		},2000);
		
	}
}
function sendNextUser_Byuser3(id,ok){
	
	if(confirm("确认要提交吗？")){
	
		var  approve_json ={
								   "ok":ok,
							       "id":id,
							       "opinion":'同意'+"||"+'同意'+"||"+'同意'+"",
							       "faultCause":$("#text_reason").val(),
							       "influence":$("#text_impact").val(),
							       "mainTenancePolicy":$("#text_strategy").val(),
							       "operator":operator
							    };
			 var Url="/Rcm/Manage/updateFaultInfo_user2ById.do";
			// alert(JSON.stringify(approve_json));
			 // TODO 演示需要注释
			 /*PostJSONQuery(Url,approve_json,
					    function(response){
						if(response.info=="sucess"){
							 window.setInterval(function(){
								 $("#success_block_shending").fadeIn("slow");
							}, 100);
							
					   		window.setTimeout(function(){
								location="_audit_list.jsp";
							},2000);
						}else{
							window.confirm("审定失败");
						}
				}); */
			 //演示需要
			 window.setInterval(function(){
				 $("#success_block_shending").fadeIn("slow");
			}, 100);
			
	   		window.setTimeout(function(){
				location="_audit_list.jsp";
			},2000);
		
	}
}
/**任务分配取消*/
function task_cancel(){
	$("#tableOfTaskToalloc").slideUp("slow");
}
/**显示本专业诊断结果***/

function showFaultInfo(id){
	query_criteria={
			"id":id
	};
	getFaultInfo(query_criteria);
	
}

function getFaultInfo(json){
	 var query_json = {	 
		 "id":json.id
			 };
	 
	 var Url="/Rcm/Manage/getFaultinfo.do";
	 PostJSONQuery(Url,query_json,function(response){
		//alert("response="+JSON.stringify(response));
		 var warningList = response;
	//alert("getFaultInfo is dowm"+warningList.faultCause);
		   $("#text_reason").val(warningList.faultCause);
		   $("#text_impact").val(warningList.influence);
		   $("#text_strategy").val(warningList.maintenancePolicy);
		   location="#link_FaultInfo";
	});
	
}
/***任务分配**/
var setting ={
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
			{id:31, pId:3, name:"陆成(共1项任务未处理)"},
			{id:32, pId:3, name:"吴斌(共2项任务未处理)"},
			
			{id:4, pId:0, name:"通号", open:true, nocheck:true},
			{id:41, pId:4, name:"周林(共1项任务未处理)"},
			{id:42, pId:4, name:"李帅(共1项任务未处理)"},
			
			{id:5, pId:0, name:"工务", open:true, nocheck:true},
			{id:51, pId:5, name:"杨涛(共1项任务未处理)"},
			{id:52, pId:5, name:"高峰(共3项任务未处理)"},
			
			{id:6, pId:0, name:"车辆", open:true, nocheck:true},
			{id:61, pId:6, name:"王军(共2项任务未处理)"},
			{id:62, pId:6, name:"张燕(共3项任务未处理)"}
			
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
		
/********occ/assessOfwarning.jsp 告警建议评估********************/
$(document).ready(function(){
	$('input[name=profession]').click(function(){
		if(this.checked){
			$("#text_strategy").val('');
			$("#labeloftextarea").html($(this).val());
		}else{
		}
	});
});
function showsave(){
	confirm("保存成功");
}


//TODO 测试使用方法
		
function test_mal_faultTree(){//推演出故障百分比
	var treeObj = $.fn.zTree.getZTreeObj("mal_faultTree");
    var equipment_to_sub=$("#user3_equipmentid")[0].innerHTML;
    var attr=equipment_to_sub.split(".");
	var strof=attr[2]+"."+attr[3];
		  if(strof=="08.01"){//转辙机
      		  var node = treeObj.getNodeByParam("name", "定位表示回路故障", null);
      		  node.name="定位表示回路故障 60%";
      		  node.font={'color':'red'};
      		  treeObj.updateNode(node);
      		  
      		  var node3 = treeObj.getNodeByParam("name", "反位表示回路故障", null);
      		  node3.name="反位表示回路故障 30%";
      		  node3.font={'color':'red'};
      		  treeObj.updateNode(node3);
      		  
      		 var node2 = treeObj.getNodeByParam("name", "轨道", null);
      		node2.name="轨道 10%";
      		node2.font={'color':'red'};
      		treeObj.updateNode(node2);
      		 treeObj.expandNode(node2, true, true, true);
      		 
      	  }else{
      		  
      	  }
	location="#link-to";
	confirm("故障推演已经完成，请查看右边的故障树");
	
}


function test_mal_equipmentTree(){//推演出设备故障的比例
	var treeObj = $.fn.zTree.getZTreeObj("mal_equipmentTree");
    var equipment_to_sub=$("#user3_equipmentid")[0].innerHTML;
    var attr=equipment_to_sub.split(".");
	  var strof=attr[2]+"."+attr[3];
	
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

