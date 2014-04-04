var _component_couple_back=null;
var _lineNo_couple_back=null;
var _faulttype_couple_back=null;
/***维修反馈表格详情单***/
function detailsOfback_ticket(id){
	var notify_query_json = {"id": id};
	var Url="/Rcm/Manage/getMaintainDescById.do";
	PostJSONQuery(Url,notify_query_json,function(response){
		 var attr=response.opinions.split("||");
		 var warning_Level_info={
				 "1":"<span style='color:red'>一级告警</span>",
				 "2":"<span style='color:#f6b52c'>二级告警</span>",
				 "3":"<span style='color:blue'>三级告警</span>",
				 "4":"<span style='color:green'>四级告警</span>"
					 };
		
		 $("#maintainID").html(response.maintenceCode);
		 $("#lineNo").html(response.lineNo);
		 $("#stationName").html(response.stationName);
		 $("#equipmentId").html(response.componentId);
		 $("#equipmentName").html(response.componentName);
		 $("#systemName").html(response.systemName);
		 $("#equipmentDescription").html(response.equipmentDescription);
		 $("#warningLevel").html(warning_Level_info[response.warningTypeLevel]);//
		 $("#warningType").html(response.warningType);
		 $("#warningDate").html(response.warningDate);
		 $("#warningInfo").html(response.warningInfo);
		 $("#faultCause").html(response.faultCause);
		 $("#option1").html(attr[0]);
		 $("#influence").html(response.faultImpact);
		 $("#option2").html(attr[1]);
		 $("#maintenancePolicy").html(response.maintencePolicy);
		 $("#option3").html(attr[2]);
		 $("#maintainInfo").html(response.maintainInfo);
		 $("#changeEquipment").html(response.changeEquipment);
		 $("#maintainResult").html(response.maintainResult);
		 
		 getImgUrl(response.componentId);
		  _component_couple_back=response.componentName;
		  _lineNo_couple_back=response.lineNo;
		  _faulttype_couple_back=response.warningType;
		  $("#profession_name").html(response.componentName);
		 getMaintain_couple_back(1);
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
		$("#mfmp").slideDown();
	}else if(strof=="02.24"){//高压开关
		$("#onLineImg")[0].src=faultImg[0]; // TODO 触网和开关没有在途试图
	}else if(strof=="14.101"){//触网
		$("#onLineImg")[0].src=faultImg[0];
	}else if(strof=="13.100"){//轨道
		$("#onLineImg")[0].src=faultImg[3];
	}else if(strof=="16.300"){//桥梁
		$("#onLineImg")[0].src=faultImg[1];
	}
	
	
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
		  for(var i=0;i<informList.length;i++){
			  $("#query_result_table_body_getMaintain_couple_back").append(""+
					"<tr style='height:auto'>" +
					"<td >"+informList[i].repairsDate.substring(0,11)+"</td>" +
					"<td >"+informList[i].lineNo+"号线</td>" +
					"<td >"+informList[i].stationName+"</td>" +
					"<td >"+'告警内容'+"</td>" +
					"<td >"+'维修策略'+"</td>" +
					"<td >"+informList[i].maintainInfo+"</td>" +
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
//----------------------------------趋势分析统计表--------------------------------------------------//
//获得点代码  形参设备id
function   getReqData(id){
	//TODO
	
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





