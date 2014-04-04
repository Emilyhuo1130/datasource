<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <title>主动维保平台-计划修规程</title>
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/main_page.css" /> 
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/alert.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/impact.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/d3/nv.d3.css" />
  <style type="text/css">
  	div select{
  		margin-left:20px;;
  	}
  </style>
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/emerge1_update.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/event.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert_update.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/dateFormat.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/report.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
<%
	String userName =(String)session.getAttribute("username");
%>
<script type="text/javascript">
  $(document).ready(function(){
	//浮动导航菜单
			var userName = "<%=userName%>";
		user1_goto(userName);
		//---------------------------------------------
 		planMaintence_program();
 	 HighlightNav('spage_name');	
 	 //显示浮动
 	
 	
  });
 //通知单的浮动效果函数
	function showDetail(flag, a) {
								
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
       <span id="name_lable" style="font-size: 14px; color: yellow; "><%=userName%></span>
      </div>
    </div>
  </div>
   <div id="navigation" >
    	<!-- 		<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div> -->
    			<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" >告警管理</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" style="color:#cba300;">主动维保管理</a></div>
 			 </div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
			<!-- 子导航菜单始点 -->
		  <div id="sub_nav">
			    <ul>
			      <li class="list_item">
			        <span style="color:#0989aa;font-weight:bold;position:relative;top:5px;font-size:.95em;">计划修维修规程</span> 
			      </li>
			    </ul>
  </div>
  <div style="max-width: 100%;height:600px; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
  <!-- 子导航菜单终点 -->
		<div class="steps_title" style="height: 20px;">
</div>
<div id="query_result" ><!-- 查询框区域 -->
 		<div style="display:inline-block;">
      		<div id="query_criteria" style="padding-top: 14px;" >
      		 <label for="condition1"></label>
        <select id="condition1" class="combobox" name="criteria[condition1]">
          <option value="">资产名称</option>
          <option value="道岔">道岔</option>
	      <option value="隧道风机通风系统">隧道风机通风系统</option>
	      <option value="信号机">信号机</option>
	      <option value="给排水系统">给排水系统</option>
	      <option value="高压开关柜">高压开关柜</option>
	      <option value="低压开关柜">低压开关柜</option>
	      <option value="400v馈线">400v馈线</option>
        </select>
        
       		<label for="condition2"></label>
       		 <select id="condition2" class="combobox" name="criteria[condition2]">
         		<option value="">所属系统</option>
         		<option value="信号系统">信号系统</option>
          		<option value="综合监控系统">综合监控系统</option>
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
        <select id="condition5" class="combobox" name="criteria[condition5]" style="margin-right: 0px;">
          <option value="">告警等级</option>
          <option value="大修">大修</option>
          <option value="小修">小修</option>
          <option value="中修">中修</option>
        </select>
        
      <div id="time_range">
        <label for="start_time">开始时间</label>
        <input type="date" id="start_time" name="criteria[start_time]" style="height:15px;" />
        <div style="width:3%;display: inline-block;"></div>
        <label for="end_time">结束时间</label>
        <input type="date" id="end_time" name="criteria[end_time]" style="height: 15px;" />
        <a style="float: right" class="button_query" onclick="select_planMaintencePrograme_data();" >
        </a>
      </div>
      </div>
    </div>
<div id="main_alert_content" style="position:relative;padding-top:0px;width:98%;left:1%;height:400px;border-width:1px;" >
  <div style="background-image: url('../../images/table_background.png');">
    <table id="query_result_table" style="position:relative;top:1%;width:940px;">
      <caption>
      </caption>
      <thead >
      <tr id="query_result_table_header">
        <th scope="col" >序号</th>
        <th scope="col" >设备编码</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >修程</th>
        <th scope="col" >维修工作内容</th>
        <th scope="col" >上次时间</th>
        <th scope="col" >设备开始使用日期</th>
        <th scope="col" >维修周期</th>
       
      </tr>
     </thead>
      <tbody id="query_result_table_body" >
   

 
      </tbody>
    </table>
  </div>
  </div>
  </div>
  <div id="query_result_footer" style="position:relative;left:1%;width:98%;">

    <div id="query_result_footer_current_page">
        <img src="../../images/previous_triangle.png" onclick="report_planMaintence_backpage();" />
          	第<span id="equ_resum_nowpage">1</span>页
        <img onclick="report_planMaintence_nextpage();" src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
    </div>
    <div id="query_result_footer_total_page">
          	共<span id="equ_resum_totalpage">1</span> 页
    </div>
     <div id="query_result_footer_total_page" style="float:right;">
          	共<span id="report_pre_page_totalCount"></span> 条记录
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

<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

</body>

</html>
