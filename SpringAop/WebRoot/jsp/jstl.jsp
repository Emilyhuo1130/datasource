<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>user page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/commons.js"></script>
<script type="text/javascript" src="../js/jstl.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		getSelect();
	});
</script>


</head>
<body>
<h5>${allUserInfo.userInfo.username}</h5>
<h5>${allUserInfo.userInfo.password}</h5>
<h5>${allUserInfo.name}</h5>
<h6><c:out value="${allUserInfo.name}"></c:out></h6>
<!-- c:if  -->
<c:if test="${allUserInfo.name=='钓鱼'}">
	show 钓鱼
	<c:if test="${allUserInfo.userInfo.password=='123456'}">
		<h5>show password</h5>
	</c:if>
</c:if>


<!--  c:choose c:when -->
<c:choose>
	<c:when test="${allUserInfo.userInfo.password=='12345'}">
		密码正确
	</c:when>
	<c:otherwise>
		密码错误
	</c:otherwise>
</c:choose>

<br/>
	<select id="select1">
		<c:forEach items="${allUserInfo.list}" var="e">
			<option value="${e}">${e}</option>
		</c:forEach>
		<script>
			$("#select1").val("${allUserInfo.name}");
		</script>
	</select>
	
	
	<p/>
	<select id="select2">
	
	</select>

</body>
</html>