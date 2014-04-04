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
  		<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
 		 <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/impact.css"/>
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/dhtmlxTree/codebase/dhtmlxtree.css"/>
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/d3/nv.d3.css"/>
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/tabview/tabview.css"/>
  
  		<script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/main_page.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/alert.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/event.js"></script>
 		 <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
 		<script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxtree.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script> 
		 <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
 		<%String userName =(String)session.getAttribute("username"); %>
	</head>
	<body style="" >
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
    			<div class="nav_item_main" ><a id="Main" href="../layouts/application.jsp" style="color:#cba300;">首页</a></div>
    			<div class="nav_item_normal" ><a id="Alert" href="../alert/process_selector.jsp" >告警管理</a></div>
    			<div class="nav_item_normal" ><a id="Event" href="../event/_sc.jsp" >实件分析</a></div>
   				<div class="nav_item_normal" ><a id="Impact" href="../impact/show.jsp" >影响分析</a></div>
    			<div class="nav_item_normal" ><a id="Target" href="../target/_repair_statistics.jsp" >指标分析</a></div>
    			<div class="nav_item_normal" ><a id="Strategy" href="../strategy/show.jsp" >策略制定</a></div>
    			<div class="nav_item_normal" ><a id="Ticket" href="../ticket/_notification_ticket.jsp" >通知单</a></div>
    			<div class="nav_item_normal" ><a id="Report" href="../report/_alert_history_statistics.jsp" >报表管理</a></div>
 			 </div>
		</div>
		<div id="part2">
			<div style="margin-top: 20px;">
				<%@include file="_step3.jsp" %>
			</div>
		</div>
		<div id="part3" >
  			UCS Holdings CopyRight 2013
		</div>
	<script type="text/javascript">
  		HighlightNav('page_names');
	</script>
	</body>
</html>
