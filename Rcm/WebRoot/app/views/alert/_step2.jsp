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
        <th scope="col" >健康状况</th>
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

    <script type="text/javascript">
        initLineChart('#line_chart svg');
      </script>

      <div id="reason" style="width: 70%; display: inline-block;">
        <label for="text_reason" class="label_text">故障可能原因</label><br/>
        <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="say something." rows="5" cols="1" id="text_reason" name="text_reason" readonly="readonly">
        </textarea>
      </div>
      <div id="opinion1" style="width: 28%; display: inline-block;">
        <label for="text_opinion1" class="label_text">审定意见</label><br/>
        <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="" rows="5" cols="1" id="text_opinion1" name="text_opinion1" ></textarea>
      </div>

      <div id="impact" style="width: 70%; display: inline-block;">
        <label for="text_impact" class="label_text">故障影响</label><br/>
        <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="say something." rows="5" cols="1" id="text_impact" name="text_impact" readonly="readonly">  
        </textarea>
      </div>
      <div id="opinion2" style="width: 28%; display: inline-block;">
        <label for="text_opinion2" class="label_text">审定意见</label><br/>
        <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="" rows="5" cols="1" id="text_opinion2" name="text_opinion2" ></textarea>
      </div>

      <div id="strategy" style="width: 70%; display: inline-block;">
        <label for="text_strategy" class="label_text">维修策略</label><br/>
        <textarea style="background-color: transparent; width: 100%;font-size: 14px;" placeholder="say something." rows="6" cols="1" id="text_strategy" name="text_strategy" readonly="readonly">
        </textarea>
      </div>
      <div id="opinion3" style="width: 28%; display: inline-block;">
        <label for="text_opinion3" class="label_text">审定意见</label><br/>
        <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="" rows="6" cols="1" id="text_opinion3" name="text_opinion3" ></textarea>
      </div>
    </div>
<!-- 审定和终止按钮 -->
    <div style="text-align:right; max-width: 100%;margin-top: 20px;" >
      <a class="abort"  href="javascript:OnApproveByUser3(<%=id%>,'goback');" ></a>
      <a class="button_audit"  href="javascript:sendNextUser_Byuser3(<%=id%>,'&quot;goto&quot;');"></a><!-- OnApproveByUser3(<%=id%>,'goto'); -->
    </div>
    </div>
    <div id="success_block_shending" style="background-color:#ccffdd;border:1px blue solid;padding-top:5px;text-align:center;width:300px;height:40px;position:relative;left:350px;top:-400px;z-index:1000;display:none;"><span style="font-size:20px;color:red;font-weight:bold;" >审定成功</span></div>
  </form>
</div>
	</body>
</html>
