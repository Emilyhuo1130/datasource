<%@page import="com.google.gson.stream.JsonWriter"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>任务详情</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="imagetoolbar" content="no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
	<link type="text/css" rel="stylesheet" href="../../css/table.css" />
	<link href="../../css/sample.css" rel="stylesheet" type="text/css" />
	 <style type="text/css">
    	.suc_info{
    			position:absolute;
    			z-index:100;
	        	width:400px;
	        	display:none;
	        	height:60px;
	        	left:25%;
	        	top:25%;
	        	border:3px solid purple;
	        	background-color:#c7edcc; 
    	
    	
    	}
    	.suc_info span{
    		position:relative;
    		left:33%;
    		top:20%;
    		
    		color:purple;
    		text-align:center;
    		vertical-align:middle;
    		font-weight:bold;
    		font-size:2.5em;
    	}
    </style>
	<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
	<script type="text/javascript" src="../../js/common.js"></script>
	<script type="text/javascript" src="../../js/ucs_manage.js"></script>
	<script type="text/javascript">
	<%
	
	String userAccount=(String)(session.getAttribute("userAccount"));
	String date=request.getParameter("date");
	String projectName=new String(request.getParameter("projectName").getBytes("iso8859-1"),"utf-8");
	String nowadayHour=request.getParameter("finishHour");
	String id=request.getParameter("id");
	String userName=new String(request.getParameter("userName").getBytes("iso8859-1"),"utf-8");
	%>
	var date='<%=date%>';
	var projectName='<%=projectName%>';
	var nowadayHour='<%=nowadayHour%>';
	var id='<%=id%>';
	var userName='<%=userName%>';
	var userAccount='<%=userAccount%>';
	var current_page=1;
	var totalpages=1;
	var year=new Date().format("yyyy");
	$(document).ready(function(){
		$(document.body).append("<div id='suc_info' class='suc_info'><span >修改成功</span></div>");
		fillContentToSelect();
		initData();
		updateHistoryMission();
	});
	//初始加载数据
	function initData(){
			$.ajax({  
		        url :"do_getHistoryMissionById.do",  
		        type : "post",  
		        dataType:"json",  
		        async:false,
		        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
		        data :{
		        		"id":id
		        	  }, 
		        success : function(response) {  
		  			//alert(JSON.stringify(response));
		  			var userDetail=response.oneInfo;
		  			$("#nowaDate").val(userDetail.farmatDate);
		  			$("#projectName").val(userDetail.projectName);
		  			$("#nowadayHour").val(userDetail.finishHouse);
		  			$("#nowadayMission").html(userDetail.taskDetail);
		        },
		        error:function(){
	            }
			});
			
		
	}
	//下拉列表中填入项目名称
	function  fillContentToSelect(){
		$.ajax({  
	        url :"public/select_project.do",  
	        type : "post",  
	        dataType:"json",  
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8", 
	        data :{  }, 
	        success : function(response) {  
	        	//alert(JSON.stringify(response));
	        	var items=response.utils;
	        	items.forEach(function(e,i){
	        		$("#projectName").append("<option value="+e+">"+e+"</option>");
	        		
	        	});
	        
	        },  
	        error:function(XmlHttpRequest,textStatus, errorThrown)
            {
	        	
	        	 
            }
	    }); 
		
	}
	//更新操作
	function  updateHistoryMission(){
		$("#save").click(function(){
			$.ajax({  
		        url :"do_updateHistoryMission.do",  
		        type : "post",  
		        dataType:"json",  
		        async:false,
		        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
		        data :{
		        		"userAccount":userAccount,
		        		"farmatDate":$("#nowaDate").val(),
		        		"commitDate":null,
		        		"projectName":$("#projectName").val(),
		        		"userName":userName,
		        		"finishHouse":$("#nowadayHour").val(),
		        		"taskDetail":$("#nowadayMission").val(),
		        		"id":id
		        	  }, 
		        success : function(response) {  
		        	$("#suc_info").fadeIn("slow");
		        	setTimeout(function(){
		        		location.href="showWeekMissionList.jsp";
	  				},1500);
		        },
		        error:function(){
	               // alert("admin/do_updateProject.do无法进入success;");
	            },
			});
			
		});
		
	}
</script>
</head>
<body>
	<body style="background-image: url('../../images/bank.jpg')">
	<div  id="attendees" class="formFrame" style="height:750px;scrolling:none;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:650px;"><!-- 框架边框 -->
		
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:50px;border-bottom:1px solid #888888;"></div>
			<div style="position:relative;left:10px;"><!-- 表格定位 -->
			<table style="word-break:break-all;border-color:#ccccff;width:940px;height:500px;" cellpadding=0;
			cellspacing=2 border="1"  class="tablesorter" >
			<thead >
			
				<tr>
					<th width="90px" ></th>
					<th  >当日任务详情</th>
				</tr>
			</thead>
			<tbody>
				<tr height=5% >
					<td>日期</td>
					<td><input style="background-color:#ccccff;width:31%;position:relative;left:-286px;top:12px;" type="date" name="date"  id="nowaDate" value=""/></td>
								
				</tr>
				<tr height=5%>
					<td>项目名称</td>
					<td><!-- <input style="width:100%;height:100%;" type="text" name="projectName"  id="projectName"  value=""/> -->
					<select id="projectName" name="projectName"  style="background-color:#ccccff;width:31%;position:relative;left:-288px;top:12px;">
					</select>
					<script>
						
					</script>
					</td>
				</tr>
				<tr height=5%>
					<td>当日工时(时)</td>
					<td><input type="text" style="width:100%;height:100%;" name="nowadayHour"  id="nowadayHour"  value=""/></td>
					
				</tr>
				<tr  height=50%>
					<td>当日完成任务</td>
					<td><textarea  style="width:100%;height:100%;background-color:#c7edcc; "  name="nowadayMission"  id="nowadayMission"></textarea></td>
					
				</tr>
				<tr  height=20%>
					
					<td colspan="2"> <button style="position:relative;width:120px;height:30px;" name="save"  id="save" >更新</button></td>
					
				</tr>
			</tbody>
			
			</table>
			</div><!-- 表格定位终点 -->
                	</div>
	</div><!-- 框架边框终点 -->       
</body>
