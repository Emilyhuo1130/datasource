<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>  
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">      
    <title>首页</title>  
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">  
<script type="text/javascript" src="./js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="./js/jquery.form.js" ></script>
</head>  
  <body>  
<%--<ckfinder:ckfinder basePath="/CKFinderJava_0100/ckfinder/" width="700" height="500" /> --%>  
<%--<iframe src="demo.jsp" frameborder="0" scrolling="yes" id="setframe" name="setframe" width="100%" height="100%"></iframe>', --%>
<form action="do.jsp" method="post">  
<textarea cols="80" id="editor1" name="editor1" rows="10">
<p><img alt='' src='userfiles/images/222(1).jpg' style='width: 80px; height: 60px;' /></p>
</textarea>  
<input type="submit" value="Submit" />  
</form>  
<ckfinder:setupCKEditor basePath="ckfinder/" editor="editor1" />  
<ckeditor:replace replace="editor1" basePath="ckeditor/" />  
  </body>  
</html>  