<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <title>主动维保平台</title>
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/main_page.css" /> 
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/alert.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/impact.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/dhtmlxTree/codebase/dhtmlxtree.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/d3/nv.d3.css" />
  
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
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
		//onReport_notify_Query();

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
    	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div>
    	<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" >告警管理</a></div>
    	<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    	<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" style="color:#cba300;">主动维保管理</a></div>
 </div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="../report/report_historyAlert_statistics_count2.jsp">告警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="../report/_precaution_history_statistics_count2.jsp">预警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="../report/_health_index_analysis2.jsp">健康指数分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
       <a href="../report/_kpi_analysis2.jsp">KPI分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="../report/_rcm_ticket2.jsp" style="color:#0989aa;">主动维保通知单</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="../report/_device_info2.jsp">设备履历表</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
  	<br/>
  		<form action="#" method="post">
  <div>
    <div style="display:inline-block;">
      <div id="query_criteria" style="padding-top: 14px;" >
        <label for="operation_condition1"></label>
        <select style="height: 21px;" id="operation_condition1" class="combobox" name="criteria[condition1]">
          <option >专业</option>
          <option >通号</option>
          <option >供电</option>
          <option >工务</option>
          <option >车辆</option>
        </select>
       
       
       
      
     
    
        <label for="start_time" style="color: black;">开始时间</label>
        <input type="date" id="start_time" name="criteria[start_time]" style="height: 21px;" />
        <div style="width:3%;display: inline-block;"></div>
        <label for="end_time" style="color: black;">结束时间</label>
        <input type="date" id="end_time" name="criteria[end_time]" style="height: 21px;" />
        <a style="float: right" class="button_query" onclick="query_noti_operation();" >
        </a>
    
  </div>
</form>
<div id="query_result" style="padding-left:10px;width:938px;margin-top: 0px;height:400px;">
<div style="height: 320px;border-right:1px solid black;">
  <div style="background-image: url('../../images/table_background.png');height:300px;">
    <table id="query_result_table" >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">
        <th scope="col" >序号</th>
        <th scope="col" >专业</th>
        <th scope="col" >通知单编号</th>
        <th scope="col" >通知单状态</th>
        <th scope="col" >通知单下达时间</th>
        <th scope="col" >详情</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;">
      <tr style="height:26px">
      	<td>1</td>
      	<td>供电</td>
      	<td>3423144</td>
      	<td>已下达</td>
      	<td>2013-9-22</td>
      	<td><a href="#">详情</a></td>
      </tr>
       <tr style="height:26px;background:#DDDDFF">
      	<td>2</td>
      	<td>供电</td>
      	<td>2453144</td>
      	<td>已下达</td>
      	<td>2013-9-23</td>
      	<td><a href="#">详情</a></td>
      </tr>
       <tr style="height:26px">
      	<td>3</td>
      	<td>供电</td>
      	<td>3476544</td>
      	<td>已下达</td>
      	<td>2013-9-25</td>
      	<td><a href="#">详情</a></td>
      </tr>
       <tr style="height:26px;background:#DDDDFF">
      	<td>4</td>
      	<td>供电</td>
      	<td>3425431</td>
      	<td>已下达</td>
      	<td>2013-10-22</td>
      	<td><a href="#">详情</a></td>
      </tr>
       <tr style="height:26px">
      	<td>5</td>
      	<td>供电</td>
      	<td>3632144</td>
      	<td>已下达</td>
      	<td>2013-10-22</td>
      	<td><a href="#">详情</a></td>
      </tr>
       <tr style="height:26px;background:#DDDDFF">
      	<td>6</td>
      	<td>供电</td>
      	<td>3423546</td>
      	<td>已下达</td>
      	<td>2013-10-25</td>
      	<td><a href="#">详情</a></td>
      </tr>
      </tbody>
    </table>
  </div>
  <div id="query_result_footer" >
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="report_noti_backpage();"/>
     	 第<span id="operation_noti_page_nowpage">1</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="report_noti_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="operation_noti_page_totalpage">1</span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="operation_noti_page_totalCount" style="text-align:right;">4</span> 条记录
    </div>
  </div>
  <script type="text/javascript">
    /*OnAlertQuery();*/
    /*CheckBoxGroupInitialize(['select_all',
     'select_all_1',
     'select_all_2',
     'select_all_3',
     'select_all_4']);*/
    //TableInitial('query_result_table', 7);
  </script>
</div> 
  	<br/>
  </div>
  </div>
</div>
</div>
<div id="part3" >
  UCS Holdings CopyRight 2013
</div>
</body>
</html>
