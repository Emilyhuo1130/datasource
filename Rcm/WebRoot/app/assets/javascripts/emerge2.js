function OnEmergePageQuery(){
  var alert_query_json = {
      "page":{"pageCount":"15",
	  "pageNo":"1"},
	  "statments":"1"
		  };
  
	 alert("entring emerge2.js");
	 var Url="/Rcm/Manage/getWaringListBystatments.do";
     emergePostJSONQuery(Url,alert_query_json,function(response){
	  var pageNo = alert_query_json.page.pageNo; 
	
	  var warningList=response["warningList"];
	  //window.confirm(warningList)
	 //window.confirm(JSON.stringify(warningList));
	  //$('#query_result_table tr').remove();
	  for(var i=0;i<warningList.length;i++){
		  var statmentsnumber= warningList[i]["statments"];
		 // window.confirm(statmentsnumber);//获得状态信息列表
		  
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
		  var from ={"1":"确认","2":"核实","3":"审定","4":"已处理"};
		  var page=pageNo;
		var leve=warningList[i]["warningTypeLevel"];
		var checkBox=null;
		var onmouse=null;
		if(stat=="确认中"){
			onmouse="<tr style='height:25px'>";
					//window.confirm("else if is ok");
				 $('#query_result_table_body').append("" +
					 onmouse+
		  		"<td style='color:#505050'  scope='col' >"+warningList[i]["equipmentId"]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+warningList[i]["equipmentname"]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+warningList[i]["systemName"]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+warningList[i]["warningType"]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+warningtLevel[warningList[i]["warningTypeLevel"]]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+warningList[i]["warninginfo"]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+warningList[i]["lineNo"]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+warningList[i]["stationName"]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+warningList[i]["warningDate"]+"</td>" +
		  		"<td style='color:#505050' scope='col' >"+stat+"</td>" +
		  		"<td style='color:#505050' scope='col' >系统生成</td>" +
		  		"<td style='color:#505050' scope='col'>"+from[warningList[i].statments]+"</td>"+
   				"<td style='color:#505050' scope='col'><a  href='javascript:esureEvent();'class='next'  onclick='cacelEvent();'>详情</a></td>"+
   				"tr"
		  		);
		  
			
		}
		else{
			checkBox="</label><input   name='items' disabled='disabled'  type='checkbox'/></th>";
			onmouse="<tr  style='height:25px' name='show'  onmouseover='OnMainPageContentReceived("+statmentsnumber+");' >";
		}
			
		 
	  } 
    });  
}


//user1将状态更新为confirm
function OnOperativeUpdateOfUser2(id){
	//window.confirm("onOperativeUpdate1 is ok");
  var operative_index_query_json = {
   "action":"confirm","ids":[id]
   };
 // window.confirm(JSON.stringify(operative_index_query_json));
  var Url="/Rcm/Manage/warningOperate.do";
  PostJSONQuery(Url,operative_index_query_json,
    function(response){
	 
      window.confirm(response);
      var health_array = response['listHealth'];
    });
}
/**上一页*/
function backpage(){
	
	$("#select_all").attr('checked',false);
	var nowpage= $('#emerge_page_nowpage')[0].innerHTML;
	var totalpage= $('#emerge_page_totalpage')[0].innerHTML;
	if(nowpage<=1){
		alert("这是第一页，无法往前");
	}else {
		var backPageNo=nowpage-1;
		selectData(backPageNo);
	}
	
	
}
/**下一页*/
function nextpage(){
	$("#select_all").attr('checked',false);
	var nowpage= $('#emerge_page_nowpage')[0].innerHTML;
	var totalpage= $('#emerge_page_totalpage')[0].innerHTML;
	
	if(nowpage>=totalpage){
		alert("这是最后一页s，无法往前");
	}else{
		var Page=nowpage-1;
		var nextPageNo=Page+2;
		selectData(nextPageNo);
	}
}

function esureEvent(){
	var esure = $("table tr th").val("确认");
	//alert(esure.val());
	if("确认"===esure.val()){
		//alert(document.getElementById("info1").innerHTML);
		document.getElementById("info1").innerHTML="2";
		document.getElementById("info2").innerHTML="核实员***， 已终止对“告警编号+资产明编号+资产名称+所属系统+告警类型.....”的核实流程。";
		document.getElementById("info3").innerHTML="2013-05-20 12：53：12";
		OnOperativeUpdateOfUser1();
		
			
	}
	
}
function clickEvent(){
	
	document.getElementById("test").innerHTML="yyyyyyyyyyy";
}
function cacelEvent(){
	/*alert("uuuuuus");*/
	var cacel = $("table tr th").val("清除");
	if("清除"===cacel.val()){
		var obj = $("table tr td span #info3").text("sdeeeeeeeeeeeeeeeeeeeee");
		
	}
	
}

function loadData(){
}
 