<%@page import="com.google.gson.stream.JsonWriter"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>查看用户任务</title>
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
		var current_page=1;
		var totalpages=1;
		var weekNumber=1;
		var userAccount = "<%=userAccount%>";
		var year=new Date().format("yyyy");
	$(document).ready(function(){
		getAllUserName();//获得用户名列表
		getAllWeekNumber();//获得周列表
		weekNumber=getWeekNumber();//当前显示第几周
		pagination("current_page",current_page);//给页码赋初值
		conditionSearch(current_page);//根据条件查询
		setPageNum($("#current_page").val(),totalpages);
	});
	
		//按钮
	function conditionSearch(current_page){
		
				var condition={"userName":$("#userName option:selected").val(),
											"startweekNo":$("#startWeek").val(),
											"endweekNo":$("#endWeek").val(),
											"pageSize":"10",
											"current_page":current_page};
				
					paging(current_page,condition);
				
			}
				
	//分页
	function  paging(current_page,condition_json){
		//alert("stweek="+condition_json.startweekNo);
		$.ajax({  
	        url :"do_select_AllUserHistoryTasks.do",  
	        type : "post",  
	        dataType:"json",  
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{
	        		"userName":condition_json.userName,
					"startweekNo": condition_json.startweekNo ,
					"endweekNo":condition_json.endweekNo ,
					"year":year,
					"pageSize":condition_json.pageSize,
					"page":current_page
				  }, 
	        success : function(response) {  
	        	$("table tbody tr td").html("");
	         	var taskInfo=response.weektaskList;
	         	totalpages = response.totalPage;
	         	$("#total_page").html(totalpages);
	         	taskInfo.forEach(function(e,i){
	        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(condition_json.current_page)-1)*10+i);
	        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.userAccount);
	        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.projectName);
	        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.weekTask);
	        				$("table tbody tr:eq("+i+") td:eq(3)").mouseover(function(){
		         				showFloatStack(this,e.weekTask);
		         		
		         			});
	        	        	}); 
	        	         	
	        },  
	        error:function(XmlHttpRequest,textStatus, errorThrown)
            {
            }
	    }); 
		
	}
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
	        	var userNames = response.utils;
	        	var userName_json = eval("("+userNames+")");
	        	userName_json.forEach(function(e,i){
	        		$("#userName option:eq(0)").after("<option value="+e+">"+e+"</option>");
	        	}); 
	        },  
	        error : function(data) {  
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
	        	var weekNumbers = response.utils;
	        	weekNumbers.forEach(function(e,i){
	        		$("#startWeek").append("<option value='"+e+"'>第"+e+"周</option>");
	        		$("#endWeek").append("<option value='"+e+"'>第"+e+"周</option>");
	        	});
	        },  
	        error : function(data) {  
	        }  
	    }); 
		
	}
	//显示第几周
	function getWeekNumber(){
		var thisWeekNumber=1;
		$.ajax({  
	        url :"public/show_thisweek.do",  
	        type : "post",  
	        dataType:"json",
			async:false, 
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{}, 
	        success : function(response) {  
	        	thisWeekNumber = response.utils;
	        	//alert("thisWeekNumber="+thisWeekNumber);
	        	$("#startWeek option").each(function(i,e){
	        		var $e=$(e);
	        		if($e.val()==thisWeekNumber){
	        			$("#startWeek option:eq("+i+")").attr("selected","selected");
	        			$("#endWeek option:eq("+i+")").attr("selected","selected");
	        		}
	        		
	        	});
	        	
	        	
	        },  
	        error : function(data) {  
	        }  
	    }); 
		return thisWeekNumber;
	}
	//分页显示 	//首页  上一页  下一页 末页
	function  setPageNum(current_page,total_page){
		
		$("#first_page").click(function(){
			$("#current_page").html(1);
			conditionSearch(1,condition);
		});
		$("#pre_page").click(function(){
			prepage(current_page,total_page);
			
		});
		$("#next_page").click(function(){
			//alert(JSON.stringify(condition));
			nextpage(current_page,total_page);
		});
		$("#last_page").click(function(){
			//alert(JSON.stringify(condition));
			$("#current_page").html(total_page);
			conditionSearch(total_page);
		});
	};
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
		 姓名：<select name="name" id="userName" value="">
					<option value="">---请选择---</option>
				</select>
				 &nbsp;&nbsp;&nbsp;
	           &nbsp;&nbsp;&nbsp;
	            日期： <input type="Date" name="date" id="date"  readonly="readonly" style="background-color:#ececec;"/>
	            <script>
	            	$("#date").val(new Date().format("yyyy-MM-dd"));
	            </script>
	           
	      周查询：    <select name="startWeek" id="startWeek">
	      		
	      		</select>
	        	  &nbsp;&nbsp;&nbsp; ——  &nbsp;&nbsp;&nbsp;
	            <select name="endWeek" id="endWeek">
	      		
	      		</select>
	</div>
		<div style="position:relative;left:790px;top:30px;">
				<button name="search_btn" id="search_button" class="button"  onclick="conditionSearch(1);">查询</button>
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
					<th  width=10%>序号</th>
					<th  width=15%>姓名</th>
					<th  width=25%>项目名称</th>
					<th  width=65%>本周任务</th>
				</tr>
			</thead>
			<tbody>
	
				<tr>
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
				</tr>
				<tr>
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
				</tr>
				<tr>
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
				</tr>
				<tr>
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
				</tr>
				<tr>
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
				</tr>
			</tbody>
			
			</table>
		 <!--分页-->
			 <div id="paginationArea" style="height:50px;"></div>
                <div id="pages">
                    <a href="javascript:;" id="first_page">首页</a>
        	        <a href="javascript:;" id="pre_page"> 上一页</a>
        	  	       第<a href="javascript:;" id="current_page">1</a>页
                    <a href="javascript:;" id="next_page">下一页</a>
                    <a href="javascript:;" id="last_page">末页</a>
                    	 总页数:<a href="javascript:;" id="total_page">1</a>
                </div>    
                </div>
                </div>   
</body>
