function getServicePath()
{
	//return "http://192.168.2.119:8080/CCTV_manage/jsp/manage/";//标准在工程名后加斜杠 ，之后用相对路径访问
	return "/CCTV_Manage/jsp/manage/";
}

/**post请求**/
function PostJSONQuery(postURL,json, on_success)
{
  $.ajax({
    url: getServicePath()+postURL,
    type:"post",
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    processData : true,
    data: json,
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	showAlertInfo("error");
    }
  });
}

function PostJSONQuerySendString(postURL,json, on_success)
{
  $.ajax({
    url: getServicePath()+postURL,
    type:"post",
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    processData : true,
    data: JSON.stringify(json),
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	showAlertInfo("error");
    }
  });
}



/**消息提示框***/
function showAlertInfo(message){
	$("#someinfo").html(message);
	$(".test").fbmodal({
		buttons: true
	}); 
}
/**从服务端获取选择区位**/
function selectsections(){
	//alert("/**从服务端获取选择区位**/");
	var json={};
	PostJSONQuery("initAllSectionInfo.do",json,function(response){
		//alert(JSON.stringify(response));
		var info=response;
		for(var i=0;i<info.length;i++){
			$("#u43").append("<OPTION  value='"+info[i]+"'>"+info[i]+"</OPTION>");
		}
	      
	});
}

/**************分页************/
//首页
function firstpage(flag){
	if(flag=="showAllUsers"){
		searchUsers(1);
	}else if(flag=='vidiconManage'){
		searchvidiconManage(1);
	}else if(flag=='VCRManage'){
		searchVCRManagee(1);
	}else if(flag=='systemConfig'){
		searchsystemConfig(1);
	}else if(flag=='controlGroup'){
		searchcontrolGroup(1);
	}else if(flag=='logerManage'){
		searchlogerManage(1);
	}else if(flag=='zoneManage'){
		searchzoneManage(1);
	}
	$("#current_page").html(1);
}
//上一页
function backpage(flag){
	var page=parseInt($("#current_page").html());
	if(page>1){
		if(flag=="showAllUsers"){
			searchUsers(page-1);
		}else if(flag=='vidiconManage'){
			searchvidiconManage(page-1);
		}else if(flag=='VCRManage'){
			searchVCRManagee(page-1);
		}else if(flag=='systemConfig'){
			searchsystemConfig(page-1);
		}else if(flag=='controlGroup'){
			searchcontrolGroup(page-1);
		}else if(flag=='logerManage'){
			searchlogerManage(page-1);
		}else if(flag=='zoneManage'){
			searchzoneManage(page-1);
		}
		$("#current_page").html(page-1);
	}
	
}
//下一页
function nextpage(flag){
	var page=parseInt($("#current_page").html());
	var totalPage=parseInt($("#totalPage").html());
	if(totalPage>page){
		if(flag=="showAllUsers"){
			searchUsers(page+1);
		}else if(flag=='vidiconManage'){
			searchvidiconManage(page+1);
		}else if(flag=='VCRManage'){
			searchVCRManagee(page+1);
		}else if(flag=='systemConfig'){
			searchsystemConfig(page+1);
		}else if(flag=='controlGroup'){
			searchcontrolGroup(page+1);
		}else if(flag=='logerManage'){
			searchlogerManage(page+1);
		}else if(flag=='zoneManage'){
			searchzoneManage(page+1);
		}
		$("#current_page").html(page+1);
	}
}
//最后一页
function lastpage(flag){
	var totalPage=parseInt($("#totalPage").html());
	if(flag=="showAllUsers"){
		searchUsers(totalPage);
	}else if(flag=='vidiconManage'){
		searchvidiconManage(totalPage);
	}else if(flag=='VCRManage'){
		searchVCRManagee(totalPage);
	}else if(flag=='systemConfig'){
		searchsystemConfig(totalPage);
	}else if(flag=='controlGroup'){
		searchcontrolGroup(totalPage);
	}else if(flag=='logerManage'){
		searchlogerManage(totalPage);
	}else if(flag=='zoneManage'){
		searchzoneManage(totalPage);
	}
	$("#current_page").html(totalPage);
}




/**返回按钮***/
function reback(){
	top.cenFrame.history.go(-1);
}

/***非法字符正则字符验证*/
function veriftycharacter(com,id){
	//alert("非法字符正则字符验证"+com.value);
	var id="#"+id;
	var regExp=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
	if(com.value.length>0){
		if(regExp.test(com.value)){
			//alert(com.value+"存在非法字符");
			$(id).slideDown("slow");
		}else{
			$(id).slideUp("slow");
		}
	}else{
		$(id).slideUp("slow");
	}
}
/***电话号码验证*/
function veriftyPhoneNumber(com,id){
	var id="#"+id;
	var regExp=/^[1][358][0-9]{9}$/;
	if(com.value.length>0){
		if(regExp.test(com.value)){
			$(id).slideUp("slow");
		}else{
			$(id).slideDown("slow");
		}
		
	}else{
		$(id).slideUp("slow");
	}
}
/***系统配置数字正则表达式验证*/
function veriftyNumber(com){
	var regExp=/^(?:[1-9][0-9](\.\d*)?|100(\.\d*)?$/;
	if(com.value.length>0){
		
		if(regExp.test(com.value)){
			
		}else{
			showAlertInfo("请输入大于1小于100的数字");
		}
	}else{
		//$(id).slideUp("slow");
	}
	
	
	
}

function veriftyNumber2(com,id){
	var id="#"+id;
	var regExp=/^(?:[1-9]\d|[2-9])$/;
	if(com.value.length>0){
		
		if(regExp.test(com.value)){
			$(id).slideUp("slow");
		}else{
			$(id).slideDown("slow");
		}
	}else{
		$(id).slideUp("slow");
	}
}
function veriftyNumber3(com,id){
	var id="#"+id;
	var v=parseInt(com.value);
	if(v<100&&v>0){
		$(id).slideUp("slow");
	}else{
		$(id).slideDown("slow");
	}
}

/***ip规范验证**/
function veriftyIP(com,id){
	var id="#"+id;
	var regExp=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	if(com.value.length>0){
		
		if(regExp.test(com.value)){
			$(id).slideUp("slow");
		}else{
			$(id).slideDown("slow");
		}
	}else{
		$(id).slideUp("slow");
	}
}
/***MAC规范验证**/
function veriftyMAC(com,id){
	var id="#"+id;
	var regExp=/[A-F\d]{2}[:\-][A-F\d]{2}[:\-][A-F\d]{2}[:\-][A-F\d]{2}[:\-][A-F\d]{2}[:\-][A-F\d]{2}/;
	if(com.value.length>0){
		
		if(regExp.test(com.value)){
			$(id).slideUp("slow");
		}else{
			$(id).slideDown("slow");
		}
	}else{
		$(id).slideUp("slow");
	}
}
/***数字格式规范**/
function veriftyAllNumber(com,id){
	var id="#"+id;
	var regExp=/^[0-9]*$/;
	if(com.value.length>0){
		if(regExp.test(com.value)){
			$(id).slideUp("slow");
		}else{
			$(id).slideDown("slow");
		}
		
	}else{
		$(id).slideUp("slow");
	}
}



/********************************************保存前的验证**************************************************/
/***字符串复返正则验证*/
function veriftyChar(text){
	var regExp=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
	if(text.length>0){
		if(regExp.test(text)){
			return false;
		}else{
			return true;
		}
		
	}else{
		return true;
	}
}
/***手机号码*/
function veriftyPhone(text){
	var regExp=/^[1][358][0-9]{9}$/;
	if(text.length>0){
		if(regExp.test(text)){
			return true;
		}else{
			return false;
		}
		
	}else{
		return true;
	}
}

/***ip规范验证**/
function veriftyIPreturn(text){
	var regExp=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	if(text.length>0){
		if(regExp.test(text)){
			return true;
		}else{
			return false;
		}
		
	}else{
		return true;
	}
}

/***MAC规范验证**/
function veriftyMACreturn(text){
	var regExp=/[A-F\d]{2}[:\-][A-F\d]{2}[:\-][A-F\d]{2}[:\-][A-F\d]{2}[:\-][A-F\d]{2}[:\-][A-F\d]{2}/;
	if(text.length>0){
		if(regExp.test(text)){
			return true;
		}else{
			return false;
		}
		
	}else{
		return true;
	}
}
/***数字格式规范**/
function veriftyAllNumberreturn(text){
	var regExp=/^[0-9]*$/;
	if(text.length>0){
		if(regExp.test(text)){
			return true;
		}else{
			return false;
		}
		
	}else{
		return true;
	}
}
/***0-100的数字*/
function veriftyNumber99(text){
	var v=parseInt(text);
	if(v<100&&v>0){
		return true;
	}else{
		return false;
	}
}






/**树结构模拟数据***/
var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
var zNodes =[
			{
            "name": "区位",
            "children": [
                {
                    "name": "机房",
                    "children": [
                        {
                            "name": "摄像机3",
                            "value": 3,
                            "checked": true
                        }
                    ]
                },
                {
                    "name": "广场",
                    "children": []
                },
                {
                    "name": "办公室",
                    "children": [
                        {
                            "name": "摄像机4",
                            "value": 4,
                            "checked": false
                        }
                    ]
                },
                {
                    "name": "仓库",
                    "children": [
                        {
                            "name": "摄像机2",
                            "value": 2,
                            "checked": true
                        }
                    ]
                }
            ]
        }
		];