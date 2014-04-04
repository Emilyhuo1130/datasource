//向服务器发请求的ajax

//------------------------------------------------------------------------------//
/**报表管理模块的告警历史统计表*/	
/**上一页*/
function report_his_backpage(){
	
	var nowpage= $('#report_page_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_report(backPageNo);
	}
	
	
}
/**下一页*/
function report_his_nextpage(){
	var nowpage= $('#report_page_nowpage')[0].innerHTML;
	var totalpage= $('#report_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_report(nextPageNo);
	}
}

/**告警历史统计表//查询按钮*/
function query_report(val9){	
	
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
			            "warningType":"故障告警"
									};
	onReport_equipment_Query(query_criteria);
}
/**告警历史统计表*/
function onReport_equipment_Query(query_criteria){
	//alert("query_criteria="+JSON.stringify(query_criteria));
  var query_post_json = {
			        "page": {
			            "pageCount": "5",
			            "pageNo": "1"
			        },
			        "query": {
			            "endTime": "",
			            "lineNo": "",
			            "starTime": "",
			            "component":"",
			            "stationName": "",
			            "subSystemName": "",
			            "systemName": "",
			            "warningType":"故障告警"
			        }
};
	 
  if (query_criteria!=null)
  {
    if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
    
    if (query_criteria.component) {
      query_post_json.query.component = query_criteria.component;
    }
    
    if (query_criteria.systemName) {
      query_post_json.query.systemName = query_criteria.systemName;
    }
    
    if (query_criteria.subSystemName) {
      query_post_json.query.subSystemName = query_criteria.subSystemName;
    }
    
    if (query_criteria.lineNo) {
      query_post_json.query.lineNo = query_criteria.lineNo;
    }
    
     
      if (query_criteria.stationName) {
      query_post_json.query.stationName = query_criteria.stationName;
    }
      
  
     
     if (query_criteria.warningTypeLevel) {
      query_post_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
    }
     
    if (query_criteria.starTime) {
      query_post_json.query.starTime = query_criteria.starTime;
    }
    
    if (query_criteria.endTime) {
     query_post_json.query.endTime = query_criteria.endTime;
    }
    
   }
   
  var Url="/Rcm/Manage/getcountofhistoryWarning.do";
  PostJSONQuery(Url,query_post_json,function(response){
		//alert("fenye="+JSON.stringify(response));
	 var historyList = response.historyList;
		//alert(JSON.stringify(operateindexList));
		 var pageNo = query_post_json.page.pageNo; 
	    $('#report_page_totalpage')[0].innerHTML = response["totalPage"];
	    $('#report_page_totalCount')[0].innerHTML = response["totalCount"];
	    
	    $('#report_page_nowpage')[0].innerHTML = pageNo;
	    var alertLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	    
	    $('#query_result_table_body tr').remove();

		for(var i=0;i<historyList.length;i++){
		var page=pageNo;
	   
		var component_req={"component":historyList[i].component,"warningType":"故障告警"};
		$("#query_result_table_body").append("<tr style='height:26px'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+historyList[i].systemName+"</td>"+
			"<td>"+historyList[i].subSystemName+"</td>"+
			"<td>"+historyList[i].lineNo+"</td>"+
			"<td>"+historyList[i].stationName+"</td>"+
			"<td>"+historyList[i].component+"</td>"+
			"<td>"+historyList[i].count+"</td>"+
			"<td><a href='report_historyAlert_statistics.jsp?req="+historyList[i].component+"'>详情</a></td>"+
		"</tr>");
		}
	});

  }
/**告警历史查询详情表***************************************************************/
/**查询按钮*/
function query_report_history(val9){	
	
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
			            "warningType":"故障告警"
									};
	onReport_equipment_Query_Details(query_criteria);
}
/**上一页*/
function historyAlert_backpage(){
	
	var nowpage= $('#report_page_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_report_history(backPageNo);
	}
	
	
}
/**下一页*/
function historyAlert_nextpage(){
	var nowpage= $('#report_page_nowpage')[0].innerHTML;
	var totalpage= $('#report_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_report_history(nextPageNo);
	}
}
function onReport_equipment_Query_Details(query_criteria){
	//alert("query_criteria="+JSON.stringify(query_criteria));
  var query_post_json = {
        "page": {
            "pageCount": "10",
            "pageNo": "1"
        },
        "query": {
            "component":query_criteria.component,
            "endTime": "",
            "lineNo": "",
            "starTime": "",
            "stationName": "",
            "subSystemName":"",
            "systemName": "",
            "warningType": "故障告警",
            "warningTypeLevel": ""
        }
};
	// alert(JSON.stringify(query_post_json));
  if (query_criteria!=null)
  {
    if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
    
    if (query_criteria.component) {
      query_post_json.query.component = query_criteria.component;
    }
    
    if (query_criteria.systemName) {
      query_post_json.query.systemName = query_criteria.systemName;
    }
    
    if (query_criteria.subSystemName) {
      query_post_json.query.subSystemName = query_criteria.subSystemName;
    }
    
    if (query_criteria.lineNo) {
      query_post_json.query.lineNo = query_criteria.lineNo;
    }
    
     
      if (query_criteria.stationName) {
      query_post_json.query.stationName = query_criteria.stationName;
    }
      
  
     
     if (query_criteria.warningTypeLevel) {
      query_post_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
    }
     
    if (query_criteria.starTime) {
      query_post_json.query.starTime = query_criteria.starTime;
    }
    
    if (query_criteria.endTime) {
     query_post_json.query.endTime = query_criteria.endTime;
    }
    
   }
   
  var Url="/Rcm/Manage/gethistoryWarningList.do";
  PostJSONQuery(Url,query_post_json,function(response){
		//alert("fenye="+JSON.stringify(response));
	 var historyList = response.historyList;
		//alert(JSON.stringify(operateindexList));
		 var pageNo = query_post_json.page.pageNo; 
	    $('#report_page_totalpage')[0].innerHTML = response["totalPage"];
	    $('#report_page_totalCount')[0].innerHTML = response["totalCount"];
	    
	    $('#report_page_nowpage')[0].innerHTML = pageNo;
	    var alertLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	    
	    $('#query_result_table_body tr').remove();

		for(var i=0;i<historyList.length;i++){
		var page=pageNo;
	   
		
		$("#query_result_table_body").append("<tr style='height:26px'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+historyList[i].systemName+"</td>"+
			"<td>"+historyList[i].subSystemName+"</td>"+
			"<td>"+historyList[i].lineNo+"</td>"+
			"<td>"+historyList[i].stationName+"</td>"+
			"<td>"+historyList[i].component+"</td>"+
			"<td>"+historyList[i].warninginfo+"</td>"+
			"<td>"+alertLevel[historyList[i].warningTypeLevel]+"</td>"+
			"<td>"+historyList[i].warningstatments+"</td>"+
			"<td width='180px'>"+historyList[i].warningTime+"</td>"+
		"</tr>");
		}
	});

  }
/**预警历史统计表*********************************************************************/
function  showFultureReportInfo(query_criteria){
	  var query_post_json = {
			        "page": {
			            "pageCount": "10",
			            "pageNo": "1"
			        },
			        "query": {
			            "endTime": "",
			            "lineNo": "",
			            "starTime": "",
			            "component":"",
			            "stationName": "",
			            "subSystemName": "",
			            "systemName": "",
			            "warningType":"故障预警"
			        }
};
	 if (query_criteria!=null)
	  {
	    if (query_criteria.pageNo) {
	      query_post_json.page.pageNo = query_criteria.pageNo;
	    }
	    
	    if (query_criteria.component) {
	      query_post_json.query.component = query_criteria.component;
	    }
	    
	    if (query_criteria.systemName) {
	      query_post_json.query.systemName = query_criteria.systemName;
	    }
	    
	    if (query_criteria.subSystemName) {
	      query_post_json.query.subSystemName = query_criteria.subSystemName;
	    }
	    
	    if (query_criteria.lineNo) {
	      query_post_json.query.lineNo = query_criteria.lineNo;
	    }
	    
	     
	      if (query_criteria.stationName) {
	      query_post_json.query.stationName = query_criteria.stationName;
	    }
	     if (query_criteria.warningTypeLevel) {
	      query_post_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
	    }
	     
	    if (query_criteria.starTime) {
	      query_post_json.query.starTime = query_criteria.starTime;
	    }
	    
	    if (query_criteria.endTime) {
	     query_post_json.query.endTime = query_criteria.endTime;
	    }
	    
	   }
	 var Url="/Rcm/Manage/getcountofhistoryWarning.do";
	PostJSONQuery(Url,query_post_json,function(response){
		var historyList = response.historyList;
		 var pageNo = query_post_json.page.pageNo; 
	    $('#report_pre_page_totalpage')[0].innerHTML = response["totalPage"];
	    $('#report_pre_page_totalCount')[0].innerHTML = response["totalCount"];
	    $('#report_pre_page_nowpage')[0].innerHTML = pageNo;
	    $('#query_result_table_body tr').remove();
		for(var i=0;i<historyList.length;i++){
		var page=pageNo;
		$("#query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((parseInt(page)-1)*10)+1+i)+"</td>"+
			"<td>"+historyList[i].systemName+"</td>"+
			"<td>"+historyList[i].subSystemName+"</td>"+
			"<td>"+historyList[i].lineNo+"</td>"+
			"<td>"+historyList[i].stationName+"</td>"+
			"<td>"+historyList[i].component+"</td>"+
			"<td>"+historyList[i].count+"</td>"+
			"<td width='180px'><a href='_precaution_history_statistics.jsp?req="+historyList[i].component+"'>详情</a></td>"+
		"</tr>");
		}
	});
}

/**预警历史统计详情表**************************************************************************/
function  showFultureReportInfo_Details(query_criteria){
	var query_post_json = {
		        "page": {
		            "pageCount": "10",
		            "pageNo": "1"
		        },
		        "query": {
		            "component":query_criteria.component,
		            "endTime": "",
		            "lineNo": "",
		            "starTime": "",
		            "stationName": "",
		            "subSystemName":"",
		            "systemName": "",
		            "warningType": "故障预警",
		            "warningTypeLevel": ""
		        }
		};
	//alert(JSON.stringify(query_post_json));
	 if (query_criteria!=null)
	  {
	    if (query_criteria.pageNo) {
	      query_post_json.page.pageNo = query_criteria.pageNo;
	    }
	    
	    if (query_criteria.component) {
	      query_post_json.query.component = query_criteria.component;
	    }
	    
	    if (query_criteria.systemName) {
	      query_post_json.query.systemName = query_criteria.systemName;
	    }
	    
	    if (query_criteria.subSystemName) {
	      query_post_json.query.subSystemName = query_criteria.subSystemName;
	    }
	    
	    if (query_criteria.lineNo) {
	      query_post_json.query.lineNo = query_criteria.lineNo;
	    }
	    
	     
	      if (query_criteria.stationName) {
	      query_post_json.query.stationName = query_criteria.stationName;
	    }
	     if (query_criteria.warningTypeLevel) {
	      query_post_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
	    }
	     
	    if (query_criteria.starTime) {
	      query_post_json.query.starTime = query_criteria.starTime;
	    }
	    
	    if (query_criteria.endTime) {
	     query_post_json.query.endTime = query_criteria.endTime;
	    }
	    
	   }
	 var Url="/Rcm/Manage/gethistoryWarningList.do";
	PostJSONQuery(Url,query_post_json,function(response){
		var historyList = response.historyList;
		//alert(JSON.stringify(historyList));
		 var pageNo = query_post_json.page.pageNo; 
	    $('#report_pre_page_totalpage')[0].innerHTML = response["totalPage"];
	    $('#report_pre_page_totalCount')[0].innerHTML = response["totalCount"];
	    $('#report_pre_page_nowpage')[0].innerHTML = pageNo;
	    var alertLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	    $('#query_result_table_body tr').remove();
		for(var i=0;i<historyList.length;i++){
		var page=pageNo;
	   
		$("#query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+historyList[i].systemName+"</td>"+
			"<td>"+historyList[i].subSystemName+"</td>"+
			"<td>"+historyList[i].lineNo+"</td>"+
			"<td>"+historyList[i].stationName+"</td>"+
			"<td>"+historyList[i].component+"</td>"+
			"<td>"+historyList[i].warninginfo+"</td>"+
			"<td>"+alertLevel[historyList[i].warningTypeLevel]+"</td>"+
			"<td>"+historyList[i].warningstatments+"</td>"+
			"<td>"+historyList[i].warningTime+"</td>"+
		"</tr>");
		}
	});
}
/**上一页*/
function report_pre_backpage(){
	
	var nowpage= $('#report_pre_page_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_pre_report(backPageNo);
	}
	
	
}
/**下一页*/
function report_pre_nextpage(){
	var nowpage= $('#report_pre_page_nowpage')[0].innerHTML;
	var totalpage= $('#report_pre_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_pre_report(nextPageNo);
	}
}
//查询按钮

function query_pre_report(val9,warningType){	
	
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
			            "warningType":warningType,
			            "pageNo": val9
									};
	onReport_pre_equipment_Query(query_criteria);
}
//按钮查询操作  预警
function onReport_pre_equipment_Query(query_criteria){
	//alert("query_criteria="+JSON.stringify(query_criteria));
  var query_post_json = {
        "page": {
            "pageCount": "10",
            "pageNo": "1"
        },
        "query": {
            "component": "",
            "endTime": "",
            "lineNo": "",
            "starTime": "",
            "stationName": "",
            "subSystemName":"",
            "systemName": "",
            "warningType": "故障预警",
            "warningTypeLevel": ""
        }
};

  
  
  if (query_criteria)
  {
    if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
    
    if (query_criteria.component) {
      query_post_json.query.component = query_criteria.component;
    }
    
    if (query_criteria.systemName) {
      query_post_json.query.systemName = query_criteria.systemName;
    }
    
    if (query_criteria.subSystemName) {
      query_post_json.query.subSystemName = query_criteria.subSystemName;
    }
    
    if (query_criteria.lineNo) {
      query_post_json.query.lineNo = query_criteria.lineNo;
    }
     
      if (query_criteria.stationName) {
      query_post_json.query.stationName = query_criteria.stationName;
    }
      
  
     
     if (query_criteria.warningTypeLevel) {
      query_post_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
    }
     
    if (query_criteria.starTime) {
      query_post_json.query.starTime = query_criteria.starTime;
    }
    
    if (query_criteria.endTime) {
     query_post_json.query.endTime = query_criteria.endTime;
    }
    
   
   
    var Url="/Rcm/Manage/gethistoryWarningList.do"; 
  PostJSONQuery(Url,query_post_json,function(response){
		//alert("fenye="+JSON.stringify(response));
	 var historyList = response.historyList;
		//alert(JSON.stringify(operateindexList));
		 var pageNo = query_post_json.page.pageNo; 
	    $('#report_pre_page_totalpage')[0].innerHTML = response["totalPage"];
	    $('#report_pre_page_totalCount')[0].innerHTML = response["totalCount"];
	    
	    $('#report_pre_page_nowpage')[0].innerHTML = pageNo;
	    var alertLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	    $('#query_result_table_body tr').remove();

		for(var i=0;i<historyList.length;i++){
		var page=pageNo;
	   
		
		$("#query_result_table_body").append("<tr style='height:26px'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+historyList[i].systemName+"</td>"+
			"<td>"+historyList[i].subSystemName+"</td>"+
			"<td>"+historyList[i].lineNo+"</td>"+
			"<td>"+historyList[i].stationName+"</td>"+
			"<td>"+historyList[i].component+"</td>"+
			"<td>"+historyList[i].warninginfo+"</td>"+
			"<td>"+alertLevel[historyList[i].warningTypeLevel]+"</td>"+
			"<td>"+historyList[i].warningstatments+"</td>"+
			"<td width='180px'>"+historyList[i].warningTime+"</td>"+
		"</tr>");
		}
	});
}
  }


//-----------------------------健康指数分析表----------------------------------------------------//
//报表管理健康指数下的设施指数表

//健康指数里设施指数的分页查询
/**上一页*/
function report_equp_backpage(){
	
	var nowpage= $('#report_page_nowpage')[0].innerHTML;
	var totalpage= $('#report_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_health_equ_report(backPageNo);
	}
	
	
}
/**下一页*/
function report_equp_nextpage(){
	var nowpage= $('#report_page_nowpage')[0].innerHTML;
	var totalpage= $('#report_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_health_equ_report(nextPageNo);
	}
}
//
//健康指数设施指数的查询按钮
function query_health_equ_report(val5){	
	
	var val1 = $('#condition1').val();//线路
	var val2 = $('#condition2').val();//车站
	var val3 = $('#condition3').val();//资产名称
	var val4 = $('#condition4').val();//健康等级
	
	var query_criteria={
			            "lineNo": val1, 
			            "stationName":val2, 
			            "equipmentname": val3, 
			            "healthIndex": val4, 
			            "pageNo": val5
									};
	onReport_Health_Query(query_criteria);
}
//健康指数下的设施指数的按钮查询操作
function onReport_Health_Query(query_criteria){
	//alert("query_criteria="+JSON.stringify(query_criteria));
  var query_post_json ={
        "page": {
            "pageCount": "10", 
            "pageNo": "1"
        }, 
        "query": {
            "component": "", 
            "endTime": "", 
            "lineNo": "", 
            "production_house": "", 
            "starTime": "", 
            "stationName": "", 
            "subsystemName": "", 
            "systemName": "",
            "healthLevel":""
        }
};

  
  
  if (query_criteria!=null)
  {
    if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
    
    if (query_criteria.equipmentname) {
      query_post_json.query.component = query_criteria.equipmentname;
    }
    
    
    if (query_criteria.lineNo) {
      query_post_json.query.lineNo = query_criteria.lineNo;
    }
    
     
      if (query_criteria.stationName) {
      query_post_json.query.stationName = query_criteria.stationName;
    }
      
     
     if (query_criteria.healthIndex) {
      query_post_json.query.healthLevel = query_criteria.healthIndex;
    }
     }
    
   
  var Url="/Rcm/Manage/getReportforms_Equipment_index.do";
  PostJSONQuery(Url,query_post_json,function(response){
		//alert("fen="+JSON.stringify(response));
	  $('#report_page_totalCount')[0].innerHTML = response["totalCount"];
	  $('#report_page_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = query_post_json.page.pageNo; 
	  $('#report_page_nowpage')[0].innerHTML = pageNo;
	   
	  var infoList=response.infoList;
	  
	  $('#query_result_table_body tr').remove();
	  
	   var healthLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	  for(var i=0;i<infoList.length;i++){
		
		  var page=pageNo;
			$("#query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+infoList[i].systemName+"</td>"+
			"<td>"+infoList[i].subsystemName+"</td>"+
			"<td >"+infoList[i].lineNo+"</td>"+
			"<td >"+infoList[i].stationName+"</td>"+
			"<td >"+infoList[i].component+"</td>"+
			"<td >"+infoList[i].locationId+"</td>"+
			"<td >"+infoList[i].componentDescription+"</td>"+
			"<td >"+healthLevel[infoList[i].healthLevel]+"</td>"+
			/*"<td >"+"<a href='javascript:showTend("+infoList[i].id+");'>趋势查看</a>"+"</td>"+*/
		"</tr>");
		}
	});

  }

function  infoList(id){
	
	
}




//----------------运营指数显示-------------------------------------------------------------------//
//运营指数里的分页查询
/**上一页*/
function report_backpage(){
	
	var nowpage= $('#operation_page_nowpage')[0].innerHTML;
	var totalpage= $('#operation_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_ope_operation(backPageNo);
	}
	
	
}
/**下一页*/
function report_nextpage(){
	var nowpage= $('#operation_page_nowpage')[0].innerHTML;
	var totalpage= $('#operation_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_ope_operation(nextPageNo);
	}
}
//
//运营指数的查询按钮
function query_ope_operation(val5){	
	//alert("ii");	
	var val1 = $('#operation_condition1').val();//lineNo
	var val2 = $('#operation_condition2').val();//健康等级
	var val3 = $('#start_time').val();//start_time
	var val4 = $('#end_time').val();//end_time

	var query_criteria={
						"lineNo":val1,
						"healthindex":val2,
						"startDate":val3,
						"endDate":val4,
						"pageNo":val5
						};
	onReport_operation_Query(query_criteria);
}
//运营指数的按钮查询操作
function onReport_operation_Query(query_criteria){
  var query_post_json = {
        "page": {
            "pageCount": "10",
            "pageNo": "1"
        },
        "query": {
            "lineNo":"",
            "healthindex":"",
            "startDate": "",
            "endDate": ""
        }
};

  //alert(query_criteria.healthindex+","+query_criteria.starTime);
  if (query_criteria!=null)
  {
    if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
    if (query_criteria.lineNo) {
       query_post_json.query.lineNo = query_criteria.lineNo;
      }
    if (query_criteria.healthindex) {
      query_post_json.query.healthindex = query_criteria.healthindex;
    }
    if (query_criteria.endDate) {
     query_post_json.query.endDate = query_criteria.endDate;
    }
    if (query_criteria.startDate) {
      query_post_json.query.startDate = query_criteria.startDate;
    }
  
   }
  var Url="/Rcm/Manage/getFormsOperateindexList.do";
  PostJSONQuery(Url,query_post_json,function(response){
		//alert("fenye="+JSON.stringify(response["totalCount"]));
	  $('#operation_page_totalCount')[0].innerHTML = response["totalCount"];
	  $('#operation_page_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = query_post_json.page.pageNo; 
	  $('#operation_page_nowpage')[0].innerHTML = pageNo;
	  var operateindexList=response.operateindexList;
	   var healthLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	  $('#operative_index_query_result_table_body tr').remove();
	  for(var i=0;i<operateindexList.length;i++){
		
		  var page=pageNo;
			$("#operative_index_query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+operateindexList[i].lineNo+"</td>"+
			"<td>"+healthLevel[operateindexList[i].healthindex]+"</td>"+
			"<td>"+operateindexList[i].leveloneCount+"</td>"+
			"<td>"+operateindexList[i].leveltwoCount+"</td>"+
			"<td>"+operateindexList[i].levelthreeCount+"</td>"+
			"<td>"+operateindexList[i].levelforeCount+"</td>"+
			"<td width='180px'>"+operateindexList[i].time+"</td>"+
		
		"</tr>");
		}
	});
}

//-------------------------------------维保通知单--------------------------------------------------------------//

/**上一页*/
function report_noti_backpage(){
	
	var nowpage= $('#operation_noti_page_nowpage')[0].innerHTML;
	var totalpage= $('#operation_noti_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_noti_operation(backPageNo);
	}
	
	
}
/**下一页*/
function report_noti_nextpage(){
	var nowpage= $('#operation_noti_page_nowpage')[0].innerHTML;
	var totalpage= $('#operation_noti_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_noti_operation(nextPageNo);
	}
}
//通知单按钮查询

function query_noti_operation(val8){	
	//alert("ii");	
	var val1 = $('#operation_condition1').val();//系统
	var val2 = $('#operation_condition2').val();//子系统
	var val3 = $('#operation_condition3').val();//线路
	var val4 = $('#operation_condition4').val();//车站
	var val5 = $('#operation_condition5').val();//资产名称
	var val6 = $('#start_time').val();//开始时间
	var val7 = $('#end_time').val();//结束时间

	var query_criteria={
						"systemName":val1,
						"subsystemName":val2,
						"lineNo":val3,
						"stationName":val4,
						"component":val5,
						"starTime":val6,
						"endTime":val7,
						"pageNo":val8
						};
	onReport_notify_Query(query_criteria);
}
//报表统计告警
function onReport_notify_Query(query_criteria){
  var query_post_json = {
        "page": {
            "pageCount": "10",
            "pageNo": "1"
        },
        "query": {
            "component":"",
            "endTime": "",
            "lineNo": "",
            "starTime": "",
            "stationName": "",
            "subsystemName": "",
            "systemName": ""
        }
};

  //alert(query_criteria.healthindex+","+query_criteria.starTime);
  if (query_criteria!=null)
  {
	 
     if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
   
     if (query_criteria.systemName) {
       query_post_json.query.systemName= query_criteria.systemName;
      }
      if (query_criteria.subsystemName) {
       query_post_json.query.subsystemName = query_criteria.subsystemName;
      }
    
     if (query_criteria.lineNo) {
       query_post_json.query.lineNo = query_criteria.lineNo;
      }
     if (query_criteria.stationName) {
       query_post_json.query.stationName = query_criteria.stationName;
      }
     if (query_criteria.component) {
       query_post_json.query.component = query_criteria.component;
      }
     if (query_criteria.starTime) {
      query_post_json.query.starTime= query_criteria.starTime;
    }
     if (query_criteria.endTime) {
     query_post_json.query.endTime = query_criteria.endTime;
    }
  
   }
  var Url="/Rcm/Manage/getReportforms_requisition.do";
 // alert("query_post_json"+JSON.stringify(query_post_json));
  PostJSONQuery(Url,query_post_json,function(response){
		//alert("mainte="+JSON.stringify(response));
	  $('#operation_noti_page_totalCount')[0].innerHTML = response["totalCount"];
	  $('#operation_noti_page_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = query_post_json.page.pageNo; 
	  $('#operation_noti_page_nowpage')[0].innerHTML = pageNo;

	  var maint_state={"0":"已下达","1":"处理中","2":"处理已完成"};
	  var requisitionList=response.requisitionList;
	   var healthLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	  $('#query_result_table_body tr').remove();
	  for(var i=0;i<requisitionList.length;i++){
		
		  var page=pageNo;
			$("#query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+requisitionList[i].systemName+"</td>"+
			"<td>"+requisitionList[i].subsystemName+"</td>"+
			"<td>"+requisitionList[i].lineNo+"</td>"+
			"<td>"+requisitionList[i].stationName+"</td>"+
			"<td>"+requisitionList[i].component+"</td>"+
			"<td>"+requisitionList[i].requisitionNo+"</td>"+
			"<td>"+maint_state[requisitionList[i].requisitionstatments]+"</td>"+
			"<td>"+(requisitionList[i].sendTime).substring(0,11)+"</td>"+
			"<td>"+requisitionList[i].maintainTime+"</td>"+
			"<td>"+requisitionList[i].maintainDepartment+"</td>"+
			"<td>"+requisitionList[i].maintainPerson+"</td>"+
		"</tr>");
		}
	});
}



//-----------------------------------设备履历表--------------------------------------------//
function onReport_resum_Query(query_criteria){
	var report_resum_query={
        "page": {
            "pageCount": "10", 
            "pageNo": "1"
        }, 
        "query": {
            "component":"", 
            "endTime": "", 
            "lineNo": "", 
            "production_house":"", 
            "starTime": "", 
            "stationName":"", 
            "subsystemName":"", 
            "systemName": ""
        }
};
	
	 if (query_criteria!=null)
  {
     if (query_criteria.pageNo) {
      report_resum_query.page.pageNo = query_criteria.pageNo;
    }
     if (query_criteria.systemName) {
       report_resum_query.query.systemName= query_criteria.systemName;
      }
      if (query_criteria.subsystemName) {
       report_resum_query.query.subsystemName = query_criteria.subsystemName;
      }
    
     if (query_criteria.lineNo) {
       report_resum_query.query.lineNo = query_criteria.lineNo;
      }
     if (query_criteria.stationName) {
       report_resum_query.query.stationName = query_criteria.stationName;
      }
     if (query_criteria.component) {
       report_resum_query.query.component = query_criteria.component;
      }
     if (query_criteria.starTime) {
      report_resum_query.query.starTime = query_criteria.starTime;
    }
     if (query_criteria.endTime) {
     report_resum_query.query.endTime = query_criteria.endTime;
    }
  
   }
	 var Url="/Rcm/Manage/getEquipment_record.do";
  PostJSONQuery(Url,report_resum_query,function(response){
		//alert("mainte="+JSON.stringify(response));
	  $('#equ_resum_totalCount')[0].innerHTML = response["totalCount"];
	  $('#equ_resum_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = report_resum_query.page.pageNo; 
	  $('#equ_resum_nowpage')[0].innerHTML = pageNo;

	  var resEquipment_recordList=response.resEquipment_recordList;
	  $('#query_result_table_body tr').remove();
	  for(var i=0;i<resEquipment_recordList.length;i++){
		
		  var page=pageNo;
			$("#query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+resEquipment_recordList[i].systemName+"</td>"+
			"<td>"+resEquipment_recordList[i].subsystemName+"</td>"+
			"<td>"+resEquipment_recordList[i].lineNo+"</td>"+
			"<td>"+resEquipment_recordList[i].stationName+"</td>"+
			"<td>"+resEquipment_recordList[i].component+"</td>"+
			"<td>"+resEquipment_recordList[i].componentId+"</td>"+
			"<td>"+resEquipment_recordList[i].locationId+"</td>"+
			"<td>"+resEquipment_recordList[i].modelstandard+"</td>"+
			"<td>"+(resEquipment_recordList[i].outTime).substring(0,11)+"</td>"+
			"<td>"+resEquipment_recordList[i].buyTime+"</td>"+
			"<td>"+resEquipment_recordList[i].production_house+"</td>"+
			"<td>"+resEquipment_recordList[i].production_coding+"</td>"+
		"</tr>");
		}
	});
	
	
} 

/**上一页*/
function report_resum_backpage(){
	
	var nowpage= $('#equ_resum_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_resum_operation(backPageNo);
	}
	
	
}
/**下一页*/
function report_resum_nextpage(){
	var nowpage= $('#equ_resum_nowpage')[0].innerHTML;
	var totalpage= $('#equ_resum_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_resum_operation(nextPageNo);
	}
}
//
//运营指数的查询按钮
function query_resum_operation(val9){	
	//alert("ii");	
	var val1 = $('#resum_condition1').val();//系统
	var val2 = $('#resum_condition2').val();//子系统
	var val3 = $('#resum_condition3').val();//线路
	var val4 = $('#resum_condition4').val();//车站
	var val5 = $('#resum_condition5').val();//资产名称
	var val6 = $('#resum_condition6').val();//生产厂家
	var val7 = $('#start_time').val();//start_time
	var val8 = $('#end_time').val();//end_time

	var query_criteria={
            			"systemName": val1,
            			"subsystemName":val2, 
            			"lineNo": val3, 
            			"stationName":val4, 
						"component": val5, 
           				"production_house":val6, 
           				"starTime": val7, 
            			"endTime":val8, 
            			"pageNo":val9
						};
	onReport_resum_Query(query_criteria);
}

//--------------------------------kpi模块----------------------------------------//
function  showKpiInfo(query_criteria){
	var req_kpi_json = {
        "page": {
            "pageCount": "10",
            "pageNo": "1"
        },
        "query": {
            "component": "",
            "endTime": "",
            "lineNo": "",
            "starTime": "",
            "stationName": "",
            "subSystemName": "",
            "systemName": ""
        }
};
	
	if(query_criteria!=null){
		if(query_criteria.pageNo){
			req_kpi_json.page.pageNo=query_criteria.pageNo;
		}
		if(query_criteria.systemName){
			req_kpi_json.query.systemName=query_criteria.systemName;
		}
		if(query_criteria.subSystemName){
			req_kpi_json.query.subSystemName=query_criteria.subSystemName;
		}
		if(query_criteria.lineNo){
			req_kpi_json.query.lineNo=query_criteria.lineNo;
		}
		if(query_criteria.stationName){
			req_kpi_json.query.stationName=query_criteria.stationName;
		}
		if(query_criteria.component){
			req_kpi_json.query.component=query_criteria.component;
		}
		if(query_criteria.starTime){
			req_kpi_json.query.starTime=query_criteria.starTime;
		}
		if(query_criteria.endTime){
			req_kpi_json.query.endTime=query_criteria.endTime;
		}
		
	}
	 var Url="/Rcm/Manage/getReportforms_KPI.do";
	PostJSONQuery(Url,req_kpi_json,function(response){
	//alert(JSON.stringify(response)); 
		var kpiList = response.infoList;
		$("#report_kpi_totalCount")[0].innerHTML=response["totalCount"];
		$("#report_kpi_totalpage")[0].innerHTML=response["totalPage"];
		 var pageNo = req_kpi_json.page.pageNo; 
	  	$('#report_kpi_nowpage')[0].innerHTML = pageNo;
	  	
	  	$('#query_result_table_body tr').remove();
	  	
	  for(var i=0;i<kpiList.length;i++){
		  var page=pageNo;
			$("#query_result_table_body").append(""+
			"<tr style='height:26px;'>"+
			"<td >"+(((page-1)*10)+1+i)+"</td>"+
			"<td width='100px'>"+kpiList[i].systemName+"</td>"+
			"<td width='100px'>"+kpiList[i].subsystemName+"</td>"+
			"<td>"+kpiList[i].lineNo+"</td>"+
			"<td width='60px'>"+kpiList[i].stationName+"</td>"+
			"<td>"+kpiList[i].component+"</td>"+
			"<td>"+kpiList[i].safety_production_days+"</td>"+
			"<td>"+kpiList[i].force_stop_days+"</td>"+
			"<td>"+kpiList[i].noplan_stop_days+"</td>"+
			"<td>"+kpiList[i].no_fault_days+"</td>"+
			"<td>"+kpiList[i].key_equipment_repair_time+"</td>"+
			"<td>"+kpiList[i].key_technology_repair_time+"</td>"+
			"<td>"+kpiList[i].physical_variance+"</td>"+
		    "</tr>");
		}
		
	});
	
	
}
//分页
function  report_kpi_backpage(){
	var nowpage= $('#report_kpi_nowpage')[0].innerHTML;
	var totalpage= $('#report_kpi_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		select_kpi_data(backPageNo);
	}
	
}

function  report_kpi_nextpage(){
	var nowpage= $('#report_kpi_nowpage')[0].innerHTML;
	var totalpage= $('#report_kpi_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		select_kpi_data(nextPageNo);
	}
	
}

//查询按钮
function select_kpi_data(val8){
	var val1 = $("#condition1").val();
	var val2 = $("#condition2").val();
	var val3 = $("#condition3").val();
	var val4 = $("#condition4").val();
	var val5 = $("#condition5").val();
	var val6 = $("#start_time").val();
	var val7 = $("#end_time").val();
	var query_criteria={
		"systemName":val1,
		"subSystemName":val2,
		"lineNo":val3,
		"stationName":val4,
		"component":val5,
		"starTime":val6,
		"endTime":val7,
		"pageNo":val8
		
	};
	showKpiInfo(query_criteria);
	
}

/**告警管理模块历史查询详情表*/
function onReport_equipment_Query_Details_alert(query_criteria){
	//alert("query_criteria="+JSON.stringify(query_criteria));
  var query_post_json = {
        "page": {
            "pageCount": "10",
            "pageNo": "1"
        },
        "query": {
            "component":"",
            "endTime": "",
            "lineNo": "",
            "starTime": "",
            "stationName": "",
            "subSystemName":"",
            "systemName": "",
            "warningType": "故障告警",
            "warningTypeLevel": ""
        }
};
	// alert(JSON.stringify(query_post_json));
  if (query_criteria!=null)
  {
    if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
    
    if (query_criteria.component) {
      query_post_json.query.component = query_criteria.component;
    }
    
    if (query_criteria.systemName) {
      query_post_json.query.systemName = query_criteria.systemName;
    }
    
    if (query_criteria.subSystemName) {
      query_post_json.query.subSystemName = query_criteria.subSystemName;
    }
    
    if (query_criteria.lineNo) {
      query_post_json.query.lineNo = query_criteria.lineNo;
    }
    
     
      if (query_criteria.stationName) {
      query_post_json.query.stationName = query_criteria.stationName;
    }
      
  
     
     if (query_criteria.warningTypeLevel) {
      query_post_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
    }
     
    if (query_criteria.starTime) {
      query_post_json.query.starTime = query_criteria.starTime;
    }
    
    if (query_criteria.endTime) {
     query_post_json.query.endTime = query_criteria.endTime;
    }
    
   }
   
  var Url="/Rcm/Manage/gethistoryWarningList.do";
  PostJSONQuery(Url,query_post_json,function(response){
		//alert("fenye="+JSON.stringify(response));
	 var historyList = response.historyList;
		//alert(JSON.stringify(operateindexList));
		 var pageNo = query_post_json.page.pageNo; 
	    $('#report_page_totalpage')[0].innerHTML = response["totalPage"];
	    $('#report_page_totalCount')[0].innerHTML = response["totalCount"];
	    
	    $('#report_page_nowpage')[0].innerHTML = pageNo;
	    var alertLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	    
	    $('#query_result_table_body tr').remove();

		for(var i=0;i<historyList.length;i++){
		var page=pageNo;
	   
		
		$("#query_result_table_body").append("<tr style='height:26px'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+historyList[i].systemName+"</td>"+
			"<td>"+historyList[i].subSystemName+"</td>"+
			"<td>"+historyList[i].lineNo+"</td>"+
			"<td>"+historyList[i].stationName+"</td>"+
			"<td>"+historyList[i].component+"</td>"+
			/*"<td>"+historyList[i].warninginfo+"</td>"+*/
			"<td scope='col' >"+
	  		"<span   onmouseover='showDetail(true,this);' onmouseout='showDetail(false,this);'>"+historyList[i].warninginfo.substring(1,8)+"..."+"</span>"+
	  		"<div class='detail_info'>"+
       			"<textarea style='height:150px; width:250px;'rows='6' cols='1'>"+historyList[i].warninginfo+
       			"</textarea>"+
    		"</div>"+
	  		"</td>" +
			"<td>"+alertLevel[historyList[i].warningTypeLevel]+"</td>"+
			"<td>"+historyList[i].warningstatments+"</td>"+
			"<td width='180px'>"+historyList[i].warningTime+"</td>"+
		"</tr>");
		}
	});

  }



/**预警历史统计详情表*********************告警管理模块*****************************************************/
function  showFultureReportInfo_Details_alert(query_criteria){
	var query_post_json = {
		        "page": {
		            "pageCount": "10",
		            "pageNo": "1"
		        },
		        "query": {
		            "component":"",
		            "endTime": "",
		            "lineNo": "",
		            "starTime": "",
		            "stationName": "",
		            "subSystemName":"",
		            "systemName": "",
		            "warningType": "故障预警",
		            "warningTypeLevel": ""
		        }
		};
	 if (query_criteria!=null)
	  {
	    if (query_criteria.pageNo) {
	      query_post_json.page.pageNo = query_criteria.pageNo;
	    }
	    
	    if (query_criteria.component) {
	      query_post_json.query.component = query_criteria.component;
	    }
	    
	    if (query_criteria.systemName) {
	      query_post_json.query.systemName = query_criteria.systemName;
	    }
	    
	    if (query_criteria.subSystemName) {
	      query_post_json.query.subSystemName = query_criteria.subSystemName;
	    }
	    
	    if (query_criteria.lineNo) {
	      query_post_json.query.lineNo = query_criteria.lineNo;
	    }
	    
	     
	      if (query_criteria.stationName) {
	      query_post_json.query.stationName = query_criteria.stationName;
	    }
	     if (query_criteria.warningTypeLevel) {
	      query_post_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
	    }
	     
	    if (query_criteria.starTime) {
	      query_post_json.query.starTime = query_criteria.starTime;
	    }
	    
	    if (query_criteria.endTime) {
	     query_post_json.query.endTime = query_criteria.endTime;
	    }
	    
	   }
	 var Url="/Rcm/Manage/gethistoryWarningList.do";
	PostJSONQuery(Url,query_post_json,function(response){
		var historyList = response.historyList;
		 var pageNo = query_post_json.page.pageNo; 
	    $('#report_pre_page_totalpage')[0].innerHTML = response["totalPage"];
	    $('#report_pre_page_totalCount')[0].innerHTML = response["totalCount"];
	    $('#report_pre_page_nowpage')[0].innerHTML = pageNo;
	    var alertLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	    $('#query_result_table_body tr').remove();
		for(var i=0;i<historyList.length;i++){
		var page=pageNo;
	   
		$("#query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+historyList[i].systemName+"</td>"+
			"<td>"+historyList[i].subSystemName+"</td>"+
			"<td>"+historyList[i].lineNo+"</td>"+
			"<td>"+historyList[i].stationName+"</td>"+
			"<td>"+historyList[i].component+"</td>"+
			/*"<td>"+historyList[i].warninginfo+"</td>"+*/
			"<td scope='col' >"+
	  		"<span   onmouseover='showDetail(true,this);' onmouseout='showDetail(false,this);'>"+historyList[i].warninginfo.substring(1,8)+"..."+"</span>"+
	  		"<div class='detail_info'>"+
       			"<textarea style='height:150px; width:250px;'rows='6' cols='1'>"+historyList[i].warninginfo+
       			"</textarea>"+
    		"</div>"+
	  		"</td>" +
			"<td>"+alertLevel[historyList[i].warningTypeLevel]+"</td>"+
			"<td>"+historyList[i].warningstatments+"</td>"+
			"<td>"+historyList[i].warningTime+"</td>"+
		"</tr>");
		}
	});
}
/************************************计划修规程始*******************************************/

function  planMaintence_program(query_criteria){
	var query_post_json = {
		        "page": {
		            "pageCount": "10",
		            "pageNo": "1"
		        },
		        "query": {
		        	"equipmentName": "",
		        	"systemName": "",
		        	"lineNO": "",
		        	"stationName": "",
		        	"warningTypeLevel": "",
		        	"starTime": "",
		            "endTime": ""
		        }
		};
	 if (query_criteria!=null)
	  {
	    if (query_criteria.pageNo) {
	      query_post_json.page.pageNo = query_criteria.pageNo;
	    }
	    
	    if (query_criteria.component) {
	      query_post_json.query.equipmentName = query_criteria.component;
	    }
	    
	    if (query_criteria.systemName) {
	      query_post_json.query.systemName = query_criteria.systemName;
	    }
	    
	    if (query_criteria.lineNo) {
		      query_post_json.query.lineNo = query_criteria.lineNo;
		    }
	    
	      if (query_criteria.stationName) {
	      query_post_json.query.stationName = query_criteria.stationName;
	    }
	      
	     if (query_criteria.warningTypeLevel) {
	      query_post_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
	    }
	     
	    if (query_criteria.starTime) {
	      query_post_json.query.starTime = query_criteria.starTime;
	    }
	    
	    if (query_criteria.endTime) {
	     query_post_json.query.endTime = query_criteria.endTime;
	    }
	    //alert(JSON.stringify(query_criteria));  检测入参是否正确
	   }
	 var Url="/Rcm/Manage/getmaintain_plan_table.do";
	PostJSONQuery(Url,query_post_json,function(response){
		var plan_tableList = response.plan_tableList;
		//alert(JSON.stringify(plan_tableList));
		 var pageNo = query_post_json.page.pageNo; 
	    $('#equ_resum_totalpage')[0].innerHTML = response["totalPage"];
	    $('#report_pre_page_totalCount')[0].innerHTML = response["totalCount"];
	    $('#equ_resum_nowpage')[0].innerHTML = pageNo;
	    $('#query_result_table_body tr').remove();
		for(var i=0;i<plan_tableList.length;i++){
		var page=pageNo;
		$("#query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+plan_tableList[i].equipmentId+"</td>"+
			"<td>"+plan_tableList[i].equipmentname+"</td>"+
			"<td>"+plan_tableList[i].systemName+"</td>"+
			"<td>"+plan_tableList[i].lineNo+"</td>"+
			"<td>"+plan_tableList[i].stationName+"</td>"+
			"<td>"+plan_tableList[i].warningTypeLevel+"</td>"+
			"<td scope='col' >"+
	  		"<span   onmouseover='showDetail(true,this);' onMouseOut='showDetail(false,this);'>"+plan_tableList[i].maintaininfo.substring(0,3)+"..."+"</span>"+
	  		"<div class='detail_info'>"+
       			"<textarea style='height: 150px; width:250px;'rows='6' cols='1'>"+plan_tableList[i].maintaininfo+
       			"</textarea>"+
    		"</div>"+
			"<td>"+plan_tableList[i].lastTime_maintainDate+"</td>"+
			"<td>"+plan_tableList[i].productDate+"</td>"+
			"<td>"+plan_tableList[i].maintain_period+"</td>"+
		"</tr>");
		}
	});
}

//查询按钮
function select_planMaintencePrograme_data(val8){
	var val1 = $("#condition1").val();
	var val2 = $("#condition2").val();
	var val3 = $("#condition3").val();
	var val4 = $("#condition4").val();
	var val5 = $("#condition5").val();
	var val6 = $("#start_time").val();
	var val7 = $("#end_time").val();
	var query_criteria={
			"equipmentName": val1,
        	"systemName": val2,
        	"lineNO": val3,
        	"stationName":val4,
        	"warningTypeLevel":val5,
        	"starTime": val6,
            "endTime": val7,
            "pageNo":val8
		
	};
	planMaintence_program(query_criteria);
	
}
//分页--------------------------计划修预警------------------------------//
function  report_planMaintence_backpage(){
	var nowpage= $('#equ_resum_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		select_planMaintencePrograme_data(backPageNo);
	}
	
}

function  report_planMaintence_nextpage(){
	var nowpage= $('#equ_resum_nowpage')[0].innerHTML;
	var totalpage= $('#equ_resum_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		select_planMaintencePrograme_data(nextPageNo);
	}
	
}

/************************************计划修规程终*******************************************/
