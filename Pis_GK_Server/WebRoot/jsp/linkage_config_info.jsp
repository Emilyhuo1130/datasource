<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	 <link type="text/css" rel="stylesheet" href="css/validate.css" />
	 <style>
	 html{overflow-y:scroll;}
	 	table tr:hover{
	 		background-color:#ddddff;
	 	}
	 #showPositionCode:hover{
	 	cursor:pointer;
	 	background-color:red;
	 }
	 #query_result_table tbody tr td{
	 	line-height:20px;
	 }
	 </style>
	 <script language="JavaScript" src="../js/jquery-1.4.4.min.js"></script>
		 <script language="JavaScript" src="../js/jqueryPromit.js"></script>
		 <script language="JavaScript" src="../js/jqueryBlockUI.js"></script>
		 <script language="JavaScript" src="../js/utils.js"></script>
		 <script language="JavaScript" src="../js/gk.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
			//启用滚动条
			   $(document.body).css({
			    "overflow-x":"auto",
			    "overflow-y":"auto"
			 });
			
			$("#showPositionCode").mouseover(function(){
				$("#hover_showpositioncode").slideDown("slow");
				
				
			});
			$("#showPositionCode").mouseout(function(){
				$("#hover_showpositioncode").slideUp("slow");
				
			});
		
			
		});
		//删除按钮
		function deleteManager(reqData){
			$.createDialog("确定要删除这条配置吗?",function(){
				var req_json={"linkageCode":reqData};
				PostJSONDelete(req_json,function(response){
				location.reload();
				});
				
			});
			
			
		}
	</script>
	 
</head>

<body class="bgm" style="position:relative;top:-9px;">
	<div id="part1" >
  </div>
  
  <div id="part2" style="">
  		<div id="sub_nav" style="margin-top: -6px;">
  		
  		</div>
  <div style="max-width: 100%;padding:10px; border: 1px solid gray; border-top: none;">
  	<br/>	
  	
  	<div id="showPositionCode" style="margin-bottom:20px;width:90px;font-weight:bold;">显示区位图</div>
  	<div style="height:10px;"></div>
  	<div id="hover_showpositioncode"> <img src="../images/hover_div_showposition.png"/> </div> 
  	
	<form action="getinfoList.do" method="post">
    <div style="display:inline-block;">
	<div style="width:938px;height:739px;"><!-- 分边 -->
	<div id="query_result" style="width:938px;height:679px;border-left:1px solid black;border-right:1px solid black;">
	<div id="alert_content" style="padding-top: 0px; height: 650px;border-bottom: 1px solid gray;" >
	  <div id="table_bgm" style="border-bottom: 1px solid 0;">
	    <table  style="width:938px;" id="query_result_table" >
	      <thead>
	      <tr id="query_result_table_header">
        
	        <th scope="col" width="15%">联动效果类型</th>
	        <th scope="col" width="10%" >联动时刻</th>
	        <th scope="col" width="10%">联动时长</th>
	        <th scope="col" >设备区位号</th>
	        <th scope="col" width="15%">操作</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body"  >
      
      <c:forEach items="${Pic_LinkageList}" var="element" varStatus="record" begin="0" end="26" step="1">
		<tr>
			<td >${element.multiScreenEffect}</td>
			<td >${element.synchronizationPlay}</td>
			
			<td id="time">${element.time}</td>
			  	
	        <td id="linkageCode">
	        <c:forEach items="${element.linkInfoList}" var="e" varStatus="record" begin="0" end="26" step="1">
	       				 ${e.playOrder} 
	         </c:forEach> 
	        </td>
			<td id="operation">
			<!--<a href="linkageDetail.jsp?linkageCode=${element.linkageCode}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
			--><a id="deleteRecord" href="javascript:deleteManager(${element.linkageCode});">删除</a>
			</td>
		</tr>
		 </c:forEach> 
		 
      </tbody>
    </table>
  </div>
  </div>
  
 <div id="query_result_footer" style="width: 936px;">
    <div id="query_result_footer_current_page">
   </div>
    <div style="float: right; padding-right: 20px;">
     	 当前记录总数:<span id="record_count"></span>条
    </div>
    </div>
  </div>
 
  
 </div><!-- 分边 -->
</div>
</form>
    </div>




	</div>
<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

</body>

</html>