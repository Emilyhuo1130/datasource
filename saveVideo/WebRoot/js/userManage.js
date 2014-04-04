$(document).ready(function() {
// 绑定表单提交事件处理器
   $("#formtest").submit(function() {
   //提交表单
	   var option=setOptions(showRequest,"start.do");
	   option.success= function(response){
	              if(response){
	            	 alert("开始保存文件");
	              }else{
	            	  alert("定时器正在运行");
	              }
	   };
	                   
	   $(this).ajaxSubmit(option);
	
	   // !!! Important !!!
	   // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
	   return false;
   });
   // pre-submit callback
});


$(document).ready(function() {
	// 绑定表单提交事件处理器
	   $("#formtest2").submit(function() {
	   //提交表单
		   var option=setOptions(showRequest,"linux1.do");
		   option.success= function(response){
		              if(response){
		            	 alert("开始保存文件");
		              }else{
		            	  alert("定时器正在运行");
		              }
		   };
		                   
		   $(this).ajaxSubmit(option);
		
		   // !!! Important !!!
		   // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
		   return false;
	   });
	   // pre-submit callback
	});

$(document).ready(function() {
	// 绑定表单提交事件处理器
	   $("#formtest3").submit(function() {
	   //提交表单
		   var option=setOptions(showRequest,"linux2.do");
		   option.success= function(response){
		              if(response){
		            	 alert("开始保存文件");
		              }else{
		            	  alert("定时器正在运行");
		              }
		   };
		                   
		   $(this).ajaxSubmit(option);
		
		   // !!! Important !!!
		   // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
		   return false;
	   });
	   // pre-submit callback
	});








function showRequest(){
	alert("befordSubmit");
	return true;
}



function end(){
	
	PostJSONQuery("end.do",{},function(response){
		if(response){
			alert("定时器已经停止");
		}
	});
}