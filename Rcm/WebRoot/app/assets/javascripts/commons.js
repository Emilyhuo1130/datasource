function  getRealPath(){
	var realpath = "/Rcm/Service";
	return realpath;
}

function PostJSONQuery(url_ , json, on_success)
{
	//window.confirm("ajax is ok");
  $.ajax({
    url:url_,
    type: "POST",
    data: JSON.stringify(json),
    dataType: "json",
    success: function(response) {
	 // window.confirm("ajax="+JSON.stringify(response));
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    
    }
  });
}