<%@page import="com.google.gson.stream.JsonWriter"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>本周任务详情</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="imagetoolbar" content="no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
	<link type="text/css" rel="stylesheet" href="../../css/table.css" />
	<link href="../../css/sample.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
	<script type="text/javascript" src="../../js/common.js"></script>
	<script type="text/javascript" src="../../js/ucs_manage.js"></script>
	<script type="text/javascript">
	<%
	
	String userAccount=(String)(session.getAttribute("userAccount"));
	String id=request.getParameter("id");
	%>
	var id='<%=id%>';
	var userAccount='<%=userAccount%>';
	var current_page=1;
	var totalpages=1;
	var year=new Date().format("yyyy");
	var weekNum=getWeekNum();
	$(document).ready(function(){
		
		initData();
		updatecurrentMission();
	});
	//初始加载数据
	function initData(){
			$.ajax({  
		        url :"do_getCurrentWeekMissionById.do",  
		        type : "post",  
		        dataType:"json",  
		        async:false,
		        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
		        data :{
		        		"id":id
		        	  }, 
		        success : function(response) {  
		  			//alert(JSON.stringify(response));
		  		 	var weekTask=response.taskInfo;
		  			$("#projectName").val(weekTask.projectName);
		  			$("#currentWeekMission").val(weekTask.weekTask); 
		        },
		        error:function(){
	               // alert("初始数据失败");
	            }
			});
			
		
	}
	//获取周号
	function getWeekNum(){
		$.ajax({  
	        url :"public/show_thisweek.do",  
	        type : "post",  
	        dataType:"json",
			async:false, 
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{}, 
	        success : function(response) {  
	        	//alert(JSON.stringify(response));
				return response.utils;	        	
	        },  
	        error : function(data) {  
	        	//alert("无法获取周号");
	        }  
	    }); 
	}
	//更新操作
	function  updatecurrentMission(){
		$("#save").click(function(){
			$.ajax({  
		        url :"do_updateCurrentWeekMission.do",  
		        type : "post",  
		        dataType:"json",  
		        async:false,
		        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
		        data :{
		        		"id":id,
		        		"projectName":$("#projectName").val(),
		        		"userAccount":userAccount,
		        		"weekNum":weekNum,
		        		"weekTask":$("#currentWeekMission").val()
		        	  }, 
		        success : function(response) {  
		  			alert("修改成功");
		  			setTimeout(function(){
	  					location.href="lookThisWeekMission.jsp";
	  				});
		        },
		        error:function(){
	               // alert("admin/do_updateProject.do无法进入success;");
	            },
			});
			
		});
		
	}
</script>
</head>
	<body style="background-image: url('../../images/bank.jpg')">
	<div  id="attendees" class="formFrame" style="height:1050px;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:1000px;"><!-- 框架边框 -->
		
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:500px;z-index:10;"><!-- 左边线 -->
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
					<th  >本周任务</th>
				</tr>
			</thead>
			<tbody>
				<tr height=10% >
					<td >项目名称</td>
					<td><input style="width:100%;height:100%;background-color:#efefef;" type="text" name="projectName"  id="projectName" readonly="readonly" value=""/></td>
								
				</tr>
				<tr  height=60%>
					<td>本周任务</td>
					<td><textarea  style="width:100%;height:100%;background-color:#c7edcc; "  name="currentWeekMission"  id="currentWeekMission"></textarea></td>
					
				</tr>
				<tr  height=30%>
					
					<td colspan="2"> <button style="position:relative;width:120px;height:30px;" name="save"  id="save" >保存</button></td>
					
				</tr>
			</tbody>
			
			</table>
			</div><!-- 表格定位终点 -->
          </div>
	</div><!-- 框架边框终点 -->       
</body>
