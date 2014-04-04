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
 <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/event.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/emerge.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
 <script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
<%String userName =(String)session.getAttribute("username"); %>
<script type="text/javascript">
  $(document).ready(function(){
  	OnEmergePageQuery(); 
 	 HighlightNav('spage_name');	
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
    <div class="nav_item_main" ><a id="Main" href="../layouts/application.jsp">首页</a></div>
    <div class="nav_item_normal" ><a id="Alert" href="../alert/process_selector.jsp" style="color:#cba300;">告警管理</a></div>
    <div class="nav_item_normal" ><a id="Event" href="../event/_sc.jsp" >实件分析</a></div>
    <div class="nav_item_normal" ><a id="Impact" href="../impact/show.jsp" >影响分析</a></div>
    <div class="nav_item_normal" ><a id="Target" href="../target/_repair_statistics.jsp" >指标分析</a></div>
    <div class="nav_item_normal" ><a id="Strategy" href="../strategy/show.jsp" >策略制定</a></div>
    <div class="nav_item_normal" ><a id="Ticket" href="../ticket/_notification_ticket.jsp" >通知单</a></div>
    <div class="nav_item_normal" ><a id="Report" href="../report/_alert_history_statistics.jsp" >报表管理</a></div>
      
  </div>
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
		<div class="steps_title" style="height: 20px;">
  <img src="../../images/alert_step1_title.png" />
</div>

<div id="query_result" >
<div id="alert_content" style="padding-top: 0px; height: 608px; border-bottom: 1px solid gray;" >
  <div style="background-image: url('../../images/table_background.png');">
    <table id="query_result_table" border="0" >
      <caption>
      </caption>
      <thead >
      <tr id="query_result_table_header"><!--
        <th scope="col" style="width: 20px;" ></th>
        <th scope="col" style="background-image: none; width: 20px;"></th>
        --><th scope="col" >序号</th>
        <th scope="col" >告警编号</th>
        <th scope="col" >资产编号</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >告警类型</th>
        <th scope="col" >告警等级</th>
        <th scope="col" >告警内容</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >告警时间</th>
        <th scope="col" >状态</th>
        <th scope="col" ></th>
        <th scope="col" ></th>
       
      </tr>
     </thead>
      <tbody id="query_result_table_body">
   

 
      </tbody>
    </table>
  </div>
  </div>
  <div id="query_result_footer" style="width: 958px;">

    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png" />
     		 第 1 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
    </div>
    <div id="query_result_footer_total_page">
      		共 10 页
    </div>
  </div>
  <script type="text/javascript">
    OnAlertQuery();
  </script>
  	<div style="display:block; clear: both;margin-top: 8px;">
    <div id="alert_info_header">
      <div id="sub_nav" style="line-height: 30px; font-weight: bold; font-size: 15px; color: #424242;">
        消息提示
      </div>
    </div>
    <div id="alert_content" style="padding-top: 0px; height: 208px; border-bottom: 1px solid gray;" >
      <div id="alert_content_datagrid" style="margin: 0px; width: 100%; background: none;">
        	<div  id="alert_content_table">
         	 <div style="background-image: url('../../images/table_background.png');">
            <table id="query_result_table" border="0" style="height: 208px;">
              <caption>
              </caption>
              <thead>
              <tr id="query_result_table_header">
                <th scope="col" >序号</th>
                <th scope="col" >消息内容</th>
                <th scope="col" >时间</th>
              </tr>
              </thead>
              <tbody id="query_result_table_body">
              <tr>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr style="color: #e4460d;">
                <td><span id="info1" > </span></td>
                <td><span id="info2" > </span></td>
                <td><span id="info3" > </span></td>
              </tr>
              </tbody>
            </table>
         
          </div>
        </div>
      </div>
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
