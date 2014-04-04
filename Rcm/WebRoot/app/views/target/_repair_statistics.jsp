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
 <script language="JavaScript" src="../../assets/javascripts/alert_update.js"></script>
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
		showOperationTarget();
		getMultiBarChartData(10,"#chart1 svg");
		TendLineChangeEvent(10,"#chart2 svg");
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
    		<!--  	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div>-->
    			<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" >告警管理</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;"style="color:#cba300;" >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
 			 </div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="_repair_statistics.jsp" style="color:#0989aa;">运营统计指标</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="_operative_statistics.jsp">维修统计指标</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
    <br/>
   <%--<div style="width:942px;height:379px;border-right:1px solid black;">
    --%><div id="query_result" style="padding-left:8px;width:942px;border-right:1px solid black;bomargin-top: 0px;" >
  <div style="background-image: url('../../assets/images/table_background.png'); width: 942px; margin: auto;height:300px;">
    <table id="query_result_table" border="0"  >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">
        <th scope="col" >序号</th>
        <th scope="col" >线路</th>
        <th scope="col" >安全生产天数</th>
        <th scope="col" >强迫停运天数</th>
        <th scope="col" >非计划停运天数</th>
        <th scope="col" >月份</th>
        <th scope="col" >更新时间</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;" >
      
      </tbody>
    </table>
   </div>
  
  <div id="query_result_footer" style="width: 940px;margin: auto; ">
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="targets_backpage();"/>
     	 第<span id="ope_page_nowpage">0</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="targets_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="ope_page_totalpage"></span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="tar_ope_count" style="text-align:right;"></span> 条记录
    </div>
  </div>
 <!--  </div>-->
  </div><!-- 分边 -->
  
  <div style="height: 300px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;margin-bottom: 25px;">
    <div style="padding-left: 20px; padding-top: 10px;padding-bottom: -5px;">
     	 <label for="condition1"></label>
     	 <select id="condition1" class="combobox" name="criteria[condition2]" onchange="MultiBarChangeEvent(this.value,'#chart1 svg');">
        <option value="">线路（多选）</option>
        <option value="10" selected="selected">10号线</option>
      </select>
    </div>
    <div id="chart1" style="margin: auto;width: 900px; ">
      <svg style="height: 250px;"></svg>
    </div>
    </div>

  <div style="height: 300px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px; margin-bottom: 8px;">
    <div style="padding-left: 20px; padding-top: 20px;">
      <label for="condition1"></label>
   <label for="condition2"></label>
      <select id="condition2" class="combobox" name="criteria[condition2]" onchange="TendLineChangeEvent(this.value,'#chart2 svg');">
        <option value="">线路（多选）</option>
        <option value="10" selected="selected">10号线</option>
      
      </select>
    </div>
    <div id="chart2" style="margin: auto;width: 900px;">
      <svg style="height: 250px;"></svg>
    </div>
    </div>
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
