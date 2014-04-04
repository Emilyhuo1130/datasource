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
	String projectName=new String(request.getParameter("projectName").getBytes("iso8859-1"),"utf-8");
	String id=request.getParameter("id");
	String userName=new String(request.getParameter("userName").getBytes("iso8859-1"),"utf-8");
	%>
	var projectName='<%=projectName%>';
	var id='<%=id%>';
	var userName='<%=userName%>';
	var userAccount='<%=userAccount%>';
	var current_page=1;
	var totalpages=1;
	var year=new Date().format("yyyy");
	var weekNo=parseInt(getWeekNum())+1;
	$(document).ready(function(){
		$(document.body).append("<div id='suc_info' class='suc_info'><span >更新成功</span></div>");
		
		fillContentToSelect();
		initData();
		updateHistoryMission();
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
	        success : function(response){  
	        	//alert(JSON.stringify(response));
	        	var weekPlan=response.taskInfo;
	        	$("#weekNo").val(weekPlan.weekNo);
	        	$("#projectName").val(weekPlan.projectName);
	        	$("#nextMissionPlan").val(weekPlan.weekTask);
	        	},
	        error : function(data) {  
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
	//获取周号
	function getWeekNum(){
		var nextNo=0;
		$.ajax({  
	        url :"public/show_thisweek.do",  
	        type : "post",  
	        dataType:"json",
			async:false, 
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{}, 
	        success : function(response) {  
	        	//alert(JSON.stringify(response));
				$("#weekNo").val(response.utils);	
				nextNo=response.utils;
	        },  
	        error : function(data) {  
	        	//alert("--------------error------------------!");
	        }  
	    }); 
		return nextNo;
	}
	//更新操作
	function  updateHistoryMission(){
		$("#save").click(function(){
			$.ajax({  
		        url :"do_updateCurrentWeekMission.do",  
		        type : "post",  
		        dataType:"json",  
		        async:false,
		        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
		        data :{
		        		"userAccount":userAccount,
		        		"projectName":$("#projectName").val(),
		        		"WeekNumber":weekNo,
		        		"weekTask":$("#nextMissionPlan").val(),
		        		"id":id
		        	  }, 
		        success : function(response) {  
		        	$("#suc_info").fadeIn("slow");
		        	setTimeout(function(){
		        		location.href="showWeekMissionPlanList.jsp";
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
			<table id="nextPlan" style="
			word-break:break-all;
			border-color:#ccccff;
			width:940px;
			table-layout:fixed;
			overflow:hidden;
			height:500px;" cellpadding=0;
			cellspacing=2 border="1"  class="tablesorter" >
			<thead >
			
				<tr>
					<th width="90px" ></th>
					<th >下周计划</th>
				</tr>
			</thead>
			<tbody>
				<tr height=5% >
					<td>周号</td>
					<td><input style="width:100%;height:100%;background-color:#cccccc;" type="text" name="weekNo"  id="weekNo" readonly="readonly" value=""/></td>
								
				</tr>
				<tr height=5%>
					<td>项目名称</td>
					<td>
						<select id="projectName" name="projectName"  style="background-color:#ccccff;width:31%;position:relative;left:-288px;top:15px;">
						</select>
					</td>
					
				</tr>
				<tr  height=50%>
					<td>下周计划</td>
					<td><textarea  style="width:100%;height:100%;background-color:#c7edcc; "  name="nowadayMission"  id="nextMissionPlan"></textarea></td>
					
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
