<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个人信息</title>
	
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

    <script type="text/javascript">
			$(document).ready(function(){
				$(document.body).append("<div id='suc_info' class='suc_info'><span >修改成功</span></div>");
				validateForm();			
		
			});
		
		//保存操作
			function  save(){
				$("#save_form").click(function(){
					$.ajax({  
				        url :"do_modifyAdminInfo.do",  
				        type : "post",  
				        dataType:"json",  
				        async:false,
				        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
				        data :{ 
				        		"oldPw":$("#oldPW").val(),
				        		"newPw":$("#newPW").val(),
				        		"rePassword":$("#newPwEnsure").val(),
				        		"adminName":$("#count").val()
				        	  }, 
				        success : function(response) {
				        	//alert(JSON.stringify(response));
				        	if(response.userinfo){//bool类型
				        	$("#suc_info").fadeIn("slow");
				  				setTimeout(function(){
				  					location.href="userInfo.jsp";
				  				},1500);
				        	}else{
				        		$("#origin_error").slideDown("slow");
				        	}
				  			
				        },
					
					});
				});
			};
			//表单验证
				function validateForm(){
					$("#save_form").click(function(){
						var flag1=validatePassword("newPW","newPwEnsure","msg","nul_msg","&nbsp;&nbsp;&nbsp;两次密码输入不一致","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;输入不能为空");
						if(flag1){
							save();
						}
					});
				}
	 </script>
 </head>

<body  style="background-image:url('../../images/bank.jpg')">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:500px;"><!-- 框架边框 -->
			
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:70px;border-bottom:1px solid #888888;">
			</div>
 <div id="form_position" style="position:relative;left:200px;top:100px;"><!-- 表单定位 -->
<table   width="700"  class='table_edit'>
	<tr id="firstTr">
     <td> </td>
     <td align="left">
     	 <font color="red">&nbsp;*&nbsp;</font>带*为必填
     </td>
   </tr>
   <!--  <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户名:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="adminName" value="" size="30" id="adminName" type="text" />
	        <span style="color:red;" id="s_userName"></span>
	    </td>
	</tr> -->
	<tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>账号:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="count" value="admin" size="30" id="count" type="text"   readonly="readonly" />
	        <span style="color:red;" id="s_count"></span>
	    </td>
	</tr>
	<!--  <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户电话:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="phone" value="" size="30" id="phone" type="text" />
	        <span style="color:red;" id="s_phone"></span>
	    </td>
	</tr> -->
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户密码:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="password"  value="" size="30" id="oldPW" type="text"  />
	        <div id="origin_error" style="display:none;background-color:#cceedd;border:1px black solid;width:180px;">
	       		  <span style="color:red;font-weight:bold;font-size:.65em;">&nbsp;&nbsp;&nbsp;&nbsp; 原始密码输入有误</span>
	         </div>
	    </td>
	</tr> 
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>修改后密码:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="password"  value="" size="30" id="newPW" type="text" />
	     
	    </td>
	</tr> 
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>确认修改后密码:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="password"  value="" size="30" id="newPwEnsure" type="text" />
	         <div id="msg" style="display:none;background-color:#cceedd;border:1px black solid;width:180px;height:20px;">
	       		  <span style="color:red;text-align:center;font-size:.65em;font-weight:bold;"></span>
	         </div>
	          <div id="nul_msg" style="display:none;background-color:#cceedd;border:1px black solid;width:180px;height:20px;">
	       		  <span style="color:red;text-align:center;font-size:.65em;font-weight:bold;"></span>
	         </div>
	    </td>
	</tr> 
	
 
</table>
		<div id="save_button" style="position:relative;left:200px;top:20px;">
			<button  id="save_form" class="button" name="save">提交</button> 
		</div>

				</div><!-- 表单定位终 -->
			
		</div><!-- 边框定位终 -->
	</body>
</html>