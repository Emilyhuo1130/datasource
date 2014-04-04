
function setOptions(showRequest,url_val){
	var options = { 
            //target:        '#output2',   // target element(s) to be updated with server response
            beforeSubmit:  showRequest,  // pre-submit callback
            success:      function(response){
            	
            } ,  // post-submit callback

            // other available options: 
            url:       url_val,        // override for form's 'action' attribute
            type:      "post"        // 'get' or 'post', override for form's 'method' attribute
            //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
            //clearForm: true        // clear all form fields after successful submit 
            //resetForm: true        // reset the form after successful submit 

            // $.ajax options can be used here too, for example: 
            //timeout:   3000 
        };
	return options;
}


/**post请求**/
function PostJSONQuery(postURL,json, on_success)
{
  $.ajax({
    url: postURL,
    type:"post",
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    processData : true,
    data: json,
    dataType: "json",
    success: function(response) {
      on_success(response);
    },
    error: function (xhr, ajaxOptions, thrownError) {
    	//showAlertInfo("error");
    }
  });
}


