<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>系统管理员</TITLE>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<link href="../css/top.css" rel="stylesheet" type="text/css">
		<script language="javascript" src="../js/jquery-1.7.2.js" type="text/javascript"></script>
		<script language="javascript" src="../js/common.js" type="text/javascript"></script>
	</HEAD>
	<%HttpSession user_session = request.getSession();
		String userAccount=(String)user_session.getAttribute("userAccount");
	%>
	<BODY  >
		
		<table width="100%" height="56" border="0" cellpadding="3"
			cellspacing="0">
			<tr>
				<td height="2"></td>
				<td align="right" valign="middle"></td>
			</tr>
			<tr>
				<td width="51%" height="33px" bgcolor="#FFFFFF" class="tpl"
					style="padding-left: 10px;">
					<a class="pre" href="javascript:top.cenFrame.history.go(-1);">后退</a>
					<a class="ne" href="javascript:top.cenFrame.history.go(1);">前进</a>
					<a class="flush" href="javascript:top.mainFrame.location.reload();">刷新</a>

					<a class="logout" href="javascript:adminLogout('../');">退出</a>

				</td>
				<td width="60%" align="right" valign="middle" bgcolor="#FFFFFF"
					class="tpl">

					<span style="font-size: 12px; padding-right: 12px;"><img
							src="../images/account.gif" alt="µ±Ç°µÇÂ¼" width="19" height="20"
							align="absmiddle">系统管理员&nbsp;&nbsp;&nbsp;<%=userAccount%> </span>

				</td>
			</tr>
		</table>

		<SCRIPT language="JavaScript">

function adminLogout()
{
 if (confirm("确认退出？"))
 {
 	 parent.location.href='login.jsp';
 }
}
</SCRIPT>


	</BODY>
</HTML>
