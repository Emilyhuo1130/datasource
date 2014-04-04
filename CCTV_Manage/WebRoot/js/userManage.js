/***用户管理**/
//用户管理查询按钮获取入参
function searchUsers(page){
	var userName=$("#userName").val();
	var userAccount=$("#userAccount").val();
	var userLevel=$("#userLevel").val();
	if(userLevel=="--请选择--"){
		userLevel="";
	}
	if(page==0){
		page=1;
	}
	if((veriftyChar(userName))&&(veriftyChar(userAccount))){//验证通过 可以查询
		selectAllUsers(userName,userAccount,userLevel,page,10);
	}else{
		showAlertInfo("查询条件存在非法字符");
	}
	
}

//显示所有用户
function selectAllUsers(useName,userAccount,userLevel,page,pageSize){
	//alert(useName+userAccount+userLevel+page+pageSize);
	 var json = {
			 operatorName:useName,
			 operatorAccount:userAccount,
			 level:userLevel,
			 page:page,
			 pageSize:pageSize
	 };
	 //alert(JSON.stringify(json));
	PostJSONQuery("searchUser.do",json,function(response){
		//showAlertInfo(JSON.stringify(response));
		$('#totalPage')[0].innerHTML = 	response.pages;;
		$("table tbody tr td").html("");
			response.operatorInfo.forEach(function(e,i){
        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(page)-1)*10+i+1);
        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.operatorAccount);
        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.operatorName);
        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.phoneNumber);
        				$("table tbody tr:eq("+i+") td:eq(4)").html(e.operatorLevel);
        				$("table tbody tr:eq("+i+") td:eq(5)").html("<a href='javascript:deleteUserInfo("+e.operatorId+");'>删除</a> | <a href='modifyUser.jsp?id="+e.operatorId+"'>修改</a>"); 
        	}); 
        	
			if(response.operatorInfo.length==0){
	 					showAlertInfo("没有查找到符合条件的数据！"); 
			}
        	
		});
}
//获取所有监控组名称
function selectAllControlGroup(id){
	var json={};
	PostJSONQuery("getAllMonitorName.do",json,function(response){
		for(var i=0;i<response.length;i++){
			if(i%4==0){
				$("#controlGroup").append("</br>");
			}
			$("#controlGroup").append("<input value="+response[i]+" type='checkbox' name='group'/><span>"+response[i]+"</span>");
		}
		showOneUserInfo(id,response);//显示用户信息
		
	      
	});
}
//通过删除用户
function deleteUserInfo(id){
	$("#someinfo").html("是否真的删除这个用户");
	$(".test").fbmodal({
		cancelbutton: true
	}
	,function(callback){
     if(callback == 1){
    	 var json={operatorId:id};
 		PostJSONQuery("deleteUser.do",json,function(response){
 			if(response.deleteInfo){
 				setTimeout(function(){
 					showAlertInfo("删除成功");
  				},1000);
 				var page=parseInt($("#current_page").html());
 				searchUsers(page);
 			}else{
 				setTimeout(function(){
 					showAlertInfo("删除失败"); 
  				},1000);
 			}
 		});
    }
	if(callback == 2){
       
	    
    }
});  
	
	
}
//通过id找用户信息
function showOneUserInfo(id,groups){
	var json={operatorId:id};
	PostJSONQuery("searchUserById.do",json,function(response){
		
		response=response.findOperatorById;
	//	alert(JSON.stringify(response));
		$("#userId").val(response.operatorId);
		$("#userName").val(response.operatorName);
		$("#userAccount").val(response.operatorAccount);
		$("#userpassword").val(response.operatorPw);
		$("#phoneNumber").val(response.phoneNumber);
		$("#choseOperate").val(response.operatorLevel);
		$("#remark").val(response.remark);
		/**显示拥有的监控组**/
		var havegroup=response.monitorGroupNames;
		for(var i=0;i<havegroup.length;i++){
			for(var j=0;j<groups.length;j++){
				if(havegroup[i]==groups[j]){
					$("input[value='"+havegroup[i]+"']").attr('checked','checked');
					break;
				}
			}
		}
		
		var power=$("#choseOperate").val();
		showgroups(power);
		
	      
	});
}


//保存或者修改用户信息
function saveUserInfo(){
	$("#someinfo").html("是否确定真的保存？");
	$(".test").fbmodal({
		cancelbutton: true
	}
		,function(callback){
		    if(callback == 1){
		    	tosaveUserInfo();
		    }
			if(callback == 2){
		          
		    }
	});  
	
}

function tosaveUserInfo(){
	var userID=$("#userId").val();
	var userName=$("#userName").val();
	var userAccount=$("#userAccount").val();
	var operatorpw=$("#userpassword").val();
	var phoneNumber=$("#phoneNumber").val();
	var remark=$("#remark").val();
	var userLevel=$("#choseOperate").val();
	
	var controlGroup = "";
     $("input:checked").each(function(){  
    	 	controlGroup += $(this).val()+"#";
      });
	if(userLevel=="超级操作员"){
		controlGroup="";
	}
	//alert(controlGroup);
	if(operatorpw.length==0||userName.length==0||userAccount.length==0||phoneNumber.length==0){
		showAlertInfo("请填写完整信息");
	}else{
		var flag=(veriftyChar(operatorpw))&&(veriftyChar(userName))&&(veriftyChar(userAccount))&&(veriftyChar(remark))&&(veriftyPhone(phoneNumber));
		//验证普通操作员至少选一个监控组
		var pv=false;
		if(userLevel=="普通操作员"){
			if (controlGroup.length==0){
				pv=true;
			}
		}
		if(pv){
			setTimeout(function(){
				showAlertInfo("请至少选择一个监控组");
				},600);	
		}else{
			if(flag){//信息符合规则 提交发请求
			var json={
					operatorId:userID,
					operatorName:userName,
					operatorAccount:userAccount,
					operatorPw:operatorpw,
					phoneNumber:phoneNumber,
					operatorLevel:userLevel,
					remark:remark,
					monitorGroups:controlGroup
					
			};
			//alert(JSON.stringify(json));
			//验证手机和账户是否存在
			if(userID=null||userID.length==0){//新加的用户需要验证账户和手机号码是否存在
				//验证账号手机号唯一
				var json2={
						operatorAccount:userAccount,
						phoneNumber:phoneNumber
						};
				PostJSONQuery("verifyAccountandPone.do",json2,function(response){
					//alert(response);
						if(response=="1"){//1 账号名重复 2：手机重复
							setTimeout(function(){
										showAlertInfo("保存失败,账号名重复");
					  				},600);
						}else if(response=="2"){
							setTimeout(function(){
										showAlertInfo("保存失败,手机号码重复");
					  				},600);
						}else if(response=="3"){
							setTimeout(function(){
										showAlertInfo("保存失败,账号名和手机号码重复");
					  				},600);
						}else if(response=="4"){
							PostJSONQuery("updateUser.do",json,function(response){
								if(response){
									setTimeout(function(){
										showAlertInfo("保存成功");
					  				},600);
									setTimeout(function(){
										reback();
					  				},2000);
								}else{
									setTimeout(function(){
										showAlertInfo("保存失败");
					  				},1000);
									
								}
							});
						}
					});
				
				
			}else{//更新用户 需要验证被更新的人的手机号码是否与其他人相同
				//TODO
				var jsonotherphone={
					operatorId:json.operatorId,
					phoneNumber:json.phoneNumber
					};
					//alert(JSON.stringify(jsonotherphone));
				PostJSONQuery("veriftyotherphoneNumber.do",jsonotherphone,function(response){
					if(response){//返回true 表示不存在相同的手机号
						PostJSONQuery("updateUser.do",json,function(response){
							if(response){
								setTimeout(function(){
									showAlertInfo("保存成功");
				  				},600);
								setTimeout(function(){
									reback();
				  				},2000);
							}else{
								setTimeout(function(){
									showAlertInfo("保存失败");
				  				},1000);
								
							}
						});
					}else{
						setTimeout(function(){
									showAlertInfo("此手机号码已经被其他人使用，请换一个手机号码！");
				  				},600);
					}
				});
				
				
			}
			
			
		}else{//存在错误信息
			showAlertInfo("存在不合符规范的字符");
		}
		}
		
		
		
	}
}


//获取所有监控组名称
function getAllControlGroup(){
	json={};
	PostJSONQuery("getAllMonitorName.do",json,function(response){
		for(var i=0;i<response.length;i++){
			if(i%4==0){
				$("#controlGroup").append("</br>");
			}
			$("#controlGroup").append("<input value="+response[i]+" type='checkbox' name='group'/><span>"+response[i]+"</span>");
		}
		var power=$("#choseOperate").val();
		showgroups(power);
	});
}
/***超级操作员不显示监控组**/
function showgroups(power){
	if(power=="超级操作员"){
		$("#controlGroup").slideUp("slow");
		$("#AllgroupNames").html("");
	}else if(power=="普通操作员"){
		$("#AllgroupNames").html("监控组:");
		$("#controlGroup").slideDown("slow");
	}
}




