function showdown(){
		$("#showDownimage").slideUp("slow");
		$("#showUpimage").slideDown("slow");
		$("#nextimage").slideDown("slow");
}

function showup(){
	$("#showDownimage").slideDown("slow");
	$("#showUpimage").slideUp("slow");
	$("#nextimage").slideUp("slow");
}

function showXY(e){
		$("#showImage2").slideDown("slow");
		//$("#cebianlan").slideUp("slow");
}
function cleartext(){
		$("#showImage2").slideUp("slow");
		//$("#cebianlan").slideDown("slow");
}

/**显示需要cocc处理的告警***/
function OnEmergePageQuery_emerge1(pageNo){
  var alert_query_json = {
	  		"page":{"pageCount":"3",
				    "pageNo":pageNo},
	        "statments":"0"};
  
  var Url="/Rcm/Manage/getWaringListBystatments.do";
     PostJSONQuery(Url,alert_query_json,function(response){
	 
	  $('#coccmain_page_nowpage')[0].innerHTML = pageNo;
	  $('#coccmain_page_totalpage')[0].innerHTML = response["totalPage"];
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
		  		"<td   >"+warningList[i]["lineNo"]+"</td>" +
		  		"<td  >"+warningList[i]["equipmentname"]+"</td>" +
		  		"<td   >"+warningtLevel[warningList[i]["warningTypeLevel"]]+"</td>" +
		  		"<td  >"+warningList[i]["warningType"]+"</td>" +
		  		"<td   >"+warningList[i]["warninginfo"]+"</td>" +
		  		"<td   >"+warningList[i]["warningDate"]+"</td>" +
		  		"<td  ><a href='javascript:alltoTaskToOCC("+warningList[i]['lineNo']+","+warningList[i]['id']+");'  >确认</a></td>"+
   				"</tr>"
		  		);
		
		
	  } 
	 
    });  
}

/***上一页和下一页***/
function cocc_backpage(){
	
	var nowpage= $('#coccmain_page_nowpage')[0].innerHTML;
	var totalpage= $('#coccmain_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		OnEmergePageQuery_emerge1(backPageNo);
	}
}
function cocc_nextpage(){
	//$("#select_all").attr('checked',false);
	var nowpage= $('#coccmain_page_nowpage')[0].innerHTML;
	var totalpage= $('#coccmain_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		//alert("nextpage="+nextPageNo);
		OnEmergePageQuery_emerge1(nextPageNo);
	}
}


/***分配任务到某条线路的OCC**/
function alltoTaskToOCC(lineNo,id){
	if(window.confirm("确认分配任务给"+lineNo+"号线的调度员?")){
		OnOperativeUpdateOfUser1(id,'confirm');
		
	 }
}
/***cocc总调度对存在的问题进行确认**/
function OnOperativeUpdateOfUser1(id,stat){
	
	  var operative_index_query_json = {
	     "action":stat,
		  "ids":[id],
		  "operator":operator//当前的操作人员
	  };
	  var Url="/Rcm/Manage/warningOperate.do";
	  //alert(JSON.stringify(operative_index_query_json));
	// TODO 演示需求注释掉
	  
	/*  PostJSONQuery(Url,operative_index_query_json,
	    function(response){
		  var v_info1="确认成功";
		  var v_info2="确认失败";
		 if(response.info=="sucess"&&stat=="confirm"){
			window.confirm(v_info1);
		    OnEmergePageQuery_emerge1($('#coccmain_page_nowpage')[0].innerHTML);
			 
		 }else if(response.info=="false"&&stat=="confirm"){
			 window.confirm(v_info2);
		 }
	    });*/
	  //演示用
	  	window.confirm("确认成功");
	    OnEmergePageQuery_emerge1($('#coccmain_page_nowpage')[0].innerHTML);
}
/***显示一级告警，二级告警，三级告警，四级告警的比列**/
function getNofinishedWaringList(){
	var alert_query_json = {
	  		"pageCount":3,
			"pageNo":1
	};
	
	var Url="/Rcm/Manage/getNofinishedWaringList.do";
    PostJSONQuery(Url,alert_query_json,function(response){
    	$(function(){
    		$("#test1").speedometer({ percentage: response.oneIndex || 0 });
    		$("#test2").speedometer({ percentage: response.twoIndex || 0 });
    		$("#test3").speedometer({ percentage: response.threeIndex || 0 });
    		$("#test4").speedometer({ percentage: response.fourIndex || 0 });
    	});	
	 
    });  
	
  	
}
/***底下两张图***/
function GetAlertCountHistoryPerLevel(domName){
var test_image1=[{"level":"1线号","count":["78.44629833538212","84.75194946133158","90.75354698241918","73.47424835923304","73.5778843024917","60.59777372047934","85.50919675377594","78.13632206544499","84.66796082355513","77.61190286708711"]},{"level":"2线号","count":["76.81331014739018","82.03118576258072","70.94913308021356","83.87797184026617","81.35171350534083","80.25441115674342","84.78631031708196","90.304127528849","72.73736856989872","87.70556270732493"]},{"level":"3线号","count":["99.53676614722117","85.18540280620391","97.75073439021435","67.14637810547207","92.11788071198734","64.73263024048077","83.3821656268123","84.6958265893363","84.8247962844979","64.62769076150074"]},{"level":"4线号","count":["99.08187015287072","73.46637423880699","83.47928706316408","74.2664921629917","61.787003973443106","78.27236924125154","72.7802137481274","73.20260301711565","65.70954832199625","87.81802126296583"]},{"level":"5线号","count":["84.76496230575547","68.4060509707099","84.53266790998967","95.49900239973401","82.58038463876684","91.3033446486823","90.5773246743525","65.92827311247898","79.3007682086033","75.16635749839305"]},{"level":"6线号","count":["63.66497924660857","61.52327726394701","60.31944677674672","96.26924715195395","81.8162569283808","61.96825241923672","97.8797430883786","66.33417480093786","96.58955593850877","69.09190342816694"]},{"level":"7线号","count":["97.59995883138548","89.13813195793564","97.77759719844745","90.67731844886227","99.38622392807274","60.754757287992696","85.34400238031381","98.94751546791807","88.91606098630541","60.756574921607466"]},{"level":"8线号","count":["70.89604916080475","62.946595486787785","60.91459499765291","90.8046686064148","66.6537955646899","98.7630379922077","91.73438172968919","87.40433738622075","86.78984795228352","67.51823252867476"]},{"level":"9线号","count":["66.28892118416238","67.2068827874104","64.17811059762275","98.26989952740598","63.17895173347222","76.0891985797446","75.00389295756156","81.45663428441537","71.88970710221008","67.34442107281156"]},{"level":"10线号","count":["84.35184172871438","88.23633859476851","97.22637282145544","89.66845881974649","97.80221266567216","82.04790782628892","78.69403393364232","62.22877281162995","87.23005911062744","80.95206098981967"]},{"level":"11线号","count":["96.31748779114935","71.95334679316811","73.41598430101689","62.783795586902386","89.43532728629324","67.27935611959677","93.74116012825685","99.87549848012874","61.291805943868","82.95258697623511"]},{"level":"12线号","count":["60.15337757478036","66.08631430156132","79.78900414630502","72.0702538778305","80.75496415443013","96.09990855818731","73.55093455823197","84.41137685223954","77.77745807700946","75.42938162755956"]},{"level":"13线号","count":["62.49024476333384","74.20049834671192","85.37199318143878","79.29457470464001","93.35725834660079","85.80203029045643","92.55841339676752","99.51365569747526","93.29015095417749","72.44412834820234"]}];

      var result = [];

      test_image1.forEach(
        function (element, index, array) {
          var alert_count_history_per_level = {};
          alert_count_history_per_level.key = element.level;
          alert_count_history_per_level.values = [];
          element.count.forEach(
            function(elementCount, indexCount, arrayCount){
              alert_count_history_per_level.values[indexCount] = {x: indexCount + 1,y: parseInt(elementCount)};
              if (isNaN(alert_count_history_per_level.values[indexCount].y))
              {
                alert_count_history_per_level.values[indexCount].y = 0;
              }
            }
          );

          result[index] = alert_count_history_per_level;
		//alert("result="+ result[0].values[1].x +","+ result[0].values[1].y);
        }
      );
          //alert("result="+ JSON.stringify(result));
      initAlertHistoryChart(domName, result);
  
  
}

function initAlertHistoryChart(domName, data){
  nv.addGraph(function() {
    var chart = nv.models.multiBarChart();

    chart.xAxis
      .axisLabel('天');
    //.tickFormat(d3.format(',r'));

    chart.yAxis
      .axisLabel('地铁线路健康指数');
    //.tickFormat(d3.format('.02f'));

    d3.select(domName)
      .datum(data)
      .transition().duration(500)
      .call(chart);

    nv.utils.windowResize(chart.update);

    return chart;
  });
}


function GetAlertCountHistoryPerLevel_2(domName){
	var test_image1=[{"level":"安全生产天数","count":["28","30","29","29","29","30","30","30","29","28","30","30"]},{"level":"强迫停运天数","count":["1","3","1","2","3","1","2","2","1","2","3","2"]},{"level":"非计划停运天数","count":["3","3","1","3","1","2","3","1","2","2","2","3"]}];

	      var result = [];

	      test_image1.forEach(
	        function (element, index, array) {
	          var alert_count_history_per_level = {};
	          alert_count_history_per_level.key = element.level;
	          alert_count_history_per_level.values = [];
	          element.count.forEach(
	            function(elementCount, indexCount, arrayCount){
	              alert_count_history_per_level.values[indexCount] = {x: indexCount + 1,y: parseInt(elementCount)};
	              if (isNaN(alert_count_history_per_level.values[indexCount].y))
	              {
	                alert_count_history_per_level.values[indexCount].y = 0;
	              }
	            }
	          );

	          result[index] = alert_count_history_per_level;
			//alert("result="+ result[0].values[1].x +","+ result[0].values[1].y);
	        }
	      );
	          //alert("result="+ JSON.stringify(result));
	      initAlertHistoryChart_2(domName, result);
	  
	  
	}

	function initAlertHistoryChart_2(domName, data){
	  nv.addGraph(function() {
	    var chart = nv.models.multiBarChart();

	    chart.xAxis
	      .axisLabel('月');
	    //.tickFormat(d3.format(',r'));

	    chart.yAxis
	      .axisLabel('天 ');
	   // .tickFormat(d3.format('.02f'));

	    d3.select(domName)
	      .datum(data)
	      .transition().duration(500)
	      .call(chart);

	    nv.utils.windowResize(chart.update);

	    return chart;
	  });
	}

	
	/*******cocc查看occ的需要处理的事件，但是无操作权限*******************/
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

