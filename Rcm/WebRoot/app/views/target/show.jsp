<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <%String userName =(String)session.getAttribute("username"); %>
  <script language="JavaScript" src="../../assets/javascripts/jquery-1.3.2.min.js"></script>	
</head>
<body>
  <div id="sub_nav">
    <ul>
      <li class="list_item">
        <a  href="../target/_repair_statistics.jsp" target="target">运营统计指标</a>
      </li>
      <li class="list_separator">
        |
      </li>
      <li class="list_item">
        <a  href="../target/_operative_statistics.jsp" target="target">维修统计指标</a>
      </li>
    </ul>
  </div>	
  <div style="max-width: 100%; border: 1px solid gray; border-top: none; background-color: #f9f9fb;">
    <br/>
    <div id="include">	
		<iframe src="../target/_repair_statistics.jsp" name="target" style="width:958px; height:700px; border:none" ></iframe>		
		</div>
    <br/>
  </div>
</body>
</html>
