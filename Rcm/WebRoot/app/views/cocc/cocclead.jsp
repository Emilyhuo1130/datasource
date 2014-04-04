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
 <script language="JavaScript" src="../../assets/javascripts/cocclead.js"></script>
  <script language="JavaScript" src="../../assets/javascripts/navigation.js"></script>
 <script language="JavaScript" src="../../assets/javascripts/jQueryUI/jquery-ui.js"></script> 
<script language="JavaScript" src="../../assets/javascripts/tabview/tabview.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/d3.v2.js"></script>
<script language="JavaScript" src="../../assets/javascripts/d3/nv.d3.js"></script>
<%String userName =(String)session.getAttribute("username"); %>
<script type="text/javascript">
	$(document).ready(function(){
		//test();
			//浮动导航菜单
 			var userName = "<%=userName%>";
		user1_goto(userName);
		//showOperationTarget();
		getMultiBarChartData(10,"#chart1 svg");
		getchar2imageData("#chart2 svg");
});
</script>
<style type="">
	
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
    			<div id="mainpage" class="nav_item_main"   >  <a id="Main" href="../cocc/cocclead.jsp" style="color:#cba300;">首页</a></div>
    			<div   class="nav_item_normal" > <a  href="../cocclead/report_historyAlert_statistics_count.jsp" >统计报表</a></div>
 			 </div> 
</div>
<div id="part2" >
		<div style="margin-top: 20px;">
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="#" style="color:#0989aa;">综合协调中心领导主页</a>
      </li>
      <li class="list_separator">
     
    </ul>
  </div>
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
    <br/>
    <div style="height: 300px; width: 940px; margin:auto; border: 0px solid gray; margin-top: 8px;margin-bottom: 25px;">
	    <div style="padding-left: 20px; padding-top: 10px;padding-bottom: -5px;">
	     	<canvas id="myCanvas" width="900" height="280" style="border:0px solid #c3c3c3;">
			    <label>一号线</label>
			Your browser does not support the canvas element.
			</canvas>
			<script type="text/javascript">
				/**圆圈图***/
				var c=document.getElementById("myCanvas");
				var cxt=c.getContext("2d");
				cxt.fillStyle="#E0E0FF";
				//cxt.strokeStyle="#EA5145";
				cxt.beginPath();
				cxt.arc(300,140,120,0,Math.PI*2,true);
				cxt.closePath();
				cxt.fill();
				
				var c2=document.getElementById("myCanvas");
				var cxt2=c2.getContext("2d");
				cxt2.font = "normal 13px Arial";
				
				for(var i=1;i<=12;i++){
				if(i%10==0){
					cxt2.fillStyle="#FF0000";
				}else if(i%4==0){
					cxt2.fillStyle="#ffc000";
				}else if(i%3==0){
					cxt2.fillStyle="#558ED5";
				}else if(i%2==0){
					cxt2.fillStyle="#00B050";
				}else{
					cxt2.fillStyle="#00B050";
				}
					cxt2.beginPath();
					//cxt2.strokeStyle="#E0E0FF";
					cxt2.arc(300+120*Math.cos((Math.PI/6)*i),140+120*Math.sin((Math.PI/6)*i),10,0,Math.PI*2,true);
					cxt2.closePath();
					cxt2.fill();
					
					cxt2.fillStyle="black";
					cxt2.fillText("   "+i+"号线",300+120*Math.cos((Math.PI/6)*i),140+120*Math.sin((Math.PI/6)*i));
				}
				
			var g=document.getElementById("myCanvas");
			var image1=g.getContext("2d");
			for(var i=1;i<=4;i++){
			
			if(i==1){
				image1.fillStyle="#FF0000";
			}else if(i==2){
				image1.fillStyle="#ffc000";
			}else if(i==3){
				image1.fillStyle="#558ED5";
			}else if(i==4){
				image1.fillStyle="#00B050";
			
			}
				image1.beginPath();
				image1.arc(600,40+40*i,8,0,Math.PI*2,true);
				image1.closePath();
				image1.fill();
				
			}
			image1.fillStyle="black";
			image1.font="normal 13px Arial";
			image1.fillText("代表运营质量很差",630,44+40);
			image1.fillText("代表运营质量一般",630,44+80);
			image1.fillText("代表运营质量良好",630,44+120);
			image1.fillText("代表运营质量健康",630,44+160);
			
				 
			</script>
			
			
	    </div>
	    
    </div>
    <!-- 统计图表 -->
    <div style="height: 300px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;margin-bottom: 25px;">
    <table>
	      	 	<tr>
	      	 		<td width="500">
	      	 			<div style="width:100%;text-align: center;">
	      	 				<label class="label_Title">地铁运营年健康统计分析</label>
	      	 			</div>
	      	 			<div style="border: 0px solid gray;width: 100%;height: 290px">
	      	 				<div id="chart2" style="width:100%;">
							  <select id="condition1" class="combobox" name="criteria[condition2]" >
						        <option value="">线路（多选）</option>
						        <option value="10" selected="selected">10号线</option>
						      </select>
						      <svg style="border: 0px dashed green; width: 100%; height: 240px; "></svg>
	      				</div>
	      	 		</td>
	      	 		<td width="500">
	      	 			<div style="width:100%;text-align: center;">
	      	 				<label class="label_Title">地铁运营月健康统计分析</label>
	      	 			</div>
						 <div id="chart1" style="border: 0px solid gray;width: 100%;height: 290px">
						  <select id="condition1" class="combobox" name="criteria[condition2]" onchange="MultiBarChangeEvent(this.value,'#chart1 svg');">
					        <option value="">线路（多选）</option>
					        <option value="10" selected="selected">10号线</option>
					      </select>
					      	<svg style="border: 0px dashed green; width: 100%; height: 240px; "></svg>
					    </div>
	      	 		</td>
	      	 	</tr>
	      	 </table>
    </div>
     
    
   <%--<div style="width:942px;height:379px;border-right:1px solid black;">
    --%><div id="query_result" style="padding-left:8px;width:942px;border-right:1px solid black;bomargin-top: 0px;padding-bottom: 10px;" >
  <div style="background-image: url('../../assets/images/table_background.png'); width: 942px; margin: auto;height:300px;padding-bottom: 10px;">
    <table id="query_result_table" border="0"  >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">
        <th scope="col" >线路号</th>
        <th scope="col" >一级故障数</th>
        <th scope="col" >已完成故障检修</th>
        <th scope="col" >未完成检修</th>
        <th scope="col" >平均检修时间</th>
        <th scope="col" >二级故障数</th>
        <th scope="col" >已完成故障检修</th>
        <th scope="col" >未完成检修</th>
        <th scope="col" >平均检修时间</th>
      </tr>
      </thead>
      <tbody id="query_result_table_body" style="border:hidden;" >
      	<tr style="height:23px">
      		<td>1</td>
      		<td>1</td>
      		<td>1</td>
      		<td>0</td>
      		<td>5小时</td>
      		<td>2</td>
      		<td>2</td>
      		<td>0</td>
      		<td>4小时</td>
      	</tr>
      	<tr style="height:23px;background:#DDDDFF">
      		<td>2</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      	</tr>
      	<tr style="height:23px">
      		<td>3</td>
      		<td>1</td>
      		<td>0</td>
      		<td>0</td>
      		<td>3小时</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      	</tr>
      	<tr style="height:23px;background:#DDDDFF">
      		<td>4</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      		<td>1</td>
      		<td>1</td>
      		<td>0</td>
      		<td>3小时</td>
      	</tr>
      	<tr style="height:23px">
      		<td>5</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      		<td>1</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      	</tr>
      	<tr style="height:23px;background:#DDDDFF">
      		<td>6</td>
      		<td>2</td>
      		<td>1</td>
      		<td>1</td>
      		<td>4小时</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      	</tr>
      	<tr style="height:23px">
      		<td>7</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      		<td>3</td>
      		<td>2</td>
      		<td>1</td>
      		<td>6小时</td>
      	</tr>
      	<tr style="height:23px;background:#DDDDFF">
      		<td>8</td>
      		<td>1</td>
      		<td>1</td>
      		<td>0</td>
      		<td>2小时</td>
      		<td>1</td>
      		<td>1</td>
      		<td>0</td>
      		<td>2小时</td>
      	</tr>
      	<tr style="height:23px">
      		<td>9</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      		<td>2</td>
      		<td>1</td>
      		<td>1</td>
      		<td>3小时</td>
      	</tr>
      	<tr style="height:23px;background:#DDDDFF">
      		<td>10</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      		<td>2</td>
      		<td>2</td>
      		<td>0</td>
      		<td>4.5小时</td>
      	</tr>
      	<tr style="height:23px">
      		<td>11</td>
      		<td>1</td>
      		<td>1</td>
      		<td>0</td>
      		<td>6小时</td>
      		<td>1</td>
      		<td>1</td>
      		<td>0</td>
      		<td>5小时</td>
      	</tr>
      	<tr style="height:23px;background:#DDDDFF">
      		<td>13</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0</td>
      		<td>0小时</td>
      		<td>1</td>
      		<td>0</td>
      		<td>1</td>
      		<td>0小时</td>
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
     	 共<span id="tar_ope_count" style="text-align:right;">13</span> 条记录
    </div>
  </div>
 <!--  </div>-->
  </div><!-- 分边 -->
  
  <!--<div style="height: 300px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px;margin-bottom: 25px;">
    <div style="padding-left: 20px; padding-top: 10px;padding-bottom: -5px;">
     	 <label for="condition1"></label>
     	
    </div>
    <div id="chart1" style="margin: auto;width: 900px; ">
      <svg style="height: 250px;"></svg>
    </div>
    </div>

  -->
  <!--<div style="height: 300px; width: 940px; margin:auto; border: 1px solid gray; margin-top: 8px; margin-bottom: 8px;">
    <div style="padding-left: 20px; padding-top: 20px;">
      <label for="condition1"></label>
   <label for="condition2"></label>
      <select id="condition2" class="combobox" name="criteria[condition2]" onchange="TendLineChangeEvent(this.value,'#chart2 svg');">
        <option value="">线路（多选）</option>
        <option value="10" selected="selected">10号线</option>
      
      </select>
    </div>
    <div id="chart2" style="margin: auto;width: 900px;">
      <svg style="height: 250px;"></svg>
    </div>
    </div>
--></div>
    <br/>
  </div>
</div>
  <br/>
</div>

<div id="part3" >
  UCS Holdings CopyRight 2013
</div>

</body>
<script type="text/javascript">
  HighlightNav('spage_name');
</script>
</html>
