
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>项目统计</title>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
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
	var year=new Date().format("yyyy");
	var userAccount="<%=userAccount%>";
	var current_page=1;
	var totalpages=1;
	$(document).ready(function(){
		fillContentToSelect();
		//初始化数据之后 进行显示
		var page_init={"projectName":"","startTime":$("#startTime").val(),"endTime":$("#endTime").val()};
		totalpages=getTotalPages(page_init);
		
		pagination("current_page",current_page);//显示页码  点哪一页就显示哪页的数据
		conditionSearch(current_page);//根据条件查询
		setPageNum($("#current_page").val(),totalpages);
		
	});
	
	//按钮查询
	function conditionSearch(current_page){
				/* 	
 			   	$("#projectName option").each(function(i,e){
						var $e = $(e);
						if($e.val()==$("#projectName").val()&&$e.val()!=""){
							index=i;
							setSelectedItem(i);
						}
						
				}); */
					var condition_json_projectName=$("#projectName").val();
					var condition_json_startTime=$("#startTime").val();
					var condition_json_endTime=$("#endTime").val();
					var condition={"userAccount":userAccount,"projectName": condition_json_projectName,"starTime":condition_json_startTime,"endTime":condition_json_endTime};
					//alert("入参"+JSON.stringify(condition));
					paging(current_page,condition);
			
				
			}
	//显示数据
	function  paging(current_page,condition_json){
		//alert("paging入参="+JSON.stringify(condition_json));
		var req_json={"action":"do_Project_Statistics.do","data":{}
		};
		
		$.ajax({  
	        url :req_json.action,  
	        type : "post",  
	        dataType:"json",  
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{  "projectName":condition_json.projectName,       
	            	 "pageSize":"10",  
	          		 "page":current_page,     
	           		 "year":year,            
	           		 "starTime":condition_json.starTime,            
	           		 "endTime": condition_json.endTime           
				  }, 
	        success : function(response) {  
	       		//alert("paging="+JSON.stringify(response));
	        	$("table tbody tr td").html("");
	         	var taskInfo=response.statistics;
	         	taskInfo.forEach(function(e,i){
	        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(current_page)-1)*10+i+1);
	        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.projectName);
	        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.starTime+" —— "+e.endTime);
	        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.useDays);
	        				$("table tbody tr:eq("+i+") td:eq(4)").html("<a href='"+encodeURI("itemDetails.jsp?projectName="+e.projectName+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&year="+year)+"'>查看详细</a>"); 
	        				});  
	        	         	
	        },  
	        error:function(XmlHttpRequest,textStatus, errorThrown)
            {
            }
	    }); 
		
	}
	//下拉列表中填入内容
	function  fillContentToSelect(index){
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
	        error:function()
            {
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
            }
	    }); 
	       		 return totalpage-1;//返回总页数
		
	}

			//设置选择列表的被选定的项
			function setSelectedItem(index){
				setTimeout(function(){
					$("#projectName option:eq("+index+")").attr("selected","selected");
				},100);
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
	<div  id="attendees" class="formFrame" style="height:450px;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:550px;"><!-- 框架边框 -->
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
	<div id="search" style="position:relative;left:30px;top:30px;">
		项目名称：<select id="projectName" name="projectName" >
					 <option  value="" >---请选择---</option> 
					
				</select>
				 &nbsp;&nbsp;&nbsp; 
				 &nbsp;&nbsp;&nbsp; 
	  	  日期：<input type="Date" name="startTime" id="startTime"/>
	  	   
	        	&nbsp;&nbsp;&nbsp; —— &nbsp;&nbsp;&nbsp;
	           
	          <input type="Date" name="endTime" id="endTime" />
	            <script>
		           		$("#startTime").val(new Date().format("yyyy-MM-dd"));
		           		$("#endTime").val(new Date().format("yyyy-MM-dd"));
		        </script>
	<div><button style="position:relative;left:700px;" name="search" id="search_button" class="button" onclick="conditionSearch(1);">查询</button ></div>
	
	</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:70px;border-bottom:1px solid #888888;">
	
		</div>
		<div style="position:relative;left:10px;top:30px;">
			<table style="width:940px;height:300px;" border=1 cellspacing=0 class="tablesorter" >
			<thead >
			
				<tr>
				<th>序号</th>
				<th>项目</th>
				<th>统计周期</th>
				<th>工时统计(天)</th>
				<th>操作</th>
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
			</div><!-- 表单定位终点 -->
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
        </div><!-- 边框定位终点 --> 
	</body>
</html>