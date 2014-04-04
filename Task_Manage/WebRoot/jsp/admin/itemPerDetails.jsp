<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>个人每日项目统计明细</title>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
		<link type="text/css" rel="stylesheet" href="../../css/table.css" />
		<link href="../../css/sample.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
		<script type="text/javascript" src="../../js/common.js"></script>
		<script type="text/javascript" src="../../js/ucs_manage.js"></script>
		 <%
		 String  projectName  = new String(request.getParameter("projectName").getBytes("ISO-8859-1"),"utf-8");
		 String  userName  = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8");
		 String startTime=request.getParameter("startTime");
		 String endTime=request.getParameter("endTime");
		 String year=request.getParameter("year");
		%> 
		<script type="text/javascript">
			var time_year=new Date().format("yyyy-MM-dd hh:mm:ss");
			var userName="<%=userName%>";
			var year=<%=year%>;
			var current_page=1;
			var totalpages=1;
			var startTime="<%=startTime%>";
			var endTime="<%=endTime%>";
			var projectName="<%=projectName%>";
			startTime=(startTime==""?year+"-01-01":startTime);
			endTime=(endTime==""?year+"-12-31":endTime);
	
			$(document).ready(function(){
				var page_init={"projectName":"","startTime":startTime,"endTime":endTime};
				totalpages=getTotalPages(page_init);
				pagination("current_page",current_page);//显示页码  点哪一页就显示哪页的数据
				conditionSearch(current_page);
				setPageNum(current_page,totalpages-1);
			});
			
			//获取总页数
			function getTotalPages(page_json){
				var req_json={"action":"do_Project_Statisticspages.do","data":{}};
				var totalpage=1;
				$.ajax({  
			        url :req_json.action,  
			        type : "post",  
			        dataType:"json",  
			        async:false,
			        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
			        data :{ 
			        	"projectName":page_json.projectName,
						"startTime":page_json.startTime,
						"endTime":page_json.endTime,
						"year":year,
						"pageSize":"10"
						  }, 
			        success : function(response) {  
			        	totalpage=response.statistics;
			        	$("#total_page").html(totalpage-1);
			        	
			        },  
			        error:function(XmlHttpRequest,textStatus, errorThrown)
		            {
		            }
			    }); 
			       		 return totalpage;//返回总页数
				
			}
			//按钮查询
			function conditionSearch(current_page){
							var condition_json_projectName=projectName;
							var condition_json_startTime=$("#startTime").val();
							var condition_json_endTime=$("#endTime").val();
							var condition={"projectName": condition_json_projectName,"userName":userName,"starTime":condition_json_startTime,"endTime":condition_json_endTime};
							
							paging(current_page,condition);
							
			}
			function  paging(current_page,condition_json){
				//alert(condition_json.projectName+","+condition_json.starTime+","+condition_json.endTime);
				var req_json={"action":"do_Project_one_details.do","data":{}
				};
				$.ajax({  
			        url :req_json.action,  
			        type : "post",  
			        dataType:"json",  
			        async:false,
			        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
			        data :{  
			        		"projectName":condition_json.projectName,  
			        		 "userName":condition_json.userName,
			            	 "pageSize":"10",  
			          		 "page":current_page,     
			           		 "year":year,            
			           		 "startTime":condition_json.starTime,
			           		 "endTime":condition_json.endTime
						  }, 
						 
			        success : function(response) {  
			        	//alert(JSON.stringify(response));
			        	$("table tbody tr td").html("");
			        	var item_info=response.statistics;
			         	item_info.forEach(function(e,i){
			        				$("table tbody tr:eq("+i+") td:eq(0)").html(i+1);
			        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.farmatDate);
			        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.finishHouse);
			        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.taskDetail); 
			        	        	});  
			        //项目列表代写    重复的删除掉 数组扩容 新建一个数组 每放一个进去就与新数组中每一个元素比较，不同就放入，有相同的就不放
			        	         	//设置当前页页码的样式  必须
				        	    	$("#page"+current_page).css({"background-color":"#eeeeff"});
			        	         	$("#page"+current_page).siblings().css({"background-color":"#ccccee"});
			        	         	
			        },  
			        error:function(XmlHttpRequest,textStatus, errorThrown)
		            {
		               // alert("分页paging出问题");
		            }
			    }); 
				
			}
			
			
			//分页显示 	//首页  上一页  下一页 末页
			function  setPageNum(current_page,total_page){
				
				$("#first_page").click(function(){
					$("#current_page").html(1);
					conditionSearch(1);
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

	<body style="background-image: url('../../images/bank.jpg')">
	<div  id="attendees" class="formFrame" style="height:650px;scrolling:none;">
	<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
	</div>
	<div style="border:1px solid #999999; width:958px;height:600px;"><!-- 框架边框 -->
	<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
	</div>
	<div id="search" style="position:relative;left:30px;top:30px;">
	  	  日期：<input type="Date" name="startTime" id="startTime"/>
	  	   
	        	&nbsp;&nbsp;&nbsp; —— &nbsp;&nbsp;&nbsp;
	           
	          <input type="Date" name="endTime" id="endTime" />
	  	  		
	<div><button style="position:relative;left:700px;" name="search" id="search_button" class="button" onclick="conditionSearch(1);">查询</button ></div>
	
	</div>
	<div style="position:relative;left:30px;top:100px;">用户名：<%=userName%></div>
	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:100px;border-bottom:1px solid #888888;">
			<div style="position:relative;left:10px;top:130px;">
			<table style="width:940px;height:300px;" border=1 cellspacing=0 class="tablesorter" >
			<thead >
			
				<tr>
				<th>序号</th>
				<th>日期</th>
				<th>工时统计(小时)</th>
				<th>当日完成任务</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width=10%></td>
					<td width=20%></td>
					<td width=20%></td>
					<td width=50%></td>
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
	 <div style="position:relative;left:30px;top:25px;">
		<!--  <span id="current_page" style="display:none;">1</span> -->
			 <div id="paginationArea" style="height:20px;"></div>
                <div id="pages">
                    <a href="javascript:;" id="first_page">首页</a>
        	        <a href="javascript:;" id="pre_page"> 上一页</a>
        	         <a href="javascript:;" id="current_page">1</a>
                    <a href="javascript:;" id="next_page">下一页</a>
                    <a href="javascript:;" id="last_page">末页</a>
                    	  总页数:<a href="javascript:;" id="total_page">1</a>
                </div>  
                </div>  
                </div>
                </div>
                </div>
                </div><!-- 框架定位终点 -->  
	</body>
</html>