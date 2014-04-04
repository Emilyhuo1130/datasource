<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html  >
	<head>
	<title>后台管理系统</title>
		 <link type="text/css" rel="stylesheet" href="css/validate.css" /> 
		  <link type="text/css" rel="stylesheet" href="css/client.css" />
		<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
		 <script type="text/javascript" src="../js/jqueryPromit.js"></script>
		 <script type="text/javascript" src="../js/jqueryBlockUI.js"></script>
		 <script type="text/javascript" src="../js/utils.js"></script>
		 <script type="text/javascript" src="../js/gk.js"></script>
		 <%String linkageCode = request.getParameter("linkageCode"); %>
		 <script>
		 	var linkage_code = "<%=linkageCode%>";
		 	var g_ip=[];
		 	var g_order=[];
		 	var LinkInfoList=[];
		 	$(document).ready(function(){
		 		getLinkageOption();
		 		loadDetailData(linkage_code);//获得某条记录的详情
		 		selectEquipments();
		 		addNewEquipment();
		 		clearContent();
		 		validateTime();
				 submitForm();
			 		
		 		
		 		//alert(JSON.stringify("${Pic_LinkageInfo.time}"));
		 		
		 	});
		 	//初始加载数据
		 	function loadDetailData(linkageCode){
		 		
		 		var req_json={"linkageCode":linkageCode};
		 		PostJSONLoad(req_json,function(response){
		 			//alert(JSON.stringify(response));
		 			var linkageList=response.linkInfoList;
		 			var playOrder=response.playOrder;
		 			var multiScreenEffect=response.multiScreenEffect;
		 			var synchronizationPlay = response.synchronizationPlay;
		 			var time=response.time;
		 			$("#linkageType").val(multiScreenEffect);
		 			$("#linkagePlay").val(synchronizationPlay);
		 			$("#timeOfLinkage").val(time);
		 			$("#linkageEquipments").val("见左表");
		 			$("#playOrder").val(playOrder);
		 			//alert(linkageList);
		 		 linkageList.forEach(function(e,i){
		 				$("#selectTable tbody tr:eq("+i+") td:eq("+0+")").html(e.local_ip);
		 				$("#selectTable tbody tr:eq("+i+") td:eq("+1+")").html(e.playOrder);
		 				
		 			}); 
		 			
		 			
		 		});
		 		
		 	}
		 	//提交数据
		 	function ajaxManager(){
		 	LinkInfo=[];
		 		$("#selectTable tbody tr").each(function(i,e){
		 			var $e=$(e);
		 			if($e.children(0).html()!=""){
		 				 if(!isNaN(parseInt($e.children(0)[0].innerHTML))){
		 					//alert($e.children(0).html()+","+$e.children(0)[1].innerHTML);
		 					var linkInfo={"local_ip":$e.children(0)[0].innerHTML,"playOrder":$e.children(0)[1].innerHTML};
		 					LinkInfoList.push(linkInfo);
		 				} 
		 			}
		 			
		 		});
		 	
		 		var req_json=[
							{		"linkageCode":linkage_code,
									"multiScreenEffect":$("#linkageType").val(),
									"synchronizationPlay":$("#linkage_play").val(),
									"time":$("#timeOfLinkage").val(),
									"linkInfoList":LinkInfoList
							}
		 		              ];
		 		PostJSONLink(req_json,function(response){
		 			alert("更新成功");
		 			location.href="showPic_Linkage.do";
		 		});
		 	}
		 	//获得ip
		 	function getIp(clientPosition){
		 		var req_json={
		 				"client_Id":clientPosition,
		 				"linkageCode":linkage_code
		 				
		 		};
		 		PostJSONQuery_getIP(req_json,function(response){
		 			//alert(JSON.stringify(response));
		 			$("#linkageEquipments").val(response);
		 		});
		 	}
		 	
		 	function submitForm(){
		 		$("#c_submit").click(function(){
		 			ajaxManager();
		 			if($("input")[0].value!=""){
		 					
			 				return true;
		 			
		 			}else{
		 				alert("请确保填入全部信息");
		 				return false;
		 			}
		 			
		 		});
		 	}
		 	function  addNewEquipment(){
		 		var ifIn=true;
				$("#addNew").click(function(){
					 for(var i=0;i<26;i++){
						// alert($("#selectTable tbody tr:eq("+i+")").children(0).html()=="");
		 				if($("#selectTable tbody tr:eq("+i+")").children(0).html()==""){
		 					for(var j=0;j<i;j++){
		 						//alert($("#linkageEquipments").val()+","+$("#selectTable tbody tr:eq("+j+") td:eq("+0+")").html());
		 						if($("#linkageEquipments").val()==$("#selectTable tbody tr:eq("+j+") td:eq("+0+")").html()||
		 								$("#linkageEquipments").val()=="见左表"||$("#linkageEquipments").val()=="此区位尚未配置！"){
		 							ifIn=false;
		 							break;
		 						}else{
		 							ifIn=true;
		 						}
		 						if($("#playOrder").val()==$("#selectTable tbody tr:eq("+j+") td:eq("+1+")").html()){
		 							ifIn=false;
		 							break;
		 						}else{
		 							ifIn=true;
		 						}
		 						
		 					}//for over
		 					if(ifIn){
		 						$("#selectTable tbody tr:eq("+i+") td:eq("+0+")").html($("#linkageEquipments").val());
 			 					$("#selectTable tbody tr:eq("+i+") td:eq("+1+")").html($("#playOrder").val());
 			 					break;
		 					}
		 							
	 								
		 				};
		 			
		 			};
					});
				};
		 	function selectEquipments(){
		 		$("#select_clientCode table tr td div").click(function(){
		  			
		  			getIp($(this).html());
		  			
		  		});
		 	}
		 //清除按钮
		 function clearContent(){
			 $("table tbody tr td a").each(function(i,e){
				 var $e=$(e);
				$e.click(function(){
					//alert($(this).html());
				$e.parent().parent().find("td:eq(0)").html("");
				$e.parent().parent().find("td:eq(1)").html("");
				
				}); 
				 
			 });
			 
		 }
		//获取联动类型数据
		 	function getLinkageOption(){
		 		PostJSONGetLinkageOption(function(response){
		 			//alert(JSON.stringify(response));
		 			var optionList=response;
		 			for(var i=optionList.length-1;i>=0;i--){
		 				$("#linkageType").append("<option value="+optionList[i].value+">"+optionList[i].name+"</option>");
		 			}
		 		});
		 	}
		 	//验证表单数据
			function validateTime(){
				$("#timeOfLinkage").blur(function(){
					//alert(validateData(/^[1-9]\d{0,9}$/,$("#timeOfLinkage").val(),"timeOfLinkage_error","输入必须是正整数"));
					validateData(/^[1-9]\d{0,9}$/,$("#timeOfLinkage").val(),"timeOfLinkage_error","输入必须是正整数");
				});
			}
		 </script>
	</head>
	<body class="bgm">
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
	    <div class="nav_item_normal" ><a id="Alert" href="getserverinfo.do" style="color:#cba300;">服务器配置</a></div>
	    <div class="nav_item_normal" ><a id="Event" href="toAdd.do">客户端配置</a></div>
	    <div class="nav_item_normal" ><a id="Linkage" href="linkageSet.jsp">联动策略配置</a></div>
	    <div class="nav_item_normal" ><a id="LinkageList" href="showPic_Linkage.do">联动策略列表</a></div>
      
  	</div>
  </div>
  
	<div id="form1"><!-- 表单定位 -->
	<!-- <form action="#"  method="post" > -->
	<fieldset name="group1" class="fieldset">
	<legend >联动配置</legend>

	<table class="table">
		<tr>
			
			<td>
				<span>联动效果类型：</span> 
		    </td>
		    <td>
			 <select name="linkageType" id="linkageType">
			 		
			 </select>
			 
		    </td>
		</tr>
		<tr>
			<td>
				<span>联动时刻：</span>
			</td>
			
		    <td>
				<select id="linkage_play"  name="linkagePlay" value="" >
					<option value="1">整点</option>
					<option value="2">半点</option>
					<option value="3">一刻钟</option>
				</select>
		    </td>
		</tr>
		<tr>
			<td>
				<span>联动时长：</span>
			</td>
			
		    <td>
				<input type="text"  id="timeOfLinkage" name="timeOfLinkage" value="${Pic_LinkageInfo.time}"/> (秒)
				 <div id="timeOfLinkage_error" style="display:none;background-color:#cceedd;border:1px black solid;width:130px;"><span style="color:red;font-size:.65em;"></span></div>
		    </td>
		</tr>
		<tr>
			<td>
				<span>参与联动的设备：</span>
			</td>
			
		    <td>
				<input style="width:150px;background-color:#ffffff;" type="text" id="linkageEquipments" name="linkageEquipments"  readonly="readonly"  value=""/>
		    </td>
		</tr>
		<tr>
			<td>
				<span>该设备的播放列号：</span>
			</td>
			
		    <td>
					<select id="playOrder"  name="playOrder" value="" >
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					
				</select>
		    </td>
		</tr>
			<tr>
			<td>
				<span></span>
			</td>
			
		    <td>
				<button name="addNew" class="button_add" id="addNew">新增一个设备</button>
		    </td>
		</tr>
	</table>
	
	
					
				
		<div style="height:20px;"></div>
		<!-- 隐式div -->
		<div id="select_clientCode" style="display:block;background-color:#eeeeff; border:2px solid #ccccff;">
		<div id="table1">
		<table style="margin:0px;padding-left:25px;pdding-right:25px;">
			<tr>
				<td><div class="square">1</div></td>
				<td><div class="square">2</div></td>
				<td><div class="square">3</div></td>
				<td ><div class="square">4</div></td>
				
				<td id="td5"><div class="square">5</div></td>
				<td><div class="square">6</div></td>
				<td><div class="square">7</div></td>
				<td><div class="square">8</div></td>
				<td><div class="square">9</div></td>
				<td><div class="square">10</div></td>
				<td ><div class="square">11</div></td>
				<td id="td12"><div class="square">12</div></td>
				<td><div class="square">13</div></td>
				
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
	<div id="c_div" style="width:300px;height:60px;margin-top:20px;">
		<input id="c_reset" class="button" style="height:40px;position:relative;left:560px;" type="button" name="server_reset" value="重置"onclick="new function(){
			document.location.reload();		
			
		}"/>
		<input id="c_submit" class="button" style="height:40px;position:relative;left:560px;" type="submit" name="server_submit" value="提交"/>
	</div>

	</fieldset>
	<!-- 表格存放配置好的机器序号及播放顺序 -->
	
		<table id="selectTable" style=" 
		position:absolute;
		word-break:break-all;font-size:.75em;
		left:-150px;top:118px;
		width:30%;height:605px;text-align:center;
		border-color:#eeeeff;border-bottom:0px solid #eeeeee;"  border="1"  >
			<thead >
				<tr>
					<th width="45%">设备IP</th>
					<th width="40%">播放顺序</th>
					<th >清除</th>
				</tr>
			</thead>
			
			<tbody >
			
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				<tr style="border-bottom:1px solid #aaaaaa;"><td></td><td></td><td><a href="javascript:;" id="clear_td1">清除</a></td></tr>
				
		
			</tbody>
		</table> 
	<!-- </form> -->
	
	</div><!-- 表单定位终 -->
	
	<div id="part3" >
  		UCS Holdings CopyRight 2013
	</div>
	
	
	</body>
</html>
