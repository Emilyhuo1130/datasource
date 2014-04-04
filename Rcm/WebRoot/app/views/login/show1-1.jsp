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
	<body>
		<div id="background" style="width: 100%;  ">
  			<div id="image" style="position:absolute; top:40%; background-image: url('../../images/background_login.png'); width: 100%; height: 100px;">
  		</div>
	</div>
	<div id="login_panel" style="position: absolute; top: 29%; left: 35%; background-image: url('../../images/login_panel.png'); width: 546px; height: 334px;">
  		<div id="login_input" style="position: relative; left: 240px; top: 130px;">
  			<form action="show.jsp" method="post">
    			<table style="height: 100px;">
      				<tr>
       					 <td style="width: 50px;">
         					 <label for="system_name" class="label_text">用户名</label>
        				</td>
        				<td style="width: 150px;">
          					<input style="height: 20px;" class="input_content" type="text" id="username" name="username" value="" />
        				</td>
      				</tr>
      				<tr>
       					 <td style="width: 50px;">
         					 <label for="subsystem_name" class="label_text" >密码</label>
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
    	<%
    		String username =(String)(request.getParameter("username"));
    	   String password =(String)(request.getParameter("password"));
    	   //人名字典  陈强  张华  刘鹏  李红 朱军
  			if("chenqiang".equals(username)){
	    	   session.setAttribute("username","陈强");
  				
  			}else if("zhanghua".equals(username)){
  				 session.setAttribute("username", "张华");
  			}else if("liupeng".equals(username)){
 				 session.setAttribute("username", "刘鹏");
 			}else if("lihong".equals(username)){
 				 session.setAttribute("username", "李红");
 			}else if("zhujun".equals(username)){
 				 session.setAttribute("username", "朱军");
 			}else if("zhouhong".equals(username)){
 				 session.setAttribute("username", "周红");
 			}else if("huming".equals(username)){
 				 session.setAttribute("username", "胡明");
 			}else if("caoxue".equals(username)){
 				 session.setAttribute("username", "曹雪");
 			}else if("zhoulin".equals(username)){
 				 session.setAttribute("username", "周林");
 			}
 			
 			
 			
 			
    	  // session.setAttribute("username",username);
    	   if("chenqiang".equals(username)){
    	   response.sendRedirect("../layouts/application.jsp");   
    	   } else if("zhanghua".equals(username)){
    	    response.sendRedirect("../alert/_check_list.jsp"); 
    	   }else if("liupeng".equals(username)){
    	    response.sendRedirect("../alert/_audit_list.jsp"); 
    	   }else if("lihong".equals(username)){
    	    response.sendRedirect("../alert/_send_list.jsp"); 
    	   }else if("zhujun".equals(username)){
       	    response.sendRedirect("../event/_sc.jsp"); 
       	   } else if("zhouhong".equals(username)){
       	    response.sendRedirect("../occ/occmain.jsp"); 
       	   }else if("huming".equals(username)){
       	    response.sendRedirect("../cocc/coccmain.jsp"); 
       	   }else if("caoxue".equals(username)){
       	    response.sendRedirect("../occ/_audit_list.jsp"); 
       	   }else if("zhoulin".equals(username)){
       	    response.sendRedirect("../occ/faultAlert_check_list_user4.jsp"); 
       	   }     
    	 %> 	 
 	</form>
  </div>
</div>
</body>
</html>
