<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统警戒值设置</title>
	
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="../../css/jquery-ui-themes.css" type="text/css" rel="stylesheet">
    <link href="../../css/axure_rp_page.css" type="text/css" rel="stylesheet">
    <link href="../../css/axurerp_pagespecificstyles.css" type="text/css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
    <link type="text/css" rel="stylesheet" href="../../css/table.css" />
    <link type="text/css" rel="stylesheet" href="../../css/fbmodal.css" />
	<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
	<script type="text/javascript" src="../../js/common.js" ></script>
	<script type="text/javascript" src="../../js/jquery.fbmodel.js" ></script>
	<script type="text/javascript" src="../../js/systemconfig.js" ></script>
    <script type="text/javascript">
			$(document).ready(function(){
				readsystemMalarm_Value();
		
			});
	 </script>
 </head>
<body style="background-image:url('../../images/bank.jpg')">
<table border="0" cellPadding="" cellspacing="0" >
	<tr>
		<td width=100% height=23 align=left class='page-title' nowarp>
			<img src="../../images/page_title.gif" alt="title" width="17"
				height="12" align="middle">

			&nbsp; &nbsp;<span id="navigation">系统警戒值设置</span>
		</td>
		<td width=592 align="left"></td>
	</tr>
</table>
<form id="reqForm" name="reqForm" action="">
<div style="position:relative;left:200px;top:200px;">
<table   width="700"  class='table_edit'>
	<!-- 消息提示框 -->
	<div class="test">
		<font><font id="someinfo">确定删除此条记录?</font>
			<a href="javascript: vaoid()" id="close"><font></font></a> 
	</div>
	
    <tr>
	    <td align="right" valign="middle" width=192>CPU占率高于:</td>
	    <td >
	        <input onblur="veriftyNumber3(this,'errornumberChar');" name="CPUnumber" size="30" id="CPUnumber" type="text"  dataType="LimitB" min="1" max="10" msg="1-20个字符" />
	       	<span>%,设为不正常</span> <span style="font-size:15px;color:red" >*</span>
	       	<span style="display:none;color:red" id="errornumberChar" >请输入大于1小于100的整数</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>进出流量高于:</td>
	    <td >
	        <input onblur="veriftyNumber3(this,'notNumber');" name="flowNumber" size="30" id="flowNumber" type="text"  />
	        <span>%,设为不正常</span> <span style="font-size:15px;color:red" >*</span>
	        <span style="display:none;color:red" id="notNumber" >请输入大于1小于100的整数</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>剩余总磁盘空间低于:</td>
	    <td >
	        <input onblur="veriftyNumber3(this,'notNumberT')" name="zoneNumber" size="30" id="zoneNumber" type="text"  />
	       	<span>%,设为不正常</span> <span style="font-size:15px;color:red" >*</span>
	        <span style="display:none;color:red" id="notNumberT" >请输入大于1小于100的整数</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>剩余磁盘空间低于:</td>
	    <td >
	        <input onblur="veriftyNumber3(this,'errorzoneNumberChar');" name="lastZoneNumber" size="30" id="lastZoneNumber" type="text"  dataType="LimitB" min="1" max="20" msg="内容为11个字节" />
	        <span>%,设为不正常</span> <span style="font-size:15px;color:red" >*</span>
	        <span style="display:none;color:red" id="errorzoneNumberChar" >请输入大于1小于100的整数</span>
	    </td>
	</tr>
	
</table>
<div id="save_button">
	<input type="button"  onclick="saveSystemMalarmInfo();" id="saveInfo"  class="adduserSave" value="保存"/>
	<input type="button"  onclick="reback();"  id="" class="adduserCancel" value="返回"/>
</div>
</div>

</form>
</body>
</html>