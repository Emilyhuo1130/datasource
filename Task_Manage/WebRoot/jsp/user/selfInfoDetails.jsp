<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个人详情</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
    <link type="text/css" rel="stylesheet" href="../../css/table.css" />
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

      <% 
	 
	    String userAccount = (String)session.getAttribute("userAccount");
	  
      %>
    <script type="text/javascript">
   		 var userAccount="<%=userAccount%>";
			$(document).ready(function(){
				$(document.body).append("<div id='suc_info' class='suc_info'><span >修改成功</span></div>");
				initData();
				validateForm();
				
			});
			
			//初始加载------------------------------!
			function  initData(){
				
					$.ajax({  
				        url :"do_getMyUserInfo.do",  
				        type : "post",  
				        dataType:"json",  
				        async:false,
				        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
				        data :{
				        		"userAccount":userAccount
				        	  }, 
				        success : function(response) {  
				  			//alert(JSON.stringify(response));
				  			var userInfo=response.userinfo;
				  			$("#user_id").val(userInfo.id);
				  			$("#userName").val(userInfo.name);
				  			$("#userAccount").val(userInfo.userAccount);
				  			$("#phoneNumber").val(userInfo.phoneNumber);
				        },
				        error:function(){
			              //  alert("初始加载失败");
			            },
					});
					
			}
			
				//保存操作------------------------------!
			function  update(){
					$.ajax({  
				        url :"do_modifyInfo.do",  
				        type : "post",  
				        dataType:"json",  
				        async:false,
				        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
				        data :{ "id":$("#user_id").val(),
				        		"userAccount":$("#userAccount").val(),
				        		"userName":$("#userName").val(),
				        		"phoneNumber":$("#phoneNumber").val(),
				        		"oldPW":$("#oldPW").val(),
				        		"newPW":$("#newPW").val(),
				        		"rePassword":$("#rePassword").val()
				        	  }, 
				        success : function(response) {  
				        	if(response.userinfo){
				        		$("#suc_info").fadeIn("slow");
				  				setTimeout(function(){
				  					location.href="../welcome.jsp";
				  				},1500);
				  				
				        		
				        	}else{
				        		$("#origin_error").slideDown("slow");
				        		
				        	}
				        },
				        error:function(){
				        	//alert("修改失败");
			            },
					});
					
				
			}
				//验证密码
				function validateForm(){
					$("#save_form").click(function(){
						//验证修改后的密码
						var flag1=validatePassword("newPW","rePassword","msg","nul_msg","&nbsp;&nbsp;&nbsp;两次密码输入不一致","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;输入不能为空");
						if(flag1){
							update();
							
						}
					});
				}
	 </script>
 </head>

<body  style="background-image:url('../../images/bank.jpg')">
	<div  id="attendees" class="formFrame" style="height:450px;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:500px;"><!-- 框架边框 -->
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:100px;border-bottom:1px solid #888888;">
	
		</div>

 <div style="position:relative;left:200px;top:30px;"><!-- 表单定位 -->
<table   style="width:700px;padding:20px;"  class='table_edit'>
	<tr id="firstTr">
     <td> </td>
     <td align="left">
     </td>
   </tr>
    <tr>
    	<td style="display:none;" id="user_id"></td>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户名:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="userName" value="" size="30" id="userName" type="text" />
	        <span style="color:red;" id="s_userName"></span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>账号:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="count" value="" size="30" id="userAccount" type="text" disabled="disabled" />
	        <span style="color:red;" id="s_count"></span>
	    </td>
	</tr>
	
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>电话:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="phone" value="" size="30" id="phoneNumber" type="text" />
	        <span style="color:red;" id="s_phone"></span>
	    </td>
	</tr>
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>原始密码:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="password"  value="" size="30" id="oldPW" type="password" />
	        <span style="color:red;" id="s_oldpassword"></span>
	         <div id="origin_error" style="display:none;background-color:#cceedd;border:1px black solid;width:180px;">
	       		  <span style="color:red;font-weight:bold;font-size:.65em;">&nbsp;&nbsp;&nbsp;&nbsp; 原始密码输入有误</span>
	         </div>
	    </td>
	</tr> 
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>修改后密码:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="password"  value="" size="30" id="newPW" type="password"  />
	        <span style="color:red;" id="s_nowpassword"></span>
	    </td>
	</tr> 
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>确认修改后密码:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="password"  value="" size="30" id="rePassword" type="password"   />
	         <div id="msg" style="display:none;background-color:#cceedd;border:1px black solid;width:180px;height:20px;">
	       		  <span style="color:red;text-align:center;font-size:.65em;font-weight:bold;"></span>
	         </div>
	        
	    </td>
	</tr> 
	<tr>
		<td></td>
		<td>
			  <div id="nul_msg" style="display:none;background-color:#cceedd;border:1px black solid;width:180px;height:20px;">
	       		  <span style="color:red;text-align:center;font-size:.65em;font-weight:bold;"></span>
	         </div>
		</td>
	</tr>
 
</table>
<div id="save_button" style="positon:relative;left:190px;top:20px;">
	<button  id="save_form" class="button" name="save">提交</button> 
</div>
</div><!-- 表单定位终 -->
			</div>
		</div><!-- 框架定位终点 -->
	</body>
</html>