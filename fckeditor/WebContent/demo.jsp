<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	var home="<%=path.substring(1)%>";
</script>
<script type="text/javascript" language="javascript" src="./fckeditor/fckeditor.js"></script>
<script type="text/javascript" language="javascript" src="./fckeditor/fckconfig.js"></script>
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
                    url:       "default_do.jsp",        // override for form's 'action' attribute
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
<title>Insert title here</title>
</head>
<body>
<form id="formtest">
<table width="100%" border="0">
<tr>
    <td height="25">
   eeeeee <input type="text" >
      <textarea name="contest" id="contest" style="width:100%; height:400px;">
      </textarea>
<script type="text/javascript">
	var oFCKeditor = new FCKeditor( 'contest' );
	oFCKeditor.BasePath = home+'/' ;
	oFCKeditor.ToolbarSet = 'Default' ;
	//oFCKeditor.ToolbarSet = "Custom" ;
	oFCKeditor.Width = '100%' ;
	oFCKeditor.Height = '400' ;
	oFCKeditor.Value = '' ;
	oFCKeditor.ReplaceTextarea(); 
	//oFCKeditor.Create() ;
</script>
      <input type="submit" name="Submit" value="提交" />
    </td>
</tr>
</table>
</form>
</body>

</html>