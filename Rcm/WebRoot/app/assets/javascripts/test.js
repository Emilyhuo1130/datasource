


function OnMainPageQuery(){
  var alert_query_json = {
      "page":{
        "pageCount":10,
        "pageNo":1
      },     
      "query":{
        "endDate":"2099-1-1",
        "equipmentname":"",
        "lineNo":"10",
        "startDate":"1990-1-1",
        "stationName":"",
        "statments":"",
        "systemName":"",
        "warningType":"",
        "warningTypeLevel":""
      }
  };
  
  
 //alert(JSON.stringify(query_criteria));
  var Url="/Rcm/Manage/getWaringList.do";
  PostQuery(Url,alert_query_json,function(response){
	 alert(JSON.stringify(response));
    });  
}
