<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>登录界面</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/jquery-1.7.2.js" ></script>
		<script type="text/javascript" src="../js/common.js"></script>
		<script type="text/javascript" src="../js/ucs_manage.js"></script>
	</head>


<script type="text/javascript">
	$(document).ready(function(){
		var reqUrl="";
		 $("#login").click(function(){
			if($("#userAccount").val()=="admin"){
				reqUrl="admin_Log";
			}else{
				Account="adminAccount";
				Pw="userPw";
				reqUrl="user_Log";
			}
			var req_json={"action":reqUrl+"/login.do","data":{"user":$("#userAccount").val(),"userAccount":$("#userAccount").val(),"userPw":$("#userPw").val()}};
			
			$.ajax({  
		        url :req_json.action,  
		        type :"post",  
		        dataType:"json",  
		        asycn:false,
		        contentType:"application/x-www-form-urlencoded;charset=utf-8",  
		        data:{"userAccount":$("#userAccount").val() , "userPw":$("#userPw").val()}, 
		        success : function(response) {  
		        	//alert(JSON.stringify(response));
		        	 if(response.user&&reqUrl=="user_Log"){
							location="main_normal.jsp?userName="+req_json.data.user;
						}else if(response.admin&&reqUrl=="admin_Log"){
							location="main.jsp?userName="+req_json.data.user;
						}else{
							$("#msg").fadeIn("slow");
						}  
		        },  
		       	 error : function(data) {  
		        	//alert("--------------error------------------!");
		        }  
		    }); 
			
		}); 
		 submitBindEnter();
		
	});
	//回车键绑定提交按钮
	function submitBindEnter(){
		  document.onkeyup = function (event) {
	            var e = event || window.event;
	            var keyCode = e.keyCode || e.which;
	            switch (keyCode) {
	                case 13:
	                    $("#login").click();
	                    break;
	                default:
	                    break;
	            }
	        };
		
	}
</script>
<STYLE type=text/css>
html {
	filter: progid : DXImageTransform.Microsoft.BasicImage(grayscale = 1);
}

body {
	background-color: #D8E8F8;
}
</STYLE>


		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p style="height: 304px; ">
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		 <!-- <form  name="login_form" action="login.do" method="post"> -->
		<table width="636" height="215" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td align="right" valign="bottom" background="../images/loginbg.jpg">
					<table width="636" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="55%"></td>
							<td width="45%">
								<table width="257" height="142" border="0" align="right"
									cellpadding="0" cellspacing="0">
									<tr>
										<td width="110" align="center">
											用户名:
										</td>
										<td width="147">
											<input type="text" name="userAccount" value="" size="16" id="userAccount"
												maxlength="20" class="input_search" />
										</td>
										
									</tr>
									<tr>
										<td align="center">
											密&nbsp;&nbsp;&nbsp;&nbsp;码:
										</td>
										<td>
											<input type="password" name="userPw" value="" size="16" id="userPw"
												maxlength="16" class="input_search" />
												<br/>
											<span id="msg" style="background-color:#c7edcc;color:red;display:none;">用户名或密码错误</span>
										</td>
									</tr>

									<tr>
										<td>
											&nbsp;
										</td>
										<td align="center">
											<table height="30" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td align="center">
														<button id="login"  name="login">登录</button>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		 <!-- </form>  -->


		<table width="100%" height="240" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" valign="bottom" class="font_login_copyright">
					&copy; ucs
				</td>
			</tr>
		</table>