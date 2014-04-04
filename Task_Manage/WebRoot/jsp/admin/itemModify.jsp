<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>项目修改</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
     <link type="text/css" rel="stylesheet" href="../../css/table.css" />
      <style type="text/css">
    	.suc_info{
    			position:absolute;
    			z-index:100;
	        	width:400px;
	        	display:none;
	        	height:40px;
	        	left:25%;
	        	top:25%;
	        	border:3px solid purple;
	        	background-color:#c7edcc; 
    	
    	
    	}
    	.suc_info span{
    		position:relative;
    		left:5%;
    		top:20%;
    		
    		color:purple;
    		text-align:center;
    		vertical-align:middle;
    		font-weight:bold;
    		font-size:1.5em;
    	}
    </style>
    <script language="javascript" src="../../js/jquery-1.7.2.js" type="text/javascript"></script>
	<script language="javascript" src="../../js/common.js" type="text/javascript"></script>
	<script language="javascript" src="../../js/ucs_manage.js" ></script>
    <%String id=request.getParameter("id"); %>
    <script type="text/javascript">
		var id="<%=id%>";
		var time_year=new Date().format("yyyy-MM-dd hh:mm:ss");
		
		$(document).ready(function(){
			$(document.body).append("<div id='suc_info' class='suc_info'><span >更新成功</span></div>");
			$(document.body).append("<div id='error_info' class='suc_info'><span >项目名称、状态、类型不能为空</span></div>");
			initData(id);
			 save();
			
		});
		
	
	
	//初始化加载数据
	function initData(id){
		var req_json={"action":"do_findProjectsByID.do",};
		$.ajax({  
	        url :req_json.action,  
	        type : "post",  
	        dataType:"json", 
	        async:false,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        data :{ "id":id }, 
	        success : function(response) {  
	  			var item=response.project;
	  			$("#projectName").val(item.projectName);
	  			$("#item_status").val(item.projectState);
	  			//$("#item_type").val(item.projectState);
	  			$("#item_type option").each(function(i,e){
	  				var $e=$(e);
	  				//alert($e.val()+","+item.projectState);
	  				if($e.val()==item.projectType){
		  				$("#item_type option:eq("+i+")").attr("selected","selected");
		  			}
	  			
	  			});
	  			$("#item_status option").each(function(i,e){
	  				var $e=$(e);
	  				//alert($e.val()+","+item.projectType);
	  				//alert($e.val()+","+item.projectState);
	  				if($e.val()==item.projectState){
		  				$("#item_status option:eq("+i+")").attr("selected","selected");
		  			}
	  			});
	  			$("#remark").val(item.projectRemark);
	  			
	  			
	        },
	        error:function(XmlHttpRequest,textStatus, errorThrown)
            {
                //alert("admin/do_findProjectsByID.do无法进入success;");
            }
		});
	}
	//保存操作
	function  save(){
		$("#save").click(function(){
			if($("#projectName").val()!=""&&$("#item_status").val()!=""&&$("#remark").val()!=""){
				$.ajax({  
			        url :"do_updateProject.do",  
			        type : "post",  
			        dataType:"json",  
			        async:false,
			        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
			        data :{ "projectName":$("#projectName").val(),
			        		"projectState":$("#item_status").val(),
			        		"projectType":$("#item_type").val(),
			        		"projectRemark":$("#remark").val(),
			        		"id":id
			        	  }, 
			        success : function(response) {  
			        	$("#error_info").fadeOut("fast");
			        	$("#suc_info").fadeIn("slow");
		  				setTimeout(function(){
		  					location.href="lookItem.jsp";
		  				},1500);
			        },
			        error:function(){
		               // alert("admin/do_updateProject.do无法进入success;");
		            },
				});
			}else{
				$("#error_info").slideDown("slow");
			}
		
			
		});
		
	}
	//取消操作
	function cance(){
			location.href="lookItem.jsp";
	}
 	</script>
 	
 	</head>
 
 
<body	 style="background-image:url('../../images/bank.jpg')">
<table border="0" cellPadding="" cellspacing="0">
				<tr>
					<td width=100% height=23 align=left class='page-title'>
						<img src="../../images/page_title.gif" alt="title" width="17"
							height="12" >

						&nbsp; &nbsp;项目修改
					</td>
					<td width=592 align="left"></td>
				</tr>
			</table>
<table   width="700"  class='table_edit'>
<tr >
     <td> </td>
     <td align="left">
     
      <font color="red">&nbsp;*&nbsp;</font>带*号为必填项
     </td>
 
   </tr>
   
    <tr>
    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>项目名称</td>
    <td >
        <input name="itemName" size="30" id="projectName" type="text"  />
        <span style="color:red;" id="s_itemName"></span>
    </td>
</tr>
<tr>
    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>项目状态</td>
    <td >
        <select name="projectState" id="item_status" style="width:185px" >
        	<option value="未开始">未开始</option>
        	<option value="调研阶段">调研阶段</option>
        	<option value="开发阶段">开发阶段</option>
        	<option value="测试阶段">测试阶段</option>
        	<option value="验收阶段">验收阶段</option>
        	<option value="已完成">已完成</option>
        </select>
        <span style="color:red;" id="s_item_status"></span>
    </td>
</tr>
<tr>
    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>项目类型</td>
    <td >
       <select name="projectType" id="item_type" style="width:185px" >
        	<option value="售前项目">售前项目</option>
        	<option value="内部支撑项目">内部支撑项目</option>
        	<option value="立项项目">立项项目</option>
        	<option value="产品设计项目">产品设计项目</option>
        	<option value="其他项目">其他项目</option>
        	
        </select>
        <span style="color:red;" id="s_item_type"></span>
    </td>
</tr>


 <tr>
    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>备注：</td>
    <td >
        <textarea name="remark"  id="remark" rows="10" cols="80"  style="width:500px;height:200px;"  ></textarea>
    	<span style="color:red;" id="s_remark"></span>
    </td>
</tr>
    

<tr>
<td>
     <td align="left" valign="middle" colspan="2" >
    	 <button  id="save" >保存</button>
    	 <button  id="cance"  class="button" onclick="cance();">取消 </button>
    	 
    </td>
</tr>
 
</table>
</body>
</html>