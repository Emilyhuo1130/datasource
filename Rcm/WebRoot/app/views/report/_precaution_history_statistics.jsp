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
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/main_page.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/event.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/report.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
 <script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
 <%String userName =(String)session.getAttribute("username"); 
 	String req = request.getParameter("req");
 	String  req_component  = new String(request.getParameter("req").getBytes("ISO-8859-1"),"utf-8");
 %>
 <script type="text/javascript">
	$(document).ready(function(){
		//浮动导航菜单
				var userName = "<%=userName%>";
		user1_goto(userName);
		//加载数据
		var component="<%=req_component%>";
		var req={"component":component};
		//alert("rq="+req);
		showFultureReportInfo_Details(req);

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
    	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" style="color:#cba300;">首页</a></div>
    	<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" >告警管理</a></div>
    	<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    	<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
  </div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="../report/report_historyAlert_statistics_count.jsp">告警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="../report/_precaution_history_statistics_count.jsp" style="color:#0989aa;">预警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="../report/_health_index_analysis.jsp">健康指数分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
       <a href="../report/_kpi_analysis.jsp">KPI分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="../report/_rcm_ticket.jsp">主动维保通知单</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="../report/_device_info.jsp">设备履历表</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
  <br/>
  		<form action="#" method="post">
  <div>
    <div style="display:inline-block;">
      <div id="query_criteria" style="padding-top: 14px;" >
        <label for="report_condition1"></label>
        <select id="report_condition1" class="combobox" name="criteria[condition1]">
          <option value="">系统</option>
          <option value="信号系统">信号系统</option>
          <option value="综合监控系统">综合监控系统</option>
        </select>
        <label for="report_condition2"></label>
        <select id="report_condition2" class="combobox" name="criteria[condition2]">
          <option value="">子系统</option>
	      <option value="轨旁设备">轨旁设备</option>
	      <option value="BAS系统">BAS系统</option>
	      <option value="电力SCADA系统">电力SCADA系统</option>
        </select>
        <label for="report_condition3"></label>
        <select id="report_condition3" class="combobox" name="criteria[condition4]">
          <option value="">线路</option>
	      <option value="10">10号线</option>
        </select>
        <label for="report_condition4"></label>
        <select id="report_condition4" class="combobox" name="criteria[condition5]">
         <option value="">车站</option>
         <option value="航中路站">航中路站</option>
         <option value="紫藤路">紫藤路</option>
         <option value="龙柏新村">龙柏新村</option>
        </select>
       <label for="report_condition5"></label>
        <select id="report_condition5" class="combobox" name="criteria[condition6]">
          <option value="">资产名称</option>
          <option value="道岔">道岔</option>
	      <option value="隧道风机通风系统">隧道风机通风系统</option>
	      <option value="信号机">信号机</option>
	      <option value="给排水系统">给排水系统</option>
	      <option value="高压开关柜">高压开关柜</option>
	      <option value="低压开关柜">低压开关柜</option>
	      <option value="400v馈线">400v馈线</option>
        </select>
        <label for="report_condition6"></label>
        <select id="report_condition6" class="combobox" name="criteria[condition7]" style="margin-right: 0px;">
          <option value="">预警等级</option>
          <option value="1">一级</option>
          <option value="2">二级</option>
          <option value="3">三级</option>
          <option value="4">四级</option>
        </select>
      </div>
      <div id="time_range">
        <label for="report_start_time">开始时间</label>
        <input type="date" id="report_start_time" name="criteria[start_time]" style="height: 21px;" />
        <div style="width:3%;display: inline-block;"></div>
        <label for="report_end_time">结束时间</label>
        <input type="date" id="report_end_time" name="criteria[end_time]" style="height: 21px;" />
        <a style="float: right" class="button_query" onclick="query_pre_report();" >
        </a>
      </div>
    </div>
  </div>
</form>
<div id="query_result" style="padding-left:10px;width:938px;margin-top: 0px;height:400px;">
<div style="height:320px;border-right:1px solid black;">
  <div style="background-image: url('../../images/table_background.png');height:300px;">
    <table id="query_result_table" border="0" >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">
        <th scope="col" >序号</th>
        <th scope="col" >系统</th>
        <th scope="col" >子系统</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >预警内容</th>
        <th scope="col" >预警等级</th>
        <th scope="col" >预警状态</th>
        <th scope="col" >预警时间</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;">
      </tbody>
    </table>
  </div>
 <div id="query_result_footer">
    <div id="query_result_footer_current_page" >
      <img src="../../images/previous_triangle.png"  onclick="report_pre_backpage();"/>
     	 第<span id="report_pre_page_nowpage">0</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="report_pre_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="report_pre_page_totalpage"></span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="report_pre_page_totalCount" style="text-align:right;"></span> 条记录
    </div>
  </div>
  <script type="text/javascript"></script>
</div>	
  <br/>
  </div>
  </div>
</div>
</div>

<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

<script type="text/javascript">
  HighlightNav('spage_name');
</script>
</body>
</html>
