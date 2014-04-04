<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>user page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/commons.js"></script>
<script type="text/javascript" src="../js/userManage.js"></script>



</head>
<body>
	username:<input value="${user.username}"/><br/>
	password:<input value="${user.password}"><br/>
	<a href="getAllUserInfo.do">测试jstl</a><br/>
	<a href="html5.jsp">测试HTML5</a>
	<a href="adminMain.do">查看系统管理员页面</a><br/>
	<input type="button" onclick="logout();" value="退出"/>
</body>
</html>