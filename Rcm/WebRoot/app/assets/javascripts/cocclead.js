

//----------------------------------运营指标分析-----------------------------------------------------------//










//--------------------------维修统计指标-------------------------------------------------------------//






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
/**Test***/
var char2_image=[{"index_":[90,88,98,79,83,99,86,87,91,93],"name":"健康指数统计","date":["2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013"]}];
function getchar2imageData(domName){
      var result = [];
      char2_image.forEach(
        function (element, index, array) {
          var target_per_day = {};
          target_per_day.key = element.name;
          target_per_day.values = [];
          element.index_.forEach(
            function(elementCount, indexCount, arrayCount){
              target_per_day.values[indexCount] = {x:char2_image[0].date[indexCount] ,y:elementCount };
              if (isNaN(target_per_day.values[indexCount].y))
              {
                target_per_day.values[indexCount].y = 0;
              }
            }
          );
          result[index] = target_per_day;
        }
      );
    
    	  init_lineChart(domName,result);
}

function init_lineChart(domName,data)
{
  nv.addGraph(function() {
    var chart = nv.models.multiBarChart();

   //chart.showControls(false);
    //chart.stacked(true);

    chart.xAxis
      .axisLabel('年份(Y)')
      .tickFormat(d3.format(',f'));

    chart.yAxis
      .axisLabel('健康指数(index)') 
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

