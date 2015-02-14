package com.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class UserLoginFilter implements Filter{
	Logger log=Logger.getLogger(getClass());
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * 拦截器***/
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		System.out.println("doFilter");
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		
		SysContent.setRequest(request);
		SysContent.setResponse(response);
		HttpSession session=request.getSession();
		String url=request.getRequestURI();
		log.debug("在拦截器中捕获的请求地址：          "+url);
//		if(session.getAttribute("Log_name")==null){
//			if(url!=null&&url.indexOf("login")<0){
//				response.sendRedirect("/SpringAop/jsp/login.jsp");
//				return;
//			}
//		}
		arg2.doFilter(arg0, arg1);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
