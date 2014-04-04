
//-------------------------------------显示记录-----------------------------------------------------//

//----------------------------------运营指标分析-----------------------------------------------------------//
var month={"1":"一月","2":"二月","3":"三月","4":"四月","5":"五月","6":"六月","7":"七月","8":"八月","9":"九月","10":"十月","11":"十一月","12":"十二月"};
function  showOperationTarget(){
	var query_target_json={
				"page":{
					"pageCount":"10",
					"pageNo":"1"
						},
			"reqString":"getAll"
				};
	 var Url="/Rcm/Manage/getOperateStatistics.do";
	PostJSONQuery(Url,query_target_json,function(response){
	  $('#tar_ope_count')[0].innerHTML = response["totalCount"];
	  $('#ope_page_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = query_target_json.page.pageNo; 
	  $('#ope_page_nowpage')[0].innerHTML = pageNo;
	   
	  var operateStatisticsList=response.operateStatisticsList;
	  $('#query_result_table_body tr').remove();
	  for(var i=0;i<operateStatisticsList.length;i++){
		
		var page=pageNo;
		
		 var onmouse="<tr  style='height:26px' name='show' >";
		  $('#query_result_table_body').append("" +
				  onmouse +
		  		"<td   >"+(((page-1)*10)+1+i)+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["lineNo"]+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["safetyDays"]+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["offstreamDays"]+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["noPlanoffstreamDays"]+"</td >" +
		  		"<td   >"+month[operateStatisticsList[i]["month"]]+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["updateDay"]+"</th>" +
		  		"</tr>");
	  } 
    });  

		
		
		
		
	
	
}
/**上一页*/
function targets_backpage(){
	
	var nowpage= $('#ope_page_nowpage')[0].innerHTML;
	var totalpage= $('#ope_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		queryTarget(backPageNo);
	}
	
	
}
/**下一页*/
function targets_nextpage(){

	var nowpage= $('#ope_page_nowpage')[0].innerHTML;
	var totalpage= $('#ope_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		queryTarget(nextPageNo);
	}
}



function OnTargetQuery(query_criteria){
	
  var targets_query_json ={
        "page": {
            "pageCount": "10",
            "pageNo": "1"
        },
        "reqString": "getAll"
};
  
  if(query_criteria.pageNo){
	  //alert(query_criteria.pageNo);
	  targets_query_json.page.pageNo=query_criteria.pageNo;
  }
  var Url="/Rcm/Manage/getOperateStatistics.do";
  PostJSONQuery(Url,targets_query_json,function(response){
	 	//alert(response["totalCount"]);
	  $('#tar_ope_count')[0].innerHTML = response["totalCount"];
	  $('#ope_page_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = targets_query_json.page.pageNo; 
	  $('#ope_page_nowpage')[0].innerHTML = pageNo;
	   
	   var operateStatisticsList=response.operateStatisticsList;
	   //alert("rr"+operateStatisticsList.length);
	  $('#query_result_table_body tr').remove();
	  for(var i=0;i<operateStatisticsList.length ;i++){
		  var page=pageNo;
		 var onmouse="<tr  style='height:26px' name='show' >";
		  $('#query_result_table_body').append("" +
				  onmouse +
		  		"<td   >"+(((page-1)*10)+1+i)+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["lineNo"]+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["safetyDays"]+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["offstreamDays"]+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["noPlanoffstreamDays"]+"</td >" +
		  		"<td   >"+month[operateStatisticsList[i]["month"]]+"</td >" +
		  		"<td   >"+operateStatisticsList[i]["updateDay"]+"</th>" +
		  		"</tr>");
	  } 
    });  
}

//查询按钮
function queryTarget(val1){	

	var query_criteria={
						"pageNo":val1
						};
	OnTargetQuery(query_criteria);
}


//---------------------test
function test(){
	
var json={"a":1,"b":2,"c":3};
alert(targets_json.janury.lineNo);
}
//--------------------------维修统计指标-------------------------------------------------------------//

/**上一页*/
function targets_mainten_backpage(){
	
	var nowpage= $('#ope_mainten_nowpage')[0].innerHTML;
	var totalpage= $('#ope_mainten_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		query_mainten_Target(backPageNo);
	}
	
	
}
/**下一页*/
function targets_mainten_nextpage(){

	var nowpage= $('#ope_mainten_nowpage')[0].innerHTML;
	var totalpage= $('#ope_mainten_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		query_mainten_Target(nextPageNo);
	}
}



function OnTargetMaintenQuery(query_criteria){
	
  var targets_query_json ={
        "page": {
            "pageCount": "10", 
            "pageNo": "1"
        }, 
        "reqString": "getAll"
};
  if(query_criteria!=null){
	  
  if(query_criteria.pageNo){
	  //alert(query_criteria.pageNo);
	  targets_query_json.page.pageNo=query_criteria.pageNo;
  }
  }
  
  var Url="/Rcm/Manage/getMaintainStatistics.do";
  PostJSONQuery(Url,targets_query_json,function(response){
	 	//alert(JSON.stringify(response));
	  $('#tar_mainten_count')[0].innerHTML = response["totalCount"];
	  $('#ope_mainten_totalpage')[0].innerHTML = response["totalPage"];
	  var pageNo = targets_query_json.page.pageNo; 
	  $('#ope_mainten_nowpage')[0].innerHTML = pageNo;
	   
	   var maintainStatisticsList=response.maintainStatisticsList;
	   
	  $('#query_result_table_body tr').remove();
	  for(var i=0;i<maintainStatisticsList.length;i++){
		  var page=pageNo;
		 var onmouse="<tr  style='height:26px' name='show' >";
		  $('#query_result_table_body').append("" +
				  onmouse +
		  		"<td   >"+(((page-1)*10)+1+i)+"</td >" +
		  		"<td   >"+maintainStatisticsList[i]["systemName"]+"</td >" +
		  		"<td   >"+maintainStatisticsList[i]["equipmentName"]+"</td >" +
		  		"<td   >"+maintainStatisticsList[i]["noFaultDays"]+"</td >" +
		  		"<td   >"+maintainStatisticsList[i]["statisticsDays"]+"</td >" +
		  		"<td   >"+maintainStatisticsList[i]["statisticsInterval"]+"</th>" +
		  		"<td   >"+maintainStatisticsList[i]["realityMistiming"]+"</th>" +
		  		"<td   >"+month[maintainStatisticsList[i]["month"]]+"</td >" +
		  		"<td   >"+maintainStatisticsList[i]["updateDay"]+"</th>" +
		  		"</tr>");
	  } 
    });  
}

//查询按钮
function query_mainten_Target(val1){	

	var query_criteria={
						"pageNo":val1
						};
	OnTargetMaintenQuery(query_criteria);
}
//----------------------------------统计图------------------------------------------------//

//获取统计图的数据
function MultiBarChangeEvent(lineNo,domName){
	//alert("oooo="+domName+",lineNo="+lineNo);
	getMultiBarChartData(lineNo,domName);
	
	
}

function TendLineChangeEvent(lineNo,domName){
	getMultiBarChartData(lineNo,domName);
	
	
}
function getMultiBarChartData(lineNo,domName){
	var query_chart_data={ "lineNo":lineNo};
	 var Url="/Rcm/Manage/getoneLineOperateStatistics.do";
	PostJSONQuery(Url,query_chart_data,
    function(response){
      var result = [];
	//alert("data="+JSON.stringify(response));
      response.infoList.forEach(
        function (element, index, array) {
          var target_per_day = {};
          target_per_day.key = element.name;
          target_per_day.values = [];
          element.days.forEach(
            function(elementCount, indexCount, arrayCount){
              target_per_day.values[indexCount] = {x: indexCount + 1,y: parseInt(elementCount)};
              if (isNaN(target_per_day.values[indexCount].y))
              {
                target_per_day.values[indexCount].y = 0;
              }
            }
          );

          result[index] = target_per_day;
          console.log(result);
		//alert("result="+ result[0].values[1].x +","+ result[0].values[1].y);
        }
      );
          //alert("result="+ JSON.stringify(result));
      if(domName=="#chart1 svg"){
		init_MultiBarChart(domName,result);
      }else if(domName=="#chart2 svg"){
    	  init_lineChart(domName,result);
      }
    });
  
	
}
//条形图

function init_MultiBarChart(domName,data)
{
  nv.addGraph(function() {
    var chart = nv.models.multiBarChart();

    chart.showControls(false);
    //chart.stacked(true);

     chart.xAxis
      .axisLabel('时间(月)');
      
    	  

    chart.yAxis
      .axisLabel('天数(d)')
      .tickFormat(d3.format(',.1f'));

	
	  
    d3.select(domName)
      .datum(data)
      .transition().duration(500).call(chart);

    nv.utils.windowResize(chart.update);

    return chart;
  });

}
//曲线图

function init_lineChart(domName,data)
{
  nv.addGraph(function() {
    var chart = nv.models.lineChart();

   //chart.showControls(false);
    //chart.stacked(true);

    chart.xAxis
      .axisLabel('月份(m)')
      .tickFormat(d3.format(',f'));

    chart.yAxis
      .axisLabel('天数(d)') 
      .tickFormat(d3.format(',.1f'));

    d3.select(domName)
      .datum(data)
      .transition().duration(500).call(chart);

    nv.utils.windowResize(chart.update);

    return chart;
  });

}

//----------------------------维修统计指标统计图--------------------------------------------//

function getMaintenceCountTargetData(value,domName){
	
	var maintence_query_data={"systemName": value};
	 var Url="/Rcm/Manage/getOneSysmaintai.do";
	PostJSONQuery(Url,maintence_query_data,
    function(response){
      var result = [];
            	
	//alert("data="+JSON.stringify(response));
      response.infoList.forEach(
        function (element, index, array) {
          var target_per_day = {};
          target_per_day.key = element.name;
          target_per_day.values = [];
          element.days.forEach(
            function(elementCount, indexCount, d){
            	
              target_per_day.values[indexCount] = {x:indexCount+1,y: parseInt(elementCount)};
              if (isNaN(target_per_day.values[indexCount].y))
              {
                target_per_day.values[indexCount].y = 0;
              }
            }
          );
			response.updateTime.forEach(function(element,index){
				target_per_day.values[index].x=element;
				
			});
			
          result[index] = target_per_day;
		//alert("result="+ result[0].values[1].x +","+ result[0].values[1].y);
        }
      );
          //alert("result="+ JSON.stringify(result));
      init_cumulativeLineChart(domName,result);
    });
	
}
//维修统计指标分析图
function init_cumulativeLineChart(domName,data)
{
  nv.addGraph(function() {
    var chart = nv.models.lineWithFocusChart();

    // chart.showControls(false);
    //chart.stacked(true);

    chart.xAxis
      .axisLabel('时间(t)')
      .tickFormat(function(d) {
        return d3.time.format('%x')(new Date(d));
      });
	chart.x2Axis
      .axisLabel('时间(t)')
      .tickFormat(function(d) {
        return d3.time.format('%x')(new Date(d));
      });

   chart.yAxis
      .axisLabel('天数')
      .tickFormat(d3.format('.f'));
    chart.y2Axis
         .tickFormat(d3.format(',.f'));
    d3.select(domName)
      .datum(data)
      .transition().duration(500).call(chart);

    nv.utils.windowResize(chart.update);

    return chart;
  });

}
//*****************告警数量分析图*************************
function init_warningNumLineChart(domName,data)
{
  nv.addGraph(function() {
    var chart = nv.models.multiBarChart();

    // chart.showControls(false);
    //chart.stacked(true);

    chart.xAxis
      .axisLabel('告警状态')
      .tickFormat('');

   chart.yAxis
      .axisLabel('告警数量(条)')
      .tickFormat(d3.format('/d'));
   
    d3.select(domName)
      .datum(data)
      .transition().duration(500).call(chart);

    nv.utils.windowResize(chart.update);

    return chart;
  });

}
//*******************************************
//获得直方图的告警数量
	function getRateOfWarning(){
		var url_="/Rcm/Manage/getDifNumOfWaring.do";
		var reqjson={"pageNo":1,"pageCount":10};
		PostJSONQuery(url_, reqjson, function(response){
			//{"warningsNoDeal":3,"warningDealing":11,"waaringDealed":2}
			//console.log(JSON.stringify(response));
			var data=[{"key":"未处理","values":[{"x":.3,"y":response.warningsNoDeal}]},{"key":"确认中","values":[{"x":.6,"y":response.warningDealing}]},{"key":"已处理","values":[{"x":.9,"y":response.waaringDealed}]}];
      	init_warningNumLineChart("#chart1 svg",data);
		});
		
		
	}