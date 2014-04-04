<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html >
	<head>
	<title>后台管理系统</title>
		 <link type="text/css" rel="stylesheet" href="css/validate.css" /> 
		<style>
		table #selectTable tr td{
			height:20px;
		}
		#selectTable tr:hover{
			background-color:#ccccff;
		}
		html { overflow-y: scroll; }
		</style>
		
		 <script language="JavaScript" src="../js/jquery-1.4.4.min.js"></script>
		 <script language="JavaScript" src="../js/jqueryPromit.js"></script>
		 <script language="JavaScript" src="../js/jqueryBlockUI.js"></script>
		 <script language="JavaScript" src="../js/utils.js"></script>
		 <script language="JavaScript" src="../js/gk.js"></script>
		 
		  <script type="text/javascript">
		  <% String cfgstr = request.getParameter("config_Pis");%>
		
		  $(document).ready(function(){
			  var cfg_json = "<%=cfgstr%>";
				alert(cfg_json);
			  $("#client_position").focus(function(){
				  
			 	$("#select_clientCode").slideDown("slow");
				  
			  });
			   $("#client_position").blur(function(){
				  
			 	$("#select_clientCode").slideUp("slow");
				  
			  });
		  		$("#select_clientCode table tr td div").click(function(){
		  			$("#client_position").val($(this).html());
		  			
		  		});
		  		//给演示时间付初始值60秒
		  		$("#MutiTime").val(60);
		  		$("#SingleTime").val(60);
		  		//从服务端拿到左侧列表正的演示效果的内容并迭代放入表中
		  		putEfectDataToTable();
		  		
		  		//判断输入数据是否有空值或重复
			  $("#tijiao").click(function(){
				  	if($("#client_position").val()!=""&& $("#Local_ip").val()!=""
				  			&& $("#MutiTime").val()!=""&& $("#SingleTime").val()!=""){
				  		var  clientId=$("#Local_ip").val();
						var  positionCode=$("#client_position").val();
						
						var  position_json={Local_ip:clientId,cliend_id:positionCode};
						PostJSONQuery_positionCode(position_json,function(response){
							
							if(response.info=="success"){
								validate_form();
								if(validate_form()){
									//使用ajax提交表单  修改添加时同一个方法
									var localIp=$("#Local_ip").val();
									console.log("local_ip**********"+localIp);
									$.createDialog("确认更新?",function(){
										postJsonQuery_addCfg(localIp);
									});
								}
								$("#error_info").slideUp("normal");
							}else{
								$("#error_info").slideDown("normal");
								
							}
							
						});
				  		
				  	}else{
				  		
					$.msg("请确保已填写全部信息");				  		
				  return false;
				  	}
				  	
				  
			  });
		  		
		  		//控制路径输出
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
		  		
		  			
		  });
		 
		  //在多幅特效和单幅特效之间进行切换
		  function toggleType(){
			  console.log("********切换************");
			  //迭代feildset对象 对其中的每一个进行操作
				  var efectType =  $(".efectType").val();
				  if(efectType=="多幅特效"){
					  $("#singleEfect").css("display","none");
					  $("#multiEfect").css("display","block");
					  
				  }else if(efectType=="单幅特效"){
					  $("#multiEfect").css("display","none");
					  $("#singleEfect").css("display","block");
				  };
				  
			  
			  
		  }
		//从服务端拿到左侧列表正的演示效果的内容并迭代放入表中
		function  putEfectDataToTable(){
			
			
			//拿到数据
			console.log("***拿到的json字符串***"+'${config_Pis}');
			
			//将json字符串解析成json对象
			var infoJson = eval('('+'${config_Pis}'+')');
			var multiEfects =infoJson.client.pic_m; //多幅特效
			var singleEfects = infoJson.client.pic_s;//单幅特效
			
	 		multiEfects.forEach(function(e,i){
				$("#selectTable").append("<tr id='tr"+(i)+"'>"+
						"<td></td>"+
						"<td><a  onmouseover='showFloatInfo("+(i)+");'><span hidden='hidden'>多幅</span>查看详情"+"</a></td>"+
						"<td>"+
							"<a href='javascript:deleteThisTr("+(i)+","+0+");' >清除</a>"+
						"</td>"+
					"</tr>");
				
				
			//隐式的table
			//将json对象放入多幅隐藏区
				$("#selectTable_json_multi tbody").append("<tr id='tr"+(i)+"'>"+
						"<td>多幅演示效果"+(i)+"</td>"+
						"<td>"+
							JSON.stringify(e) +
						"</td>"+
					"</tr>");
			
				
			}); 
			
	 		//获取当前的行号
			var multi_index = $("#selectTable tbody tr ").size();
			
			
		 	singleEfects.forEach(function(e,i){
				$("#selectTable").append("<tr id='tr"+(i+multiEfects.length)+"'>"+
						"<td></td>"+
						"<td><a  onmouseover='showFloatInfo("+(i+multi_index+1)+");'><span hidden='hidden'>单幅</span>查看详情"+"</a></td>"+
						"<td>"+
							"<a href='javascript:deleteThisTr("+(i+multi_index)+","+1+");' >清除</a>"+
						"</td>"+
					"</tr>");
				console.log("****单幅迭代中的存行的json数据*******"+JSON.stringify(e));
				
			//隐式的table
			//将json对象放入隐藏区
				$("#selectTable_json_single tbody").append("<tr id='tr"+(i)+"'>"+
						"<td>单幅演示效果"+(i)+"</td>"+
						"<td>"+
							JSON.stringify(e) +
						"</td>"+
					"</tr>");
			
				
			}); 
			
			
		}
		  
		  </script>
	</head>
	
	<body  class="bgm" >
	<div id="part1">
	 <div id="header_background">
	<div id="thead">
		<div id="header_title">
			<div style="display: inline-block;">后台管理系统</div>
		</div>
		<div class="exit">
			<a href="login.jsp">退出</a>
		</div>
	</div>
	</div>
	<div id="navigation" >
	
	    <div class="nav_item_normal" ><a id="Main" href="getinfoList.do">设备信息</a></div>
	    <div class="nav_item_normal" ><a id="Alert" href="getserverinfo.do" >服务器配置</a></div>
	    <div class="nav_item_normal" ><a id="Event" href="toAdd.do" style="color:#cba300;">客户端配置</a></div>
	    <div class="nav_item_normal" ><a id="Linkage" href="linkageSet.jsp">联动策略配置</a></div>
	    <div class="nav_item_normal" ><a id="LinkageList" href="showPic_Linkage.do">联动策略列表</a></div>
  	</div>
  </div>
  
	<!-- 表单定位---------------------------------------------------------------------- -->
	<div id="form1">
	
	<fieldset id="group" class="fieldset">
	<legend >基本信息设置:</legend>
	
	<table class="table_c_addCfg">
		<tr>
			<td>
			</td>
			<td> 客服端区位：</td>
			<td>
				  <input id="client_position" class="input_text" type="text" name="Client_id" value="${config_Pis.client_Id }"/>
				  
		    </td>
		</tr>
		<tr>
			<td>
			</td>
			<td> 客服端IP：</td>
		    <td>
					 <input id="Local_ip" class="input_text" type="text" name="Local_ip" value="${ config_Pis.local_ip }" /><br/>
					 <input class="input_text" type="hidden" name="oldLocal_ip" value="${ config_Pis.local_ip }"/>
		    <div id="data_error" style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">IP格式有误</span></div>
		    </td>
		</tr>
	</table>
	
		<div id="error_info" style="display:none;background-color:#cceedd;position:relative;left:400px;border:1px black solid;width:390px;height:20px;"><span style="color:red;font-weight:bold;">区位号或者客户端ip与现有的冲突，请检查后更换</span></div>
		
		</fieldset>
		
		<!-- *****************************隐式div*************************** -->
		
		<div id="select_clientCode" style="display:none;background-color:#eeeeff;">
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
				<div id="serveplat1"><span>服务台</span></div>
				<div id="serveplat2"><span>服务台</span></div>
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
		
	<fieldset id="group1" class="fieldset" style="height:220px;">
	<legend >图片演示效果：</legend>
		<!--*******************************  演示效果类型****************************************-->
	<div style="position:relative;left:40%;">
			<input type="button" class="button" name="addEfect" value="添加演示效果" style="position:relative;left:37%;width:150px;height:30px;font-size:.3em;color:#000000;" onclick="addEfectToTable();"/>
	</div>
	<table class="table_clentCfg" style="position:relative;left:-2%;">
		<tbody>
		<tr>
			<td>演示效果类型</td>
			<td>
				<select id="efectType" class="efectType">
					<option value="">请选择</option>
					<option value="多幅特效" >多幅特效</option>
					<option value="单幅特效" >单幅特效</option>
				</select>
			</td>
		</tr>
		</tbody>
		
	</table>
	<!-- 多幅和单幅的特效类型分开  初始化为请选择 -->
	 	<table id="multiEfect" style="display:none;">
		<tr>
			 <td></td> 
			 <td>特效类型：</td> 
			<td>
				    <select id="MultiEfect" name="PicShow"  >
				   <%--  <c:forEach items="${info.effects.multi_Effects}" var="e" begin="0" varStatus="i"> --%>
				      <c:forEach items="${config_Pis.multi_Effects}" var="e" begin="0" varStatus="i">
				
						<option value=${ e.value }>${e.name}</option>
						
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
						//测试el表达式的输出
						console.log("******picShow*******");
							/* if("${info.client.pic_m[0].picShow}"=="${e.value}"){
								$("#MultiEfect option:eq(${e.value}").attr("selected","selected");
								
							}  */
						
						</script>
					</c:forEach>
					</select><br/>
					<div id="novalue_info" style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">请选择特效</span></div>
			</td>
			
		</tr>
		<tr>
			<td>
			
			</td>
			
			<td>图片路径:</td> 
			<td>
				    <select id="MutiResFolder" name="MutiResFolder" >
				  <%-- <c:forEach  items="${info.effects.pic_Mulit_url}" var="e" > --%>
				    <c:forEach  items="${config_Pis.pic_Mulit_url}" var="e" >
				  
					<option value="${e.value}">${e.name}</option>
					
					</c:forEach>  
				</select>
					<script>
						
					
					</script>
			</td>
				
		</tr>
		<tr>
			<td>
			</td>
				<td>随机播放：</td>
			<td>
				    <select id="MultiRandom" name="MutiRandom" >
						<option value="true">true</option>
						<option value="false">false</option>
					</select><br />
						<script>
						
						
						</script>
			</td>
		</tr>
		<tr>
			<td>
				
			</td>
				<td>演示时间：</td>
			<td>
					<input id="MutiTime" class="input_text" type="text"  name="MutiTime" value="${info.client.pic_m[0].time }" onclick="validatePlaytime('MutiTime')"/>
					<span style="color:black;">(秒)</span>
					<div  style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">必须是大于等于5秒的整数</span></div>
			</td>
		</tr>
	</table> 
		
		
		
		
		
<!-- 单幅 -->
	<table id="singleEfect" style="display:none;">
			<tr>
			<td>
			
			</td>
			
			<td>图片路径:</td> 
			<td>
				    <select id="singleResFolder" name="singleResFolder" >
				 <%--  <c:forEach  items="${info.effects.pic_Mulit_url}" var="e" > --%>
				   <c:forEach  items="${config_Pis.pic_Mulit_url}" var="e" >
				  
					<option value="${e.value}">${e.name}</option>
					
					</c:forEach>  
				</select>
					<script>
					
					
					</script>
			</td>
				
		</tr>
		<tr>
			<td>
				
			</td>
				<td>演示时间：</td>
			<td>
					<input id="singleTime" class="input_text" type="text"  name="singleTime" value="${info.client.pic_s[0].time }" onclick="validatePlaytime('singleTime')"/>
					<span style="color:black;">(秒)</span>
					<div  style="display:none;background-color:#cceedd;border:1px black solid;width:100px;"><span style="color:red;font-size:.65em;">必须是大于等于5秒的整数</span></div>
			</td>
		</tr>

	</table>
	</fieldset>
	<!-- 隐式table存放演示效果对象   初始化的数据迭代放入  分多幅和单幅 -->
	<!-- 多幅 -->
		<table id="selectTable_json_multi" hidden="hidden" style=" 
		position:absolute;
		z-index:10;
		word-break:break-all;font-size:.75em;
		left:1%;top:118px;
		width:18%;text-align:center;
		border-color:#eeeeff;border-bottom:0px solid #eeeeee;"  border="1"  >
			<thead >
				<tr>
					<th width="20%">多幅演示效果</th>
					<th width="80%">json对象</th>
				</tr>
			</thead>
			<tbody >
			</tbody>
		</table> 
		<!-- 单幅 -->
		<table id="selectTable_json_single" hidden="hidden" style=" 
		position:absolute;
		z-index:20;
		word-break:break-all;font-size:.75em;
		left:1%;top:118px;
		width:18%;text-align:center;
		border-color:#eeeeff;border-bottom:0px solid #eeeeee;"  border="1"  >
			<thead >
				<tr>
					<th width="20%">单幅演示效果</th>
					<th width="80%">json对象</th>
				</tr>
			</thead>
			<tbody >
			</tbody>
		</table> 
		
		<!-- 表格存放配置好的机器序号及播放顺序 显式   -->
	
		<table id="selectTable" 
		style="position:absolute;
		word-break:break-all;font-size:.75em;
		left:1%;top:118px;
		width:18%;text-align:center;
		border-color:#eeeeff;border-bottom:0px solid #eeeeee;"  border="1"  >
			<thead >
				<tr>
					<th width="25%">播放顺序</th>
					<th width="55%">添加特效</th>
					<th width="20%">操作</th>
				</tr>
			</thead>
			<tbody >
			</tbody>
		</table> 
	
	
	<!--提交-->
	
		<div id="submit_button" >
			<input id="chongzhi" class="button"  style="background-image:url('../images/reset.png');"type="button" name="reset" value="重置"
			onclick="reset_form();"/>
			
			<input id="tijiao" class="button" style="background-image:url('../images/tijiao.png');" type="button" name="tijiao" value="提交" />
			
		</div>
	
	</div><!-- 表单定位终 -->

	
	<div id="part3" >
 		 UCS Holdings CopyRight 2013
	</div>
	
	</body>
</html>
