//保存管理员密码
function saveadminInfo(){
	var adminAccount=$("#adminAccount").val();
	var oldpassword=$("#oldpassword").val();
	var newpassword =$("#newpassword").val();
	var newpasswordagain =$("#newpasswordagain").val();
	
	if(newpassword==newpasswordagain){//两次密码相同
		var admin={
			adminAccount:adminAccount,
			adminPw:oldpassword,
			adminNewPw:newpassword
		};
		PostJSONQuery("updateAdminInfo.do",admin,function(response){
			if(response){
				showAlertInfo("修改成功，请牢记密码！");
			}else{
				showAlertInfo("原密码不正确，修改失败！");
			}
		
        	
		});
	}else{//两次密码不同
		showAlertInfo("两次输入的新密码不一致，请重新输入。");
	}
	
	
	
	
}
