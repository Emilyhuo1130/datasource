<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>hidden</TITLE>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<STYLE type=text/css>
BODY {
	MARGIN: 0px
}
</STYLE>
		<SCRIPT type="text/javascript">

var win = "on";
function HideList()
{
	if (win == "on")
	{
		win = "off";
		document.getElementById('arrow').className='hiddenr';
		window.top.frameset_body.cols= "0,14,*"	;
	}
	else
	{
		document.getElementById('arrow').className='hiddenl';
		win = "on";
		window.top.frameset_body.cols= "160,14,*";
	}
}
</SCRIPT>
		<META content="MSHTML 6.00.2800.1593" name=GENERATOR>
	</HEAD>
	<BODY>
		<TABLE class=hiddentable height="100%" cellSpacing=0 cellPadding=0 border=0>
			<TBODY>
				<TR>
					<TD class=hiddentop width=13></TD>
				</TR>
				<TR>
					<TD class=hiddenl id=arrow style="CURSOR: hand" onclick=HideList() width=13></TD>
				</TR>
				<TR>
					<TD class=hiddenbottom></TD>
				</TR>
			</TBODY>
		</TABLE>
	</BODY>
</HTML>
