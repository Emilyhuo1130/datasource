<%@page import="com.google.gson.stream.JsonWriter"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>查看历史日报</title>
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
	var userAccount = "<%=userAccount%>";
	var totalpages=1;
	var year=new Date().format("yyyy");
	var current_page=1;
	$(document).ready(function(){
		getAllUserName();
		getAllWeekNumber();
		conditionSearch(current_page);//根据条件查询
		pagination("current_page",current_page);
		setPageNum(current_page,$("#total_page").html());
			//alert("currenet_page="+current_page);
		
	});
	
	//按钮查询
	function conditionSearch(current_page){
		//alert("current_page1="+current_page);
		var condition={"userAccount":userAccount,
						"userName":$("#userName").val(),
						"year":year,
						"starTime":$("#startTime").val(),
						"endTime":$("#endTime").val(),
						"pageSize":"10",
						"current_page":current_page,
						"total_page":$("#total_page").html()};
		
					paging(current_page,condition);
				
			}
				
	//分页   页数独立传入
	function  paging(current_page , condition_json){
		$.ajax({  
	        url :"do_select_AllUserHistoryWeekTasks.do",  
	        type : "post",  
	        dataType:"json",  
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{
	        		"userAccount":userAccount,
        			"userName":condition_json.userName,
					"starTime":condition_json.starTime,
					"endTime":condition_json.endTime,
					"year":year,
					"pageSize":condition_json.pageSize,
					"page":current_page
			  }, 
	        success : function(response) {  
	        	$("table tbody tr td").html("");
	        	totalpages=(response.totalPage==0?1:response.totalPage);
	        	$("#total_page").html(totalpages);
	         	var taskInfo=response.taskParticularsList;
	         	taskInfo.forEach(function(e,i){
	        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(condition_json.current_page)-1)*10+i);
	        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.userName);
	        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.projectName);
	        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.finishHouse);
	        				$("table tbody tr:eq("+i+") td:eq(4)").html(e.taskDetail); 
	        				$("table tbody tr:eq("+i+") td:eq(4)").mouseover(function(){
		         				showFloatStack(this,e.taskDetail);
		         		
		         			});
	        	        	}); 
	        	         	
	        },  
	        error:function(XmlHttpRequest,textStatus, errorThrown)
            {
            }
	    }); 
	}
	
	//获取周方法
	function getAllWeekNumber(){
		$.ajax({  
	        url :"select_weeks.do",  
	        type : "post",  
	        dataType:"json",
			async:false, 
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{}, 
	        success : function(response) {  
	        	//alert(JSON.stringify(response));
	        	
	        	
	        },  
	        error : function(data) {  
	        }  
	    }); 
		
	}
	//分页显示 	//首页  上一页  下一页 末页
	function  setPageNum(current_page,total_page,condition){
		var condition={"userAccount":userAccount,
						"userName":$("#userName").val(),
						"year":year,
						"starTime":$("#startTime").val(),
						"endTime":$("#endTime").val(),
						"pageSize":"10",
						"current_page":current_page,
						"total_page":$("#total_page").html()};
		
		$("#first_page").click(function(){
			$("#current_page").html(1);
			conditionSearch(1,condition);
			
		});
		$("#pre_page").click(function(){
			prepage(current_page,$("#total_page").html(),condition);
		});
		$("#next_page").click(function(){
			//alert(JSON.stringify(condition));
			nextpage(current_page,$("#total_page").html(),condition);
		});
		$("#last_page").click(function(){
			//alert(JSON.stringify(condition));
			$("#current_page").html($("#total_page").html());
			conditionSearch($("#total_page").html(),condition);
		});
	};
	
	//获得所有用户名
	function getAllUserName(){
		$.ajax({  
	        url :"show_userName.do",  
	        type : "post",  
	        dataType:"json",
			async:false, 
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{}, 
	        success : function(response) {  
	        	//alert(JSON.stringify(response));
	        	var userNames = response.utils;
	        	var userName_json = eval("("+userNames+")");
	        	userName_json.forEach(function(e,i){
	        		$("#userName").append("<option value='"+e+"'>"+e+"</option>");
	        	}); 
	        },  
	        error : function(data) {  
	        }  
	    }); 
		
	}
</script>
</head>
<body>
	<body style="background-image: url('../../images/bank.jpg')">
	<div  id="attendees" class="formFrame" style="height:450px;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:550px;"><!-- 框架边框 -->
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
	<div id="search" style="position:relative;left:30px;top:50px;">
		 姓名：<select id="userName">
					<option value="">---请选择---</option>
				</select>
				 &nbsp;&nbsp;&nbsp;
	           &nbsp;&nbsp;&nbsp;
	           
	           日期：   <input type="Date" name="startTime" id="startTime" />
	        	  &nbsp;&nbsp;&nbsp; ——  &nbsp;&nbsp;&nbsp;
	            <input type="Date" name="endTime" id="endTime" />
	      		 <script>
	            	$("#startTime").val(new Date().format("yyyy-MM-dd"));
	            	$("#endTime").val(new Date().format("yyyy-MM-dd"));
	            </script>
	</div>
	<div style="position:relative;left:790px;top:30px;">
		<button name="search" id="search_button" class="button" onclick="conditionSearch(1);">查询</button>
	</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:70px;border-bottom:1px solid #888888;">
	
			</div>
			<table style="word-break:break-all;
					 border-color:#ccccff;
					 position:relative;
					 width:940px;
					 height:300px;
					 left:10px;
					 table-layout:fixed;"
					 cellpadding=0;
					 cellspacing=2 border="1"  class="tablesorter">
			<thead >
			
				<tr>
					<th width=5%>序号</th>
					<th width=10%>姓名</th>
					<th width=25%>项目名称</th>
					<th width=6.5%>工时</th>
					<th width=60%>完成事项</th>
				</tr>
			</thead>
			<tbody>
	
	
				<tr>
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
				</tr>
				<tr>
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
				</tr>
				<tr>
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
				</tr>
				<tr>
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
				</tr>
				<tr>
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
				</tr>
			</tbody>
			
			</table>
		 <!--分页-->
			 <div id="paginationArea" style="height:30px;"></div>
                <div id="pages">
                    <a href="javascript:;" id="first_page">首页</a>
        	        <a href="javascript:;" id="pre_page"> 上一页</a>
        	        第 <a href="javascript:;" id="current_page">1</a>页
                    <a href="javascript:;" id="next_page">下一页</a>
                    <a href="javascript:;" id="last_page">末页</a>
                   	 总页数:<a href="javascript:;" id="total_page">1</a>
                </div>    
                </div>
                </div>   
</body>
