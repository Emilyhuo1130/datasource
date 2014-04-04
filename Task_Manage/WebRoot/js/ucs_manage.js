function addUserInfo(){
	
	location.href="addUser.jsp";
	
}
//获取当前的年份
function getYearNum(){
	return new Date().format("yyyy");
}
//发送ajax请求
function sendAjaxRequest(req_json,onsuccess){
	var reqData=req_json.data;
	$.ajax({  
        url :getServicePath()+req_json.action+"?callback=?",  
        type : "post",  
        dataType:"jsonp",  
        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
        data :reqData, 
        jsonpCallback:"person",
        success : function(response) {  
        	alert("-------------");
        	onsuccess(response);
        },  
        error : function(data) {  
        	alert("error------------------!");
            alert(JSON.stringify(data));  
        }  
    });  
	
}

//登录
function loginSys(){
	var loginNameObj = document.getElementById("userName");
	var loginPswdObj = document.getElementById("userPw");
	if(loginNameObj.value.length==0)
	{
		alert("用户名和密码不能为空!");
	}
	else if(loginPswdObj.value.length<4)
	{
		alert("密码长度不够!");
	}
  	else
  	{
  		loginNameObj.value = loginNameObj.value.replace(/\s+$|^\s+/g,"");
  		return true;
  	}
  	return false; 
}


function showLoginRst(rst, msg, roleId)
{
	if(rst=="0")
	{
		if (roleId == "1") {
			location.href = "main.jsp";
		} else if (roleId == "2") {
			location.href = "main_normal.jsp";
		}
	}
	else
	{
		alert(msg);
	}
};
//添加任务项
function addMission(){
	var req_json={"action":"user_Log/login.do",
							"data":{"user":"normal",
									"item":$("#item").val(),
									"needDay":$("#needDay").val(),
									"mission_thisWeek":$("#mission_thisWeek").val(),
									"mission_nextWeek":$("#mission_nextWeek").val()
									}
				};
	sendAjaxRequest(req_json,function(response){
		alert(JSON.stringify(response));
		
	});
	
}
//跳转到修改用户页面后显示目前该条用户的信息
function showThisUserInfo(id){
	alert(id);
	//location="userDetails.jsp?id=1";
}
//去掉select重复的option的内容
function  distinctOption(id){
	var projectsList=new Array($(id).children().length);
 	var temp=$(id).children();//将jquery对象转化为数组
 	
 	//将所有option内容保存
 	for(var i=0;i<$(id).children().length;i++){
 		 projectsList[i]=temp[i].innerHTML;
 	}
 	//去掉相同的option内容
 	 var data={};      
     var desiredArr=new Array();    
     for(var i=0;i<temp.length;i++){    
         data[projectsList[i]]=projectsList[i];    
     }   
     for(var pro in data){    
         desiredArr.push(data[pro]); 
       }    
     //（3）删掉所有子节点  
     var parent = $(id)[0];    
     //这里因为childNodes节点会动态变下标，所以用0的index实现全部删除    
     for (var i = 0, length= parent.childNodes.length; i < length; i++){    
         parent.removeChild(parent.childNodes[0]);    
     }   
       
     //（4）添加过滤后的子节点  
     for (var i = 0, length= desiredArr.length; i < length; i++){  
         var div_view=document.createElement("option");     
         var text = $(id)[0];    
         div_view.innerText=desiredArr[i];    
         text.appendChild(div_view);   
     }  
}

//获得当前的格式化后的日期 yyyy-mm-dd-----------------------------------------------------------

/**
* 时间对象的格式化;
*/
Date.prototype.format = function(format){
 /*
  * eg:format="YYYY-MM-dd hh:mm:ss";
  */
 var o = {
  "M+" :  this.getMonth()+1,  //month
  "d+" :  this.getDate(),     //day
  "h+" :  this.getHours(),    //hour
  "m+" :  this.getMinutes(),  //minute
  "s+" :  this.getSeconds(), //second
  "q+" :  Math.floor((this.getMonth()+3)/3),  //quarter
  "S"  :  this.getMilliseconds() //millisecond
   };
  
   if(/(y+)/.test(format)) {
    format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
   }
 
  for(var k in o) {
    if(new RegExp("("+ k +")").test(format)) {
      format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    }
   }
 return format;
};
//------------------------------分页--------------------------------------------//
var condition={"projectName": "" , "userName":"", "starTime":getYearNum()+"-01-01" , "endTime":getYearNum()+"-12-31"};
//规定每页显示5条记录 即 pageSize=5，总的页数使用ajax从服务端获取，然后动态在页面生成分页标签 首页 上一页  1下一页末页
function  pagination(id,current_page){
		$("#"+id).css({"background-color":"#eeeeff"});
};
function setPage(num,total_page){
		$("#current_page").html(num);
}

//上一页 下一页              要传入一个json对象
 function  prepage(current_page,total_page,condition){
	
	var preNum=parseInt($("#current_page").html());
	if(preNum>1){
		preNum=preNum-1;
		$("#current_page").html(preNum);
	}
	conditionSearch(preNum,condition);
}

function nextpage(current_page,total_page,condition){
	var nextNum=parseInt($("#current_page").html());
	if(nextNum<total_page){
		nextNum=nextNum+1;
		$("#current_page").html(nextNum);
	}
	conditionSearch(nextNum,condition);
	
}

//验证方法  正则表达式和需要的请求数据由调用者提供 id是存放错误信息的地方
//在jsp页面搭配一个div来提示用户
// <div id="data_error" style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">msg</span></div>
//<div id="nul_error" style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">msg</span></div>

function   validateData(regExp,req_data,id,nul_id,msg,nul_msg){
		var flag=false;
	//首先验证是否为空
		if(req_data==""){
			flag=false;
			$("#"+nul_id).slideDown("slow");
		}else{
			$("#"+nul_id).slideUp("slow");
			if(regExp.test(req_data)){
				$("#"+id).slideUp("slow");
				flag = true;
			}else{
				$("#"+id).find("span").html(msg);
				$("#"+id).slideDown("slow");
				flag = false;
			}
			
		}
	
	return flag;
}
//验证两次输入密码是否一致
var flag=false;
function  validatePassword(old_id,new_id,msg_id,nul_id,msg,nul_msg){
	if($("#"+old_id).val()==""||$("#"+new_id).val()==""){
		$("#"+nul_id).find("span").html(nul_msg);
		$("#"+nul_id).slideDown("slow");
		flag=false;
	}else{
		$("#"+nul_id).slideUp("slow");
		if($("#"+old_id).val()==$("#"+new_id).val()){
			$("#"+msg_id).slideUp("slow");
			flag=true;
		}else{
			$("#"+msg_id).slideDown("slow");
			$("#"+msg_id).find("span").html(msg);
			flag=false;
		}
	}
return flag;
	
}
//测试输入的字节长度

	String.prototype.len=function(){
			return this.replace(/[^x00-xff]/g,"aa").length;
		};
		
//tabel 行添加浮动层方法    本节点   放置的内容
		function   showFloatStack(jqueryNode,text){
			var $node = $(jqueryNode).parent();
			$node.siblings("div").remove();
			$node.after("<div style='position:absolute;z-index:100;display:none;opacity:.8;width:410px;height:100px;background-color:#ccccff;'><textarea style='width:100%;height:100%;border-width:thin medium thick 1px;border-color:#ccccff'>"+text+"</textarea></div>");
			$(jqueryNode).mouseover(function(){
				$node.siblings("div").css({"display":"block"});
				$node.css({"background-color":"#ccccff"});
				$node.css({"opacity":".8"});
			});
			$(jqueryNode).mouseout(function(){
				$node.css({"background-color":"#ffffff"});
				$node.css({"opacity":"1"});
				$node.siblings("div").css({"display":"none"});
			});
		
		
			
			
		}
