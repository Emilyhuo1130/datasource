<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <title>主动维保平台</title>

</head>
<body>
	<div id="part2" >
			<%     
			if(session.getAttribute("username")==null){
				response.sendRedirect("../alert/step0.jsp");
			}else if("陈强".equals(session.getAttribute("username"))){
    	   		response.sendRedirect("../alert/current _confirm_list.jsp");   
    	   } else if("张华".equals(session.getAttribute("username"))){
    	   		response.sendRedirect("../alert/_check_list.jsp"); 
    	   }else if("刘鹏".equals(session.getAttribute("username"))){
    	    	response.sendRedirect("../alert/_audit_list.jsp"); 
    	   }else if("李红".equals(session.getAttribute("username"))){
    	    	response.sendRedirect("../alert/_send_list.jsp"); 
    	   }else{
    	    	response.sendRedirect("../alert/_confirm_list.jsp");   
    	   }
   	    %>
</div>
</body>
</html>
