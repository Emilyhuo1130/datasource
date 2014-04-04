<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<title>用户信息</title>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
		<link type="text/css" rel="stylesheet" href="../../css/table.css" />
		<link href="../../css/sample.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="../../js/jquery-1.7.2.js"type="text/javascript"></script>
		<script language="javascript" src="../../js/common.js" type="text/javascript"></script>
		<script language="javascript" src="../../js/ucs_manage.js" ></script>
	<script  type="text/javascript">
	<%
	String userAccount=(String)(session.getAttribute("userAccount"));
	%>
	var userAccount="<%=userAccount%>";//转成字符串
		var current_page=1;
		var totalpages=5;
		var year=new Date().format("yyyy");
		
	$(document).ready(function(){
		var condition_init={"userAccount":userAccount,"userName":"","starTime":year+"-01-01","endTime":year+"-12-31"};
		totalpages=getTotalPages(condition_init);//获取总页数 */
		pagination("current_page",current_page);//显示页码  点哪一页就显示哪页的数据
		conditionSearch(current_page);//根据条件查询
		setPageNum($("#current_page").val(),totalpages-1);

	
	});
	
	
 	//获取总页数
	function getTotalPages(condition_json){
 		if(condition_json.userName=="---请选择---"){
 			condition_json.userName="";
 		}
		var totalpage=1;
		$.ajax({  
	        url :"do_getadminpages.do",  
	        type : "post",  
	        dataType:"json",  
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{ 
					"userName":"",
					"userAccount":"",
					"pageSize":"10" 
				  }, 
	        success : function(response) {  
	        	totalpage=response.userpage;
	        	$("#total_page").html(totalpage-1);
	        	
	        },  
	        error:function(XmlHttpRequest,textStatus, errorThrown)
            {
            }
	    }); 
	       		 return totalpage;//返回总页数
		
	}
 	
				//按钮查询
			function conditionSearch(current_page){
					
							var condition_json_startTime=year+"-01-01";
							var condition_json_endTime=year+"-12-31";
							var condition={"userAccount":userAccount,"userName": $("#userName option:selected").val(),"starTime":condition_json_startTime,"endTime":condition_json_endTime};
							paging(current_page,condition);
							
							
						
					}
	
	
	//删除用户
	function deleteUser(id,index){
		var req_json={"action":"do_deleteUser.do","data":{}};
		if(confirm("确定要删除用户吗？")){
			$.ajax({  
		        url :req_json.action,  
		        type : "post",  
		        dataType:"json",  
		        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
		        data :{"id":id}, 
		        success : function(response) {  
			      	location.reload();
		        },  
		        error : function(data) {  
		            //alert("----error-----!"+JSON.stringify(data));  
		        }  
		    });
		}{
			
		};
	 
	}
	//分页
	function  paging(current_page,condition_json){
		var req_json={"action":"do_findUser.do","data":{}};
		
		$.ajax({  
	        url :req_json.action,  
	        type : "post",  
	        dataType:"json",  
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{ "userName":condition_json.userName,
	        		"uesrAccount":condition_json.userAccount,
					"pageSize":"10",
					"page":current_page
				  }, 
	        success : function(response) {  
	     		//alert(JSON.stringify(response));
	        	$("table tbody tr td").html("");
	         	var taskInfo=response.userinfo;
	         	taskInfo.forEach(function(e,i){
	        				$("table tbody tr:eq("+i+") td:eq(0)").html((parseInt(current_page)-1)*10+i+1);
	        				$("table tbody tr:eq("+i+") td:eq(1)").html(e.name);
	        				$("table tbody tr:eq("+i+") td:eq(2)").html(e.userAccount);
	        				$("table tbody tr:eq("+i+") td:eq(3)").html(e.phoneNumber);
	        				$("table tbody tr:eq("+i+") td:eq(4)").html("<a  href='"+encodeURI("userDetails.jsp?id="+e.id+"&userName="+e.name+"&userAccount="+e.userAccount+"&phoneNumber="+e.phoneNumber+"&password="+e.password)+"';> 修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deleteUser("+e.id+","+i+");'>删除</a>"); 
	        				/* <a  href='"+encodeURI("userDetails.jsp?id="+e.id+"&userName="+e.name+"&userAccount="+e.userAccount+"&phoneNumber="+e.phoneNumber+"&password="+e.password)+"';> */
	        	//设置当前页页码的样式  
    	    	$("#page"+current_page).css("background-color","#eeeeff");
	         	$("#page"+current_page).siblings().css("background-color","#ccccee");
	         	
	         	 //项目列表代写    重复的删除掉 数组扩容 新建一个数组 每放一个进去就与新数组中每一个元素比较，不同就放入，有相同的就不放
	        	$("#userName").append("<option value="+e.name+">"+e.name+"</option>");
	        	//$("#userAccount").append("<option value="+e.userAccount+">"+e.userAccount+"</option>");

	        	});  
	         	distinctOption("#userName");
	         	//distinctOption("#userAccount");
	        	         	
	        },  
	        error:function(XmlHttpRequest,textStatus, errorThrown)
            {
               // alert("do_findUser.do  error");
            }
	    }); 
	
		
	}

	//分页显示 	//首页  上一页  下一页 末页
	function  setPageNum(current_page,total_page){
		
		$("#first_page").click(function(){
			$("#current_page").html(1);
			conditionSearch(1);
		});
		$("#pre_page").click(function(){
			prepage(current_page,total_page);
			
		});
		$("#next_page").click(function(){
			//alert(JSON.stringify(condition));
			nextpage(current_page,total_page);
		});
		$("#last_page").click(function(){
			//alert(JSON.stringify(condition));
			$("#current_page").html(total_page);
			conditionSearch(total_page);
		});
	};
	//设置选择列表的被选定的项
	function setSelectedItem(id,index){
		setTimeout(function(){
			$("#"+id+" option:eq("+index+")").attr("selected","selected");
		},100);
	}
	</script>
	</head>

	<body style="background-image: url('../../images/bank.jpg')">
		<div  id="attendees" class="formFrame" style="height:450px;scrolling:none;">
			<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
			</div>
			<div style="border:1px solid #999999; width:958px;height:500px;"><!-- 框架边框 -->
			
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
				<div id="search" style="position:relative;left:30px;top:40px;">
				用户名：
						<select  name="username" id="userName" >
						<option value=""></option>
						</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <!--       账号：
			          <select  name="count" id="userAccount" >
			          		<option value=""></option>
			          </select> -->
	           
				<div style="position:relative;left:650px;">
						<button name="search" class="button" id="searchInfo" onclick="conditionSearch(1);">查询</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button name="addUser" class="button"  onclick="addUserInfo();">添加用户</button>
		</div>
	</div>
	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:50px;border-bottom:1px solid #888888;">
			<div style="position:relative;left:10px;top:70px;">
	
			<table style="width:940px;height:300px;" border=1 cellspacing=0 class="tablesorter" >
			<thead >
			
				<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>账号</th>
				<th>手机</th>
				<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width=10%></td>
					<td width=20%></td>
					<td width=25%></td>
					<td width=25%></td>
					<td width=20%></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
							</tr>
			
			
			</tbody>
			
			</table>
			 <!--分页-->
		 <div style="position:relative;left:10px;top:-15px;">
			 <div id="paginationArea" style="height:20px;"></div>
                <div id="pages">
                    <a href="javascript:;" id="first_page">首页</a>
        	        <a href="javascript:;" id="pre_page"> 上一页</a>
        	         <a href="javascript:;" id="current_page">1</a>
                    <a href="javascript:;" id="next_page">下一页</a>
                    <a href="javascript:;" id="last_page">末页</a>
                 	   总页数:<a href="javascript:;" id="total_page">1</a>
                	</div>  
                	</div>
                </div>
                </div>
                </div>
          </div><!-- 表单定位终 -->     
	</body>
</html>