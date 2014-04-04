<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <meta name="author" content="Lu Jun" />
  <meta name="description" content="RCM" />
  <meta name="keywords" content="RCM, Mac" />
  <title>主动维保平台</title>
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/main_page.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/alert.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/impact.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/dhtmlxTree/codebase/dhtmlxtree.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/d3/nv.d3.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/tabview/tabview.css"/>
 
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
   <script language="JavaScript" src="../../assets/javascripts/analysis.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
    <script language="JavaScript" src="../../assets/javascripts/alert_update.js"></script>
     <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
  <%String userName =(String)session.getAttribute("username"); %>
	<script type="text/javascript">
 		$(document).ready(function(){
 			//浮动导航菜单
				var userName = "<%=userName%>";
		user1_goto(userName);
		//changeLabel(userName);
				//加载数据
      		OnAnalysisQuery();
      		
 			showTendChart("#sc_trend_analysis svg");
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
    			<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" >告警管理</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" style="color:#cba300;" >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
  </div> 

</div>
<div id="part2" >
		<div style="margin-top: 20px;">
  		<div id="sub_nav">
   		 <ul>
      		<li class="list_item">
        		<a  href="../event/_sc.jsp" style="color:#0989aa;">在线监测</a>
      		</li>
      		<li class="list_separator">
      		 |
     	 </li>
      <li class="list_item">
        <a  href="../event/_health_index.jsp">健康指数</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="../event/_device_tree.jsp">设备树</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="../event/_malfunction_tree.jsp">故障树</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%;padding:10px; border: 1px solid gray; border-top: none;">
  	<br/>	
	<form action="#" method="">
  	<div>
    <div style="display:inline-block;">
      <div id="query_criteria">

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

        <select id="condition2" class="combobox" name="criteria[condition2]">
          <option value="">所属系统</option>
          <option value="信号系统">信号系统</option>
          <option value="综合监控系统">综合监控系统</option>
        
        </select>

        <select id="condition3" class="combobox" name="criteria[condition3]">
          <option value="">线路</option>
          <option value="10">十号线</option>
         
        </select>

        <select id="condition4" class="combobox" name="criteria[condition4]">
         <option value="">车站</option>
         <option value="航中路站">航中路站</option>
         <option value="紫藤路">紫藤路</option>
         <option value="龙柏新村">龙柏新村</option>
        </select>

        <select id="condition5" class="combobox" style="width: 80px;" name="criteria[condition5]">
          <option value="">状态</option>
          <option value="1">一级</option>
          <option value="2">二级</option>
          <option value="3">三级</option>
         <option value="4">四级</option>
        </select>
        <div style="padding:10px;">
        <label for="condition6"><span style="color:black; font-size: 14px;">开始时间</span></label>
        <input type="date" id="condition6" name="criteria[start_time]" style="height: 21px; width: 100px;" />
        <label for="condition7"><span style="color:black; font-size: 14px;">结束时间</span></label>
        <input type="date" id="condition7" name="criteria[end_time]" style="height: 21px;width: 100px;" />
        </div>
        <div style="position:absolute;top:35%;left:75%;">
        <a style="display: inline-block; vertical-align: middle;" class="button_query" onclick="queryRecord();" >
        </a>
        </div>
      </div>
    </div>
  </div>
</form>

<div style="width:938px;height:379px;border-right:1px solid black;"><!-- 分边 -->
<div id="query_result">
<div id="alert_content" style="padding-top: 0px; height: 350px; border-bottom: 1px solid gray;" >
  <div style="background-image: url('../../images/table_background.png'); border-bottom: 1px solid 0;">
    <table style="width:938px" id="query_result_table" border="0" >
      <thead>
      <tr id="query_result_table_header">
        <th scope="col" ></th>
        <th scope="col" >序号</th>
        <th scope="col" >设备编号</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >部件描述</th>
        <th scope="col" >线路</th>
        <th scope="col" >状态</th>
        <th scope="col" >监测点信息</th>
        <th scope="col" >时间</th>
        <th scope="col" >在途视图</th>
        <th scope="col" >趋势分析</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;">

      </tbody>
    </table>
  </div>
  </div>
  
  <div id="query_result_footer" style="width: 936px;">
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png" onclick="backpage();" />
     	 第 <span id="analysis_page_nowpage">1</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="nextpage();"/>
    </div>
    <!--<img src="" />  -->
    <div id="query_result_footer_total_page">
      	共 <span id="analysis_page_totalpage">1</span> 页
    </div>
    <div style="float: right; padding-right: 20px;">
     	 当前告警总数:<span id="alert_count">1</span>条
    </div>
  </div>
 </div>
</div>

<div id="sc_online_view" class="image_view" width='433px'>
  <span style="color: black; font-size: 14px;">故障视图</span><br/>
  <img id="onLineImg" src="../../assets/images/Snap1.jpg" style="border: 1px dashed green; width: 100%; height: 90%;"/>
</div>
<a name="tend"></a>
<div id="sc_trend_analysis" class="image_view"  style="width:433px; float:right;">
  <span style="color: black;font-size: 14px;">趋势分析</span><br/>
  <svg  id="#nv-line" style="border: 1px dashed green; width: 100%; height: 272px; "></svg>
  
	
</div>

</div>	
</div>
</div>
<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

<script type="text/javascript">
  HighlightNav('page_name');
    
</script>
</body>
</html>
