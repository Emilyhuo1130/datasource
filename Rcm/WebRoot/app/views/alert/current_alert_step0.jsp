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
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/d3/nv.d3.css" />
  <style>
  	div.detail_info_f
		{
			background-color: #fee08f;
		    border: 1px solid #DDDDDD;
		    color: #000;
		    position: absolute;
			opacity: 1;
		    z-index: 100;
		    min-width: 200px;
		    min-height: 150px;
		    display: none;
		    padding: 3px;
		    overflow: visible;
		    text-align: left;
		}
  </style>
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/emerge1_update.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/event.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/divPromptFrame.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert_update.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/dateFormat.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
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
		  OnEmergePageQuery_emerge1({"warningType":"故障告警"});
		  setTimeout(function(){
  			showFloatInfo();
			  
		  },100);
  	
 	 HighlightNav('spage_name');	
 	
 	
  });
  
//通知单的浮动效果函数
	function showDetail(flag,a) {
								
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
<!-- 用户提示框始 -->
<div id="showUsers" style="position:absolute;z-index:10005;"  class='detail_info'></div>
<!-- 用户提示框终 -->
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
    			<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div>
    			<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" style="color:#cba300;">告警管理</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
 	</div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
				<!-- 子导航菜单始点 -->
		  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="current_alert_step0.jsp" style="color:#0989aa;">故障告警</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="current_faultPrecaution_alert_step0.jsp">预警告警</a>
      </li>
       <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a href="current_planMatntence_alert_step0.jsp">计划修预警</a>
      </li>
    </ul>
  </div>
  <!-- 子导航菜单终点 -->
		<div class="steps_title" style="height: 20px;">
  <img src="../../images/alert_step1_title.png" />
</div>
<div id="query_result" >

<div id="main_alert_content" style="padding-top: 0px; height: 350px; border-bottom: 1px solid gray;" >
  <div style="background-image: url('../../images/table_background.png');">
    <table id="query_result_table" border="0" >
      <caption>
      </caption>
      <thead >
      <tr id="query_result_table_header">
        <th scope="col" >序号</th>
        <th scope="col" >告警编号</th>
        <th scope="col" >设备编号</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >告警类型</th>
        <th scope="col" >告警等级</th>
        <th scope="col" width=10%>告警内容</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >告警时间</th>
        <th scope="col" >状态</th>
        <th scope="col" width=3%></th>
        <th scope="col" width=3%></th>
       
      </tr>
     </thead>
      <tbody id="query_result_table_body">
   

 
      </tbody>
    </table>
  </div>
  </div>
  <div id="query_result_footer" style="width:958px;">

    <div id="query_result_footer_current_page">
        <img src="../../images/previous_triangle.png" onclick="backpage('故障告警');" />
          	第<span id="user1_emerge_page_nowpage">1</span>页
        <img onclick="nextpage('故障告警');" src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
    </div>
    <div id="query_result_footer_total_page">
          	共<span id="user1_emerge_page_totalpage">1</span> 页
    </div>
     <div id="query_result_footer_total_page" style="float:right;">
          	共<span id="user1_emerge_page_totalcount"></span> 条记录
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
    <div id="main_alert_content" style="padding-top: 0px; height: 320px; border-bottom: 1px solid gray;" >
      <div id="alert_content_datagrid" style="margin: 0px; width: 100%; background: none;">
        	<div  id="alert_content_table" style="height:320px;" >
         	 <div style="background-image: url('../../images/table_background.png');">
         	 <span id="message_table">
            <table id="query_result_table" border="0" >
              <caption>
              </caption>
              <thead>
              <tr id="query_result_table_header">
                <th scope="col" width="60px">序号</th>
                <th scope="col" >消息内容</th>
                <th scope="col" >时间</th>
              </tr>
              </thead>
              <tbody id="query_result_table_body" >
             
              </tbody>
            </table>
            
         	</span>
          </div>
          </div>
    <div id="query_result_footer" style="width:958px;">
    <div id="query_result_footer_current_page">
      <img class="handpointer" src="../../images/previous_triangle.png"  onclick="alert_backpage();"/>
     	 第<span id="alert_page_nowpage">1</span> 页
      <img class="handpointer" src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="alert_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="alert_page_totalpage">1</span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="alert_page_totalCount" style="text-align:right;"></span> 条记录
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
