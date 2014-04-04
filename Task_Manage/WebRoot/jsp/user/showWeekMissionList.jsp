<%@page import="com.google.gson.stream.JsonWriter"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>查看已完成任务</title>
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
	%>
	var userAccount="<%=userAccount%>";
	var current_page=1;
	var totalpages=1;
	var year=new Date().format("yyyy");
	$(document).ready(function(){
		var condition_init={"userAccount":userAccount,"projectName":"","year":year,"userName": "","starTime":"","endTime":""};
		paging(current_page,condition_init);//分页显示
		pagination("current_page",current_page,totalpages);//显示页码  点哪一页就显示哪页的数据
		conditionSearch();//根据条件查询
		setPageNum(current_page,totalpages,condition_init);   
	});

	//分页显示
	function  paging(current_page,condition_json){
		var req_json={"action":"do_findHistoryTask.do","data":{}};
		var totalPage=1;
		$.ajax({  
	        url :req_json.action,  
	        type : "post",  
	        dataType:"json",  
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{ 
	        		"userAccount":userAccount,
	        		"projectName":condition_json.projectName,
					"startTime":condition_json.starTime,
					"endTime":condition_json.endTime,
					"year":year,
					"pageSize":"10",
					"page":current_page
				  }, 
	        success : function(response) {  
	     		//alert(JSON.stringify(response));
	     		if(response==null){
	     			$("table tbody tr td").html("");
	     		}
	        	$("table tbody tr td").html("");
	         	var taskInfo=response.tasklist;
	         	totalpages=response.pages;
	         	//alert("current_page*10="+parseInt(current_page)*10);
	         	//插入之前清空列表
	         	$("table tbody tr td").html("");
	         	taskInfo.forEach(function(e,i){
	         		$("table tbody tr:eq("+i+") td:eq(0)").html((current_page-1)*10+i+1);
    				$("table tbody tr:eq("+i+") td:eq(1)").html(e.farmatDate); 
    				$("table tbody tr:eq("+i+") td:eq(2)").html(e.projectName);
    				$("table tbody tr:eq("+i+") td:eq(3)").html(e.finishHouse); 
    				$("table tbody tr:eq("+i+") td:eq(4)").html(e.taskDetail); 
    				$("table tbody tr:eq("+i+") td:eq(5)").html("<a href='"+ encodeURI("weekMissionDetails.jsp?&date="+e.farmatDate+"&userName="+e.userName+"&userAccount="+userAccount+"&id="+e.id+"&projectName="+e.projectName+"&finishHour="+e.finishHouse)+"'>查看</a>"); 
    				$("table tbody tr:eq("+i+") td:eq(6)").html("<a href='javascript:deleteHistoryMission("+e.id+","+i+");'>删除</a>"); 
	        //项目列表代写    重复的删除掉 数组扩容 新建一个数组 每放一个进去就与新数组中每一个元素比较，不同就放入，有相同的就不放
	        
	        	        	}); 
	        	         	//设置当前页页码的样式
		        	    	$("#page"+current_page).css({"background-color":"#eeeeff"});
	        	         	$("#page"+current_page).siblings().css({"background-color":"#ccccee"});
	        },  
	        error:function(XmlHttpRequest,textStatus, errorThrown)
            {
                //alert("初始数据异常");
            }
	    }); 
		
		return totalPage;
		
	}
 	//按钮查询
	function conditionSearch(){
				$("#search_info").click(function(){
					var condition_json_projectName=$("#projectName").val();
					var condition_json_startTime=$("#startTime").val();
					var condition_json_endTime=$("#endTime").val();
					var condition={"userAccount":userAccount,"projectName": condition_json_projectName,"starTime":condition_json_startTime,"endTime":condition_json_endTime};
					paging(current_page,condition);
					setPageNum(current_page,totalpages,condition);
					
				});
				
			}
	//删除历史任务
	function deleteHistoryMission(id,index){
		var req_json={"action":"do_deleteHistoryMission.do","data":{}};
		if(confirm("确定要删除该记录吗？")){
			$.ajax({  
		        url :req_json.action,  
		        type : "post",  
		        dataType:"json",  
		        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
		        data :{"id":id}, 
		        success : function(response) { 
		        	alert("删除成功");
		        	 setTimeout(function(){
		        		location.reload();
		        	},500); 
		        },  
		        error : function(data) {  
		            alert("删除失败");  
		        }  
		    });
		}{
			
		};
	 
	}
	//分页显示 	//首页  上一页  下一页 末页
	function  setPageNum(current_page,total_page,condition){
		
		$("#first_page").click(function(){
			paging(1,condition);
			$("#current_page").html(1);
		});
		$("#pre_page").click(function(){
			prepage(current_page,totalpages,condition);
			
		});
		$("#next_page").click(function(){
			nextpage(current_page,total_page,condition);
		});
		$("#last_page").click(function(){
			paging(total_page,condition);
			$("#current_page").html(total_page);
		});
	}
</script>
</head>
<body>
	<body style="background-image: url('../../images/bank.jpg')">
	<div  id="attendees" class="formFrame" style="height:450px;scrolling:none;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:500px;"><!-- 框架边框 -->
		
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<div id="search">
			<div style="position:relative;left:50px;top:20px;"><!-- 查询条件 -->
		             开始日期： <input type="Date" id="startTime" name="startTime" value=""/>
		         &nbsp;&nbsp;&nbsp; ——  &nbsp;&nbsp;&nbsp;
		           
		             截止日期: <input type="Date" id="endTime" name="endTime" value="" />
		           <script>
		           		$("#startTime").val(new Date().format("yyyy-MM-dd"));
		           		$("#endTime").val(new Date().format("yyyy-MM-dd"));
		           </script>
		<div style="position:relative;left:790px;">
			<button name="search" id="search_info" class="button" >查询</button>
		</div>
		</div><!-- 查询条件终点 -->
	
	</div>
			<div style="position:relative;left:0px;width:960px;height:50px;border-bottom:1px solid #888888;"></div>
			<div style="position:relative;left:10px;"><!-- 表格定位 -->
			<table style="word-break:break-all;
			border-color:#ccccff;
			width:940px;height:300px;
			 table-layout:fixed;
			 overflow:hidden;" 
			cellpadding=0;
			cellspacing=2 border="1"  class="tablesorter" >
			<thead >
			
				<tr>
				<th width="5%">序号</th>
				<th width="10%">日期</th>
				<th width="10%">项目名称</th>
				<th width="10%">当日工时(时)</th>
				<th width="45%">当日完成任务</th>
				<th colspan="2" width="10%">操作</th>
				
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
			
			</table>
			</div><!-- 表格定位终点 -->
		 <!--分页-->
		<!--  <span id="current_page" style="display:none;">1</span> -->
			 <div id="paginationArea" style="height:20px;"></div>
                <div id="pages">
                    <a href="javascript:;" id="first_page">首页</a>
        	        <a href="javascript:;" id="pre_page"> 上一页</a>
        	         <a href="javascript:;" id="current_page">1</a>
                    <a href="javascript:;" id="next_page">下一页</a>
                    <a href="javascript:;" id="last_page">末页</a>
                </div>  
                	</div>
	</div><!-- 框架边框终点 -->       
</body>
