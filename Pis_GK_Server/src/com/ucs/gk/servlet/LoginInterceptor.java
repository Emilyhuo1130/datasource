package com.ucs.gk.servlet;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	private static String config_properties=System.getProperty("user.dir")+"/WEB-INF";
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest requert, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=requert.getSession();
		System.out.println("session:"+session.getId());
		System.out.println("获取请求的项目地址"+requert.getRequestURI());;
		System.out.println("获取请求的全链接地址"+requert.getRequestURL());;
		String username=(String)session.getAttribute("username");
		String password=(String)session.getAttribute("password");
		Properties p = new Properties();
		String login_admin=null;
		String login_pw=null;
		try{
			p.load(new FileInputStream(config_properties+"/admin_login.properties"));
			login_admin=p.getProperty("login.username");
			login_pw=p.getProperty("login.password");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if((username!=null)&&(password!=null)&&(username.equals(login_admin))&&(password.equals(login_pw))){
			return true;
		}else{
			response.sendRedirect("login.jsp");
			return false;
		}
		
	}

}
