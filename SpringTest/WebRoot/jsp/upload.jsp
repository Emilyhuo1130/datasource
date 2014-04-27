<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  	<script type="text/javascript" src="../js/jquery-1.4.4.min.js" ></script>
  	<script type="text/javascript" src="../js/jquery.form.js" ></script>
  	<script type="text/javascript">
  	var options = { 
                    //target:        '#output2',   // target element(s) to be updated with server response
                    beforeSubmit:  showRequest,  // pre-submit callback
                    success:      function(response){
                    	
                    } ,  // post-submit callback
 
                    // other available options: 
                    url:       "/SpringTest/jsp/ajaxUploadTest.do",        // override for form's 'action' attribute
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
  		$(document).ready(function(){
  			$("#formTest").submit(function(){
  				options.success=function(response){
  					showResponse(response);
  				};
  				options.beforeSubmit=showRequest;
  				$(this).ajaxSubmit(options);
  				return false;
  			});
  		});
  	</script>
<title>Insert title here</title>
</head>
<body>
	<form action="testUpload1.do" enctype="multipart/form-data" method="post">
		<input type="file" name="file" />
		<input type="file" name="file" />
		<input type="submit" value="上传1"/>
	</form>
	<p/>
	<form action="testUpload2.do" enctype="multipart/form-data" method="post">
		<input type="file"  name="file" />
		<input type="submit" value="上传2"/>
	</form>
	<p/>
	<form action="testUpload3.do" enctype="multipart/form-data" method="post">
		<input type="file"  name="file" />
		<input type="submit" value="上传3"/>
	</form>
	<br/>
	-------------------------------------------------------------------
	<br/>
	基于ajax的文件上传
	<br/>
	<form id="formTest" enctype="multipart/form-data">
		<input type="file" name="file"/>
		<input type="submit" value="基于ajax的上传"/>
	</form>
</body>
</html>