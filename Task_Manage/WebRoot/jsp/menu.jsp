<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理员菜单</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<link href="../css/default.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/jquery-1.7.2.js" ></script>
		<script type="text/javascript" src="../js/common.js"></script>
		<script type="text/javascript" src="../js/ucs_manage.js"></script>
		<script>
			$(document).ready(function(){
				
			});
		</script>
	</head>


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
									onClick="operateMenu('1');">用户任务管理</a>
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
					<a href="../jsp/admin/lookNowaweekMission.jsp" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">查看本周任务</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/admin/dailyReport.jsp" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">查看历史日报</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/admin/lookNextWeekMissionPlan.jsp" target="mainFrame"
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
									onClick="operateMenu('2');">项目管理</a>
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
					<a href="../jsp/admin/lookItem.jsp" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">查看项目信息</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/admin/addItem.jsp" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">添加项目</a>
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
									onClick="operateMenu('3');">项目统计</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
		<table id="subtree3" style="DISPLAY: none" width="80%" border="0"
			align="center" cellpadding="0" cellspacing="0" class="left-table02">
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/admin/itemCount.jsp" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">查看统计信息</a>
				</td>
			</tr>
			<tr>
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
								<a href="javascript:;" target="mainFrame" class="left-font03"
									onClick="operateMenu('4');">用户管理</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	<table id="subtree4" style="DISPLAY: none" width="80%" border="0"
			align="center" cellpadding="0" cellspacing="0" class="left-table02">
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="../jsp/admin/userInfo.jsp" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">查看所有用户</a>
				</td>
			</tr>
			<tr>
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
									onClick="operateMenu('5');">个人信息</a>
							</td>
						</tr>
					</table>
					<table id="subtree5" style="DISPLAY: none" width="80%" border="0"
								align="center" cellpadding="0" cellspacing="0" class="left-table02">
						<tr>
							<td width="9%" height="20">
								<img  src="../images/ico06.gif" width="8" height="12" />
							</td>
							<td width="91%">
								<a href="../jsp/admin/adminSelfInfo.jsp" target="mainFrame"
									class="left-font03" onClick="changeSubmenuIcon(this);">修改个人信息</a>
							</td>
						</tr>
					<tr>
				</tr>
		</table>
				</td>
			</tr>
		</table>
	</body>
</html>


