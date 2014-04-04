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
		//showKpiInfo();
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
       <a href="javascript:;" style="color:#0989aa;">KPI分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="../report/_rcm_ticket2.jsp">主动维保通知单</a>
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
      <div id="query_criteria" style="padding-top: 14px; width: 760px; padding-bottom: 14px;" >
        <label for="condition1"></label>
        <select style="height: 21px;" id="condition1" class="combobox" name="criteria[condition1]">
           <option >专业</option>
          <option >通号</option>
          <option >供电</option>
          <option >工务</option>
          <option >车辆</option>
        </select>
       
        <label for="condition3"></label>
        <select style="height: 21px;" id="condition3" class="combobox" name="criteria[condition3]">
          <option value="">线路</option>
	      <option value="10">10号线</option>
        </select>
        
      
        
        <label for="start_time"><span style="color: black;">开始时间</span></label>
        <input type="date" id="start_time" name="criteria[start_time]" style="height: 21px;" />
         <label for="end_time"><span style="color: black;">结束时间</span></label>
        <input type="date" id="end_time" name="criteria[end_time]" style="height: 21px;" />
        <a style="float: right;" class="button_query" onclick="select_kpi_data();" ></a>
       
   
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
        <th scope="col" style="border: 1px solid gray;">专业</th>
        <th scope="col" style="border: 1px solid gray;">线路</th>
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
      	<tr  style="height:26px">
      		<td>1</td>
      		<td>供电</td>
      		<td>10</td>
      		<td>360</td>
      		<td>2</td>
      		<td>2</td>
      		<td>362</td>
      		<td>0.5</td>
      		<td>150</td>
      		<td>1</td>
      	</tr>
      	<tr  style="height:26px;background:#DDDDFF">
      		<td>2</td>
      		<td>通号</td>
      		<td>10</td>
      		<td>361</td>
      		<td>1</td>
      		<td>1</td>
      		<td>362</td>
      		<td>1</td>
      		<td>136</td>
      		<td>2</td>
      	</tr>
      	<tr  style="height:26px">
      		<td>3</td>
      		<td>工务</td>
      		<td>10</td>
      		<td>359</td>
      		<td>1</td>
      		<td>2</td>
      		<td>363</td>
      		<td>0.5</td>
      		<td>127</td>
      		<td>2</td>
      	</tr>
      	<tr  style="height:26px;background:#DDDDFF">
      		<td>4</td>
      		<td>车辆</td>
      		<td>10</td>
      		<td>362</td>
      		<td>2</td>
      		<td>3</td>
      		<td>363</td>
      		<td>1</td>
      		<td>206</td>
      		<td>1</td>
      	</tr>
      </tbody>
    </table>
    
  </div>
 
</div>

 <div id="query_result_footer" style="position:relative;left:1px;width:935px;">
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="report_kpi_backpage();"/>
     	 第<span id="report_kpi_nowpage">1</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="report_kpi_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="report_kpi_totalpage">1</span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="report_kpi_totalCount" style="text-align:right;">4</span> 条记录
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
