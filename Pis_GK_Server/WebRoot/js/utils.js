

function   showFloatStack(jqueryNode,conTent){
	
	var $node = $(jqueryNode).parent();
		$(jqueryNode).mouseover(function(){
			//兄弟菜单的子菜单收回   本子菜单滑出
			$node.siblings().find("div").hide("10");
			//追加内容
			$node.append(conTent);
			//显示内容
			$node.children("div").show("10");
			//设置样式
			$node.find("div").css({"font-size":".85em"});
});
	
	$node.mouseout(function(){
		$node.siblings("div").mouseleave(function(){
				$(this).fadeOut("fast");
		});
		$node.mouseleave(function(){
			$node.find("div").hide("10");
			
		});
		
	});
	
}

//获得区位绑定的ip
function getBindIp(req_json,callback){
	  $.ajax({
		    url: "getIPByClientId.do",
		    type:"post",
		    contentType : "application/x-www-form-urlencoded;charset=utf-8",
		    processData : true,
		    data: req_json,
		    dataType: "json",
		    success: function(response) {
		    			callback(response);
		    },
		    error: function (xhr, ajaxOptions, thrownError) {
		    
		    }
		  });
	  
}

$.msg.defaults = {
		// 默认style样式链接
		css: '../jsp/css/jquerymsg.css',
		// 动画时间
		duration: 500,
		// 消息内容
		msg: "等待...",
		// 消息类型:inverse(默认)/warning/error/success/info/muted
		type: "info",
		// 消息位置，默认水平垂直居中
		position: {
			
		}
	};


//获取屏幕宽高
function getMonitorSize(){
	return [document.body.offsetWidth,document.body.offsetHeight];
}




//设计弹出框  包含确认和取消按钮


 $.createDialog=function(msg,callback){
	 var dialog_default={
				"alertDialog":{
					"position":"absolute",
					"left":getMonitorSize()[0]/5+'px',
					"top": getMonitorSize()[0]/5+'px'
					
				},
				"disableStack":{
					"width":getMonitorSize()[0]+'px',
					"height":getMonitorSize()[1]+'px',
					"left":0,
					"top":0,
					"opacity":".5"
					
				},
				"button":{
					"ensure":true,
					"cancel":false
				}
				
		};
		//alert("this obj is"+this);this是这个函数本身
		var $document = $(window.document.body);//html的body对象
		//alert($document.html());
		//删除之前的
		$document.find("#alertDialog").remove();
		$document.find("#disableStack").remove();
		
		$document.append("<div id='alertDialog' style='width:500px;height:100px;'>" +
				"<div style='height:20px;background-color:#0000e1;'></div>" +
				"<div style='height:80px;background-color:#e2eddb;'>" +
				"<div><div style='height:10px;'></div><span id='msg'  style='margin-left:30%;'>"+msg+"</span></div>"+
					"<div style='position:relative;left:50%;top:30%;'>" +
						"<button id='btn_ensure' class='button' >确认</button>" +
						"<button  id='btn_cancel' class='button' style='position:relative;left:10px;'>取消</button>" +
					"</div>"+
				"</div>" +
				"</div>");
	
		//计算位置
		var d = dialog_default;
		//alert(JSON.stringify(d));
			$("#alertDialog").css("position",d.alertDialog.position);
			$("#alertDialog").css("z-index",9999);
			$("#alertDialog").css( "left",d.alertDialog.left);
			$("#alertDialog").css( "top",d.alertDialog.top);
			
		//遮罩层
			$document.append("<div id='disableStack' style='position:absolute;z-index:9998;'></div>");
			$("#disableStack").css("width",d.disableStack.width);
			$("#disableStack").css("height",d.disableStack.height);
			$("#disableStack").css("left",d.disableStack.left);
			$("#disableStack").css("top",d.disableStack.top);
			$("#disableStack").css("background-color","#888888");
			$("#disableStack").css("opacity",d.disableStack.opacity);
			
		//按钮事件
			$("#btn_ensure").click(function(){
				$("#disableStack").remove();
				$("#alertDialog").remove();
				callback();
				return true;
				});

			$("#btn_cancel").click(function(){
				$("#disableStack").remove();
				$("#alertDialog").remove();
				return false;
				});
		
	return  $("button").click;//居然可以返回一个事件
			
	 };

	 










