<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" language="javascript" src="./fckeditor/fckeditor.js"></script>
<title>FCKeditor测试接收结果</title>
</head>

<body>
<%
    String contest = request.getParameter("contest");
	//out.print(contest);
%>

<form id="form1" name="form1" method="post" action="#">
<table width="100%" border="0">
<tr>
    <td height="25">
      <textarea name="contest" id="contest" style="width:100%; height:400px;">
      <%=contest %>
      </textarea>
<script type="text/javascript">
	var oFCKeditor = new FCKeditor( 'contest' );
	oFCKeditor.BasePath = 'fckeditor/' ;
	oFCKeditor.ToolbarSet = 'Default' ;
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


