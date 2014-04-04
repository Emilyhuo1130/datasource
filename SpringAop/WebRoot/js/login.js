$(document).ready(function() {
// 绑定表单提交事件处理器
   $("#formtest").submit(function() {
   //提交表单
	   var option=setOptions(showRequest,"login.do");
	   option.success= function(response){
	              if(response){
	            	  location.href="userMain.do";
	              }else{
	            	  
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

function test(){
	alert('-----');
}