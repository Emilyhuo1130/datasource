 <%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	
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
  					
  				getDetailInfo4(<%=id%>);
  				
  			});
            </script>
	
	</head>
	<body>
		<div class="steps_title">
  <img src="../../images/alert_step4_title.png" />
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

  <div style="max-width: 72%;display: inline-block; height: 700px;padding-left: 2%;padding-right: 0px;">
    <table style="border-collapse:separate; border-spacing: 10px; text-align: right; width: 100%;">
      <tr >
       <td style="width: 60px;" ></td>
        <td style="width: 100px;" ></td>
        <td style="width: 60px;" >
          <label for="ticket_id" class="label_text">维修建议单号</label>
        </td>
        <td>
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
        <td style="width: 60px;">
          <label for="start_time" class="label_text">开始时间</label>
        </td>
        <td style="width: 90px;">
          <input id="start_time" name="start_time" type="date" style="width: 110px;" value="<%=dataString%>" />
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
        <td>
          <select style="width: 100%;" class="combobox" id="system_main" name="system">
          	<option value="">系统</option>
            <option value="信号系统">信号系统</option>
            <option value="综合监控系统" >综合监控系统</option>
            
          </select>
        </td>
        <td>
          <label for="subsystem" class="label_text">子系统</label>
        </td>
        <td>
          <select style="width: 100%;" class="combobox" id="subsystem" name="subsystem">
          	<option value="">子系统</option>
            <option value="轨旁设备" >轨旁设备</option>
            <option value="BAS系统">BAS系统</option>
            <option value="电力SCAD系统">电力SCAD系统</option>
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
          <textarea style="height: 125px;background-color: transparent; width: 100%;" placeholder="say something." rows="8" cols="1" id="malfunction_desc" name="malfunction_desc"></textarea>
        </td>
      </tr>
      <tr>
        <td>
          <label for="malfunction_cause" class="label_text">故障原因</label>
        </td>
        <td colspan="7">
          <textarea style="height: 125px;background-color: transparent; width: 100%;" placeholder="say something." rows="8" cols="1" id="malfunction_cause" name="malfunction_cause"></textarea>
        </td>
      </tr>
      <tr>
        <td>
          <label for="text_impact" class="label_text">故障影响</label><br/>
        </td>
        <td colspan="7">
          <textarea style="height: 125px;background-color: transparent; width: 100%;" placeholder="say something." rows="8" cols="1" id="text_impact" name="text_impact">  
          </textarea>
        </td>
      </tr>
      <tr>
        <td>
          <label for="strategy" class="label_text">维修优化</label>
        </td>
        <td colspan="7">
          <textarea style="height: 125px;background-color: transparent; width: 100%;" placeholder="say something." rows="8" cols="1" id="strategy" name="strategy">
		</textarea>
        </td>
      </tr>
      <tr>
        <td>
          <label for="sendto" class="label_text">发送至</label>
        </td>
        <td colspan="7">
          <select style="width: 100px; float:left;" class="combobox" id="sendto" name="sendto">
            <option>维保系统</option>
            <option>维保仓库</option>
          </select>
        </td>
      </tr>
    </table>
  </div>
</div>
<div id="success_block_send" style="background-color:#ccffdd;border:1px blue solid;padding-top:5px;text-align:center;width:300px;height:40px;position:relative;left:350px;top:-400px;z-index:1000;display:none;"><span style="font-size:20px;color:red;font-weight:bold;" >发送成功</span></div>
<div style="text-align:right; max-width: 100%;margin-top: 10px; margin-right: 7%;" >
  <a type="submit" class="submit" href="javascript:;" onclick="sendMessage(<%=id%>,'<%=equipmentId%>');"; style="margin-right: 100px;"></a>
</div>
	</body>
</html>
