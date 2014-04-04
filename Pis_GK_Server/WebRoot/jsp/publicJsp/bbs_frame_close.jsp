<%@ page contenttype="text/html; charset=utf-8" pageencoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html public "-//w3c//dtd html 4.01 transitional//en">
<html>
	<head>
		<title>hidden</title>
		<meta http-equiv=content-type content="text/html; charset=utf-8">
		<style type=text/css>
body {
	margin: 0px
}
</style>
		<script type="text/javascript">

var win = "on";
function hidelist()
{
	if (win == "on")
	{
		win = "off";
		document.getelementbyid('arrow').classname='hiddenr';
		window.top.frameset_body.cols= "0,14,*"	;
	}
	else
	{
		document.getelementbyid('arrow').classname='hiddenl';
		win = "on";
		window.top.frameset_body.cols= "160,14,*";
	}
}
</script>
		<meta content="mshtml 6.00.2800.1593" name=generator>
	</head>
	<body>
		<table class=hiddentable height="100%" cellspacing=0 cellpadding=0 border=0>
			<tbody>
				<tr>
					<td class=hiddentop width=13></td>
				</tr>
				<tr>
					<td class=hiddenl id=arrow style="cursor: hand" onclick="hidelist();" width=13></td>
				</tr>
				<tr>
					<td class=hiddenbottom></td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
