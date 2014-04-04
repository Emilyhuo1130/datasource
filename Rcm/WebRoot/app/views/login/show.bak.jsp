<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <title>主动维保平台</title>
   	<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/application.css" />
  	<link type="text/css" rel="stylesheet" href="../../assets/stylesheets/login.css" />
	<script language="JavaScript" src="../../assets/javascripts/application.js"></script>
	<script language="JavaScript" src="../../assets/javascripts/login.js"></script>
	</head>
	<body style=" background-image: url('login.jpg');">
	
  		<div id="login_input" style="position: relative; left: 180px; top: 420px;">
  			<form action="show.jsp" method="post">
    			<table style="height: 100px;">
      				<tr>
       					 <td style="width: 100px;">
         					 <label for="system_name" class="label_text" style="font-size:25px; color:#04676c">用户名</label>
        				</td>
        				<td style="width: 150px;">
          					<input style="height: 25px;width:200px" class="input_content" type="text" id="username" name="username" value="" />
        				</td>
      				</tr>
      				<tr>
       					 <td style="width: 100px;">
         					 <label for="subsystem_name" class="label_text" style="color:#04676c;font-size:25px" >密&nbsp;&nbsp;&nbsp;码</label>
       					 </td>
        				<td style="width: 150px;">
          					<input style="height: 25px; width:200px" class="input_content" type="password" id="password" name="password" value="" />
        				</td>
      			  </tr>
      			  <tr>
        			<td style="width: 50px;">
        			</td>
        			<td style="width: 150px; text-align: center;">
        			   		  	
        				<button type="submit" class="button_login">
          				</button>
       			 	</td>
      			</tr>
    	</table>
    	
 	</form>
  </div>



</body>
</html>
