<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>项目统计明细</title>

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
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			String year=request.getParameter("year");
		%>
		<script type="text/javascript">
			var time_year=new Date().format("yyyy-MM-dd hh:mm:ss");
			var startTime="<%=startTime%>";
			var endTime="<%=endTime%>";
			var projectName ="<%=projectName%>";
			var year=<%=year%>;
			var current_page=1;
			var totalpages=1;
			startTime=(startTime==""?year+"-01-01":startTime);
			endTime=(endTime==""?year+"-12-31":endTime);
			//alert("startTime="+startTime+","+"endTime="+endTime);
			$(document).ready(function(){
				fillContentToSelect();
				var page_init={"projectName":"","startTime":startTime,"endTime":endTime};
				pagination("current_page",current_page);//显示页码  点哪一页就显示哪页的数据
				$("#datamsg").html("日期："+(startTime==""?year+'-01-01':startTime)+"  ——  "+(endTime==""?year+'-12-31':endTime));
				//获得总页数
				totalpages=getTotalPages(page_init);
				conditionSearch(current_page);
				setPageNum(current_page,totalpages);
				
			});
			
			//按钮查询
			function conditionSearch(current_page){
						
							var condition_json_projectName=projectName;
							var condition_json_userName=$("#userName").val();
							if(condition_json_userName=="---请选择---"){
								condition_json_userName="";
							}
							var condition_json_startTime=startTime;
							var condition_json_endTime=endTime;
							var condition={"projectName": condition_json_projectName,"userName":condition_json_userName,"starTime":condition_json_startTime,"endTime":condition_json_endTime};
							paging(current_page,condition);
							
						
					}
			//初始加载数据
			function paging(current_page,condition_json){
				var req_json={"action":"do_Project_details_List.do","data":{}
				};
			
				$.ajax({  
			        url :req_json.action,  
			        type : "post",  
			        dataType:"json",  
			        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
			        data:{ 
			        		"userName":condition_json.userName,
			        		"projectName":condition_json.projectName,
							"starTime":condition_json.starTime,
							"endTime":condition_json.endTime,
							"year":year,
							"pageSize":"10",
							"page":current_page
				  }, 
			        success : function(response) {  
			        	//alert(JSON.stringify(response));
			        	$("table tbody tr td").html("");
			        	var item_info=response.statistics.detailsList;
			         	item_info.forEach(function(e,i){
			        				$("table tbody tr:eq("+i+") td:eq(0)").html(i+1);
			        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.projectName);
			        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.userName);
			        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.days); 
			        				$("table tbody tr:eq("+i+") td:eq(4)").html("<a href='"+encodeURI("itemPerDetails.jsp?projectName="+e.projectName+"&startTime="+startTime+"&endTime="+endTime+"&year="+year+"&userName="+e.userName)+"'>查看详细</a>"); 
			        
			        				});  
			        },  
			        error : function(data) {  
			        }  
			    });
				
			}
			//下拉列表中填入内容   用户姓名
			function  fillContentToSelect(index){
				$.ajax({  
			        url :"show_userName.do",  
			        type : "post",  
			        dataType:"json",  
			        async:false,
			        contentType: "application/x-www-form-urlencoded;charset=utf-8", 
			        data :{  }, 
			        success : function(response) {  
			        	//alert(JSON.stringify(response));
			        	var names=response.utils;
			        		var names_json=eval("("+names+")");
			        		//alert(typeof names_json);
			        		names_json.forEach(function(e,i){
			        		$("#userName").append("<option value="+e+">"+e+"</option>");
			        		
			        	});
			        
			        },  
			        error:function()
		            {
			        	//alert("下拉列表中填入内容出错");
			        	 
		            }
			    }); 
				
			}
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
			        	//alert(JSON.stringify(response));
			        	totalpage=response.statistics;
			        	$("#total_page").html(totalpage-1);
			        	
			        },  
			        error:function(XmlHttpRequest,textStatus, errorThrown)
		            {
		               // alert("无法获得总页数");
		            }
			    }); 
			       		 return totalpage-1;//返回总页数
				
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
		<!-- 项目名称：<select id="projectName">
					<option value=""></option>
				</select>
				 &nbsp;&nbsp;&nbsp; 
				 &nbsp;&nbsp;&nbsp;  -->
	            姓名：<select id="userName">
					<option value="">---请选择---</option>
				</select>
	           
	<div><button style="position:relative;left:700px;" name="search" id="search_button" class="button"  onclick="conditionSearch(1);">查询</button ></div>
		<div style="position:relative;left:0px;top:60px;"><span style="background-color:#cdcfee;font-size:1.5em;" id="datamsg"></span></div>
	</div>
	
	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:70px;border-bottom:1px solid #888888;">
			<div style="position:relative;left:10px;top:130px;">
			<table style="width:940px;height:300px;" border=1 cellspacing=0 class="tablesorter" >
			<thead >
			
				<tr>
				<th>序号</th>
				<th>项目</th>
				<th>姓名</th>
				<th>工时统计(天)</th>
				<th>查看详细</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width=10%></td>
					<td width=20%></td>
					<td width=30%></td>
					<td width=20%></td>
					<td width=20%></td>
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