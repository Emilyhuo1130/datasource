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
		//test();
			//浮动导航菜单
 					var userName = "<%=userName%>";
		user1_goto(userName);
 				
		//onReport_resum_Query();

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
    	<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;"style="color:#cba300;" >主动维保管理</a></div>
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
        <a  href="../report/_precaution_history_statistics_count2.jsp" >预警历史统计表</a>
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
        <a href="../report/_rcm_ticket2.jsp">主动维保通知单</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="../report/_device_info2.jsp" style="color:#0989aa;">设备履历表</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
  	<br/>
  		<form action="#" method="post">
  <div>
    <div style="display:inline-block;">
      <div id="query_criteria" style="padding-top: 14px;" >
        <label for="resum_condition1"></label>
        <select id="resum_condition1" class="combobox" name="criteria[condition1]">
          <option >专业</option>
          <option >通号</option>
          <option >供电</option>
          <option >工务</option>
          <option >车辆</option>
        </select>
       
        
        <label for="resum_condition3"></label>
        <select id="resum_condition3" class="combobox" name="criteria[condition3]">
          <option value="">线路</option>
	      <option value="10">10号线</option>
        </select>
        <label for="resum_condition4"></label>
        <select id="resum_condition4" class="combobox" name="criteria[condition4]">
         <option value="">车站</option>
         <option value="航中路站">航中路站</option>
         <option value="紫藤路">紫藤路</option>
         <option value="龙柏新村">龙柏新村</option>
        </select>
        <label for="resum_condition5"></label>
        <select id="resum_condition5" class="combobox" name="criteria[condition5]">
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
      <div id="time_range">
        <label for="start_time">开始时间</label>
        <input type="date" id="start_time" name="criteria[start_time]" style="height: 21px;" />
        <div style="width:3%;display: inline-block;"></div>
        <label for="end_time">结束时间</label>
        <input type="date" id="end_time" name="criteria[end_time]" style="height: 21px;" />
        <a style="float: right" class="button_query" onclick="query_resum_operation();" >
        </a>
      </div>
    </div>
  </div>
</form>
<div id="query_result" style="width:950px;padding-left:3px;margin-top: 0px;height:400px;">
<div style="height: 320px;border-right:1px solid black;">
  <div style="background-image: url('../../images/table_background.png');height:300px;">
    <table id="query_result_table" border="0" >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">

        <th scope="col" >序号</th>
        <th scope="col" >专业</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >生产厂家</th>
        <th scope="col" >购买日期</th>
        <th scope="col" >详情</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;" >
      <tr style="height:26px">
      	<td >1</td>
      	<td>供电</td>
      	<td>10</td>
      	<td>航中路</td>
      	<td>信号机</td>
      	<td>上海道路设备有限公司</td>
      	<td>2013-2-3</td>
      	<td><a href="#">查看详情</a></td>
      </tr>
      <tr style="background:#DDDDFF;height:26px">
      	<td>2</td>
      	<td>供电</td>
      	<td>5</td>
      	<td>航中路</td>
      	<td>高压开关柜</td>
      	<td>上海道路设备有限公司</td>
      	<td>2013-2-4</td>
      	<td><a href="#">查看详情</a></td>
      </tr>
      <tr style="height:26px">
      	<td>3</td>
      	<td>供电</td>
      	<td>5</td>
      	<td>航中路</td>
      	<td>400V馈线</td>
      	<td>上海道路设备有限公司</td>
      	<td>2013-2-6</td>
      	<td><a href="#">查看详情</a></td>
      </tr>
      <tr style="background:#DDDDFF;height:26px">
      	<td>4</td>
      	<td>供电</td>
      	<td>3</td>
      	<td>航中路</td>
      	<td>高压开关柜</td>
      	<td>上海道路设备有限公司</td>
      	<td>2013-2-9</td>
      	<td><a href="#">查看详情</a></td>
      </tr>
      </tbody>
    </table>
  </div>
  <div id="query_result_footer" style="width:948px;">
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="report_resum_backpage();"/>
     	 第<span id="equ_resum_nowpage">0</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="report_resum_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="equ_resum_totalpage"></span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="equ_resum_totalCount" style="text-align:right;"></span> 条记录
    </div>
  </div>
  <script type="text/javascript">
    /*OnAlertQuery();*/
    /*CheckBoxGroupInitialize(['select_all',
     'select_all_1',
     'select_all_2',
     'select_all_3',
     'select_all_4']);*/
    TableInitial('query_result_table', 7);
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
<script type="text/javascript">
  HighlightNav('spage_name');
</script>
</html>
