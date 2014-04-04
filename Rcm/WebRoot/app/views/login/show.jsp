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
 			}else if("admin".equals(username)){
 				 session.setAttribute("username", "系统管理员");
 			}else if("liumin".equals(username)){
 				 session.setAttribute("username", "刘敏");
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
       	    response.sendRedirect("../occ/application.jsp"); 
       	   }else if("huming".equals(username)){
       	    response.sendRedirect("../cocc/coccmain.jsp"); 
       	   }else if("caoxue".equals(username)){
       	    response.sendRedirect("../occ/_audit_list.jsp"); 
       	   }else if("zhoulin".equals(username)){
       	    response.sendRedirect("../occ/faultAlert_check_list_user4.jsp"); 
       	   }else if("admin".equals(username)){
       	    response.sendRedirect("../power/powerset.jsp"); 
       	   }else if("liumin".equals(username)){
       	    response.sendRedirect("../cocc/cocclead.jsp"); 
       	   }         
    	 %> 	 
    	
 	</form>
  </div>



</body>
</html>
