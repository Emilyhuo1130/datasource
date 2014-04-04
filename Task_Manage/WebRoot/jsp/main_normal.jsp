<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>主页</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>

	<%
		
	    String userName=request.getParameter("userName");
		HttpSession user_session = request.getSession();
		user_session.setAttribute("userAccount", userName);
	%>
	<frameset rows="60,*" cols="*" frameborder="no" framespacing="0"
		id="main">
		<frame src="top_normalUser.jsp" name="topFrame" scrolling="No"
			noresize="noresize" id="topFrame" border="0" />
		<FRAMESET cols="205,18,*" border="0" framespacing="0"
			id='frameset_body'>
			<FRAME src="menu_normalUser.jsp" name="treeframe" border="0"/>
			<frame name="cenFrame" src="bbs_frame_close.jsp" scrolling="No"
				noresize="noresize" id="cenFrame" title="cenFrame" border="0" />
			<FRAME src="welcome.jsp" name="mainFrame" id="mainFrame" border="0"/>
		</FRAMESET>
	</frameset>

</html>
