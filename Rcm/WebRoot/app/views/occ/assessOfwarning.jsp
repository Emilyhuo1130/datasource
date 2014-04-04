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
		 <script language="JavaScript" src="../../assets/javascripts/jquery.ztree.core-3.5.js"></script> 
		 <script language="JavaScript" src="../../assets/javascripts/jquery.ztree.excheck-3.5.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
 		 <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
		<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
		<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script> 
 		  <script language="JavaScript" src="../../assets/javascripts/occ-engineer.js"></script>
<%String userName =(String)session.getAttribute("username"); %>
<script type="text/javascript">
	$(document).ready(function(){
		//test();
			//浮动导航菜单
 			var userName = "<%=userName%>";
		user1_goto(userName);
		//showOperationTarget();
		//getMultiBarChartData(10,"#chart1 svg");
		//TendLineChangeEvent(10,"#chart2 svg");
		
		
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
    		<!-- <div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../layouts/application.jsp" >首页</a></div> -->	
    			<div id="alertManage"  class="nav_item_normal" >  <a id="Alert" href="javascript:;"style="color:#cba300;" >告警管理</a></div>
    			<div id="maintenceAnalysis"  class="nav_item_normal" >  <a id="Analy" href="javascript:;" >主动维保分析</a></div>
    			<div id="maintenceManege"  class="nav_item_normal" >  <a id="Manage" href="javascript:;" >主动维保管理</a></div>
 			 </div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="_repair_statistics.jsp" style="color:#0989aa;">告警评估建议</a>
      </li>
      <li class="list_separator">
     
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
    <br/>
   <%--<div style="width:942px;height:379px;border-right:1px solid black;">
    --%>
    <form action="#" method="">
  		<div>
    		<div style="display:inline-block;">
      		<div id="query_criteria" style="padding-top: 14px;" >
       		<label for="report_condition1"></label>
       		 <select id="report_condition1" class="combobox" name="criteria[condition1]">
         		<option value="">信号</option>
         		<option value="信号系统">信号系统</option>
          		<option value="综合监控系统">综合监控系统</option>
        	</select>
        <label for="report_condition2"></label>
	        <select id="report_condition2" class="combobox" name="criteria[condition2]">
	          <option value="">信号系统</option>
	          <option value="轨旁设备">轨旁设备</option>
	          <option value="BAS系统">BAS系统</option>
	          <option value="电力SCADA系统">电力SCADA系统</option>
	        </select>
        <label for="report_condition3"></label>
	        <select id="report_condition3" class="combobox" name="criteria[condition4]">
	          <option value="">地铁线路</option>
	          <option value="10">10号线</option>
	        </select>
        <label for="report_condition4"></label>
        <select id="report_condition4" class="combobox" name="criteria[condition5]">
         <option value="">车站</option>
         <option value="航中路站">航中路站</option>
         <option value="紫藤路">紫藤路</option>
         <option value="龙柏新村">龙柏新村</option>
        </select>
        
       
      
      <div id="time_range">
        <label for="report_start_time">开始时间</label>
        <input type="date" id="report_start_time" name="criteria[start_time]" style="height: 21px;" />
        <div style="width:3%;display: inline-block;"></div>
        <label for="report_end_time">结束时间</label>
        <input type="date" id="report_end_time" name="criteria[end_time]" style="height: 21px;" />
        <a style="float: right" class="button_query" onclick="query_report('故障告警');" >
        </a>
      </div>
      </div>
    </div>
  </div>
</form>
    <div id="query_result" style="padding-left:8px;width:942px;border-right:1px solid black;bomargin-top: 0px;" >
  <div style="background-image: url('../../assets/images/table_background.png'); width: 942px; margin: auto;height:120px;">
    
    <table id="query_result_table" border="0"  >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">
     	 <th scope="col" >操作</th>
     	 <th scope="col" >专业</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >告警等级</th>
        <th scope="col" >告警日期</th>
        <th scope="col" >告警内容</th>
        <th scope="col" >状态</th>
       
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;" >
      	<tr style="height:24px">
      		<td><input type="checkbox"/></td>
      		<td>信号</td>
      		<td>信号系统</td>
      		<td>1</td>
      		<td>富锦路</td>
      		<td>S15A信号机</td>
      		<td style="color:blue">三级告警</td>
      		<td>1013-11-01</td>
      		<td>列车信号非正常关闭</td>
      		<td>维修完成</td>
      		
      	</tr>
      	<tr style="height:24px;background:#DDDDFF">
      		<td><input type="checkbox"/></td>
      		<td>信号</td>
      		<td>信号系统</td>
      		<td>2</td>
      		<td>广兰路</td>
      		<td>S15B信号机</td>
      		<td style="color:blue">三级告警</td>
      		<td>1013-11-08</td>
      		<td>列车信号非正常关闭</td>
      		<td>维修完成</td>
      	</tr>
      	<tr style="height:24px">
      		<td><input type="checkbox" /></td>
      		<td>信号</td>
      		<td>信号系统</td>
      		<td>10</td>
      		<td>航中路</td>
      		<td>P01A转辙机</td>
      		<td style="color:red">一级告警</td>
      		<td>1013-11-16</td>
      		<td>道岔失表示告警</td>
      		<td>维修完成</td>
      	</tr>
      	<tr style="height:24px;background:#DDDDFF">
      		<td><input type="checkbox" /></td>
      		<td>信号</td>
      		<td>信号系统</td>
      		<td>3</td>
      		<td>宝山路</td>
      		<td>P01A转辙机</td>
      		<td style="color:red">一级告警</td>
      		<td>1013-11-23</td>
      		<td>道岔失表示告警</td>
      		<td>维修完成</td>
      	</tr>
      	
      	
      </tbody>
    </table>
   </div>
  
  <div id="query_result_footer" style="width: 940px;margin: auto; ">
    <div id="query_result_footer_current_page">
      <img src="../../images/previous_triangle.png"  onclick="targets_backpage();"/>
     	 第<span id="ope_page_nowpage">1</span> 页
      <img src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" onclick="targets_nextpage();"/>
    </div>
    <div id="query_result_footer_total_page">
     	 共<span id="ope_page_totalpage">1</span> 页
    </div>
     <div style="float: right; padding-right: 20px;">
     	 共<span id="tar_ope_count" style="text-align:right;"></span> 4条记录
    </div>
  </div>
 <!--  </div>-->
  </div><!-- 分边 -->
  
  <div style="height: auto; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;margin-bottom: 25px;">
    <div style="padding-left: 20px; padding-top: 10px;padding-bottom: -5px;">
     	 <label for="condition1" style="color:#505050">本次建议重要成度</label>
     	 <select style="width:100px" >
	        <option >重要</option>
	        <option >次要</option>
	        <option >不重要</option>
     	 </select>
     	 <p/>
     	 <label for="condition1" style="color:#505050">请选择本次建议的专业: </label>
     	 <input id="r1" value="建设" name="profession" type="radio"/><span style="color:#505050" >建设</span>&nbsp;&nbsp;
     	 <input id="r2" value="运营" name="profession" type="radio" /><span style="color:#505050" >运营</span>&nbsp;&nbsp;
     	 <input id="r3" value="维保" name="profession" type="radio" /><span style="color:#505050" >维保</span>&nbsp;&nbsp;
     	 <input id="r4" value="资产" name="profession" type="radio"/><span style="color:#505050" >资产</span>&nbsp;&nbsp;
    </div>
    <!--  
      <div id="strategy" style="width: 100%; display: inline-block;">
        <label for="text_strategy" class="label_text">运营建议：</label><br/>
       
        <textarea style="color:#505050;background-color: transparent; width: 100%;font-size: 14px;" placeholder="say something." rows="6" cols="1" id="text_strategy" name="text_strategy">
        发生道岔故障后，首先，轨道交通控制中心行车调度（以下称之为行调）应优先选择变更进路组织行车。若无变更进路时，则立即任命故障道岔附近车站值班站长为先期现场处置负责人下线路钩锁故障道岔；若现场道岔尖轨密贴，且不管是左位还是右位都能组织行车时，原则上不再断电手摇道岔，而是立即加钩锁器组织行车；若现场道岔四开，列车还未进站时则优先将道岔断电手摇至接车进路要求的位置，列车在车站时则优先将道岔断电手摇至发车进路要求的位置。
        </textarea>
      </div>
      -->
      <div id="strategy" style="width: 100%; display: inline-block;">
        <label for="text_strategy"  class="label_text"><span id="labeloftextarea">维保</span>建议：</label><br/>
        <textarea style="color:#505050;background-color: transparent; width: 100%;font-size: 14px;" placeholder="say something." rows="6" cols="1" id="text_strategy" name="text_strategy">
        维修人员需要进入线路抢修时，做好安全防护后，方可安排维修人员到现场进行抢修。
1）原则上谁组织行车由谁组织抢修。行调组织行车时，由行调组织进行抢修；车站组织行车时，如线路按站间电话联系法或电话闭塞法组织行车时，由车站组织进行抢修； 
2）在行调组织抢修的情况下，车站下线路钩锁道岔的人员携带可与行调直接通话的通讯电台与行调联系，与行调不能直接联系时通过车控室中转； 
3）在车站组织抢修的情况下，车站下线路钩锁道岔人员直接与车控室联系组织行车。  
        
        </textarea>
      </div>
      <div id="strategy" style="text-align:center;width: 100%; display: inline-block;padding-bottom: 20px">
        <label for="text_strategy" class="label_text"></label><br/>
        	<input onclick="showsave();" type="button" value="保存" style="width:80px;"/>
      </div>
   
    </div>

 
</div>
    <br/>
  </div>
</div>
</div>

<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

</body>
<script type="text/javascript">
  HighlightNav('spage_name');
</script>
</html>
