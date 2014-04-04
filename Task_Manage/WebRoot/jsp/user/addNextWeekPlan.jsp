<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>添加下周计划</title>

		<meta http-equiv="content-type" content="text/html; charset=utf-8">
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
		%>
		var userAccount="<%=userAccount%>";//转成字符串
		$(document).ready(function(){
			$(document.body).append("<div id='suc_info' class='suc_info'><span >添加成功</span></div>");
			fillContentToSelect();
			getWeekNumber();
			validateForm();
	});
		

		
		//保存按钮
	function save(){
		var req_json={"action":"do_addPlans.do","data":{}};
			$.ajax({  
		        url :req_json.action,  
		        type : "post",  
		        dataType:"json",  
		        async:false,
		        contentType: "application/x-www-form-urlencoded;charset=utf-8", 
		        data :{ 
		        		 "userAccount":userAccount,
		        		 "projectName":$("#projectName").val(),
						 "WeekNumber":parseInt($("#thisweek").html())+1,
						 "planDetails":$("#mission_nextWeek").val()
					
					  }, 
		        success : function(response) {  
		        	$("#suc_info").fadeIn("slow");
		        	setTimeout(function(){
		        		location.href="showWeekMissionPlanList.jsp";
	  				},1500);
		        },  
		        error:function(XmlHttpRequest,textStatus, errorThrown)
	            {
	            }
		    });
	 
			
	}
			//显示第几周
		function getWeekNumber(){
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
			//获取项目列表
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
					//alert("获取项目列表失败");		        	 
	            }
		    }); 
			
		}
		 //表单验证
		  function validateForm(){
				$("#save").click(function(){
					 var flag1= validateData(/^\s[^\s]+|[\S]+$/,$("#projectName").val(),"projectName_error","projectName_error","项目名称不能为空","项目名称不能为空");
					 var flag2= validateData(/^\s[^\s]+|[\S]+$/,$("#mission_nextWeek").val(),"nextWeekPlan_nul_error","nextWeekPlan_nul_error","下周计划不能为空","下周计划不能为空");
					  if(flag1&&flag2){
						  save();
					  }
				});
		  }
    </script>
	</head>
	
	<body 	style="background-image: url('../../images/bank.jpg')">
			<div style="width:960px;height:45px;">
				<span  style="align-height:50px;vertical-align:middle;font-size:2.5em;color:purple;">本周是第<span id="thisweek" style="fontsize:2.8em;color:blue;"></span>周</span>
			</div>
			
			<div  id="attendees" class="formFrame" style="height:650px;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
		
			<div style="border:1px solid #999999; width:958px;height:600px;"><!-- 框架边框 -->
			<div style="width:2px;height:600px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<div style="position:relative;left:100px;top:50px;">
				<table style="padding:20px;width:700px;">
					<tr>
						<td><span>请选择项目：</span>	
							<select id="projectName" >
								<option value="">----请选择----</option>
							</select>
							 <div id="projectName_error" style="display:none;width:180px;height:20px;background-color:#cceedd;border:1px black solid;"><span style="color:red;font-weight:bold;font-size:.75em;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名不能为空</span></div>
						</td>
					</tr>
				</table>
			</div>
			
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:50px;border-bottom:1px solid #888888;">
	
		</div>
		<br/>
			
			<div id="addMissoin" style="width:800px;height:300px;position:relative;left:30px;">
			
			<table>
			<tr>
				<td style="width:30%;font-weight:bold;font-size:.8em;">
					下周计划任务
				</td>
				<td>
					<textarea id="mission_nextWeek" style="background-color:#bee4c6;width:700px;height:300px;resize:none;" name="mission_nextWeek" ></textarea>
					 <div id="nextWeekPlan_nul_error" style="display:none;width:180px;height:20px;background-color:#cceedd;border:1px black solid;"><span style="color:red;font-weight:bold;font-size:.75em;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下周计划不能为空</span></div>
				</td>
				<td>
				
				</td>
			</tr>
			<tr height="30px;">
				<td></td><td></td>
			</tr>
				
			
			</table>
		
			</div>
			</div>
			<div style="height:60px;"></div>
			<button  style="position:relative;left:380px;top:-150px;" name="save" id="save" class="button" >保存</button>
			</div>
	</body>
</html>