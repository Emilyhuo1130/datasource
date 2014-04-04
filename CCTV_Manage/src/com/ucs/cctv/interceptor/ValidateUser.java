package com.ucs.cctv.interceptor;

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
import org.springframework.web.servlet.ModelAndView;



@SuppressWarnings("rawtypes")
public class ValidateUser implements Filter { 
	
	static Logger log = Logger.getLogger(ValidateUser.class);
	 

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain arg2) 
			throws IOException, ServletException {
		//转化为httpservlet
		log.info("*******进入过滤器***********");
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
    	log.info("*******请求路径:***********"+request.getRequestURI());
    	String uri = request.getRequestURI();
    	String action =uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));//前包后不包
    		HttpSession session=request.getSession();
    		String userAccount=(String)session.getAttribute("userAccount");
    		log.info("userAccount="+userAccount);
    		log.info("**********action************="+action);
    		if("login".equals(action)||userAccount!=null){
    		}else if(userAccount==null){
    			response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
    		}
    		arg2.doFilter(arg0, arg1);
    		return;
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}  
  
}  