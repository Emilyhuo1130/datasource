package com.interrceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/****
 * 拦截器
 * 
 * **/
public class UserLoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest requert, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String login_admin="admin";
		String login_pw="123456";
		String login_user="zhang";
		
		
		HttpSession session=requert.getSession();
		
		String adminName=(String)session.getAttribute("adminName");
		String userName=(String)session.getAttribute("userName");
		String password=(String)session.getAttribute("password");
		
		
		if((userName!=null)&&(userName.equals(login_user))){
			return true;
		}else{
			
			response.sendRedirect(requert.getContextPath()+"/index.jsp");
			return false;
		}
	}

}
