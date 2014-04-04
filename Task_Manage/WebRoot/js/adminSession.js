function adminSessionCheck(prx)
{
	$.ajax({
		url: serviceContextPath()+'servlet/Service?'+Math.random(),
		type:'post',
		dataType:'json',
		data:'{"method":"adminAction","type":"AdminManager","req":{"action":"sessionCheck","loginName":"","loginPswd":""}}',
		success:function(response){
			//alert(response.loginRst +":"+ response.loginMsg);
			if(response.loginRst!="0")
			{
				alert(response.loginMsg);
				parent.location.href = prx+"admin/login.html";
			}
		},
		error:function(){
			alert("登陆失败，请求失败！");
		}
	});
}

function adminLogout(prx)
{
	$.ajax({
		url: serviceContextPath()+'servlet/Service?'+Math.random(),
		type:'post',
		dataType:'json',
		data:'{"method":"adminAction","type":"AdminManager","req":{"action":"logout","loginName":"","loginPswd":""}}',
		success:function(response){
			//alert(response.loginRst +":"+ response.loginMsg);
			if(response.loginRst=="0")
			{
				parent.location.href = prx+"admin/login.html";
			}
		},
		error:function(){
			alert("登陆失败，请求失败！");
		}
	});
}