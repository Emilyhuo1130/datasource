<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>监控组管理</title>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
		<link type="text/css" rel="stylesheet" href="../../css/table.css" />
		<link type="text/css" rel="stylesheet" href="../../css/fbmodal.css" />
		<link href="../../css/sample.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
		<script type="text/javascript" src="../../js/common.js"></script>
		<script type="text/javascript" src="../../js/jquery.fbmodel.js" ></script>
		<script type="text/javascript" src="../../js/groupManage.js" ></script>
			<script type="text/javascript">
				
			$(document).ready(function(){
				$("#controlName").val("");
				
				selectcontrolGroup('',1,10);
				
			
			
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
			<div style="border:1px solid #999999; width:958px;height:540px;"><!-- 框架边框 -->
			
		<div id="search" style="position:relative;left:30px;top:30px;">
						
				监控组名称：<input onblur="veriftycharacter(this,'errorNameChar')" type="text" name="controlName" id="controlName"/>
				<span style="display:none;color:red;" id="errorNameChar" >非法字符</span>
						 &nbsp;&nbsp;&nbsp; 
						 &nbsp;&nbsp;&nbsp; 
			  			
				
						
	           
	<div><button onclick="searchcontrolGroup(0);" style="position:relative;left:700px;" name="search" id="search_button" class="button">查询</button ></div>
	
	</div>
	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:50px;border-bottom:1px solid #888888;">
			<div style="position:relative;left:10px;top:70px;">
			<table  style="width:940px;
					height:300px;
					overflow:hidden;
					table-layout:fixed;
					border:2px solid #aaaacc;"
					border=1 cellspacing=0 
					class="tablesorter" >
			
			<INPUT  type="button" onclick="go();" value="添加监控组"/>
			<script type="text/javascript">
				function go(){
					location.href="addGroup.jsp";
				}
			</script>
			</button >
			<thead >
				<tr>
				<th width=5%>序号</th>
				<th width=25%>名称</th>
				<th width=15%>备注</th>
				<th width=15%>操作</th>
				
				</tr>
			</thead>
			<tbody>
				<!--<tr>
					<td>1</td>
					<td >白天仓库监控</td>
					<td >白天的</td>
					<td ><a href="href='javascript:deleteGroup(1);'">删除</a> |<a href="modifyGroup.jsp?id=1">修改</a></td>
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
	 <!--分页-->
	 <div style="position:relative;left:30px;top:55px;">
		<!--  <span id="current_page" style="display:none;">1</span> -->
			 <div id="paginationArea" style="height:20px;"></div>
                <div id="pages">
                     <a href="javascript:firstpage('controlGroup');" id="first_page">首页</a>
        	        <a href="javascript:backpage('controlGroup');" id="pre_page"> 上一页</a>
        	        <a href="javascript:;" id="current_page">1</a>
                    <a href="javascript:nextpage('controlGroup');" id="next_page">下一页</a>
                    <a href="javascript:lastpage('controlGroup');" id="last_page">末页</a>
                    <a href="javascript:;">共<span id="totalPage">2</span>页</a>
                </div>      
                </div>
                </div><!-- 分页定位终点 -->
                </div><!-- 表单定位终点 --> 
                </div><!-- 边框定位终点 -->
	</body>
</html>