<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改区位信息</title>
	
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="../../css/jquery-ui-themes.css" type="text/css" rel="stylesheet">
    <link href="../../css/axure_rp_page.css" type="text/css" rel="stylesheet">
    <link href="../../css/axurerp_pagespecificstyles.css" type="text/css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
    <link type="text/css" rel="stylesheet" href="../../css/table.css" />
    <link type="text/css" rel="stylesheet" href="../../css/fbmodal.css" />
	<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
	<script type="text/javascript" src="../../js/common.js"></script>
	<script type="text/javascript" src="../../js/jquery.fbmodel.js" ></script>
	<script type="text/javascript" src="../../js/zoneManage.js" ></script>
	 <% 
	 	 String id=request.getParameter("id");
      %> 
    <script type="text/javascript">
			$(document).ready(function(){
				var id="<%=id%>";
				
				showzoneByID(id);
			});
	 </script>
 </head>
<body style="background-image:url('../../images/bank.jpg')">
<table border="0" cellPadding="" cellspacing="0" >
	<tr>
		<td width=100% height=23 align=left class='page-title' nowarp>
			<img src="../../images/page_title.gif" alt="title" width="17"
				height="12" align="middle">

			&nbsp; &nbsp;<span id="navigation">修改区位信息</span>
		</td>
		<td width=592 align="left"></td>
	</tr>
</table>
<form id="reqForm" name="reqForm" action="">
<div style="position:relative;left:200px;top:200px;">
<table   width="700"  class='table_edit'>
	
    <tr>
	    <td align="right" valign="middle" width=192>区位名称:</td>
	    <td >
	        <input onblur="veriftycharacter(this,'errorNameChar')" name="zoneName" size="30" id="zoneName" type="text"  dataType="LimitB" min="1" max="10" msg="1-20个字符" />
	      	<span style="font-size:15px;color:red" >*</span>
	      	<input type="hidden" id="zoneId" value=""/>
	       <span style="display:none;color:red" id="errorNameChar" >非法字符</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>备注:</td>
	    <td >
	        <input onblur="veriftycharacter(this,'errorRemarkChar')" name="remark" size="30" id="remark" type="text"  />
	        <span style="display:none;color:red" id="errorRemarkChar" >非法字符</span>
	    </td>
	</tr>
 
</table>
		<div class="test">
			<font><font id="someinfo">确定删除此条记录?</font>
				<a href="javascript: vaoid()" id="close"><font></font></a> 
		</div>
<div id="save_button">
	<input onclick="savezoneinfo();" id="saveInfo" type="button"  class="adduserSave" value="保存"/>
	<input type="button" onclick="reback();"  class="adduserCancel" value="返回"/>
	
</div>
</div>
</div>

</form>
</body>
</html>