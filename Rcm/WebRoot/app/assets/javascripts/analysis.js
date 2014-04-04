



/**上一页*/
function backpage(){
	
	var nowpage= $('#analysis_page_nowpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		queryRecord(backPageNo);
	}
	
	
}
/**下一页*/
function nextpage(){
	var nowpage= $('#analysis_page_nowpage')[0].innerHTML;
	var totalpage= $('#analysis_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		queryRecord(nextPageNo);
	}
}


//
function OnAnalysisQuery(query_criteria){
	
  var alert_query_json = {
      "page":{
        "pageCount":"10",
        "pageNo":"1"
      },     
      "query":{
        "endDate":"2099-1-1",
        "equipmentname":"",
        "lineNo":"",
        "startDate":"1990-1-1",
        "stationName":"",
        "warningTypeLevel":"",
        "systemName":""
      }
  };
  
  if (query_criteria)
  {
    if (query_criteria.pageNo) {
      alert_query_json.page.pageNo = query_criteria.pageNo;
    }
    if (query_criteria.endDate) {
      alert_query_json.query.endDate = query_criteria.endDate;
    }
    if (query_criteria.startDate) {
      alert_query_json.query.startDate = query_criteria.startDate;
    }
    if (query_criteria.equipmentName) {
        alert_query_json.query.equipmentname = query_criteria.equipmentName;
      }
    if (query_criteria.lineNo) {
        alert_query_json.query.lineNo = query_criteria.lineNo;
      }
    if (query_criteria.stationName) {
        alert_query_json.query.stationName = query_criteria.stationName;
      }
    if (query_criteria.warningTypeLevel) {
        alert_query_json.query.warningTypeLevel = query_criteria.warningTypeLevel;
      }
    if (query_criteria.systemName) {
        alert_query_json.query.systemName = query_criteria.systemName;
      }
    
  }
  var Url="/Rcm/Manage/getWaringList.do";
  PostJSONQuery(Url,alert_query_json,function(response){
	 //分页的赋值方法
	  //alert(JSON.stringify(response));
	  $('#alert_count')[0].innerHTML = response["totalCount"];
	  $('#analysis_page_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = alert_query_json.page.pageNo; 
	  $('#analysis_page_nowpage')[0].innerHTML = pageNo;
	   
	  var warningList=response.warningList;
	  $('#query_result_table_body tr').remove();
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
		  var status={
				  	"4": "<img src='../../images/none.gif' />", 
				  	"3": "<img src='../../images/blue.gif' />", 
				  	"2": "<img src='../../images/yellow.gif' />", 
				  	"1": "<img src='../../images/red.gif' />"
					};
		  var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
		  var page=pageNo;
		var leve=warningList[i]["warningTypeLevel"];
		
		 var onmouse="<tr  style='height:26px' name='show' >";
		  $('#query_result_table_body').append("" +
				  onmouse +
		  		"<td ><div>"+status[leve]+"</div></td>" +
		  		"<td>"+(((page-1)*10)+1+i)+"</td >" +
		  		"<td>"+warningList[i]["equipmentId"]+"</td >" +
		  		"<td>"+warningList[i]["equipmentname"]+"</td >" +
		  		"<td>"+warningList[i]["systemName"]+"</td >" +
		  		"<td>"+warningList[i]["stationName"]+"</td >" +
		  		"<td>"+warningList[i]["lineNo"]+"</td >" +
		  		"<td>"+warningtLevel[leve]+"</th>" +
		  		"<td>"+warningList[i]["warninginfo"].substring(0,5)+"..."+"</td >" +
		  		"<td>"+warningList[i]["warningDate"]+"</td >" +
		  		"<td><a href='javascript:getImgUrl("+"\""+warningList[i]["id"]+"\""+");'>在线视图</a></td >" +
		  		"<td><a href='#tend' onclick='getReqData("+"\""+warningList[i]["equipmentId"]+"\""+");'>趋势分析</a></td >" +
		  		"</tr>");
	  } 
	  //初始化显示故障视图
	  getImgUrl(warningList[0].id);
	  getReqData(warningList[0]["equipmentId"]);
    });  
}

//查询按钮
function queryRecord(val8){	
	
	var val1 = $('#condition1').val();//equipmentName
	var val2 = $('#condition2').val();//systemname
	var val3 = $('#condition3').val();//lineNo
	var val4 = $('#condition4').val();//station
	var val5 = $('#condition5').val();//warningLivel
	var val6 = $('#condition6').val();//startDate
	var val7 = $('#condition7').val();//endDate
	

	var query_criteria={
						"systemName":val2,
						"lineNo":val3,
						"stationName":val4,
						"equipmentName":val1,
						"warningTypeLevel":val5,
						"startDate":val6,
						"endDate":val7,
						"pageNo":val8
						};
	OnAnalysisQuery(query_criteria);
}


//健康指数下的运营指数的显示-----------------------------------------------------------------------------------//

function  showOperationIndex(){
	var query_post_json={
        "page": {
            "pageCount": "10", 
            "pageNo": "1"
        }, 
        "query": {
            "endDate":"", 
            "lineNo": "", 
            "startDate": ""
        }
}
	var Url="/Rcm/Manage/getOperateindexList.do";
	PostJSONQuery(Url,query_post_json,function(response){
		var operateindexList = response.operateindexList;
		//alert(JSON.stringify(operateindexList[0].lineNo));
		 var pageNo = query_post_json.page.pageNo; 
	    $('#operation_page_totalpage')[0].innerHTML = response["totalPage"];
	    $('#operation_page_totalCount')[0].innerHTML = response["totalCount"];
	    
	    $('#operation_page_nowpage')[0].innerHTML = pageNo;
	    var healthLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
		for(var i=0;i<operateindexList.length;i++){
		var page=pageNo;
	   
	  //$('#query_result_table_body tr').remove();
		
		$("#operative_index_query_result_table_body").append("<tr style='height:26px'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+operateindexList[i].lineNo+"</td>"+
			"<td>"+healthLevel[operateindexList[i].healthindex]+"</td>"+
			"<td >"+operateindexList[i].leveloneCount+"</td>"+
			"<td >"+operateindexList[i].leveltwoCount+"</td>"+
			"<td >"+operateindexList[i].levelthreeCount+"</td>"+
			"<td >"+operateindexList[i].levelforeCount+"</td>"+
			"<td width='180px'>"+operateindexList[i].time+"</td>"+
		
		"</tr>");
		}
	});
	
	
}
//运营指数里的分页查询
/**上一页*/
function operation_backpage(){
	
	var nowpage= $('#operation_page_nowpage')[0].innerHTML;
	var totalpage= $('#operation_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_operation(backPageNo);
	}
	
	
}
/**下一页*/
function operation_nextpage(){
	var nowpage= $('#operation_page_nowpage')[0].innerHTML;
	var totalpage= $('#operation_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_operation(nextPageNo);
	}
}
//
//运营指数的查询按钮
function query_operation(val4){	
	
	var val1 = $('#operation_condition1').val();//lineNo
	var val2 = $('#start_time').val();//start_time
	var val3 = $('#end_time').val();//end_time

	var query_criteria={
						"lineNo":val1,
						"startDate":val2,
						"endDate":val3,
						"pageNo":val4
						};
	onAnalysis_operation_Query(query_criteria);
}
//运营指数的按钮查询操作
function onAnalysis_operation_Query(query_criteria){
	//alert("query_criteria="+JSON.stringify(query_criteria));
  var query_post_json = {
        "page": {
            "pageCount": "10", 
            "pageNo":"1"
        }, 
        "query": {
            "endDate":"", 
            "lineNo": "", 
            "startDate":""
        }
};

  
  
  if (query_criteria)
  {
    if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
    if (query_criteria.endDate) {
     query_post_json.query.endDate = query_criteria.endDate;
    }
    if (query_criteria.startDate) {
      query_post_json.query.startDate = query_criteria.startDate;
    }
   
    if (query_criteria.lineNo) {
       query_post_json.query.lineNo = query_criteria.lineNo;
      }
    var Url="/Rcm/Manage/getOperateindexList.do";
  PostJSONQuery(query_post_json,function(response){
		//alert("fenye="+JSON.stringify(response));
	  $('#operation_page_totalCount')[0].innerHTML = response["totalCount"];
	  $('#operation_page_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = query_post_json.page.pageNo; 
	  $('#operation_page_nowpage')[0].innerHTML = pageNo;
	   
	  var operateindexList=response.operateindexList;
	  $('#operative_index_query_result_table_body tr').remove();
	   var healthLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
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
  }


//实件分析下的设施指数-----------------------------------------------------------------------------------------//

//设施指数里的分页查询
/**上一页*/
function equipment_backpage(){
	
	var nowpage= $('#equipment_page_nowpage')[0].innerHTML;
	var totalpage= $('#equipment_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_equipment(backPageNo);
	}
	
	
}
/**下一页*/
function equipment_nextpage(){
	var nowpage= $('#equipment_page_nowpage')[0].innerHTML;
	var totalpage= $('#equipment_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_equipment(nextPageNo);
	}
}
//
//设施指数的查询按钮
function query_equipment(val9){	
	
	var val1 = $('#equipment_condition1').val();//资产名称
	var val2 = $('#equipment_condition2').val();//所属系统
	var val3 = $('#equipment_condition3').val();//线路
	var val4 = $('#equipment_condition4').val();//车站
	var val5 = $('#equipment_condition5').val();//状态
	var val6 = $('#equipment_condition6').val();//健康指数
	var val7 = $('#equipment_start_time').val();//开始时间
	var val8 = $('#equipment_end_time').val();//结束时间
	var query_criteria={
			            "equipmentname": val1, 
			            "systemName": val2,
			            "lineNo": val3, 
			            "stationName":val4, 
			            "statments": val5, 
			            "healthIndex": val6, 
			            "startDate": val7, 
			            "endDate":val8, 
			            "pageNo": val9
									};
	onAnalysis_equipment_Query(query_criteria);
}
//设施指数的按钮查询操作
function onAnalysis_equipment_Query(query_criteria){
	//alert("query_criteria="+JSON.stringify(query_criteria));
 var query_post_json={
        "page": {
            "pageCount": "10", 
            "pageNo": "1"
        }, 
        "query": {
            "endDate": "", 
            "equipmentname": "", 
            "healthIndex": "", 
            "lineNo": "10", 
            "startDate": "", 
            "stationName": "", 
            "statments": "", 
            "systemName": ""
        }
};

  
  
  if (query_criteria!=null)
  {
    if (query_criteria.pageNo) {
      query_post_json.page.pageNo = query_criteria.pageNo;
    }
    
    if (query_criteria.equipmentname) {
      query_post_json.query.equipmentname = query_criteria.equipmentname;
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
      
     if (query_criteria.statments) {
      query_post_json.query.statments = query_criteria.statments;
    }
     
     if (query_criteria.healthIndex) {
      query_post_json.query.healthIndex = query_criteria.healthIndex;
    }
     
    if (query_criteria.startDate) {
      query_post_json.query.startDate = query_criteria.startDate;
    }
    
    if (query_criteria.endDate) {
     query_post_json.query.endDate = query_criteria.endDate;
    }
    }
   
   
  var Url="/Rcm/Manage/getChattelanalyseList.do";
  PostJSONQuery(Url,query_post_json,function(response){
		//alert("fenye="+JSON.stringify(response));
	  $('#equipment_page_totalCount')[0].innerHTML = response["totalCount"];
	  $('#equipment_page_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = query_post_json.page.pageNo; 
	  $('#equipment_page_nowpage')[0].innerHTML = pageNo;
	   
	  var operateindexList=response.chattelanalyseList;
	  $('#facility_index_query_result_table_body tr').remove();
	   var healthLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
	  for(var i=0;i<operateindexList.length;i++){
		
		  var page=pageNo;
			$("#facility_index_query_result_table_body").append("<tr style='height:26px;'>"+
			"<td>"+(((page-1)*10)+1+i)+"</td>"+
			"<td>"+operateindexList[i].componentId+"</td>"+
			"<td>"+operateindexList[i].equipmentname+"</td>"+
			"<td >"+operateindexList[i].systemName+"</td>"+
			"<td >"+operateindexList[i].lineNo+"</td>"+
			"<td >"+operateindexList[i].state+"</td>"+
			"<td >"+operateindexList[i].woringCount+"</td>"+
			"<td width='180px'>"+operateindexList[i].time+"</td>"+
			"<td >"+healthLevel[operateindexList[i].healthIndex]+"</td>"+
		"</tr>");
		}
	});

  }
//----------------------------------趋势分析统计表--------------------------------------------------//
//获得点代码  形参设备id
function   getReqData(id){
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


//-----------------------------------在途视图-------------------------------------------------------------//
function   getImgUrl(id){
	var faultImg=["../../assets/images/Snap1.jpg","../../assets/images/Snap2.jpg","../../assets/images/Snap3.jpg","../../assets/images/Snap4.jpg"];
	var inId=parseInt(id);
	var randomNum=(inId % 4);
	$("#onLineView")[0].src=faultImg[randomNum];
	
	
}

//显示实例分析的故障视图图例，通过使用arningId % 4   mod 随机选取4张图例中的一张显示
function findFaultView(){
	
	
}







