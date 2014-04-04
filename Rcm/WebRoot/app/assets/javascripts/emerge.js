function OnEmergePageQuery(){
	//window.confirm("query is ok");
  var alert_query_json = {
    "page":{"pageCount":"15","pageNo":"1"},
    "statments":"2"  
  };
  
  var Url="/Rcm/Manage/getWaringListBystatments.do";
  emergePostJSONQuery(Url,alert_query_json,function(response){
	 //window.confirm("postquery is ok");
    
	  var pageNo = alert_query_json.page.pageNo; 
	
	  var warningList=response["warningList"];
	  //window.confirm(JSON.stringify(warningList));
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
		  var warningtLevel={"1":"一级","2":"二级","3":"三级","4":"四级"};
		  var page=pageNo;
		var leve=warningList[i]["warningTypeLevel"];
		var checkBox=null;
		var onmouse=null;
			checkBox="</label><input   id='"+warningList[i]["id"]+"' name='needitems' type='checkbox'/></th>";
			onmouse="<tr style='height:25px'>";
			//将未处理的告警信息显示出来
			 $('#query_result_table_body').append("" +
				  onmouse +
		  		/*"<th  scope='col'  ><div>"+status[warningList[i]["warningTypeLevel"]]+"</div></th>" +
		  		"<th scope='col'>" +
		  		"<label for='select_all'>" +
		  		checkBox +*/
		  		"<th style='color:#505050'  scope='col' >"+(((page-1)*10)+1+i)+"</th>" +
		  		"<th style='color:#505050'  scope='col' >"+warningList[i]["warningId"]+"</th>" +
		  		"<th style='color:#505050'  scope='col' >"+warningList[i]["equipmentId"]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+warningList[i]["equipmentname"]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+warningList[i]["systemName"]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+warningList[i]["warningType"]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+warningtLevel[warningList[i]["warningTypeLevel"]]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+warningList[i]["warninginfo"]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+warningList[i]["lineNo"]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+warningList[i]["stationName"]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+warningList[i]["warningDate"]+"</th>" +
		  		"<th style='color:#505050' scope='col' >"+stat+"</th>" 
		  		/*"<th style='color:#505050' scope='col'><a href='javascript:OnOperativeUpdateOfUser1();' class='next'  onclick='esureEvent();' >确认</th>"+
   				"<th style='color:#505050' scope='col'><a    class='next'  onclick='cacelEvent();'>清除</a></th>"*/
		  		);
		  
			
		 
	  } 
    });  
}



function OnOperativeUpdate(){
		//window.confirm("onOperativeUpdate is ok");
  var health_level = {"4": "healthy", "3": "good", "2": "danger", "1": "severe"};
  var operative_index_query_json = {
          "reqString":"getAll"
    	};
  
  var Url="/Rcm/Manage/getLineHealth.do";
  emergePostJSONQuery(Url,operative_index_query_json,
    function(response){
	 //window.confirm("response="+response);
      var health_array = response['listHealth'];
     // window.confirm(health_array);
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
 