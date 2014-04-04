<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html >
	<head>
	<title>后台管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		 <link type="text/css" rel="stylesheet" href="css/validate.css" /> 
		 <link type="text/css" rel="stylesheet" href="css/client.css" />
		 <link type="text/css" rel="stylesheet" href="css/dpcp.css" />
		 <link type="text/css" rel="stylesheet" href="css/jquerymsg.css" />
		 <link type="text/css" rel="stylesheet" href="css/table.css" />
		 <link href="css/sample.css" rel="stylesheet" type="text/css" />
		  <style>
		  	html{	
		  	overflow-y:scroll;
		  	overflow-x:scroll;
		  	}
		  </style>
		 <script language="JavaScript" src="../js/jquery-1.4.4.min.js"></script>
		 <script language="JavaScript" src="../js/jqueryPromit.js"></script>
		 <script language="JavaScript" src="../js/jqueryBlockUI.js"></script>
		 <script language="JavaScript" src="../js/utils.js"></script>
		 <script language="JavaScript" src="../js/gk.js"></script>
		<style>
		table #selectTable tr td{
			height:20px;
		}
		#selectTable tr:hover{
			background-color:#ccccff;
		}
		
			html { overflow-y: scroll;overflow-x: scroll; }
		</style>
		
		
		
		
		  <script type="text/javascript">
		  $(document).ready(function(){
			 
			  $("#client_position").focus(function(){
			 	$("#select_clientCode").slideDown("slow");
			  });
			   $("#client_position").blur(function(){
			 	$("#select_clientCode").slideUp("slow");
			  });
			   
		  		$("#select_clientCode table tr td div").click(function(){
		  			
		  			$("#client_position").val($(this).html());
		  			fillIp();
		  			
		  		});
		  		//给演示时间付初始值60秒
		  		$("#MutiTime").val(30);
		  		$("#SingleTime").val(10);
		  		
		  
		  		//判断输入数据是否有空值
			  $("#tijiao").click(function(){
				  	if($("#client_position").val()!=""&& $("#Local_ip").val()!=""){
				  		var  clientId=$("#Local_ip").val();
						var  positionCode=$("#client_position").val();
						
						var  position_json={"Local_ip":clientId,"cliend_id":positionCode};
						PostJSONQuery_positionCode(position_json,function(response){
							console.info("response="+JSON.stringify(response));
							if(response.info=="success"){
								console.info("*************进行验证************");
								var validate_flag = validate_form();
								console.warn(validate_flag);
								if(validate_flag){
									//使用ajax提交表单
									$.createDialog("确定要提交该配置吗?",function(){
										$.msg("提交成功，3秒跳转...");
										setTimeout(function(){
											postJsonQuery_addCfg("");
										},3000);
									
									});
								
									
								}else{
									
								}
								$("#error_info").slideUp("slow");
							}else{
								$("#error_info").slideDown("slow");
								
							}
							
						});
				  		
				  	}else{
				  		
				//$.msg("请确保填入全部信息！");  	
				$.msg({
					type: "warning",
					msg: "请确保填入全部信息！"
				});

				  return false;
				  	}
				  	
				  
			  });
		  		
		  		//控制路径输出   不同的效果给出不同的路径
		  		$("#MultiEfect").change(function(){
		  			
		  			var json_efect=$("#MultiEfect option:eq("+$(this).val()+")").html();
		  			var json_url={effectname:json_efect};
		  			PostJSONQuery(json_url, function(response){
		  				var infolist = response.infoList;
		  				$("#MutiResFolder option").remove();
		  				infolist.forEach(function(e){
		  					
		  					$("#MutiResFolder").append("<option value="+e.value+">"+e.name+"</option>");
		  					
		  				});
		  				
		  				
		  			});
		  			
		  		});
		  		
		  		/********************************* 在多幅特效和单幅特效之间进行切换*************************************************/
		  		$("fieldset").each(function(i,e){
		  			var $e = $(e);
		  			$e.find(".efectType").each(function(index,element){
			  			var $e = $(e);
			  			$e.change(function(){
				  			console.log("******change*******");
				  			toggleType(i);
				  			
				  		});
			  		});
		  			
		  		});
		  		//显示左侧列表浮动层
		  		showFloatInfo();
		  		
		  		//自动填入ip
		  		$("#client_position").blur(function(){
		  			fillIp();
		  			
		  		});
		  		
		  	
		  });
		 
		  //在多幅特效和单幅特效之间进行切换
		  function toggleType(){
			  //迭代feildset对象 对其中的每一个进行操作
				  var efectType =$(".efectType").val();
				  if(efectType=="多幅特效"){
					  $("#multiEfect").css("display","block").siblings("table").css("display","none");
				  }else if(efectType=="单幅特效"){
					  $("#singleEfect").css("display","block").siblings("table").css("display","none");
				  }else if(efectType=="视频"){
					  $("#video").css("display","block").siblings("table").css("display","none");
					  
				  };
		  }
		  
		  function fillIp(){
				console.info("clientId="+$("#client_position").val());
	  			getBindIp({"cliendId":$("#client_position").val()},function(response){
		  			console.info(response);
		  			$("#Local_ip").val(response);
		  		});
		  }
			
		  
		  </script>
	</head>
	
	<body  class="bgm" >
	<div id="part1">
  </div>
  
	<!-- 表单定位---------------------------------------------------------------------- -->
	<div id="sub_nav_long" style="margin-top: -10px;">
  		
  	</div>
  	<div ><!-- 左侧列表与右侧部分放到一个部分 -->
	<div id="form1" style="height:600px;border:1px solid #000000;">
	<fieldset id="group" style="position:relative;top:3%;"  class="fieldset">
	<legend style="" >基本信息设置:</legend>
	
	<table class="table_c_addCfg" style="position:relative;left:-16%;">
		<tr>
			<td>
			</td>
			<td> 客服端区位：</td>
			<td>
				  <input id="client_position" style="width:270px;" class="input_text" type="text" name="Client_id" value="${oneInfo.client.client_Id }"/>
				  
		    </td>
		</tr>
		<tr>
			<td>
			</td>
			<td> 客服端IP：</td>
		    <td>
					 <input id="Local_ip" style="width:270px;" class="input_text" type="text" name="Local_ip" value="" /><br/>
					 <input class="input_text" type="hidden" name="oldLocal_ip" value="${ oneInfo.client.local_ip }"/>
		    <div id="data_error" style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">IP格式有误</span></div>
		    </td>
		</tr>
	</table>
	
		<div id="error_info" style="display:none;background-color:#cceedd;position:relative;left:400px;border:1px black solid;width:390px;height:40px;"><span style="color:red;font-weight:bold;">区位号或者客户端ip与现有的冲突，请先将设备信息中该区位号或者ip的配置删除后再进行配置</span></div>
		
		</fieldset>
		
		<!-- *****************************隐式div*************************** -->
		
		<div id="select_clientCode" style="position:relative;top:4%;display:none;background-color:#eeeeff;">
		<div id="table1">
		<table style="margin:0px;padding-left:25px;pdding-right:25px;">
			<tr>
				<td><div class="square">13</div></td>
				<td><div class="square">12</div></td>
				<td><div class="square">11</div></td>
				<td ><div class="square">10</div></td>
				<td id="td5"><div class="square">9</div></td>
				<td><div class="square">8</div></td>
				<td><div class="square">7</div></td>
				<td><div class="square">6</div></td>
				<td><div class="square">5</div></td>
				<td><div class="square">4</div></td>
				<td ><div class="square">3</div></td>
				<td id="td12"><div class="square">2</div></td>
				<td><div class="square">1</div></td>
				
			</tr>
		</table>
		</div>
			
			<div id="pre_div">
				<div id="serveplat1"><span style="line-height:40px;color:#ffffff;">服务台</span></div>
				<div id="serveplat2"><span style="line-height:40px;color:#ffffff;">服务台</span></div>
			</div>
			<div id="table2">
			<table style="margin:0px;padding-left:25px;">
			<tr>
				<td><div class="square">14</div></td>
				<td><div class="square">15</div></td>
				<td><div class="square">16</div></td>
				<td><div class="square">17</div></td>
				<td id="td18"><div class="square">18</div></td>
				<td><div class="square">19</div></td>
				<td><div class="square">20</div></td>
				<td><div class="square">21</div></td>
				<td><div class="square">22</div></td>
				<td><div class="square">23</div></td>
				<td><div class="square">24</div></td>
				<td id="td25"><div class="square">25</div></td>
				<td><div class="square">26</div></td>
				
			</tr>
		</table>
		</div>
		</div>
		<!-- ********************************************************************************** -->
		
	<fieldset id="group1" class="fieldset" style=" height:220px;position:relative;top:5%;">
	<legend style="">图片演示效果：</legend>
		<!--*******************************  演示效果类型****************************************-->
	<div style="position:relative;left:40%;">
			<input type="button" class="button" name="addEfect" value="添加演示效果" style="position:relative;left:37%;width:128px;height:30px;font-size:.3em;color:#000000;" onclick="addEfectToTable();"/>
	</div>
	<table class="table_clentCfg" style="position:relative;left:-18%;">
		<tbody>
		<tr>
			<td>演示效果类型</td>
			<td>
				<select id="efectType" style="width:270px;" class="efectType">
					<option value="">请选择</option>
					<option value="多幅特效" >多幅特效</option>
					<option value="单幅特效" >单幅特效</option>
					<option value="视频" >视频</option>
				</select>
			</td>
		</tr>
		</tbody>
		
	</table>
	<!-- 多幅 单幅   视频-->
	<!-- 多幅 -->
	<div id="efects">
	
	 	<table id="multiEfect" style="display:none;position:relative;left:-16%;">
		<tr>
			 <td></td> 
			 <td>特效类型：</td> 
			<td>
				    <select id="MultiEfect" name="PicShow" style="width:270px;" value="${oneInfo.client.pic_m.picShow }" >
				    <c:forEach items="${oneInfo.effects.multi_Effects}" var="e" begin="0">
						<option value="${e.value}">${e.name}</option>
						
					<script>
							
							 if("${e.name}"=="淡入淡出"){
								 var req_json={
										 "effectname":"淡入淡出"
										 
								 };
								
								 event_fadeInOut_url(req_json);
							} 
							 if("${e.name}"=="水墨画"){
								 var req_json={
										 "effectname":"水墨画"
										 
								 };
								 
									event_shuimohua_url(req_json);
								} 
						</script>
						<script>
							if("${oneInfo.client.pic_m.picShow}"=="${e.value}"){
								$("#MultiEfect option:eq("+"${e.value}"+")").attr("selected","selected");
								
							}
						
						</script>
					</c:forEach>
					</select><br/>
					<div id="novalue_info" style="display:none;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">请选择特效</span></div>
			</td>
			
		</tr>
		<tr>
			<td>
			
			</td>
			
			<td>图片路径:</td> 
			<td>
				    <select id="MutiResFolder" style="width:270px;" name="MutiResFolder" >
				  <c:forEach  items="${oneInfo.effects.pic_Mulit_url}" var="e" >
				  
					<option value="${e.value}">${e.name}</option>
					
					</c:forEach>  
				</select>
					<script>
						$("#MutiResFolder option").each(function(i,e){
							if(e.value=="${oneInfo.client.pic_m.resFolder}"){
								
								$("#MutiResFolder option:eq("+i+")").attr("selected","selected");
								
							}
							
							
						});							
						
					
					</script>
			</td>
				
		</tr>
		<tr hidden="hidden">
			<td>
			</td>
				<td>随机播放：</td>
			<td>
				    <select id="MultiRandom" name="MutiRandom"  value="${oneInfo.client.pic_m.random }" >
						<option value="true">true</option>
						<option value="false">false</option>
					</select><br />
						<script>
							if("${oneInfo.client.pic_m.random }"=="true"){
								$("#MultiRandom option:eq(0)").attr("selected","selected");
							}else{
								$("#MultiRandom option:eq(1)").attr("selected","selected");
							};
						
						</script>
			</td>
		</tr>
		<tr>
			<td>
				
			</td>
				<td>演示时间：</td>
			<td>
					<input id="MutiTime" class="input_text" style="width:200px;" type="text"  name="MutiTime" value="30" onclick="validatePlaytime('MutiTime')"/>
					<span style="color:black;">(秒)</span>
					<div  style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">必须是大于等于5秒的整数</span></div>
			</td>
		</tr>
	</table> 
		
		
		
		
		
<!-- 单幅 -->
	<table id="singleEfect" style="display:none;position:relative;left:-16%;">
			<tr>
			<td>
			
			</td>
			
			<td>图片路径:</td> 
			<td>
				    <select id="singleResFolder" style="width:270px;" name="singleResFolder" >
				  <c:forEach  items="${oneInfo.effects.pic_Single_url}" var="e" >
				  
					<option value="${e.value}">${e.name}</option>
					
					</c:forEach>  
				</select>
					<script>
						$("#singleResFolder option").each(function(i,e){
							if(e.value=="${oneInfo.client.pic_s.resFolder}"){
								
								$("#singleResFolder option:eq("+i+")").attr("selected","selected");
								
							}
							
							
						});							
						
					
					</script>
			</td>
				
		</tr>
		<tr>
			<td>
				
			</td>
				<td>演示时间：</td>
			<td>
					<input id="singleTime" class="input_text" style="width:150px;" type="text"  name="singleTime" value="10" onclick="validatePlaytime('singleTime')"/>
					<span style="color:black;">秒(单张演示时间)</span>
					<div  style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">必须是大于等于5秒的整数</span></div>
			</td>
		</tr>

	</table>
	
	<table id="video" style="display:none; position:relative;left:-16%;">
		<tr>
			<td>
			
			</td>
			
			<td>视频路径:</td> 
			<td>
				    <select id="videoResFolder" name="videoResFolder" style="width:270px;" >
				  <c:forEach   items="${oneInfo.effects.video_url}" var="e" >
				  
					<option value="${e.value}">${e.name}</option>
					
					</c:forEach>  
				</select>
					<script>
					
					</script>
			</td>
				
		</tr>
	</table>
	</div>
	</fieldset>
	
	<!-- 隐式table存放演示效果对象   初始化的数据迭代放入  -->
		<table id="selectTable_json" hidden="hidden" style=" 
			position:absolute;
			z-index:10;
			word-break:break-all;font-size:.75em;
			left:1%;top:118px;
			width:18%;text-align:center;
			border-color:#eeeeff;border-bottom:0px solid #eeeeee;"  border="1"  >
			<thead >
				<tr>
					<th width="20%">播放顺序</th>
					<th width="20%">演示效果</th>
					<th width="60%">json对象</th>
				</tr>
			</thead>
			<tbody >
			</tbody>
		</table> 
		
		
		
		<!-- 表格存放配置好的机器序号及播放顺序 -->
	
		<table id="selectTable" 
		style="position:absolute;
		word-break:break-all;
		font-size:.75em;
		left:3%;
		top:9.5%;
		width:18%;
		text-align:center;
		border-color:#eeeeff;
		border-bottom:0px solid #eeeeee;"  border="1"  >
			<thead style="background-color:#fff;">
				<tr >
				    <th width="35px">播放顺序</th>
					<th width="60px">添加特效</th>
					<th width="25px">操作</th>
				</tr>
			</thead>
			<tbody >
			</tbody>
		</table> 
	
	
	<!--提交-->
	
		<div style="margin:5px;position:relative;top:5%;" id="submit_button" >
			<input id="chongzhi" class="button" style="text-align:center;" class="button" name="reset" value="重置" onclick="reset_form();"/>
			
			<input id="tijiao" class="button"  style="text-align:center;" class="button" name="tijiao" value="提交" />
			
		</div>
	</div>
	</div><!-- 表单定位终 -->

	
	<div id="part3" >
 		 UCS Holdings CopyRight 2013
	</div>
	
	</body>
</html>
