<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/commons.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
</head>

<body>
	<form id="formtest">
		username:<input type="text" name="username" required="required"/><br/>
		passworld:<input type="password" name="password" required="required"><br/>
		<input value="提交" type="submit"/> 
	</form>
	<input type="button" onclick="test();"/>
</body>
</html>