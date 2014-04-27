function PostJSONQuery(json, on_success)
{
  $.ajax({
    url: "http://192.168.2.102:8080/Task_Manage/admin/do_userspages.do",
    type:"post",
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    processData : true,
    data: json,
    dataType: "json",
    success: function(response) {
      on_success(response);
      alert(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	alert("error");
    }
  });
}

function changeImagesAddress(){
	alert("test");
	 var alert_query_json = {
			 userName:"淡入淡出"
	 };
	 
	PostJSONQuery(alert_query_json,function(response){
		alert("response");
		alert(response.listInfo.length);//response 文件夹集合 response[i].value
		                                          //response[i].name
		
		alert(response.listInfo[0].age);
		
	});
}




/***跨域请求**/
function kuayu(){
	alert("test");
	 var alert_query_json = {
			 pageSize:"10",
			 userName:"",
			 userAccount:""
	 };
	 
	PostJSONQuery(alert_query_json,function(response){
		alert("response");
		
	});
}

function jsNetRegist(){  
    var jsonParam = {  
    		pageSize:"10",
			 userName:"",
			 userAccount:""
          };  
    var url = 'http://192.168.2.102:8080/Task_Manage/admin/do_userspages.do?pageSize=10&&userName=&&userAccount=';  
    $.getJSON(url, function(data){  
    	alert("55555");
    });
}

function downfile(){
	location.href="resource/file.zip";
}
