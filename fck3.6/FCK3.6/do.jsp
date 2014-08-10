<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>  
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>  
<%
    String contest = request.getParameter("editor1");
	//out.print(contest);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="./js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="./js/jquery.form.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#editor1").html(<%=contest%>);
	})
</script>

<title>Insert title here</title>
</head>
<body>
<form action="getContent" method="post">  
<textarea cols="80" id="editor1" name="editor1" rows="10"></textarea>  
<input type="submit" value="Submit" />  
</form>  
<ckfinder:setupCKEditor basePath="ckfinder/" editor="editor1" />  
<ckeditor:replace replace="editor1" basePath="ckeditor/" />  
</body>
</html>