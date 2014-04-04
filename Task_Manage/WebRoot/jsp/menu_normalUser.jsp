<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>普通用户菜单</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<link href="../css/default.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/jquery-1.7.2.js" ></script>
		<script type="text/javascript" src="../js/common.js"></script>
		<script type="text/javascript" src="../js/ucs_manage.js"></script>
	</head>
		<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		%>
	<%
		
		HttpSession user_session = request.getSession();
		String userAccount=(String)user_session.getAttribute("userAccount");
		
	%>
	<script type="text/javascript"> 

function  operateMenu(id){

	var state=$("#subtree"+id).css("display");
	$(".left-table02").hide();	
	$(".left-table01").find("img").attr("src","../images/ico04.gif");
	$("#subtree"+id).css("display",state);	
	if(state=='none'){
		$("#img"+id).attr("src","../images/ico03.gif");
	}
	$("#subtree"+id).toggle();
}

function changeSubmenuIcon(thisObj){
	$(".left-table02").find("img").attr("src","../images/ico06.gif");
	$(thisObj).parent().parent().find("img").attr("src","../images/ico05.gif");
}
</script>
	<STYLE type=text/css>
</STYLE>
	
	<body>

		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="left-table01">
			<tr>
				<td height="29">
					<table width="85%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="8%">
								<img name="img1" id="img1" src="../images/ico04.gif" width="8"
									height="11" />
							</td>
							<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03"
									onClick="operateMenu('1');">任务管理</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
		</table>
			<table id="subtree1" style="DISPLAY: none" width="80%" border="0"
			align="center" cellpadding="0" cellspacing="0" class="left-table02">
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/user/lookThisWeekMission.jsp?userName=<%=userAccount%>" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">查看本周任务</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/user/showWeekMissionList.jsp?userName=<%=userAccount%>" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">查看历史任务</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/user/addMission.jsp?userName=<%=userAccount%>" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">添加任务</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/user/addNextWeekPlan.jsp?userName=<%=userAccount%>" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">添加下周计划</a>
				</td>
				
			</tr>
					<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/user/showWeekMissionPlanList.jsp?userName=<%=userAccount%>" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">查看下周计划</a>
				</td>
				
			</tr>
			
		</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="left-table01">
			<tr>
				<td height="29">
					<table width="85%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="8%">
								<img name="img1" id="img1" src="../images/ico04.gif" width="8"
									height="11" />
							</td>
							<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03"
									onClick="operateMenu('2');"> 个人信息管理</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
		</table>
			<table id="subtree2" style="DISPLAY: none" width="80%" border="0"
			align="center" cellpadding="0" cellspacing="0" class="left-table02">
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/user/selfInfoDetails.jsp?userAccount=<%=userAccount%>" 
					target="mainFrame"  
					class="left-font03" 
					onClick="changeSubmenuIcon(this);">修改个人信息</a>
				</td>
				
			</tr>
			
		</table>

	</body>
</html>


