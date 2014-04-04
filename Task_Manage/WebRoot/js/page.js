var totalResponse = null;
var responseTableObj = null;
var lastPage = 1;//总页数
var pageCute = 1;//当前页



//根据分页返回array
function pageResponse(pagecute) {
	pageCute = pagecute;
	var pageLines = 10;//每页10条
	var resultLines = totalResponse.list.length;//总结果数
	lastPage = parseInt(resultLines / pageLines) + ((resultLines % pageLines > 0) ? 1 : 0);//总页数
	var str = "";
	var j = 0;
	responseTableObj = new Array();
	for (i = (pagecute - 1) * pageLines; i < pagecute * pageLines; i++) {
		if (i < resultLines) {
			responseTableObj[j] = (totalResponse.list)[i];
			j++;
		}
	}
	var str = "";
	for (i = 1; i <= lastPage; i++) {
		if (i == pagecute) {
			str += "<font color='#f00110'>" + i + "/" + lastPage + "</font>&nbsp;";
		}
	}
	document.getElementById("pageshow").innerHTML = str;
	return responseTableObj;
}

function imgshowpage(pagestr, prx) {
	if(totalResponse == null ||totalResponse.list == null ||  totalResponse.list.length ==0)
	{
		return;
	}
	if (pagestr == "first") {
		document.getElementById("first").src = prx + "images/microsoftLook/first(off).gif";
		document.getElementById("last").src = prx + "images/microsoftLook/last(on).gif";
		show(1);
	} else {
		if (pagestr == "pre") {
			if (pageCute == 1) {
				window.event.returnValue = false;
			} else {
				document.getElementById("last").src = prx + "images/microsoftLook/last(on).gif";
				if ((eval(pageCute - 1)) == 1) {
					document.getElementById("first").src = prx + "images/microsoftLook/first(off).gif";
				}
				show(eval(pageCute - 1));
			}
		} else {
			if (pagestr == "next") {
				if (pageCute == lastPage) {
					window.event.returnValue = false;
				} else {
					document.getElementById("first").src = prx + "images/microsoftLook/first(on).gif";
					if ((eval(pageCute + 1)) == lastPage) {
						document.getElementById("last").src = prx + "images/microsoftLook/last(off).gif";
					}
					show(eval(pageCute + 1));
				}
			} else {
				if (pagestr == "last") {
				    document.getElementById("first").src = prx + "images/microsoftLook/first(on).gif";
					document.getElementById("last").src = prx + "images/microsoftLook/last(off).gif";
					show(lastPage);
				}
			}
		}
	}
}
function go() {
	var pageNo = document.getElementById("pageNo").value;
	var re = /^[0-9]*[1-9][0-9]*$/;
	if (!re.test(pageNo)) {
		document.getElementById("pageNo").value = "";
		alert("请输入一个正整数!");
	} else {
		if (totalResponse == null ||totalResponse.list == null ||
				 totalResponse.list.length ==0 || pageNo > lastPage) {
			document.getElementById("pageNo").value = "";
		} else {
			pageCute = parseInt(pageNo);
			show(pageCute);
		}
	}
}

