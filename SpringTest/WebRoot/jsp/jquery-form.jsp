<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript" src="../js/jquery-1.4.4.min.js" ></script>
  	<script type="text/javascript" src="../js/jquery.form.js" ></script>
  	<script type="text/javascript">
  	
            $(document).ready(function() {
                
               // 绑定表单提交事件处理器
                $("#formtest").submit(function() {
                    //提交表单
                    options.success= function(response){
                    	showResponse(response);
                    };
                   options.beforeSubmit=showRequest;
                   
                    $(this).ajaxSubmit(options);

                    // !!! Important !!!
                    // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
                    return false;
                });
                // pre-submit callback
            });
            
            
            var options = { 
                    //target:        '#output2',   // target element(s) to be updated with server response
                    beforeSubmit:  showRequest,  // pre-submit callback
                    success:      function(response){
                    	
                    } ,  // post-submit callback
 
                    // other available options: 
                    url:       "/SpringTest/testform.do",        // override for form's 'action' attribute
                    type:      "post"        // 'get' or 'post', override for form's 'method' attribute
                    //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
                    //clearForm: true        // clear all form fields after successful submit 
                    //resetForm: true        // reset the form after successful submit 
 
                    // $.ajax options can be used here too, for example: 
                    //timeout:   3000 
                };
                
            
                function showRequest() {
                    alert("before submit");
                    return true;//返回true才可以进行下一步
                } 
                // post-submit callback
                function showResponse(response) {
                    alert("submit success"+response);
                }
  		
  	</script>
  </head>
  <body>

	<form id="formtest" >
		<input  type="email"  required name="email" placeholder="123@163.com"/>
		<input  type="text"  name="telephone" placeholder="13921219802" pattern="^[1][358][0-9]{9}$" title="例如:13921219802"/>
		<input  id="submit" type="submit" value="提交" ;/>
	</form>
  
  
  </body>
</html>
