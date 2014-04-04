<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
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
		
		}
		
		
  </style>
			<script type="text/javascript">
  		
  			$(document).ready(function(){
  			
  				getResponse(<%=id%>);
  				
  				
  			});
            </script>
	</head>
	<body>
	<!-- 用户提示框始 -->
<div id="showUsers" class='detail_info'></div>
<!-- 用户提示框终 -->
		<div class="steps_title">
  <img src="../../images/alert_step2_title.png" />
</div>
<div id="query_result">
  <div style="background-image: url('../../images/table_background.png');">
    <table id="query_result_table" border="0" >
      <caption>
      </caption>
      <thead>
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
		 <%
      	Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dataString = format.format(date);
       %>
      <tbody id="query_result_table_body">
   
      </tbody>
    </table>
  </div>

 
    <div id="active_analysis" style="margin-top: 30px;">
      <div id="device_tree_img"  style="width: 250px;" >
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
      
      <div id="online_view" style="width: 250px; ">
        <label for="img_online_view" class="label_text">在途视图</label><br/>
        <table>
	      	<tbody>
	      		<tr>
	      			<td valign="top">
	      				<div>
        	 				<img src="" id="onLineImg" style="width: 100%; height: 250px; " />
       					 </div>
	      			</td>
	      		</tr>
	      	</tbody>
	      </table>
      </div>
      
      <div id="malfunction_tree" style="width: 250px; height: ">
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
      </p>
      <div id="divOfslideUpactive" style="text-align: center;">
	      <input type="button" style="width: 100px;" onclick="slideUpactive_analysis();" value="进行任务分配"/>
      </div>
      			
    
     	
     	
    
      
    </div>
    <!-- 任务分配 -->
     <div id="tasktoPerson" class="alltoTask" style="text-align: center;display: none">	  
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
     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:committhisTask(<%=id%>);">确定</a></button></td>
     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:OnAbortProcess(<%=id%>,<%=stat%>);">驳回</a></button></td>
     		 		<td><button  style="width: 80px;height: 20px"><a href="javascript:cancelthisTask();">取消</a></button></td>
     		 	</tr>
			 </div>
    </div>
    <div id="success_block" style="background-color:#ccffdd;border:1px blue solid;padding-top:5px;text-align:center;width:300px;height:40px;position:relative;left:350px;top:-400px;z-index:1000;display:none;"><span style="font-size:20px;color:red;font-weight:bold;" >核实成功</span></div>
  
    
    
   
    

  </div>
	</body>
</html>

