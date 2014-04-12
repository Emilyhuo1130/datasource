<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>html5测试页</title>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/html5.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		getimage();
		getLine();
		getRect();
		disposeImage();
	});
</script>
</head>
<body>
	当输入文本是运行脚本：<input oninput="oninput();" type="text"/>
	<select id="select1"> 
		<option>第1页</option>
	</select>
	<br/>
	<h4>canvas 圆形测试</h4><br/>
	<canvas id="myCanvas" width="900" height="280" style="border:1px solid #c3c3c3;">
			Your browser does not support the canvas element.
	</canvas>
	<h4>canvas 线条测试</h4><br/>
	<canvas id="myCanvas2" width="900" height="280" style="border:1px solid #c3c3c3;">
			Your browser does not support the canvas element.
	</canvas>
	<h4>canvas 矩形测试</h4><br/>
	<canvas id="myCanvas3" width="900" height="280" style="border:1px solid #c3c3c3;">
			Your browser does not support the canvas element.
	</canvas>
	<h4>canvas 图像处理</h4><br/>
	<canvas id="myCanvas4" width="900" height="280" style="border:1px solid #c3c3c3;">
			Your browser does not support the canvas element.
	</canvas>
</body>
</html>