package com.test.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 通过拦截器则添加request信息
 * 在面向切面中可以使用
 * 
 * **/
public class SysContent {
	public static ThreadLocal<HttpServletResponse> local_response=new ThreadLocal<HttpServletResponse>();
	public static ThreadLocal<HttpServletRequest> local_request=new ThreadLocal<HttpServletRequest>();
	
	
	public static HttpServletRequest getRequest(){
		return local_request.get();
	}
	public static  void setRequest(HttpServletRequest req){
		local_request.set(req);
	}
	public static HttpServletResponse getResponse(){
		return local_response.get();
	}
	public static void setResponse(HttpServletResponse res){
		local_response.set(res);
	}
	
	public HttpSession getsession(){
		return (HttpSession)local_request.get().getSession();
	}
}
