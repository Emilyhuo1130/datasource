<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>添加任务</title>

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
 		var year=new Date().format("yyyy-MM-dd");
		$(document).ready(function(){
			$(document.body).append("<div id='suc_info' class='suc_info'><span >添加成功</span></div>");
			fillContentToSelect();
			getWeekNumber();
			saveMission();
		});
 	
 	
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
		//保存操作
		function  saveMission(){
			$("#save").click(function(){
				if(validateForm()){
					var req_json={"action":"do_addTasks.do","data":{}};
					$.ajax({  
				        url :req_json.action,  
				        type : "post",  
				        dataType:"json",  
				        async:false,
				        contentType: "application/x-www-form-urlencoded;charset=utf-8", 
				        data :{ "date":$("#nowaDate").val(),
				        		"projectName":$("#item").val(),
								"taskTime":$("#needTime").val(),
								"taskDetail":$("#mission_today").val(),
								"userAccount":userAccount
							  }, 
				        success : function(response) {  
				        	$("#suc_info").fadeIn("slow");
				        	setTimeout(function(){
				        		location.href="showWeekMissionList.jsp?userAccount="+userAccount;
			  				},1500);
				        
				        },  
				        error:function(XmlHttpRequest,textStatus, errorThrown)
			            {
			            }
				    }); 
				}else{
				}
					
			
				
			});
		}
		//下拉列表中填入内容
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
		        		$("#item").append("<option value="+e+">"+e+"</option>");
		        		
		        	});
		        
		        },  
		        error:function(XmlHttpRequest,textStatus, errorThrown)
	            {
		        	
		        	 
	            }
		    }); 
			
		}
		//表单验证
		function validateForm(){
			//验证工时
				 var flag1= validateData(/^\s[^\s]+|[\S]+$/,$("#item").val(),"projectName_error","projectName_error","项目名称不能为空","项目名称不能为空");
				 var flag2= validateData(/^\s[^\s]+|[\S]+$/,$("#needTime").val(),"workTime_nul_error","workTime_nul_error","所用工时不能为空","所用工时不能为空");
				 var flag3= validateData(/^\s[^\s]+|[\S]+$/,$("#mission_today").val(),"mission_nul_error","mission_nul_error","任务内容不能为空","任务内容不能为空");
				if(flag1&&flag2&&flag3){
					return true;
				}else{
					return false;
				}
		}
    </script>
	</head>
	
	<body  style="background-image: url('../../images/bank.jpg')">
	<div  id="attendees" class="formFrame" style="height:450px;">
		
	<div id="att1"  class="attendees">
		<!-- <form id="reqForm" name="reqForm"> -->
		<div style="width:960px;height:45px;">
		<span  style="align-height:50px;vertical-align:middle;font-size:2.5em;color:purple;">本周是第<span id="thisweek" style="fontsize:2.8em;color:blue;"></span>周</span>
		</div>
			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:500px;"><!-- 框架边框 -->
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:100px;border-bottom:1px solid #888888;">
			<div style="position:relative;left:120px;"><!-- 定位查询 条件-->
			<table style="padding:20px;
			width:700px;
			
			
			">
				<tr>
					<td><span>请选择项目：</span>	
						<select id="item" >
							<option value="">请选择</option>
						</select>
						<div id="projectName_error" style="display:none;width:180px;height:20px;background-color:#cceedd;border:1px black solid;"><span style="color:red;font-weight:bold;font-size:.75em;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名称不能为空</span></div>
					</td>
					<td><span>所用工时：</span><input type="text" name="needTime" id="needTime"  value=""/>时
					<div id="workTime_nul_error" style="display:none;width:180px;height:20px;background-color:#cceedd;border:1px black solid;"><span style="color:red;font-weight:bold;font-size:.75em;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工时不能为空</span></div>
					</td>
					<td><span>日期：</span><input type="date" name="nowaDate" id="nowaDate" readonly="readonly"  style="background-color:#ececec;" value=""/>
					<script>
						$("#nowaDate").val(new Date().format("yyyy-MM-dd"));
					</script>
					</td>
				</tr>
				<tr>
					
					<td></td>
					<td>
						<div id="finishour_error" style="display:none;background-color:#cceedd;border:1px black solid;width:300px;"><span style="color:red;font-size:.65em;"></span></div>
					</td>
					<td></td>
				</tr>
			</table>
			
		
		</div><!-- 定位查询条件结束 -->
		</div>
		<br/>
			
			<div id="addMissoin" style="width:800px;height:300px;position:relative;left:50px;">
			
			<table>
			<tr>
				<td>
					<span class="span">今日任务</span>
				</td>
				<td>
					<textarea id="mission_today" style="width:700px;height:300px;resize:none;" name="missonThis" ></textarea>
					<div id="mission_nul_error" style="display:none;width:180px;height:20px;background-color:#cceedd;border:1px black solid;"><span style="color:red;font-weight:bold;font-size:.75em;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任务内容不能为空</span></div>
				</td>
				<td>
				
				</td>
			</tr>
			<tr height="30px;">
				<td></td><td></td>
			</tr>
				
			
			</table>
			<div style="position:relative;top:280px;">
				<button  style="position:relative;left:350px;top:-300px;" name="save" id="save" class="button" >保存</button>
			</div>
			</div>
			</div>
		<!-- </form> -->
			</div>
	</div><!-- 框架边框终点 -->
	</body>
</html>