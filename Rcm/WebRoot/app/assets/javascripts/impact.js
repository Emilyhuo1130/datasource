
/**********************************************策略定制***********************************************/
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
		  $.fn.zTree.init($("#impact_faultTree"), setting, systems);
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
		 //alert(JSON.stringify(response));
				  $("#equipment_name").val(response.equipmentName);
				  $("#equipment_id").val(response.equipmentId);
				  $("#system_name").val(response.systemName);
				  $("#equipmentDescription").val(response.equipmentDescription);
				  $("#malfunction_desc").val(response.type);//故障描述
				  $("#malfunction_impact").val(response.influence);//运营影响
				  $("#malfunction_yuanyin").val(response.faultCause);//故障原因
				  $("#malfunction_wiexiu").val(response.maintenancePolicy);//维修策略
				  faultType=$("#malfunction_desc").val();
				  
			    });
	 
}

/***************************************影响分析**************************************************/

function getimpactTree(query_criteria){
	
	  var operative_index_query_json = {
	   	    "systemName":"",
	    	"equipmentName":""
	    	};
	  if (query_criteria){
		  if(query_criteria.systemName){
			  operative_index_query_json.systemName=query_criteria.systemName;
		  }
		  if(query_criteria.equipmentName){
			  operative_index_query_json.equipmentName=query_criteria.equipmentName;
		  }
		 
		  
	  }
	  var Url="/Rcm/Manage/getimpactanalysttree.do";
	  PostJSONQuery(Url,operative_index_query_json,
	    function(response){
		  //alert(JSON.stringify(response));
		  var setting = {	};
		  var  systems=response.name;
		  $.fn.zTree.init($("#impact_faultTree"), setting, systems);
	    });
	
	 
	}
/***故障树节点搜索*/
function findFaultInfoOnAlertQueryimpact(){
	var val1=$("#condition1").val();
	var val2=$("#condition2").val();
	//var val3=$("#condition3").val();
	
	var query_criteria={
			"systemName":val1,
			"equipmentName":val2
			};
	if((query_criteria.equipmentName!=null)&&(query_criteria.equipmentName.trim().length>0)){
		getimpactTree(query_criteria);
		
	}
}


/**用于点击故障树和设备树的查询*/
var faultType=null;
var faultInfoID=0;

function showFaultInfo(id){
	query_criteria={
			"id":id
			};
	getFaultInfo(query_criteria);
	faultInfoID=id;
}


/**故障树撤销按钮和保存按钮*/
function cancelFaultlInfo(){
	if(faultType!=null){
		var operative_index_query_json = {
					"id":faultInfoID
		};
		 var Url="/Rcm/Manage/findFaultInfoByID.do";
		PostJSONQuery(Url,operative_index_query_json,
				function(response){
			
			$("#equipment_name").val(response.equipmentName);
			$("#equipment_id").val(response.equipmentId);
			$("#system_name").val(response.systemName);
			$("#equipmentDescription").val(response.equipmentDescription);//部件描述
			$("#malfunction_desc").val(response.type);//故障描述
			$("#malfunction_yuanyin").val(response.faultCause);//故障原因
			$("#malfunction_wiexiu").val(response.maintenancePolicy);//维修策略
			$("#malfunction_impact").val(response.influence);//运营影响
			
		});
		
	}
	 
}
function saveFaultlInfo(){
	if(faultInfoID!=0){
		if(confirm('确认保存已修改的信息?')){
			var val1=$("#malfunction_desc").val();
			var val2=$("#malfunction_yuanyin").val();
			var val3=$("#malfunction_wiexiu").val();
			var val4=$("#malfunction_impact").val();
			var operative_index_query_json = {
						"id":faultInfoID,
						"fultDescription":val1,
						"faultCause":val2,
						"maintenancePolicy":val3,
						"influence":val4
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


