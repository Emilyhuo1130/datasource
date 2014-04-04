function getSelect(){
	PostJSONQuery("getSelect.do",{},function(response){
		for(var i=0;i<response.length;i++){
			$("#select2").append("<option value="+response[i]+">"+response[i]+"</option>");
		}
		$("#select1").val("晒太阳");
	});
	
	
}
