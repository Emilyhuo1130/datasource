function getServicePath()
{
	return "http://localhost:8888/Task_Manage/";//标准在工程名后加斜杠 ，之后用相对路径访问
	//return "http://localhost:8888/taskManager_view/";
}

function imgContextPath()
{
	return "/file/img/";
}

function ringContextPath()
{
	return "/SupermarketClient/websites/";	
}
/**
 *获取URL参数?xx=123 
 */
function GetQueryString(name){
	var reg= new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
	var url = document.URL;
	var queryStr = url.substring(url.indexOf("?"), url.length);
	var r = queryStr.substr(1).match(reg);
	if(r!=null){
		return decodeURI(r[2]);
	}
	else{
		return null;
	}
}

function trim(obj) 
{  
	return obj.replace(/(^\s*)|(\s*$)/g, "");
} 

/**
 * 业务上下线
 * @param id-业务id
 * @param flag 1-上线 2-下线
 * @param packageFlag -业务类型 0:开卡业务 1:业务办理 2:软件 4:彩铃 3:小说 5:游戏 6 活动 7手机 8首页活动
 * @param obj-当前对象
 */
function softinline(id,flag,packageFlag,obj)
{
	if(flag==1)
 	{
		if(!confirm("确认上线吗?")){
				return; 
		}
		
	}else if(flag==2)
	{
		if(!confirm("确认下线吗?")){
					return; 
		}
	}
	var reqMethod = "mobileMMAction";
	var reqClass = "NovelInfoManager";
	var reqContent ='"action":"line",';
		reqContent += '"model":"'+id+'",';
		reqContent += '"lineFlag":"'+flag+'",';
		reqContent += '"packageFlag":"'+packageFlag+'"';
		
	$.ajax({
		url:serviceContextPath()+'servlet/Service?'+Math.random(),
		type:'post',
		dataType:'json',
		data:'{"method":"'+reqMethod+'","type":"'+reqClass+'","req":{'+reqContent+'}}',
		success:function(data){
				if(data.actionRst == 0)
				{
					alert("操作成功!");
					$(obj).parent().parent().hide();
					queryList();
				}		
		},
		error:function(e){
				alert("操作失败!");
		}
	});
}
