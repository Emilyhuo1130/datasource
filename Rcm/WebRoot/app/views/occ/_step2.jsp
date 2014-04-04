<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
			 <style>
  	div.detail_info
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
			left:50%;
			top:130%;
		}
		
		
		div.tasktoallot
			{
				background-color: #F0F0F0;
			    border: 1px solid #DDDDDD;
			    color: #000;
			    position: absolute;
			    z-index: 10;
			    min-width: 140px;
			    min-height: 400px;
			    display: none;
			    opacity:0.8;
			    padding: 3px;
			    overflow: visible;
			    text-align: left;
				left:33%;
				top:400px;
			}
		
		
  </style>
		
		<script type="text/javascript">
  			<%String id = request.getParameter("id");
  			  String stat = request.getParameter("stat");
  			 
  			%>
  		
            </script>
	</head>
	<body>
		<div class="steps_title">
  <img src="../../images/alert_step3_title.png" />
  <a name="link-to"></a>
</div>

<div id="query_result">

  <div style="background-image: url('../../images/table_background.png');">
    <table id="query_result_table" border="0" >
      <caption>
      </caption>
      <thead>
      	<%
      	Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dataString = format.format(date);
       %>  
      <tr id="query_result_table_header">
        <th scope="col" >设备编号</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >告警类型</th>
        <th scope="col" >告警等级</th>
        <th scope="col" >告警内容</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >告警时间</th>
        <th scope="col" >状态</th>
       
        <th scope="col" >来自</th>
        <th scope="col" ></th>
      </tr>
      </thead>

      <tbody id="query_result_table_body"></tbody>
    </table>
  </div>

  <form action="#" method="post">
    <div id="active_analysis" style="margin-top: 30px;">
      <div id="device_tree_img"  style="width: 200px; " >
	      <label for="img_device_tree" class="label_text">设备树</label><br/>
	      <table>
	      	<tbody>
	      		<tr>
	      			<td valign="top">
	      				<div id="img_device_tree">
	      					<ul id="mal_equipmentTree" class="ztree"></ul>
	      	 			 </div>
	      			</td>
	      		</tr>
	      	</tbody>
	      </table>
      </div>
      
      <div id="online_view" style="width:350px;padding-left:80px;padding-bottom:50px;">
        <label for="img_online_view" class="label_text">在途视图</label><br/>
        <table>
	      	<tbody>
	      		<tr>
	      			<td valign="top">
	      				<div>
        	 				<img src=""  id="onLineImg" style="width: 100%; height: 250px; " />
       					 </div>
	      			</td>
	      		</tr>
	      	</tbody>
	      </table>
      </div>
      
      <div id="malfunction_tree" style="width: 200px;float:right; ">
        <label for="img_malfunction_tree" class="label_text">故障树</label><br/>
        	<table>
	      	<tbody>
	      		<tr>
	      			<td valign="top">
	      				<div  id="img_device_tree">
      						<ul id="mal_faultTree" class="ztree"></ul>
      	 				 </div>
	      			</td>
	      		</tr>
	      	</tbody>
	      </table>
      </div>

   


      <div style="text-align:center;width: 100%; ">
     		<div id="sc_trend_analysis_father" style="display:none">
	        	<label for="text_strategy" class="label_text">趋势分析</label><br/>
	        	<div id="sc_trend_analysis" class="image_view"  style="width:100%; ">
	 			 	<svg  id="#nv-line" style="border: 1px dashed green; width: 100%; height: 272px; "></svg>
				</div>
			</div>
			<!-- 开始诊断过程 -->
        	<div id="startTodiagnose" class="image_view"  style="border: 0px dashed red; width: 60%; height: 272px;display: none ">
        		<img src="image1.png"  alt="" id="diagnoseimages"  height="90%">	
        		<br/>
        		<div >
        			<a id="buttonOfdiagnoseimages" style="width: 120px;height: 20px" href="javascript:next_diagnoseimage();">下一步</a>
        		</div>
        	</div>
        	<br/>
        	<div id="buttonofstartTodiagnose" style="display: none">
	     		<a style="width: 120px;height: 20px" href="javascript:startTodiagnose();">开始诊断</a>
	        </div>
	               <div id="success_block_shending" style="background-color:#ccffdd;border:1px blue solid;padding-top:5px;text-align:center;width:300px;height:40px;position:relative;left:350px;top:-400px;z-index:1000;display:none;"><span style="font-size:20px;color:red;font-weight:bold;" >故障诊断操作成功</span></div>
     
	        <!-- 显示本专业诊断结果表 -->
	        <div id="tableOfdiagnoseResult" class="image_view"  style="border: 0px dashed red; width: 100%; height:auto; display: none ;text-align: left">
        		<a name="link_FaultInfo"></a>
        		<label class="label_text" style="font-size: 18px">本专业诊断结果：</label><br/>
        		<div id="reason" style="width: 100%; display: inline-block;">
        			 <label for="text_reason" class="label_text">故障可能原因:</label><br/>
       				 <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="say something." rows="2" cols="1" id="text_reason" name="text_reason" readonly="readonly">
       				 </textarea>
      			</div>
      			
      			<div id="impact" style="width: 100%; display: inline-block;">
       				 <label for="text_impact" class="label_text">故障影响:</label><br/>
       				 <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="say something." rows="2" cols="1" id="text_impact" name="text_impact" readonly="readonly">  
       				 </textarea>
     			 </div>
      
      			<div id="strategy" style="width: 100%; display: inline-block;">
      				  <label for="text_strategy" class="label_text">维修策略:</label><br/>
        			  <textarea style="background-color: transparent; width: 100%;font-size: 14px;" placeholder="say something." rows="2" cols="1" id="text_strategy" name="text_strategy" readonly="readonly">
        			  </textarea>
      			</div>
      			<label class="label_text" style="font-size: 18px"><span id="profession_name">其他</span>-<span id="equipemnt_Description">其他</span>维修履历表：</label><br/>
      			
      			
      			
				
      			<div id="query_result" style="width:100%;margin-top: 0px; height:auto;">
				<div style="height:auto;border-right:1px solid black;"><!-- 分边 -->
				  <div style="background-image: url('../../images/table_background.png');height:auto;">
				    <table id="query_result_table" border="0px" >
				      <thead style="text-align:center;">
				      <tr id="query_result_table_header">
				     
				        <th  scope="col" >日期</th>
				        <th scope="col" >线路</th>
				        <th scope="col" >车站</th>
				        <th scope="col" >告警内容</th>
				        <th scope="col" >维修策略</th>
				        <th scope="col" >维修内容</th>
				      </tr>
				      </thead>
				    
				     <!--id="query_result_table_body" -->
				     	 <tbody id="query_result_table_body_getMaintain_couple_back" style="border:hidden;color: #8f8f8f;text-align: center;">
				     	
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
  				<div style="text-align: center;">
  					<label class="label_text" style="font-size: 18px"><a href="javascript:allocation_task();">进行任务分配</a></label><br/>
  				</div>
  				
        	</div>
        	<div class="image_view" id="showotherresultOfdiagnose_father"  style="border: 0px dashed red; width: 100%; height:auto; display: none ;text-align: left">
  				<label id="showotherresultOfdiagnose" class="label_text" style="font-size: 18px;display: none"><a id="changeText" href="javascript:showotherresultOfdiagnose();">显示其他专业诊断结果</a></label><br/>
  			</div>	
  				
      			
      			
     				 
        	<!-- 显示任务分配 -->
        	<div  id="tableOfTaskToalloc" class='tasktoallot'  style="width:500px;height:200px; border:0px solid red;text-align: center;">
				<a name="showtasktoallot"></a>
				<div id="malfunction_tree" style="width: 250px; height: ">
        			<label for="img_malfunction_tree" class="label_text">任务分配</label><br/>
       				 <input id="citySel" type="text" readonly value="" style="width:300px;" />
        			<table>
				      	<tbody>
				      		<tr>
				      			<td valign="top">
				      				<div  id="img_device_tree">
			      						<ul id="treeDemo" class="ztree" ></ul>
			      	 				 </div>
			      						
				      			</td>
				      		</tr>
				      	</tbody>
	     			 </table>
				 </div>
				     <div>
				     		 	<tr>
				     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:OnApproveByUser3_goto(<%=id%>,'goto');">确定</a></button></td>
				     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:OnApproveByUser3_goto(<%=id%>,'goback');">驳回</a></button></td>
				     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:task_cancel();">取消</a></button></td>
				     		 	</tr>
					 </div>
							 
				    	
					 
        	</div>
        	
        	
  				<!-- 其他专业诊断结果 -->
  				
  				
  			<div id="tableOfotherdiagnoseResult" class="image_view"  style="border: 0px dashed red; width: 100%; height:auto; display: none ;text-align: left">
        		<label class="label_text" style="font-size: 18px">供电专业诊断结果：</label><br/>
        		<div id="reason" style="width: 100%; display: inline-block;">
        			 <label for="text_reason" class="label_text">故障可能原因:</label><br/>
       				 <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="say something." rows="2" cols="1" id="" name="text_reason" readonly="readonly">
开关差动保护跳闸
       				 </textarea>
      			</div>
      			
      			<div id="impact" style="width: 100%; display: inline-block;">
       				 <label for="text_impact" class="label_text">故障影响:</label><br/>
       				 <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="say something." rows="2" cols="1" id="" name="text_impact" readonly="readonly">  
开关柜负责对轨道交通内的设备进行供电，其出现故障后，对于运营会造成一定的影响，为避免造成大的影响，故障出现后应立即维修。
       				 </textarea>
     			 </div>
      
      			<div id="strategy" style="width: 100%; display: inline-block;">
      				  <label for="text_strategy" class="label_text">维修策略:</label><br/>
        			  <textarea style="background-color: transparent; width: 100%;font-size: 14px;" placeholder="say something." rows="2" cols="1" id="" name="text_strategy" readonly="readonly">
1.行程开关调节不当：行程开关是控制电机储能位置的限位开关。当电机储能到位时，将电机源切断。如果限位过高时，机构储能已满。故障现象是:电机空转不停机、储能指示灯不亮。只有打开控制开关才能使电机停止。限位调节过低时，电机储能未满提前停机。由于储能不到位开关不能合闸。调节限位的方法是手动慢慢储能找到正确位置，并且紧固。
        			  </textarea>
      			</div>
      			<label class="label_text" style="font-size: 18px">供电-断路器维修履历表：</label><br/>
      			
      			
      			
				
      			<div id="query_result" style="width:100%;margin-top: 0px; height:230px;">
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
				     	 <tbody id="query_result_table_body_other" style="border:hidden;color: #8f8f8f;text-align: left;">
				     	 <tr>
				     	 	<td width="120px">2013-09-09</td>
				     	 	<td width="80px">10号线</td>
				     	 	<td width="100px">龙柏新村</td>
				     	 	<td width="180px">开关跳闸回路断线</td>
				     	 	<td width="400px">1.控制电源熔丝熔断或空开跳开，TWJ、HWJ继电器同时失磁，控制回路断线信号报出</td>
				     	 	<td width="300px">合闸电磁铁烧坏，更换电磁铁</td>
				     	 </tr>
				     	 <tr>
				     	 	<td width="120px">2013-06-09</td>
				     	 	<td width="80px">10号线</td>
				     	 	<td width="100px">龙柏新村</td>
				     	 	<td width="180px">开关差动保护跳闸</td>
				     	 	<td width="400px">1.行程开关调节不当：行程开关是控制电机储能位置的限位开关。当电机储能到位时，将电机源切断。如果限位过高时，机构储能已满。故障现象是:电机空转不停机、储能指示灯不亮。只有打开控制开关才能使电机停止。限位调节过低时，电机储能未满提前停机。由于储能不到位开关不能合闸。调节限位的方法是手动慢慢储能找到正确位置，并且紧固。</td>
				     	 	<td width="300px">合闸回路辅助开关S1烧坏，更换回路辅助开关S1</td>
				     	 </tr>
				     	
				      </tbody>
				    </table>
				  </div>
				  	<div id="query_result_footer" style="width: 100%" >
				    <div id="query_result_footer_current_page">
				      <img src="../../images/previous_triangle.png"  onclick="" />
				      		第 <span id="notify_page_nowpage">1</span> 页
				      <img src="../../images/previous_triangle.png" onclick=""; style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
				    </div>
				    <div id="query_result_footer_total_page">
				      		共 <span id="notify_page_totalpage">1</span> 页
				    </div>
				    <div id="query_result_footer_total_page" style="float:right;">
				      		共 <span id="notify_page_totalCount">2</span>条记录
				      		
				    </div>
				  </div>
				  
				  </div>
  				</div>
  			
     				 
        	</div>
      </div>
      
    </div>
    
    
    

    </div>
  </form>
</div>
  
	</body>
</html>
