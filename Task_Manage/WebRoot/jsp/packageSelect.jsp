<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>ä¸å¡æ¥è¯¢</title>
		<meta http-equiv="Expires" content="-1" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../css/dpcp.css" />
		<script language="javascript" src="../js/common.js" type="text/javascript"></script>
		<script language="javascript" src="../js/jquery-1.4.2.min.js"
			type="text/javascript"></script>
		<script language="javascript" src="../js/page.js"
			type="text/javascript"></script>
			


		<script>
		//ä»urlè¯·æ±ä¸­è·åå¥é¤typeId 0-å¼å¡ 1-ä¸å¡
		var req_packageTypeId = document.URL;
		if(req_packageTypeId.indexOf('?')>0)
		{
			req_packageTypeId = req_packageTypeId.substring(req_packageTypeId.indexOf('?')+1);
		}
		else
	  	{
			alert('åæ°ä¸æ­£ç¡®ï¼è¯·ä¼ å¥æ­£ç¡®çå¥é¤ç±»å');
			req_packageTypeId = 0;
		}
		var totalResponse = null;
		var responseTableObj = null;
		var lastPage = 1;//æ»é¡µæ°
		var pageCute = 1;//å½åé¡µ
		//æ¥è¯¢æ¹æ³
		function queryList()
		{
			var val_packageName = $("#packageName").val();
		
			var reqMethod = "packageInfoQuery";
			var reqClass = "PackageManager";
			var	reqContent = '"action":"card_list",';
				reqContent+= '"name":"'+val_packageName+'",';
				reqContent+= '"businessType":"'+req_packageTypeId+'"';
		
			$.ajax({
				url:serviceContextPath()+'servlet/Service?'+Math.random(),
				type:'post',
				dataType:'json',
				data:'{"method":"'+reqMethod+'","type":"'+reqClass+'","req":{'+reqContent+'}}',
				success:function(response){
					totalResponse = response;
					if(response==null)
					{
						alert("ç»å½å·²è¶æ¶ï¼è¯·éåºéæ°ç»å½ï¼");
					}
					else
					{
						show(1)
					}
				},
				error:function(){
					alert("ç»éå¤±è´¥ï¼è¯·æ±å¤±è´¥ï¼");
				}
			});
		}
		
		//æ¾ç¤ºåå®¹
		function show(i) {
			var array = pageResponse(i);
			var str = "<table class='tablesorter' width='100%' cellspacing='1' border='1px'>";
			str += "<th>å¥é¤ç¼å·</th><th>å¥é¤åç§°</th><th>åå»ºæ¶é´</th><th>é¢è§</th><th>æä½</th>";
			for (var i = 0; i < array.length; i++) {
				str += "<tr id='tr_"+array[i].packageId+"'>";
				str += "<td>"+array[i].packageCode+"</td>";
				str += "<td>"+array[i].packageName+"</td>";
				str += "<td>"+array[i].createTime+"</td>";
				str += "<td><a href='"+imgContextPath()+array[i].fileUrl+"' target='_imgShow'>é¢è§</a></td>";
				str += "<td>";
				str += "<input type='button' onclick='selectPackage(\""+array[i].packageName+"("+array[i].packageCode+")"+"\",\""+array[i].packageId+"\")' value='éæ©' />";
				str += "</td>";
				str += "</tr>";
			}
			str += "</table>"
			document.getElementById("tableout").innerHTML = str;
		}
		
		//ç»æåºæ¥
		function packageRstShow(responseObj)
		{
			rst = responseObj.actionRst;
			msg = responseObj.actionDesc;
			if(rst=='0')
			{
				alert(msg);
				document.getElementById('tr_'+responseObj.packageId).style.display = "none";
			}
			else
			{
				alert(msg);
			}
		}

		//éæ©å¥é¤
		function selectPackage(val1, val2){
			var returnObj = new Object();
			returnObj.packageShow = val1;
			returnObj.packageId = val2;
			window.returnValue = returnObj;
			window.close();
		}
		</script>

	</head>

	<body>
		<div id="body">
			<table border="0" cellPadding="" cellspacing="0">
				<tr>
					<td width=100% height=23 align=left class='page-title' nowarp>
					<img src="../images/page_title.gif" alt="title" width="17" height="12" align="absmiddle" />
					&nbsp; &nbsp;å¥é¤éæ© Â» å¥é¤æ¥è¯¢
				</td>
			<td width=592 align="left"></td>
		</tr>
	</table>

	<fieldset style="width: 98%; padding: 10px">
		<legend>
			æ¥è¯¢æ¡ä»¶
		</legend>

		<table width="97%" border="0" cellpadding="0" cellspacing="1" class="table_query">
			<tr>
				<td valign="middle" class="td_item_right">
					å¥é¤åç§°ï¼
				</td>
				<td class="td_normal_left">
					<input type="text" id="packageName" name="packageName" value="" />
				</td>
				<td class="td_item_right" colspan="8">
					<input type="button" class="short-button" value="æ¥ è¯¢" onclick="queryList()" />
				</td>
			</tr>
		</table>
	</fieldset>
	<div id ="tableout"> </div>

	<table align="center" width="90%" border='0' cellspacing='0' cellpadding='0' >
		<tr>
			<td>
				<img id="first" alt="First" src="../images/microsoftLook/first(off).gif" border="0" onclick="imgshowpage('first','../')"/>
				<img id="previous" alt="Previous" src="../images/microsoftLook/previous(on).gif" border="0" onclick="imgshowpage('pre','../')"/>
				<span id="pageshow"></span>
				<img id="next" alt="Next" src="../images/microsoftLook/forward(on).gif" border="0" onclick="imgshowpage('next','../')"/>
				<img  id="last" alt="Last" src="../images/microsoftLook/last(on).gif" border="0" onclick="imgshowpage('last','../')"/></td>
			<td>
				<input type="text" value="" id="pageNo"  size="3" />&nbsp;&nbsp;<input type="button" onclick = "go()" value="go" />
			</td>
		</tr>
	</table>
	<script>
		queryList();
	</script>
	</body>
</html>
