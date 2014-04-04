

function logout(){
	alert("logout");
	PostJSONQuery("clearSession.do",{},function(response){
		if(response){
			location="/SpringAop/jsp/login.jsp";
		}
	});
	
}