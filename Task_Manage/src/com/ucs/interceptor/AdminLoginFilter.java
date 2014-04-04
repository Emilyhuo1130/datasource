package com.ucs.interceptor;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String url=request.getRequestURI();
		HttpSession session=request.getSession();
		String userAccount=(String)session.getAttribute("userAccount");
		String adminAccount=(String)session.getAttribute("adminAccount");
		if((userAccount==null&&adminAccount==null)&&(url.indexOf("login")<0)){
			response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
			return;
		}else{
			if((userAccount!=null&&adminAccount==null)&&(url.indexOf("main.")>0)){
				response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
				return;
			}else if((userAccount==null&&adminAccount!=null)&&(url.indexOf("main_normal.")>0)){
				response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
				return;
			}
		}
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("---------------------拦截器加载---------------------");
	}

}
