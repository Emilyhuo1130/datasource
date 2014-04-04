<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  
  <title>后台管理国客星空</title>
   		
  	 <link type="text/css" rel="stylesheet" href="css/validate.css" />
	 <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
		 <script type="text/javascript" src="../js/jqueryPromit.js"></script>
		 <script type="text/javascript" src="../js/jqueryBlockUI.js"></script>
		 <script type="text/javascript" src="../js/utils.js"></script>
		 <script type="text/javascript" src="../js/gk.js"></script>
	
	<style>
	 html { overflow-y: scroll; } 
		table tr td label{
			color:#ffffff;
			font-weight:bold;
			
		}
	
	</style>
	</head>
	<body >
		
	<div id="login_panel" style="position: absolute;left:.2%;top:-10%;  background-image: url('../images/loginpanel.jpg'); width: 100%; height:110%;">
  		<div id="login_input" style="position: relative; left: 30%; top: 50%;">
  			<form action="login.do" method="post">
    			<table style="height: 100px;position:relative;left:9%;">
      				<tr>
       					 <td style="width: 50px;">
         					 <label for="system_name" class="label_text">用户名</label>
        				</td>
        				<td style="width: 150px;">
          					<input style="height: 20px;" class="input_content" type="text" id="username" name="username" value="" />
        				</td>
        				<td>
        					<span style="color:red;font-weight:bold;">${errorInfo}</span>
        				</td>
      				</tr>
      				<tr>
       					 <td style="width: 50px;">
         					
        				</td>
        				<td style="width: 150px;height:20px;">
          					
        				</td>
        				<td>
        					
        				</td>
      				</tr>
      				<tr>
       					 <td style="width: 50px;">
         					 <label for="subsystem_name" class="label_text" >密<span style="hidden:hidden;"> &nbsp;</span>码</label>
       					 </td>
        				<td style="width: 150px;">
          					<input style="height: 20px;" class="input_content" type="password" id="password" name="password" value="" />
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
</div>
</body>
</html>
