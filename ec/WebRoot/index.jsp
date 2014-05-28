<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String userName=(String)session.getAttribute("userName");
	String dd="99999";
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>消费统计首页</title>
    <link rel="stylesheet" type="text/css"  href="./ext-3.3.0/resources/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css"  href="./ec/css/PublicUtils.css"/>
    <link rel="stylesheet" type="text/css"  href="./ec/css/forms.css"/>
	<script type="text/javascript" language="javascript" src="./ext-3.3.0/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" language="javascript" src="./ext-3.3.0/ext-all.js"></script>
	<script language="javascript" type="text/javascript" src="./ec/js/utils/commons.js"></script>
	<script language="javascript" type="text/javascript" src="./ec/js/utils/PublicUtils.js"></script>
	<script language="javascript" type="text/javascript" src="./ec/js/ux/Ext.ux.Notification.js"></script>
	<script language="javascript" type="text/javascript" src="./ec/js/utils/Province-citys.js"></script>
	<script language="javascript" type="text/javascript" src="./ec/js/pagejs/mainpage.js"></script>
	<script language="javascript" type="text/javascript" src="./ec/js/pagejs/RegisterForm.js"></script>
	<script language="javascript" type="text/javascript" src="./ec/js/pagejs/CommitBug.js"></script>
	
	<script language="javascript" type="text/javascript" src="./ec/js/ux/FieldLabeler.js"></script>
    <script language="javascript" type="text/javascript" src="./ec/js/ux/FieldReplicator.js"></script>
	
	<script language="javascript" type="text/javascript" src="./ec/js/utils/Dialog.js"></script>
	
	
	
  </head>
  
  <body>
    站点正在开发阶段，界面美化将会陆续进行（工作很忙的。。。。），基本的每天的消费记录功能可以使用  项目开始开发时间为2014-5-25<br>
    由于数据量小，主页暂时无法开放大规模数据统计模块。<br/>
   此项目是个人爱好项目， 如果你有好的建议和意见，请给开发者<a href="#" id="commitbug">留言</a><br/>
   
   
   
  <c:if test="${userName==null}">
  	<button id="zhuce">注册用户</button>
  	<button id="login">登陆</button>
  </c:if>
  <c:if test="${userName!=null}">
  		当前用户：<%=userName %><br>
  		<a href="Main.jsp">访问个人主页</a>
  		<a href="deleteSession@UserController.do">注销</a>
  </c:if>
  
    
  </body>
</html>
