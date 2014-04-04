function addUserInfo(){
	
	location.href="addUser.jsp";
	
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
         desiredArr.push(data[pro])    
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
//规定每页显示5条记录 即 pageSize=5，总的页数使用ajax从服务端获取，然后动态在页面生成分页标签 首页 上一页  123。。。下一页末页
//分页区  paginationArea
function  pagination(id,current_page,total_page){
	//alert(total_page);new function(){alert('执行赋值');$('#putpre_page').html("+i-1+");$('#putnext_page').html("+i+1+");}
	for(var i=total_page;i>0;i--){
		$("#"+id).after(""+
	         
	             "<a id='page"+i+"' class='current_page'  href='javascript:setPage("+i+","+total_page+");' onclick='paging("+i+");'>"+i+"</a>"
	          
		); 
		$("#"+id+current_page).css({"background-color":"#eeeeff"});
		
	};
	
};
function setPage(num,total_page){
		$("#current_page").html(num);
}

//上一页 下一页
function  prepage(total_page){
	var preNum=parseInt($("#current_page").html());
	if(preNum>1){
		preNum=preNum-1;
		$("#current_page").html(preNum);
	}
	paging(preNum,total_page);
}

function nextpage(total_page){
	var nextNum=parseInt($("#current_page").html());
	if(nextNum<total_page){
		nextNum=nextNum+1;
		$("#current_page").html(nextNum);
	}
	paging(nextNum,total_page);
	
}
//用户添加任务时将项目名称 工时填入表格
function  addAnotherProjectToTable(){
	$("#mission_today tbody tr").each(function(i,e){
		var $e=$(e);
	//	alert($e.children(0).html());
		if($e.children(0).html()==""||$e.children(0).html()==null){
			$e.children(0).html(44);
			$e.children(1).html(66);
		}
		
	});
	
}