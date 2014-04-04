<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>添加项目</title>
	
    <meta   http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link   type="text/css" rel="stylesheet" href="../../css/dpcp.css" />
    <link   type="text/css" rel="stylesheet" href="../../css/table.css" />
         <style type="text/css">
    	.suc_info{
    			position:absolute;
    			z-index:100;
	        	width:400px;
	        	display:none;
	        	height:60px;
	        	left:25%;
	        	top:25%;
	        	border:3px solid purple;
	        	background-color:#c7edcc; 
    	
    	
    	}
    	.suc_info span{
    		position:relative;
    		left:33%;
    		top:20%;
    		
    		color:purple;
    		text-align:center;
    		vertical-align:middle;
    		font-weight:bold;
    		font-size:2.5em;
    	}
    </style>
	<script language="javascript" src="../../js/jquery-1.7.2.js" type="text/javascript"></script>
	<script language="javascript" src="../../js/common.js" type="text/javascript"></script>
	<script language="javascript" src="../../js/ucs_manage.js" ></script>
    <script type="text/javascript">
      
	 
	
	  //---------------初始启动
	  		$(document).ready(function(){
	  			$(document.body).append("<div id='suc_info' class='suc_info'><span >添加成功</span></div>");
	  			validateForm();
				goback();
			});
	  
	  
	  //保存操作
	  function save(){
				var req_json={"action":"do_addProject.do","data":{"project":{"projectName":$("#projectName").val(),"projectState":$("#projectState").val(),"userPw":$("#userPw").val(),"projectRemark":$("#projectRemark").val()}}};
				if($("#projectState").val()!=""&&$("#projectType").val()!=""){
					$.ajax({  
				        url :req_json.action,  
				        type : "post",  
				        async: false,
				        dataType:"json",  
				        contentType: "application/x-www-form-urlencoded;charset=utf-8", 
				        data :{ "projectName":$("#projectName").val(),
								"projectState":$("#projectState").val(),
								"projectType":$("#projectType").val(),
								"projectRemark":$("#remark_info").val()
							  }, 
				        success:function(response) { 
				        	$("#suc_info").fadeIn("slow");
				        	setTimeout(function(){
			  					location.href="lookItem.jsp";
			  				},1500);
				        },  
				        error:function()
			            {
			                
			            }
				    }); 
				}else{
					
				}
			
				       
					
	  }
	  //返回
	  function goback(){
		  $("#cancel").click(function(){
			  
			  location.href="lookItem.jsp";
			  
		  });
	  }
	  //跳转页
			function  goPage(ifSuccess){
							 window.location.href="lookItem.jsp";
					}
	  
	  //表单验证
	  function validateForm(){
			$("#save").click(function(){
				 var flag1= validateData(/^\s[^\s]+|[\S]+$/,$("#projectName").val(),"projectName_error","projectName_error","项目名称不能为空","项目名称不能为空");
				 var flag2= validateData(/^\s[^\s]+|[\S]+$/,$("#projectState").val(),"projectState_error","projectState_error","项目状态不能为空","项目状态不能为空");
				 var flag3= validateData(/^\s[^\s]+|[\S]+$/,$("#projectType").val(),"projectType_error","projectType_error","项目类型不能为空","项目类型不能为空");
				  if(flag1&&flag2&&flag3){
					  save();
				  }
			});
	  }
 </script>
 </head>
 
 
<body	style="background-image:url('../../images/bank.jpg')">
<table border="0" cellspacing="0">
				<tr>
					<td width=100% height=23 align=left class='page-title' >
						<img src="../../images/page_title.gif" alt="title" width="17"
							height="12" align="middle"/>

						&nbsp; &nbsp;添加项目
					</td>
					<td width=592 align="left"></td>
				</tr>
			</table>
<div  id="attendees" class="formFrame" style="height:450px;">
	<div id="lineStyle" style="background-image:url('../../images/table_title.png');width:960px;height:30px;">
		</div>
			<div style="border:1px solid #999999; width:958px;height:500px;"><!-- 框架边框 -->
			<div style="width:2px;height:1500px;position:absolute;left:-500px;top:300px;z-index:10;"><!-- 左边线 -->
			<img style="-webkit-transform: rotateZ(90deg);" src="../../images/line_frame.png"/>
			</div>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<div style="position:relative;left:0px;width:960px;height:100px;border-bottom:1px solid #888888;">
	
		</div>
		
<table   width="700"  class='table_edit'>
<tr >
     <td> </td>
     <td align="left">
      带
      <font color="red">&nbsp;*&nbsp;</font>为必填项
     </td>
 
   </tr>
   
    <tr>
    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>项目名称</td>
    <td >
        <input name="projectName" size="30" id="projectName" type="text"  />
        <div id="projectName_error" style="display:none;width:180px;height:20px;background-color:#cceedd;border:1px black solid;"><span style="color:red;font-weight:bold;font-size:.75em;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名不能为空</span></div>
    </td>
</tr>
<tr>
    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>项目状态</td>
    <td >
       <select name="projectState" id="projectState" style="width:185px" >
      		 <option value="">---请选择---</option>
        	<option value="未开始">未开始</option>
        	<option value="调研阶段">调研阶段</option>
        	<option value="开发阶段">开发阶段</option>
        	<option value="测试阶段">测试阶段</option>
        	<option value="验收阶段">验收阶段</option>
        	<option value="已完成">已完成</option>
        </select>
       <div id="projectState_error" style="display:none;width:180px;height:20px;background-color:#cceedd;border:1px black solid;"><span style="color:red;font-weight:bold;font-size:.75em;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目状态不能为空</span></div>
    </td>
    </tr>
    <tr>
      <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>项目类型</td>
    <td >
        <select name="projectType" id="projectType" style="width:185px" >
        	 <option value="">---请选择---</option>
        	<option value="售前项目">售前项目</option>
        	<option value="内部支撑项目">内部支撑项目</option>
        	<option value="立项项目">立项项目</option>
        	<option value="产品设计项目">产品设计项目</option>
        	<option value="其他项目">其他项目</option>
        	
        </select>
         <div id="projectType_error" style="display:none;width:180px;height:20px;background-color:#cceedd;border:1px black solid;"><span style="color:red;font-weight:bold;font-size:.75em;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名不能为空</span></div>
    </td>
</tr>


 <tr>
    <td align="right" valign="middle" width=192> <font color="#FF0000"> *</font>备注</td>
    <td >
        <textarea name="projectRemark"  id="remark_info" rows="10" cols="80"  style="width:500px;height:200px;" dataType="LimitB" min="1" max="2000" msg="必须在[1,2000]个字节之内" ></textarea>
    	<span style="color:red;" id="s_projectRemark"></span>
    </td>
</tr>
    

<tr>
     <td align="middle"  colspan="2" >
         <button  name="save" id="save" >提交</button>
         <button  name="cancel" id="cancel" >返回</button>
    </td>
</tr>
 
</table>
	</div>
</div><!-- 边框定位终点 -->
</body>
</html>