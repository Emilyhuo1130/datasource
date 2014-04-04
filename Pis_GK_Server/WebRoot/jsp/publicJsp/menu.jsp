<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理员菜单</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link href="../../css/style.css" rel="stylesheet" type="text/css">
		<link href="../../css/css.css" rel="stylesheet" type="text/css">
		<link href="../../css/default.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/jquery-1.4.4.min.js" ></script>
		<script>
			$(document).ready(function(){
				
			});
		</script>
	</head>


	<script type="text/javascript"> 

function  operateMenu(id){

	var state=$("#subtree"+id).css("display");
	$(".left-table02").hide();	
	$(".left-table01").find("img").attr("src","../../images/ico04.gif");
	$("#subtree"+id).css("display",state);	
	if(state=='none'){
		$("#img"+id).attr("src","../../images/ico03.gif");
	}
	$("#subtree"+id).toggle();
}

function changeSubmenuIcon(thisObj){
	$(".left-table02").find("img").attr("src","../../images/ico06.gif");
	$(thisObj).parent().parent().find("img").attr("src","../../images/ico05.gif");
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
								<img name="img1" id="img1" src="../../images/ico04.gif" width="8"
									height="11" />
							</td>
							<td width="92%">
								<a href="../getinfoList.do" target="mainFrame" class="left-font03"
									onClick="operateMenu('1');">设备信息</a>
							</td>
						</tr>
					</table>
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
								<img name="img1" id="img1" src="../../images/ico04.gif" width="8"
									height="11" />
							</td>
							<td width="92%">
								<a href="../getserverinfo.do" target="mainFrame" class="left-font03"
									onClick="operateMenu('2');">服务端配置</a>
							</td>
						</tr>
					</table>
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
								<img name="img1" id="img1" src="../../images/ico04.gif" width="8"
									height="11" />
							</td>
							<td width="92%">
								<a href="../toAdd.do" target="mainFrame" class="left-font03"
									onClick="operateMenu('3');">客户端配置</a>
							</td>
						</tr>
					</table>
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
								<img name="img1" id="img1" src="../../images/ico04.gif" width="8"
									height="11" />
							</td>
							<td width="92%">
								<a href="../linkageSet.jsp" target="mainFrame" class="left-font03"
									onClick="operateMenu('4');">联动策略配置</a>
							</td>
						</tr>
					</table>
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
								<img name="img1" id="img1" src="../../images/ico04.gif" width="8"
									height="11" />
							</td>
							<td width="92%">
								<a href="../showPic_Linkage.do" target="mainFrame" class="left-font03"
									onClick="operateMenu('5');">联动策略列表</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>


