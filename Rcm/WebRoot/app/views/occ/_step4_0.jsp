 <%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<style>
		textarea{
			height:50px;
			resize:none;
		}
		
		.button_1 , .button_2{
			width:100px;
			height:30px;
		}
		div.tasktoallot
			{
				 background-color: #F0F0F0;
			    border: 1px solid #DDDDDD;
			    color: #000;
			    position: absolute;
			    z-index: 10;
			    min-width: 140px;
			    min-height: 200px;
			    display: none;
			    opacity:0.9;
			    padding: 3px;
			    overflow: visible;
			    text-align: left;
				left:40%;
				top:60%
			}
	</style>
	<script type="text/javascript">
  			 <%	String id = request.getParameter("id");
  			 	String equipmentId = request.getParameter("equipmentId");
  			 %>
       		 <%
      			Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dataString = format.format(date);
       		 %> 
       		
  				$(document).ready(function(){
  					setTimeout(function(){
  						console.info("********systemname=**********"+$("#system_main").val().trim());
  						setSystemAndSub();	
  					},50);
  				
  			
  				$("#system_main").bind('change',function(){setSystemAndSub();});
  				
  				showTendChart("#sc_trend_analysis svg");
  			});
  				function setSystemAndSub(){
  				    if($("#system_main").val()=="信号系统"){
  	  					$("#subsystem").html("");//子系统初始赋空
  	  					$("#subsystem").append("<option value='轨旁设备'>轨旁设备</option>");
  	  				}else if($("#system_main").val()=="综合监控系统"){
  	  					$("#subsystem").html("");//子系统初始赋空
  	  					$("#subsystem").append(
  	  		  		            "<option value='BAS系统'>BAS系统</option>"+
  	  		  		            "<option value='电力SCAD系统'>电力SCAD系统</option>");
  	  				}else if($("#system_main").val()=="线路"){
  	  					$("#subsystem").html("");//子系统初始赋空
  	  					$("#subsystem").append(
  	  					"<option value='轨道系统'>轨道系统</option>"+
  			            "<option value='触网系统'>触网系统</option>"+
  	  					"<option value='桥梁系统'>桥梁系统</option>"+
  			            "<option value='隧道系统'>隧道系统</option>"
  			            );
  	  				}else{
  	  						$("#subsystem").html("");//子系统初始赋空
  	  						$("#subsystem").append("<option value=''>--子系统--</option>");
  	  					};
  				}
            </script>
	
	</head>
	<body>
	<div class="steps_title">
  		<img src="../../images/alert_step4_title.png" />
	</div>
	
	<!-- 显示任务分配 -->
        	<div  id="tableOfTaskToalloc" class='tasktoallot' style="width:500px;height:200px; border:0px solid red;text-align: center;">
				<a name="link1"></a>
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
				     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:OnApproveByUser4_goto();">确定</a></button></td>
				     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:#">驳回</a></button></td>
				     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:task_cancel();">取消</a></button></td>
				     		 	</tr>
					 </div>
							 
				    	
					 
        	</div>
	

<div style="padding-top: 30px;">
  <div style=" max-width: 25%; height: 700px;display: inline-block; vertical-align: top;">
    <div id="device_tree" style="display: block;width: 100%">
      <label for="img_device_tree" class="label_text">设备树</label><br/>
      <div style="width: 100%; " id="img_device_tree">
      		<ul id="mal_equipmentTree" class="ztree"></ul>
      	</div>
    </div>
    <div id="online_view" style="display: block; width: 100%;">
      <label for="img_online_view" class="label_text">在途视图</label><br/>
      <img src="" style="width: 100%; height: 200px;" id="onLineImg" />
    </div>
    <div id="malfunction_tree" style="display: block;width: 100%">
      <label for="img_malfunction_tree" class="label_text">故障树</label><br/>
      <div style="width: 100%; " id="img_malfunction_tree">
      		<ul id="mal_faultTree" class="ztree"></ul>
      	</div>
    </div>
  </div>
  
  
  <!-- 供电专业版块 -->
  <div style="position:relative;width:700px;left:28%;top:-700px;">
  <!-- 切换区 1-->
  <div id="first_workspace" style="position:relative;display:none;" class="tog">
  <span style="color:black;font:bold;">供电专业-断路器审定意见:</span>
 <div id="reason" style="width:100%; display: inline-block;">
        <label for="text_reason" class="label_text">故障可能原因</label><br/>
        <textarea  readonly="readonly" style="background-color: transparent; width: 430px; font-size: 14px;" placeholder="say something." rows="5" cols="1" id="text_reason" name="text_reason">开关差动保护跳闸</textarea>
         <textarea readonly="readonly" style="background-color: transparent; width: 250px; font-size: 14px; " placeholder="审定意见" rows="5" cols="1" id="text_opinion1" name="text_opinion1"  >同意</textarea>
      </div>
		
		

      <div id="impact" style="width: 100%; display: inline-block;">
        <label for="text_impact" class="label_text">故障影响</label><br/>
        <textarea  readonly="readonly" style="background-color: transparent; width: 430px; font-size: 14px;" placeholder="say something." rows="5" cols="1" id="text_impact" name="text_impact">开关柜负责对轨道交通内的设备进行供电，其出现故障后，对于运营会造成一定的影响，为避免造成大的影响，故障出现后应立即维修。</textarea>
         <textarea readonly="readonly"  style="background-color: transparent; width: 250px; font-size: 14px;" placeholder="审定意见" rows="5" cols="1" id="text_opinion2" name="text_opinion2" >同意</textarea>
      </div>
		
      <div id="strategy" style="width: 100%; display: inline-block;">
        <label for="text_strategy" class="label_text">维修策略</label><br/>
        <textarea  readonly="readonly" style="background-color: transparent; width: 430px;font-size: 14px;" placeholder="say something." rows="6" cols="1" id="text_strategy" name="text_strategy">1.行程开关调节不当：行程开关是控制电机储能位置的限位开关。当电机储能到位时，将电机源切断。如果限位过高时，机构储能已满。故障现象是:电机空转不停机、储能指示灯不亮。只有打开控制开关才能使电机停止。限位调节过低时，电机储能未满提前停机。由于储能不到位开关不能合闸。调节限位的方法是手动慢慢储能找到正确位置，并且紧固。</textarea>
        <textarea readonly="readonly" style="background-color: transparent; width: 250px; font-size: 14px;" placeholder="审定意见" rows="6" cols="1" id="text_opinion3" name="text_opinion3" >同意</textarea>
      </div>
     
    <div id="success_block_shending" style="background-color:#ccffdd;border:1px blue solid;padding-top:5px;text-align:center;width:300px;height:40px;position:relative;left:350px;top:-400px;z-index:1000;display:none;"><span style="font-size:20px;color:red;font-weight:bold;" >审定成功</span></div>
       <span style="color:black;font:bold;">供电专业-断路器维修履历表:</span>
       <!-- 供电专业断路器维修履历表 -->
         <div id="query_result" style="width:100%;margin-top: 0px; height:auto;">
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
				     	 <tbody id="" style="border:hidden;color: #8f8f8f;text-align: center;">
				     		<tr>
					      		<td>2012-06-08</td>
					      		<td>10</td>
					      		<td>航中路站</td>
					      		<td>开关跳闸</td>
					      		<td>检查总包回路</td>
					      		<td>更换电磁铁</td>
					      	</tr>
				      </tbody>
				    </table>
				  </div>
				  	<div id="query_result_footer" style="width: 100%" >
				    <div id="query_result_footer_current_page">
				      <img src="../../images/previous_triangle.png"  onclick="" />
				      		第 <span id="page_nowpage">1</span> 页
				      <img src="../../images/previous_triangle.png" onclick=""; style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
				    </div>
				    <div id="query_result_footer_total_page">
				      		共 <span id="page_totalpage">1</span> 页
				    </div>
				    <div id="query_result_footer_total_page" style="float:right;">
				      		共 <span id="page_totalCount">1</span>条记录
				      		
				    </div>
				  </div>
				  
				  </div>
  				</div>
       
         <!-- <button class="button_1">专业1</button>
     	 <button class="button_1">专业2</button> -->
     	 
      </div><!-- 切换区1结束 --> 
      <div style="height:30px;"></div>
      
       <!-- 切换区 2-->
       <div class="tog">
    <table style="border-collapse:separate; border-spacing: 10px; text-align: right; width: 100%;">
      <tr >
       <td style="width: 60px;" >
       		<label for="ticket_id" class="label_text">维修建议单号</label>
       </td>
        <td style="width: 90px;" >
        	 <input style="height: 20px;" class="input_content" type="text" id="ticket_id" name="ticket_id" value="" />
        </td>
        
        <td style="width: 60px;">
          <label for="operator" class="label_text">操作员</label>
        </td>
        <td>
          <select style="width: 100%;" class="combobox" id="operator" name="operator">
            <option>黄</option>
            <option>小黄</option>
            <option>小王</option>
          </select>
        </td>
      
        <td style="width:60px;">
          <label for="start_time" class="label_text">开始时间</label>
        </td>
        <td style="width: 90px;">
          <input id="start_time" name="start_time" type="date" style="width: 120px;" value="<%=dataString%>" />
        </td>
          <td style="width: 60px;" >
          
        </td>
        <td>
         
        </td>

       
      </tr>
      <tr>
        <td>
          <label for="asset_name" class="label_text">线路</label>
        </td>
        <td>
          <input style="height: 20px;" class="input_content" type="text" id="asset_lineNo" name="asset_name" value="" />
        </td>
        <td>
          <label for="asset_name" class="label_text">车站</label>
        </td>
        <td>
          <input style="height: 20px;" class="input_content" type="text" id="asset_stationname" name="asset_name" value="" />
        </td>
        <td>
          <label for="system_main" class="label_text">系统</label>
        </td>
        <td width=110>
          <select   style="width:100%;" class="combobox" id="system_main" name="system">
          	<option value="">系统</option>
            <option value="信号系统">信号系统</option>
            <option value="综合监控系统" >综合监控系统</option>
            <option value="线路" >线路</option>
          </select>
        </td>
        <td>
          <label for="subsystem" class="label_text">子系统</label>
        </td>
        <td>
          <select style="width: 100%;" class="combobox" id="subsystem" name="subsystem">
          	<option value="">--子系统--</option>
           <!--  <option value="轨旁设备" >轨旁设备</option>
            <option value="BAS系统">BAS系统</option>
            <option value="电力SCAD系统">电力SCAD系统</option> -->
          </select>
        </td>

      </tr>
      <tr>
        <td>
          <label for="asset_name" class="label_text">资产名称</label>
        </td>
        <td>
          <input style="height: 20px;" class="input_content" type="text" id="asset_equipmentname" name="asset_name" value="" />
        </td>
        <td>
          <label for="asset_id" class="label_text">资产编码</label>
        </td>

        <td>
          <input style="height: 20px;" class="input_content" type="text" id="component_code" name="component_name" value="" />
        </td>
        <td>
          <label for="malfunction_level" class="label_text">告警等级</label>
        </td>
        <td>
          <select style="width: 100%;" class="combobox" id="malfunction_level" name="malfunction_level">
            <option value="1">一级</option>
            <option value="2">二级</option>
            <option value="3">三级</option>
            <option value="4">四级</option>
          </select>
        </td>
        <td>
          <label for="health_index" class="label_text">健康指数</label>
        </td>
        <td>
          <select style="width: 100%;" class="combobox" id="health_index" name="health_index">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
          </select>
        </td>
      </tr>
      <tr></tr>
      <tr>
        <td>
          <label for="malfunction_desc" class="label_text">故障描述</label>
        </td>
        <td colspan="7">
           <textarea style="height: 60px;background-color: transparent; width:100%;" placeholder="say something." rows="8" cols="1" id="malfunction_desc" name="malfunction_desc"></textarea>
          
        </td>
         
         
       
      </tr>
      <tr>
        <td>
          <label for="malfunction_cause" class="label_text">故障原因</label>
        </td>
        <td colspan="7">
          <textarea  style="height: 60px;background-color: transparent; width: 65%;" placeholder="say something." rows="8" cols="1" id="malfunction_cause_director" name="malfunction_cause"></textarea>
          <textarea style="height: 60px;background-color: transparent; width:33.5%;" placeholder="审定意见" rows="8" cols="1" id="opinion1" name="opinion1"></textarea>
        </td>
      </tr>
      <tr>
        <td>
          <label for="text_impact" class="label_text">故障影响</label><br/>
        </td>
        <td colspan="7">
          <textarea   style="height: 60px;background-color: transparent; width: 65%;" placeholder="say something." rows="8" cols="1" id="text_impact_directo" name="text_impact"> </textarea> 
          <textarea style="height: 60px;background-color: transparent; width:33.5%;" placeholder="审定意见" rows="8" cols="1" id="opinion2" name="opinion2"></textarea>
         
        </td>
      </tr>
      <tr>
        <td>
          <label for="strategy" class="label_text">维修优化</label>
        </td>
        <td colspan="7">
          <textarea  style="height:60px;background-color: transparent; width: 65%;" placeholder="say something." rows="8" cols="1" id="maintenancePolicy" name="strategy"></textarea>
           <textarea style="height: 60px;background-color: transparent; width:33.5%;" placeholder="审定意见" rows="8" cols="1" id="opinion3" name="opinion3"></textarea>
		
        </td>
      </tr>
    </table>
    <!-- 审定和终止按钮 -->
    <div style="text-align:right; max-width: 100%;" >
     
      <a class="button_audit"  href="javascript:showPersonToalltoTask(<%=id%>,'<%=equipmentId2%>');"></a>
    </div>
    <div id="success_block_send" style="background-color:#ccffdd;border:1px blue solid;padding-top:5px;text-align:center;width:300px;height:40px;position:relative;left:120px;top:-300px;z-index:1000;display:none;"><span style="font-size:20px;color:red;font-weight:bold;" >审定成功</span></div>
    <!-- 道岔历史维修履历表 -->
 	 <span style="color:black;font:bold;"><span id="profession_name">道岔</span>历史维修履历表</span>
 		  <div id="query_result" style="width:100%;margin-top: 0px; height:auto;">
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
				     	 <tbody id="query_result_table_body_getMaintain_couple_back" style="border:hidden;color: #8f8f8f;font-size:.7em;text-align:center;">
				     		<tr>
					      		<td width="100px">2012-06-08</td>
					      		<td  width="50px">10号线</td>
					      		<td  width="80px">航中路站</td>
					      		<td  width="100px">道岔转换不到底</td>
					      		<td>检查工务轨距轨道水平差有无变，电务设备检测杆各部螺丝是否松动</td>
					      		<td  width="100px">修复检测杆</td>
					      	</tr>
					      	
				      </tbody>
				    </table>
				  </div>
				  	<div id="query_result_footer" style="width: 100%" >
				    <div id="query_result_footer_current_page">
				      <img src="../../images/previous_triangle.png"  onclick="backpageMaintain_couple_back();" />
				      		第 <span id="getMaintain_couple_back_page_nowpage">1</span> 页
				      <img src="../../images/previous_triangle.png" onclick="nextpageMaintain_couple_back();"; style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
				    </div>
				    <div id="query_result_footer_total_page">
				      		共 <span id="getMaintain_couple_back_page_totalpage">1</span> 页
				    </div>
				    <div id="query_result_footer_total_page" style="float:right;">
				      		共 <span id="getMaintain_couple_back_page_totalCount">1</span>条记录
				      		
				    </div>
				  </div>
				  
				  </div>
  				</div>
    <div style="height:20px;"></div>
   <!-- 切换区2结束 --> 
    <!-- 切换按钮 -->
    <!--   <button class="button_2">专业1</button>
      <button class="button_2">专业2</button> -->
      
  </div>
       <!-- 切换区2结束 --> 
  
 </div>
 <div id="sc_trend_analysis" class="image_view"  style="width:700px; position:relative;left:250px;top:-700px;display:none">
 		 <span style="color: black;font-size: 14px;">趋势分析</span><br/>
  	<svg  id="#nv-line" style="border: 1px dashed green; width: 100%; height: 200px; "></svg>
  </div>
</div>
	</body>
</html>
