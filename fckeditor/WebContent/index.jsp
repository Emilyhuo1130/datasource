<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"  href="./ext-3.3.0/resources/css/ext-all.css"/>
<script type="text/javascript" language="javascript" src="./ext-3.3.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" language="javascript" src="./ext-3.3.0/ext-all.js"></script>
<script type="text/javascript" language="javascript" src="./js/commons.js"></script>
<script type="text/javascript" language="javascript" src="./js/CommitBug.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
		var win=new ec.js.pagejs.CommitBug();
		win.show(this);
});
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>