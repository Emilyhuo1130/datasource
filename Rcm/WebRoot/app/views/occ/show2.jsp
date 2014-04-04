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
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/zTreeStyle.css"/>
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/d3/nv.d3.css"/>
  		<link rel="stylesheet" type="text/css" href="../../assets/stylesheets/tabview/tabview.css"/>
  
		 <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
		 <script language="JavaScript" src="../../assets/javascripts/jquery.ztree.core-3.5.js"></script> 
		 <script language="JavaScript" src="../../assets/javascripts/jquery.ztree.excheck-3.5.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
 		 <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
		<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script> 
 		  <script language="JavaScript" src="../../assets/javascripts/occ-engineer.js"></script>
		<%String userName =(String)session.getAttribute("username"); %>
 		<script type="text/javascript">
  		<%String rid = request.getParameter("id");
  		String equipmentId2 = request.getParameter("equipmentId");%>
  		var operator=null;
  			$(document).ready(function(){
  			//浮动导航菜单
  				var userName = "<%=userName%>";
  				operator=userName;
  				user1_goto(userName);
  				
  				getDetailInfo3(<%=rid%>);
  				getReqData("<%=equipmentId2%>");
  				
  			});
            </script>
           	
	</head>
	<body style="" >
		<!-- 用户提示框始 -->
		<div id="showUsers" class='detail_info'></div>
	<!-- 用户提示框终 -->
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
    		<!-- 	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div> -->
    			<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" style="color:#cba300;">告警管理</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
 	</div> 
		</div>
		<div id="part2">
			<div style="margin-top: 20px;">
				<%@include file="_step2.jsp" %>
			</div>
		</div>
		<div id="part3" >
  			UCS Holdings CopyRight 2013
		</div>
	
	</body>
</html>
