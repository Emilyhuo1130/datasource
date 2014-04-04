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
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/main_page.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert_update.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/report.js"></script>
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
		onReport_Health_Query();
		onReport_operation_Query(); 

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
 <!--   <div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div> --> 
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
        <a  href="../report/report_historyAlert_statistics_count.jsp">告警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="../report/_precaution_history_statistics_count.jsp">预警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="../report/_health_index_analysis.jsp" style="color:#0989aa;">健康指数分析表</a>
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
  		<div class="TabView" id="TabView">
 		 <div class="Tabs" style="max-width: 100%; border-bottom: 1px solid black; padding-left: 15px; padding-top: 15px;">
   		 <a style="border: 1px solid gray; border-bottom: 0px;">设施指数</a>
   		 <a style="border: 1px solid gray; border-bottom: 0px;">运营指数</a>
  		</div>
  		<div class="Pages" style="max-width: 100%; height: 739px;">
    	<div class="Page">
      	<div class="Pad">
        <!-- *** Page1 Start *** -->
        <br />
       		<form action="#" method="post">
  <div>
    <div style="display:inline-block; width: 730px; padding-bottom: 14px;">
      <div id="query_criteria" style="padding-top: 0px;"><%--
        <label for="condition1"></label>
        <select id="condition1" class="combobox" name="criteria[condition1]">
          <option>系统</option>
          <option>option2</option>
          <option>option3</option>
          <option>option4</option>
          <option>option5</option>
        </select>
        <label for="condition2"></label>
        <select id="condition2" class="combobox" name="criteria[condition2]">
          <option>子系统</option>
          <option>option2</option>
          <option>option3</option>
          <option>option4</option>
          <option>option5</option>
        </select>
        --%><label for="condition1"></label>
        <select id="condition1" class="combobox" name="criteria[condition1]">
          <option value="">线路</option>
	          <option value="10">10号线</option>
        </select>
        <label for="condition2"></label>
        <select id="condition2" class="combobox" name="criteria[condition2]">
         <option value="">车站</option>
         <option value="航中路站">航中路站</option>
         <option value="紫藤路">紫藤路</option>
         <option value="龙柏新村">龙柏新村</option>
        </select>
     <label for="condition3"></label>
        <select id="condition3" class="combobox" name="criteria[condition3]">
          <option value="">资产名称</option>
          <option value="道岔">道岔</option>
	      <option value="隧道风机通风系统">隧道风机通风系统</option>
	      <option value="信号机">信号机</option>
	      <option value="给排水系统">给排水系统</option>
	      <option value="高压开关柜">高压开关柜</option>
	      <option value="低压开关柜">低压开关柜</option>
	      <option value="400v馈线">400v馈线</option>
        </select>
        <label for="condition4"></label>
        <select id="condition4" class="combobox" name="criteria[condition4]">
          <option value="">健康等级</option>
          <option value="1">一级</option>
          <option value="2">二级</option>
          <option value="3">三级</option>
          <option value="4">四级</option>
        </select>
        <a style="float: right" class="button_query" onclick="query_health_equ_report();" ></a>
      </div>
    </div>
  </div>
</form>
<div id="query_result" style="width:938px;padding-left:10px;margin-top: 0px;height:400px;">
<div style="height: 320px;border-right:1px solid black;">
  <div style="background-image: url('../../images/table_background.png');height:300px;">
    <table class="query_result_table" id="facility_index_query_result_table" border="0" >
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
        <th scope="col" >位置编码</th>
        <th scope="col" >资产描述</th>
        <th scope="col" >健康等级</th>
        <%--<th scope="col" >趋势查看</th>
      --%></tr>
      </thead>
      <tbody class= "query_result_table_body" id="query_result_table_body" style="border:hidden;">
      </tbody>
    </table>
  </div>
 <div id="query_result_footer" >
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="report_equp_backpage();"/>
     	 第<span id="report_page_nowpage">0</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="report_equp_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="report_page_totalpage"></span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="report_page_totalCount" style="text-align:right;"></span> 条记录
    </div>
  </div>
  <div id="chart1" style="margin-top: 20px;">
    <svg></svg>
  </div>
  <script type="text/javascript">
    //OnFacilityQuery();
   // TableInitial('facility_index_query_result_table', 10);
  </script><%--
  <script type="text/javascript">
    initLineChart('#chart1 svg');
  </script>
--%></div>

        <!-- *** Page1 End ***** -->
	</div>
      </div>
    </div>

    <!-- *** Page2 Start *** -->

    <div class="Page">
      <div class="Pad">
        <br />
        	<form action="#" method="post">
  <div>
    <div style="display:inline-block; width: 850px;margin-bottom: 15px;">
      <div id="query_criteria" style="padding-top: 0px; width: 100%;"><%--
        <label for="condition2"></label>
        <select id="condition2" class="combobox" name="criteria[condition2]">
          <option>系统</option>
          <option>option2</option>
          <option>option3</option>
          <option>option4</option>
          <option>option5</option>
        </select>

        <label for="condition3"></label>
        <select id="condition3" class="combobox" name="criteria[condition3]">
          <option>子系统</option>
          <option>option2</option>
          <option>option3</option>
          <option>option4</option>
          <option>option5</option>
        </select>

        --%><label for="operation_condition1"></label>
          <select id="operation_condition1" class="combobox" name="criteria[condition1]">
          <option value="">线路</option>
	          <option value="10">10号线</option>
        </select>
        </select>

       <label for="operation_condition2"></label>
        <select id="operation_condition2" class="combobox" name="criteria[condition2]">
          <option value="">健康等级</option>
          <option value="1">一级</option>
          <option value="2">二级</option>
          <option value="3">三级</option>
          <option value="4">四级</option>
        </select>


        <label for="start_time"><span style="color:black; font-size: 14px;">开始时间</span></label>
        <input type="date" id="start_time" name="criteria[start_time]" style="height: 21px; width: 100px;" />
        <label for="end_time"><span style="color:black; font-size: 14px;">结束时间</span></label>
        <input type="date" id="end_time" name="criteria[end_time]" style="height: 21px;width: 100px;" />
        <a style="float: right" class="button_query" onclick="query_ope_operation();" >
        </a>
      </div>
    </div>
  </div>
</form>
<div id="query_result" style="width:938px;padding-left:10px;margin-top: 0px;height:400px;">
<div style="height:320px;border-right:1px solid black;">
  <div style="background-image: url('../../images/table_background.png');height:300px;">
    <table class="query_result_table" id="operative_index_query_result_table" border="0" >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">
        <th scope="col" >序号</th>
        <th scope="col" >线路</th>
        <th scope="col" >健康指数</th>
        <th scope="col" >一级报警数</th>
        <th scope="col" >二级报警数</th>
        <th scope="col" >三级报警数</th>
        <th scope="col" >四级报警数</th>
        <th scope="col" style="width:100px;" > 更新时间</th>
      </tr>
      </thead>
      <tbody class="query_result_table_body" id="operative_index_query_result_table_body" style="border:hidden;">
      </tbody>
    </table>
  </div>
  <div id="query_result_footer" >
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="report_backpage();"/>
     	 第<span id="operation_page_nowpage">0</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="report_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="operation_page_totalpage"></span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="operation_page_totalCount" style="text-align:right;"></span> 条记录
    </div>
  </div>
  <script type="text/javascript">
    //OnOperativeQuery();
    //TableInitial('operative_index_query_result_table', 6);
  </script>
</div>
        <!-- *** Page2 End ***** -->
        </div>
      </div>
    </div>
    <script type="text/javascript">
      tabview_initialize('TabView');
    </script>
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
