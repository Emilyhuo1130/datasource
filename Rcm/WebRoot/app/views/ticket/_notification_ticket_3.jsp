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
 <script language="JavaScript" src="../../assets/javascripts/alert_update.js"></script>
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
	//--------------------------------------------------------------	
		OnNotifyPageQuery(1,"计划预警");
	});
	//通知单的浮动效果函数
		function showDetail(flag, a) {
			
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
				//var detailDiv = '显示所有的权限';
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
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
    <!-- 	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div> -->
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
        <a  href="_notification_ticket.jsp">故障告警维保通知单</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="_notification_ticket_2.jsp" >预警告警维保通知单</a>
      </li>
       <li class="list_separator">
        |
      </li>
       <li class="list_item">
        <a href="_notification_ticket_3.jsp" style="color:#0989aa;">计划预警维保通知单</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none;">
  <br />
  <form action="#" method="post">
  <div>
    <div style="display:inline-block;">
      <div id="query_criteria" style="padding-top: 0px; width: 100%;">
        <label for="condition3"></label>
        <select id="condition3" class="combobox" name="criteria[condition1]">
          <option value="">所属系统</option>
          <option value="信号系统">信号系统</option>
          <option value="综合监控系统">综合监控系统</option>
        </select>

        <label for="condition2"></label>
        <select id="condition2" class="combobox" name="criteria[condition3]">
          <option value="">线路</option>
          <option value="10">十号线</option>
        </select>

        <label for="condition1"></label>
        <select id="condition1" class="combobox" name="criteria[condition4]">
          <option value="">车站</option>
          <option value="航中路站">航中路站</option>
          <option value="紫藤路">紫藤路</option>
          <option value="龙柏新村">龙柏新村</option>
        </select>

        <label for="condition4"></label>
        <select id="condition4" class="combobox" name="criteria[condition5]">
          <option value="">资产名称</option>
          <option value="道岔">道岔</option>
	      <option value="隧道风机通风系统">隧道通风系统</option>
	      <option value="信号机">信号机</option>
	      <option value="给排水系统">给排水系统</option>
	      <option value="高压开关柜">高压开关柜</option>
	      <option value="低压开关柜">低压开关柜</option>
	      <option value="400v馈线">400v馈线</option>
        </select>

        <label for="condition5"></label>
        <select id="condition5" class="combobox" name="criteria[condition6]">
          <option value="">告警等级</option>
          <option value="大修">大修</option>
          <option value="中修">中修</option>
          <option value="小">小修</option>
        </select>
         <div style="padding:10px;">
        <label for="notify_page_start_time"><span style="color:black; font-size: 14px;">开始时间</span></label>
        <input type="date" id="notify_page_start_time" name="criteria[start_time]" style="height: 21px; width: 100px;" />
        <label for="notify_page_end_time"><span style="color:black; font-size: 14px;">结束时间</span></label>
        <input type="date" id="notify_page_end_time" name="criteria[end_time]" style="height: 21px;width: 100px;" />
        </div>
        <!-- 查询按钮 -->
         <div style="position:absolute;top:30%;left:75%;height:20px;">
        <a style="float: right" class="button_query" onclick="selectNotifyData_Plan();" >
        </a>
        </div>
     </div>
    </div>
  </div>
</form>
<br/>
<div id="query_result" style="width:938px;margin-top: 0px;padding-left:10px; height:739px;">
<div style="height:308px;border-right:1px solid black;"><!-- 分边 -->
  <div style="background-image: url('../../images/table_background.png');height:300px;">
    <table id="query_result_table" border="0px" >
      <thead>
      <tr id="query_result_table_header">
      <label for="select_all" style="display:none"></label>
      <input id="select_all"  type="checkbox" onclick="CheckboxSelect('select_all')" style="display:none"/></th>
        <th scope="col" >序号</th>
        <th scope="col" >维修建议单号</th>
        <th scope="col" >设备编码</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >维修类型</th>
        <th scope="col" >维修工作内容</th>
        <th scope="col" >维修周期</th>
        <th scope="col" >计划修时间</th>
      </tr>
      </thead>
    
     <!--id="query_result_table_body" -->
     	 <tbody id="query_result_table_body" style="border:hidden;">
     	
      </tbody>
    </table>
  </div>
  	<div id="query_result_footer" >
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="backpage_maintence_plan();" />
      		第 <span id="notify_page_nowpage">0</span> 页
      <img src="../../images/previous_triangle.png" onclick="nextpage_maintence_plan();" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
    </div>
    <div id="query_result_footer_total_page">
      		共 <span id="notify_page_totalpage"></span> 页
      		 <span id="alert_count" style="display:none">0</span>
      		 <span id="main_page_nowpage" style="display:none">0</span>
      		  <span id="main_page_totalpage" style="display:none">0</span>
    </div>
    <div id="query_result_footer_total_page" style="float:right;">
      		共 <span id="notify_page_totalCount"></span>条记录
      		
    </div>
  </div>
  <script type="text/javascript">
    TableInitial('query_result_table', 7);
  </script>
    <br/>
  </div>
  </div>
</div>
</div>
</div>

<div id="part3" >
  UCS Holdings CopyRight 2013
</div>
	<script language="JavaScript" type="text/javascript">
 	 HighlightNav('spage_name');
	</script>
</body>
</html>
