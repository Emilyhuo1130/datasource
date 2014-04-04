package com.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter_face.LoginInterface;
import com.inter_face.ManageInterface;

@Controller
public class Login {
	@Resource
	private LoginInterface logdao;
	
	@RequestMapping(value="/jsp/login")
	@ResponseBody
	public boolean login(HttpServletRequest req,HttpServletResponse res){
		System.out.println(req.getParameter("username")+"   "+req.getParameter("password"));
		HttpSession session=req.getSession();
		session.setAttribute("Log_name", req.getParameter("username"));
		session.setAttribute("Log_password", req.getParameter("password"));
		return logdao.login("zhang", "123456");
	}
}
