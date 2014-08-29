<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript" src="../js/jquery-1.4.4.min.js" ></script>
  	<script type="text/javascript" src="../js/jquery.form.js" ></script>
  	<script type="text/javascript">
            $(document).ready(function() {
                var options = { 
                    //target:        '#output2',   // target element(s) to be updated with server response
                    beforeSubmit:  showRequest,  // pre-submit callback
                    success:      function(response){
                    	showResponse(response);
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
               // 绑定表单提交事件处理器
                $("#formtest").submit(function() {
                    //提交表单
                    $(this).ajaxSubmit(options);

                    // !!! Important !!!
                    // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
                    return false;
                });
                // pre-submit callback
                function showRequest() {
                    alert("before submit");
                    return true;//返回true才可以进行下一步
                } 
                // post-submit callback
                function showResponse(response) {
                    alert("submit success"+response);
                }
            });
  		
  	</script>
  </head>
  <body>
  	<form action = "/SpringTest/login.do" method = "post" >

 name: 
<input type="text" name="name" />
<br />
password: 
<input type="text" name="password" />
<br />
<input type="submit" value = "SUBMT" />

</form>

管理员登录：<br/>
<form action = "/SpringTest/admin/login.do" method = "post" >

 name: 
<input type="text" name="adminName" />
<br />
password: 
<input type="text" name="password" />
<br />
<input type="submit" value = "SUBMT" />

</form>


普通用户登录：<br/>

<form action = "/SpringTest/user/login.do" method = "post" >

 name: 
<input type="text" name="userName" />
<br />
password: 
<input type="text" name="password" />
<br />
<input type="submit" value = "SUBMT" />

</form>




	<form id="formtest" >
		<input  type="email" name="email" placeholder="123@163.com"/>
		<input  type="text"  name="telephone" placeholder="13921219802" pattern="[a-z]" title="例如:13921219802"/>
		<input type="text" name="name" placeholder="name" min="5" man="50" onFocus="$(this).select()"/>
		<input  id="submit" type="submit" value="提交" ;/>
	</form>
  
  
  <h3>下载测试</h3>
  <br/>
  <a onclick="testdownload()">下载</a>
  <script type="text/javascript">
  		function testdownload(){
  			window.open("../download.do");
  		}
  		
  		function PostJSONQuery()
  		{
  		  $.ajax({
  		    url: "../download.do",
  		    type:"post",
  		    processData : true,
  		    data: {},
  		    dataType: "json",
  		    success: function(response) {
  		     
  		    },
  		    error: function (xhr, ajaxOptions, thrownError) {
  		    	alert("error");
  		    }
  		  });
  		}
  </script>
  
  <h3>异步下载测试</h3>
  <br/>
  <a onclick="PostJSONQuery()">下载</a>
  
  
  </body>
</html>
