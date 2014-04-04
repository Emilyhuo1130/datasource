<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.3.2.min.js"></script>
</head>
<body style="" >
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="#" id="alert">告警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="#" id="precaution">预警历史统计表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="#" id="health">健康指数分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
       <a href="#" id="kpi">KPI分析表</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="#" id="rcm">主动维保通知单</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="#" id="device">设备履历表</a>
      </li>
    </ul>
    <script type="text/javascript">
		$(function(){
			$("#alert").click(function(){
			$("#add").load("_alert_history_statistics.jsp");
			return false;
			});	
			$("#precaution").click(function(){
			$("#add").load("_precaution_history_statistics.jsp");
			return false;
			});	
			$("#health").click(function(){
			$("#add").load("_health_index_analysis.jsp");
			return false;
			});	
			$("#kpi").click(function(){
			$("#add").load("_kpi_analysis.jsp");
			return false;
			});	
			$("#device").click(function(){
			$("#add").load("_rcm_ticket.jsp");
			return false;
			});	
			$("#rcm").click(function(){
			$("#add").load("_device_info.jsp");
			return false;
			});					
		});	
	</script>
    
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
  <br/>
  <span id="add"></span>
  <%@include file="_alert_history_statistics.jsp" %>
  <br/>
  </div>
</body>
</html>
