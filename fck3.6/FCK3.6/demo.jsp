<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>  
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">      
    <title>首页</title>  
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">  
<script type="text/javascript" src="./js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="./js/jquery.form.js" ></script>
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
                    url:       "do.jsp",        // override for form's 'action' attribute
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
<script type="text/javascript">
function test(){
	/* var editor=CKEDITOR.replace( 'editor1' );
	var s1=editor.document.getBody().getText(); //取得纯文本

	var s2=editor.document.getBody().getHtml(); //取得html文本 */
	var str=CKEDITOR.instances.editor1.getData();
	alert(str)
	
}
</script>
</head>  
  <body>  
<%--<ckfinder:ckfinder basePath="/CKFinderJava_0100/ckfinder/" width="700" height="500" /> --%>  
<%--<iframe src="demo.jsp" frameborder="0" scrolling="yes" id="setframe" name="setframe" width="100%" height="100%"></iframe>', --%>
<form action="do.jsp" method="post" id="formtest">  
<textarea cols="80" id="editor1" name="editor1" rows="10">

</textarea>  
<input type="submit" value="Submit" />  
</form>  
<input type="button" value="ceshi" onclick="test();" /> 
<ckfinder:setupCKEditor basePath="ckfinder/" editor="editor1" />  
<ckeditor:replace replace="editor1" basePath="ckeditor/" />  
  </body>  
</html>  