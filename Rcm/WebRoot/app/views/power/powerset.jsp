<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <meta name="author" content="Lu Jun" />
  <meta name="description" content="RCM" />
  <meta name="keywords" content="RCM, Mac" />
  <title>主动维保平台</title>
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" /> 
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/main_page.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/alert.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/zTreeStyle.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/impact.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/dhtmlxTree/codebase/dhtmlxtree.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/d3/nv.d3.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/tabview/tabview.css"/>
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/jquery.ztree.core-3.5.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/jquery.ztree.excheck-3.5.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
   <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
  <script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/power.js"></script>
  
  <%String userName =(String)session.getAttribute("username"); %>
	<script type="text/javascript">
		$(document).ready(function(){
			//浮动导航菜单
					var userName = "<%=userName%>";
		user1_goto(userName);
			
	});
	</script>
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
    			<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../power/powerset.jsp" style="color:#cba300;">角色-用户权限管理</a></div>
    		 </div> 
</div>
<div id="part2" >
	<div id="alert_info_header">
  <div id="sub_nav" style="line-height: 30px; font-weight: bold; font-size: 15px; color: #424242; margin-top: 20px;">
    权限设置
  </div>
</div>


<div id="device_tree_query_result" style="height:500px;border: 1px gray solid;padding-bottom: 0px;">
  <div id="device_tree" style="height: 500px;width:470px;float:left">
    <%@include file="powerchange.jsp" %>
  </div>

  <div style="float:left; width: 470px;height: 500px;">
	 <%@include file="userset.jsp" %>
   
  </div>
</div>
	
</div>

<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

</body>
</html>
