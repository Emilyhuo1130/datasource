<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<?xml version="1.0" standalone="no"?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <meta name="author" content="Lu Jun" />
  <meta name="description" content="RCM" />
  <meta name="keywords" content="RCM, Mac" />
  <title>COCC总调度</title>
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/main_page.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/alert.css" />
  <link type="text/css" rel="stylesheet" href="../../assets/stylesheets/event.css" />
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/impact.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/cocc.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/dhtmlxTree/codebase/dhtmlxtree.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/d3/nv.d3.css"/>
  <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/tabview/tabview.css"/>
 
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
     <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
   <script language="JavaScript" src="../../assets/javascripts/cocc.js"></script>
   <script language="JavaScript" src="../../assets/javascripts/jquery.speedometer.js"></script>
   <script language="JavaScript" src="../../assets/javascripts/jquery.jqcanvas-modified.js"></script>
  <%String userName =(String)session.getAttribute("username"); %>
	<script type="text/javascript">
	var operator=null;
 		$(document).ready(function(){
 			//浮动导航菜单
 			operator="<%=userName%>";
			var userName = "<%=userName%>";
		user1_goto(userName);
				//加载数据
		//加载待处理的数据
      	OnEmergePageQuery_emerge1(1);
      	/***显示一级告警，二级告警，三级告警，四级告警的比列**/
      	getNofinishedWaringList();
      	/**显示底下两张图*/
      	GetAlertCountHistoryPerLevel('#char1 svg');
      	GetAlertCountHistoryPerLevel_2('#char2 svg');
 		});
	</script>
	
	<style>

div.lineInfo
{
	background-color: #DDDDDD;
    border: 1px solid #DDDDDD;
    opacity:.8;
    color: #000;
    position: absolute;
    z-index: 10;
    min-width: 200px;
    min-height: 150px;
   	display: none;
    opacity:.8;
    padding: 3px;
    overflow: visible;
    text-align: left;
	left:15%;
	top:85%
}
</style>
	
	
	
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
    		<!-- 	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../cocc/coccmain.jsp" style="color:#cba300;">首页</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;"  >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
 			 --> 
 			 </div> 

</div>
<div id="part2" >
		<div style="margin-top: 20px;">
  		<div id="sub_nav">
   		 <ul>
      		<li class="list_item">
        		<a  href="#" style="color:#0989aa;">COCC总调度</a>
      		</li>
      		
    </ul>
  </div>
  <div style="max-width: 100%;padding:10px; border: 1px solid gray; border-top: none;">
  	<br/>	


<div style="width:938px;height:150px;border-right:0px solid black;">
		<div style="border-bottom: 2px solid gray;width:936;text-align:right" >
	<spn style="color:#f8626e;">Cocc总调度员<%=session.getAttribute("username")%>,您好!欢迎登陆到个人主页!</spn> 
	</div>
<!-- 分边 -->
<div id="query_result" >
<div id="alert_content" style="padding-top: 0px; height: 100px; border-bottom: 1px solid gray;border-right: 1px solid gray;" >
  <div style="background-image: url('../../images/table_background.png'); border-bottom: 1px solid 0;">
    <table style="width:938px" id="query_result_table" border="0" >
      <thead>
      <tr id="query_result_table_header">
        
        <th scope="col" >设备编号</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >线路</th>
         <th scope="col" >部件描述</th>
        <th scope="col" >告警等级</th>
        <th scope="col" >告警类型</th>
        <th scope="col" >告警内容</th>
        <th scope="col" >告警时间</th>
        <th scope="col" >操作</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;">
		<tr  style="height: 26px; border-bottom: 1px solid gray;color: red">
			<td>10.29.08.001</td>
			<td>P01A(转辙机)</td>
			<td>信号系统</td>
			<td>10号线</td>
			<td>P01A(转辙机)</td>
			<td>一级告警</td>
			<td>故障告警</td>
			<td>道岔失表示</td>
			<td>2013-12-12 22:22:22</td>
			<td><a href="javascript:alltoTaskToOCC('10');">确认</a></td>
		</tr>
		
		<tr  style="height: 26px; border-bottom: 1px solid gray;color: #f6b52c">
			<td>10.29.08.001</td>
			<td>SISA信号机</td>
			<td>信号系统</td>
			<td>10号线</td>
			<td>SISA信号机</td>
			<td>二级告警</td>
			<td>故障告警</td>
			<td>列车信号非正常关闭</td>
			<td>2013-12-12 22:22:22</td>
			<td><a href="#">确认</a></td>
		</tr>
		
		<tr  style="height: 26px; border-bottom: 1px solid gray;color: blue ">
			<td>10.29.03.001</td>
			<td>隧道风机TVF_L1</td>
			<td>信号系统</td>
			<td>10号线</td>
			<td>隧道风机TVF_L1</td>
			<td>三级告警</td>
			<td>故障告警</td>
			<td>隧道风机TVF_L1故障</td>
			<td>2013-12-12 22:22:22</td>
			<td><a href="#">确认</a></td>
		</tr>
			
			
      </tbody>
    </table>
  </div>
  </div>
  
  <div id="query_result_footer" style="width: 936px;border-right: 1px solid gray;">
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png" onclick="backpage();" />
     	 第 <span id="coccmain_page_nowpage">1</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="nextpage();"/>
    </div>
    <!--<img src="" />  -->
    <div id="query_result_footer_total_page">
      	共 <span id="coccmain_page_totalpage">1</span> 页
    </div>
    <div style="float: right; padding-right: 20px;">
     	 当前告警总数:<span id="alert_count">1</span>条
    </div>
  </div>
 </div>
</div>
	<!--<input type="text"  id="text1" width="300px"/>
	
	
	--><div onMouseOut="cleartext()" id="showImage2" class='lineInfo' style="width:100px;height:400px; border:0px solid red">
						<img   src="../../images/line.png" height="400" width="150"/>
    </div>
	
	<div id="cebianlan"   onMouseMove="showXY(event)"   style="width:1px;height:700px; border:0px solid red;float:left">
						
    </div>
    <div style="height: 40px"></div>
	<div  style="float:letf;">
		<img  src="../../images/COCCMAIN.jpg"  usemap="#10号线线路条" width="935px" height="720px" />
		<map name="10号线线路条">
			<area shape=rect coords=130,350,550,360 href="occmain.jsp">
			<area shape=rect coords=550,260,560,360 href="occmain.jsp">
		</map>
	
	</div>
	<div id="showDownimage"  >
		<img onclick="showdown();" src="../../images/showDown.jpg" width="936px" height="30px" />
	
	</div>
	<div id="showUpimage" style="display: none">
		<img onclick="showup();"  src="../../images/showUp.jpg" width="936px" height="30px" />
	
	</div>
	<div id="nextimage" style="display: none;background: url('../../images/COCCMAIN-2.png');width: 936px;height: 795px;">
		<!--
		<img   src="../../images/COCCMAIN-2.jpg" width="936px" height="795px" />
		-->
		<div>
		<ul id="menu">
			<li>
				<div style="width:210px;height:180px; border:0px solid red;" >
					<div id="test1">
						90
					</div>
				</div>
			</li>
			<li>
				<div style="width:210px;height:180px; border:0px solid red;" >
					<div id="test2">
						90
					</div>
				</div>
			</li>
			<li>
				<div style="width:210px;height:180px; border:0px solid red;" >
					<div id="test3">
						90
					</div>
				</div>
			</li>
				
			<li>
				<div style="width:210px;height:180px; border:0px solid red;" >
					<div id="test4">
						90
					</div>
				</div>
			</li>
			
		</ul>
		</div>
		
		
		
		<div  style="width:936px;height:705px; border:0px solid gray;text-align: center;">
		<table style="padding-top: 150px;">
			<tr >
			<td>
				<div id="char1" style="width:463px;height:332px; border:0px solid red;" >
					<svg style="width:463px;height:332px;"></svg>
					<label style="color:black">健康指数       健康90-100 良好80-90 危险70-80 0-60 严重</label>
				</div>
			</td>
			<td>
				<div id="char2" style="width:463px;height:332px; border:0px solid red;" >
					<svg style="width:463px;height:332px;"></svg>
					<label style="color:black">10号线运营统计指标</label>
				</div>
			</td>
		
			</tr>
			</table>
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
