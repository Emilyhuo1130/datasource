<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>修改管理员登录密码</title>
	
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
	<script type="text/javascript" src="../../js/admin.js" ></script>
		<%HttpSession user_session = request.getSession();
		String userAccount=(String)user_session.getAttribute("userAccount");
		
		%>
    <script type="text/javascript">
    	
			$(document).ready(function(){
				$("#adminAccount").val("<%=userAccount%>");
				
			});
	 </script>
 </head>
<body style="background-image:url('../../images/bank.jpg')">
<table border="0" cellPadding="" cellspacing="0" >
	<tr>
		<td width=100% height=23 align=left class='page-title' nowarp>
			<img src="../../images/page_title.gif" alt="title" width="17"
				height="12" align="middle">

			&nbsp; &nbsp;<span id="navigation">修改管理员登录密码</span>
		</td>
		<td width=592 align="left"></td>
	</tr>
</table>
<form id="reqForm" name="reqForm" action="">
<div style="position:relative;left:200px;top:200px;">
<table   width="700"  class='table_edit'>
	<div class="test">
		<font><font id="someinfo">确定删除此条记录?</font>
			<a href="javascript: vaoid()" id="close"><font></font></a> 
	</div>
		<!--<div style="width:400px; height:300px; border:solid 1px red; ">
		<video width="400" height="300" controls="controls">
  			<source src="Dorado7xBasicTraining-Video-20110917-06.mp4" type="video/mp4">
		</video>
		</div>
    -->
    <tr>
	    <td align="right" valign="middle" width=192>管理员账户:</td>
	    <td >
	       
	        <input readonly="readonly" name="adminAccount" size="30" id="adminAccount" type="text"  dataType="LimitB" min="1" max="10" msg="1-20个字符" />
	      <span style="font-size:15px;color:red" >*</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>管理员原来密码:</td>
	    <td >
	        <input  name="oldpassword" size="30" id="oldpassword" type="password"  />
	       <span style="font-size:15px;color:red" >*</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>管理员新密码:</td>
	    <td >
	        <input name="newpassword" size="30" id="newpassword" type="password"  />
	        <span style="font-size:15px;color:red" >*</span>
	        <span style="display:none;color:red" id="errorpasswordChar" >非法字符</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>确定管理员新密码:</td>
	    <td >
	        <input  name="newpasswordagain" size="30" id="newpasswordagain" type="password"  dataType="LimitB" min="1" max="20" msg="内容为11个字节" />
	        <span style="font-size:15px;color:red" >*</span>
	    </td>
	</tr>
	
	
 </p>
	
</table>
<div id="save_button">
	<input type="button"  onclick="saveadminInfo();" id="saveInfo"  class="adduserSave" value="保存"/>
	<input type="button"  onclick="reback();"  id="" class="adduserCancel" value="返回"/>
</div>
</div>

</form>
</body>
</html>