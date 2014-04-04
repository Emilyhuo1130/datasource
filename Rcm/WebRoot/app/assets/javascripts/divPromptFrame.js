	function sAlert(str) 
	{ 
	msgw=400;//提示窗口的宽度 
	msgh=100;//提示窗口的高度 
	titleheight=25; //提示窗口标题高度 
	bordercolor="#336699";//提示窗口的边框颜色 
	titlecolor="#99CCFF";//提示窗口的标题颜色 
	var sWidth,sHeight; 
	sWidth=document.body.offsetWidth;//获取窗口宽度 
	sHeight=screen.height;//获取屏幕高度 
	var bgObj=document.createElement("div");//关键在这里，原理：在body中创建一个div，并将其宽度与高度设置为覆盖整个窗体，如此一来就无法对其它窗口时行操作 
	bgObj.setAttribute('id','bgDiv'); 
	bgObj.style.position="absolute"; 
	bgObj.style.top="0"; 
	bgObj.style.background="#777"; 
	bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75"; 
	bgObj.style.opacity="0.6"; 
	bgObj.style.left="0"; 
	bgObj.style.width=sWidth + "px"; 
	bgObj.style.height=sHeight + "px"; 
	bgObj.style.zIndex = "10000"; 
	document.body.appendChild(bgObj);//设置完此div后将其显示出来 
	/**************************************************************************/
	sendNextUser_Byuser1(true,"18");
	} 
	
	function removeDiv(flag) 
	{ 
		alert($("#bgObj").text());
		if(flag){
		$("#bgObj").remove();//移除覆盖整个窗口的div层 
		}
	} ;