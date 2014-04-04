<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改摄像机配置信息</title>
	
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
		<script type="text/javascript" src="../../js/vidiconManage.js" ></script>
	<% 
	 	 String id=request.getParameter("id");
      %> 
    <script type="text/javascript">
			$(document).ready(function(){
				var id="<%=id%>";
				
				showOneVidicon(id);
			});
	 </script>
 </head>
<body style="background-image:url('../../images/bank.jpg')">
<table border="0" cellPadding="" cellspacing="0" >
	<tr>
		<td width=100% height=23 align=left class='page-title' nowarp>
			<img src="../../images/page_title.gif" alt="title" width="17"
				height="12" align="middle">

			&nbsp; &nbsp;<span id="navigation">修改摄像机配置信息</span>
		</td>
		<td width=592 align="left"></td>
	</tr>
</table>
		<div class="test">
			<font><font id="someinfo">确定删除此条记录?</font>
				<a href="javascript: vaoid()" id="close"><font></font></a> 
		</div>
<form id="reqForm" name="reqForm" action="">
<div style="position:relative;left:200px;top:200px;">
<table   width="700"  class='table_edit'>
	<div id="main_container">

<div id="u0" class="u0_container"   >
	<div id="u0_img" class="u0_normal detectCanvas"></div>
<div id="u1" class="u1" style="visibility:hidden;"  >
	<div id="u1_rtf"></div>
</div>
</div>
<div id="u2" class="u2"  >
	<div id="u2_rtf"><p style="text-align:left;">
	<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">摄像机名称:<span style="font-size:15px;color:red" >*</span></span>
	
	</p>
	<span style="display:none;color:red;" id="errorNameChar" >非法字符</span>
</div>
</div>
<INPUT onblur="veriftycharacter(this,'errorNameChar');" id="u3" placeholder="摄像机1" title="填写摄像机名称"  type=text value="" class="u3" />
<div id="u4" class="u4"  >
	<div id="u4_rtf"><p style="text-align:left;">
		<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">摄像机IP:<span style="font-size:15px;color:red" >*</span></span>
	</p>
	<span style="display:none;color:red;" id="errorIPChar" >IP格式不正确</span>
	</div>
</div>
	<input type="hidden" id="VidiconId" value=""/>
<INPUT id="u5" onclick="saveVidiconinfo();" type="button" class="u5" value="保存"   >

<INPUT id="u6" onclick="reback();" type="button" class="u6" value="返回"   >

<div id="u7" class="u7"  >
	<div id="u7_rtf"><p style="text-align:left;">
		<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">摄像机型号:</span>
		</p>
	</div>
</div>
<INPUT onblur="veriftycharacter(this,'errorModelChar');" placeholder="SONY" title="填写摄像机的型号" id="u8" type=text value="" class="u8"     >

<INPUT onblur="veriftyIP(this,'errorIPChar');" id="u9" type=text value="" class="u9"  placeholder="192.168.2.200" title="填写摄像机的IP地址"     >

<div id="u42" class="u42"  >
	<div id="u42_rtf"><p style="text-align:left;">
		<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">所属区位:<span style="font-size:15px;color:red" >*</span></span>
		</p>
	</div>
</div>
<SELECT id="u43" class="u43"   >
	<!--<OPTION  value="仓库">仓库</OPTION>
	<OPTION  value="办公室">办公室</OPTION>
--></SELECT>
<div id="u11" class="u11"  >
		<div id="u11_rtf"><p style="text-align:left;">
		<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">容量大小:</span>
		</p>
		<span style="display:none;color:red;" id="errorNumberChar" >请输入数字</span>
	</div>
</div>
<INPUT onblur="veriftyAllNumber(this,'errorNumberChar');" id="u12"   title="填写录像机的容量大小" placeholder="4000"  type=text value="" class="u12"     >

<div id="u13" class="u13"  >
	<div id="u13_rtf">
		<p style="text-align:left;">
		<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">MAC:</span>
		</p>
		<span style="display:none;color:red;" id="errorMACChar" >MAC格式错误 </span>
	</div>
</div>
<INPUT onblur="veriftyMAC(this,'errorMACChar')"  id="u14" type=text value="" class="u14"  title="填写摄像机的MAC地址" placeholder="00:24:21:19:BD:E4"      >

<DIV id="u15" class="treeroot" style="position:absolute; left:555px; top:108px; width:137px; height:145px; overflow:visible;" >


<DIV id="cncu15" style="position:absolute; left:0px; top:0px; width:1px; height:1px; overflow:visible; " >

<DIV id="u16" class="treenode" style="position:absolute; left:0px; top:0px; width:92px; height:20px; overflow:visible;">

<div id="u17" class="u17_container"   >
<div id="u17_img" class="u17_normal detectCanvas"></div>
	<div id="u18" class="u18" style="visibility:hidden;"  >
	<div id="u18_rtf">
	</div>
	</div>
</div>





<DIV id="cncu16" style="position:absolute; left:0px; top:0px; width:1px; height:1px; overflow:visible; " >

<DIV id="u21" class="treenode" style="position:absolute; left:20px; top:20px; width:92px; height:20px; overflow:visible;">

<div id="u22" class="u22_container"   >
	<div id="u22_img" class="u22_normal detectCanvas"></div>
<div id="u23" class="u23"  >

</div>
</div>


</DIV>
</DIV>
</DIV>

<div id="u40" class="u40"  >
<div id="u30_rtf"><p style="text-align:left;">
	<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">摄像机类型:<span style="font-size:15px;color:red" >*</span></span>
	</p>
</div>
</div>
<SELECT id="u41" class="u41"   >
<OPTION  value="枪机">枪机</OPTION>
<OPTION  value="快球">快球</OPTION>
<OPTION  value="半球">半球</OPTION>
</SELECT>




</div>
	
   
 
</table>

</div>

</form>
</body>
</html>