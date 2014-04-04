<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>系统配置</title>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
    	<link href="../../css/axurerp_pagespecificstyles.css" type="text/css" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
		<link type="text/css" rel="stylesheet" href="../../css/table.css" />
		<link type="text/css" rel="stylesheet" href="../../css/fbmodal.css" />
		<link href="../../css/sample.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
		<script type="text/javascript" src="../../js/common.js"></script>
		<script type="text/javascript" src="../../js/jquery.fbmodel.js" ></script>
		<script type="text/javascript" src="../../js/systemconfig.js" ></script>
			<script type="text/javascript">
				
			$(document).ready(function(){
				searchsystemConfig(1);
			
			
			});
			
			
			</script>
	
	</head>

	<body style="background-image: url('../../images/bank.jpg')">
	<!-- 消息提示框 -->
	<div class="test">
		<font><font id="someinfo">确定删除此条记录?</font>
			<a href="javascript: vaoid()" id="close"><font></font></a> 
	</div>
	<div  id="attendees" class="formFrame" style="height:450px;scrolling:none;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:550px;"><!-- 框架边框 -->
			
		<div id="search" style="position:relative;left:30px;top:20px;">
						
				<span style="font-size:20px">总磁盘剩余容量：</span><span id="surplus_capacity" style="font-size:20px">78</span><span style="font-size:20px">%</span>
						
	           
	
	</div>
	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:30px;border-bottom:1px solid #888888;">
			<div style="position:relative;left:10px;top:40px;">
			<table  style="width:940px;
					height:300px;
					overflow:hidden;
					table-layout:fixed;
					border:2px solid #aaaacc;"
					border=1 cellspacing=0 
					class="tablesorter" >
			</button >
	<input type="button" value="设置系统警戒值" onclick="settingSystemalarm_value();"/>
			<script type="text/javascript">
				function settingSystemalarm_value(){
					location.href="settingSystemalarm_value.jsp?";
				}
			</script>
			<thead >
				<tr>
				<th width=5%>序号</th>
				<th width=25%>磁盘名</th>
				<th width=20%>磁盘大小(T)</th>
				<th width=15%>剩余容量(%)</th>
				
				</tr>
			</thead>
			<tbody>
				<!--<tr>
					<td>1</td>
					<td >磁盘1</td>
					<td >30T</td>
					<td >50%</td>
				</tr>
				--><tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
							<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				
			</tbody>
			
			</table>
			</div><!-- table定位 -->
                </div>
                </div><!-- 分页定位终点 -->
                </div><!-- 表单定位终点 --> 
                </div><!-- 边框定位终点 -->
                <!--分页-->
	 <div style="position:relative;left:30px;top:-20px;">
		<!--  <span id="current_page" style="display:none;">1</span> -->
			 <div id="paginationArea" style="height:20px;"></div>
                <div id="pages">
                     <a href="javascript:firstpage('systemConfig');" id="first_page">首页</a>
        	        <a href="javascript:backpage('systemConfig');" id="pre_page"> 上一页</a>
        	        <a href="javascript:;" id="current_page">1</a>
                    <a href="javascript:nextpage('systemConfig');" id="next_page">下一页</a>
                    <a href="javascript:lastpage('systemConfig');" id="last_page">末页</a>
                    <a href="javascript:;">共<span id="totalPage">2</span>页</a>
                </div>      
                </div>
               
	<span style="font-size:20px">磁盘容量低于</span><input onblur="veriftyNumber(this);" style="font-size:20px" type="text" value="" id="least_capacity"/><span style="font-size:20px">%    自动删除三十天前数据</span>
	</p>
	<INPUT onclick="savesystemConfig();" id="systemconfigSvae" type="button" class="publicSave" value="保存"   >

	<INPUT id="systemconfigCancel" onclick="reback();" type="button" class="publicCancel" value="返回"   >
	</body>
</html>