<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>摄像机管理</title>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link href="../../css/axurerp_pagespecificstyles.css" type="text/css" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
		<link type="text/css" rel="stylesheet" href="../../css/table.css" />
		<link href="../../css/sample.css" rel="stylesheet" type="text/css" />
		<link type="text/css" rel="stylesheet" href="../../css/fbmodal.css" />
		<script type="text/javascript" src="../../js/jquery-1.7.2.js" ></script>
		<script type="text/javascript" src="../../js/common.js"></script>
		<script type="text/javascript" src="../../js/jquery.fbmodel.js" ></script>
		<script type="text/javascript" src="../../js/VCRManage.js" ></script>
			<script type="text/javascript">
				
			$(document).ready(function(){
				$("#VCRName").val("");
				$("#VCRModel").val("");
				selectAllVCRS('','','',1,10);
			
			});
			
			
			</script>
	
	</head>

	<body style="background-image: url('../../images/bank.jpg')">
	<div  id="attendees" class="formFrame" style="height:450px;scrolling:none;">

			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:540px;"><!-- 框架边框 -->
			
		<div id="search" style="position:relative;left:30px;top:30px;">
						
				录像机名称：<input onblur="veriftycharacter(this,'errorNameChar')" type="text" name="VCRName" id="VCRName"/>
				<span style="display:none;color:red" id="errorNameChar" >非法字符</span>
						 &nbsp;&nbsp;&nbsp; 
						 &nbsp;&nbsp;&nbsp; 
			  			
				录像机型号：<input onblur="veriftycharacter(this,'errorModelChar')" type="text" name="VCRModel" id="VCRModel"/>
				<span style="display:none;color:red" id="errorModelChar" >非法字符</span><!--
						 &nbsp;&nbsp;&nbsp; 
						 &nbsp;&nbsp;&nbsp; 
			           
			    		
				所属区位：<select id="zone" name="">
							<option>--请选择--</option>
							<option value="仓库">仓库</option>
							<option value="办公室">办公室</option>
						  </select>
						
	           
	--><div><button onclick="searchVCRManagee(0);" style="position:relative;left:700px;" name="search" id="search_button" class="button">查询</button ></div>
	
	</div>
		<div class="test">
			<font><font id="someinfo">确定删除此条记录?</font>
				<a href="javascript: vaoid()" id="close"><font></font></a> 
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
			<INPUT  type="button" onclick="addVCR();" value="添加录像机"/>
			<script type="text/javascript">
				function addVCR(){
					location.href="addVCR.jsp";
				}
			</script>
			
			</button >
			<thead >
				<tr>
				<th width=5%>序号</th>
				<th width=25%>录像机名称</th>
				<th width=15%>IP</th>
				<th width=15%>录像机型号</th>
				
				<th width=10%>操作</th>
				</tr>
			</thead>
			<tbody>
				<!--<tr>
					<td>1</td>
					<td >一号摄像机</td>
					<td >192.168.2.101</td>
					<td >索尼</td>
					<td ><a href='javascript:deleteVCR("+e.id+")'>删除</a> | <a href="modifyoneVCR.jsp?id=1">修改</a></td>
				</tr>
				--><tr>
					<td></td>
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
					<td></td>
				</tr>
				<tr>
					<td></td>
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
					
					<td></td>
				</tr>
				<tr>
					<td></td>
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
				
					<td></td>
				</tr>
				<tr>
					<td></td>
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
				
					<td></td>
				</tr>
				<tr>
					<td></td>
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
                    <a href="javascript:firstpage('VCRManage');" id="first_page">首页</a>
        	        <a href="javascript:backpage('VCRManage');" id="pre_page"> 上一页</a>
        	        <a href="javascript:;" id="current_page">1</a>
                    <a href="javascript:nextpage('VCRManage');" id="next_page">下一页</a>
                    <a href="javascript:lastpage('VCRManage');" id="last_page">末页</a>
                    <a href="javascript:;">共<span id="totalPage">2</span>页</a>
                </div>      
                </div>
                </div><!-- 分页定位终点 -->
                </div><!-- 表单定位终点 --> 
                </div><!-- 边框定位终点 -->
	</body>
</html>