<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<%String id = request.getParameter("id");%>
		<%
	      	Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dataString = format.format(date);
        %>  
        
       <script language="JavaScript" src="../../assets/javascripts/jquery-1.4.4.min.js"></script>
         <script language="JavaScript" src="../../assets/javascripts/commons.js"></script>
 		<script language="JavaScript" src="../../assets/javascripts/alert.js"></script>
       <script type="text/javascript">
       	$(document).ready(function(){
       		<%
      			
        		String  notifys  = new String(request.getParameter("record").getBytes("ISO-8859-1"),"utf-8");
       			String[] infos = new String[5];
       			infos = notifys.split(",");
       			
       	     %>
       		
       	});
       </script>
	</head>
	<body>
		<div class="steps_title">
  <img src="../../images/alert_step4_title.png" />
</div>

<div style="background-image:url('../../assets/images/table_background.png');margin-top: 30px;text-align:left; vertical-align:middle; height: 300px;width: 100%;border-bottom: 1px solid gray;">
  <div style="position: relative; top: -170px;  width: 40px; height: 200px; display: inline-block;  margin-left: 25px;">
    <img src="../../images/OK.png" />
  </div>

  <div style=" width: 300px; height: 200px; display:inline-block; margin-top: 30px; margin-left: 0px; ">
    <span id='success_info' style="font-size: 18px; font-weight: bold; color: #424242;">维修建议单发送成功</span>

    <table style="font-size: 14px; font-weight: bold; color: #505050; margin-top: 10px; margin-bottom: 10px;">
      <tr style="height: 25px;">
        <td>系统：</td>
        <td id='system'><%=infos[0]%></td>
      </tr>
      <tr style="height: 25px;">
        <td>设备：</td>
        <td id='equipmentName'><%=infos[1]%></td>
      </tr>
      <tr style="height: 25px;">
        <td>故障：</td>
        <td id='faultCause'><%=infos[2]%></td>
      </tr>
      <tr style="height: 25px;">
        <td>维修建议单号：</td>
        <td id='empCode'><%=infos[3]%></td>
      </tr>
      <tr style="height: 25px;">
        <td>发送至：</td>
        <td id='department'><%=infos[4]%></td>
      </tr>
    </table>
    <a class="continue" href="faultAlert_check_list_user4.jsp"></a>
  </div>
</div>
	</body>
</html>
