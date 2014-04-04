<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	 <link type="text/css" rel="stylesheet" href="css/validate.css" />
	 <style>
	 html{overflow-y:scroll;overflow-x: scroll;}
	 	table tr:hover{
	 		background-color:#ddddff;
	 	}
	 #showPositionCode:hover{
	 	cursor:pointer;
	 	background-color:red;
	 }
	 </style>
		 <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
		 <script type="text/javascript" src="../js/jqueryPromit.js"></script>
		 <script type="text/javascript" src="../js/jqueryBlockUI.js"></script>
		 <script type="text/javascript" src="../js/utils.js"></script>
		 <script type="text/javascript" src="../js/gk.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
		//区位滑入滑出
			$("#showPositionCode").mouseover(function(){
				$("#hover_showpositioncode").slideDown("normal");
				
			});
	    
			$("#showPositionCode").mouseout(function(){
				$("#hover_showpositioncode").slideUp("normal");
				
			});
			
			//动态浮动层
			$("#query_result_table_body tr").each(function(i,e){
				var $e = $(e);
					var floatJson_0 = getNaviList(0,i);
					showFloatStack($e.find("td:eq(2)"),floatJson_0.value);
					
					var floatJson_1 = getNaviList(1,i);
					showFloatStack($e.find("td:eq(3)"),floatJson_1.value);
					
					var floatJson_2 = getNaviList(2,i);
					showFloatStack($e.find("td:eq(4)"),floatJson_2.value);
				
			});
		
		});
		
	//准备弹出框的数据   index_0是 按名称  按路径         index_1 按行号检索
		function getNaviList(index_0 , index_1){
			  //初始化数据
		    var resJson = eval('('+$("#hiddenJson").html()+')');//有值
		    console.info("******查看隐藏的json********"+JSON.stringify(resJson));
		    var pic_nameStrs=[];//名称字符串数组
		    var pic_urlStrs=[];//路径字符串数组
		    var video_Strs=[];
		      for ( var i = 0; i <resJson.configList.length; i++) {
		    	  
		    	  var pic_m_nameLi=resJson.configList[i].pic_mNames;//多幅名称的集合
			      var pic_m_resFolders=resJson.configList[i].pic_m_image_urls;//多幅路径的集合
			      var videoNames =  resJson.configList[i].viedoNames;
			      var pic_nameStr=[];
			      var pic_m_urlstr=[];
			      var videoNamesStr =[];
			      pic_m_nameLi.forEach(function(e,i){
			    	  
			    	  pic_nameStr.push("<li>"+e+"</li>");
			      });
		    	  console.info("*****pic_nameStr=*****"+pic_nameStr);
			      pic_m_resFolders.forEach(function(e,i){
			    	  pic_m_urlstr.push("<li>"+e+"</li>");
			    	  
			      }); 
			      videoNames.forEach(function(e,i){
			    	  videoNamesStr.push("<li>"+e+"</li>");
			    	  
			      });
			      
			    	  console.info("*****pic_m_urlstr=*****"+pic_m_urlstr);
			      //concat链接数组的元素拼成字符串
				     var pic_m_namstring =  pic_nameStr.join("");
				      var pic_m_urlstring = pic_m_urlstr.join("");
				      var videostring = videoNamesStr.join("");
				      pic_nameStrs.push(pic_m_namstring);
				      pic_urlStrs.push(pic_m_urlstring);
				      video_Strs.push(videostring);
				      
				     
			}
		     
		      //0是name集合  1是url集合
			 var navi_json=[
						      {
						       	 "naviName":"multiName","value":"<div style='position:absolute;z-index:10;left:31.5%;border:1px solid purple; display:none;width:12.5%;background-image:url(../images/background.png);'>"+
						       	 									"<ul style='position:relative;left:-28px;list-style-type:none;line-height:24px;color:black;'>"+
						       	 									pic_nameStrs[index_1]+
						       										"<li>&nbsp;</li>"+
						       										"</ul></div>"
						       },
						       
						      {
			 				     "naviName":"multiUrl","value":"<div style='position:absolute;z-index:15;display:none;border:1px solid purple;left:44%;width:21%;background-image:url(../images/background.png);'>"+
			 				       	 									"<ul style='position:relative;left:-28px;list-style-type:none;line-height:24px;color:black;'>"+
			 				       	 										pic_urlStrs[index_1]+
			 				       									    "<li>&nbsp;</li>"+
			 				       										"<ul></div>"
			 				    },
			 				    {
				 				     "naviName":"videoUrl","value":"<div style='position:absolute;z-index:25;display:none;border:1px solid purple;left:65%;width:18%;background-image:url(../images/background.png);'>"+
 	 									"<ul style='position:relative;left:-38px;list-style-type:none;line-height:24px;color:black;'>"+
 	 									video_Strs[index_1]+
 									    "<li>&nbsp;</li>"+
 										"<ul></div>"
}
						                 								
						      ];
			 
						return navi_json[index_0];
						
						
		}
	</script>
	 
</head>

<body class="bgm">
  
  <div id="part2">
  		<div id="sub_nav" style="margin-top:-10px;">
  		
  		</div>
  <div style="max-width: 100%;padding:10px; border: 1px solid gray; border-top: none;">
  	<br/>	
  	
  	<div id="showPositionCode" style="margin-bottom:20px;width:90px;font-weight:bold;">显示区位图</div>
  	<div style="height:10px;"></div>
  	<div id="hover_showpositioncode"> <img src="../images/hover_div_showposition.png"/> </div> 
  	<!-- 发送请求 -->
	<form action="getinfoList.do" method="post">
    <div style="display:inline-block;">
      
	<div style="width:938px;height:739px; " ><!-- 分边 -->
	<div id="query_result" style="width:938px;height:679px;border-left:1px solid black;border-right:1px solid black;">
	<div id="alert_content" style="padding-top: 0px; height: 650px; border-bottom: 1px solid gray;" >
	  <div id="table_bgm" style="border-bottom:0;">
	    <table  style="width:938px;" id="query_result_table" >
	      <thead>
	      <tr id="query_result_table_header">
        
	        <th style="width:13%">本机IP</th>
	        <th style="width:15%">客户端id（区位号）</th>
	        <th style="width:15%">多幅名称</th>
	        <th style="width:25%">多幅路径</th>
	        <th style="width:22%">视频</th>
	        <th style="width:20%">操作</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body"  > 
      
      <c:forEach items="${config_Pis.configList}" var="element" varStatus="record" begin="0" end="26" step="1">
		<tr>
			<td id="local_ip">${ element.local_ip}</td>
			<td id="client_qw">${ element.client_Id }</td>
			<td id="mulshow">${element.pic_mNames[0]} &#133;</td>
	        <td id="MutiResFolder">${element.pic_m_image_urls[0]}&#133;</td>
	        <td id="video">${element.viedoNames[0]}&#133;</td>
			<td id="operation"><%-- <a href="c_config_modify.jsp?config_Pis={'local_ip':${ element.local_ip},'client_Id':${ element.client_Id },'pic_mNames':${element.pic_mNames[0]},'pic_m_image_urls':${element.pic_m_image_urls[0]}}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp; --%><a href="javascript:deleteOneClient('${element.local_ip}');">删除</a></td>
		</tr>
		 </c:forEach>
		 
      </tbody>
    </table>
    <span id="hiddenJson" hidden="hidden">${config_str}</span>
  </div>
  </div>
  
 <div id="query_result_footer" style="width: 936px;">
    <div id="query_result_footer_current_page">
    <%--  <img id="pre_page"  src="../images/previous_triangle.png" onclick="backPage();" />
     	 第 <span id="nowpage">1</span> 页
     <img id="next_page" src="../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transfom:rotate(180deg);"  onclick="nextPage();"/> --%>
    <div id="query_result_footer_total_page"><%--
      	共 <span id="totalpage"><script type="text/javascript">document.getElementById('totalpage').innerHTML=Math.floor((${config_Pis.clients.size()}-1)/3)+1;</script></span> 页
    --%></div>
    <div style="float: right; padding-right: 20px;">
     	 当前记录总数:<span id="record_count">${config_Pis.configList.size()}</span>条
    </div>
    </div>
  </div>
 
  
 </div>
</div>
    </div>
</form>




	</div>
</div>
<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

</body>

</html>