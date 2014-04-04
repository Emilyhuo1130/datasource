<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.3.2.min.js"></script>	
  <%String userName =(String)session.getAttribute("username"); %>
</head>
<body>
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="../ticket/_notification_ticket.jsp" target="ticket">运营统计指标</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="../ticket/_feedback_ticket.jsp" target="ticket">维修统计指标</a>
      </li>
    </ul>
  </div>	
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
    <br/>
    <iframe src="../ticket/_notification_ticket.jsp" name="ticket" style="width:958px; height:700px; border:none" ></iframe>			
    <br/>
  </div>
</body>
</html>
