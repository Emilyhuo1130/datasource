<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    
    <title>日志查看</title>
    
		  
  </head>
  
  <body>
    
     <c:forEach items="${loginfo}" var="element" >
     <tr>${element }</tr><br/>
     </c:forEach>
  </body>
</html>
