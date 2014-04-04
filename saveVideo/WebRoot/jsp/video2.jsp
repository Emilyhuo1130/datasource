<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/commons.js"></script>
<script type="text/javascript" src="../js/userManage.js"></script>

</head>
<body>
	<h6>windows 终端指令</h6>
	<form id="formtest">
		<input type="text" value="192.168.2.125" name="ip"/>
		<input type="text" value="C:/Users/kathy/Desktop/" name="address"/>
		<input type="submit" value="提交">
	</form>

	<br/>
	<input type="button" value="停止定时器" onclick="end();"/>
</body>
</html>