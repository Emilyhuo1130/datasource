function OnSCContentReceived(xmlhttp)
{
  if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
  {
    //alert(xmlhttp.responseText);
    var table_body = document.getElementById("query_result_table_body");
    $("#query_result_table_body").empty();
    //table_body.rows.clear();
    table_body.innerHTML = xmlhttp.responseText;
    TableInitial('query_result_table', 10, 15);
  }
}

function OnSCQuery()
{
  LoadXMLDoc('/event/sc_content', 'POST', OnSCContentReceived);
}


function OnFacilityContentReceived(xmlhttp)
{
  if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
  {
    //alert(xmlhttp.responseText);
    var table_body = document.getElementById("facility_index_query_result_table_body");
    $("#facility_index_query_result_table_body").empty();
    //table_body.rows.clear();
    table_body.innerHTML = xmlhttp.responseText;
    TableInitial('facility_index_query_result_table', 9);
  }
}

function OnFacilityQuery()
{
  LoadXMLDoc('/event/facility_content', 'POST', OnFacilityContentReceived);
}

function OnOperativeContentReceived(xmlhttp)
{
  if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
  {
    //alert(xmlhttp.responseText);
    var table_body = document.getElementById("operative_index_query_result_table_body");
    $("#operative_index_query_result_table_body").empty();
    //table_body.rows.clear();
    table_body.innerHTML = xmlhttp.responseText;
    TableInitial('operative_index_query_result_table', 6);
  }
}

function OnOperativeQuery()
{
  LoadXMLDoc('/event/operative_content', 'POST', OnOperativeContentReceived);
}









/**设备树查询按钮*/
function OnAlertQuery(){
	var val0=$("#condition2").val();
	var val1=$("#condition1").val();
	var val2=$("#condition5").val();
	var val3=$("#condition4").val();
	
	var query_criteria={
			"id":0,
			"stationName":val0,
			"systemName":val1,
			"component":val2,
			"subComponent":val3
			};
	if((query_criteria.component!=null)&&(query_criteria.component.trim().length>0)){
		//展开节点
		getEquipmentTree(query_criteria);
		//查找指定部件的信息
		getEquipmentInfo(query_criteria);
	}
}


	function getEquipmentTree(query_criteria){
	
	  var operative_index_query_json = {
	    	"stationName":"",
	    	"systemName":"",
	    	"component":"",
	    	"subComponent":""
	    	};
	  if (query_criteria){
		  if(query_criteria.stationName){
			  operative_index_query_json.stationName=query_criteria.stationName;
		  }
		  if(query_criteria.systemName){
			  operative_index_query_json.systemName=query_criteria.systemName;
		  }
		  if(query_criteria.component){
			  operative_index_query_json.component=query_criteria.component;
		  }
		  if(query_criteria.subComponent){
			  operative_index_query_json.subComponent=query_criteria.subComponent;
		  }
		
	  }
	  var Url="/Rcm/Manage/getAllEquipmentTree.do";
	  PostJSONQuery(Url,operative_index_query_json,
	    function(response){
		  var setting = {	};
		  var  systems=response.name;
		  $.fn.zTree.init($("#treeDemo"), setting, systems);
	    });
	
	}
	
	function getEquipmentInfo(query_criteria){
		
		  var operative_index_query_json = {
		    	"id":0,
		    	"stationName":"",
		    	"systemName":"",
		    	"component":"",
		    	"subComponent":""
		    	};
		  if (query_criteria){
			  if(query_criteria.stationName){
				  operative_index_query_json.stationName=query_criteria.stationName;
			  }
			  if(query_criteria.systemName){
				  operative_index_query_json.systemName=query_criteria.systemName;
			  }
			  if(query_criteria.component){
				  operative_index_query_json.component=query_criteria.component;
			  }
			  if(query_criteria.subComponent){
				  operative_index_query_json.subComponent=query_criteria.subComponent;
			  }
			  if(query_criteria.id!=0){
				  operative_index_query_json.id=query_criteria.id;
			  }
		  }
		  var Url="/Rcm/Manage/searchEquipmentInfo.do";
		  var level={"1":"一级","2":"二级","3":"三级","4":"四级"};
		  PostJSONQuery(Url,operative_index_query_json,
		    function(response){
			  $("#equipment_name").val(response.component);
			  $("#equipment_id").val(response.subComponentid);
			  $("#system_name").val(response.systemName);
			  $("#equipmentDescription").val(response.subComponent);
			  $("#level").val(level[response.level]);
			  $("#upEquipment").val(response.subSystemName);
			 // $("#equipment_name").val(response.equipmentName);
			  
		    });
		
		}
	
	/**故障树*/
	function getFaultTree(query_criteria){
		  var operative_index_query_json = {
		    	"systemName":"",
		    	"equipmentName":"",
		    	"subCom":""
		    	};
		  if (query_criteria){
			  if(query_criteria.systemName){
				  operative_index_query_json.systemName=query_criteria.systemName;
			  }
			  if(query_criteria.equipmentName){
				  operative_index_query_json.equipmentName=query_criteria.equipmentName;
			  }
			  if(query_criteria.subCom){
				  operative_index_query_json.subCom=query_criteria.subCom;
			  }
			  
		  }
		  var Url="/Rcm/Manage/getFaultTree.do";
		  PostJSONQuery(Url,operative_index_query_json,
		    function(response){
			  var setting = {	};
			  var  systems=response.name;
			  $.fn.zTree.init($("#mal_faultTree"), setting, systems);
		    });
		
		 
		}
	/***故障树节点搜索*/
	function findFaultInfoOnAlertQuery(){
		var val1=$("#condition1").val();
		var val2=$("#condition2").val();
		var val3=$("#condition3").val();
		
		var query_criteria={
				"systemName":val1,
				"equipmentName":val2,
				"subCom":val3
				};
		if((query_criteria.equipmentName!=null)&&(query_criteria.equipmentName.trim().length>0)){
			getFaultTree(query_criteria);
		}
	}
	
	function getFaultInfo(query_criteria){
		var operative_index_query_json = {
			    	"id":0,
			    	"systemName":"",
			    	"equipmentName":"",
			    	"subcomponent":""
			    	};
		
		 if (query_criteria){
			  if(query_criteria.systemName){
				  operative_index_query_json.systemName=query_criteria.systemName;
			  }
			  if(query_criteria.equipmentName){
				  operative_index_query_json.equipmentName=query_criteria.equipmentName;
			  }
			  if(query_criteria.subCom){
				  operative_index_query_json.subcomponent=query_criteria.subCom;
			  }
			  if(query_criteria.id!=0){
				  operative_index_query_json.id=query_criteria.id;
			  }
		  }
		 var Url="/Rcm/Manage/getFaultinfo.do";
		 PostJSONQuery(Url,operative_index_query_json,
				    function(response){
					  $("#equipment_name").val(response.equipmentName);
					  $("#equipment_id").val(response.equipmentId);
					  $("#system_name").val(response.systemName);
					  $("#equipmentDescription").val(response.equipmentDescription);
					  $("#malfunction_desc").val(response.type);
					  $("#malfunction_reason").val(response.faultCause);
					  $("#repair_guide").val(response.maintenancePolicy);
					  faultType=$("#malfunction_desc").val();
				    });
		 
	}
	
	
	
	
	
	var faultType=null;
	var faultInfoID=0;
/**用于点击故障树和设备树的查询*/
function showEquipmentInfo(id){
	query_criteria={
			"id":id
	};
	
	getEquipmentInfo(query_criteria);
}

function showFaultInfo(id){
	query_criteria={
			"id":id
	};
	faultInfoID=id;
	getFaultInfo(query_criteria);
	
}
/**故障树撤销按钮很保存按钮*/
/*function cancelFaultlInfo(){
	if(faultType!=null){
		var operative_index_query_json = {
				"method":"findFaultInfoByFaultType",
				"req":{
					"faultType":faultType
				},
				"type":"StrategyManager"
		};
		
		 var Url="/Rcm/Manage/findFaultInfoByFaultType.do";
		PostJSONQuery(operative_index_query_json,
				function(response){
			
			$("#equipment_name").val(response.equipmentName);
			$("#equipment_id").val(response.equipmentId);
			$("#system_name").val(response.systemName);
			$("#equipmentDescription").val(response.equipmentDescription);//部件描述
			$("#malfunction_desc").val(response.type);//故障描述
			$("#malfunction_reason").val(response.faultCause);//故障原因
			$("#repair_guide").val(response.maintenancePolicy);//维修策略
			
		});
		
	}
	 
}*/
function saveFaultlInfo(){
	if(faultInfoID!=0){
		if(confirm('确认保存已修改的信息?')){
			var val1=$("#malfunction_desc").val();
			var val2=$("#malfunction_reason").val();
			var val3=$("#repair_guide").val();
			var operative_index_query_json = {
					
						"id":faultInfoID,
						"fultDescription":val1,
						"faultCause":val2,
						"maintenancePolicy":val3
			};
			 var Url="/Rcm/Manage/updateFaultInfo.do";
			PostJSONQuery(Url,operative_index_query_json,
					function(response){
				if(response!=null){
					alert("更新成功！");
				}
			});
			
		}
	}
	 
}