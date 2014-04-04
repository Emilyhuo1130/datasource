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
 <script language="JavaScript" src="../../assets/javascripts/report.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/main_page.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/event.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
 <script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxtree.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
 <%String userName =(String)session.getAttribute("username"); %>
 <script type="text/javascript">
	$(document).ready(function(){
		//浮动导航菜单
		var userName = "<%=userName%>";
		user1_goto(userName);
		showKpiInfo();
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
   <!-- 	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div> --> 
		<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../cocc/cocclead.jsp" >首页</a></div>
    	<div   class="nav_item_normal" > <a  href="report_historyAlert_statistics_count.jsp" style="color:#cba300;">统计报表</a></div>
  </div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="report_historyAlert_statistics_count.jsp">告警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="_precaution_history_statistics_count.jsp">预警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="_health_index_analysis.jsp">健康指数分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
       <a href="javascript:;" style="color:#0989aa;">KPI分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="_rcm_ticket.jsp">主动维保通知单</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="_device_info.jsp">设备履历表</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
  	<br/>
  		<form action="#" method="post">
  <div>
    <div style="display:inline-block;">
      <div id="query_criteria" style="padding-top: 14px; width: 760px; padding-bottom: 14px;" >
        <label for="condition1"></label>
        <select id="condition1" class="combobox" name="criteria[condition1]">
          <option value="">系统</option>
          <option value="信号系统">信号系统</option>
          <option value="综合监控系统">综合监控系统</option>
        </select>
        <label for="condition2"></label>
        <select id="condition2" class="combobox" name="criteria[condition2]">
          <option value="">子系统</option>
	      <option value="轨旁设备">轨旁设备</option>
	      <option value="BAS系统">BAS系统</option>
	      <option value="电力SCADA系统">电力SCADA系统</option>
        </select>
        <label for="condition3"></label>
        <select id="condition3" class="combobox" name="criteria[condition3]">
          <option value="">线路</option>
	      <option value="10">10号线</option>
        </select>
        <label for="condition4"></label>
        <select id="condition4" class="combobox" name="criteria[condition4]">
         <option value="">车站</option>
         <option value="航中路站">航中路站</option>
         <option value="紫藤路">紫藤路</option>
         <option value="龙柏新村">龙柏新村</option>
        </select>
        <label for="condition5"></label>
        <select id="condition5" class="combobox" name="criteria[condition5]">
          <option value="">资产名称</option>
          <option value="道岔">道岔</option>
	      <option value="隧道风机通风系统">隧道风机通风系统</option>
	      <option value="信号机">信号机</option>
	      <option value="给排水系统">给排水系统</option>
	      <option value="高压开关柜">高压开关柜</option>
	      <option value="低压开关柜">低压开关柜</option>
	      <option value="400v馈线">400v馈线</option>
        </select>
      </div>
        <div id="time_range" style="width:608px;">
        <label for="start_time"><span style="color: black;">开始时间</span></label>
        <input type="date" id="start_time" name="criteria[start_time]" style="height: 21px;" />
         <label for="end_time"><span style="color: black;">结束时间</span></label>
        <input type="date" id="end_time" name="criteria[end_time]" style="height: 21px;" />
        <a style="float: right;" class="button_query" onclick="select_kpi_data();" ></a>
        </div>
    </div>
  </div>
</form>


<div id="query_result" style="width:938px;padding-left:10px;margin-top: 0px;height:400px;">
<div style="height: 320px;padding-left:0px;border-right:1px solid black;border-left: 1px solid gray;">
  <div style="background-image: url('../../assets/images/table_background.png') ;">
    <table id="query_result_table"  style="border:0;">
      <caption>
      </caption>
      <thead >
      <tr id="query_result_table_header" style="height:30px; background-color: #cccccc; background-image: none;">

        <th scope="col" style="border: 1px solid gray;" >序号</th>
        <th scope="col" style="border: 1px solid gray;">系统</th>
        <th scope="col" style="border: 1px solid gray;">子系统</th>
        <th scope="col" style="border: 1px solid gray;">线路</th>
        <th scope="col" style="border: 1px solid gray;">车站</th>
        <th scope="col" style="border: 1px solid gray;">资产名称</th>
        <th scope="col" style="border: 1px solid gray;">全年连续安全生产天数</th>
        <th scope="col" style="border: 1px solid gray;">设备强迫停运次数</th>
        <th scope="col" style="border: 1px solid gray;">设备非计划停运次数</th>
        <th scope="col" style="border: 1px solid gray;">关键设备平均无故障可用时间</th>
        <th scope="col" style="border: 1px solid gray;">关键设备平均修复时间</th>
        <th scope="col" style="border: 1px solid gray;">关键技术对象平均维修间隔时间</th>
        <th scope="col" style="border: 1px solid gray;">计划预估维修工时数与实际维修工时数差</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body"  style="border-left:0px;">
      
      </tbody>
    </table>
    
  </div>
 
</div>

 <div id="query_result_footer" style="position:relative;left:1px;width:935px;">
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="report_kpi_backpage();"/>
     	 第<span id="report_kpi_nowpage">0</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="report_kpi_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="report_kpi_totalpage"></span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="report_kpi_totalCount" style="text-align:right;"></span> 条记录
    </div>
  </div>
  	
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
