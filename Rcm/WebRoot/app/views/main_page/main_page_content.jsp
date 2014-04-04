<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE table PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
		<%
		
		
		 %>
	<table>
  	<tr>
    <td> 
   		<c:if test="${warningId ==1}" scope="request">
    	 	<img src="../../images/blue.gif" />
    	</c:if>
   		<c:if test="${warningId ==2}"  scope="request">
    	 	<img src="../../images/yellow.gif" />
    	</c:if>
     	<c:if test="${warningId ==3}" scope="request">
     		<img src="../../images/orange.gif" />
    	</c:if>
     	<c:if test="${warningId ==4}" scope="request">
     		<img src="../../images/red.gif" />
    	</c:if>
    	</td>
   	 	<td>
   	 	<c:choose>
         <c:when test="${statments == '#'}">
         <input type="radio" name="statements" disable="disable"/>
         </c:when>
         <c:otherwise>
         	<input type="radio" name="statements"/>
         </c:otherwise>
     </c:choose>
    </td>
    <td>${index + 1}</td>
    <td>${warningId}</td>
    <td>${equipmentId}</td>
    <td>${equipmentname}</td>
    <td>${systemName}</td>
    <td>${equipmentDescription}</td>
    <td>${lineNo}</td>
    <td>${stationName}</td>
    <td>${warningType}</td>
    <td>${warningTypeLevel}</td>
    <td>${warninginfo}</td>
    <td>${warningDate}</td>
    <td>${statments}</td>
  </tr>
  </table>
	</body>

</html>	
	







