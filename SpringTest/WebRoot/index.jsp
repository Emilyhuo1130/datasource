<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/test.js"></script>
  </head>
  
  <body>
    This is my JSP page. <br>
    
    
    <a href = http://localhost:8080/SpringTest/json.do?userName=gaojidong> 带参传递  返回JSON</a>
    <br>
    <br>
        <a href =http://localhost:8080/SpringTest/jsp/login.jsp> 测试MVC POST </a>
    <br>
        <input onclick="changeImagesAddress();" type="button" value="测试springMVC 异步请求"/>
        <input type="text" id="text1"/>
        <br>
        <input onclick="jsNetRegist();" type="button" value="测试springMVC 跨域请求"/>
       
    
  </body>
  <% 	  
    	   session.setAttribute("username","zhang");
    	   %>
</html>
