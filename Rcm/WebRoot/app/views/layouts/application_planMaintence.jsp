<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  		<title>主动维保平台</title>
  		
  		<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" />
  		<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
  		<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/main_page.css" />
  		<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/alert.css" />
  		<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/zTreeStyle.css" />
  		<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
 		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/impact.css"/>
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/dhtmlxTree/codebase/dhtmlxtree.css"/>
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/d3/nv.d3.css"/>
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/tabview/tabview.css"/>
  		<style type="text/css">
  				#nav_item_main{
  				opacity:.8;
  				width:80px;
  				height:30px;
  			}
  			
  		</style>
  		<script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
		 <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
  		<script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/alert_update.js"></script>
			<script language="JavaScript" src="../../assets/javascripts/main_page.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
		<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script> 
		<% String name =(String)session.getAttribute("username"); %>
		<script type="text/javascript">
 			$(document).ready(function(){
 				//浮动导航菜单
 				var userName = "<%=name%>";
 				user1_goto(userName);
 				
 			});
 		</script>
		
	</head>
	<body>
		<div id="part1" >
  			<div id="header_background" >
    			<div id="header_title" >
    				<div style="display: inline-block;float: right;">
       					 <a id="logout" href="../login/show.jsp">退出</a>
     				 </div>
      				<div style="display: inline-block;">主动维保平台</div>
      
      				<div style="display: inline-block;float: right; margin-right: 10px;">
      					 <span style="font-size: 14px; color: yellow; "><%=session.getAttribute("username")%></span>
      				</div>
    			</div>
  			</div>
 			 <div id="navigation" >
    			<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" style="color:#cba300;">首页</a></div>
    			<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" >告警管理</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
 			 </div> 
		</div>
	 <div id="part2">
		 	<%@include file='../main_page/show_planMaintence.jsp'%>"
		</div> 
		<div id="part3" >
  			UCS Holdings CopyRight 2013
		</div>
	
	</body>
	
</html>
