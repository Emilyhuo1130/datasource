<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="testUpload1.do" enctype="multipart/form-data" method="post">
		<input type="file" name="file" />
		<input type="file" name="file" />
		<input type="submit" value="上传1"/>
	</form>
	<p/>
	<form action="testUpload2.do" enctype="multipart/form-data" method="post">
		<input type="file"  name="file" />
		<input type="submit" value="上传2"/>
	</form>
	<p/>
	<form action="testUpload3.do" enctype="multipart/form-data" method="post">
		<input type="file"  name="file" />
		<input type="submit" value="上传3"/>
	</form>
</body>
</html>