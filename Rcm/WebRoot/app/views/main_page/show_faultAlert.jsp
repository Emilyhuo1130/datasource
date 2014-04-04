<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
	<%String userName =(String)session.getAttribute("username"); %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="">
		

div.lineInfo
{
    background-color: #DDDDDD;
    border: 1px solid #DDDDDD;
    color: #000;
}
div.lineInfo
{
    position: absolute;
    z-index: 10;
    min-width: 200px;
    min-height: 150px;
    display: none;
    opacity:.8;
    padding: 3px;
    overflow: visible;
    text-align: left;
	left:50%;
	top:17%
}

div.LineHealth
{
    background-color: #DDDDDD;
    border: 1px solid #DDDDDD;
    opacity:.8;
    color: #000;
}
div.LineHealth
{
    position: absolute;
    z-index: 10;
    min-width: 200px;
    min-height: 150px;
    display: none;
    opacity:.8;
    padding: 3px;
    overflow: visible;
    text-align: left;
	left:50%;
	top:63%
}
.ulli{
	color:#0989aa;
}
	</style>
		<script type="text/javascript">
 		$(document).ready(function(){
 			OnOperativeIndexUpdate();
      		OnMainPageQuery({'warningType':'故障告警'});
        	showimportmentSomething();
        	HighlightNav('page_names');
        	mainpageFlush();
        	showHealthLine();
         	 $("#subNavi ul li a").click(function(){
         		 //alert($(this).html());
         		// alert($(this).parent().siblings("li").find("a").html());//比较
        	 	if($(this).attr("color")!="#0989aa"){
        				$(this).css("color","#0989aa");
        				$(this).parent().siblings("li").find("a").css("color","#000000");
        		}  
        		
        	}); 
 		});
 		function showDetail(flag){
 			if(flag){
 				$("#showImage").slideDown("slow");
 				$("#showLineHealth").slideDown("slow");
 			}else{
 				$("#showImage").slideUp("slow");
 				$("#showLineHealth").slideUp("slow");
 			}
 		}
 		//通知单的浮动效果函数
 		function showDetail_text(flag,a) {
 									
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
	<body>
	<!-- 悬浮图片和图表始 -->
				<div id="showImage" class='lineInfo' style="width:500px;height:300px; border:0px solid red">
						<img src="../../images/train.png" height="300" width="500"/>
        		</div>
        		<div id="showLineHealth" class='LineHealth' style="width:500px;height:200px; border:0px solid red">
						 <svg style="border: 1px dashed green; width:500px; height:200px; "></svg>
        		</div> 
     <!-- 悬浮图片和图表终-->
	
	
	
	
			<div style="float: left;margin-top: 20px; margin-right: 8px; width: 512px; height: 187px; display: inline-block;vertical-align: top;">
  				<div id="operative_index_label" style="background-image: url('../../images/operative_index_title_bg.png');line-height: 30px; font-weight: bold; font-size: 15px; color: #424242;">
    			运营指数
  				</div>
 				 <div id="operative_index_content" style="background-image: url('../../images/operative_index_body_bg.png'); height: 157px; width: 100%; ">
    				<div style=" height: 110px; ">
      					<table style="padding-left: 20px; padding-top: 12px;">
        					<tr style="height: 27px;">
         							 <td><div id="operative_index_1" class="healthy" ></div> </td>
          							 <td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '1'});" href="#"><span style="font-weight: normal;">一号线</span></a></td>
          							 <td class="delimiter"></td>
          							 <td><div id="operative_index_2" class="healthy" ></div> </td>
          							 <td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '2'});" href="#"><span style="font-weight: normal;">二号线</span></a></td>
          							 <td class="delimiter"></td>
          							 <td><div class="healthy" id="operative_index_3" ></div> </td>
          							 <td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '3'});" href="#"><span style="font-weight: normal;">三号线</span></a></td>
         							 <td class="delimiter"></td>
          							 <td><div class="healthy"  id="operative_index_4" ></div> </td>
          							<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '4'});" href="#"><span style="font-weight: normal;">四号线</span></a></td>
          							<td class="delimiter"></td>
          							<td><div class="healthy" id="operative_index_5" ></div> </td>
          							<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '5'});" href="#"><span style="font-weight: normal;">五号线</span></a></td>
        					</tr>
        					<tr style="height: 27px;">
          						<td><div class="healthy" id="operative_index_6"></div> </td>
          						<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '6'});" href="#"><span style="font-weight: normal;">六号线</span></a></td>
          						<td class="delimiter"></td>
          						<td><div class="healthy" id="operative_index_7"></div> </td>
          						<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '7'});" href="#"><span style="font-weight: normal;">七号线</span></a></td>
          						<td class="delimiter"></td>
          						<td><div class="healthy" id="operative_index_8"></div> </td>
          						<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '8'});" href="#"><span style="font-weight: normal;">八号线</span></a></td>
         						<td class="delimiter"></td>
          						<td><div class="healthy" id="operative_index_9"></div> </td>
          						<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '9'});" href="#"><span style="font-weight: normal;">九号线</span></a></td>
          						<td class="delimiter"></td>
          						<td><div class="healthy" id="operative_index_10"></div> </td>
          						<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '10','warningType':$('#alert_prameter').html()});" href="#"><span onmouseover='showDetail(true,10);' onmouseout='showDetail(false,10);' style="font-weight: normal;">十号线</span></a></td>
        					</tr>
        					<tr style="height: 27px;">
         						 <td><div class="healthy" id="operative_index_11"></div> </td>
          						 <td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '11'});" href="#"><span style="font-weight: normal;">十一号线</span></a></td>
         						 <td class="delimiter"></td>
          						<td><div class="healthy" id="operative_index_12"></div> </td>
          						<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '12'});" href="#"><span style="font-weight: normal;">十二号线</span></a></td>
          						<td class="delimiter"></td>
          						<td><div class="healthy" id="operative_index_13"></div> </td>
          						<td style="color: #434343; font-size: 12px;"><a onclick="OnMainPageQuery({'lineNo': '13'});" href="#"><span style="font-weight: normal;">十三号线</span></a></td>
       						 </tr>
     				 </table>
    			</div>
    			<!-- 隐藏的span 放置告警预警联动的入参 -->
    			<span id="alert_prameter" hidden=hidden>故障告警</span>
   				 <div style="height: 30px; ">
      				<table style="margin-left: 20px;">
        				<tr style="height: 20px;">
         					 <td style="width: 50px;">
            					<span style="color: #434343; font-size: 13px; font-weight: bold;">图例</span>
          					</td>
          					<td style="width: 18px;"><div class="healthy" ></div> </td>
          					<td style="width: 50px; color: #434343; font-size: 12px;">健康</td>
          					<td style="width: 18px;"><div class="good" ></div> </td>
          					<td style="width: 50px; color: #434343; font-size: 12px;">良好</td>
          					<td style="width: 18px;"><div class="danger" ></div> </td>
          					<td style="width: 50px; color: #434343; font-size: 12px;">危险</td>
          					<td style="width: 18px;"><div class="severe" ></div> </td>
         					<td style="width: 50px; color: #434343; font-size: 12px;">严重</td>
       					 </tr>
      				</table>
    			</div>
  			</div>
		</div>

		<div style="float: left;margin-top: 20px;margin-left: 0px;width: 440px; height: 187px; display: inline-block;vertical-align: top;">
  			<div id="bigshot" style="background-image: url('../../images/bigshot_title_bg.png');line-height: 30px; font-weight: bold; font-size: 15px; color: #424242;">
    		重要事件
  		</div>
  		<div id="bigshot_content" style="background: no-repeat url('../../images/bigshot_body_bg.png'); padding-top: 12px;width: 100%; height: 157px;">
    		<ul id="nextli">
      			<li style="color: black; margin-left: 17px; padding-bottom: 6px;"><a href="#"><span style="font-weight: normal;">莘庄地铁站南广场部分公交站将临时搬迁</span> </a><img style="vertical-align: top;" src="../../images/new.png" /></li>
      			<li style="color: black; margin-left: 17px; padding-bottom: 6px;"><a href="#"><span style="font-weight: normal;">清明轨交短驳车3月30日起运行</span> </a><img style="vertical-align: top;" src="../../images/new.png" /></li>
      			<li style="color: black; margin-left: 17px; padding-bottom: 6px;"><a href="#"><span style="font-weight: normal;">应对客流增势 轨交3号线北段3站</span> </a></li>
      			<li style="color: black; margin-left: 17px; padding-bottom: 6px;"><a href="#"><span style="font-weight: normal;">双休日出现大客流发送25万人次 清明铁路增开33对</span> </a></li>
      			<li style="color: black; margin-left: 17px; padding-bottom: 6px;"><a href="#"><span style="font-weight: normal;">11号线全力应对F1客流力应对F1客流F1客流力应对F1客流</span> </a></li>
    		</ul>
  		</div>
		</div>
		<div style="display:block; float: left; width: 100%; height: 8px;"></div>
			<div style="display:block; clear: both;">
  				<div id="alert_info_header">
  		<!-- 子菜单始 -->
   	  		<div  id="subNavi" style="position:absolute;z-index:100;left:16%;font-size:.85em;"><ul>
		      <li class="list_item">
		        <a  href="javascript:OnMainPageQuery({'warningType':'故障告警'});" style="color:#0989aa;" >故障告警</a>
		      </li>
		      <li class="list_separator">
		        |
		      </li>
		      <li class="list_item">
		        <a href="javascript:OnMainPageQuery({'warningType':'故障预警'});">预警告警</a>
		      </li>
		       <li class="list_separator">
		        |
		      </li>
		      <li class="list_item">
		        <a href="application_planMaintence.jsp">计划修预警</a>
		      </li>
   	  		</ul></div>
   	  		<!-- 子菜单终-->
    				<div id="sub_nav" style="line-height: 30px; font-weight: bold; font-size: 15px; color: #424242;">
      	
    				</div>
  				</div>
 				 <div id="main_alert_content">
    				<div id="alert_content_datagrid">
     					 <div id="alert_content_query_criteria">
      							 <form action="#" method="post">
         							 <div>
            							<div style="display:inline-block;">
             								 <div id="query_criteria" >
               									  <label for="condition1"></label>
               										  <select id="condition1" hidden="hidden" class="combobox" name="criteria[condition1]">
                 										 <option value="">告警类型</option>
                  										<option value="故障告警" selected="selected">故障告警</option>
                  										<option value="故障预警">故障预警</option>
                  										
                									</select> 
                								<label for="condition2"></label>
                									<select id="condition2" class="combobox" name="criteria[condition2]">
                 										 <option value="">所属系统</option>
                  										 <option value="信号系统">信号系统</option>
                  										 <option value="综合监控系统">综合监控系统</option>
                 										
                									</select>
               									 <label for="condition3"></label>
                									<select id="condition3" class="combobox" name="criteria[condition3]">
                  										<option value="">线路</option>
                  										<option value="10">10号线</option>                		
                									</select>
                								<label for="condition4"></label>
                									<select id="condition4" class="combobox" name="criteria[condition4]">
                  										<option value="">车站</option>
                  										<option value="虹桥火车站">虹桥火车站</option>          
                   										<option value="T2航站楼站">T2航站楼站</option>
                   										<option value="T1航站楼站">T1航站楼站</option>
                   										<option value="上海动物园站">上海动物园站</option>
                   										<option value="龙溪路站">龙溪路站</option>
                   										<option value="水城路站">水城路站</option>
                   										<option value="伊犁站">伊犁站</option>
                   										<option value="宋园路站">宋园路站</option>
                   										<option value="虹桥路站">虹桥路站</option>
                   										<option value="上海交大站">上海交大站</option>
                   										<option value="上海图书馆站">上海图书馆站</option>
                   										<option value="陕西路南站">陕西路南站</option>
                   										<option value="新天地站">新天地站</option>
                   										<option value="老西门站">老西门站</option>
                   										<option value="豫园站">豫园站</option>
                   										<option value="南京东路站">南京东路站</option>
                   										<option value="天潼路站">天潼路站</option>
                   										<option value="四川北路站">四川北路站</option>
                   										<option value="海伦路站">海伦路站</option>
                   										<option value="邮电新村站">邮电新村站</option>
                   										<option value="四平路站">四平路站</option>
                   										<option value="四平路站">c</option>
                   										<option value="国权路站 ">国权路站</option>
                   										<option value="五角场站">五角场站</option>
                   										<option value="江湾体育场站">江湾体育场站</option>
                   										<option value="三门路站">三门路站</option>
                   										<option value="殷高东路站">殷高东路站</option>
                   										<option value="新江湾城站">新江湾城站</option>
                   										<option value="航中路站">航中路站</option>
                   										<option value="紫藤路站">紫藤路站</option>
                   										<option value="龙柏新村站">龙柏新村站</option>
                   										<option value="吴中路停车场站">吴中路停车场站</option> 
                   										<option value="控制中心">控制中心</option>                                                             										                  										                            										
                									</select>
                								<label for="condition5"></label>
                									<select id="condition5" class="combobox" name="criteria[condition5]">
                  										<option value="">资产名称</option>
                  										          <option value="转辙机">转辙机</option>
															      <option value="隧道风机">隧道风机</option>
															      <option value="信号机">信号机</option>
															      <option value="开关">开关</option>
															      <option value=" 隧道"> 隧道</option>
															      <option value="轨道">轨道</option>
															      <option value="触网">触网</option>
															      <option value="桥梁 ">桥梁 </option>
                  										
                									</select>
                								<label for="condition6"></label>
                									<select id="condition6" class="combobox" name="criteria[condition6]">
                 				 						<option value="">告警等级</option>
                  										<option value="1">一级</option>
                  										<option value="2">二级</option>
                  										<option value="3">三级</option>
                  										<option value="4">四级</option>
                									</select>
                								<label for="condition7"></label>
                									<select id="condition7" class="combobox" name="criteria[condition7]" style="margin-right: 0px;">
                  										<option value="">状态</option>
                  										<option value="未处理">未处理</option>
                  										<option value="确认中">确认中</option>
                  										<option value="已处理">已处理</option>
                  										<option value="已取消">已取消</option>
                									</select>
              								</div>
             								 <div id="time_range">
                								<label for="start_time"><span style="font-weight: normal; font-size: 13px;">开始时间</span></label>
                									<input value ="" id="main_page_start_time" type="date" id="start_time" name="criteria[start_time]" style="height: 21px;" />
                									<div style="width:3%;display: inline-block;"></div>
                								<label for="end_time"><span style="font-weight: normal; font-size: 13px;">结束时间</span></label>
                									<input value="" id="main_page_end_time" type="date" id="end_time" name="criteria[end_time]" style="height: 21px;" />
                									<a style="float: right" class="button_query" onclick="selectData();" >
                									</a>
              								</div>
           								 </div>
          							</div>
       							</form>
      						</div>
      						<div id="alert_content_table">
      							<div id="main_alert_content" style="padding-top: 0px; height: 288px; width:941px; border-bottom: 1px solid gray;" >
        							<div style="background-image: url('../../images/table_background.png');">
          								<table id="query_result_table" border="0" >		
            								<thead id="main_page_thead">
            									<tr id="query_result_table_header">
              										<th scope="col" style="width: 20px;" ></th>
              										<th scope="col" style="background-image: none; width: 20px;">
                									<label for="select_all"></label>
                									<input id="select_all"  type="checkbox" onclick="CheckboxSelect('select_all')" /></th>
              										<th scope="col" >序号</th>
              										<th scope="col" >告警编号</th>
              										<th scope="col" >设备编号</th>
              										<th scope="col" >资产名称</th>
              										<th scope="col" >所属系统</th>
              										<th scope="col" >部件描述</th>
             				 						<th scope="col" >线路</th>
              										<th scope="col" >车站</th>
              										<th scope="col" >告警类型</th>
              										<th scope="col" >告警等级</th>
              										<th scope="col" >告警内容</th>
              										<th scope="col" >告警时间</th>
              										<th scope="col" >状态</th>
              										<th scope="col" ></th>
        							<th scope="col" ></th>
           										 </tr>
           										 
            								</thead >
            								<tbody id="query_result_table_body">
       											
           								 	</tbody>
            								
       										<tbody>
           								 	</tbody>
          								</table>
        							</div>
        						</div>
        					</div>   
        					<div id="query_result_footer">
          						<div id="query_result_footer_current_page">
            						<img src="../../images/previous_triangle.png" onclick="backpage();" />
            							第<span id="main_page_nowpage">0</span>页
            						<img onclick="nextpage();" src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
          						</div>
          						<div id="query_result_footer_total_page">
          	 							共<span id="main_page_totalpage"></span> 页
          						</div>
          						<div style="float: right; padding-right: 20px;">
           							 当前告警总数: <span id="alert_count"></span>条
          						</div>
		       						<!-- 确认和取消 -->
        					<div id="alert_content_process">
        						<a style="float: right" class="button_process_alarm" href="../alert/current_process_selector.jsp"> </a>
        						<a style="float: right;" class="button_cancel_alarm" onclick="OnCancelAlarm();"  > </a>
      						</div> 			
      	 				<div id="alert_content_chart">
      						<div id="chart2" style="outline: 0px solid black;margin-top: 0px; margin-left: 200px; margin-right: 130px; width: 220px; height: 220px; display: inline-block;">
       							 <svg style="height: 220px;"></svg>
        							<div style="text-align: center; vertical-align: top; color: black; font-size: 12px;">线路告警数量比例</div>
     						</div>
      						<div id="chart3" style="outline: 0px solid black;margin-top: 0px; width: 220px; height: 220px; display: inline-block;" >
        						<svg style="height: 220px;"></svg>
        						<div style="text-align: center; vertical-align: top; color: black; font-size: 12px;">等级告警数量比例</div>
     				 		</div>
      						<div id="chart1">
        						<svg style="height: 220px;"></svg>
        							<div style="text-align: center; vertical-align: top; color: black; font-size: 12px;">等级告警数量历史</div>
      						</div>
      						<script type="text/javascript">
        					
      						</script>
    					</div>
    				</div>    
    			</div>
    				<script type="text/javascript">
    					GetAlertCountHistoryPerLevel('#chart1 svg');
        				GetAlertCountPerLine('#chart2 svg');
        				GetAlertCountPerLevel('#chart3 svg');
      					mainpageFlush();
    				</script>
  				</div>
  				</div>
			<div class="TrOverlay"></div>
	</body>
</html>
