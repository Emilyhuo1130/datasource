<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <title>主动维保平台-计划修预警</title>
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/main_page.css" /> 
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/alert.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/impact.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/d3/nv.d3.css" />
  
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert_update.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/emerge1_update.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/event.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/dateFormat.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
<%String userName =(String)session.getAttribute("username"); %>
<script type="text/javascript">
  $(document).ready(function(){
	//浮动导航菜单
			var userName = "<%=userName%>";
		user1_goto(userName);
		//---------------------------------------------
 		 setTimeout(function(){
  			showFloatInfo();
 		 },100);
  	
 		var query_criteria={"statments":"0"};
 		showPlanMaintenceAlertInfo(query_criteria);
 		
 	 HighlightNav('spage_name');	
 	 //显示浮动
 	
 	
  });
  
  //通知单的浮动效果函数
	function showDetail(flag, a) {
	  //检查入参
		  var detailDiv = a.parentNode.getElementsByTagName("div")[0];		
			//var detailDiv = '显示所有的权限';
        if (flag) {
            detailDiv.style.display = "block";
        }
        else{
            detailDiv.style.display = "none";
      	  
        }
} 
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
    			<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div>
    			<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" style="color:#cba300;">告警管理</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
 			 </div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
			<!-- 子导航菜单始点 -->
		  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="current_alert_step0.jsp" >故障告警</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="current_faultPrecaution_alert_step0.jsp">预警告警</a>
      </li>
       <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="current_planMatntence_alert_step0.jsp" style="color:#0989aa;">计划修预警</a>
      </li>
    </ul>
  </div>
  <!-- 子导航菜单终点 -->
		<div class="steps_title" style="height: 20px;">
</div>
<div id="query_result" >

<div id="main_alert_content" style="padding-top: 0px; height: 350px; border-bottom: 1px solid gray;" >
  <div style="background-image: url('../../images/table_background.png');">
    <table id="query_result_table" border="0" >
      <caption>
      </caption>
      <thead >
      <tr id="query_result_table_header">
        <th scope="col" >序号</th>
        <th scope="col" >系统</th>
        <th scope="col" >子系统</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >预警内容</th>
        <th scope="col" >告警内容</th>
        <th scope="col" >告警时间</th>
        <th scope="col" >操作</th>
       
      </tr>
     </thead>
      <tbody id="query_result_table_body">
   

 
      </tbody>
    </table>
  </div>
  </div>
  <div id="query_result_footer" style="width: 958px;">

    <div id="query_result_footer_current_page">
        <img src="../../images/previous_triangle.png" onclick="backpage_plan();" />
          	第<span id="user1_emerge_page_nowpage">1</span>页
        <img onclick="nextpage_plan();" src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
    </div>
    <div id="query_result_footer_total_page">
          	共<span id="user1_emerge_page_totalpage">1</span> 页
    </div>
     <div id="query_result_footer_total_page" style="float:right;">
          	共<span id="user1_emerge_page_totalcount"></span> 条记录
    </div>
  </div>
  <script type="text/javascript">
    OnAlertQuery();
  </script>
  	<div style="display:block; clear: both;margin-top: 8px;">
    <div id="alert_info_header">
    </div>
    </div>
</div>
</div>	

</div>
<div id="part3" style="position:relative;top:200px;" >
  UCS Holdings CopyRight 2013
</div>

</body>

</html>
