package com.org.zhang.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.org.zhang.pojo.UsersInfo;
import com.org.zhang.pojo.tuils.Parameters;
import com.org.zhang.service.face.UserControllerFace;
import com.sun.faces.context.RequestMap;



@Controller
public class UserController {
	private final Parameters  parameters = new Parameters(); 
	Logger log=Logger.getLogger(UserController.class);
	@Resource
	private UserControllerFace service;
	
	
	/**
	 * @public_verifyUserName
	 * 验证用户名是否注册过
	 * @boolean true注册过
	 * **/
	@RequestMapping(value="/public_verifyUserName@UserController",method=RequestMethod.POST)
	@ResponseBody
	public boolean public_verifyUserName(HttpServletRequest req,HttpServletResponse res){
		return service.public_verifyUserName(req.getParameter("userName"));
	}
	/****
	 * @public_addUserInfo
	 * 添加用户
	 * @boolean true 添加成功
	 * ***/
	@RequestMapping(value="/public_addUserInfo@UserController",method=RequestMethod.POST)
	@ResponseBody
	public boolean public_addUserInfo(HttpServletRequest req,HttpServletResponse res){
		UsersInfo user=parameters.toPojo(new UsersInfo(), req);
		
		return user==null? false:service.addUserInfo(user);
	}
	/**
	 * @login
	 * 用户登录
	 * @ModelAndView
	 * */
	@RequestMapping(value="/login@UserController",method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest req,HttpServletResponse res){
		String userName=null;
		Object principal =  SecurityContextHolder.getContext()  
		  .getAuthentication().getPrincipal();  
		if(principal instanceof UserDetails){  
		 userName =((UserDetails)principal).getUsername();  
		 
		  Iterator it = ((UserDetails)principal).getAuthorities().iterator();  
		  String authority = "";  
		  while(it.hasNext()){  
		    authority = ((GrantedAuthority)it.next()).getAuthority();  
		    log.info("Authority:"+authority);  
		  }  
		} 
		req.getSession().setAttribute("userName", userName);

		return new ModelAndView("Main","res",userName);
	}
	
	/**
	 * @showMain
	 * 显示用户首页
	 * **/
	@RequestMapping(value="/showMain@UserController",method=RequestMethod.GET)

	public ModelAndView showMain(HttpServletRequest req,HttpServletResponse res){
		String name=(String)req.getSession().getAttribute("userName");
		return new ModelAndView("Main","res",name);
	}
	
	
	
	/**
	 * @deleteSession
	 * 用户登出并且删除session
	 * **/
	@RequestMapping(value="/deleteSession@UserController",method=RequestMethod.GET)

	public ModelAndView deleteSession(HttpServletRequest req,HttpServletResponse res){
		req.getSession().removeAttribute("userName");
		try {
			res.sendRedirect(req.getContextPath()+"/j_spring_security_logout");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("index");
	}
	
	
}
