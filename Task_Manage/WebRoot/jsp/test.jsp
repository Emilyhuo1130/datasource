<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<script  type="text/javascript" src="js/jquery-1.7.2.js" ></script>
	<script   type="text/javascript" src="js/common.js"></script>
	<script   type="text/javascript" src="js/ucs_manage.js"></script>
	<script  type="text/javascript">
		$(document).ready(function(){
					var req_json={"action":"user_Log/login.do","data":{}};
				$("#send").click(function(){
					sendAjaxRequest(req_json,function(response){
						alert(response);
					});
				});
			
				 
		});
		
	
	</script>
  </head>
  
  <body>
   <button name="send"id="send" onclick="sendReq();">发请求</button>
  </body>
</html>
