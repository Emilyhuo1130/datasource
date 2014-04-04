<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html  >
	<head>
	<title>后台管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		 <link type="text/css" rel="stylesheet" href="css/validate.css" /> 
		 <link type="text/css" rel="stylesheet" href="css/client.css" />
		 <link type="text/css" rel="stylesheet" href="css/dpcp.css" />
		 <link type="text/css" rel="stylesheet" href="css/jquerymsg.css" />
		 <link type="text/css" rel="stylesheet" href="css/table.css" />
		 <link href="css/sample.css" rel="stylesheet" type="text/css" />
		  <style>
		  	html{
		  	overflow-y:scroll;
		  	overflow-x:scroll;
		  	}
		  </style>
		 <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
		 <script type="text/javascript" src="../js/jqueryPromit.js"></script>
		 <script type="text/javascript" src="../js/jqueryBlockUI.js"></script>
		 <script type="text/javascript" src="../js/utils.js"></script>
		 <script type="text/javascript" src="../js/gk.js"></script>
		 <script>
		 		
		 	$(document).ready(function(){
		 		//启用滚动条
				   $(document.body).css({
				    "overflow-x":"auto",
				    "overflow-y":"auto"
				 });
		 		
		 		$("#c_submit").click(function(){
		 			if($("input")[0].value!=""){
		 				var flag=$("input[name='bisimmediateshutdown']:checked").val();
		 				if(flag=="true"){
		 					$.createDialog("确定立即关机?",function(){
		 						$.msg("更新成功!");
		 						$("form").submit();
		 						return true;
		 					});
		 				
		 				}else{
		 					$.createDialog("是否确认提交该配置?",function(){
		 						$.msg("完成提交，页面3秒后跳转...");
		 						  window.setTimeout(function(){
		 							$("form").submit();
		 						},3000);  
		 						
		 					});
		 				
			 				return true;
		 				}
		 			
		 			}else{
		 				$.msg("请确保填入全部信息!");
		 				return false;
		 			}
		 			
		 		});
		 		
		 		
		 	});
		 
		 
		 </script>
	</head>
	<body class="bgm" style="position:relative;top:-9px;">
  		
  	<div id="part2" style="height:750px;border:1px solid #aaaaaa;">
  	<div id="sub_nav" style="margin-top:-7px;">
  		
  		</div>
	<form action="setServerInfo.do"  method="post" >
	
	<div style="height:20px;"></div>
	<div  style="position:relative;left:10%;height:350px;width:75%;">
	<div style='height:20px;'></div>
	<table class="table_Cfg">
		<tr>
			
			<td>
				<span>服务器IP：</span> 
		    </td>
		    <td>
			  <input  onblur="changeserver_IP_TypeToreadonly();"   readonly="readonly" type="text" name="server_IP" value="${config_Pis.server_IP}" /><br />
		    </td>
		     <td>
		    	 <input  class="button" type="button" value="修改" onclick="changeserver_IP_Type();" />
		    </td> 
		</tr>
		<tr></tr>
		<tr>
			<td>
				<span>版本号：</span>
			</td>
			
		    <td>
				 <input name="version" type="text"   value="${config_Pis.version}" />
		    </td>
		</tr>
		<tr></tr>
		<tr>
			<td>
				<span>定时更新时间：</span>
			</td>
			
		    <td>
		    <script language="javascript">
			var str = "";
			document.writeln("<div id=\"_contents1\" style=\"padding:6px; background-color:#E3E3E3; font-size: 12px; border: 1px solid #777777; position:absolute; left:?px; top:?px; width:?px; height:?px; z-index:1; visibility:hidden\">");
			str += "\u65f6<select id=\"_hour1\">";
			for (h = 0; h <= 9; h++) {
			    str += "<option value=\"0" + h + "\">0" + h + "</option>";
			}
			for (h = 10; h <= 23; h++) {
			    str += "<option value=\"" + h + "\">" + h + "</option>";
			}
			str += "</select> \u5206<select id=\"_minute1\">";
			for (m = 0; m <= 9; m++) {
			    str += "<option value=\"0" + m + "\">0" + m + "</option>";
			}
			for (m = 10; m <= 59; m++) {
			    str += "<option value=\"" + m + "\">" + m + "</option>";
			}
			str += "</select> \u79d2<select id=\"_second1\">";
			for (s = 0; s <= 9; s++) {
			    str += "<option value=\"0" + s + "\">0" + s + "</option>";
			}
			for (s = 10; s <= 59; s++) {
			    str += "<option value=\"" + s + "\">" + s + "</option>";
			}
			str += "</select> <input name=\"queding\" type=\"button\" onclick=\"_select1()\" value=\"\u786e\u5b9a\" style=\"font-size:12px\" /></div>";
			document.writeln(str);
			var _fieldname;
			 var settime_flag1=false;
			function _SetTime1(tt) {
			    _fieldname = tt;
			    var ttop = tt.offsetTop;    
			    var thei = tt.clientHeight;    
			    var tleft = tt.offsetLeft;   
			    while (tt = tt.offsetParent) {
			        ttop += tt.offsetTop;
			        tleft += tt.offsetLeft;
			    }
			    document.getElementById("_contents1").style.top = ttop + thei + 4;
			    document.getElementById("_contents1").style.left = tleft;
			    document.getElementById("_contents1").style.visibility = "visible";
			    settime_flag1=false;
			    
			}
			function _select1() {
			    _fieldname.value = document.getElementById("_hour1").value + ":" + document.getElementById("_minute1").value + ":" + document.getElementById("_second1").value;
			    document.getElementById("_contents1").style.visibility = "hidden";
			    settime_flag1=true;
			    return settime_flag1;
			}
</script>
				 <input type="text" name="update_Time" value="${config_Pis.update_Time}" onclick="_SetTime1(this)" />
		    </td>
		</tr>
		<tr></tr>
				<tr>
			<td>
				<span>唤醒时间：</span>
			</td>
			
		    <td>
		    <script language="javascript">
			var str = "";
			document.writeln("<div id=\"_contents2\" style=\"padding:6px; background-color:#E3E3E3; font-size: 12px; border: 1px solid #777777; position:absolute; left:?px; top:?px; width:?px; height:?px; z-index:1; visibility:hidden\">");
			str += "\u65f6<select id=\"_hour2\">";
			for (h = 0; h <= 9; h++) {
			    str += "<option value=\"0" + h + "\">0" + h + "</option>";
			}
			for (h = 10; h <= 23; h++) {
			    str += "<option value=\"" + h + "\">" + h + "</option>";
			}
			str += "</select> \u5206<select id=\"_minute2\">";
			for (m = 0; m <= 9; m++) {
			    str += "<option value=\"0" + m + "\">0" + m + "</option>";
			}
			for (m = 10; m <= 59; m++) {
			    str += "<option value=\"" + m + "\">" + m + "</option>";
			}
			str += "</select> \u79d2<select id=\"_second2\">";
			for (s = 0; s <= 9; s++) {
			    str += "<option value=\"0" + s + "\">0" + s + "</option>";
			}
			for (s = 10; s <= 59; s++) {
			    str += "<option value=\"" + s + "\">" + s + "</option>";
			}
			str += "</select> <input name=\"queding\" type=\"button\" onclick=\"_select2()\" value=\"\u786e\u5b9a\" style=\"font-size:12px\" /></div>";
			document.writeln(str);
			var _fieldname;
			 var settime_flag2=false;
			function _SetTime2(tt) {
			    _fieldname = tt;
			    var ttop = tt.offsetTop;    
			    var thei = tt.clientHeight;    
			    var tleft = tt.offsetLeft;   
			    while (tt = tt.offsetParent) {
			        ttop += tt.offsetTop;
			        tleft += tt.offsetLeft;
			    }
			    document.getElementById("_contents2").style.top = ttop + thei + 4;
			    document.getElementById("_contents2").style.left = tleft;
			    document.getElementById("_contents2").style.visibility = "visible";
			    settime_flag2=false;
			}
			function _select2() {
			    _fieldname.value = document.getElementById("_hour2").value + ":" + document.getElementById("_minute2").value + ":" + document.getElementById("_second2").value;
			    document.getElementById("_contents2").style.visibility ="hidden";
			    settime_flag2=true;
			    return settime_flag2;
			}
</script>
				 <input type="text" name="startUpTime" value="${config_Pis.startUpTime}" onclick="_SetTime2(this)" readonly="readonly"/>
		    </td>
		</tr>
		<tr></tr>
				<tr>
			<td>
				<span>关闭时间：</span>
			</td>
			
		    <td>
		    
		    <script language="javascript">
			var str = "";
			document.writeln("<div id=\"_contents3\" style=\"padding:6px; background-color:#E3E3E3; font-size: 12px; border: 1px solid #777777; position:absolute; left:?px; top:?px; width:?px; height:?px; z-index:1; visibility:hidden\">");
			str += "\u65f6<select id=\"_hour3\">";
			for (h = 0; h <= 9; h++) {
			    str += "<option value=\"0" + h + "\">0" + h + "</option>";
			}
			for (h = 10; h <= 23; h++) {
			    str += "<option value=\"" + h + "\">" + h + "</option>";
			}
			str += "</select> \u5206<select id=\"_minute3\">";
			for (m = 0; m <= 9; m++) {
			    str += "<option value=\"0" + m + "\">0" + m + "</option>";
			}
			for (m = 10; m <= 59; m++) {
			    str += "<option value=\"" + m + "\">" + m + "</option>";
			}
			str += "</select> \u79d2<select id=\"_second3\">";
			for (s = 0; s <= 9; s++) {
			    str += "<option value=\"0" + s + "\">0" + s + "</option>";
			}
			for (s = 10; s <= 59; s++) {
			    str += "<option value=\"" + s + "\">" + s + "</option>";
			}
			str += "</select> <input name=\"queding\" type=\"button\" onclick=\"_select3()\" value=\"\u786e\u5b9a\" style=\"font-size:12px\" /></div>";
			document.writeln(str);
			var _fieldname;
			var settime_flag3=false;
			function _SetTime3(tt) {
			    _fieldname = tt;
			    var ttop = tt.offsetTop;    
			    var thei = tt.clientHeight;    
			    var tleft = tt.offsetLeft;   
			    while (tt = tt.offsetParent) {
			        ttop += tt.offsetTop;
			        tleft += tt.offsetLeft;
			    }
			    document.getElementById("_contents3").style.top = ttop + thei +4;
			    document.getElementById("_contents3").style.left = tleft;
			    document.getElementById("_contents3").style.visibility = "visible";
			    settime_flag3=false;
			 
			}
			function _select3() {
			    _fieldname.value = document.getElementById("_hour3").value + ":" + document.getElementById("_minute3").value + ":" + document.getElementById("_second3").value;
			    document.getElementById("_contents3").style.visibility = "hidden";
			    settime_flag3=true;
			    return settime_flag3;
			}
</script>
				 <input type="text" name="shutDownTime" value="${config_Pis.shutDownTime}" onclick="_SetTime3(this)" readonly="readonly"/>
		    </td>
		</tr>
<%-- 		<tr>
			<td><span>同步播放：</span></td>
			<td>
				<select id="syschronize_play"  name="synchronizationPlay" value="${config_Pis.synchronizationPlay}" >
				
					<option value="1">整点</option>
					<option value="2">半点</option>
					<option value="3">一刻钟</option>
				
				</select>
				<script type="text/javascript">
					var index="${config_Pis.synchronizationPlay}"-1;
			
					$("#syschronize_play option:eq("+index+")").attr("selected","selected");
				
				</script>
			 </td>
		</tr> --%>
		<tr></tr>
		<tr>
			<td><span>是否立即更新：</span></td>
			<td>
				<span>是</span><input type="radio" name="flag" value="true"/> 
				&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <span>否</span><input type="radio" name="flag" value="false" checked="checked"/>
			 </td>
		</tr>
		<tr></tr>
			<tr>
			<td><span>是否立即关机：</span></td>
			<td>
				<span>是</span><input type="radio" name="bisimmediateshutdown" value="true"/> 
				&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <span>否</span><input type="radio" name="bisimmediateshutdown" value="false" checked="checked"/>
			 </td>
		</tr>
	</table>
	<div id="c_div">
		<input id="c_reset" class="button" style="height:30px;text-align:center;position:relative;left:60%;"  name="server_reset" value="重置" onclick="new function(){
			$.createDialog('确认要重置?(恢复到上一次的设定)',function(){
				document.location.reload();		
				
			});
			
		}"/>
		<input id="c_submit" class="button" style="height:30px;position:relative;left:70%;"  name="server_submit"  value="提交" />
	</div>
	</div>
	</form>
	</div>
	
	
	<div id="part3" >
  		UCS Holdings CopyRight 2013
	</div>
	</body>
</html>
