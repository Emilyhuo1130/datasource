<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.3.2.min.js"></script>
    <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
  </head>
  <body>
  		<div id="sub_nav">
   		 <ul>
      		<li class="list_item">
        		<a  href="#" id="sc">在线监测</a>
      		</li>
      		<li class="list_separator">
      		 |
     	 </li>
      <li class="list_item">
        <a  href="#" id="health">健康指数</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="#" id="device">设备树</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="#" id="malfunction">故障树</a>
      </li>
    </ul>
   <script type="text/javascript">
    	$(function(){
					$("#sc").click(function(){
					$("#add").load("_sc.jsp");
					return false;
					});	
					$("#health").click(function(){
					$("#add").load("_health_index.jsp");
					return false;
					});	
					$("#device").click(function(){
					$("#add").load("_device_tree.jsp");
					return false;
					});		
					$("#malfunction").click(function(){
					$("#add").load("_malfunction_tree.jsp");
					return false;
					});						
				});	
		</script> 
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none;">
  	<br/>	
  	<span id="add"></span>
  	<%@include file="_sc.jsp" %>	
  	<br/>
  </div>
  </body>
</html>
