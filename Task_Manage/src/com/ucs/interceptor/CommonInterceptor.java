package com.ucs.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
  


import org.springframework.web.servlet.HandlerInterceptor;  
import org.springframework.web.servlet.ModelAndView;  

import com.ucs.connect.date.GetUserResponse;


public class CommonInterceptor implements HandlerInterceptor {  

	 
	
  
    //在业务处理器处理请求之前被调用 
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
    	System.out.println(request.getRequestURI());
    	String url=request.getRequestURI();
    	int num_user=url.indexOf("user");
    	int num_admin=url.indexOf("admin");
    	System.out.println(num_user+"-------------------------");
    	System.out.println(num_admin+"-------------------------");
    	if(num_user!=-1){
    		HttpSession session=request.getSession();
    		String userAccount=(String)session.getAttribute("userAccount");
    		String userPassWord=(String)session.getAttribute("userPassWord");
    		if((userAccount!=null)&&(userPassWord!=null)){
    			return true;
    		}else{
    			response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
    			return false;
    		}
    		
    	}else if(num_admin!=-1){
    		HttpSession session=request.getSession();
    		String adminAccount=(String)session.getAttribute("adminAccount");
    		String adminPassWord=(String)session.getAttribute("adminPassWord");
    		System.out.println(adminAccount+adminPassWord);
    		if((adminAccount!=null)&&(adminPassWord!=null)){
    			return true;
    		}else{
    			response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
    			return false;
    		}
    		
    	}else{
    		response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
			return false;
    	}
    	
    }  
    //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
        // TODO Auto-generated method stub  
    }  
  
    // 在DispatcherServlet完全处理完请求后被调用   
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
        // TODO Auto-generated method stub  
    }  
  
}  