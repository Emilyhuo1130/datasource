package com.org.zhang.controller;


import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.zhang.service.face.SecurityServiceInterFace;

@Controller
public class SecurityTest {
	@Resource
	private SecurityServiceInterFace dao;
	private String username;
	
	@RequestMapping(value="/test")
	@ResponseBody
	public boolean getinput(HttpServletRequest req,HttpServletRequest res){
		//security 获取用户信息
		Object principal =  SecurityContextHolder.getContext()  
		  .getAuthentication().getPrincipal();  
		if(principal instanceof UserDetails){  
		  username =((UserDetails)principal).getUsername();  
		  System.out.println(username);
		  Iterator it = ((UserDetails)principal).getAuthorities().iterator();  
		  String authority = "";  
		  while(it.hasNext()){  
		    authority = ((GrantedAuthority)it.next()).getAuthority();  
		    System.out.println("Authority:"+authority);  
		  }  
		} 
		boolean b=dao.getinput();
		return b;
	}
	
	
}
