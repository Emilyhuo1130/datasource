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
 <script language="JavaScript" src="../../assets/javascripts/targetAnalysis.js"></script>
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
		//test();
		//浮动导航菜单
 					var userName = "<%=userName%>";
		user1_goto(userName);
		OnTargetMaintenQuery();
		getMaintenceCountTargetData("信号系统","#chart2 svg");
		
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
  <!--   	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" style="color:#cba300;">首页</a></div> -->
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
        <a  href="_repair_statistics.jsp">运营统计指标</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="_operative_statistics.jsp" style="color:#0989aa;">维修统计指标</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%;height:739px; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
    <br/>
    	<div id="query_result" style="padding-left:8px;width:942px;border-right:1px solid black;margin-top: 0px;350px">
  <div style="background-image: url('../../images/table_background.png'); width: 942px; margin: auto;height:300px; ">
    <table id="query_result_table" border="0"  >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">
        <th scope="col" >序号</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >平均无故障时间</th>
        <th scope="col" >平均维修时间</th>
        <th scope="col" >平均维修间隔时间</th>
        <th scope="col" >计划维修工时数与实际差</th>
        <th scope="col" >月份</th>
        <th scope="col" >更新时间</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;" >
      
      </tbody>
    </table>
    </div>
   <div id="query_result_footer" style="width: 940px;margin: auto;style=""height: 300px ">
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="targets_mainten_backpage();"/>
     	 第<span id="ope_mainten_nowpage">0</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="targets_mainten_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="ope_mainten_totalpage"></span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="tar_mainten_count" style="text-align:right;"></span> 条记录
    </div>
  </div>
  </div>
  
  <div style="height: 300px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;">
    <div style="padding-left: 20px; padding-top: 20px;">
      <label for="condition1"></label>
      <select id="condition1" class="combobox" name="criteria[condition1]" style="width: 120px;" onchange="getMaintenceCountTargetData(this.value,'#chart2 svg')">
          <option value="">系统</option>
          <option value="信号系统" selected="selected"">信号系统</option>
          <option value="综合监控系统">综合监控系统</option>
      </select>

     <%-- <label for="condition2"></label>
      <select id="condition2" class="combobox" name="criteria[condition2]">
          <option value="">资产名称</option>
          <option value="道岔">道岔</option>
	      <option value="隧道通风系统">隧道通风系统</option>
	      <option value="信号机">信号机</option>
	      <option value="给排水系统">给排水系统</option>
	      <option value="高压开关柜">高压开关柜</option>
	      <option value="低压开关柜">低压开关柜</option>
	      <option value="400v馈线">400v馈线</option>
      </select>

      <label for="condition3"></label>
      <select id="condition3" class="combobox" name="criteria[condition3]">
        <option>统计指标</option>
        <option>option2</option>
        <option>option3</option>
        <option>option4</option>
        <option>option5</option>
      </select>
    --%></div>
    <div id="chart2">
      <svg style="height: 250px;margin: auto;width: 900px;"></svg>
    </div>
    <%--<script type="text/javascript">
      initTargetLineChart("#chart2 svg");
    </script>
  --%></div>
</div>
    <br/>
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
