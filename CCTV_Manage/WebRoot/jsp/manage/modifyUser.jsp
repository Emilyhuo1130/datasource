<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改用户信息</title>
	
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
	<script type="text/javascript" src="../../js/userManage.js" ></script>
	 <% 
	 	 String id=request.getParameter("id");
      %> 
    <script type="text/javascript">
			$(document).ready(function(){
				var id="<%=id%>";
				selectAllControlGroup(id);//获取监控组名称
				//showOneUserInfo(id);
				
		
			});
	 </script>
 </head>
<body style="background-image:url('../../images/bank.jpg')">
<table border="0" cellPadding="" cellspacing="0" >
	<tr>
		<td width=100% height=23 align=left class='page-title' nowarp>
			<img src="../../images/page_title.gif" alt="title" width="17"
				height="12" align="middle">

			&nbsp; &nbsp;<span id="navigation">修改用户信息</span>
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
    <tr>
	    <td align="right" valign="middle" width=192>操作员姓名:</td>
	    <td >
	        <input onblur="veriftycharacter(this,'errorNameChar')" name="userName" size="30" id="userName" type="text"  dataType="LimitB" min="1" max="10" msg="1-20个字符" />
	      	<span style="font-size:15px;color:red" >*</span>
	      	<input type="hidden" id="userId" value=""/>
	       <span style="display:none;color:red" id="errorNameChar" >非法字符</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>操作员账号:</td>
	    <td >
	        <input readonly="readonly" onblur="veriftycharacter(this,'errorAccountChar')" name="userAccount" size="30" id="userAccount" type="text"  />
	      	<span style="font-size:15px;color:red" >*</span>
	        <span style="display:none;color:red" id="errorAccountChar" >非法字符</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>操作员密码:</td>
	    <td >
	        <input onblur="veriftycharacter(this,'errorpasswordChar')" name="userpassword" size="30" id="userpassword" type="text"  />
	        <span style="font-size:15px;color:red" >*</span>
	        <span style="display:none;color:red" id="errorpasswordChar" >非法字符</span>
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>操作员手机号码:</td>
	    <td >
	        <input onblur="veriftyPhoneNumber(this,'errorPhoneChar')" name="phoneNumber" size="30" id="phoneNumber" type="text"  dataType="LimitB" min="1" max="20" msg="内容为11个字节" />
	        <span style="font-size:15px;color:red" >*</span>
	        <span style="display:none;color:red" id="errorPhoneChar" >手机号码不符合</span>
	    </td>
	</tr>
	 <tr>
	    <td align="right" valign="middle" width=192>操作员等级:</td>
	    <td >
	    	<select onchange="showgroups(this.value);"  id="choseOperate">
	    		<option value="超级操作员">超级操作员</option>
	    		<option value="普通操作员">普通操作员</option>
	    	</select>
	        
	    </td>
	</tr>
	<tr>
	    <td align="right" valign="middle" width=192>操作员备注:</td>
	    <td >
	        <input onblur="veriftycharacter(this,'errorChar')" name="remark" size="30" id="remark" type="text"  dataType="LimitB" min="1" max="20" msg="内容为1-20个字节" />
	        <span style="color:red;" id="s_remark"></span>
	    </td>
	</tr>
	</p>
	<tr>
	     <td id ="AllgroupNames" align="right" valign="middle" width=192>监控组:</td>
	    <td >
	    <div id="controlGroup" style="border:0px solid red"><!--
	    		  </br>
			      <input value="仓库" type="checkbox" name="group" checked="checked" /><span>基本监控</span>
			      <input  value="办公室公室"  type="checkbox" name="group" /><span >白天监控</span>
			      <input value="地下室"  type="checkbox" name="group" />地下室监控
			      <input  type="checkbox" name="group" />黑夜仓库监控
			      </br>
			      <input  type="checkbox" name="group" />黑夜办公室监控
			      <input  type="checkbox" name="group" />黑夜地下室监控
			      <input  type="checkbox" name="group" />其他监控
			      <input  type="checkbox" name="group" />白天地下室监控
			     </br>
			         
	    	
	    --></div>
	<!--<input type="button" onclick="controlGroupTest();"   value="controlGroupTest"/>
	  --><script type="text/javascript">
	  /*function controlGroupTest(){
	  	  			 var relateIds = "";
                     $("input:checked").each(function(){  
                              relateIds += $(this).val()+"#";
                      });
                     alert(relateIds);                    
	   
	  }*/
	 </script>     
	    </td>
	</tr>
 
</table>
<div id="save_button">
	<input onclick="saveUserInfo();" id="saveInfo" type="button"  class="adduserSave" value="保存"/>
	<input type="button" onclick="reback();"  class="adduserCancel" value="返回"/>
	
</div>
</div>
</div>

</form>
</body>
</html>