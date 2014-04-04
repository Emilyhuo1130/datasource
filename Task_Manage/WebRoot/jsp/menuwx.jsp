<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>æ é¡äº§åè¶å¸ç®¡çç³»ç»</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script src="../js/jquery-1.4.2.min.js"></script>
	</head>


	<script type="text/javascript"> 

function operateMenu(id){

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



      
      <TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
			class="left-table01">
			<tr>
				<td height="29">
					<table width="85%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="8%">
								<img name="img8" id="img8" src="../images/ico04.gif" width="8"
									height="11" />
							</td>
							<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03"
									onClick="operateMenu('8');">å®¡æ ¸ç®¡ç</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</TABLE>
		<table id="subtree8" style="DISPLAY: none" width="80%" border="0"
			align="center" cellpadding="0" cellspacing="0" class="left-table02">
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkSoftWare.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">è½¯ä»¶ä¸å¡å®¡æ ¸</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkRing.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">å½©éä¸å¡å®¡æ ¸</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkGame.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">æ¸¸æä¸å¡å®¡æ ¸</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkNovel.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">å°è¯´ä¸å¡å®¡æ ¸</a>
				</td>
			</tr>
				<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkingMobile.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">ææºå®¡æ ¸</a>
				</td>
			</tr>	
			</tr>
				<tr>
				<td width="9%" height="20">
					<img src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkingCard.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">å¼å¡å®¡æ ¸</a>
				</td>
			</tr>
			</tr>
				<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkingBusiness.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">ä¸å¡å®¡æ ¸</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkingactivite.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">æ´»å¨å®¡æ ¸</a>
				</td>
			</tr>
			<tr>
				<td width="9%" height="20">
					<img  src="../images/ico06.gif" width="8" height="12" />
				</td>
				<td width="91%">
					<a href="checking/checkingIndexactivite.html" target="mainFrame"
						class="left-font03" onClick="changeSubmenuIcon(this);">é¦é¡µæ´»å¨å®¡æ ¸</a>
				</td>
			</tr>
		</table>
		
	</body>
</html>


