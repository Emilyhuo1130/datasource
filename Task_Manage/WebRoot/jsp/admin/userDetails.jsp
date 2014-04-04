<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户详情</title>
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
	 	 String id=request.getParameter("id");
     	 String name=new String(request.getParameter("userName").getBytes("iso8859-1"),"utf-8");
	   	 String userAccount = new String(request.getParameter("userAccount").getBytes("iso8859-1"),"utf-8");
	   	 String password=request.getParameter("password");
	   	String phoneNumber = request.getParameter("phoneNumber");
      %>  
    <script type="text/javascript">
			$(document).ready(function(){
				$(document.body).append("<div id='suc_info' class='suc_info'><span >修改成功</span></div>");
				var id="<%=id%>";
				var name="<%=name%>";
				var userAccount="<%=userAccount%>";
				var phoneNumber="<%=phoneNumber%>"; 
				initData();
				save();
				//初始加载
				function  initData(){
					$("#userName").val(name);
					$("#userAccount").val(userAccount);
					$("#phoneNumber").val(phoneNumber);
					
				}
				
				//保存操作
				function save(){
					$("#save_form").click(function(){
						$.ajax({  
					        url :"do_updateUser.do",  
					        type : "post",  
					        dataType:"json",  
					        async:false,
					        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
					        data :{ "userAccount":$("#userAccount").val(),
					        		"userName":$("#userName").val(),
					        		"phoneNumber":$("#phoneNumber").val(),
					        		"userPw":$("#oldPW").val(),
					        		"id":id
					        	  }, 
					        success : function(response) {  
					        	$("#suc_info").fadeIn("slow");
				  				setTimeout(function(){
				  					location.href="userInfo.jsp";
				  				},1500);
					        },
					        error:function(){
				               // alert("保存失败");
				            }
						});
						
					});
				};
				  
				
			});
			
	 </script>
 </head>

<body  style="background-image:url('../../images/bank.jpg')">
			<table border="0" cellPadding="" cellspacing="0" >
				<tr>
					<td width=100% height=23 align=left class='page-title' >
						<img src="../../images/page_title.gif" alt="title" width="17"
							height="12" align="middle">
			
						&nbsp; &nbsp;<span id="navigation">导航</span>
					</td>
					<td width=592 align="left"></td>
				</tr>
			</table>
			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:500px;"><!-- 框架边框 -->
			
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:50px;border-bottom:1px solid #888888;">
			</div>

 <div id="form_position" style="position:relative;left:20%;top:20%;"><!-- 表单定位 -->
<table   width="700"  class='table_edit'>
	<tr id="firstTr">
     <td> </td>
     <td align="left">
     	 <font color="red">&nbsp;*&nbsp;</font>带*为必填
     </td>
   </tr>
    <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户名:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="userName" value="" size="30" id="userName" type="text" />
	        <span style="color:red;" id="s_userName"></span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>账号:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="count" value="" size="30" id="userAccount"  type="text" />
	        <span style="color:red;" id="s_count"></span>
	    </td>
	</tr>
	
	  <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户电话:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="phone" value="" size="30" id="phoneNumber" type="text"  />
	        <span style="color:red;" id="s_phone"></span>
	    </td>
	</tr> 
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>密码:&nbsp;&nbsp;&nbsp;</td>
	    <td >
	        <input name="oldPW"  value="" size="30" id="oldPW" type="text"  />
	        <span style="color:red;" id="s_password"></span>
	    </td>
	</tr> 
	
 
</table>
<div id="save_button">
	<button  id="save_form" class="button" name="save">提交</button> 
</div>

</div><!-- 表单定位终 -->
		
		
	</div><!-- 边框定位终点 -->
</body>
</html>