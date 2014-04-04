package com.ucs.gk.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
import org.apache.log4j.PropertyConfigurator;

public class AdminLoginFilter implements Filter{
	static Logger log = Logger.getLogger(AdminLoginFilter.class);
	private static String config_properties;
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
			config_properties=System.getProperty("user.dir")+"/WEB-INF";
			log.info("------------进行拦截---------------------");
		 HttpServletResponse response = (HttpServletResponse) arg1;    
         HttpServletRequest request=(HttpServletRequest)arg0;  
         HttpSession session = request.getSession(true);   
         String username=(String)session.getAttribute("username");
 		 String password=(String)session.getAttribute("password");
 		Properties p = new Properties();
		String login_admin=null;
		String login_pw=null;
		String url=request.getRequestURI();
		try{
			p.load(new FileInputStream(config_properties+"/admin_login.properties"));
			login_admin=p.getProperty("login.username");
			login_pw=p.getProperty("login.password");
		}catch(Exception e){
			e.printStackTrace();
		}
		if((username==null)&&(password==null)&& ( url.indexOf("Login")<0 && url.indexOf("login")<0 )){
				response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
				return;
		}
		arg2.doFilter(arg0, arg1);  
         
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("******************拦截器加载**************************");
		log.info(config.getServletContext().getRealPath("/"));
		System.setProperty("user.dir", config.getServletContext().getRealPath("/"));
		PropertyConfigurator.configure(System.getProperty("user.dir")+"WEB-INF/log4j.properties") ;
		log.info(System.getProperty("user.dir"));
	}

}
