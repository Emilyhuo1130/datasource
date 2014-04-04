
/**删除某个客户端**/
function deleteOneClient(local_ip){
	$.createDialog('是否真的删除这个客户端配置?',function(){
		location.href="delete.do?Local_ip="+local_ip;
	});
}

/**添加或者更新一个ip的配置信息 改用ajax请求**************************************************/
function postJsonQuery_addCfg(localIp){
	//拿到页面上的需要提交的数据
	//从隐式table中获取演示效果的集合
	console.log("************进入提交*************");
	var pic_m=[];
	var pic_s=[];
	var video=[];
	
	//进行一次迭代判断  拿出播放顺序和json对象
	$("#selectTable_json tbody tr").each(function(i,e){
		var $e = $(e).find("td:eq(2)");
		var obj = $e.html();
		console.log("********每一行的元素=********"+obj);
		
		var jsonObj = eval('('+obj+')');
		if(jsonObj.efectType=="多幅特效"){
			pic_m.push(jsonObj);
		}else if(jsonObj.efectType=="单幅特效"){
			pic_s.push(jsonObj);
		}else if(jsonObj.efectType=="视频"){
			video.push(jsonObj);
		}
	});
	console.info("**************多幅数组："+JSON.stringify(pic_m));
	console.info("**************单幅数组："+JSON.stringify(pic_s));
	console.info("**************视频数组："+JSON.stringify(video));

	var req_json = {
	    "old_local_ip": localIp,//添加的话old_ip是 ""  如果更新那么就会有old_ip
	    "local_ip": $("#Local_ip").val(),
	    "client_Id": $("#client_position").val(),
	    "pic_m": pic_m,
	    "pic_s":pic_s,
	    "ListVideoConfig":video
	};
	console.info("****req_josn=*********"+JSON.stringify(req_json));
	
	 $.ajax({
		    url: "AddorEditOneInfo.do",
		    type:"post",
		    contentType : "application/x-www-form-urlencoded;charset=utf-8",
		    processData : true,
		    data: JSON.stringify(req_json) ,
		    dataType: "json",
		    success: function(response) {
		    if(response){
		    	location.href="../jsp/getinfoList.do";
		    }
		    },
		    error: function (xhr, ajaxOptions, thrownError) {
		    	console.error(JSON.stringify(xhr)+","+ajaxOptions+","+thrownError);
		    }
		  });
}


/**将服务器IP配置设置为可写****/
function changeserver_IP_Type(){
	$("input[name='server_IP']").removeAttr('readonly');
}
/****服务器IP失去焦点  只读**/
function changeserver_IP_TypeToreadonly(){
	$("input[name='server_IP']").attr('readonly','readonly');
}

function  reset_form(){
	$.createDialog("确认要重置吗?(刷新页面)",function(){
		location.href="../jsp/toAdd.do";
	});
		
	
}


//更改图片路径
function PostJSONQuery(json, on_success)
{
  $.ajax({
    url: "changeImagesAddress.do",
    type:"post",
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    processData : true,
    data: json,
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    
    }
  });
}

//验证区位ip是否存在
function PostJSONQuery_positionCode(json, on_success)
{
  $.ajax({
    url: "veriftyIP.do",
    type:"post",
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    processData : true,
    data: json,
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    
    }
  });
}

function test(){
	alert("test");
	 var alert_query_json = {
			 userName:"zhang"
	 };
	PostJSONQuery(alert_query_json,function(response){
		alert("response");
		alert(response.code);
	});
}

//路径
var  images_url="sdfsdfasdfads";
function event_fadeInOut_url(req_json){
	PostJSONQuery(req_json,function(response){
		images_url=response;
		//alert(JOSN.stringify(response));
		return response;
		
	});
	
	
}
function event_shuimohua_url(req_json){
	PostJSONQuery(req_json,function(response){
		images_url=response;
		return response;
		
	});
	
}
//表单验证
function   validate_form(){
	var flag_form1=false;
	var flag_form2=false;
	var regExp=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	if($("#MultiEfect option:eq("+$("#MultiEfect").val()+")").html()=="请选择特效"&&$("#efectType option:selected").html()=="多幅特效"){
		flag_form1=false;
		$("#novalue_info").slideDown("normal");
		
	}else{
		$("#novalue_info").slideUp("normal");
		flag_form1=true;
	}
	
	
	if(regExp.test($("#Local_ip").val())){
		$("#data_error").slideUp("slow");
		flag_form2=true;
	}else{
		$("#data_error").slideDown("slow");
		flag_form2=false;
	}
	return flag_form1&&flag_form2;
	
}
/***选择联动效果类型时 选择要不要显示所选则视频**/
function showvideosSelect(){
	var num=$("#linkageType").val();
	if(num=="1"){
		$("#showVideos").slideDown("slow");
		
	}else{
		$("#showVideos").slideUp("slow");
	}
	
}
//验证演示时间为整数大于5秒
function  validatePlaytime(id){
	$("#"+id+"").blur(function(){
		var regEpx = /^[0-9]*[1-9][0-9]*$/;
		if(regEpx.test($(this).val())&&$(this).val()>=5){
			$(this).parent().children("div").slideUp("slow");
			return true;
		}else{
			
			$(this).parent().children("div").slideDown("slow");
		}
	});

}
//获得区位ip
function PostJSONQuery_getIP(json, on_success)
{
  $.ajax({
    url: "getipByclient_Id.do",
    type:"post",
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    processData : true,
    data:json,
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    
    }
  });
}
//添加一个联动配置
function PostJSONLink(json, on_success)
{
  $.ajax({
    url: "addPic_Linkage.do",
    type:"post",
    data: JSON.stringify(json),
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	$.msg("配置失败");
    }
  });
}
//加载某个联动配置
function PostJSONLoad(json, on_success)
{
  $.ajax({
    url: "getOnePic_Linkage.do",
    type:"post",
    data: json,
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	$.msg("getOnePic_Linkage.do---加载失败");
    }
  });
}
//联动策略列表删除按钮
function PostJSONDelete(json, on_success)
{
  $.ajax({
    url: "deleteOnePic_LinkageByCode.do",
    type:"post",
    data: json,
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	$.msg("删除失败");
    }
  });
}
//获取select 联动类型opgtion

function PostJSONGetLinkageOption(on_success)
{
  $.ajax({
    url: "getmultiScreenEffect.do",
    type:"post",
    data: {},
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	$.msg("获取select列表失败");
    }
  });
}
//获取视频文件名
function PostJSONGetvideosName(on_success)
{
  $.ajax({
    url: "getvideosName.do",
    type:"post",
    data: {},
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	$.msg("获取select列表失败");
    }
  });
}

//验证方法  正则表达式和需要的请求数据由调用者提供 id是存放错误信息的地方
var flag=false;
function   validateData(regExp,req_data,id,msg){
//test  var regExp=/^[0-9]$/;  var req_data=9;
	if(regExp.test(req_data)){
		//alert("验证通过");
		$("#"+id).slideUp("slow");
		flag = true;
	}else{
		//alert("验证无法通过");
		$("#"+id).find("span").html(msg);
		$("#"+id).slideDown("slow");
		flag = false;
	}
	return flag;
}

//feildset的克隆  未用到
function  feildsetClone(){
	//首先输出当前的feildset的个数    
	console.log("当前的feildset的个数为:"+$("fieldset").size());
	
	var feildsetCount = $("fieldset").size();
	var $clone = $("#group"+feildsetCount);
	
	var $newObj = jQuery.extend(true,{},$clone);
	$newObj.attr("id","group"+(feildsetCount));//赋值方法
	$newObj.find("legend").html("图片演示效果"+(feildsetCount));//赋值方法

	$clone.after("<fieldset id='group"+(feildsetCount+1)+"' class='fieldset' style='height:220px;'>"+$newObj.html()+"</feildset>");
	console.log("克隆出的对象为"+$newObj.attr("id"));
	console.log($newObj.html());
	
	
}  

//删除某行  序号前移
function deleteThisTr(index){
	$.createDialog("确认要清除这个特效吗?",function(){

		var _len = $("#selectTable tbody tr").size();
		console.log("删除前的行数为："+_len);
		//按照自增长的id来对应删除  删除无误
		$("#selectTable tbody").find("#tr"+index).remove();
		
		//重新迭代播放顺序
		  for(var i=index+1,j=_len;i<=j;i++){
			              $("#selectTable tbody tr[id='tr"+i+"']")
			                  .replaceWith("<tr id='tr"+(i-1)+"' align='center'>"+
			                                  "<td>"+(i-1)+"</td>"+
			                                  "<td><a href='javascript:showFloatInfo("+(i-1)+");'>"+$("#selectTable tbody").find("#tr"+(i)).find("td:eq(1) a").html()+"</a></td>"+
			              					"<td>"+
			              						"<a href='javascript:deleteThisTr("+(i-1)+");'>清除</a>"+
			              					"</td>"+
			                              +"</tr>");
			          } 
		  
		//清除隐式table中的内容    清除某条记录后序号要重建
			$("#selectTable_json tbody").find("#tr"+index).remove();
			  for(var i=index+1,j=_len;i<=j;i++){
				  $("#selectTable_json tbody tr[id='tr"+i+"']")
				  	.replaceWith("<tr id='tr"+(i-1)+"' align='center'>"+
	        		  "<td>"+(i-1)+"</td>"+//播放顺序
						"<td>演示效果"+(i-1)+"</td>"+
						"<td>"+
						$("#selectTable_json tbody").find("#tr"+(i)).find("td:eq(2)").html()+
						"</td>"+
	                      +"</tr>");
			  }
	});
	
	
}

 
//添加演示效果 ************************************重点
function  addEfectToTable(){
	//获取当前的行号
	$.createDialog("确定要添加该特效吗？",function(){
		var index = $("#selectTable tbody").find("tr").size()-1;//因为要从0开始 ，初始 加1，即从-1开始.
		console.log("********添加前此表有"+index+"行******");
		//将真实数据放入浮动table中
		var efectType=$("table #efectType").val();//演示效果类型
		console.log("********演示效果类型:******"+efectType);
		
		//判断是多幅还是单幅   特效名称要的是数字
		var json_efect=null;
		if(efectType=="多幅特效"){
			var specialType=$("#MultiEfect option:selected").val();
			var picUrl = $("#MutiResFolder option:selected").html();
			var randomPlay=$("#MultiRandom option:selected").html();
			var playTime = $("#MutiTime").val();
			
			 json_efect={"efectType":"多幅特效","sequence_number": (index+1),"picShow":specialType,"resFolder":picUrl,"random":randomPlay,"time":playTime};
			 console.log("****json_efect=*******"+JSON.stringify(json_efect));
			 $("#selectTable").append(
					 	"<tr id='tr"+(index+1)+"'>"+
					 	"<td>"+(index+1)+"</td>"+
						"<td><a href='javascript:showFloatInfo("+(index+1)+");'><span hidden='hidden'>多幅</span>查看详情"+"</a></td>"+
						"<td>"+
							"<a href='javascript:deleteThisTr("+(index+1)+");' >清除</a>"+
						"</td>"+
					"</tr>");
			//隐式的table
				//将json对象放入隐藏区
				$("#selectTable_json").append("<tr id='tr"+(index+1)+"'>"+
						"<td>"+(index+1)+"</td>"+//播放顺序
						"<td>演示效果"+(index+1)+"</td>"+
						"<td>"+
							JSON.stringify(json_efect)+
						"</td>"+
					"</tr>");
				console.log("多幅隐式表中的内容"+$("#selectTable_json").html());
			
		}else if(efectType=="单幅特效"){
			
			var picUrl = $("#singleResFolder option:selected").val();
			var playTime = $("#singleTime").val();
			 json_efect={"efectType":"单幅特效","sequence_number":(index+1),"resFolder":picUrl,"time":playTime};
			 console.log("*******json_efect=**********"+JSON.stringify(json_efect));
			 $("#selectTable").append("<tr id='tr"+(index+1)+"'>"+
					 "<td>"+(index+1)+"</td>"+
						"<td><a href='javascript:showFloatInfo("+(index+1)+");'><span hidden='hidden'>单幅</span>查看详情"+"</a></td>"+
						"<td>"+
							"<a href='javascript:deleteThisTr("+(index+1)+");' >清除</a>"+
						"</td>"+
					"</tr>");
			 
			//隐式的table
				//将json对象放入隐藏区
				$("#selectTable_json").append("<tr id='tr"+(index+1)+"'>"+
						"<td>"+(index+1)+"</td>"+//播放顺序
						"<td>演示效果"+(index+1)+"</td>"+
						"<td>"+
							JSON.stringify(json_efect)+
						"</td>"+
					"</tr>");
				console.log("单幅隐式表中的内容"+$("#selectTable_json").html());
		}else if(efectType=="视频"){
			var videoUrl = $("#videoResFolder option:selected").html();
			 json_efect={"efectType":"视频","sequence_number": (index+1),"VideoRelativePath":videoUrl};
			 console.log("*******json_efect=**********"+JSON.stringify(json_efect));
			 $("#selectTable").append("<tr id='tr"+(index+1)+"'>"+
					 "<td>"+(index+1)+"</td>"+
						"<td><a href='javascript:showFloatInfo("+(index+1)+");'><span hidden='hidden'>视频</span>查看详情"+"</a></td>"+
						"<td>"+
							"<a href='javascript:deleteThisTr("+(index+1)+");' >清除</a>"+
						"</td>"+
					"</tr>");
			//隐式的table
				//将json对象放入隐藏区
				$("#selectTable_json").append("<tr id='tr"+(index+1)+"'>"+
						"<td>"+(index+1)+"</td>"+//播放顺序
						"<td>演示效果"+(index+1)+"</td>"+
						"<td>"+
							JSON.stringify(json_efect)+
						"</td>"+
					"</tr>");
				console.log("视频隐式表中的内容"+$("#selectTable_json").html());
			 
		}
	});

	
}
//为浮动层添加数据*********************************************************************注意行的下标问题
function   getFloatInfo(index){
	
			//表层名称要判断是多幅的还是单幅的   
			var select_jsonTr = $("#selectTable tbody tr:eq("+index+") td:eq(1) a span").html();
			console.log("************当播放顺序是"+(index+1)+"时，判断是多幅还是单幅:*********"+select_jsonTr);
			//判断是多幅还是单幅      多u591a   单u5355   幅u5e45
			var regExp1=/^\u591a\u5e45/;
			var regExp2=/^\u5355\u5e45/;
			var regExp3=/^\u89c6/;
			if(regExp1.test(select_jsonTr)){
				var multi_jsonTr = $("#selectTable_json tbody tr:eq("+index+") td:eq(2)").html();
				console.log("隐表中的json对象为"+multi_jsonTr);
				var multi_jsonObj = eval('('+multi_jsonTr+')');
				$("#dynamicFloor").append("<table id='float_table'>"+
						"<tr>" +
							"<td>演示效果类型:</td><td>多幅</td></tr>" +
							"<tr><td>特效类型:</td><td>"+multi_jsonObj.picShow+"</td></tr>" +
							"<tr><td>图片路径:</td><td>"+multi_jsonObj.resFolder+"</td></tr>" +
							"<tr><td>随机播放:</td><td>"+multi_jsonObj.random+"</td></tr>" +
							"<tr><td>演示时间:</td><td>"+multi_jsonObj.time+"</td>" +
						"</tr>" +
						"</table>");
			}else if(regExp2.test(select_jsonTr)){
				var single_jsonTr = $("#selectTable_json tbody tr:eq("+(index)+") td:eq(2)").html();
				var single_jsonObj = eval('('+single_jsonTr+')');
				console.log("显示单幅对象*********"+JSON.stringify(single_jsonObj));
				$("#dynamicFloor").append("<table id='float_table'>"+
						"<tr>" +
									"<td style='width:25%'>演示效果类型:</td><td style='width:75%'>单幅</td>" +
						"</tr>" +
						"<tr>" +
									"<td>图片路径:</td><td>"+single_jsonObj.resFolder+"</td>" +
						"</tr>" +
						"<tr>" +
									"<td>演示时间:</td><td>"+single_jsonObj.time+"</td>" +
						"</tr>" +
					"</table>");
			}else if(regExp3.test(select_jsonTr)){
				var video_jsonTr = $("#selectTable_json tbody tr:eq("+(index)+") td:eq(2)").html();
				console.info("video_jsonTr="+video_jsonTr);
				var video_jsonObj = eval('('+video_jsonTr+')');
				console.log("显示视频对象*********"+JSON.stringify(video_jsonObj));
				$("#dynamicFloor").append("<table id='float_table'>"+
						"<tr >" +
									"<td style='width:25%'>演示效果类型:</td><td style='width:75%'>视频</td>" +
						"</tr>" +
						"<tr >" +
									"<td>视频路径:</td><td>"+video_jsonObj.VideoRelativePath+"</td>" +
						"</tr>" +
					"</table>");
				
			}
			
			
			$("#float_table").css({"position":"absolute"});
			$("#float_table").css({"left":"0"});
			$("#float_table").css({"spacing":"10px"});
			$("#float_table").css({"width":"330px"});
			$("#float_table").css({"height":"200px"});
			$("#float_table").css({"border":"3px solid gray"});
			$("#float_table").css({"text-align":"left"});
			$("#float_table").css({"background-color":"#ffffff"});
};

//显示浮动层
function  showFloatInfo(){
	
		 $("#selectTable tbody tr").each(function(i,e){
	 		var $e=$(e);
	 		$e.find("td:eq(1) a ").click(function(e){
	 				var py=e.pageY;
			 			//获取光标坐标 
			 			$("#selectTable tbody tr:eq("+i+")").after("<div id='dynamicFloor'></div>");
			 			//在左侧列表中单幅的行号不是从0开始的  取数据要将用总行数减去多幅的行数
			 			console.log("*************显示光标移入对应行的数据:"+i);
			 			getFloatInfo(i);
			 			$("#dynamicFloor").css({"position":"absolute"});
			 			$("#dynamicFloor").css({"top":py-80+"px"});
			 			$("#dynamicFloor").css({"z-index":"1000"});
			 			$("#dynamicFloor").css({"width":"330px"});
			 			$("#dynamicFloor").css({"height":"200px"});
			 			$("#dynamicFloor").css({"left":"200px"});
			 			$("#dynamicFloor").css({"background-color":"#ddddff"});
			 			$("#dynamicFloor").css({"opacity":"1"});
	 			
	 				
	 		});
	 		//隐藏浮动层
	 		$e.mouseout(function(){
	 			$("#selectTable").parent().find("div[id='dynamicFloor']").remove();
	 		});
	 		
		 });
	 		
	
	
}