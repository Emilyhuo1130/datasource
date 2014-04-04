<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  >
	<head>
		<title>查看本周任务</title>

		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
		<link type="text/css" rel="stylesheet" href="../../css/table.css" />
		<link href="../../css/sample.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
		<script type="text/javascript" src="../../js/common.js"></script>
		<script type="text/javascript" src="../../js/ucs_manage.js"></script>
		<script type="text/javascript">
		<%
		String userAccount=(String)(session.getAttribute("userAccount"));
		%>
		var userAccount="<%=userAccount%>";//转成字符串
		var year=new Date().format("yyyy");
		var current_page=1;
		$(document).ready(function(){
			
			getWeekNum();
			getThisweekMission($("#thisweek").html());
			//首页  上一页  下一页 末页
			$("#first_page").click(function(){
				paging(1,condition_init);
				$("#current_page").html(1);
				
			});
			$("#pre_page").click(function(){
				prepage(totalpages,condition_init);
				
			});
			$("#next_page").click(function(){
				nextpage(totalpages,condition_init);
				
			});
			$("#last_page").click(function(){
				paging(totalpages,condition_init);
				$("#current_page").html(totalpages);
			});
			
			
		    });
			
			
	//获取本周任务
	function getThisweekMission(weekNum){
		$.ajax({  
	        url :"do_findPlans.do",  
	        type : "post",  
	        dataType:"json",  
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{
	        	"userAccount":userAccount,
	        	"year":parseInt(year)+1,
	        	"WeekNumber": $("#thisweek").html(),
	        	"pageSize":"10",
        		"page":current_page
	        	
	        }, 
	        success : function(response){  
	        	//alert(JSON.stringify(response));
	        	var weekTasks=response.task;
	        	 weekTasks.forEach(function(e,i){
	        		$("#currentWeekMission tbody").append("<tr height=50 >"+
							"<td >"+e.projectName+"</td>"+
							"<td >"+
								"<textarea readonly='readonly'rows=5 cols=134>"+e.weekTask+"</textarea>"+
							"</td>"+
							"<td ></td>"+
						"</tr>");
	        		
	        	}); 
	        	
	        	},
	        error : function(data) {  
	        	//alert("无法获取本周任务");
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
				$("#thisweek").html(response.utils);	        	
	        },  
	        error : function(data) {  
	        	//alert("--------------error------------------!");
	        }  
	    }); 
	}
		
    </script>
	</head>
	
	<body  style="background-image: url('../../images/bank.jpg')">
		<div style="width:960px;height:45px;">
			<span  style="align-height:50px;vertical-align:middle;font-size:2.5em;color:purple;">本周是第<span id="thisweek" style="fontsize:2.8em;color:blue;"></span>周</span>
		</div>
		
		<div  id="attendees" class="formFrame" style="height:2250px;scrolling:none;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:2100px;"><!-- 框架边框 -->
			<div style="width:2px;height:2200px;position:absolute;left:-500px;top:500px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:100px;border-bottom:1px solid #888888;">
	
		</div>
		<br/>
			
			<div id="addMissoin" style="width:900px;height:600px;position:relative;left:30px;">
			
			
					<span class="span">本周任务</span>
					<!-- <textarea id="mission_thisWeek" style="width:700px;height:300px;resize:none;" name="missonThis" ></textarea> -->
					<table id="currentWeekMission" 
					style="
					width:880px;
					height:600px;
					overflow:hidden;
					table-layout:fixed;
					border:2px solid #aaaacc;" 
					cellpadding="3"
					border="1" 
					class="tablesorter" >
					<thead>
						<tr>
							<th width=20% >项目名称</th>
							<th width=80%>本周任务</th>
						</tr>
					</thead>
					<tbody >
					
					</tbody>
					</table>
			
		
			</div>
			</div>
	</div><!-- 框架边框终点 -->
	</body>
</html>