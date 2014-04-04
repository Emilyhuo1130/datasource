<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加监控组</title>
	
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="../../css/jquery-ui-themes.css" type="text/css" rel="stylesheet">
    <link href="../../css/axure_rp_page.css" type="text/css" rel="stylesheet">
    <link href="../../css/axurerp_pagespecificstyles.css" type="text/css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
    <link type="text/css" rel="stylesheet" href="../../css/table.css" />
    <link type="text/css" rel="stylesheet" href="../../css/demo.css" />
    <link type="text/css" rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css" />
     <link type="text/css" rel="stylesheet" href="../../css/fbmodal.css" />
	<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
	<script type="text/javascript" src="../../js/common.js"></script>
	<script type="text/javascript" src="../../js/groupManage.js" ></script>
	<script type="text/javascript" src="../../js/jquery.fbmodel.js" ></script>
	<script type="text/javascript" src="../../js/jquery.ztree.core-3.5.js" ></script>
	<script type="text/javascript" src="../../js/jquery.ztree.excheck-3.5.js" ></script>
    <script type="text/javascript">
    
			$(document).ready(function(){
				getgroupTree();
				
				//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			});
	 </script>
 </head>
<body style="background-image:url('../../images/bank.jpg')">
<table border="0" cellPadding="" cellspacing="0" >
	<tr>
		<td width=100% height=23 align=left class='page-title' nowarp>
			<img src="../../images/page_title.gif" alt="title" width="17"
				height="12" align="middle">

			&nbsp; &nbsp;<span id="navigation">增加监控组</span>
		</td>
		<td width=592 align="left"></td>
	</tr>
</table>
<form id="reqForm" name="reqForm" action="">
<div style="position:relative;left:200px;top:200px;">
<table   width="700"  class='table_edit'>
	<div id="main_container">

	<input type="hidden" id="groupId" value=""/>
	<div class="test">
		<font><font id="someinfo">确定删除此条记录?</font>
			<a href="javascript: vaoid()" id="close"><font></font></a> 
		</div>
	<tr >
	    <td>
	    	
		    <div style="width:300px;height:400px; border:0px solid white">
		    <table>
		    
		    		<tr>
				    <td style="font-size:18px" align="right" valign="middle" width=192>监控组名称:<span style="font-size:15px;color:red" >*</span></td>
				    <td >
				        <input height="25px" onblur="veriftycharacter(this,'errorNameChar');"  placeholder="监控组1" title="填写监控组名称"  name="groupName" size="30" id="groupName" type="text"  dataType="LimitB" min="1" max="10" msg="1-20个字符" />
				       <span style="display:none;color:red" id="errorNameChar" >非法字符</span>
				    </td>
				    
					</tr>
					<tr>
					    <td style="font-size:18px" align="right" valign="middle" width=192>备注:</td>
					    <td >
					        <input onblur="veriftycharacter(this,'errorAccountChar');" name="remark" size="30" id="remark" type="text"  />
					        <span style="display:none;color:red" id="errorAccountChar" >非法字符</span>
					    </td>
					</tr>
				</table>
		    </div>
	    </td>
	     <td>
	     	<label style="font-size:20px" >可选监控组</label>
		    <div style="width:300px;height:400px; border:0px solid white">
		    	<table>
		    		<ul id="treeDemo" class="ztree"></ul>
		    	</table>
		    </div>
	    </td>
	   
	</tr>
	

	<INPUT onclick="saveControlGroup();" id="u5" type="button" class="u5-2" value="保存"   >

	<INPUT id="u6" onclick="reback();" type="button" class="u6-2" value="返回"   >

</div>



</table>
</div>
</form>
</body>
</html>