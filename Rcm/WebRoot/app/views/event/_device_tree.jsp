<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <title>主动维保平台</title>
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/main_page.css" /> 
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/alert.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/zTreeStyle.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/impact.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/dhtmlxTree/codebase/dhtmlxtree.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/d3/nv.d3.css" />
  
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/main_page.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jquery.ztree.core-3.5.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
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
			getEquipmentTree();
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
    <!-- <div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div>  -->	
    	<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" >告警管理</a></div>
    	<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" style="color:#cba300;">主动维保分析</a></div>
    	<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
 	</div> 
</div>
<div id="part2" >
	
	<div style="margin-top: 20px;">
  <div id="sub_nav">
    <ul>
      <li class="list_item" id='a'>
        <a  href="../event/_sc.jsp">在线监测</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item" id='b'>
        <a  href="../event/_health_index.jsp">健康指数</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item" id='c'>
        <a href="../event/_device_tree.jsp" style="color:#0989aa;">设备树</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item" id='d'>
        <a href="../event/_malfunction_tree.jsp">故障树</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none;">
  	<br />
  		<form action="#" method="post">
  <div>
    <div style="display:inline-block; width: 957px; background-color: #f9f9fb;">
      <div id="query_criteria" style="border-bottom: 1px solid gray; padding-bottom: 15px; width: 945px;">
      	<label for="condition2"></label>
        <select id="condition2" class="combobox" name="criteria[condition1]">
          <option>车站</option>
         <option value="航中路站">航中路站</option>
         <option value="紫藤路">紫藤路</option>
         <option value="龙柏新村">龙柏新村</option>
        
        </select>
        <label for="condition1"></label>
        <select id="condition1" class="combobox" name="criteria[condition1]">
         <option value="">系统</option>
         <option value="信号系统">信号系统</option>
         <option value="综合监控系统">综合监控系统</option>
        
        </select>
        <label for="condition5"></label>
        <select id="condition5" class="combobox" name="criteria[condition5]">
          <option>资产名称</option>
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
          <option>部件</option>
          <option value="P01A(转辙机)">P01A(转辙机)</option>
         <option value="S15A信号机">S15A信号机</option>
         <option value="隧道风机TVF_I1">隧道风机TVF_I1</option>
         <option value="隧道风机TVF_I2">隧道风机TVF_I2</option>
         <option value="隧道风机TVF_II1">隧道风机TVF_II1</option>
         <option value="隧道风机TVF_II2">隧道风机TVF_II2</option>
         <option value="区间水泵QB">区间水泵QB</option>
         <option value="101进线开关">101进线开关</option>
         <option value="102进线开关">102进线开关</option>
         <option value="111动力开关">111动力开关</option>
         <option value="112动力开关">112动力开关</option>
         <option value="10A母联开关">10A母联开关</option>
         <option value="401开关断路器">401开关断路器</option>
         <option value="402开关断路器">402开关断路器</option>
         <option value="40A开关断路器">40A开关断路器</option>
         <option value="400V馈线3废水泵(主）">400V馈线3废水泵(主）</option>
         <option value="400V馈线3废水泵（备）">400V馈线3废水泵（备）</option>
         <option value="400V馈线3射流风机（主）">400V馈线3射流风机（主）</option>
         <option value="400V馈线3射流风机（备）">400V馈线3射流风机（备）</option>
       
        </select>
        <a style="display: inline-block;vertical-align: middle;" class="button_query" onclick="OnAlertQuery();" >
        </a>
      </div>
    </div>
  </div>
  </form>
<div id="device_tree_query_result" style="height: 500px; background-color: #f9f9fb;">
  <div id="device_tree" style="height: 500px;">
    <table>
      <tr>
        <td valign="top">
          <div id="treeboxbox_tree" style="width:285px; height:495px;background-color: transparent;">
          	<ul id="treeDemo" class="ztree"></ul>
          </div>
        </td>
        
      </tr>
    </table>
  </div>
  <div id="device_tree_info" style="height:500px; width: 69%;">
      <table style="height: 200px; margin-top: 30px;">
      <tr>
        <td style="width: 80px; text-align: center;">
          <label for="asset_id" class="label_text">资产名称</label>
        </td>
        <td style="width: 120px;">
          <input style="height: 20px;" class="input_content" type="text" id="equipment_name" name="system_id" value="" />
        </td>
        <td style="width: 80px; text-align: center;">
          <label for="asset_id" class="label_text">设备编号</label>
        </td>
        <td style="width: 120px;">
          <input style="height: 20px;" class="input_content" type="text" id="equipment_id" name="system_id" value="" />
        </td>
      </tr>
      <tr>
        <td style="width: 120px; text-align: center;">
          <label for="asset_id" class="label_text">所属系统</label>
        </td>
        <td >
          <input style="height: 20px;" class="input_content" type="text" id="system_name" name="system_id" value="" />
        </td>
        <td style="width: 120px; text-align: center;">
          <label for="asset_id" class="label_text">部件描述</label>
        </td>
        <td >
          <input style="height: 20px;" class="input_content" type="text" id="equipmentDescription" name="system_id" value="" />
        </td>
      </tr>
      <tr>
        <td style="width: 120px; text-align: center;">
          <label for="asset_id" class="label_text">级别</label>
        </td>
        <td style="width: 150px;" colspan="3">
          <input style="height: 20px;" class="input_content" type="text" id="level" name="system_id" value="" />
        </td>
      </tr>
      <tr>
        <td style="width: 120px; text-align: center;">
          <label for="asset_id" class="label_text">上一级设备</label>
        </td>
        <td >
          <input style="height: 20px;" class="input_content" type="text" id="upEquipment" name="system_id" value="" />
        </td>
        <td style="width: 120px; text-align: center;">
        </td>
        <td >
        </td>
      </tr>
    </table>
  </div>
</div>
  	<br />
  </div>
</div>
<div id="a"></div>
</div>

<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

</body>
<script type="text/javascript">
  HighlightNav('spage_name');
</script>
</html>
