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
 <script language="JavaScript" src="../../assets/javascripts/targetAnalysis.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/application.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/main_page.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/alert.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/event.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/impact.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
 <script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/detailsoffeedback_ticket.js"></script>

<%String userName =(String)session.getAttribute("username");
	 String id = request.getParameter("id"); 
	 String equipmentid = request.getParameter("equipmentid"); 
%>
<script type="text/javascript">
	$(document).ready(function(){
		//test();
		//浮动导航菜单
 					var userName = "<%=userName%>";
		user1_goto(userName);
		getReqData('<%=equipmentid%>');
		detailsOfback_ticket('<%=id%>');
		
});
</script>
<style type="">
	td.infoTitle
	{
		background: #ececec;
	}
	label.label_Title{
		font-size: 18px;
		color: black;
		font-family:'黑体';
		font-weight: bold;
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
    	<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div>
    	<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;" >告警管理</a></div>
    	<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    	<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" style="color:#cba300;" >主动维保管理</a></div>
 </div> 
</div>
<div id="part2" >
<div style="margin-top: 20px;">
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="#">维修反馈详情</a>
      </li>
      
    </ul>
  </div>
  <div style="max-width: 100%;height:auto; border: 1px solid gray; border-top: none; background-color: #f9f9fb;padding-bottom:20px;">
    <br/>
    
	  <div style="height: auto;padding-bottom:20px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;">
	    <div style="padding-left: 20px; padding-top: 20px;color:#515151 ">
	      <label class="label_Title" >告警信息</label>
	      <div>
	      	<table border="1">
			
			<tr>
				<td width="135" class="infoTitle" >维修建议单号</td>
				<td width="300" id="maintainID"></td>
				<td width="135" class="infoTitle">线路</td>
				<td width="300" id="lineNo"></td>
			</tr>

			<tr>
				<td width="135" class="infoTitle">车站</td>
				<td width="300" id="stationName"></td>
				<td width="135" class="infoTitle">设备编码</td>
				<td width="300" id="equipmentId"></td>

			</tr>
			
			<tr>
				<td width="135" class="infoTitle">资产名称</td>
				<td width="300" id="equipmentName"></td>
				<td width="135" class="infoTitle">所属系统</td>
				<td width="300" id="systemName"></td>

			</tr>
			
			<tr>
				<td width="135" class="infoTitle">部件描述</td>
				<td width="300" id="equipmentDescription"></td>
				<td width="135" class="infoTitle">告警等级</td>
				<td width="300" id="warningLevel"></td>
				
			</tr>
			
			<tr>
				<td width="135" class="infoTitle">告警类型</td>
				<td width="300" id="warningType"></td>
				<td width="135" class="infoTitle">告警时间</td>
				<td width="300" id="warningDate"></td>
				
			</tr>
			
			

		</table>
		<table  border="1">
			<tr>
				<td width="135" class="infoTitle">告警内容</td>
				<td width="743" id="warningInfo"></td>
				
				
			</tr>
			
			<tr>
				<td width="135" class="infoTitle">故障可能原因</td>
				<td width="743" id="faultCause"></td>
			</tr>
			
			<tr>
				<td width="135" class="infoTitle">审定意见</td>
				<td width="743" id="option1"></td>
			</tr>

			
			<tr>
				<td width="135" class="infoTitle">故障影响</td>
				<td width="743" id="influence">
				</td>
			</tr>
			
			<tr>
				<td width="135" class="infoTitle">审定意见</td>
				<td width="743" id="option2"></td>
			</tr>
			<tr>
				<td width="135" class="infoTitle">维修策略</td>
				<td width="743" id="maintenancePolicy">
			</td>
			</tr>
			<tr>
				<td width="135" class="infoTitle">审定意见</td>
				<td width="743" id="option3"></td>
			</tr>
			
		</table>
	      </div>
	
	 	</div>
	    
	  </div>
	  
	  <br/>
    
	  <div style="height: 150px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;">
	    <div style="padding-left: 20px; padding-top: 20px;color:#515151">
	      <label class="label_Title">维修信息</label>
	      <div>
	      	<table  border="1">
			<tr>
				<td width="135" class="infoTitle">维修内容</td>
				<td width="743" id="maintainInfo"></td>
				
				
			</tr>
			
			<tr>
				<td width="135" class="infoTitle">更换部件</td>
				<td width="743" id="changeEquipment"></td>
			</tr>
			
			<tr>
				<td width="135" class="infoTitle">维修结果</td>
				<td width="743" id="maintainResult">良好</td>
			</tr>
			</table>
	      </div>
	
	 	</div>
	    
	  </div>
	  
	  <br/>
    
	  <div style="height: 200px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;">
	    <div style="padding-left: 20px; padding-top: 20px;color:#515151">
	      <label class="label_Title">操作日志</label>
	      	<div>
	      	<table  border="1">
			<tr>
				<td width="135" class="infoTitle">确认人</td>
				<td width="300">胡明</td>
				<td width="135" class="infoTitle">确认时间</td>
				<td width="300">2013-01-01 22:23:26</td>

			</tr>
			<tr>
				<td width="135" class="infoTitle">确认人</td>
				<td width="300">周红</td>
				<td width="135" class="infoTitle">核实时间</td>
				<td width="300">2013-01-01 22:40:12</td>

			</tr>
			<tr>
				<td width="135" class="infoTitle">确认人</td>
				<td width="300">曹雪</td>
				<td width="135" class="infoTitle">审定时间</td>
				<td width="300">2013-01-01 22:45:30</td>
			</tr>
			<tr>
				<td width="135" class="infoTitle">确认人</td>
				<td width="300">周林</td>
				<td width="135" class="infoTitle">发送时间</td>
				<td width="300">2013-01-01 23:03:55</td>

			</tr>
			<tr>
				<td width="135" class="infoTitle">确认人</td>
				<td width="300">王涛</td>
				<td width="135" class="infoTitle">维修时间</td>
				<td width="300">2013-01-02 12:13:32</td>

			</tr>
			</table>
	      </div>
	
	 	</div>
	    
	  </div>
	  
	  <br/>
    
	  <div style="height: auto; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;">
	    <div style="padding-left: 20px; padding-right:20px;padding-bottom:20px; padding-top: 20px;color:#515151">
	      <label class="label_Title">告警分析</label>
	      <div style=" border-top: 1px solid gray;border-bottom :1px solid gray;">
	      	 <label class="label_Title" style="font-size: 16px">在途视图和趋势分析</label>
	      	 <table>
	      	 	<tr>
	      	 		<td width="350">
	      	 			<div style="border: 0px solid gray;width: 100%;height: 200px">
	      	 				<img src="../../assets/images/Snap4.jpg" id="onLineImg" alt="" width="350px" height="200px" />
	      				</div>
	      	 		</td>
	      	 		<td width="650">
	      				 <div id="sc_trend_analysis"   style="width:100%; ">
 			 				<svg  id="#nv-line" style="border: 0px dashed green; width: 100%; height: 200px; "></svg>
						 </div>
	      	 		</td>
	      	 	</tr>
	      	 </table>
	      </div>
			<!-- 设备维修履历表 -->
	      	<label class="label_Title" style="font-size: 16px"><span id="profession_name">道岔</span>维修履历表</label>
	      	<div id="query_result" style="width:100%;margin-top: 0px; height:auto;padding-bottom: 20px">
				<div style="height:auto;border-right:1px solid black;"><!-- 分边 -->
				  <div style="background-image: url('../../images/table_background.png');height:auto;">
				    <table id="query_result_table" border="0px" >
				      <thead style="text-align:center;">
				      <tr id="query_result_table_header">
				     
				        <th scope="col" >日期</th>
				        <th scope="col" >线路</th>
				        <th scope="col" >车站</th>
				        <th scope="col" >告警内容</th>
				        <th scope="col" >维修策略</th>
				        <th scope="col" >维修内容</th>
				      </tr>
				      </thead>
				    
				     <!--id="query_result_table_body" -->
				      <tbody id="query_result_table_body_getMaintain_couple_back" style="border:hidden;">
				     	
				      </tbody>
				    </table>
				  </div>
				  	<div id="query_result_footer" style="width: 100%" >
				    <div id="query_result_footer_current_page">
				      <img src="../../images/previous_triangle.png"  onclick="backpageMaintain_couple_back();" />
				      		第 <span id="getMaintain_couple_back_page_nowpage">0</span> 页
				      <img src="../../images/previous_triangle.png" onclick="nextpageMaintain_couple_back();"; style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
				    </div>
				    <div id="query_result_footer_total_page">
				      		共 <span id="getMaintain_couple_back_page_totalpage"></span> 页
				    </div>
				    <div id="query_result_footer_total_page" style="float:right;">
				      		共 <span id="getMaintain_couple_back_page_totalCount"></span>条记录
				      		
				    </div>
				  </div>
				  
				  </div>
  				</div>
  				<!-- 维修优化建议 -->
  				 <div id="mfmp" style=" border-top: 1px solid gray;border-bottom :1px solid gray;display:none">
  					<label class="label_Title" style="font-size: 16px">维修策略优化建议</label><br/>
        航中路站道岔转折机P01A最近半年，事故发生率比较高，建议调整计划维修周期，把原来小修的周期从12个月调整为6个月，中修的时间从24个月调整为12个月。如果短时间内再次发生故障，可以考虑整体更换
  					
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
