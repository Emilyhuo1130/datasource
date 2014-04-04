package com.ucs.rcm.reqobj;
/**ajax请求的主体部分*/

public class HttpRequest {
	private String type;//从页面上接收到的方法的类名
	
	private String method;//从页面上接受到的方法的名称
	private Object req;//以obj格式传输的主要参数内容
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object getReq() {
		return req;
	}
	public void setReq(Object req) {
		this.req = req;
	}


}
