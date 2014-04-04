<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加用户</title>
	
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
    <link type="text/css" rel="stylesheet" href="../../css/table.css" />
	<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
	<script type="text/javascript" src="../../js/common.js"></script>
	<script type="text/javascript" src="../../js/ucs_manage.js"></script>
    <script type="text/javascript">
			$(document).ready(function(){
				$("#save").click(function(){
					
					var req_json={"action":"do_addUser.do","data":{}
					};
					$.ajax({  
				        url :req_json.action,  
				        type : "post",  
				        dataType:"json",  
				        async:false,
				        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
				        data :{ "userName":$("#userName").val(),
								"userAccount":$("#userAccount").val(),
								"userPw":$("#password").val(),
								"phoneNumber":$("#phoneNumber").val()
}, 
				        success : function(response) {  
				        	alert("添加用户成功");
				        	setTimeout(function(){
			  					location.href="userInfo.jsp";
			  				});
				        
				        },  
				        error : function(data) {  
				        	//alert("--------------error------------------!");
				           // alert(JSON.stringify(data));  
				        }  
				    }); 
		});
		
	});
	 </script>
 </head>
<body style="background-image:url('../../images/bank.jpg')">
<table border="0" cellPadding="" cellspacing="0" >
	<tr>
		<td width=100% height=23 align=left class='page-title' nowarp>
			<img src="../../images/page_title.gif" alt="title" width="17"
				height="12" align="middle">

			&nbsp; &nbsp;<span id="navigation">添加用户</span>
		</td>
		<td width=592 align="left"></td>
	</tr>
</table>
<form id="reqForm" name="reqForm" action="">
<div style="position:relative;left:200px;top:200px;">
<table   width="700"  class='table_edit'>
	<tr id="firstTr">
     <td> </td>
     <td align="left">
     	 <font color="red">&nbsp;*&nbsp;</font>带*为必填
     </td>
   </tr>
    <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户名</td>
	    <td >
	        <input name="userName" size="30" id="userName" type="text"  dataType="LimitB" min="1" max="10" msg="1-20个字符" />
	        <span style="color:red;" id="s_userName"></span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>账号</td>
	    <td >
	        <input name="userAccount" size="30" id="userAccount" type="text"  />
	        <span style="color:red;" id="s_count"></span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户密码 </td>
	    <td >
	        <input name="userPw" size="30" id="password" type="text"  dataType="LimitB" min="1" max="20" msg="内容为1-20个字节" />
	        <span style="color:red;" id="s_password"></span>
	    </td>
	</tr>
	 <tr>
	    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>用户电话</td>
	    <td >
	        <input name="phoneNumber" size="30" id="phoneNumber" type="text"  dataType="LimitB" min="1" max="20" msg="内容为1-20个字节" />
	        <span style="color:red;" id="s_phone"></span>
	    </td>
	</tr>
 
</table>
<div id="save_button">
	<button id="save"  name="save" >保存</button>
</div>
</div>

</form>
</body>
</html>