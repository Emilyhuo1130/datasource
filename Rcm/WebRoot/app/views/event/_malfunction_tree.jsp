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
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/dhtmlxTree/codebase/dhtmlxtree.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/d3/nv.d3.css" />
 
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/main_page.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/jquery.ztree.core-3.5.js"></script>
   <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/event.js"></script>
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
			getFaultTree();
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
        <a href="../event/_device_tree.jsp">设备树</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item" id='d'>
        <a href="../event/_malfunction_tree.jsp" style="color:#0989aa;">故障树</a>
      </li>
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none;">
  <br />
  		<form action="#" method="post">
  <div>
    <div style="display:inline-block;width: 99%;">
      <div id="query_criteria" style="border-bottom: 1px solid gray; padding-bottom: 15px; width: 944px;background-color: #f9f9fb;">
        <label for="condition1"></label>
        <select id="condition1" class="combobox" name="criteria[condition1]">
          <option>系统</option>
         <option value="信号系统">信号系统</option>
         <option value="综合监控系统">综合监控系统</option>
        </select>

        <label for="condition2"></label>
        <select id="condition2" class="combobox" name="criteria[condition2]">
          <option>资产名称</option>
          <option value="道岔">道岔</option>
	      <option value="信号机">信号机</option>
	      <option value="隧道风机通风系统">隧道风机通风系统</option>
	      <option value="给排水系统">给排水系统</option>
	      <option value="高压开关柜">高压开关柜</option>
	   
        </select>

        <label for="condition3"></label>
        <select id="condition3" class="combobox" name="criteria[condition3]">
          <option>部件</option>
          <option value="转辙机">转辙机</option>
         <option value="信号机">信号机</option>
         <option value="隧道风机">隧道风机</option>
         <option value="水泵">水泵</option>
         <option value="断路器">断路器</option>
       
        </select>

        <a style="display: inline-block;vertical-align: middle;" class="button_query" onclick="findFaultInfoOnAlertQuery();" >
        </a>
      </div>
    </div>
  </div>
</form>
<div id="device_tree_query_result" style="height: 500px;background-color: #f9f9fb;">
  <div id="device_tree" style="height: 500px;">
    <table>
      <tr>
        <td valign="top">
          <div id="treeboxbox_tree" style="width:285px; height:495px;background-color: transparent;">
          		<ul id="mal_faultTree" class="ztree"></ul>
          </div>
        </td>
     
        
      </tr>
    </table>
  </div>
  <div id="device_tree_info" style="height:500px; width: 69%;">
    <table style="height: 100px;">
      <tr>
        <td style="width: 120px; text-align: center;">
          <label for="asset_id" class="label_text">资产名称</label>
        </td>
        <td style="width: 120px;">
          <input style="height: 20px;" class="input_content" type="text" id="equipment_name" name="system_id" value="" />
        </td>
        <td style="width: 120px; text-align: center;">
          <%--<label for="asset_id" class="label_text">设备编号</label>
        --%></td>
        <td style="width: 120px;"><%--
          <input style="height: 20px;" class="input_content" type="text" id="equipment_id" name="system_id" value="" />
        --%></td>
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
    </table>

    <table style="height: 300px;">

      <tr>
        <td style="width: 120px; text-align: center;">
          <label for="malfunction_desc" class="label_text">故障描述</label>
        </td>
        <td style="width:430px;">
          <textarea style="background-color: transparent; width: 100%;" placeholder="say something." rows="6" cols="1" id="malfunction_desc" name="malfunction_desc" readonly="readonly"></textarea>
        </td>
      </tr>
      <tr>
        <td style="width: 120px; text-align: center;">
          <label for="malfunction_desc" class="label_text">故障原因</label>
        </td>
        <td >
          <textarea style="background-color: transparent; width: 100%;" placeholder="say something." rows="6" cols="1" id="malfunction_reason" name="malfunction_reason"></textarea>
        </td>
      </tr>
      <tr>
        <td style="width: 120px; text-align: center;">
          <label for="malfunction_desc" class="label_text">维修策略</label>
        </td>
        <td >
          <textarea style="background-color: transparent; width: 100%;" placeholder="say something." rows="6" cols="1" id="repair_guide" name="repair_guide"></textarea>
        </td>
      </tr>
    </table>

    <div style="text-align:right; max-width: 70%;margin-top: 20px;" ><%--
      <a class="cancel" href="javascript:cancelFaultlInfo();"></a>
      <a class="save" href="javascript:saveFaultlInfo();"></a>

    --%></div>
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
<script type="text/javascript">
  HighlightNav('spage_name');
</script>
</body>
</html>
